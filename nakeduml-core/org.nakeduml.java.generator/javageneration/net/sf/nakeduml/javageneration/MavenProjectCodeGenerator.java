package net.sf.nakeduml.javageneration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import net.sf.nakeduml.emf.load.EmfWorkspaceLoader;
import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.feature.ITransformationStep;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.TransformationProcess;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nakeduml.java.metamodel.OJPackage;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedPackage;

public abstract class MavenProjectCodeGenerator{
	protected TransformationProcess process = new TransformationProcess();
	protected File outputRoot;
	protected File modelDirectory;
	protected String workspaceIdentifier;
	protected NakedUmlConfig cfg;
	private ResourceSet resourceSet;
	// CAlled in standalone build process
	protected MavenProjectCodeGenerator(String outputRoot,String modelDirectory){
		this.outputRoot = new File(outputRoot);
		this.workspaceIdentifier = this.outputRoot.getName();
		this.modelDirectory = new File(modelDirectory);
		this.resourceSet = EmfWorkspaceLoader.setupStandAloneAppForUML2();
	}
	// CAlled from Eclipse
	protected MavenProjectCodeGenerator(ResourceSet rs,NakedUmlConfig cfg,File modelDirectory){
		this.workspaceIdentifier = cfg.getWorkspaceIdentifier();
		this.outputRoot = cfg.getOutputRoot();
		this.modelDirectory = modelDirectory;
		this.cfg = cfg;
		this.resourceSet = rs;
	}
	/**
	 * May be called multiple times
	 */
	protected void generateCodeForSingleModel(String modelFileName) throws Exception,IOException,FileNotFoundException{
		System.out.println("Generating code for single model: " + modelFileName);
		process.removeModel(TextWorkspace.class);
		process.removeModel(OJPackage.class);
		long start = System.currentTimeMillis();
		File modelFile = new File(modelDirectory, modelFileName);
		EmfWorkspace workspace = loadSingleModel(modelFile);
		NakedUmlConfig cfg = prepareConfig();
		cfg.store();
		process.execute(cfg, workspace, getSteps());
		workspace.getMappingInfo().store();
		System.out.println("Generating code for model '" + modelFileName + "' took " + (System.currentTimeMillis() - start) + " ms");
	}
	protected EmfWorkspace loadSingleModel(File modelFile) throws Exception{
		EmfWorkspace workspace = EmfWorkspaceLoader.loadSingleModelWorkspace(resourceSet, modelFile, prepareConfig().getWorkspaceIdentifier());
		return workspace;
	}
	protected NakedUmlConfig prepareConfig() throws IOException{
		if(this.cfg == null){
			NakedUmlConfig cfg = new NakedUmlConfig(new File(modelDirectory, "nakeduml.properties"));
			cfg.loadDefaults(workspaceIdentifier);
			cfg.setOutputRoot(outputRoot);			
		}
		cfg.store();
		return cfg;
	}
	public void transformDirectory() throws Exception,IOException,FileNotFoundException{
		System.out.println("Transforming model directory: " + modelDirectory);
		long start = System.currentTimeMillis();
		EmfWorkspace workspace = loadDirectory();
		NakedUmlConfig cfg = prepareConfig();
		process.execute(cfg, workspace, getSteps());
		System.out.println("Transforming nakedWorkspace '" + modelDirectory + "' took " + (System.currentTimeMillis() - start) + " ms");
	}
	protected EmfWorkspace loadDirectory() throws IOException{
		EmfWorkspace workspace = EmfWorkspaceLoader.loadDirectory(resourceSet, modelDirectory, prepareConfig().getWorkspaceIdentifier());
		return workspace;
	}
	protected abstract Set<Class<? extends ITransformationStep>> getSteps();
	protected static Set<Class<? extends ITransformationStep>> toSet(Class<? extends ITransformationStep>...classes){
		return new HashSet<Class<? extends ITransformationStep>>(Arrays.asList(classes));
	}
	public void generateIntegrationCode() throws Exception{
		EmfWorkspace workspace = loadDirectory();
		process.removeModel(OJAnnotatedPackage.class);
		process.removeModel(TextWorkspace.class);
		INakedModelWorkspace nmw = process.findModel(INakedModelWorkspace.class);
		workspace.setMappingInfo(nmw.getWorkspaceMappingInfo());
		nmw.clearGeneratingModelOrProfiles();
		process.integrate();
		workspace.getMappingInfo().store();
	}
}
