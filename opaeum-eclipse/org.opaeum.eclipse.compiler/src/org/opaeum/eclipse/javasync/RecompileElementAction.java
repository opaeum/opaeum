package org.opaeum.eclipse.javasync;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.opaeum.eclipse.OpaeumEclipsePlugin;
import org.opaeum.eclipse.ProgressMonitorTransformationLog;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.starter.AbstractOpaeumAction;
import org.opaeum.eclipse.starter.Activator;
import org.opaeum.eclipse.starter.EclipseProjectGenerationStep;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.TransformationProcess;
import org.opaeum.filegeneration.FileGenerationPhase;
import org.opaeum.filegeneration.TextFileDeleter;
import org.opaeum.filegeneration.TextFileGenerator;
import org.opaeum.java.metamodel.OJPackage;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.textmetamodel.TextOutputNode;
import org.opaeum.textmetamodel.TextProject;
import org.opaeum.textmetamodel.TextWorkspace;
import org.opaeum.validation.namegeneration.PersistentNameGenerator;

public class RecompileElementAction extends AbstractOpaeumAction{
	public RecompileElementAction(IStructuredSelection selection){
		super(selection, "Recompile Element");
		this.selection = selection;
	}
	private IStructuredSelection selection;
	@Override
	public void run(){
		for(Iterator<?> it = selection.iterator();it.hasNext();){
			Object selectedElement = it.next();
			if(selectedElement instanceof AbstractGraphicalEditPart){
				AbstractGraphicalEditPart ep = (AbstractGraphicalEditPart) selectedElement;
				if(ep.getModel() instanceof Element){
					selectedElement = ep.getModel();
				}
			}
			if(selectedElement instanceof Element){
				final Element element = (Element) selectedElement;
				new Job("Recompiling elements"){
					@Override
					protected IStatus run(final IProgressMonitor monitor){
						try{
							TransformationProcess p = JavaTransformationProcessManager.getCurrentTransformationProcess();
							if(p == null){
								Display.getDefault().syncExec(new Runnable(){
									public void run(){
										MessageDialog.openError(Display.getCurrent().getActiveShell(), "Opaeum is still initializing",
												"The Opaeum tooling is still initializing. Please try again shortly.");
									}
								});
							}else{
								monitor.beginTask("Generating Java Code", 90);
								OpaeumEclipseContext currentContext = OpaeumEclipseContext.getCurrentContext();
								INakedElement ne = currentContext.getNakedWorkspace().getModelElement(currentContext.getId(element));
								currentContext.getEmfToOpaeumSynchronizer().setCurrentEmfWorkspace(currentContext.getCurrentEmfWorkspace());
								p.replaceModel(new OJPackage());
								p.replaceModel(new TextWorkspace());
								OpaeumConfig cfg = currentContext.getConfig();
								PersistentNameGenerator png = new PersistentNameGenerator();
								png.visitRecursively(currentContext.getNakedWorkspace().getGeneratingModelsOrProfiles().iterator().next());
								Collection<INakedElement> allDescendants = ne.getAllDescendants();
								allDescendants.add(ne);
								Collection<?> processElements = p.processElements(allDescendants, JavaTransformationPhase.class, new ProgressMonitorTransformationLog(
										monitor, 60));
								TextFileGenerator tfg = new TextFileGenerator();
								tfg.initialize(cfg);
								TextFileDeleter tfd = new TextFileDeleter();
								tfg.initialize(cfg);
								for(Object object:processElements){
									if(object instanceof TextOutputNode){
										TextOutputNode txt = (TextOutputNode) object;
										if(txt.shouldDelete()){
											
											monitor.subTask("Emitting " + txt.getName());
											tfd.visitOnly(txt);
										}else {
											tfg.visitUpFirst(txt);
										}
									}
								}
								TextWorkspace txtws = p.findModel(TextWorkspace.class);
								for(TextProject textProject:txtws.getTextProjects()){
									IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(textProject.getName());
									if(project.exists()){
										project.refreshLocal(IResource.DEPTH_INFINITE, null);
									}
								}
								cfg.getSourceFolderStrategy().defineSourceFolders(cfg);
								currentContext.getUmlDirectory().refreshLocal(IProject.DEPTH_INFINITE, null);
								currentContext.getEmfToOpaeumSynchronizer().setCurrentEmfWorkspace(currentContext.getCurrentEmfWorkspace());
							}
						}catch(Exception e){
							OpaeumEclipsePlugin.logError("Recompilation Failed", e);
							// TODO Auto-generated catch block
							e.printStackTrace();
						}finally{
							monitor.done();
						}
						return new Status(IStatus.OK, Activator.PLUGIN_ID, "Elements compiled successfully");
					}
				}.schedule(1);
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
