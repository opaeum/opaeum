package org.opaeum.eclipse.javasync;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.opaeum.eclipse.OpaeumEclipsePlugin;
import org.opaeum.eclipse.ProgressMonitorTransformationLog;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.starter.Activator;
import org.opaeum.feature.TransformationProcess;

public class RecompileIntegrationCodeAction extends RecompileModelDirectoryAction{
	public RecompileIntegrationCodeAction(IStructuredSelection selection2){
		super(selection2, "Recompile Integration Code");
		// TODO Auto-generated constructor stub
	}
	@Override
	public void run(){
		final IContainer folder = (IContainer) selection.getFirstElement();
		final OpaeumEclipseContext currentContext = OpaeumEclipseContext.findOrCreateContextFor(folder);
		new Job("Recompiling Integration Code"){
			@Override
			protected IStatus run(final IProgressMonitor monitor){
				try{
					monitor.beginTask("Loading All Models", 400);
					TransformationProcess p = prepareDirectoryForTransformation(folder, monitor);
					monitor.subTask("Generating Java Code");
					p.integrate(new ProgressMonitorTransformationLog(monitor, 100));
					monitor.subTask("Generating text files");
					JavaProjectGenerator.writeTextFilesAndRefresh(new SubProgressMonitor(monitor, 100), p, currentContext,false);
					currentContext.getUmlDirectory().refreshLocal(IProject.DEPTH_INFINITE, null);
				}catch(Exception e){
					e.printStackTrace();
					return new Status(Status.ERROR, OpaeumEclipsePlugin.getPluginId(), Status.ERROR, e.getMessage(), e);
				}finally{
					monitor.done();
				}
				return new Status(IStatus.OK, Activator.PLUGIN_ID, "Model compiled successfully");
			}
		}.schedule();
	}
}
