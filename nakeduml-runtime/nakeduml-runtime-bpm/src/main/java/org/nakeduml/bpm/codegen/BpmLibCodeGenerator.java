package org.nakeduml.bpm.codegen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import net.sf.nakeduml.emf.extraction.StereotypeApplicationExtractor;
import net.sf.nakeduml.emf.load.EmfWorkspaceLoader;
import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.OutputRoot;
import net.sf.nakeduml.feature.TransformationProcess;
import net.sf.nakeduml.feature.TransformationStep;
import net.sf.nakeduml.javageneration.CharArrayTextSource;
import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.javageneration.jbpm5.MessageMarshallingImplementor;
import net.sf.nakeduml.javageneration.jbpm5.ProcessStepResolverImplementor;
import net.sf.nakeduml.javageneration.oclexpressions.OclExpressionExecution;
import net.sf.nakeduml.javageneration.oclexpressions.OclTestsStep;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nakeduml.generation.features.BpmUsingJbpm5;
import org.nakeduml.generation.features.ExtendedCompositionSemantics;
import org.nakeduml.generation.features.IntegrationTests;
import org.nakeduml.generation.features.PersistenceUsingHibernate;

public class BpmLibCodeGenerator{
	protected TransformationProcess process = new TransformationProcess();
	protected File outputRoot;
	private File modelFile;
	private ResourceSet resourceSet;
	public static void main(String[] args) throws Exception{
		new BpmLibCodeGenerator().generateCodeForSingleModel();
	}
	protected BpmLibCodeGenerator(){
		this.resourceSet=EmfWorkspaceLoader.setupStandAloneAppForUML2();

		this.outputRoot = new File(".").getAbsoluteFile().getParentFile().getParentFile();
	}
	protected void generateCodeForSingleModel() throws Exception,IOException,FileNotFoundException{
		modelFile = new File("/home/ampie/workspace_sandbox/nakeduml/nakeduml-core/org.nakeduml.metamodels/models/libraries/NakedUMLLibraryForBPM.uml");
		EmfWorkspace workspace = EmfWorkspaceLoader.loadSingleModelWorkspace(resourceSet, modelFile, "nakeduml-runtime");
		NakedUmlConfig cfg = buildConfig(workspace);
		cfg.store();
		process.execute(cfg, workspace, getSteps());
		workspace.getMappingInfo().store();
	}
	protected NakedUmlConfig buildConfig(EmfWorkspace workspace) throws IOException{
		NakedUmlConfig cfg = new NakedUmlConfig();
		cfg.setOutputRoot(outputRoot);
		cfg.load(new File(modelFile.getParentFile(), "bpm-nakeduml.properties"), workspace.getName());
		cfg.store();
		mapOutputRoots(cfg);
		return cfg;
	}
	protected void mapOutputRoots(NakedUmlConfig cfg){
		mapDomainProjects(cfg);
		mapAdaptorProjects(cfg);
	}
	protected Set<Class<? extends TransformationStep>> getSteps(){
		Set<Class<? extends TransformationStep>> steps = new HashSet<Class<? extends TransformationStep>>();
		steps.add(PersistenceUsingHibernate.class);
		steps.add(OclExpressionExecution.class);
		steps.add(MessageMarshallingImplementor.class);
		steps.add(StereotypeApplicationExtractor.class);
		steps.add(OclTestsStep.class);
		steps.add(ExtendedCompositionSemantics.class);
		steps.add(BpmUsingJbpm5.class);
		steps.add(IntegrationTests.class);
		steps.add(ProcessStepResolverImplementor.class);
		return steps;
	}
	private void mapDomainProjects(NakedUmlConfig cfg){
		OutputRoot generatedJava = cfg.mapOutputRoot(JavaTextSource.OutputRootId.DOMAIN_GEN_SRC, true, "-bpm", "src/main/generated-java");
		cfg.mapOutputRoot(JavaTextSource.OutputRootId.DOMAIN_GEN_TEST_SRC, true, "-bpm", "src/test/generated-java");
		OutputRoot domainSrc = cfg.mapOutputRoot(JavaTextSource.OutputRootId.DOMAIN_SRC, true, "-bpm", "src/main/java");
		domainSrc.dontCleanDirectoriesOrOverwriteFiles();
		OutputRoot domainTestSrc = cfg.mapOutputRoot(JavaTextSource.OutputRootId.DOMAIN_TEST_SRC, true, "-bpm", "src/test/java");
		domainTestSrc.dontCleanDirectoriesOrOverwriteFiles();
		cfg.mapOutputRoot(CharArrayTextSource.OutputRootId.DOMAIN_GEN_TEST_RESOURCE, true, "-bpm", "src/test/generated-resources");
		cfg.mapOutputRoot(CharArrayTextSource.OutputRootId.DOMAIN_GEN_RESOURCE, true, "-bpm", "src/main/generated-resources");
	}
	private void mapAdaptorProjects(NakedUmlConfig cfg){
		cfg.mapOutputRoot(JavaTextSource.OutputRootId.ADAPTOR_GEN_SRC, true, "-bpm", "src/main/generated-java");
		cfg.mapOutputRoot(CharArrayTextSource.OutputRootId.ADAPTOR_GEN_RESOURCE, true, "-bpm", "src/main/generated-resources");
		cfg.mapOutputRoot(JavaTextSource.OutputRootId.ADAPTOR_GEN_TEST_SRC, true, "-bpm", "src/test/generated-java");
		OutputRoot testSource = cfg.mapOutputRoot(JavaTextSource.OutputRootId.ADAPTOR_TEST_SRC, true, "-bpm", "src/test/java");
		testSource.dontCleanDirectories();
		OutputRoot jbossResources = cfg.mapOutputRoot(CharArrayTextSource.OutputRootId.ADAPTOR_TEST_RESOURCE_JBOSSAS, true, "-bpm",
				"src/test/jboss-resources");
		jbossResources.dontCleanDirectoriesOrOverwriteFiles();
		OutputRoot testResources = cfg.mapOutputRoot(CharArrayTextSource.OutputRootId.ADAPTOR_TEST_RESOURCE, true, "-bpm", "src/test/resources");
		testResources.dontCleanDirectoriesOrOverwriteFiles();
		cfg.mapOutputRoot(CharArrayTextSource.OutputRootId.ADAPTOR_GEN_TEST_RESOURCE, true, "-bpm", "src/test/generated-resources");
		OutputRoot mainResources = cfg.mapOutputRoot(CharArrayTextSource.OutputRootId.ADAPTOR_RESOURCE, true, "-bpm", "src/main/resources");
		mainResources.dontCleanDirectoriesOrOverwriteFiles();
	}
	protected static Set<Class<? extends TransformationStep>> toSet(Class<? extends TransformationStep>...classes){
		return new HashSet<Class<? extends TransformationStep>>(Arrays.asList(classes));
	}
	protected Set<Class<? extends TransformationStep>> getIntegrationSteps(){
		return toSet();
	}
}
