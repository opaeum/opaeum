package org.opaeum.eclipse.simulation;

import java.util.Collection;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.uml2.uml.Model;
import org.opaeum.eclipse.OpaeumEclipsePlugin;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.javasync.JavaProjectGenerator;
import org.opaeum.eclipse.javasync.JavaTransformationProcessManager;
import org.opaeum.eclipse.starter.MemoryUtil;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.ITransformationStep;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.TransformationProcess;
import org.opaeum.java.metamodel.OJWorkspace;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodels.simulation.simulation.SimulationModel;
import org.opaeum.simulation.actions.AbstractSimulationCodeGenerator;
import org.opaeum.simulation.actions.Activator;
import org.opaeum.simulation.actions.SimulationCodeGenerator;
import org.opaeum.simulation.actions.SimulationRunnerGenerator;
import org.opaeum.textmetamodel.SourceFolder;
import org.opaeum.textmetamodel.TextProject;
import org.opaeum.textmetamodel.TextWorkspace;

public final class GenerateSimulationJob extends Job{
	private final SimulationModel model;
	public GenerateSimulationJob(String name,SimulationModel model){
		super(name);
		this.model = model;
	}
	@Override
	protected IStatus run(final IProgressMonitor monitor){
		try{
			IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(model.eResource().getURI().toPlatformString(true)));
			final IContainer contextFolder = file.getParent().getParent();
			URI contextUri = URI.createPlatformResourceURI(contextFolder.getFullPath().toString(), true);
			final OpaeumEclipseContext ctx = OpaeumEclipseContext.findOrCreateContextFor(contextFolder);
			monitor.beginTask("Loading All Models", 1000);
			monitor.subTask("Loading Opaeum Metadata");
			OpaeumConfig cfg = ctx.getConfig();
			EmfWorkspace ws = null;
			for(Resource resource:model.eResource().getResourceSet().getResources()){
				if(resource.getURI().trimSegments(1).equals(contextUri)){
					if(resource.getContents().size() > 0 && resource.getContents().get(0) instanceof Model){
						Model model2 = (Model) resource.getContents().get(0);
						ws = new EmfWorkspace(model2, cfg.getVersion(), cfg.getApplicationIdentifier(), cfg.getMavenGroupId());
						ws.setName(cfg.getApplicationName());
						EcoreUtil.resolveAll(ws.getResourceSet());
						break;
					}
				}
			}
			TransformationProcess p1 = new TransformationProcess();
			Set<Class<? extends ITransformationStep>> steps = JavaTransformationProcessManager.getAllSteps(cfg);
			p1.initialize(cfg, steps);
			p1.replaceModel(ws);
			OJUtil ojUtil = new OJUtil();
			ojUtil.initialise(ws);
			p1.replaceModel(ojUtil);
			TextWorkspace tws1 = new TextWorkspace();
			p1.replaceModel(tws1);
			cfg.getSourceFolderStrategy().defineSourceFolders(cfg);
			JavaProjectGenerator.addExistingSourceFolders(tws1);
			OJWorkspace ojWorkspace = new OJWorkspace();
			p1.replaceModel(ojWorkspace);
			monitor.subTask("Generating Simulation Model");
			AbstractSimulationCodeGenerator gen = new SimulationCodeGenerator();
			gen.initialize(ojWorkspace, cfg, tws1, ws, model, ojUtil);
			gen.startVisiting(ws);
			gen = new SimulationRunnerGenerator();
			gen.initialize(ojWorkspace, cfg, tws1, ws, model, ojUtil);
			gen.startVisiting(ws);
			monitor.subTask("Generating text files");
			JavaProjectGenerator.writeTextFilesAndRefresh(new SubProgressMonitor(monitor, 400), p1, true);
			for(TextProject textProject:tws1.getTextProjects()){
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
}