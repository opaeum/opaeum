package org.opaeum.modeler.compositestructurediagram;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.uml2.uml.Port;
import org.topcased.modeler.di.model.Diagram;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.policies.ModelerLayoutEditPolicy;
import org.topcased.modeler.uml.compositestructuresdiagram.CompositeStructuresEditPartFactory;
import org.topcased.modeler.uml.compositestructuresdiagram.edit.CompositeStructuresDiagramEditPart;
import org.topcased.modeler.utils.Utils;

public class BusinessStructureEditPartFactory extends CompositeStructuresEditPartFactory{
	public EditPart createEditPart(EditPart context,Object model){
		if(model instanceof Diagram){
			return new CompositeStructuresDiagramEditPart((Diagram) model){
				@Override
				protected EditPolicy getLayoutEditPolicy(){
					return new ModelerLayoutEditPolicy(){
						protected boolean isAttachedToBorder(GraphNode node){
							if(Utils.getElement(node) instanceof Port){
								return true;
							}
							return super.isAttachedToBorder(node);
						}
						@Override
						protected boolean isSeveralDisplayAllowed(GraphNode parent,GraphNode child,boolean needModelUpdate){
							return false;
						}
					};
				}
			};
		}
		return super.createEditPart(context, model);
	}
}