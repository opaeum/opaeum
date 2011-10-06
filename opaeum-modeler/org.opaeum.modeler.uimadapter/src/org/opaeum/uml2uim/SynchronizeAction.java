package org.opaeum.uml2uim;

import java.io.IOException;

import org.opaeum.emf.workspace.EmfWorkspace;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.uml2.uml.NamedElement;
import org.opaeum.eclipse.context.OpaeumEclipseContext;

public class SynchronizeAction extends AbstractUimGenerationAction implements IObjectActionDelegate{
	protected void runActionRecursively(NamedElement modelElement,IAction action){
		doSynchronize(modelElement);
	}

	public static void doSynchronize(NamedElement modelElement){
		try{
			EmfWorkspace workspace = OpaeumEclipseContext.getCurrentContext().getCurrentEmfWorkspace();
			ResourceSet uimResourceSet = new ResourceSetImpl();
			FormFolderSynchronizer ffs = new FormFolderSynchronizer(workspace, uimResourceSet, true);
			ffs.visitWorkspace(workspace);// load existing folder model
			// build required parent folders
			ffs.visitUpThenDown(modelElement);
			FormSynchronizer fs = new FormSynchronizer(workspace, uimResourceSet, false);
			fs.visitRecursively(modelElement);
			DiagramSynchronizer ds = new DiagramSynchronizer(workspace, uimResourceSet, false);
			ds.visitRecursively(modelElement);
			UimSynchronizationPhase.save(workspace.getDirectoryUri(), workspace.getResourceSet());
			UimSynchronizationPhase.save(workspace.getDirectoryUri(), uimResourceSet);
		}catch(IOException e2){
			throw new RuntimeException(e2);
		}
	}
}
