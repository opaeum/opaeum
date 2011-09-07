package org.nakeduml.eclipse.starter;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;

import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.SourceFolderDefinition;
import net.sf.nakeduml.feature.TransformationProcess;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.Model;
import org.nakeduml.eclipse.NakedUmlEclipsePlugin;
import org.nakeduml.eclipse.ProgressMonitorTransformationLog;
import org.nakeduml.java.metamodel.OJPackage;
import org.nakeduml.topcased.uml.editor.NakedUmlEclipseContext;
import org.nakeduml.topcased.uml.editor.NakedUmlEditor;

public class RecompileModelAction implements IObjectActionDelegate{
	private IStructuredSelection selection;
	private IWorkbenchPart workbenchPart;
	static Model model;
	@Override
	public void run(IAction action){
		for(Iterator<?> it = selection.iterator();it.hasNext();){
			Object element = it.next();
			if(element instanceof Model){
				model = (Model) element;
				new Job("Recompiling model"){
					@Override
					protected IStatus run(final IProgressMonitor monitor){
						try{
							TransformationProcess p = JavaTransformationProcessManager.getCurrentTransformationProcess();
							if(p == null){
								final Shell shell = workbenchPart.getSite().getShell();
								Display.getDefault().syncExec(new Runnable(){
									public void run(){
										MessageDialog.openError(shell, "NakedUML still initializing",
												"The NakedUML tooling is still initializing. Please try again shortly.");
									}
								});
							}else{
								monitor.beginTask("Generating Java Model", 90);
								NakedUmlEclipseContext currentContext = NakedUmlEditor.getCurrentContext();
								p.replaceModel(new OJPackage());
								p.replaceModel(new TextWorkspace());
								NakedUmlConfig cfg = currentContext.getUmlElementCache().getConfig();
								
								if(cfg.getSourceFolderStrategy().isSingleProjectStrategy()){
									//Temporarily suppress directoryCleaning
									for(SourceFolderDefinition sfd:cfg.getSourceFolderDefinitions().values()){
										sfd.dontCleanDirectories();
									}
								}
								p.executeFrom(JavaTransformationPhase.class,new ProgressMonitorTransformationLog(monitor,30));
								JavaProjectGenerator.writeTextFilesAndRefresh(new SubProgressMonitor(monitor, 60), p, currentContext);
								p.findModel(EmfWorkspace.class).saveAll();
								cfg.getSourceFolderStrategy().defineSourceFolders(cfg);
								currentContext.getUmlDirectory().refreshLocal(IProject.DEPTH_INFINITE, null);
							}
						}catch(Exception e){
							NakedUmlEclipsePlugin.getDefault().getLog().log(new Status(Status.INFO, NakedUmlEclipsePlugin.getPluginId(), Status.OK, e.getMessage(), e));
							// TODO Auto-generated catch block
							e.printStackTrace();
						}finally{
							monitor.done();
						}
						return new Status(IStatus.OK, Activator.PLUGIN_ID, "Model compiled successfully");
					}

				}.schedule();
				break;
			}
		}
	}
	public File getModeFile(Model model){
		String uriPAth = model.eResource().getURI().toPlatformString(true);
		IFile modelIFile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(uriPAth));
		File modelFile = modelIFile.getLocation().toFile();
		return modelFile;
	}
	@Override
	public void selectionChanged(IAction arg0,ISelection selection){
		this.selection = (IStructuredSelection) selection;
	}
	@Override
	public void setActivePart(IAction arg0,IWorkbenchPart workbenchPart){
		this.workbenchPart = workbenchPart;
	}
}
