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
import net.sf.nakeduml.feature.DefaultTransformationLog;
import net.sf.nakeduml.feature.ISourceFolderStrategy;
import net.sf.nakeduml.feature.ITransformationStep;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.SourceFolderDefinition;
import net.sf.nakeduml.feature.TransformationProcess;
import net.sf.nakeduml.filegeneration.TextFileGenerator;
import net.sf.nakeduml.javageneration.JavaSourceFolderIdentifier;
import net.sf.nakeduml.javageneration.TextSourceFolderIdentifier;
import net.sf.nakeduml.javageneration.jbpm5.ProcessStepResolverImplementor;
import net.sf.nakeduml.javageneration.oclexpressions.OclTestGenerator;
import net.sf.nakeduml.pomgeneration.SingleProjectMavenSourceFolderStrategy;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nakeduml.generation.features.BpmUsingJbpm5;
import org.nakeduml.generation.features.ExtendedCompositionSemantics;
import org.nakeduml.generation.features.OclExpressionExecution;
import org.nakeduml.generation.features.PersistenceUsingJpa;

public class BpmLibCodeGenerator implements ISourceFolderStrategy{
	protected TransformationProcess process = new TransformationProcess();
	protected File outputRoot;
	private File modelFile;
	private ResourceSet resourceSet;
	public static void main(String[] args) throws Exception{
		new BpmLibCodeGenerator().generateCodeForSingleModel();
	}
	public BpmLibCodeGenerator(){
		this.resourceSet=EmfWorkspaceLoader.setupStandAloneAppForUML2();

		this.outputRoot = new File(".").getAbsoluteFile().getParentFile().getParentFile();
	}
	protected void generateCodeForSingleModel() throws Exception,IOException,FileNotFoundException{
		modelFile = new File("/home/ampie/Workspaces/workspace_sandbox/nakeduml/nakeduml-core/org.nakeduml.metamodels/models/libraries/OpiumBPM.library.uml");
		NakedUmlConfig cfg = buildConfig();
		EmfWorkspace workspace = EmfWorkspaceLoader.loadSingleModelWorkspace(resourceSet, modelFile, cfg);
		workspace.markLibraries("OpiumSimpleTypes.library.uml");
		process.execute(cfg, workspace, getSteps(),new DefaultTransformationLog());
		workspace.getMappingInfo().store();
	}
	protected NakedUmlConfig buildConfig() throws IOException{
		NakedUmlConfig cfg = new NakedUmlConfig(new File(modelFile.getParentFile(), "bpm-nakeduml.properties"));
		cfg.setGenerateMavenPoms(false);
		cfg.setSourceFolderStrategy(getClass().getName());
		cfg.loadDefaults("nakeduml-runtime");
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
	private void mapDomainProjects(NakedUmlConfig cfg){
		cfg.defineSourceFolder(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC, false, "", "src/main/generated-java");
		cfg.defineSourceFolder(JavaSourceFolderIdentifier.DOMAIN_GEN_TEST_SRC, false, "", "src/test/generated-java");
		SourceFolderDefinition domainSrc = cfg.defineSourceFolder(JavaSourceFolderIdentifier.DOMAIN_SRC, false, "", "src/main/java");
		domainSrc.dontCleanDirectoriesOrOverwriteFiles();
		SourceFolderDefinition domainTestSrc = cfg.defineSourceFolder(JavaSourceFolderIdentifier.DOMAIN_TEST_SRC, false, "", "src/test/java");
		domainTestSrc.dontCleanDirectoriesOrOverwriteFiles();
		cfg.defineSourceFolder(TextSourceFolderIdentifier.DOMAIN_GEN_TEST_RESOURCE, false, "", "src/test/generated-resources");
		cfg.defineSourceFolder(TextSourceFolderIdentifier.DOMAIN_GEN_RESOURCE, false, "", "src/main/generated-resources");
	}
	private void mapAdaptorProjects(NakedUmlConfig cfg){
		cfg.defineSourceFolder(JavaSourceFolderIdentifier.ADAPTOR_GEN_SRC, false, "", "src/main/generated-java");
		cfg.defineSourceFolder(TextSourceFolderIdentifier.ADAPTOR_GEN_RESOURCE, false, "", "src/main/generated-resources");
		cfg.defineSourceFolder(JavaSourceFolderIdentifier.ADAPTOR_GEN_TEST_SRC, false, "", "src/test/generated-java");
		SourceFolderDefinition testSource = cfg.defineSourceFolder(JavaSourceFolderIdentifier.ADAPTOR_TEST_SRC, false, "", "src/test/java");
		testSource.dontCleanDirectories();
		SourceFolderDefinition jbossResources = cfg.defineSourceFolder(TextSourceFolderIdentifier.ADAPTOR_TEST_RESOURCE_JBOSSAS, false, "",
				"src/test/jboss-resources");
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
	public void defineSourceFolders(NakedUmlConfig config){
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
