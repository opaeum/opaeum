package org.opaeum.eclipse.simulation;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.opaeum.eclipse.OpaeumEclipsePlugin;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.starter.AbstractOpaeumAction;
import org.opaeum.eclipse.starter.MemoryUtil;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.metamodel.workspace.ModelWorkspace;
import org.opaeum.validation.namegeneration.PersistentNameGenerator;

public class GenerateSimulationModelAction extends AbstractOpaeumAction{
	public GenerateSimulationModelAction(IStructuredSelection selection){
		super(selection, "Generate Simulation Model");
	}
	public void run(){
		final IContainer folder = (IContainer) selection.getFirstElement();
		final OpaeumEclipseContext currentContext = OpaeumEclipseContext.findOrCreateContextFor(folder);
		new Job("Generating simulation model"){
			@Override
			protected IStatus run(final IProgressMonitor monitor){
				try{
					monitor.beginTask("Loading All Models", 1000);
					EmfWorkspace ew = prepareDirectoryForTransformation(folder, monitor);
					monitor.subTask("Generating Java Code");
					throw new UnsupportedOperationException();
//					new SimulationModelGenerator(ew, currentContext.getNakedWorkspace()).run();
//					return new Status(IStatus.OK, Activator.PLUGIN_ID, "Model compiled successfully");
				}catch(Exception e){
					e.printStackTrace();
					return new Status(Status.ERROR, OpaeumEclipsePlugin.getPluginId(), Status.ERROR, e.getMessage(), e);
				}finally{
					monitor.done();
					MemoryUtil.printMemoryUsage();
				}
			}
		}.schedule();
	}
	protected EmfWorkspace prepareDirectoryForTransformation(final IContainer folder,final IProgressMonitor monitor)
			throws CoreException{
		monitor.subTask("Saving Open Models");
		final OpaeumEclipseContext ctx = OpaeumEclipseContext.findOrCreateContextFor(folder);
		monitor.worked(5);
		EmfWorkspace r = ctx.loadDirectory(new SubProgressMonitor(monitor, 200));
		ModelWorkspace nakedWorkspace = ctx.getNakedWorkspace();
		PersistentNameGenerator png = new PersistentNameGenerator();
		png.startVisiting(nakedWorkspace);
		return r;
	}
}
