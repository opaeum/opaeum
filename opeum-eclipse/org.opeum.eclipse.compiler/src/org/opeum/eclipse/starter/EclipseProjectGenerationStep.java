package org.opeum.eclipse.starter;

import java.io.ByteArrayInputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.opeum.feature.ITransformationStep;
import org.opeum.feature.OpeumConfig;
import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitBefore;
import org.opeum.feature.visit.VisitSpec;
import org.opeum.filegeneration.AbstractTextNodeVisitor;
import org.opeum.textmetamodel.SourceFolder;
import org.opeum.textmetamodel.TextDirectory;
import org.opeum.textmetamodel.TextFile;
import org.opeum.textmetamodel.TextOutputNode;
import org.opeum.textmetamodel.TextProject;

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
				// Set<String> natureSet = new HashSet<String>(Arrays.asList(description.getNatureIds()));
				// natureSet.add(JavaCore.NATURE_ID);
				// description.setNatureIds((String[]) natureSet.toArray(new String[natureSet.size()]));
				// project.setDescription(description, null);
				// IJavaProject javaProject = JavaCore.create(project);
			}else{
				project.refreshLocal(IResource.DEPTH_INFINITE, null);
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
	public void initialize(IWorkspaceRoot workspaceRoot,OpeumConfig config){
		this.root = workspaceRoot;
		this.config = config;
	}
	protected void visitParentsRecursively(TextOutputNode node){
		if(node != null){
			visitParentsRecursively(node.getParent());
			for(VisitSpec v:methodInvokers. beforeMethods){
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
