package org.opeum.eclipse.javasync;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.opeum.eclipse.OpeumEclipsePlugin;
import org.opeum.eclipse.ProgressMonitorTransformationLog;
import org.opeum.eclipse.context.OpeumEclipseContext;
import org.opeum.eclipse.starter.Activator;
import org.opeum.emf.workspace.EmfWorkspace;
import org.opeum.feature.TransformationProcess;

public class RecompileIntegrationCodeAction extends RecompileModelDirectoryAction{
	public RecompileIntegrationCodeAction(IStructuredSelection selection2){
		super(selection2, "Recompile Integration Code");
		// TODO Auto-generated constructor stub
	}
	@Override
	public void run(){
		final IContainer folder = (IContainer) selection.getFirstElement();
		final OpeumEclipseContext currentContext = OpeumEclipseContext.findOrCreateContextFor(folder);
		new Job("Recompiling Integration Code"){
			@Override
			protected IStatus run(final IProgressMonitor monitor){
				try{
					monitor.beginTask("Loading All Models", 400);
					TransformationProcess p = prepareDirectoryForTransformation(folder, monitor);
					monitor.subTask("Generating Java Code");
					p.integrate(new ProgressMonitorTransformationLog(monitor, 100));
					EmfWorkspace emfWorkspace = p.findModel(EmfWorkspace.class);
					if(emfWorkspace != null){
						emfWorkspace.saveAll();
					}
					monitor.subTask("Generating text files");
					JavaProjectGenerator.writeTextFilesAndRefresh(new SubProgressMonitor(monitor, 100), p, currentContext,false);
					currentContext.getUmlDirectory().refreshLocal(IProject.DEPTH_INFINITE, null);
				}catch(Exception e){
					e.printStackTrace();
					return new Status(Status.ERROR, OpeumEclipsePlugin.getPluginId(), Status.ERROR, e.getMessage(), e);
				}finally{
					monitor.done();
				}
				return new Status(IStatus.OK, Activator.PLUGIN_ID, "Model compiled successfully");
			}
		}.schedule();
	}
}
