package org.nakeduml.uml2uim;

import java.io.IOException;

import net.sf.nakeduml.emf.workspace.EmfWorkspace;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.nakeduml.uim.util.UmlUimLinks;

public class SynchronizeAction extends AbstractUimGenerationAction implements IObjectActionDelegate {



	protected void runActionRecursively(NamedElement modelElement) {
		try{
			Model umlModel = modelElement.getModel();
			EmfWorkspace workspace = new EmfWorkspace(umlModel, null, umlModel.getName());
			
			ResourceSet uimResourceSet=new ResourceSetImpl();
			FormFolderSynchronizer ffs = new FormFolderSynchronizer(workspace,uimResourceSet, true);
			ffs.visitWorkspace(workspace);//load existing folder model
			UmlUimLinks.init(uimResourceSet);
			UmlUimLinks.init(workspace.getResourceSet());
			//build required parent folders
			ffs.visitUpThenDown(modelElement);
			FormSynchronizer fs = new FormSynchronizer(workspace,uimResourceSet, false);
			fs.visitRecursively(modelElement);
			save(workspace.getDirectoryUri(),workspace.getResourceSet());
			save(workspace.getDirectoryUri(),uimResourceSet);
		}catch(IOException e){
			throw new RuntimeException(e);
		}
	}


}
