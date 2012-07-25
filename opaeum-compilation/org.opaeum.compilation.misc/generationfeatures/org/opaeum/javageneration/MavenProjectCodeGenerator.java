package org.opaeum.javageneration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.opaeum.emf.load.EmfWorkspaceLoader;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.DefaultTransformationLog;
import org.opaeum.feature.ITransformationStep;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.TransformationProcess;
import org.opaeum.java.metamodel.OJWorkspace;
import org.opaeum.metamodel.workspace.ModelWorkspace;
import org.opaeum.textmetamodel.TextWorkspace;

public abstract class MavenProjectCodeGenerator{
	protected TransformationProcess process = new TransformationProcess();
	protected File outputRoot;
	protected File modelDirectory;
	protected String workspaceIdentifier;
	protected OpaeumConfig cfg;
	private ResourceSet resourceSet;
	// CAlled in standalone build process
	protected MavenProjectCodeGenerator(String outputRoot,String modelDirectory){
		this.outputRoot = new File(outputRoot);
		this.workspaceIdentifier = this.outputRoot.getName();
		this.modelDirectory = new File(modelDirectory);
		this.resourceSet = EmfWorkspaceLoader.setupStandAloneAppForUML2();
	}
	// CAlled from Eclipse
	protected MavenProjectCodeGenerator(ResourceSet rs,OpaeumConfig cfg,File modelDirectory){
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
		process.removeModel(OJWorkspace.class);
		long start = System.currentTimeMillis();
		File modelFile = new File(modelDirectory, modelFileName);
		EmfWorkspace workspace = loadSingleModel(modelFile);
		OpaeumConfig cfg = prepareConfig();
		cfg.store();
		process.execute(cfg, workspace, getSteps(),new DefaultTransformationLog());
		System.out.println("Generating code for model '" + modelFileName + "' took " + (System.currentTimeMillis() - start) + " ms");
	}
	protected EmfWorkspace loadSingleModel(File modelFile) throws Exception{
		EmfWorkspace workspace = EmfWorkspaceLoader.loadSingleModelWorkspace(resourceSet, modelFile, prepareConfig());
		return workspace;
	}
	protected OpaeumConfig prepareConfig() throws IOException{
		if(this.cfg == null){
			OpaeumConfig cfg = new OpaeumConfig(new File(modelDirectory, "opaeum.properties"));
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
		OpaeumConfig cfg = prepareConfig();
		process.execute(cfg, workspace, getSteps(),new DefaultTransformationLog());
		System.out.println("Transforming nakedWorkspace '" + modelDirectory + "' took " + (System.currentTimeMillis() - start) + " ms");
	}
	protected EmfWorkspace loadDirectory() throws IOException{
		EmfWorkspace workspace = EmfWorkspaceLoader.loadDirectory(resourceSet, modelDirectory, prepareConfig());
		return workspace;
	}
	protected abstract Set<Class<? extends ITransformationStep>> getSteps();
	protected static Set<Class<? extends ITransformationStep>> toSet(Class<? extends ITransformationStep>...classes){
		return new HashSet<Class<? extends ITransformationStep>>(Arrays.asList(classes));
	}
	public void generateIntegrationCode() throws Exception{
		EmfWorkspace workspace = loadDirectory();
		process.removeModel(OJWorkspace.class);
		process.removeModel(TextWorkspace.class);
		ModelWorkspace nmw = process.findModel(ModelWorkspace.class);
		workspace.setMappingInfo(nmw.getWorkspaceMappingInfo());
		nmw.clearGeneratingModelOrProfiles();
		process.integrate(new DefaultTransformationLog());
	}
}
