package org.nakeduml.eclipse.starter;

import java.io.File;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.TransformationProcess;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
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
		File file2 = getCfgFile();
		// Load classes
		NakedUmlEclipsePlugin.getDefault();
		NakedUmlConfigDialog dlg = new NakedUmlConfigDialog(Display.getCurrent().getActiveShell(), file2);
		dlg.open();
		IWorkspaceRoot workspace = ResourcesPlugin.getWorkspace().getRoot();
		NakedUmlConfig cfg = dlg.getConfig();
		cfg.setOutputRoot(new File(workspace.getLocation().toFile(), cfg.getWorkspaceIdentifier()));
		IContainer umlDir = (IContainer) selection.getFirstElement();
		NakedUmlEclipseContext ne = NakedUmlEditor.getNakedUmlEclipseContextFor(umlDir);
		ne.reinitialize(cfg);
		TransformationProcess process = JavaTransformationProcessManager.getTransformationProcessFor(umlDir);
		if(process != null){
			JavaTransformationProcessManager.reinitializeProcess(process, cfg, ne);
		}
		try{
			umlDir.refreshLocal(IResource.DEPTH_INFINITE, null);
		}catch(CoreException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
