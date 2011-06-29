package org.nakeduml.topcased.activitydiagram.bpm;

import org.eclipse.gef.EditPolicy;
import org.topcased.modeler.di.model.Diagram;
import org.topcased.modeler.uml.activitydiagram.policies.ActivityDiagramLayoutEditPolicy;

public class BusinessProcessDiagramEditPart extends org.topcased.modeler.uml.activitydiagram.edit.ActivityDiagramEditPart{
	public BusinessProcessDiagramEditPart(Diagram model){
		super(model);
	}
	protected EditPolicy getLayoutEditPolicy(){
		return new ActivityDiagramLayoutEditPolicy();
	}

}