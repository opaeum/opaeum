package org.nakeduml.topcased.classdiagram;

import org.topcased.draw2d.figures.ComposedLabel;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.uml.classdiagram.edit.ClassEditPart;
import org.topcased.modeler.uml.classdiagram.figures.ClassFigure;

public class BusinessComponentEditPart extends ClassEditPart{

	public BusinessComponentEditPart(GraphNode obj){
		super(obj);
	}
	protected void refreshHeaderLabel(){
		super.refreshHeaderLabel();
		ClassFigure fig = (ClassFigure) getFigure();
		ComposedLabel lbl = (ComposedLabel) fig.getLabel();
		lbl.setPrefix("<<businessComponent>>");
	}
}
