package org.opeum.uml2uim;

import java.io.IOException;

import org.opeum.emf.workspace.EmfWorkspace;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.uml2.uml.NamedElement;
import org.opeum.eclipse.context.OpeumEclipseContext;

public class RegenerateAction extends AbstractUimGenerationAction implements IObjectActionDelegate{
	protected void runActionRecursively(NamedElement modelElement,IAction action){
		doGenerate(modelElement);
	}

	public static void doGenerate(NamedElement modelElement){
		try{
			OpeumEclipseContext e = OpeumEclipseContext.getCurrentContext();
			EmfWorkspace workspace = e.getCurrentEmfWorkspace();
			
			ResourceSet uimResourceSet=new ResourceSetImpl();
			FormFolderSynchronizer ffs = new FormFolderSynchronizer(workspace,uimResourceSet, true);
			ffs.visitWorkspace(workspace);//load existing folder model
			//build required parent folders
			ffs.visitUpThenDown(modelElement);
			FormSynchronizer fs = new FormSynchronizer(workspace,uimResourceSet, true);
			fs.visitRecursively(modelElement);
			DiagramSynchronizer ds = new DiagramSynchronizer(workspace,uimResourceSet,false);
			ds.visitRecursively(modelElement);
			UimSynchronizationPhase. save(workspace.getDirectoryUri(),workspace.getResourceSet());
			UimSynchronizationPhase.save(workspace.getDirectoryUri(),uimResourceSet);
		}catch(IOException e){
			throw new RuntimeException(e);
		}
	}
}
