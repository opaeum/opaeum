package org.opaeum.eclipse.simulation;

import java.util.Collection;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.opaeum.eclipse.OpaeumEclipsePlugin;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.javasync.JavaProjectGenerator;
import org.opaeum.eclipse.javasync.JavaTransformationProcessManager;
import org.opaeum.eclipse.starter.AbstractOpaeumAction;
import org.opaeum.eclipse.starter.Activator;
import org.opaeum.eclipse.starter.MemoryUtil;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.TransformationProcess;
import org.opaeum.java.metamodel.OJWorkspace;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodels.simulation.simulation.SimulationModel;
import org.opaeum.simulation.actions.AbstractSimulationCodeGenerator;
import org.opaeum.simulation.actions.SimulationGenerator;
import org.opaeum.simulation.actions.SimulationRunnerGenerator;
import org.opaeum.textmetamodel.SourceFolder;
import org.opaeum.textmetamodel.TextProject;
import org.opaeum.textmetamodel.TextWorkspace;

public class GenerateSimulationCodeAction extends AbstractOpaeumAction{
	public GenerateSimulationCodeAction(IStructuredSelection selection2){
		super(selection2, "Generate Simulation Code");
	}
	protected GenerateSimulationCodeAction(IStructuredSelection selection2,String string){
		super(selection2, string);
	}
	@Override
	public void run(){
		Object firstElement = selection.getFirstElement();
		final SimulationModel model;
		if(firstElement instanceof SimulationModel){
			model = (SimulationModel) firstElement;
		}else if(firstElement instanceof IAdaptable){
			model = (SimulationModel) ((IAdaptable) firstElement).getAdapter(EObject.class);
		}else{
			model = null;
		}
		if(model != null){
			IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(model.eResource().getURI().toPlatformString(true)));
			final IContainer contextFolder = file.getParent().getParent();
			final OpaeumEclipseContext currentContext = OpaeumEclipseContext.findOrCreateContextFor(contextFolder);
			new Job("Generating Simulation Code"){
				@Override
				protected IStatus run(final IProgressMonitor monitor){
					try{
						monitor.beginTask("Loading All Models", 1000);
						TransformationProcess p = prepareDirectoryForTransformation(contextFolder, monitor);
						monitor.subTask("Generating Simulation Model");
						AbstractSimulationCodeGenerator gen = new SimulationGenerator();
						TextWorkspace tws = p.findModel(TextWorkspace.class);
						EmfWorkspace emfWorkspace=p.findModel(EmfWorkspace.class);
						OJUtil ojUtil=p.findModel(OJUtil.class);
						gen.initialize(p.findModel(OJWorkspace.class), currentContext.getConfig(), tws, emfWorkspace, model,ojUtil);
						gen.startVisiting(emfWorkspace);
						gen = new SimulationRunnerGenerator();
						gen.initialize(p.findModel(OJWorkspace.class), currentContext.getConfig(), tws, emfWorkspace, model,ojUtil);
						gen.startVisiting(emfWorkspace);
						monitor.subTask("Generating text files");
						JavaProjectGenerator.writeTextFilesAndRefresh(new SubProgressMonitor(monitor, 400), p,  true);
						p.findModel(TextWorkspace.class);
						currentContext.getUmlDirectory().refreshLocal(IProject.DEPTH_INFINITE, null);
						for(TextProject textProject:tws.getTextProjects()){
							IProject eclipseProject = ResourcesPlugin.getWorkspace().getRoot().getProject(textProject.getName());
							IJavaProject javaProject = JavaCore.create(eclipseProject);
							Collection<SourceFolder> sourceFolders = textProject.getSourceFolders();
							for(SourceFolder sourceFolder:sourceFolders){
								IClasspathEntry[] entries = javaProject.getRawClasspath();
								IClasspathEntry[] newEntries = new IClasspathEntry[entries.length + 1];
								System.arraycopy(entries, 0, newEntries, 0, entries.length);
								IPath srcPath = javaProject.getPath().append(sourceFolder.getRelativePath());
								IClasspathEntry srcEntry = JavaCore.newSourceEntry(srcPath, null);
								newEntries[entries.length] = JavaCore.newSourceEntry(srcEntry.getPath());
								try{
									javaProject.setRawClasspath(newEntries, null);
								}catch(JavaModelException e){
								}
							}
						}
						return new Status(IStatus.OK, Activator.PLUGIN_ID, "Model compiled successfully");
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
	}
	protected TransformationProcess prepareDirectoryForTransformation(final IContainer folder,final IProgressMonitor monitor)
			throws CoreException{
		monitor.subTask("Saving Open Models");
		final OpaeumEclipseContext ctx = OpaeumEclipseContext.findOrCreateContextFor(folder);
		monitor.worked(5);
		monitor.subTask("Loading Opaeum Metadata");
		TransformationProcess p = JavaTransformationProcessManager.getTransformationProcessFor(folder);
		p.replaceModel(new OJWorkspace());
		p.replaceModel(new TextWorkspace());
		p.replaceModel(ctx.loadDirectory(new SubProgressMonitor(monitor, 200)));
		OpaeumConfig config = ctx.getConfig();
		config.getSourceFolderStrategy().defineSourceFolders(config);
		return p;
	}
}
