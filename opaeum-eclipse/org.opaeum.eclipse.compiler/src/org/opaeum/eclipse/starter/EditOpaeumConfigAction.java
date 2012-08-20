package org.opaeum.eclipse.starter;

import org.eclipse.core.resources.IContainer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.opaeum.eclipse.OpaeumConfigDialog;
import org.opaeum.eclipse.OpaeumEclipsePlugin;
import org.opaeum.eclipse.context.OpaeumEclipseContext;

public class EditOpaeumConfigAction extends AbstractOpaeumAction{
	public EditOpaeumConfigAction(IStructuredSelection selection2){
		super(selection2, "Edit Opaeum Settings");
	}
	@Override
	// public void run(IAction action){
	public void run(){
		// Load classes
		OpaeumEclipsePlugin.getDefault();
		IContainer umlDir = (IContainer) selection.getFirstElement();
		OpaeumEclipseContext ne = OpaeumEclipseContext.findOrCreateContextFor(umlDir);
		if(!(ne == null || ne.isNewlyCreated())){
			// The settings would have been edited from there
			OpaeumConfigDialog dlg = new OpaeumConfigDialog(Display.getCurrent().getActiveShell(), ne.getConfig());
			if(dlg.open() == Window.OK){
				reinitialiseConfig(ne);
			}
		}
	}
}
