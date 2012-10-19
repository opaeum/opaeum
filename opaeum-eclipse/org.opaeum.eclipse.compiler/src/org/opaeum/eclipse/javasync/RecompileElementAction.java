package org.opaeum.eclipse.javasync;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.opaeum.eclipse.OpaeumEclipsePlugin;
import org.opaeum.eclipse.ProgressMonitorTransformationLog;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.context.OpenUmlFile;
import org.opaeum.eclipse.menu.AbstractOpaeumAction;
import org.opaeum.eclipse.starter.Activator;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.TransformationProcess;
import org.opaeum.filegeneration.TextFileDeleter;
import org.opaeum.filegeneration.TextFileGenerator;
import org.opaeum.java.metamodel.OJWorkspace;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.textmetamodel.TextOutputNode;
import org.opaeum.textmetamodel.TextProject;
import org.opaeum.textmetamodel.TextWorkspace;

public class RecompileElementAction extends AbstractOpaeumAction {
	public RecompileElementAction(){
		super(null, "Recompile Element");
	}
	public RecompileElementAction(IStructuredSelection selection){
		super(selection, "Recompile Element");
		this.selection = selection;
	}
	private IStructuredSelection selection;
	@Override
	public void run(){
		for(Iterator<?> it = selection.iterator();it.hasNext();){
			Object selectedElement = it.next();
			if(!(selectedElement instanceof Element) && selectedElement instanceof IAdaptable){
				selectedElement = ((IAdaptable) selectedElement).getAdapter(EObject.class);
			}
			if(selectedElement instanceof AbstractGraphicalEditPart){
				AbstractGraphicalEditPart ep = (AbstractGraphicalEditPart) selectedElement;
				// TODO make pluggable to support Papyrus
				if(ep.getModel().getClass().getName().startsWith("org.topcased.modeler.di.model")){
					Object bridge = IntrospectionUtil.get(IntrospectionUtil.getProperty("semanticModel", ep.getModel().getClass()), ep.getModel());
					selectedElement = IntrospectionUtil.get(IntrospectionUtil.getProperty("element", bridge.getClass()), bridge);
				}
			}
			if(selectedElement instanceof Element){
				final Element element = (Element) selectedElement;
				new Job("Recompiling elements"){
					@Override
					protected IStatus run(final IProgressMonitor monitor){
						try{
							OpenUmlFile ouf = OpaeumEclipseContext.findOpenUmlFileFor(element);
							TransformationProcess p = JavaTransformationProcessManager.getTransformationProcessFor(ouf);
							if(p == null){
								Display.getDefault().syncExec(new Runnable(){
									public void run(){
										MessageDialog.openError(Display.getCurrent().getActiveShell(), "Opaeum is still initializing",
												"The Opaeum tooling is still initializing. Please try again shortly.");
									}
								});
							}else{
								monitor.beginTask("Generating Java Code", 90);
								p.replaceModel(new OJWorkspace());
								p.replaceModel(new TextWorkspace());
								OpaeumConfig cfg = ouf.getConfig();
								TreeIterator<EObject> eAllContents = element.eAllContents();
								Collection<EObject> allDescendants=new HashSet<EObject>();
								while(eAllContents.hasNext()){
									EObject eObject = eAllContents.next();
									allDescendants.add(eObject);
									
								}
								allDescendants.add(element);
								Collection<?> processElements = p.processElements(allDescendants, JavaTransformationPhase.class,
										new ProgressMonitorTransformationLog(monitor, 60));
								TextFileGenerator tfg = new TextFileGenerator();
								tfg.initialize(cfg);
								TextFileDeleter tfd = new TextFileDeleter();
								tfd.initialize(cfg);
								for(Object object:processElements){
									if(object instanceof TextOutputNode){
										TextOutputNode txt = (TextOutputNode) object;
										if(txt.shouldDelete()){
											monitor.subTask("Emitting " + txt.getName());
											tfd.visitOnly(txt);
										}else{
											tfg.visitUpFirst(txt);
										}
									}
								}
								for(TextProject textProject:p.findModel(TextWorkspace.class).getTextProjects()){
									IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(textProject.getName());
									if(project.exists()){
										project.refreshLocal(IResource.DEPTH_INFINITE, null);
									}
								}
								cfg.getSourceFolderStrategy().defineSourceFolders(cfg);
								ouf.getFile().getParent().refreshLocal(IProject.DEPTH_INFINITE, null);
							}
						}catch(Exception e){
							OpaeumEclipsePlugin.logError("Recompilation Failed", e);
							// TODO Auto-generated catch block
							e.printStackTrace();
							return new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Elements not compiled",e);
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
