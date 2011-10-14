package org.opaeum.eclipse.versioning;

import org.eclipse.core.resources.IFolder;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.opaeum.eclipse.starter.AbstractOpaeumAction;

public class VersionAction extends AbstractOpaeumAction{

	public VersionAction(IStructuredSelection selection){
		super(selection, "Version Models");
	}
	@Override
	public void run(){
		IFolder f=(IFolder) selection.getFirstElement();
		VersionDialog dlg = new VersionDialog(Display.getDefault().getActiveShell(),f);
		if(dlg.open()==Window.OK){
			
		}
	}
}
