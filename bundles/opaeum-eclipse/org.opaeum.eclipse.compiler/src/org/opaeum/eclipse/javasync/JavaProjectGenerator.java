package org.opaeum.eclipse.javasync;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.opaeum.eclipse.OpaeumEclipsePlugin;
import org.opaeum.eclipse.starter.Activator;
import org.opaeum.eclipse.starter.EclipseProjectGenerationStep;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.TransformationProcess;
import org.opaeum.filegeneration.TextFileDeleter;
import org.opaeum.filegeneration.TextFileGenerator;
import org.opaeum.java.metamodel.OJWorkspace;
import org.opaeum.pomgeneration.PomGenerationPhase;
import org.opaeum.textmetamodel.SourceFolder;
import org.opaeum.textmetamodel.TextProject;
import org.opaeum.textmetamodel.TextWorkspace;

public final class JavaProjectGenerator{
	private final OpaeumConfig cfg;
	private final IWorkspaceRoot workspace;
	private TransformationProcess process;
	public JavaProjectGenerator(TransformationProcess process,IWorkspaceRoot workspace){
		this.cfg = process.getConfig();
		this.process = process;
		this.workspace = workspace;
	}
	public IStatus run(IProgressMonitor monitor){
		try{
			monitor.beginTask("Writing sources", 80);
			TextWorkspace tws = process.findModel(TextWorkspace.class);
			if(tws != null){
				boolean hasNewJavaSourceFolders = JavaSourceSynchronizer.hasNewJavaSourceFolders(workspace, tws);
				monitor.subTask("Creating Java Projects");
				EclipseProjectGenerationStep eclipseGen = new EclipseProjectGenerationStep();
				eclipseGen.initialize(workspace, cfg);
				List<SourceFolder> sourceFolders = new ArrayList<SourceFolder>();
				List<IProject> eclipseProjects = new ArrayList<IProject>();
				if(hasNewJavaSourceFolders){
					createEclipseProjects(monitor, tws, eclipseGen, sourceFolders, eclipseProjects);
				}
				monitor.subTask("Writing Java sources");
				createEclipseFoldersForSourceFolders(monitor, eclipseGen, sourceFolders);
				if(hasNewJavaSourceFolders){
					if(cfg.generateMavenPoms()){
						prepareParentPomAndRunMavenEclipseEclipse(monitor);
						// }else if(cfg.getAdditionalTransformationSteps().contains(RapProjectBuilder.class)){
						// generatePluginProjectArtifactsAndRefresh(monitor, tws, eclipseGen, eclipseProjects);
					}
				}
				for(IProject iProject:eclipseProjects){
					iProject.refreshLocal(IProject.DEPTH_INFINITE, monitor);
				}
				monitor.worked(20);
			}
		}catch(Exception e){
			final Status error = new Status(Status.WARNING, Activator.PLUGIN_ID, e.getMessage(), e);
			Activator.getDefault().getLog().log(error);
			return error;
		}finally{
			OJWorkspace javaWorkspace = process.findModel(OJWorkspace.class);
			if(javaWorkspace != null){
				javaWorkspace.release();
			}
			process.removeModel(OJWorkspace.class);
			process.removeModel(TextWorkspace.class);
			monitor.done();
		}
		return new Status(IStatus.OK, Activator.PLUGIN_ID, "Java projects generated Successfully");
	}
	// public void generatePluginProjectArtifactsAndRefresh(IProgressMonitor monitor,TextWorkspace tws,EclipseProjectGenerationStep
	// eclipseGen,
	// List<IProject> eclipseProjects) throws JavaModelException,CoreException{
	// addExistingSourceFolders(tws, eclipseProjects);
	// EmfWorkspace mws = process.findModel(EmfWorkspace.class);
	// BootstrapGenerationPhase bgp = process.getPhase(BootstrapGenerationPhase.class);
	// if(bgp != null){
	// RapProjectBuilder rpb = bgp.getStepFor(RapProjectBuilder.class);
	// if(rpb != null){
	// rpb.initialize(cfg, tws, mws, null);
	// rpb.beforeWorkspace(mws);
	// Set<TextOutputNode> textFiles = rpb.getTextFiles();
	// for(TextOutputNode textOutputNode:textFiles){
	// eclipseGen.visitTextFile((TextFile) textOutputNode);
	// }
	// }
	// }
	// for(IProject iProject:eclipseProjects){
	// iProject.refreshLocal(IProject.DEPTH_INFINITE, monitor);
	// }
	// }
	public static void addExistingSourceFolders(TextWorkspace tws) throws CoreException,JavaModelException{
		IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
		for(IProject iProject:projects){
			if(!iProject.isOpen()){
				iProject.open(null);
			}
			if(iProject.hasNature(JavaCore.NATURE_ID)){
				addExistingSourceFoldersToTextWorkspace(tws, JavaCore.create(iProject));
			}
		}
	}
	private static void addExistingSourceFoldersToTextWorkspace(TextWorkspace tws,IJavaProject jp) throws JavaModelException{
		IPackageFragmentRoot[] allPackageFragmentRoots = jp.getPackageFragmentRoots();
		TextProject textProject = tws.createExistingTextProject(jp.getProject().getName());
		for(IPackageFragmentRoot r:allPackageFragmentRoots){
			if(!r.isArchive()){
				IResource correspondingResource = r.getCorrespondingResource();
				if(correspondingResource != null){
					IPath projectRelativePath = correspondingResource.getProjectRelativePath();
					String strings = projectRelativePath.toString();
					textProject.createExistingSourceFolder(strings);
				}
			}
		}
	}
	public void prepareParentPomAndRunMavenEclipseEclipse(IProgressMonitor monitor) throws JavaModelException,IOException,InterruptedException{
		PomGenerationPhase pgp = process.getPhase(PomGenerationPhase.class);
		pgp.getParentPom().getProject().getModules().getModule().clear();
		pgp.getParentPom().getProject().getModules().getModule().addAll(determineMavenModules());
		pgp.outputToFile(pgp.getParentPom());
		runMaven(cfg.getOutputRoot());
		monitor.worked(20);
	}
	public void createEclipseFoldersForSourceFolders(IProgressMonitor monitor,EclipseProjectGenerationStep eclipseGen,List<SourceFolder> sourceFolders)
			throws Exception{
		for(SourceFolder sourceFolder:sourceFolders){
			eclipseGen.visitSourceFolder(sourceFolder);
			monitor.worked(20 / sourceFolders.size());
		}
	}
	public void createEclipseProjects(IProgressMonitor monitor,TextWorkspace tws,EclipseProjectGenerationStep eclipseGen,List<SourceFolder> sourceFolders,
			List<IProject> eclipseProjects) throws Exception{
		// Create Eclipse projects
		for(TextProject tp:tws.getTextProjects()){
			eclipseProjects.add(eclipseGen.visitProject(tp));
			for(SourceFolder sourceFolder:tp.getSourceFolders()){
				sourceFolders.add(sourceFolder);
			}
			monitor.worked(20 / tws.getTextProjects().size());
		}
	}
	public static void runMaven(File outputRoot) throws JavaModelException,IOException,InterruptedException{
		JavaCore.setClasspathVariable("M2_REPO", new Path(System.getProperty("user.home") + "/.m2/repository"), null);
		Process p = Runtime.getRuntime().exec("mvn eclipse:eclipse -o", new String[0], outputRoot);
		p.waitFor();
		BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while((line = r.readLine()) != null){
			sb.append(line);
		}
		OpaeumEclipsePlugin.logInfo(sb.toString());
	}
	private Collection<? extends String> determineMavenModules(){
		Collection<String> result = new ArrayList<String>();
		for(IProject iProject:workspace.getProjects()){
			File dir = iProject.getLocation().toFile();
			try{
				if(this.cfg.getOutputRoot().getCanonicalPath().equals(dir.getParentFile().getCanonicalPath())){
					if(Arrays.asList(dir.list()).contains("pom.xml")){
						result.add(dir.getName());
					}
				}
			}catch(IOException e){
				throw new RuntimeException(e);
			}
		}
		return result;
	}
	// public static Map<String,String> getCurrentSourceFolders(){
	// Map<String,String> result = new HashMap<String,String>();
	// IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
	// for(IProject p:root.getProjects()){
	// try{
	// if(p.hasNature(JavaCore.NATURE_ID)){
	//
	// IPackageFragmentRoot[] allPackageFragmentRoots = jp.getAllPackageFragmentRoots();
	// }
	// }catch(CoreException e){
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// return result;
	// }
	public static void writeTextFilesAndRefresh(final IProgressMonitor monitor,TransformationProcess p,boolean cleanDirectories) throws CoreException{
		try{
			monitor.beginTask("Updating resources", 1000);
			TextWorkspace textWorkspace = p.findModel(TextWorkspace.class);
			if(!monitor.isCanceled()){
				monitor.setTaskName("Writing Text Files");
				if(cleanDirectories){
					TextFileDeleter textFileDeleter = new TextFileDeleter();
					textFileDeleter.initialize(p.getConfig());
					textFileDeleter.startVisiting(textWorkspace);
				}
				TextFileGenerator textFileGenerator = new TextFileGenerator();
				textFileGenerator.initialize(p.getConfig());
				textFileGenerator.startVisiting(textWorkspace);
				monitor.worked(500);
			}
			monitor.setTaskName("Refreshing Projects");
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			// clients expect synchronous execution
			new JavaProjectGenerator(p, root).run(new SubProgressMonitor(monitor, 500));
		}finally{
			monitor.done();
		}
	}
}