package org.opeum.topcased.activitydiagram.bpm;

import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.topcased.modeler.commands.CreateGraphNodeCommand;
import org.topcased.modeler.di.model.Diagram;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.uml.activitydiagram.policies.ActivityDiagramLayoutEditPolicy;
import org.topcased.modeler.utils.Utils;

public class BusinessProcessDiagramEditPart extends org.topcased.modeler.uml.activitydiagram.edit.ActivityDiagramEditPart{
	public BusinessProcessDiagramEditPart(Diagram model){
		super(model);
	}
	protected EditPolicy getLayoutEditPolicy(){
		return new ActivityDiagramLayoutEditPolicy(){
			protected Command getCreateCommand(EditDomain domain,GraphNode newObject,GraphNode newParent,EObject newContainerParent,Point location,Dimension dimension,
					int attach,List featuresList,boolean needModelUpdate){
				EObject elt = Utils.getElement(newObject);
				if(elt instanceof CallBehaviorAction){
					return new CreateGraphNodeCommand(domain, newObject, newParent, newContainerParent, location, dimension, attach, featuresList, needModelUpdate);
				}else{
					return super.getCreateCommand(domain, newObject, newParent, newContainerParent, location, dimension, attach, featuresList, needModelUpdate);
				}
			}
		};
	}
}