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
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
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

public final class JavaProjectGenerator extends Job{
	private final OpaeumConfig cfg;
	private final IWorkspaceRoot workspace;
	private TransformationProcess process;
	public JavaProjectGenerator(OpaeumConfig cfg,TransformationProcess process,IWorkspaceRoot workspace){
		super("Generating java projects");
		this.cfg = cfg;
		this.process = process;
		this.workspace = workspace;
	}
	@Override
	protected IStatus run(IProgressMonitor monitor){
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
				for(TextProject tp:tws.getTextProjects()){
					eclipseProjects.add(eclipseGen.visitProject(tp));
					for(SourceFolder sourceFolder:tp.getSourceFolders()){
						sourceFolders.add(sourceFolder);
					}
					monitor.worked(20 / tws.getTextProjects().size());
				}
				monitor.subTask("Writing Java sources");
				for(SourceFolder sourceFolder:sourceFolders){
					eclipseGen.visitSourceFolder(sourceFolder);
					monitor.worked(20 / sourceFolders.size());
				}
				if(hasNewJavaSourceFolders && cfg.generateMavenPoms()){
					PomGenerationPhase pgp = process.getPhase(PomGenerationPhase.class);
					pgp.getParentPom().getProject().getModules().getModule().clear();
					pgp.getParentPom().getProject().getModules().getModule().addAll(determineMavenModules());
					pgp.outputToFile(pgp.getParentPom());
					runMaven(cfg.getOutputRoot());
					monitor.worked(20);
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
			process.findModel(OJWorkspace.class).release();
			process.removeModel(OJWorkspace.class);
			process.removeModel(TextWorkspace.class);
			monitor.done();
		}
		return new Status(IStatus.OK, Activator.PLUGIN_ID, "Java projects generated Successfully");
	}
	public static void runMaven(File outputRoot) throws JavaModelException,IOException,InterruptedException{
		JavaCore.setClasspathVariable("M2_REPO", new Path(System.getProperty("user.home") + "/.m2/repository"), null);
		Process p = Runtime.getRuntime().exec("mvn eclipse:eclipse -o", new String[0], outputRoot);
		p.waitFor();
		BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line = null;
		while((line = r.readLine()) != null){
			System.out.println(line);
		}
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
	public static void writeTextFilesAndRefresh(final IProgressMonitor monitor,TransformationProcess p,OpaeumEclipseContext currentContext,boolean cleanDirectories)
			throws CoreException{
		try{
			monitor.beginTask("Updating resources", 1000);
			TextWorkspace textWorkspace = p.findModel(TextWorkspace.class);
			if(!monitor.isCanceled()){
				monitor.setTaskName("Writing Text Files");
				if(cleanDirectories){
					TextFileDeleter textFileDeleter = new TextFileDeleter();
					textFileDeleter.initialize(currentContext.getConfig());
					textFileDeleter.startVisiting(textWorkspace);
				}
				TextFileGenerator textFileGenerator = new TextFileGenerator();
				textFileGenerator.initialize(currentContext.getConfig());
				textFileGenerator.startVisiting(textWorkspace);
				monitor.worked(500);
			}
			monitor.setTaskName("Refreshing Projects");
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			//clients expect synchronous execution
			new JavaProjectGenerator(currentContext.getConfig(), p, root). run(new SubProgressMonitor(monitor, 500));
		}finally{
			monitor.done();
		}
	}
}