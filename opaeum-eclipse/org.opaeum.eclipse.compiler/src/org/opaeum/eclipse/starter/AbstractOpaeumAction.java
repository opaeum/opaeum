package org.opaeum.eclipse.starter;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.context.OpenUmlFile;
import org.opaeum.eclipse.javasync.JavaTransformationProcessManager;
import org.opaeum.feature.TransformationProcess;

public class AbstractOpaeumAction extends Action{
	protected IStructuredSelection selection;
	public AbstractOpaeumAction(IStructuredSelection selection,String name){
		super(name);
		this.selection = selection;
	}
	protected IFile getCfgFile(){
		IFolder iFolder = (IFolder) selection.getFirstElement();
		return iFolder.getFile("opaeum.properties");
	}
	protected Object getElementFrom(){
		Object firstElement = selection.getFirstElement();
		if(!(firstElement instanceof Element) && firstElement instanceof IAdaptable){
			return ((IAdaptable) firstElement).getAdapter(EObject.class);
		}
		return firstElement;
	}
	protected void reinitialiseConfig(OpaeumEclipseContext ne){
		IContainer umlDir = ne.getUmlDirectory();
		ne.reinitialize();
		for(OpenUmlFile ouf:ne.getOpenUmlFiles()){
			TransformationProcess process = JavaTransformationProcessManager.getTransformationProcessFor(ouf);
			if(process != null){
				JavaTransformationProcessManager.reinitializeProcess(process, ne.getConfig(), umlDir);
			}
		}
		try{
			umlDir.refreshLocal(IResource.DEPTH_INFINITE, null);
		}catch(CoreException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
