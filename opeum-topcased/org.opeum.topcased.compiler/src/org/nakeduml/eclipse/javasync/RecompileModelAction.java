package org.opeum.eclipse.javasync;

import java.io.File;
import java.util.Iterator;

import net.sf.opeum.emf.workspace.EmfWorkspace;
import net.sf.opeum.feature.NakedUmlConfig;
import net.sf.opeum.feature.TransformationProcess;
import net.sf.opeum.javageneration.JavaTransformationPhase;
import net.sf.opeum.textmetamodel.TextWorkspace;
import net.sf.opeum.validation.namegeneration.PersistentNameGenerator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.Model;
import org.opeum.eclipse.NakedUmlEclipsePlugin;
import org.opeum.eclipse.ProgressMonitorTransformationLog;
import org.opeum.eclipse.context.NakedUmlEclipseContext;
import org.opeum.eclipse.starter.AbstractOpiumAction;
import org.opeum.eclipse.starter.Activator;
import org.opeum.java.metamodel.OJPackage;
import org.opeum.topcased.uml.editor.NakedUmlEditor;

public class RecompileModelAction extends AbstractOpiumAction{
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
				model = (Model) element;
				new Job("Recompiling model"){
					@Override
					protected IStatus run(final IProgressMonitor monitor){
						try{
							TransformationProcess p = JavaTransformationProcessManager.getCurrentTransformationProcess();
							if(p == null){
								Display.getDefault().syncExec(new Runnable(){
									public void run(){
										MessageDialog.openError(Display.getCurrent().getActiveShell(), "Opium is still initializing",
												"The Opium tooling is still initializing. Please try again shortly.");
									}
								});
							}else{
								monitor.beginTask("Generating Java Model", 90);
								NakedUmlEclipseContext currentContext = NakedUmlEditor.getCurrentContext();
								currentContext. getEmfToNakedUmlSynchronizer().setCurrentEmfWorkspace(currentContext.getCurrentEmfWorkspace());
								p.replaceModel(new OJPackage());
								p.replaceModel(new TextWorkspace());
								NakedUmlConfig cfg = currentContext.getConfig();
								PersistentNameGenerator png = new PersistentNameGenerator();
								png.visitRecursively(currentContext.getNakedWorkspace().getGeneratingModelsOrProfiles().iterator().next());
								p.executeFrom(JavaTransformationPhase.class, new ProgressMonitorTransformationLog(monitor, 60));
								JavaProjectGenerator.writeTextFilesAndRefresh(new SubProgressMonitor(monitor, 30), p, currentContext,!cfg.getSourceFolderStrategy().isSingleProjectStrategy());
								p.findModel(EmfWorkspace.class).saveAll();
								cfg.getSourceFolderStrategy().defineSourceFolders(cfg);
								currentContext.getUmlDirectory().refreshLocal(IProject.DEPTH_INFINITE, null);
								currentContext. getEmfToNakedUmlSynchronizer().setCurrentEmfWorkspace(currentContext.getCurrentEmfWorkspace());

							}
						}catch(Exception e){
							NakedUmlEclipsePlugin.logError("Recompilation Failed", e);
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
}