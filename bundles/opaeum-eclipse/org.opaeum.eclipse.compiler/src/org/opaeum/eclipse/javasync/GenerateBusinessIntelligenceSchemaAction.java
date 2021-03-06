package org.opaeum.eclipse.javasync;

import java.io.File;
import java.io.FileWriter;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.opaeum.eclipse.OpaeumEclipsePlugin;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.menu.AbstractOpaeumAction;
import org.opaeum.eclipse.starter.Activator;
import org.opaeum.eclipse.starter.MemoryUtil;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.olap.MondrianCubeGenerator;
import org.opaeum.textmetamodel.TextFile;
import org.opaeum.textmetamodel.TextWorkspace;

public class GenerateBusinessIntelligenceSchemaAction extends AbstractOpaeumAction{
	public GenerateBusinessIntelligenceSchemaAction(IStructuredSelection selection2){
		super(selection2, "Generate Business Intelligence Schema");
	}
	protected GenerateBusinessIntelligenceSchemaAction(IStructuredSelection selection2,String string){
		super(selection2, string);
	}
	@Override
	public void run(){
		DirectoryDialog fd = new DirectoryDialog(Display.getDefault().getActiveShell());
		fd.setText("Specificy directory to export the file to");
		String dir = null;
		if((dir = fd.open()) != null){
			final String finalDir=dir;
			final IContainer folder = (IContainer) selection.getFirstElement();
			final OpaeumEclipseContext ctx = OpaeumEclipseContext.findOrCreateContextFor(folder);
			new Job("Generating Business Intelligence Schema"){
				@Override
				protected IStatus run(final IProgressMonitor monitor){
					try{
						monitor.beginTask("Loading All Models", 1000);
						EmfWorkspace workspace = prepareDirectoryForTransformation(folder, monitor);
						MondrianCubeGenerator mg = new MondrianCubeGenerator();
						monitor.subTask("Generating Schema");
						mg.initialize(null, ctx.getConfig(), new TextWorkspace(), workspace, null);
						mg.startVisiting(workspace);
						monitor.subTask("Generating text files");
						TextFile next = (TextFile) mg.getTextFiles().iterator().next();
						FileWriter fw = new FileWriter(new File(finalDir,workspace.getIdentifier() + ".xml"));
						fw.write(next.getContent());
						fw.close();
						ctx.getUmlDirectory().refreshLocal(IProject.DEPTH_INFINITE, null);
					}catch(Exception e){
						e.printStackTrace();
						return new Status(Status.ERROR, OpaeumEclipsePlugin.getPluginId(), Status.ERROR, e.getMessage(), e);
					}finally{
						monitor.done();
						MemoryUtil.printMemoryUsage();
					}
					return new Status(IStatus.OK, Activator.PLUGIN_ID, "Model compiled successfully");
				}
			}.schedule();
		}
	}
	protected EmfWorkspace prepareDirectoryForTransformation(final IContainer folder,final IProgressMonitor monitor)
			throws CoreException{
		monitor.subTask("Saving Open Models");
		final OpaeumEclipseContext ctx = OpaeumEclipseContext.findOrCreateContextFor(folder);
		monitor.worked(5);
		monitor.subTask("Loading Opaeum Metadata");
		EmfWorkspace result = ctx.loadDirectory(new SubProgressMonitor(monitor, 200));
		ctx.getConfig().getSourceFolderStrategy().defineSourceFolders(ctx.getConfig());
		return result;
	}
}