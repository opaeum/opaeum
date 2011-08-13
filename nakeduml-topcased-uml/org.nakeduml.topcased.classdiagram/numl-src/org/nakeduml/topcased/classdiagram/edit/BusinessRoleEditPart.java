package org.nakeduml.topcased.classdiagram.edit;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.nakeduml.topcased.classdiagram.figure.Gradient;
import org.topcased.draw2d.figures.ClassFigure;
import org.topcased.draw2d.figures.ComposedLabel;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.uml.classdiagram.ClassEditPolicyConstants;
import org.topcased.modeler.uml.classdiagram.edit.ClassEditPart;

public class BusinessRoleEditPart extends ClassEditPart{
	public BusinessRoleEditPart(GraphNode obj){
		super(obj);
	}
	protected void refreshHeaderLabel(){
		super.refreshHeaderLabel();
		ClassFigure fig = (ClassFigure) getFigure();
		ComposedLabel lbl = (ComposedLabel) fig.getLabel();
		lbl.setPrefix("<<businessRole>>");
	}
	@Override
	protected IFigure createFigure(){
		return new ClassFigure(){
			@Override
			public void paintChildren(Graphics graphics){
				Gradient.paintChildren(graphics, this);
				super.paintChildren(graphics);
			}
		};
	}

	protected void createEditPolicies(){
		super.createEditPolicies();
		installEditPolicy(ClassEditPolicyConstants.ASSOCIATION_EDITPOLICY, new FixedAssociationEdgeCreationEditPolicy());
		installEditPolicy(ClassEditPolicyConstants.ASSOCIATIONCLASS_EDITPOLICY, new FixedAssociationClassEdgeCreationEditPolicy());
	}
}
