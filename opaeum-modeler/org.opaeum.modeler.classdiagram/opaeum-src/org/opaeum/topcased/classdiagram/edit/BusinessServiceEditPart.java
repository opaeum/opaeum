package org.opaeum.topcased.classdiagram.edit;

import org.eclipse.draw2d.IFigure;
import org.topcased.draw2d.figures.ComposedLabel;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.uml.classdiagram.ClassEditPolicyConstants;
import org.topcased.modeler.uml.classdiagram.edit.InterfaceEditPart;
import org.topcased.modeler.uml.classdiagram.figures.InterfaceFigure;

public class BusinessServiceEditPart extends InterfaceEditPart{
	public BusinessServiceEditPart(GraphNode obj){
		super(obj);
	}
	@Override
	protected IFigure createFigure(){
		return new InterfaceFigure();
	}
	protected void refreshHeaderLabel(){
		super.refreshHeaderLabel();
		InterfaceFigure fig = (InterfaceFigure) getFigure();
		ComposedLabel lbl = (ComposedLabel) fig.getLabel();
		lbl.setPrefix("<<businessService>>");
	}
	protected void createEditPolicies(){
		super.createEditPolicies();
		installEditPolicy(ClassEditPolicyConstants.ASSOCIATION_EDITPOLICY, new FixedAssociationEdgeCreationEditPolicy());
		installEditPolicy(ClassEditPolicyConstants.ASSOCIATIONCLASS_EDITPOLICY, new FixedAssociationClassEdgeCreationEditPolicy());
	}

}
