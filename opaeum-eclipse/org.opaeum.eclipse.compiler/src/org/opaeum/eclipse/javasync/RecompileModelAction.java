package org.opaeum.eclipse.javasync;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.Model;
import org.opaeum.eclipse.EmfToOpaeumSynchronizer;
import org.opaeum.eclipse.ProgressMonitorTransformationLog;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.starter.AbstractOpaeumAction;
import org.opaeum.eclipse.starter.Activator;
import org.opaeum.eclipse.starter.MemoryUtil;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.TransformationProcess;
import org.opaeum.java.metamodel.OJWorkspace;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.textmetamodel.SourceFolderDefinition;
import org.opaeum.textmetamodel.TextWorkspace;
import org.opaeum.validation.namegeneration.PersistentNameGenerator;

public class RecompileModelAction extends AbstractOpaeumAction{
	public RecompileModelAction(IStructuredSelection selection){
		super(selection, "Recompile Model");
		this.selection = selection;
	}
	private IStructuredSelection selection;
	static Model model;
	@Override
	public void run(){
		for(Iterator<?> it = selection.iterator();it.hasNext();){
			Object element = it.next();
			if(element instanceof Model){
			}else if(element instanceof IAdaptable){
				element=((IAdaptable) element).getAdapter(EObject.class);
			}
			if(element instanceof Model){
				model = (Model) element;
				new Job("Recompiling model"){
					@Override
					protected IStatus run(final IProgressMonitor monitor){
						try{
							OpaeumEclipseContext.selectContext(model);
							OpaeumEclipseContext currentContext =OpaeumEclipseContext.getCurrentContext();

							TransformationProcess p = JavaTransformationProcessManager.getTransformationProcessFor(currentContext.getUmlDirectory());
							if(p == null||currentContext.isLoading()){
								Display.getDefault().syncExec(new Runnable(){
									public void run(){
										MessageDialog.openError(Display.getCurrent().getActiveShell(), "Opaeum is still initializing",
												"The Opaeum tooling is still initializing. Please try again shortly.");
									}
								});
							}else{
								monitor.beginTask("Generating Java Model", 90);
								currentContext.getEmfToOpaeumSynchronizer().setCurrentEmfWorkspace(currentContext.getCurrentEmfWorkspace());
								EmfToOpaeumSynchronizer eos = currentContext.getEmfToOpaeumSynchronizer();
								p.removeModel(OJWorkspace.class);
								p.removeModel(TextWorkspace.class);
								OpaeumConfig cfg = currentContext.getConfig();
								Collection<SourceFolderDefinition> values = cfg.getSourceFolderDefinitions().values();
								for(SourceFolderDefinition sfd:values){
									if(cfg.getSourceFolderStrategy().isSingleProjectStrategy()){
										if(!sfd.prefixModelIdentifierToSourceFolder()){
											sfd.dontCleanDirectories();
										}
									}
								}
								p.executeFrom(JavaTransformationPhase.class, new ProgressMonitorTransformationLog(monitor, 60),false);
								//TODO add features to SourceFolderStrategy to determine if this should be true, ie shouldCleanDirectoriesWhenGeneratingSingleModel
								JavaProjectGenerator.writeTextFilesAndRefresh(new SubProgressMonitor(monitor, 30), p, currentContext,true);
								cfg.getSourceFolderStrategy().defineSourceFolders(cfg);
								currentContext.getUmlDirectory().refreshLocal(IProject.DEPTH_INFINITE, null);
								eos.setCurrentEmfWorkspace(currentContext.getCurrentEmfWorkspace());
							}
						}catch(Exception e){
							e.printStackTrace();
							return new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Recompilation Failed",e);
						}finally{
							monitor.done();
									MemoryUtil.printMemoryUsage();

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
}
