package org.nakeduml.eclipse.starter;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.ITransformationStep;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.feature.visit.VisitSpec;
import net.sf.nakeduml.filegeneration.AbstractTextNodeVisitor;
import net.sf.nakeduml.textmetamodel.SourceFolder;
import net.sf.nakeduml.textmetamodel.TextDirectory;
import net.sf.nakeduml.textmetamodel.TextFile;
import net.sf.nakeduml.textmetamodel.TextOutputNode;
import net.sf.nakeduml.textmetamodel.TextProject;

import org.eclipse.core.internal.resources.Folder;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
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
import org.eclipse.uml2.uml.Element;

@StepDependency(phase = EclipseProjectGenerationPhase.class)
public class EclipseProjectGenerationStep extends AbstractTextNodeVisitor implements ITransformationStep{
	IWorkspaceRoot root;
	NakedUmlConfig config;
	boolean isTopToBottom = false;
	public IWorkspaceRoot getRoot(){
		return root;
	}
	public void setRoot(IWorkspaceRoot root){
		this.root = root;
	}
	public IProject visitProjectTopToBottom(TextProject tp){
		this.isTopToBottom = true;
		return visitProject(tp);
	}
	@VisitBefore()
	public IProject visitProject(TextProject tp){
		try{
			IProject project = root.getProject(tp.getName());
			if(!project.exists()){
				IProjectDescription description = root.getWorkspace().newProjectDescription(project.getName());
				Path path = new Path(config.getOutputRoot().getAbsolutePath() + "/" + tp.getName());
				description.setLocation(path);
				project.create(description, null);
				project.open(null);
				// Set<String> natureSet = new HashSet<String>(Arrays.asList(description.getNatureIds()));
				// natureSet.add(JavaCore.NATURE_ID);
				// description.setNatureIds((String[]) natureSet.toArray(new String[natureSet.size()]));
				// project.setDescription(description, null);
				// IJavaProject javaProject = JavaCore.create(project);
			}
			return project;
		}catch(RuntimeException e){
			throw e;
		}catch(CoreException e){
			throw new RuntimeException(e);
		}
	}
	@VisitBefore()
	public void visitTextDir(TextDirectory td) throws CoreException{
		IProject project = root.getProject(td.getSourceFolder().getParent().getName());
		IFolder folder = project.getFolder(td.getRelativePath());
		if(td.shouldDelete() && folder.exists()){
			folder.delete(true, null);
		}else if(!folder.exists() && td.hasContent()){
			folder.create(true, true, null);
		}else if(folder.exists()){
			deleteUnkownMembers(td, folder);
		}
	}
	private void deleteUnkownMembers(TextDirectory td,IFolder folder) throws CoreException{
		if(td.getSourceFolder().shouldClean() && isTopToBottom){
			for(IResource child:folder.members()){
				if(td.getSourceFolder().shouldClean() && !td.hasChild(child.getName())){
					child.delete(true, null);
				}
			}
		}
	}
	@VisitBefore()
	public void visitSourceFolder(SourceFolder sf) throws CoreException{
		IProject project = root.getProject(sf.getSourceFolder().getParent().getName());
		String[] string = sf.getRelativePath().replace('/', '~').split("~");
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
		deleteUnkownMembers(sf, sourceFolder);
	}
	@VisitBefore()
	public void visitTextFile(TextFile tf) throws CoreException{
		IProject project = root.getProject(tf.getParent().getSourceFolder().getParent().getName());
		IFolder folder = project.getFolder(tf.getParent().getRelativePath());
		IFile file = folder.getFile(tf.getName());
		if(file.exists()){
			if(tf.hasContent()){
				file.setContents(getContents(tf), true, false, null);
			}else{
				file.delete(true, false, null);
			}
		}else if(tf.hasContent()){
			file.create(getContents(tf), true, null);
		}
	}
	public ByteArrayInputStream getContents(TextFile tf){
		return new ByteArrayInputStream(new String(tf.getTextSource().toCharArray()).getBytes());
	}
	public void initialize(IWorkspaceRoot workspaceRoot,NakedUmlConfig config){
		this.root = workspaceRoot;
		this.config = config;
	}
	protected void visitParentsRecursively(TextOutputNode node){
		if(node != null){
			visitParentsRecursively(node.getParent());
			for(VisitSpec v:beforeMethods){
				maybeVisit(node, v);
			}
		}
	}
	public void visitUpFirst(TextOutputNode element){
		this.isTopToBottom = false;
		visitParentsRecursively(element.getParent());
		visitOnly(element);
	}
}
