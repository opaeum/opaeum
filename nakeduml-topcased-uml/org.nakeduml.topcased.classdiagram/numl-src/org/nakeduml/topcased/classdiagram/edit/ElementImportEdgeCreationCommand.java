package org.nakeduml.topcased.classdiagram.edit;

import org.eclipse.gef.EditDomain;
import org.topcased.modeler.commands.CreateTypedEdgeCommand;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;

public class ElementImportEdgeCreationCommand extends CreateTypedEdgeCommand{
	public ElementImportEdgeCreationCommand(EditDomain domain,GraphEdge newObj,GraphElement src){
		this(domain, newObj, src, true);
	}
	public ElementImportEdgeCreationCommand(EditDomain domain,GraphEdge newObj,GraphElement src,boolean needModelUpdate){
		super(domain, newObj, src, needModelUpdate);
	}
	protected void redoModel(){
		super.redoModel();
	}
	protected void undoModel(){
		super.undoModel();
	}
}