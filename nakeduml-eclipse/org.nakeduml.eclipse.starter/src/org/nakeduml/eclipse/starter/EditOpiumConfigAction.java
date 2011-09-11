package org.nakeduml.eclipse.starter;

import net.sf.nakeduml.feature.TransformationProcess;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.nakeduml.eclipse.NakedUmlConfigDialog;
import org.nakeduml.eclipse.NakedUmlEclipsePlugin;
import org.nakeduml.topcased.uml.editor.NakedUmlEclipseContext;
import org.nakeduml.topcased.uml.editor.NakedUmlEditor;

public class EditOpiumConfigAction extends AbstractOpiumAction{
	public EditOpiumConfigAction(IStructuredSelection selection2){
		super(selection2, "Edit Opium Settings");
	}
	@Override
	// public void run(IAction action){
	public void run(){
		// Load classes
		NakedUmlEclipsePlugin.getDefault();
		IContainer umlDir = (IContainer) selection.getFirstElement();
		NakedUmlEclipseContext ne = NakedUmlEditor.findOrCreateContextFor(umlDir);
		if(!ne.isNewlyCreated()){
			// The settings would have been edited from there
			NakedUmlConfigDialog dlg = new NakedUmlConfigDialog(Display.getCurrent().getActiveShell(), ne.getUmlElementCache().getConfig());
			if(dlg.open() != SWT.OK){
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
