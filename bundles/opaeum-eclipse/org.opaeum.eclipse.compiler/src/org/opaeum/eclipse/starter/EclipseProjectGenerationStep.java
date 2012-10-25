package org.opaeum.eclipse.starter;

import java.io.ByteArrayInputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.opaeum.feature.ITransformationStep;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.feature.visit.VisitSpec;
import org.opaeum.filegeneration.AbstractTextNodeVisitor;
import org.opaeum.textmetamodel.SourceFolder;
import org.opaeum.textmetamodel.TextDirectory;
import org.opaeum.textmetamodel.TextFile;
import org.opaeum.textmetamodel.TextOutputNode;
import org.opaeum.textmetamodel.TextProject;

@StepDependency(phase = EclipseProjectGenerationPhase.class)
public class EclipseProjectGenerationStep extends AbstractTextNodeVisitor implements ITransformationStep{
	IWorkspaceRoot root;
	boolean isTopToBottom = false;
	public IWorkspaceRoot getRoot(){
		return root;
	}
	public void setRoot(IWorkspaceRoot root){
		this.root = root;
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
			}else{
				project.refreshLocal(3, null);
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
		if(!string[0].isEmpty()){
			IFolder sourceFolder = project.getFolder(string[0]);
			if(!sourceFolder.exists()){
				sourceFolder.create(true, true, null);
			}
			for(int i = 1;i < string.length;i++){
				sourceFolder = sourceFolder.getFolder(string[i]);
				if(!sourceFolder.exists()){
					sourceFolder.create(true, true, null);
				}
			}
			deleteUnkownMembers(sf, sourceFolder);
		}
	}
	@VisitBefore()
	public void visitTextFile(TextFile tf) throws CoreException{
		IProject project = root.getProject(tf.getParent().getSourceFolder().getParent().getName());
		IFile file ;
		if(tf.getParent() instanceof SourceFolder && tf.getParent().getName().equals("")){
			file =project.getFile(tf.getName());
		}else{
			IFolder folder = project.getFolder(tf.getParent().getRelativePath());
			if(!folder.exists()){
				folder.create(true,true,null);
			}
			file = folder.getFile(tf.getName());
		}
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
	public void initialize(IWorkspaceRoot workspaceRoot,OpaeumConfig config){
		this.root = workspaceRoot;
		this.config = config;
	}
	protected void visitParentsRecursively(TextOutputNode node){
		if(node != null){
			visitParentsRecursively(node.getParent());
			for(VisitSpec v:methodInvokers.beforeMethods){
				maybeVisit(node, v);
			}
		}
	}
	public void visitUpFirst(TextOutputNode element){
		this.isTopToBottom = false;
		visitParentsRecursively(element.getParent());
		visitOnly(element);
	}
	public void release(){
		this.root = null;
	}
}
