package org.opaeum.eclipse.javasync;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.opaeum.eclipse.EmfPackageUtil;
import org.opaeum.eclipse.OpaeumEclipsePlugin;
import org.opaeum.eclipse.ProgressMonitorTransformationLog;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.starter.Activator;
import org.opaeum.eclipse.starter.MemoryUtil;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.TransformationProcess;
import org.opaeum.javageneration.JavaTransformationPhase;

public class RecompileEverythingAction extends AbstractDirectoryReadingAction{
	public RecompileEverythingAction(IStructuredSelection selection2){
		super(selection2, "Recompile All Models");
	}
	protected RecompileEverythingAction(IStructuredSelection selection2,String string){
		super(selection2, string);
	}
	@Override
	public void run(){
		final IContainer folder = (IContainer) selection.getFirstElement();
		final OpaeumEclipseContext currentContext = OpaeumEclipseContext.findOrCreateContextFor(folder);
		new Job("Recompiling All Models"){
			@Override
			protected IStatus run(final IProgressMonitor monitor){
				TransformationProcess p = null;
				try{
					monitor.beginTask("Loading All Models", 1000);
					p = prepareDirectoryForTransformation(folder, monitor);
					EmfWorkspace mw = p.findModel(EmfWorkspace.class);
					for(Package pkg:mw.getRootObjects()){
						if(pkg instanceof Model && EmfPackageUtil.isRegeneratingLibrary((Model) pkg)){
							mw.addGeneratingModelOrProfile(pkg);
						}
					}
					monitor.subTask("Generating Java Code");
					p.executeFrom(JavaTransformationPhase.class, new ProgressMonitorTransformationLog(monitor, 400), false);
					if(!(monitor.isCanceled())){
						p.integrate(new ProgressMonitorTransformationLog(monitor, 100));
					}
					monitor.subTask("Generating text files");
					JavaProjectGenerator.writeTextFilesAndRefresh(new SubProgressMonitor(monitor, 400), p, true);
					currentContext.getUmlDirectory().refreshLocal(IProject.DEPTH_INFINITE, null);
				}catch(Exception e){
					return OpaeumEclipsePlugin.logError("Models could not be recompiled", e);
				}finally{
					if(p != null){
						p.release();
					}
					monitor.done();
					MemoryUtil.printMemoryUsage();
				}
				return new Status(IStatus.OK, Activator.PLUGIN_ID, "Models compiled successfully");
			}
		}.schedule();
	}
}
