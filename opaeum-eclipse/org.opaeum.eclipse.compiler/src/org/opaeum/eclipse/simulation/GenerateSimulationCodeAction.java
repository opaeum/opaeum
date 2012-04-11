package org.opaeum.eclipse.simulation;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.opaeum.eclipse.OpaeumEclipsePlugin;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.javasync.JavaTransformationProcessManager;
import org.opaeum.eclipse.starter.AbstractOpaeumAction;
import org.opaeum.eclipse.starter.MemoryUtil;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.TransformationProcess;
import org.opaeum.java.metamodel.OJWorkspace;
import org.opaeum.metamodel.workspace.INakedModelWorkspace;
import org.opaeum.metamodels.simulation.simulation.SimulationModel;
import org.opaeum.textmetamodel.TextWorkspace;
import org.opaeum.validation.namegeneration.PersistentNameGenerator;

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
						throw new UnsupportedOperationException();
//						monitor.beginTask("Loading All Models", 1000);
//						TransformationProcess p = prepareDirectoryForTransformation(contextFolder, monitor);
//						monitor.subTask("Generating Simulation Model");
//						AbstractSimulationCodeGenerator gen = new SimulationGenerator();
//						TextWorkspace tws = p.findModel(TextWorkspace.class);
//						gen.initialize(p.findModel(OJWorkspace.class), currentContext.getConfig(), tws, currentContext.getNakedWorkspace(), model);
//						gen.startVisiting(currentContext.getNakedWorkspace());
//						gen = new SimulationRunnerGenerator();
//						gen.initialize(p.findModel(OJWorkspace.class), currentContext.getConfig(), tws, currentContext.getNakedWorkspace(), model);
//						gen.startVisiting(currentContext.getNakedWorkspace());
//						monitor.subTask("Generating text files");
//						JavaProjectGenerator.writeTextFilesAndRefresh(new SubProgressMonitor(monitor, 400), p, currentContext, true);
//						p.findModel(TextWorkspace.class);
//						currentContext.getUmlDirectory().refreshLocal(IProject.DEPTH_INFINITE, null);
//						for(TextProject textProject:tws.getTextProjects()){
//							IProject eclipseProject = ResourcesPlugin.getWorkspace().getRoot().getProject(textProject.getName());
//							IJavaProject javaProject = JavaCore.create(eclipseProject);
//							Collection<SourceFolder> sourceFolders = textProject.getSourceFolders();
//							for(SourceFolder sourceFolder:sourceFolders){
//								IClasspathEntry[] entries = javaProject.getRawClasspath();
//								IClasspathEntry[] newEntries = new IClasspathEntry[entries.length + 1];
//								System.arraycopy(entries, 0, newEntries, 0, entries.length);
//								IPath srcPath = javaProject.getPath().append(sourceFolder.getRelativePath());
//								IClasspathEntry srcEntry = JavaCore.newSourceEntry(srcPath, null);
//								newEntries[entries.length] = JavaCore.newSourceEntry(srcEntry.getPath());
//								javaProject.setRawClasspath(newEntries, null);
//							}
//						}
//						return new Status(IStatus.OK, Activator.PLUGIN_ID, "Model compiled successfully");
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
		ctx.executeAndWait(new AbstractCommand(){
			@Override
			public void execute(){
				ctx.loadDirectory(new SubProgressMonitor(monitor, 200));
			}
			@Override
			public void redo(){
			}
		});
		INakedModelWorkspace nakedWorkspace = ctx.getNakedWorkspace();
		PersistentNameGenerator png = new PersistentNameGenerator();
		png.startVisiting(nakedWorkspace);
		TransformationProcess p = JavaTransformationProcessManager.getTransformationProcessFor(folder);
		p.replaceModel(new OJWorkspace());
		p.replaceModel(new TextWorkspace());
		OpaeumConfig config = ctx.getConfig();
		config.getSourceFolderStrategy().defineSourceFolders(config);
		return p;
	}
}
