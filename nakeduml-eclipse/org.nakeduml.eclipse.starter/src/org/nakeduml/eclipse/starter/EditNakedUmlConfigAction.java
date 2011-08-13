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
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.nakeduml.eclipse.NakedUmlConfigDialog;
import org.nakeduml.eclipse.NakedUmlEclipsePlugin;
import org.nakeduml.topcased.uml.editor.NakedUmlEclipseContext;
import org.nakeduml.topcased.uml.editor.NakedUmlEditor;

public class EditNakedUmlConfigAction implements IObjectActionDelegate{
	private IStructuredSelection selection;
	private IWorkbenchPart activePart;
	@Override
	public void run(IAction action){
		File file2 = getCfgFile();
		// Load classes
		NakedUmlEclipsePlugin.getDefault();
		NakedUmlConfigDialog dlg = new NakedUmlConfigDialog(activePart.getSite().getShell(), file2);
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
	private File getCfgFile(){
		return getConfigFile(selection);
	}
	private File getConfigFile(IStructuredSelection selection2){
		return new File(((IFolder) selection2.getFirstElement()).getLocation().toFile(), "nakeduml.properties");
	}
	@Override
	public void selectionChanged(IAction action,ISelection selection){
		this.selection = (IStructuredSelection) selection;
		action.setEnabled(false);
		if(hasConfigFile()){
			action.setText("Edit NakedUML Configuration");
			action.setEnabled(true);
		}else if(hasUmlModels()){
			action.setText("Convert to NakedUML Model Directory");
			action.setEnabled(true);
		}
	}
	private boolean hasUmlModels(){
		IFolder firstElement = (IFolder) selection.getFirstElement();
		try{
			if(firstElement != null){
				for(IResource r:firstElement.members()){
					if(r instanceof IFile && r.getFileExtension().equals("uml")){
						return true;
					}
				}
			}
		}catch(CoreException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
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
