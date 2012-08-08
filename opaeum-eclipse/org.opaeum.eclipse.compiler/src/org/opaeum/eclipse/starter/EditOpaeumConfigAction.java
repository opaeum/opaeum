package org.opaeum.eclipse.starter;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.opaeum.eclipse.OpaeumConfigDialog;
import org.opaeum.eclipse.OpaeumEclipsePlugin;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.javasync.JavaTransformationProcessManager;
import org.opaeum.feature.TransformationProcess;

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
		if(!(ne==null || ne.isNewlyCreated())){
			// The settings would have been edited from there
			OpaeumConfigDialog dlg = new OpaeumConfigDialog(Display.getCurrent().getActiveShell(), ne.getConfig());
			if(dlg.open() == Window.OK){
				ne.reinitialize();
				TransformationProcess process = JavaTransformationProcessManager.getTransformationProcessFor(umlDir);
				if(process != null){
					JavaTransformationProcessManager.reinitializeProcess(process, ne.getConfig(),umlDir);
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
