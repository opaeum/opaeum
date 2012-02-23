package org.opaeum.jbpm5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.opaeum.emf.extraction.StereotypeApplicationExtractor;
import org.opaeum.emf.load.EmfWorkspaceLoader;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.DefaultTransformationLog;
import org.opaeum.feature.ISourceFolderStrategy;
import org.opaeum.feature.ITransformationStep;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.TransformationProcess;
import org.opaeum.feature.visit.VisitorAdapter;
import org.opaeum.filegeneration.TextFileGenerator;
import org.opaeum.generation.features.BpmUsingJbpm5;
import org.opaeum.generation.features.ExtendedCompositionSemantics;
import org.opaeum.generation.features.OclExpressionExecution;
import org.opaeum.generation.features.PersistenceUsingJpa;
import org.opaeum.javageneration.jbpm5.ProcessStepResolverImplementor;
import org.opaeum.javageneration.oclexpressions.OclTestGenerator;
import org.opaeum.pomgeneration.SingleProjectMavenSourceFolderStrategy;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;
import org.opaeum.textmetamodel.SourceFolderDefinition;
import org.opaeum.textmetamodel.TextSourceFolderIdentifier;

public class BpmLibCodeGenerator implements ISourceFolderStrategy{
	protected TransformationProcess process = new TransformationProcess();
	protected File outputRoot;
	private File modelFile;
	private ResourceSet resourceSet;
	public static void main(String[] args) throws Exception{
		new BpmLibCodeGenerator().generateCodeForSingleModel();
		VisitorAdapter.exec.shutdown();
	}
	public BpmLibCodeGenerator(){
		this.resourceSet = EmfWorkspaceLoader.setupStandAloneAppForUML2();
		this.outputRoot = new File("/home/ampie/Workspaces/workspace_sandbox/nakeduml/opaeum-runtime").getAbsoluteFile();
	}
	protected void generateCodeForSingleModel() throws Exception,IOException,FileNotFoundException{
		modelFile = new File("/home/ampie/Workspaces/workspace_sandbox/nakeduml/opaeum-linkage/org.opaeum.transformation.engine/models/libraries/OpaeumBPM.library.uml");
		OpaeumConfig cfg = buildConfig();
		EmfWorkspace workspace = EmfWorkspaceLoader.loadSingleModelWorkspace(resourceSet, modelFile, cfg);
		workspace.markLibraries("OpaeumSimpleTypes.library.uml");
		workspace.markLibraries("OpaeumSimpleTypes.papyrus.uml");
		workspace.markLibraries("OpaeumBPM.library.uml");
		process.execute(cfg, workspace, getSteps(), new DefaultTransformationLog());
		workspace.getMappingInfo().store();
	}
	protected OpaeumConfig buildConfig() throws IOException{
		OpaeumConfig cfg = new OpaeumConfig(new File(modelFile.getParentFile(), "bpm-opaeum.properties"));
		cfg.setGenerateMavenPoms(false);
		cfg.setSourceFolderStrategy(getClass().getName());
		cfg.loadDefaults("opaeum-runtime");
		cfg.setOutputRoot(outputRoot);
		cfg.getSourceFolderStrategy().defineSourceFolders(cfg);
		return cfg;
	}
	protected Set<Class<? extends ITransformationStep>> getSteps(){
		Set<Class<? extends ITransformationStep>> steps = new HashSet<Class<? extends ITransformationStep>>();
		steps.add(PersistenceUsingJpa.class);
		steps.add(OclExpressionExecution.class);
		steps.add(StereotypeApplicationExtractor.class);
		steps.add(OclTestGenerator.class);
		steps.add(ExtendedCompositionSemantics.class);
		steps.add(BpmUsingJbpm5.class);
		steps.add(ProcessStepResolverImplementor.class);
		steps.add(TextFileGenerator.class);
		return steps;
	}
	private void mapDomainProjects(OpaeumConfig cfg){
		cfg.defineSourceFolder(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC, false, "", "src/main/generated-java");
		cfg.defineSourceFolder(JavaSourceFolderIdentifier.DOMAIN_GEN_TEST_SRC, false, "", "src/test/generated-java");
		SourceFolderDefinition domainSrc = cfg.defineSourceFolder(JavaSourceFolderIdentifier.DOMAIN_SRC, false, "", "src/main/java");
		domainSrc.dontCleanDirectoriesOrOverwriteFiles();
		SourceFolderDefinition domainTestSrc = cfg.defineSourceFolder(JavaSourceFolderIdentifier.DOMAIN_TEST_SRC, false, "", "src/test/java");
		domainTestSrc.dontCleanDirectoriesOrOverwriteFiles();
		cfg.defineSourceFolder(TextSourceFolderIdentifier.DOMAIN_GEN_TEST_RESOURCE, false, "", "src/test/generated-resources");
		cfg.defineSourceFolder(TextSourceFolderIdentifier.DOMAIN_GEN_RESOURCE, false, "", "src/main/generated-resources");
	}
	private void mapAdaptorProjects(OpaeumConfig cfg){
		cfg.defineSourceFolder(JavaSourceFolderIdentifier.ADAPTOR_GEN_SRC, false, "", "src/main/generated-java");
		cfg.defineSourceFolder(TextSourceFolderIdentifier.ADAPTOR_GEN_RESOURCE, false, "", "src/main/generated-resources");
		cfg.defineSourceFolder(JavaSourceFolderIdentifier.ADAPTOR_GEN_TEST_SRC, false, "", "src/test/generated-java");
		SourceFolderDefinition testSource = cfg.defineSourceFolder(JavaSourceFolderIdentifier.ADAPTOR_TEST_SRC, false, "", "src/test/java");
		testSource.dontCleanDirectories();
		SourceFolderDefinition jbossResources = cfg.defineSourceFolder(TextSourceFolderIdentifier.ADAPTOR_TEST_RESOURCE_JBOSSAS, false, "", "src/test/jboss-resources");
		jbossResources.dontCleanDirectoriesOrOverwriteFiles();
		SourceFolderDefinition testResources = cfg.defineSourceFolder(TextSourceFolderIdentifier.ADAPTOR_TEST_RESOURCE, false, "", "src/test/resources");
		testResources.dontCleanDirectoriesOrOverwriteFiles();
		cfg.defineSourceFolder(TextSourceFolderIdentifier.ADAPTOR_GEN_TEST_RESOURCE, false, "", "src/test/generated-resources");
		SourceFolderDefinition mainResources = cfg.defineSourceFolder(TextSourceFolderIdentifier.ADAPTOR_RESOURCE, false, "", "src/main/resources");
		mainResources.dontCleanDirectoriesOrOverwriteFiles();
	}
	protected static Set<Class<? extends ITransformationStep>> toSet(Class<? extends ITransformationStep>...classes){
		return new HashSet<Class<? extends ITransformationStep>>(Arrays.asList(classes));
	}
	@SuppressWarnings("unchecked")
	protected Set<Class<? extends ITransformationStep>> getIntegrationSteps(){
		return toSet();
	}
	@Override
	public void defineSourceFolders(OpaeumConfig config){
		new SingleProjectMavenSourceFolderStrategy().defineSourceFolders(config);
		mapDomainProjects(config);
		mapAdaptorProjects(config);
	}
	@Override
	public boolean isSingleProjectStrategy(){
		return false;
	}
	@Override
	public File calculateOutputRoot(File configFile,File projectRoot,String workspaceIdentifier){
		return outputRoot;
	}
}
