package org.opaeum.eclipse.javasync;

import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.menu.AbstractOpaeumAction;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.ITransformationStep;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.TransformationProcess;
import org.opaeum.javageneration.util.OJUtil;

public abstract class AbstractDirectoryReadingAction extends AbstractOpaeumAction{
	public AbstractDirectoryReadingAction(IStructuredSelection selection,String name){
		super(selection, name);
	}

	protected TransformationProcess prepareDirectoryForTransformation(final IContainer folder,final IProgressMonitor monitor) throws CoreException{
		monitor.subTask("Saving Open Models");
		final OpaeumEclipseContext ctx = OpaeumEclipseContext.findOrCreateContextFor(folder);
		monitor.worked(5);
		monitor.subTask("Loading Opaeum Metadata");
		final EmfWorkspace ws = ctx.loadDirectory(new SubProgressMonitor(monitor, 200));
		ws.getOpaeumLibrary().reset();
		ws.calculatePrimaryModels();
		TransformationProcess p = new TransformationProcess();
		Set<Class<? extends ITransformationStep>> steps = JavaTransformationProcessManager.getAllSteps(ctx.getConfig());
		p.initialize(ctx.getConfig(), steps);
		p.replaceModel(ws);
		p.replaceModel(new OJUtil());
		OpaeumConfig config = ctx.getConfig();
		config.getSourceFolderStrategy().defineSourceFolders(config);
		return p;
	}
}