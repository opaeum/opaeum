package org.nakeduml.eclipse.starter;

import java.io.File;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.nakeduml.eclipse.NakedUmlConfigDialog;

public class EditNakedUmlConfigAction implements IObjectActionDelegate{
	private IStructuredSelection selection;
	private IWorkbenchPart activePart;
	@Override
	public void run(IAction action){
		File file2 = getCfgFile();
		new NakedUmlConfigDialog(activePart.getSite().getShell(), file2).open();
		try{
			((IContainer)selection.getFirstElement()).refreshLocal(IResource.DEPTH_INFINITE, null);
		}catch(CoreException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private File getCfgFile(){
		return getConfigFile(selection);
	}
	private File getConfigFile(IStructuredSelection selection2){
		return new File(((IFolder) selection2.getFirstElement()).getLocation().toFile(), "nakeduml.properties");
	}
	@Override
	public void selectionChanged(IAction action,ISelection selection){
		this.selection = (IStructuredSelection) selection;
		action.setEnabled(hasConfigFile());
	}
	private boolean hasConfigFile(){
		IStructuredSelection selection2 = selection;
		return hasConfigFile(selection2);
	}
	public static boolean hasConfigFile(IStructuredSelection selection2){
		IFolder firstElement = (IFolder) selection2.getFirstElement();
		if(firstElement != null){
			return firstElement.findMember("nakeduml.properties") != null;
		}else{
			return false;
		}
	}
	@Override
	public void setActivePart(IAction action,IWorkbenchPart targetPart){
		this.activePart = targetPart;
	}
}
