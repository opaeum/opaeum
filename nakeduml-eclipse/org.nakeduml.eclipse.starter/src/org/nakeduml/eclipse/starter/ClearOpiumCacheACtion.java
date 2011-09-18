package org.nakeduml.eclipse.starter;

import net.sf.nakeduml.feature.TransformationProcess;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.nakeduml.eclipse.NakedUmlEclipsePlugin;
import org.nakeduml.eclipse.javasync.JavaTransformationProcessManager;
import org.nakeduml.topcased.uml.editor.NakedUmlEclipseContext;
import org.nakeduml.topcased.uml.editor.NakedUmlEditor;

public class ClearOpiumCacheACtion extends AbstractOpiumAction{
	public ClearOpiumCacheACtion(IStructuredSelection selection){
		super(selection, "Clear Opium Cache");
	}
	public void run(){
		// Load classes
		NakedUmlEclipsePlugin.getDefault();
		IContainer umlDir = (IContainer) selection.getFirstElement();
		NakedUmlEclipseContext ne = NakedUmlEditor.findOrCreateContextFor(umlDir);
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
