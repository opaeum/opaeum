package org.nakeduml.eclipse.starter;

import java.util.Collection;
import java.util.HashMap;

import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.TransformationProcess;
import net.sf.nakeduml.filegeneration.FileGenerationPhase;
import net.sf.nakeduml.filegeneration.TextFileGenerator;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.textmetamodel.TextProject;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.Model;
import org.nakeduml.eclipse.NakedUmlEclipsePlugin;
import org.nakeduml.eclipse.ProgressMonitorTransformationLog;
import org.nakeduml.java.metamodel.OJPackage;
import org.nakeduml.topcased.uml.NakedUmlPlugin;
import org.nakeduml.topcased.uml.editor.NakedUmlEclipseContext;
import org.nakeduml.topcased.uml.editor.NakedUmlEditor;
import org.topcased.modeler.utils.ResourceUtils;

public class RecompileModelDirectoryAction extends AbstractOpiumAction{
	public RecompileModelDirectoryAction(IStructuredSelection selection2){
		super(selection2, "Recompile Model Directory");
		// TODO Auto-generated constructor stub
	}
	@Override
	public void run(){
		final IContainer folder = (IContainer) selection.getFirstElement();
		final NakedUmlEclipseContext currentContext = NakedUmlEditor.getNakedUmlEclipseContextFor(folder);
		new Job("Recompiling model directory"){
			@Override
			protected IStatus run(final IProgressMonitor monitor){
				try{
					monitor.beginTask("Loading All Models", 1000);
					monitor.subTask("Saving Open Models");
					NakedUmlPlugin.saveAllOpenFilesIn(currentContext, monitor);
					monitor.worked(5);
					monitor.subTask("Loading Opium Metadata");
					currentContext.loadDirectory(new SubProgressMonitor(monitor, 200));
					TransformationProcess p = JavaTransformationProcessManager.getTransformationProcessFor(folder);
					p.removeModel(OJPackage.class);
					p.removeModel(TextWorkspace.class);
					monitor.subTask("Generating Java Code");
					ProgressMonitorTransformationLog log = new ProgressMonitorTransformationLog(monitor, 2000);
					// TODO this is for UimSynchronizationPhase which should perhaps now take a NakedModelWorkspace as input
					p.replaceModel(currentContext.getCurrentEmfWorkspace());
					p.executeFrom(JavaTransformationPhase.class,log);
					if(!monitor.isCanceled()){
						p.integrate(log);
					}
					p.findModel(EmfWorkspace.class).saveAll();
					monitor.subTask("Generating text files");
					currentContext.getUmlDirectory().refreshLocal(IProject.DEPTH_INFINITE, null);
					JavaProjectGenerator.writeTextFilesAndRefresh(new SubProgressMonitor(monitor, 3000), p, currentContext);
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
}
