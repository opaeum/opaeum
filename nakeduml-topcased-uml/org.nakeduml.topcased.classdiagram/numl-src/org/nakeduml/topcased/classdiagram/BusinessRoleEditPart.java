package org.nakeduml.topcased.classdiagram;

import org.topcased.draw2d.figures.ClassFigure;
import org.topcased.draw2d.figures.ComposedLabel;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.uml.classdiagram.edit.ClassEditPart;

public class BusinessRoleEditPart extends ClassEditPart {

	public BusinessRoleEditPart(GraphNode obj){
		super(obj);
		// TODO Auto-generated constructor stub
	}
	protected void refreshHeaderLabel(){
		super.refreshHeaderLabel();
		ClassFigure fig = (ClassFigure) getFigure();
		ComposedLabel lbl = (ComposedLabel) fig.getLabel();
		lbl.setPrefix("<<businessRole>>");
	}
}
