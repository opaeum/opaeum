package org.opaeum.eclipse.versioning;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.opaeum.eclipse.OpaeumEclipsePlugin;
import org.opaeum.eclipse.ProgressMonitorTransformationLog;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.javasync.JavaProjectGenerator;
import org.opaeum.eclipse.starter.AbstractOpaeumAction;
import org.opaeum.feature.ITransformationStep;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.TransformationProcess;
import org.opaeum.javageneration.migration.MigrationRunnerGenerator;
import org.opaeum.javageneration.migration.MigratorGenerator;
import org.opaeum.metamodel.workspace.MigrationWorkspace;
import org.opaeum.pomgeneration.MigrationPomStep;
import org.opaeum.runtime.environment.VersionNumber;

public class GenerateMigrationProjectAction extends AbstractOpaeumAction{
	public GenerateMigrationProjectAction(IStructuredSelection selection){
		super(selection, "Generate Migration Project");
	}
	@Override
	public void run(){
		IFolder firstElement = (IFolder) selection.getFirstElement();
		final OpaeumEclipseContext toContext = OpaeumEclipseContext.findOrCreateContextFor(firstElement);
		SortedMap<VersionNumber,IFolder> resources;
		final IContainer parent = OpaeumConfig.isValidVersionNumber(firstElement.getName()) ? firstElement.getParent() : firstElement;
		resources = sortFoldersByVersion(parent);
		SortedMap<VersionNumber,IFolder> headMap = resources.headMap(toContext.getConfig().getVersion());
		if(headMap.isEmpty()){
			MessageDialog.openInformation(Display.getCurrent().getActiveShell(), "Migration", "No previous release found for "
					+ toContext.getConfig().getVersion().toVersionString());
		}else{
			final OpaeumEclipseContext fromContext = OpaeumEclipseContext.findOrCreateContextFor(headMap.get(headMap.lastKey()));
			new Job("Generating Migration Project"){
				@Override
				protected IStatus run(IProgressMonitor monitor){
					File oldOutputRoot = OpaeumEclipseContext.findOrCreateContextFor(parent).getConfig().getOutputRoot();
					OpaeumConfig toConfig = toContext.getConfig();
					try{
						monitor.beginTask("Generating Migration Project", 100);
						toContext.loadDirectory(new SubProgressMonitor(monitor, 33));
						fromContext.loadDirectory(new SubProgressMonitor(monitor, 33));
						TransformationProcess p = new TransformationProcess();
						Set<Class<? extends ITransformationStep>> steps = new HashSet<Class<? extends ITransformationStep>>();
						steps.add(MigrationRunnerGenerator.class);
						steps.add(MigratorGenerator.class);
						steps.add(MigrationPomStep.class);
						toConfig.setOutputRoot(new File(oldOutputRoot, "migration"));
						p.initialize(toConfig, steps);
						p.replaceModel(toContext.getNakedWorkspace());
						p.replaceModel(new MigrationWorkspace(fromContext.getNakedWorkspace(), toContext.getNakedWorkspace()));
						p.execute(new ProgressMonitorTransformationLog(monitor, 33));
						JavaProjectGenerator.writeTextFilesAndRefresh(monitor, p, toContext, true);
						return new Status(IStatus.OK, OpaeumEclipsePlugin.getId(), "Migration Code generated successfully");
					}catch(CoreException e){
						return new Status(IStatus.ERROR, OpaeumEclipsePlugin.getId(), "Migration Code generation failed", e);
					}finally{
						monitor.done();
						toConfig.setOutputRoot(oldOutputRoot);
					}
				}
			}.schedule();
		}
	}
	private SortedMap<VersionNumber,IFolder> sortFoldersByVersion(IContainer parent){
		SortedMap<VersionNumber,IFolder> resources = new TreeMap<VersionNumber,IFolder>();
		try{
			for(IResource r:parent.members()){
				if(OpaeumConfig.isValidVersionNumber(r.getName())){
					VersionNumber previousVersionNumber = new VersionNumber();
					previousVersionNumber.parse(r.getName());
					resources.put(previousVersionNumber, (IFolder) r);
				}
			}
		}catch(CoreException e){
			throw new RuntimeException(e);
		}
		return resources;
	}
}
