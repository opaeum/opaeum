package org.nakeduml.topcased.classdiagram;

import org.eclipse.draw2d.IFigure;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Interface;
import org.topcased.draw2d.figures.ComposedLabel;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.uml.UMLLabel;
import org.topcased.modeler.uml.UMLTools;
import org.topcased.modeler.uml.classdiagram.edit.InterfaceEditPart;
import org.topcased.modeler.uml.classdiagram.figures.InterfaceFigure;
import org.topcased.modeler.utils.Utils;

public class ResponsibilityEditPart extends InterfaceEditPart{
	public ResponsibilityEditPart(GraphNode obj){
		super(obj);
	}
	@Override
	protected IFigure createFigure(){
		return new ResponsibilityFigure();
	}
	protected void refreshHeaderLabel(){
		super.refreshHeaderLabel();
		InterfaceFigure fig = (InterfaceFigure) getFigure();
		ComposedLabel lbl = (ComposedLabel) fig.getLabel();
		lbl.setPrefix("<<responsibility>>");
	}
}
