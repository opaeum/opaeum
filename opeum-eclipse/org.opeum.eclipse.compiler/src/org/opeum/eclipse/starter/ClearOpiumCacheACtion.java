package org.opeum.eclipse.starter;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.opeum.eclipse.OpeumEclipsePlugin;
import org.opeum.eclipse.context.OpeumEclipseContext;
import org.opeum.eclipse.javasync.JavaTransformationProcessManager;
import org.opeum.feature.TransformationProcess;

public class ClearOpiumCacheACtion extends AbstractOpiumAction{
	public ClearOpiumCacheACtion(IStructuredSelection selection){
		super(selection, "Clear Opium Cache");
	}
	public void run(){
		// Load classes
		OpeumEclipsePlugin.getDefault();
		IContainer umlDir = (IContainer) selection.getFirstElement();
		OpeumEclipseContext ne = OpeumEclipseContext.findOrCreateContextFor(umlDir);
		ne.reinitialize();
		
		TransformationProcess process = JavaTransformationProcessManager.getTransformationProcessFor(umlDir);
		System.gc();
		if(process != null){
			JavaTransformationProcessManager.reinitializeProcess(process,  ne);
		}
		try{
			umlDir.refreshLocal(IResource.DEPTH_INFINITE, null);
		}catch(CoreException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
