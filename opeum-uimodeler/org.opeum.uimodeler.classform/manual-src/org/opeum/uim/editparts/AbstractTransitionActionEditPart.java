package org.opeum.uim.editparts;

import org.eclipse.draw2d.IFigure;
import org.opeum.uim.figures.ActionFigure;
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
//		Transition transition = UimEditor.getCurrentUmlLinks().getTransition((TransitionAction) getEObject());
//		Label actionFigure = ((ActionFigure) getFigure()).getActionFigure();
//		if(transition == null){
//			actionFigure.setText("Select Transition");
//		}else{
//			actionFigure.setText(transition.getName());
//		}
		super.refreshVisuals();
	}
}
