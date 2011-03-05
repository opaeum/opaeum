package net.sf.nakeduml.pomgeneration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import net.sf.nakeduml.emf.load.EmfWorkspaceLoader;
import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.OutputRoot;
import net.sf.nakeduml.feature.TransformationProcess;
import net.sf.nakeduml.feature.TransformationStep;
import net.sf.nakeduml.javageneration.CharArrayTextSource;
import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.javametamodel.OJPackage;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedPackage;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

import org.apache.commons.lang.CharRange;
import org.eclipse.emf.common.util.URI;

public abstract class MavenProjectTransformationConfiguration {
	protected TransformationProcess process = new TransformationProcess();
	private File outputRoot;
	private File modelDirectory;

	protected MavenProjectTransformationConfiguration(String outputRoot, String modelDirectory) {
		this.outputRoot = new File(outputRoot);
		this.modelDirectory = new File(modelDirectory);
	}

	/**
	 * May be called multiple times
	 */
	protected void transformSingleModel(String modelFileName) throws Exception, IOException, FileNotFoundException {
		System.out.println("Transforming single model: " + modelFileName);
		process.removeModel(TextWorkspace.class);
		process.removeModel(OJPackage.class);
		long start = System.currentTimeMillis();
		File modelFile = new File(modelDirectory, modelFileName);
		EmfWorkspace workspace = EmfWorkspaceLoader.loadSingleModelWorkspace(URI.createFileURI(modelFile.getAbsolutePath()),
				outputRoot.getName());
		workspace.setDirectoryName(this.outputRoot.getName());
		NakedUmlConfig cfg = buildConfig(workspace);
		cfg.store();
		workspace.getMappingInfo().store();
		process.execute(cfg, workspace, getSteps());
		System.out.println("Transforming model '" + modelFileName + "' took " + (System.currentTimeMillis() - start) + " ms");
	}

	protected NakedUmlConfig buildConfig(EmfWorkspace workspace) throws IOException {
		NakedUmlConfig cfg = new NakedUmlConfig();
		cfg.setOutputRoot(outputRoot);
		cfg.load(new File(modelDirectory, workspace.getDirectoryName() + "-nakeduml.properties"), workspace.getName());
		cfg.store();
		mapOutputRoots(cfg);
		return cfg;
	}

	protected void transformDirectory() throws Exception, IOException, FileNotFoundException {
		System.out.println("Transforming model directory: " + modelDirectory);
		long start = System.currentTimeMillis();
		EmfWorkspace workspace = EmfWorkspaceLoader.loadDirectory(modelDirectory, outputRoot.getName(), "uml");
		workspace.setDirectoryName(this.outputRoot.getName());
		NakedUmlConfig cfg = buildConfig(workspace);
		process.execute(cfg, workspace, getSteps());
		System.out.println("Transforming workspace '" + modelDirectory + "' took " + (System.currentTimeMillis() - start) + " ms");
	}

	protected abstract Set<Class<? extends TransformationStep>> getSteps();

	private void mapOutputRoots(NakedUmlConfig cfg) {
		cfg.mapOutputRoot(JavaTextSource.OutputRootId.DOMAIN_GEN_SRC, false, "-domain", "src/main/generated-java");
		cfg.mapOutputRoot(JavaTextSource.OutputRootId.DOMAIN_GEN_TEST_SRC, false, "-domain", "src/test/generated-java");
		OutputRoot domainSrc = cfg.mapOutputRoot(JavaTextSource.OutputRootId.DOMAIN_SRC, false, "-domain", "src/main/java");
		domainSrc.dontCleanDirectories();
		domainSrc.dontOverwriteFiles();
		OutputRoot domainTestSrc = cfg.mapOutputRoot(JavaTextSource.OutputRootId.DOMAIN_TEST_SRC, false, "-domain", "src/test/java");
		domainTestSrc.dontCleanDirectories();
		domainTestSrc.dontOverwriteFiles();
		cfg.mapOutputRoot(CharArrayTextSource.OutputRootId.DOMAIN_GEN_TEST_RESOURCE, false, "-domain", "src/test/generated-resources");
		cfg.mapOutputRoot(CharArrayTextSource.OutputRootId.DOMAIN_GEN_RESOURCE, false, "-domain", "src/main/generated-resources");
		cfg.mapOutputRoot(JavaTextSource.OutputRootId.ADAPTOR_GEN_SRC, false, "-adaptor", "src/main/generated-java");
		cfg.mapOutputRoot(JavaTextSource.OutputRootId.ADAPTOR_GEN_TEST_SRC, false, "-adaptor", "src/test/generated-java");
		cfg.mapOutputRoot(CharArrayTextSource.OutputRootId.ADAPTOR_TEST_RESOURCE_JBOSSAS, false, "-adaptor", "src/test/jboss-resources");
		cfg.mapOutputRoot(CharArrayTextSource.OutputRootId.ADAPTOR_TEST_RESOURCE, false, "-adaptor", "src/test/resources");
		cfg.mapOutputRoot(CharArrayTextSource.OutputRootId.ADAPTOR_GEN_TEST_RESOURCE, false, "-adaptor", "src/test/generated-resources");
		cfg.mapOutputRoot(CharArrayTextSource.OutputRootId.ADAPTOR_RESOURCE, false, "-adaptor", "src/main/resources");
		cfg.mapOutputRoot(JavaTextSource.OutputRootId.INTEGRATED_ADAPTOR_GEN_SRC, true, "-integrated", "src/main/generated-java");
		cfg.mapOutputRoot(JavaTextSource.OutputRootId.INTEGRATED_ADAPTOR_GEN_TEST_SRC, true, "-integrated", "src/test/generated-java");
		OutputRoot integratedTestResource = cfg.mapOutputRoot(CharArrayTextSource.OutputRootId.INTEGRATED_ADAPTOR_TEST_RESOURCE, false,
				"-integrated", "src/test/resources");
		integratedTestResource.dontCleanDirectories();
		integratedTestResource.dontOverwriteFiles();
		OutputRoot integratedResource = cfg.mapOutputRoot(CharArrayTextSource.OutputRootId.INTEGRATED_ADAPTOR_RESOURCE, false,
				"-integrated", "src/test/resources");
		integratedResource.dontCleanDirectories();
		integratedResource.dontOverwriteFiles();
		OutputRoot integratedJboss = cfg.mapOutputRoot(CharArrayTextSource.OutputRootId.INTEGRATED_ADAPTOR_TEST_RESOURCE_JBOSSAS, false,
				"-integrated", "src/test/jboss-resources");
		integratedJboss.dontCleanDirectories();
		integratedJboss.dontOverwriteFiles();
		cfg.mapOutputRoot(CharArrayTextSource.OutputRootId.INTEGRATED_ADAPTOR_GEN_RESOURCE, true, "-integrated",
				"src/main/generated-resources");
		OutputRoot webTestResources = cfg.mapOutputRoot(CharArrayTextSource.OutputRootId.WEB_TEST_RESOURCE, true, "-web",
				"src/test/resources");
		webTestResources.dontCleanDirectories();
		webTestResources.dontOverwriteFiles();
		OutputRoot jbossResources = cfg.mapOutputRoot(CharArrayTextSource.OutputRootId.WEB_TEST_RESOURCE_JBOSSAS, true, "-web",
				"src/test/jboss-resources");
		jbossResources.dontCleanDirectories();
		jbossResources.dontOverwriteFiles();
		OutputRoot webGenSrc = cfg.mapOutputRoot(JavaTextSource.OutputRootId.WEBAPP_GEN_TEST_SRC, true, "-web", "src/test/generated-java");
		OutputRoot webAppRoot = cfg.mapOutputRoot(CharArrayTextSource.OutputRootId.WEBAPP_RESOURCE, true, "-web", "src/main/webapp");
		webAppRoot.dontCleanDirectories();
		webAppRoot.dontOverwriteFiles();
	}

	protected Set<Class<? extends TransformationStep>> toSet(Class<? extends TransformationStep>... classes) {
		return new HashSet<Class<? extends TransformationStep>>(Arrays.asList(classes));
	}

	public void generateIntegrationCode() throws Exception {
		EmfWorkspace workspace = EmfWorkspaceLoader.loadDirectory(modelDirectory, outputRoot.getName(), "uml");
		workspace.setDirectoryName(this.outputRoot.getName());
		NakedUmlConfig cfg = buildConfig(workspace);
		OutputRoot iags = cfg.getOutputRoot(JavaTextSource.OutputRootId.INTEGRATED_ADAPTOR_GEN_SRC);
		// iags.overwriteFiles();
		// iags.dontCleanDirectories();
		OutputRoot iagr = cfg.getOutputRoot(CharArrayTextSource.OutputRootId.INTEGRATED_ADAPTOR_GEN_RESOURCE);
		// iagr.overwriteFiles();
		// iagr.dontCleanDirectories();
		process.removeModel(OJAnnotatedPackage.class);
		process.removeModel(TextWorkspace.class);
		process.findModel(INakedModelWorkspace.class).clearGeneratingModelOrProfiles();
		process.execute(cfg, workspace, getIntegrationSteps());
		cfg.store();
		workspace.getMappingInfo().store();
	}

	protected Set<Class<? extends TransformationStep>> getIntegrationSteps() {
		return toSet();
	}
}
