package org.nakeduml.uml2uim;

import java.io.IOException;

import net.sf.nakeduml.emf.workspace.EmfWorkspace;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.nakeduml.uim.util.UmlUimLinks;

public class RegenerateAction extends AbstractUimGenerationAction implements IObjectActionDelegate{
	protected void runActionRecursively(NamedElement modelElement,IAction action){
		doGenerate(modelElement);
	}

	public static void doGenerate(NamedElement modelElement){
		try{
			Model umlModel = modelElement.getModel();
			EmfWorkspace workspace = new EmfWorkspace(umlModel, null, umlModel.getName());
			
			ResourceSet uimResourceSet=new ResourceSetImpl();
			UmlUimLinks.associate(uimResourceSet, workspace.getUmlElementMap());
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
