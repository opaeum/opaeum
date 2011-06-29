package org.nakeduml.topcased.activitydiagram.method;

import org.eclipse.gef.EditPolicy;
import org.topcased.modeler.di.model.Diagram;
import org.topcased.modeler.uml.activitydiagram.policies.ActivityDiagramLayoutEditPolicy;

public class MethodDiagramEditPart extends org.topcased.modeler.uml.activitydiagram.edit.ActivityDiagramEditPart{
	public MethodDiagramEditPart(Diagram model){
		super(model);
	}
	protected EditPolicy getLayoutEditPolicy(){
		return new ActivityDiagramLayoutEditPolicy();
	}

}