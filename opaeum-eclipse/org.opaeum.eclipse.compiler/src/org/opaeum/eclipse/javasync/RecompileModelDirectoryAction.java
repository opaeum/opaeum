package org.opaeum.eclipse.javasync;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.opaeum.eclipse.OpaeumEclipsePlugin;
import org.opaeum.eclipse.ProgressMonitorTransformationLog;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.starter.AbstractOpaeumAction;
import org.opaeum.eclipse.starter.Activator;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.TransformationProcess;
import org.opaeum.java.metamodel.OJPackage;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.metamodel.core.INakedRootObject;
import org.opaeum.metamodel.models.INakedModel;
import org.opaeum.metamodel.workspace.INakedModelWorkspace;
import org.opaeum.textmetamodel.TextWorkspace;
import org.opaeum.validation.namegeneration.PersistentNameGenerator;

public class RecompileModelDirectoryAction extends AbstractOpaeumAction{
	public RecompileModelDirectoryAction(IStructuredSelection selection2){
		super(selection2, "Recompile Model Directory");
		// TODO Auto-generated constructor stub
	}
	protected RecompileModelDirectoryAction(IStructuredSelection selection2,String string){
		super(selection2, string);
	}
	@Override
	public void run(){
		final IContainer folder = (IContainer) selection.getFirstElement();
		final OpaeumEclipseContext currentContext = OpaeumEclipseContext.findOrCreateContextFor(folder);
		new Job("Recompiling model directory"){
			@Override
			protected IStatus run(final IProgressMonitor monitor){
				try{
					monitor.beginTask("Loading All Models", 1000);
					TransformationProcess p = prepareDirectoryForTransformation(folder, monitor);
					// TODO this is for UimSynchronizationPhase which should perhaps now take a NakedModelWorkspace as input
					monitor.subTask("Generating Java Code");
					p.executeFrom(JavaTransformationPhase.class, new ProgressMonitorTransformationLog(monitor, 400));
					if(!(monitor.isCanceled())){
						p.integrate(new ProgressMonitorTransformationLog(monitor, 100));
					}
					EmfWorkspace emfWorkspace = p.findModel(EmfWorkspace.class);
					monitor.subTask("Generating text files");
					JavaProjectGenerator.writeTextFilesAndRefresh(new SubProgressMonitor(monitor, 400), p, currentContext, true);
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
	protected TransformationProcess prepareDirectoryForTransformation(final IContainer folder,final IProgressMonitor monitor) throws CoreException{
		monitor.subTask("Saving Open Models");
		final OpaeumEclipseContext ctx = OpaeumEclipseContext.findOrCreateContextFor(folder);
		monitor.worked(5);
		monitor.subTask("Loading Opaeum Metadata");
		ctx.loadDirectory(new SubProgressMonitor(monitor, 200));
		INakedModelWorkspace nakedWorkspace = ctx.getNakedWorkspace();
		nakedWorkspace.clearGeneratingModelOrProfiles();
		for(INakedRootObject ro:nakedWorkspace.getRootObjects()){
			if(ro instanceof INakedModel && ((INakedModel) ro).isRegeneratingLibrary()){
				// TODO check if code should be regenerated;
				nakedWorkspace.addGeneratingRootObject(ro);
			}else{
				for(IResource r:ctx.getUmlDirectory().members()){
					if(r.getLocation().lastSegment().equals(ro.getFileName())){
						nakedWorkspace.addGeneratingRootObject(ro);
						break;
					}
				}
			}
		}
		PersistentNameGenerator png = new PersistentNameGenerator();
		png.startVisiting(nakedWorkspace);
		TransformationProcess p = JavaTransformationProcessManager.getTransformationProcessFor(folder);
		p.removeModel(OJPackage.class);
		p.removeModel(TextWorkspace.class);
		OpaeumConfig config = ctx.getConfig();
		config.getSourceFolderStrategy().defineSourceFolders(config);
		return p;
	}
}
