package org.nakeduml.eclipse.starter;

import net.sf.nakeduml.feature.TransformationProcess;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.Model;
import org.nakeduml.eclipse.NakedUmlEclipsePlugin;
import org.nakeduml.eclipse.ProgressMonitorTransformationLog;
import org.nakeduml.java.metamodel.OJPackage;
import org.nakeduml.topcased.uml.editor.NakedUmlEclipseContext;
import org.nakeduml.topcased.uml.editor.NakedUmlEditor;

public class RecompileModelDirectoryAction implements IObjectActionDelegate{
	private IStructuredSelection selection;
	private IWorkbenchPart workbenchPart;
	static Model model;
	@Override
	public void run(IAction action){
		final IContainer folder = (IContainer) selection.getFirstElement();
		final NakedUmlEclipseContext currentContext = NakedUmlEditor.getNakedUmlEclipseContextFor(folder);
		new Job("Recompiling model directory"){
			@Override
			protected IStatus run(final IProgressMonitor monitor){
				try{
					monitor.beginTask("Generating Java Code", 30);
					monitor.subTask("Loading EMF Resources");
					if(currentContext.getEmfWorkspaces().isEmpty()){
						currentContext.loadDirectory(new SubProgressMonitor(monitor, 10));
					}
					currentContext.prepareDirectoryTransformation();
					TransformationProcess p = JavaTransformationProcessManager.getTransformationProcessFor(folder);
					p.removeModel(OJPackage.class);
					p.removeModel(TextWorkspace.class);
					monitor.subTask("Generating Java Code");
					SubProgressMonitor monitor2 = new SubProgressMonitor(monitor, 20);
					monitor2.beginTask("", p.getPhases().size()+4);//TODO get actual number of Integration Phases
					p.setLog(new ProgressMonitorTransformationLog(monitor2));
					//TODO this is for UimSynchronizationPhase which should perhaps now take a NakedModelWorkspace as input
					p.replaceModel(currentContext.getCurrentEmfWorkspace());
					p.execute();
					p.integrate();
					monitor2.done();
					monitor.done();
					new JavaProjectGenerator(currentContext.getUmlElementCache().getConfig(), p, ResourcesPlugin.getWorkspace().getRoot(), true).schedule();
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
	}
	@Override
	public void selectionChanged(IAction action,ISelection selection){
		this.selection = (IStructuredSelection) selection;
		action.setEnabled(EditNakedUmlConfigAction.hasConfigFile((IStructuredSelection) selection));
	}
	@Override
	public void setActivePart(IAction arg0,IWorkbenchPart workbenchPart){
		this.workbenchPart = workbenchPart;
	}
}
