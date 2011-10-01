package org.opeum.eclipse.starter;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.opeum.eclipse.OpeumEclipsePlugin;
import org.opeum.eclipse.context.OpeumEclipseContext;
import org.opeum.eclipse.javasync.JavaProjectGenerator;

public class UpdateClasspathAction extends AbstractOpiumAction{
	public UpdateClasspathAction(IStructuredSelection selection2){
		super(selection2, "Update Workspace Classpaths");
	}
	@Override
	public void run(){
		final IContainer folder = (IContainer) selection.getFirstElement();
		final OpeumEclipseContext currentContext = OpeumEclipseContext.findOrCreateContextFor(folder);
		new Job("Updating Workspace Classpaths"){
			@Override
			protected IStatus run(final IProgressMonitor monitor){
				try{
					monitor.beginTask("Updating Workspace Classpaths", 1);
					JavaProjectGenerator.runMaven(currentContext.getConfig());
					ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, monitor);
				}catch(Exception e){
					OpeumEclipsePlugin.getDefault().getLog().log(new Status(Status.INFO, OpeumEclipsePlugin.getPluginId(), Status.OK, e.getMessage(), e));
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
