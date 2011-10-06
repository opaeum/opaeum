package org.opaeum.eclipse.starter;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.IStructuredSelection;

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

}
