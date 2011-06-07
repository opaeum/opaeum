package org.nakeduml.tinker.test.generator;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import net.sf.nakeduml.emf.load.EmfWorkspaceLoader;
import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.TransformationProcess;
import net.sf.nakeduml.feature.TransformationStep;
import net.sf.nakeduml.javageneration.JavaTextSource;

import org.nakeduml.tinker.composition.tinker.TinkerExtendedCompositionSemanticsJavaStep;

public class Generate {

	protected File outputRoot;
	protected File modelFile;
	protected TransformationProcess process = new TransformationProcess();

	public static void main(String[] args) throws Exception {
		File model = new File("../TestModels/Models/tinker/tinker.uml");
		File outputRoot = new File("../tinker/");
		System.out.println(outputRoot.getAbsolutePath());
		Generate g = new Generate(outputRoot, model);
		g.generate();
	}

	public Generate(File outputRoot, File modelFile) {
		super();
		this.outputRoot = outputRoot;
		this.modelFile = modelFile;
	}

	private void generate() throws Exception {
		long start = System.currentTimeMillis();
		EmfWorkspace workspace = EmfWorkspaceLoader.loadSingleModelWorkspace(modelFile, outputRoot.getName());
		workspace.setDirectoryName(outputRoot.getName());
		NakedUmlConfig cfg = buildConfig(workspace);
		cfg.store();
		process.execute(cfg, workspace, getSteps());
		workspace.getMappingInfo().store();
		System.out.println("Generating code for model '" + modelFile.getName() + "' took " + (System.currentTimeMillis() - start) + " ms");
	}

	@SuppressWarnings("unchecked")
	private Set<Class<? extends TransformationStep>> getSteps() {
		return toSet(net.sf.nakeduml.javageneration.basicjava.BasicJavaModelStep.class,
				net.sf.nakeduml.javageneration.composition.ExtendedCompositionSemanticsJavaStep.class,
				net.sf.nakeduml.emf.extraction.StereotypeApplicationExtractor.class, TinkerExtendedCompositionSemanticsJavaStep.class);
	}

	protected NakedUmlConfig buildConfig(EmfWorkspace workspace) throws IOException {
		NakedUmlConfig cfg = new NakedUmlConfig();
		cfg.setOutputRoot(outputRoot);
		cfg.load(new File(modelFile.getParent(), workspace.getDirectoryName() + "-nakeduml.properties"), workspace.getName());
		cfg.store();
		mapOutputRoots(cfg);
		return cfg;
	}

	protected void mapOutputRoots(NakedUmlConfig cfg) {
		mapDomainProjects(cfg);
	}

	private void mapDomainProjects(NakedUmlConfig cfg) {
		cfg.mapOutputRoot(JavaTextSource.OutputRootId.DOMAIN_GEN_SRC, true, "", "../src/main/generated-java");
	}

	protected static Set<Class<? extends TransformationStep>> toSet(Class<? extends TransformationStep>... classes) {
		return new HashSet<Class<? extends TransformationStep>>(Arrays.asList(classes));
	}
}
