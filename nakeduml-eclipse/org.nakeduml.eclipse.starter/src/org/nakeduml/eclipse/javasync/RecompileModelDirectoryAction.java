package org.nakeduml.eclipse.javasync;

import net.sf.nakeduml.emf.workspace.EmfWorkspace;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.TransformationProcess;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.nakeduml.eclipse.NakedUmlEclipsePlugin;
import org.nakeduml.eclipse.ProgressMonitorTransformationLog;
import org.nakeduml.eclipse.starter.AbstractOpiumAction;
import org.nakeduml.eclipse.starter.Activator;
import org.nakeduml.java.metamodel.OJPackage;
import org.nakeduml.topcased.uml.NakedUmlPlugin;
import org.nakeduml.topcased.uml.editor.NakedUmlEclipseContext;
import org.nakeduml.topcased.uml.editor.NakedUmlEditor;

public class RecompileModelDirectoryAction extends AbstractOpiumAction{
	public RecompileModelDirectoryAction(IStructuredSelection selection2){
		super(selection2, "Recompile Model Directory");
		// TODO Auto-generated constructor stub
	}
	@Override
	public void run(){
		final IContainer folder = (IContainer) selection.getFirstElement();
		final NakedUmlEclipseContext currentContext = NakedUmlEditor.findOrCreateContextFor(folder);
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
					INakedModelWorkspace nakedWorkspace = currentContext.getNakedWorkspace();
					nakedWorkspace.clearGeneratingModelOrProfiles();
					for(INakedRootObject ro:nakedWorkspace.getRootObjects()){
						if(ro instanceof INakedModel && ((INakedModel) ro).isRegeneratingLibrary()){
							// TODO check if code should be regenerated;
							nakedWorkspace.addGeneratingRootObject(ro);
						}else{
							for(IResource r:currentContext.getUmlDirectory().members()){
								if(r.getLocation().lastSegment().equals(ro.getFileName())){
									nakedWorkspace.addGeneratingRootObject(ro);
									break;
								}
							}
						}
					}
					TransformationProcess p = JavaTransformationProcessManager.getTransformationProcessFor(folder);
					p.removeModel(OJPackage.class);
					p.removeModel(TextWorkspace.class);
					monitor.subTask("Generating Java Code");
					NakedUmlConfig config = currentContext.getConfig();
					config.getSourceFolderStrategy().defineSourceFolders(config);
					ProgressMonitorTransformationLog log = new ProgressMonitorTransformationLog(monitor, 2000);
					// TODO this is for UimSynchronizationPhase which should perhaps now take a NakedModelWorkspace as input
					p.executeFrom(JavaTransformationPhase.class, log);
					if(!(monitor.isCanceled() || config.getSourceFolderStrategy().isSingleProjectStrategy())){
						p.integrate(log);
					}
					EmfWorkspace emfWorkspace = p.findModel(EmfWorkspace.class);
					if(emfWorkspace != null){
						emfWorkspace.saveAll();
					}
					monitor.subTask("Generating text files");
					JavaProjectGenerator.writeTextFilesAndRefresh(new SubProgressMonitor(monitor, 3000), p, currentContext);
					currentContext.getUmlDirectory().refreshLocal(IProject.DEPTH_INFINITE, null);
				}catch(Exception e){
					e.printStackTrace();
					return new Status(Status.ERROR, NakedUmlEclipsePlugin.getPluginId(), Status.ERROR, e.getMessage(), e);
				}finally{
					monitor.done();
				}
				return new Status(IStatus.OK, Activator.PLUGIN_ID, "Model compiled successfully");
			}
		}.schedule();
	}
}
