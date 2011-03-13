package org.nakeduml.eclipse.starter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationStep;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.filegeneration.AbstractTextNodeVisitor;
import net.sf.nakeduml.textmetamodel.SourceFolder;
import net.sf.nakeduml.textmetamodel.TextProject;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.launching.IVMInstall;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jdt.launching.LibraryLocation;

@StepDependency(phase = EclipseProjectGenerationPhase.class)
public class EclipseProjectGenerationStep extends AbstractTextNodeVisitor implements TransformationStep{
	IWorkspaceRoot root;
	NakedUmlConfig config;
	private boolean isNewProject=false;
	public IWorkspaceRoot getRoot(){
		return root;
	}
	public void setRoot(IWorkspaceRoot root){
		this.root = root;
	}
	@VisitBefore()
	public void visitProject(TextProject tp){
		try{
			IProject project = root.getProject(tp.getName());
			if(!project.exists()){
				IProjectDescription description = root.getWorkspace().newProjectDescription(project.getName());
				description.setLocation(new Path(config.getOutputRoot().getAbsolutePath() + "/" + tp.getName()));
				project.create(description, null);
				project.open(null);
				Set<String> natureSet = new HashSet<String>(Arrays.asList(description.getNatureIds()));
				natureSet.add(JavaCore.NATURE_ID);
				natureSet.add("org.maven.ide.eclipse.maven2Nature");
				description.setNatureIds((String[]) natureSet.toArray(new String[natureSet.size()]));
				project.setDescription(description, null);
				IJavaProject javaProject = JavaCore.create(project);
				IFolder binFolder = createFolder(project, "target", "classes");
				javaProject.setOutputLocation(binFolder.getFullPath(), null);
				List<IClasspathEntry> entries = new ArrayList<IClasspathEntry>();
				IVMInstall vmInstall = JavaRuntime.getDefaultVMInstall();
				LibraryLocation[] locations = JavaRuntime.getLibraryLocations(vmInstall);
				for(LibraryLocation location:locations){
					entries.add(JavaCore.newLibraryEntry(location.getSystemLibraryPath(), null, null));
				}
				// add libs to project class path
				javaProject.setRawClasspath(entries.toArray(new IClasspathEntry[entries.size()]), null);
			}
		}catch(RuntimeException e){
			throw e;
		}catch(CoreException e){
			throw new RuntimeException(e);
		}
	}
	@VisitBefore()
	public void visitSourceFolder(SourceFolder sf){
		if(isNewProject){
			try{
				// TODO this is useless - figure out how to do this with Maven plugin
				IProject project = root.getProject(sf.getParent().getName());
				IJavaProject javaProject = JavaCore.create(project);
				List<IClasspathEntry> asList = Arrays.asList(javaProject.getRawClasspath());
				Map<String,IClasspathEntry> map = new HashMap<String,IClasspathEntry>();
				for(IClasspathEntry e:asList){
					map.put(e.getPath().toString(), e);
				}
				IFolder srcMainJava = createFolder(project, sf.getRelativePath().split("/"));
				IClasspathEntry nred = JavaCore.newSourceEntry(javaProject.getPackageFragmentRoot(srcMainJava).getPath());
				map.put(nred.getPath().toString(), nred);
				javaProject.setRawClasspath((IClasspathEntry[]) map.values().toArray(new IClasspathEntry[map.size()]), null);
			}catch(RuntimeException e){
				throw e;
			}catch(JavaModelException e){
				throw new RuntimeException(e);
			}catch(CoreException e){
				throw new RuntimeException(e);
			}
		}
	}
	private IFolder createFolder(IProject project,String...string) throws CoreException{
		IFolder sourceFolder = project.getFolder(string[0]);
		if(!sourceFolder.exists()){
			sourceFolder.create(false, true, null);
		}
		for(int i = 1;i < string.length;i++){
			sourceFolder = sourceFolder.getFolder(string[i]);
			if(!sourceFolder.exists()){
				sourceFolder.create(false, true, null);
			}
		}
		return sourceFolder;
	}
	public void initialize(IWorkspaceRoot workspaceRoot,NakedUmlConfig config){
		this.root = workspaceRoot;
		this.config = config;
	}
}
