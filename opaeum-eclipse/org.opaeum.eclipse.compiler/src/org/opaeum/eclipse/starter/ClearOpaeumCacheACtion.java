package org.opaeum.eclipse.starter;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.opaeum.eclipse.OpaeumEclipsePlugin;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.javasync.JavaTransformationProcessManager;
import org.opaeum.feature.TransformationProcess;
import org.opaeum.javageneration.util.OJUtil;

public class ClearOpaeumCacheACtion extends AbstractOpaeumAction{
	public ClearOpaeumCacheACtion(IStructuredSelection selection){
		super(selection, "Clear Opaeum Cache");
	}
	public void run(){
		// Load classes
		OpaeumEclipsePlugin.getDefault();
		IContainer umlDir = (IContainer) selection.getFirstElement();
		OpaeumEclipseContext ne = OpaeumEclipseContext.findOrCreateContextFor(umlDir);
		ne.reinitialize();
		
		TransformationProcess process = JavaTransformationProcessManager.getTransformationProcessFor(umlDir);
		if(process != null){
			JavaTransformationProcessManager.reinitializeProcess(process,  ne);
		}
		try{
			umlDir.refreshLocal(IResource.DEPTH_INFINITE, null);
		}catch(CoreException e){
			e.printStackTrace();
		}
		MemoryUtil.printMemoryUsage();
	}
}
