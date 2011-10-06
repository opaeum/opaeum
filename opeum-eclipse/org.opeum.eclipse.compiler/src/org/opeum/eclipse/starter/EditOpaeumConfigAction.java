package org.opeum.eclipse.starter;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.opeum.eclipse.OpeumConfigDialog;
import org.opeum.eclipse.OpeumEclipsePlugin;
import org.opeum.eclipse.context.OpeumEclipseContext;
import org.opeum.eclipse.javasync.JavaTransformationProcessManager;
import org.opeum.feature.TransformationProcess;

public class EditOpiumConfigAction extends AbstractOpiumAction{
	public EditOpiumConfigAction(IStructuredSelection selection2){
		super(selection2, "Edit Opium Settings");
	}
	@Override
	// public void run(IAction action){
	public void run(){
		// Load classes
		OpeumEclipsePlugin.getDefault();
		IContainer umlDir = (IContainer) selection.getFirstElement();
		OpeumEclipseContext ne = OpeumEclipseContext.findOrCreateContextFor(umlDir);
		if(!(ne==null || ne.isNewlyCreated())){
			// The settings would have been edited from there
			OpeumConfigDialog dlg = new OpeumConfigDialog(Display.getCurrent().getActiveShell(), ne.getConfig());
			if(dlg.open() == Window.OK){
				ne.reinitialize();
				TransformationProcess process = JavaTransformationProcessManager.getTransformationProcessFor(umlDir);
				if(process != null){
					JavaTransformationProcessManager.reinitializeProcess(process, ne);
				}
				try{
					umlDir.refreshLocal(IResource.DEPTH_INFINITE, null);
				}catch(CoreException e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
