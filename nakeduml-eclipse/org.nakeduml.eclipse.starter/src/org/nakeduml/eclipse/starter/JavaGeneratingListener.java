package org.nakeduml.eclipse.starter;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import net.sf.nakeduml.emf.workspace.UmlElementCache.RenamedNamespace;
import net.sf.nakeduml.feature.TransformationProcess;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.pomgeneration.PomGenerationPhase;
import net.sf.nakeduml.textmetamodel.SourceFolder;
import net.sf.nakeduml.textmetamodel.TextOutputNode;
import net.sf.nakeduml.textmetamodel.TextProject;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jdt.core.IJavaModel;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.ui.refactoring.RenameSupport;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.nakeduml.topcased.uml.editor.NakedUmlContextListener;
import org.nakeduml.topcased.uml.editor.NakedUmlEclipseContext;

public final class JavaGeneratingListener implements NakedUmlContextListener{
	private final IWorkspaceRoot workspace;
	NakedUmlEclipseContext context;
	EclipseProjectGenerationStep eclipseGenerator = new EclipseProjectGenerationStep();
	private TransformationProcess process;
	private IJavaModel javaWorkspace;
	public JavaGeneratingListener(NakedUmlEclipseContext ne,IWorkspaceRoot workspace,TransformationProcess process){
		this.process = process;
		this.eclipseGenerator.initialize(workspace, ne.getUmlElementCache().getConfig());
		this.workspace = workspace;
		javaWorkspace = JavaCore.create(workspace);
		this.context = ne;
	}
	@Override
	public void onSave(IProgressMonitor monitor){
		if(context.isOpen()){
			process.replaceModel(context.getCurrentEmfWorkspace());
			renamePackages(monitor);
			synchronizeClasses(monitor);
		}
	}
	public static boolean hasNewJavaSourceFolders(IWorkspaceRoot workspace,TextWorkspace tws){
		try{
			boolean result = false;
			for(TextProject textProject:tws.getTextProjects()){
				IProject project = workspace.getProject(textProject.getName());
				if(!project.exists() || !project.hasNature(JavaCore.NATURE_ID)){
					result = true;
					break;
				}else{
					if(hasNewSourceFolder(workspace, textProject)){
						result = true;
						break;
					}
				}
			}
			return result;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	public static boolean hasNewSourceFolder(IWorkspaceRoot workspace,TextProject textProject) throws JavaModelException{
		boolean result = false;
		IJavaModel javaModel = JavaCore.create(workspace);
		IJavaProject javaProject = javaModel.getJavaProject(textProject.getName());
		for(SourceFolder sourceFolder:textProject.getSourceFolders()){
			if(!sourceFolderExistsIn(javaProject, sourceFolder)){
				result = true;
				break;
			}
		}
		return result;
	}
	private static boolean sourceFolderExistsIn(IJavaProject javaProject,SourceFolder sourceFolder) throws JavaModelException{
		boolean hasSourceFolder = false;
		for(IPackageFragmentRoot pfr:javaProject.getPackageFragmentRoots()){
			IResource resource = pfr.getResource();
			if(resource != null){
				IPath projectRelativePath = resource.getProjectRelativePath();
				String path1 = projectRelativePath.toString();
				String path2 = sourceFolder.getRelativePath();
				if(!pfr.isArchive() && path1.equals(path2)){
					hasSourceFolder = true;
				}
			}
		}
		return hasSourceFolder;
	}
	private void renamePackages(IProgressMonitor monitor){
		monitor = new SubProgressMonitor(monitor, 10);
		Set<RenamedNamespace> renamedNamespaces2 = context.getUmlElementCache().getRenamedNamespaces();
		monitor.beginTask("Renaming Packages", renamedNamespaces2.size());
		for(RenamedNamespace rn:renamedNamespaces2){
			renamePackages(rn, monitor);
			monitor.worked(1);
		}
		context.getUmlElementCache().clearRenamedNamespaces();
		monitor.done();
	}
	private void synchronizeClasses(IProgressMonitor monitor){
		try{
			Set<INakedClassifier> clss = context.getUmlElementCache().getModifiedClasses();
			if(clss.size() > 0){
				SubProgressMonitor childMonitor = new SubProgressMonitor(monitor, 10);
				childMonitor.beginTask("Generating Java", 1);
				Collection<?> processElements = process.processElements(clss, JavaTransformationPhase.class);
				childMonitor.done();
				childMonitor = new SubProgressMonitor(monitor, 10);
				childMonitor.beginTask("Writing files", processElements.size());
				TextWorkspace tws = process.findModel(TextWorkspace.class);
				if(hasNewJavaSourceFolders(workspace, tws)){
					process.executePhase(PomGenerationPhase.class, false);
					new JavaProjectGenerator(context.getUmlElementCache().getConfig(), process, workspace, false).schedule();
				}
				for(Object object:processElements){
					childMonitor.worked(1);
					if(object instanceof TextOutputNode){
						TextOutputNode txt = (TextOutputNode) object;
						eclipseGenerator.visitUpFirst(txt);
					}
				}
				childMonitor.done();
				context.getUmlElementCache().clearModifiedClass();
			}
		}finally{
			monitor.done();
		}
	}
	private void renamePackages(RenamedNamespace rn,IProgressMonitor monitor){
		try{
			for(IJavaProject jp:javaWorkspace.getJavaProjects()){
				for(IPackageFragmentRoot pfr:jp.getPackageFragmentRoots()){
					IPackageFragment pf = pfr.getPackageFragment(rn.getOldName());
					if(pf.exists()){
						try{
							RenameSupport rs = RenameSupport.create(pf, rn.getNewName(), RenameSupport.UPDATE_REFERENCES);
							Shell shell = Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getShell();
							rs.perform(shell, Activator.getDefault().getWorkbench().getActiveWorkbenchWindow());
							pf.rename(rn.getNewName(), true, null);
						}catch(Exception e){
							Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.PLUGIN_ID, e.getMessage(), e));
						}
					}
				}
			}
		}catch(JavaModelException e){
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.PLUGIN_ID, e.getMessage(), e));
		}
	}
	@Override
	public void onClose(boolean save){
	}
}