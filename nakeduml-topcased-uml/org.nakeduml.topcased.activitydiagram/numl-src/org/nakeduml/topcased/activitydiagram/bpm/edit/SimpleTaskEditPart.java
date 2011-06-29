package org.nakeduml.topcased.activitydiagram.bpm.edit;

import org.topcased.draw2d.figures.ComposedLabel;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.uml.activitydiagram.edit.OpaqueActionEditPart;

public class SimpleTaskEditPart extends OpaqueActionEditPart{
	public SimpleTaskEditPart(GraphNode obj){
		super(obj);
	}
	protected void refreshHeaderLabel(){
		super.refreshHeaderLabel();
		ComposedLabel composedLabel = (ComposedLabel) getLabel();
		composedLabel.setPrefix("<<simpleTask>>");
	}
	
}
