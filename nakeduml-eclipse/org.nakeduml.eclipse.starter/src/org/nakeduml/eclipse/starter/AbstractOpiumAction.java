package org.nakeduml.eclipse.starter;

import java.io.File;

import org.eclipse.core.resources.IFolder;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.IStructuredSelection;

public class AbstractOpiumAction extends Action{
	IStructuredSelection selection;

	public AbstractOpiumAction(IStructuredSelection selection, String name){
		super(name);
		this.selection = selection;
	}
	protected File getCfgFile(){
		return getConfigFile(selection);
	}
	protected File getConfigFile(IStructuredSelection selection2){
		return new File(((IFolder) selection2.getFirstElement()).getLocation().toFile(), "nakeduml.properties");
	}

}
