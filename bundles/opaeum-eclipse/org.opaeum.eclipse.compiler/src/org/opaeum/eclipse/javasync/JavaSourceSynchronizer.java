package org.opaeum.eclipse.javasync;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaModel;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.ui.refactoring.RenameSupport;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.OpaeumEclipsePlugin;
import org.opaeum.eclipse.ProgressMonitorTransformationLog;
import org.opaeum.eclipse.context.OpaeumEclipseContextListener;
import org.opaeum.eclipse.context.OpenUmlFile;
import org.opaeum.eclipse.starter.Activator;
import org.opaeum.eclipse.starter.EclipseProjectGenerationStep;
import org.opaeum.feature.TransformationProcess;
import org.opaeum.java.metamodel.OJWorkspace;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.pomgeneration.PomGenerationPhase;
import org.opaeum.textmetamodel.SourceFolder;
import org.opaeum.textmetamodel.TextOutputNode;
import org.opaeum.textmetamodel.TextProject;
import org.opaeum.textmetamodel.TextWorkspace;

public final class JavaSourceSynchronizer implements OpaeumEclipseContextListener{
	private IWorkspaceRoot workspace;
	private EclipseProjectGenerationStep eclipseGenerator = new EclipseProjectGenerationStep();
	private TransformationProcess process;
	private IJavaModel javaWorkspace;
	private Set<Element> nakedUmlChanges = new HashSet<Element>();
	private NamespaceRenameRequests namespaceRenameRequests = new NamespaceRenameRequests();
	private OpenUmlFile openUmlFile;
	public JavaSourceSynchronizer(OpenUmlFile ne,TransformationProcess process){
		this.openUmlFile = ne;
		this.process = process;
		this.process.replaceModel(ne.getEmfWorkspace());
		this.process.replaceModel(ne.getOJUtil());
		ne.addContextListener(this);
		ne.addContextListener(this.namespaceRenameRequests);
		this.workspace = ResourcesPlugin.getWorkspace().getRoot();
		this.eclipseGenerator.initialize(workspace, ne.getConfig());
		javaWorkspace = JavaCore.create(workspace);
	}
	@Override
	public void onSave(IProgressMonitor monitor,final OpenUmlFile f){
		// Ignore -called from OpenUMlFile - this is linked to the OpaeumBuilder
	}
	public void synchronizeNow(IProgressMonitor monitor){
		try{
			monitor.beginTask("Synchronizing Java sources", 1000);
			if(openUmlFile.getConfig().synchronizeAutomatically()){
				process.replaceModel(openUmlFile.getEmfWorkspace());
				renamePackages(new SubProgressMonitor(monitor, 500));
				synchronizeClasses(new SubProgressMonitor(monitor, 500));
			}
		}catch(Exception e){
			OpaeumEclipsePlugin.logError("Java Sources NOT Synchronized", e);
		}finally{
			monitor.done();
		}
	}
	public static boolean hasNewJavaSourceFolders(IWorkspaceRoot workspace,TextWorkspace tws){
		try{
			boolean result = false;
			for(TextProject textProject:tws.getTextProjects()){
				if(textProject.getName().contains("/") || textProject.getName().trim().isEmpty()){
					// typically used to generate deeper into an existing project, see TestSourceFolderStrategy
					return false;
				}
			}
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
		try{
			Set<NamespaceRenameRequest> renamedNamespaces2 = namespaceRenameRequests.getRenamedNamespaces();
			monitor.beginTask("Renaming Packages", renamedNamespaces2.size());
			for(NamespaceRenameRequest rn:renamedNamespaces2){
				monitor.subTask("Renaming " + rn.getOldName());
				renamePackages(rn);
				monitor.worked(1);
			}
			namespaceRenameRequests.clearRenamedNamespaces();
		}finally{
			monitor.done();
		}
	}
	private void synchronizeClasses(IProgressMonitor monitor) throws Exception{
		try{
			monitor.beginTask("Generating Java Code", 1000);
			Set<Element> clss;
			synchronized(nakedUmlChanges){
				clss = new HashSet<Element>(this.nakedUmlChanges);
				nakedUmlChanges.clear();
			}
			if(clss.size() > 0){
				process.replaceModel(new OJWorkspace());
				process.replaceModel(new TextWorkspace());
				Collection<?> processElements = process.processElements(clss, JavaTransformationPhase.class, new ProgressMonitorTransformationLog(monitor, 400));
				boolean hasNewJavaSourceFolders = hasNewJavaSourceFolders(workspace, process.findModel(TextWorkspace.class));
				if(hasNewJavaSourceFolders && process.getConfig().generateMavenPoms()){
					process.executePhase(PomGenerationPhase.class, false, new ProgressMonitorTransformationLog(monitor, 100));
				}
				int fileCount = deleteOldFilesAndCountNewFiles(monitor, processElements);
				if(fileCount < 10 && hasNewJavaSourceFolders){
					writeFilesIndividually(monitor, processElements);
				}else{
					try{
						JavaProjectGenerator.writeTextFilesAndRefresh(monitor, process, false);
					}catch(CoreException e){
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}finally{
			monitor.done();
		}
	}
	public void writeFilesIndividually(IProgressMonitor monitor,Collection<?> processElements) throws Exception{
		for(Object object:processElements){
			if(object instanceof TextOutputNode){
				TextOutputNode txt = (TextOutputNode) object;
				monitor.subTask("Emitting " + txt.getName());
				eclipseGenerator.visitUpFirst(txt);
			}
			monitor.worked(500 / processElements.size());
		}
	}
	public int deleteOldFilesAndCountNewFiles(IProgressMonitor monitor,Collection<?> processElements){
		int fileCount = 0;
		for(Object object:processElements){
			if(object instanceof TextOutputNode){
				if(((TextOutputNode) object).shouldDelete()){
					eclipseGenerator.visitUpFirst((TextOutputNode) object);
				}else{
					fileCount++;
				}
			}
			monitor.worked(500 / processElements.size());
		}
		return fileCount;
	}
	@Override
	public void synchronizationComplete(OpenUmlFile workspace,Set<Element> affectedElements){
		this.nakedUmlChanges.addAll(affectedElements);
	}
	private void renamePackages(NamespaceRenameRequest rn){
		try{
			for(IJavaProject jp:javaWorkspace.getJavaProjects()){
				for(IPackageFragmentRoot pfr:jp.getPackageFragmentRoots()){
					for(IJavaElement iJavaElement:pfr.getChildren()){
						if(iJavaElement instanceof IPackageFragment){
							IPackageFragment childPf = (IPackageFragment) iJavaElement;
							if(childPf.getElementName().contains(rn.getOldName())){
								String newName = childPf.getElementName().replaceAll(rn.getOldName(), rn.getNewName());
								rename(childPf, newName);
							}
						}
					}
				}
			}
		}catch(JavaModelException e){
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.PLUGIN_ID, e.getMessage(), e));
		}
	}
	private void rename(IPackageFragment childPf,String newName){
		try{
			boolean isValid = Character.isJavaIdentifierStart(newName.charAt(0));
			if(isValid){
				for(int i = 0;i < newName.length();i++){
					if(!Character.isJavaIdentifierPart(newName.charAt(i))){
						isValid = false;
					}
				}
			}
			if(isValid){
				RenameSupport rs = RenameSupport.create(childPf, newName, RenameSupport.UPDATE_REFERENCES);
				Shell shell = Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getShell();
				rs.perform(shell, Activator.getDefault().getWorkbench().getActiveWorkbenchWindow());
			}
			// childPf.rename(newName, true, null);
		}catch(Exception e){
			Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.PLUGIN_ID, e.getMessage(), e));
		}
	}
	@Override
	public void onClose(OpenUmlFile openUmlFile){
		this.process.release();
		this.process = null;
		this.workspace = null;
		this.nakedUmlChanges.clear();
		this.eclipseGenerator.release();
		this.eclipseGenerator = null;
	}
}