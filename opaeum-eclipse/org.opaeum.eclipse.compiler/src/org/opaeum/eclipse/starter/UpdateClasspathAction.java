package org.opaeum.eclipse.starter;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.opaeum.eclipse.OpaeumEclipsePlugin;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.javasync.JavaProjectGenerator;

public class UpdateClasspathAction extends AbstractOpaeumAction{
	public UpdateClasspathAction(IStructuredSelection selection2){
		super(selection2, "Update Workspace Classpaths");
	}
	@Override
	public void run(){
		final IContainer folder = (IContainer) selection.getFirstElement();
		final OpaeumEclipseContext currentContext = OpaeumEclipseContext.findOrCreateContextFor(folder);
		new Job("Updating Workspace Classpaths"){
			@Override
			protected IStatus run(final IProgressMonitor monitor){
				try{
					monitor.beginTask("Updating Workspace Classpaths", 1);
					JavaProjectGenerator.runMaven(currentContext.getConfig().getOutputRoot());
					ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, monitor);
				}catch(Exception e){
					OpaeumEclipsePlugin.getDefault().getLog().log(new Status(Status.INFO, OpaeumEclipsePlugin.getPluginId(), Status.OK, e.getMessage(), e));
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					monitor.done();
				}
				return new Status(IStatus.OK, Activator.PLUGIN_ID, "Model compiled successfully");
			}
		}.schedule();
	}
}
