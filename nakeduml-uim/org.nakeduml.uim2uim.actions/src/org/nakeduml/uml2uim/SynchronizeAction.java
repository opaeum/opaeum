package org.nakeduml.uml2uim;

import java.io.IOException;

import net.sf.nakeduml.emf.workspace.EmfWorkspace;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.nakeduml.topcased.uml.editor.NakedUmlEditor;
import org.nakeduml.uim.util.UmlUimLinks;

public class SynchronizeAction extends AbstractUimGenerationAction implements IObjectActionDelegate{
	protected void runActionRecursively(NamedElement modelElement,IAction action){
		doSynchronize(modelElement,findNakedUmlEditor(modelElement));
	}

	public static void doSynchronize(NamedElement modelElement, NakedUmlEditor e){
		try{
			EmfWorkspace workspace = e.getUmlElementCache().getEmfWorkspace();
			ResourceSet uimResourceSet = new ResourceSetImpl();
			UmlUimLinks.associate(uimResourceSet, e.getUmlElementCache());
			FormFolderSynchronizer ffs = new FormFolderSynchronizer(workspace, uimResourceSet, true,e.getUmlElementCache());
			ffs.visitWorkspace(workspace);// load existing folder model
			// build required parent folders
			ffs.visitUpThenDown(modelElement);
			FormSynchronizer fs = new FormSynchronizer(workspace, uimResourceSet, false,e.getUmlElementCache());
			fs.visitRecursively(modelElement);
			DiagramSynchronizer ds = new DiagramSynchronizer(workspace, uimResourceSet, false,e.getUmlElementCache());
			ds.visitRecursively(modelElement);
			UimSynchronizationPhase.save(workspace.getDirectoryUri(), workspace.getResourceSet());
			UimSynchronizationPhase.save(workspace.getDirectoryUri(), uimResourceSet);
		}catch(IOException e2){
			throw new RuntimeException(e2);
		}
	}
}
