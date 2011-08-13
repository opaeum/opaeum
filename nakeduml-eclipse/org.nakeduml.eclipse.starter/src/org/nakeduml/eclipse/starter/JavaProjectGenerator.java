package org.nakeduml.eclipse.starter;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.TransformationProcess;
import net.sf.nakeduml.pomgeneration.PomGenerationPhase;
import net.sf.nakeduml.textmetamodel.SourceFolder;
import net.sf.nakeduml.textmetamodel.TextProject;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jdt.core.JavaCore;
import org.nakeduml.topcased.uml.editor.NakedUmlEditor;

public final class JavaProjectGenerator extends Job{
	private final NakedUmlConfig cfg;
	private final IWorkspaceRoot workspace;
	private boolean recompile;
	private TransformationProcess process;
	public JavaProjectGenerator(NakedUmlConfig cfg,TransformationProcess process,IWorkspaceRoot workspace,boolean recompile){
		super("Generating java projects");
		this.cfg = cfg;
		this.process = process;
		this.workspace = workspace;
		this.recompile = recompile;
	}
	@Override
	protected IStatus run(IProgressMonitor monitor){
		try{
			monitor.beginTask("Writing sources", 100);
			SubProgressMonitor createProjects = new SubProgressMonitor(monitor, 20);
			TextWorkspace tws = process.findModel(TextWorkspace.class);
			if(tws != null){
				createProjects.beginTask("Creating Java Projects", tws.getTextProjects().size());
				monitor.subTask("Creating Java Projects");
				EclipseProjectGenerationStep eclipseGen = new EclipseProjectGenerationStep();
				eclipseGen.initialize(workspace, cfg);
				List<SourceFolder> sourceFolders = new ArrayList<SourceFolder>();
				List<IProject> eclipseProjects = new ArrayList<IProject>();
				for(TextProject tp:tws.getTextProjects()){
					eclipseProjects.add(eclipseGen.visitProjectTopToBottom(tp));
					for(SourceFolder sourceFolder:tp.getSourceFolders()){
						sourceFolders.add(sourceFolder);
					}
					createProjects.worked(1);
				}
				createProjects.done();
				SubProgressMonitor createSourceFolders = new SubProgressMonitor(monitor, 30);
				createSourceFolders.beginTask("", sourceFolders.size());
				monitor.subTask("Writing Java sources");
				for(SourceFolder sourceFolder:sourceFolders){
					if(recompile){
						eclipseGen.visitRecursively(sourceFolder);
					}else{
						eclipseGen.visitSourceFolder(sourceFolder);
					}
					createSourceFolders.worked(1);
				}
				createSourceFolders.done();
				if(JavaSourceSynchronizer.hasNewJavaSourceFolders(workspace, tws)){
					PomGenerationPhase pgp=process.getPhase(PomGenerationPhase.class);
					pgp.getParentPom().getProject().getModules().getModule().clear();
					pgp.getParentPom().getProject().getModules().getModule().addAll(determineMavenModules());
					pgp.outputToFile(pgp.getParentPom());
					SubProgressMonitor mvn = new SubProgressMonitor(monitor, 50);
					mvn.beginTask("", 5);
					monitor.subTask("Setting up Maven Dependencies");
					JavaCore.setClasspathVariable("M2_REPO", new Path(System.getProperty("user.home") + "/.m2/repository"), null);
					mvn.worked(1);
					Process p = Runtime.getRuntime().exec("mvn eclipse:eclipse -o", new String[0], cfg.getOutputRoot());
					p.waitFor();
					mvn.worked(3);
					BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
					String line = null;
					while((line = r.readLine()) != null){
						System.out.println(line);
					}
					for(IProject iProject:eclipseProjects){
						iProject.refreshLocal(IProject.DEPTH_ONE, monitor);
					}
					mvn.worked(1);
					mvn.done();
				}
			}
		}catch(Exception e){
			Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.PLUGIN_ID, e.getMessage(), e));
		}finally{
			monitor.done();
		}
		return new Status(IStatus.OK, Activator.PLUGIN_ID, "Java projects generated Successfully");
	}
	private Collection<? extends String> determineMavenModules(){
		Collection<String> result=new ArrayList<String>();
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
}