package org.nakeduml.eclipse.starter;

import java.io.File;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.TransformationProcess;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.nakeduml.eclipse.NakedUmlEclipsePlugin;
import org.nakeduml.topcased.uml.editor.NakedUmlEclipseContext;
import org.nakeduml.topcased.uml.editor.NakedUmlEditor;

public class ClearOpiumCacheACtion extends AbstractOpiumAction{
	public ClearOpiumCacheACtion(IStructuredSelection selection){
		super(selection, "Clear Opium Cache");
	}
	public void run(){
		IFile file2 = getCfgFile();
		// Load classes
		NakedUmlEclipsePlugin.getDefault();
		IWorkspaceRoot workspace = ResourcesPlugin.getWorkspace().getRoot();
		NakedUmlConfig cfg = new NakedUmlConfig(file2.getLocation().toFile());
		cfg .setOutputRoot(new File(workspace.getLocation().toFile(), cfg.getWorkspaceIdentifier()));
		IContainer umlDir = (IContainer) selection.getFirstElement();
		NakedUmlEclipseContext ne = NakedUmlEditor.getNakedUmlEclipseContextFor(umlDir);
		ne.reinitialize(cfg);
		TransformationProcess process = JavaTransformationProcessManager.getTransformationProcessFor(umlDir);
		System.gc();
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
