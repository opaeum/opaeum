package org.nakeduml.uim.editparts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.uml2.uml.Transition;
import org.nakeduml.uim.TransitionAction;
import org.nakeduml.uim.figures.ActionFigure;
import org.nakeduml.uim.util.UmlUimLinks;
import org.topcased.draw2d.figures.Label;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.EMFGraphNodeEditPart;

public class AbstractTransitionActionEditPart extends EMFGraphNodeEditPart{
	public AbstractTransitionActionEditPart(GraphNode obj){
		super(obj);
	}
	protected IFigure createFigure(){
		return new ActionFigure();
	}
	public void refreshVisuals(){
		Transition transition = UmlUimLinks.getInstance(getEObject()).getTransition((TransitionAction) getEObject());
//		Label actionFigure = ((ActionFigure) getFigure()).getActionFigure();
//		if(transition == null){
//			actionFigure.setText("Select Transition");
//		}else{
//			actionFigure.setText(transition.getName());
//		}
		super.refreshVisuals();
	}
}
