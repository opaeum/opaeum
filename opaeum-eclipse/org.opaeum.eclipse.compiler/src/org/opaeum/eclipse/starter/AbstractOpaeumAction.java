package org.opaeum.eclipse.starter;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.uml2.uml.Element;

public class AbstractOpaeumAction extends Action{
	protected IStructuredSelection selection;

	public AbstractOpaeumAction(IStructuredSelection selection, String name){
		super(name);
		this.selection = selection;
	}
	protected IFile getCfgFile(){
		return getConfigFile(selection);
	}
	protected IFile getConfigFile(IStructuredSelection selection2){
		IFolder iFolder = (IFolder) selection2.getFirstElement();
		return iFolder.getFile("opaeum.properties");
	}
	protected Object getElementFrom(){
		Object firstElement = selection.getFirstElement();
		if(!(firstElement instanceof Element) && firstElement instanceof IAdaptable){
			return ((IAdaptable) firstElement).getAdapter(EObject.class);
		}
		return firstElement;
	}

	
}
