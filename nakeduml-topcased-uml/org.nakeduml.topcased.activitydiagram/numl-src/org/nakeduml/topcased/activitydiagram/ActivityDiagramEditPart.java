package org.nakeduml.topcased.activitydiagram;

import org.eclipse.gef.EditPolicy;
import org.topcased.modeler.di.model.Diagram;
import org.topcased.modeler.uml.activitydiagram.policies.ActivityDiagramLayoutEditPolicy;

public class ActivityDiagramEditPart extends org.topcased.modeler.uml.activitydiagram.edit.ActivityDiagramEditPart{
	public ActivityDiagramEditPart(Diagram model){
		super(model);
	}
	protected EditPolicy getLayoutEditPolicy(){
		return new ActivityDiagramLayoutEditPolicy();
	}

}