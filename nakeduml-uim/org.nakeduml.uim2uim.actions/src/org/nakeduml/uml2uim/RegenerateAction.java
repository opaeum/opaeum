package org.nakeduml.uml2uim;

import java.io.IOException;

import net.sf.nakeduml.emf.workspace.EmfWorkspace;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.nakeduml.uim.util.UmlUimLinks;

public class RegenerateAction extends AbstractUimGenerationAction implements IObjectActionDelegate{
	//FOr operation: task (error if not task) and invocation form
	//for statemachine and activity: edit form
	//for class : edit and create form
	//for opaque action: task (error if not task)
	//for state: stateform
	protected void runActionRecursively(NamedElement modelElement){
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
			FormSynchronizer fs = new FormSynchronizer(workspace,uimResourceSet, true);
			fs.visitRecursively(modelElement);
			DiagramSynchronizer ds = new DiagramSynchronizer(workspace,uimResourceSet,false);
			ds.visitRecursively(modelElement);
			save(workspace.getDirectoryUri(),workspace.getResourceSet());
			save(workspace.getDirectoryUri(),uimResourceSet);
		}catch(IOException e){
			throw new RuntimeException(e);
		}
	}
}
