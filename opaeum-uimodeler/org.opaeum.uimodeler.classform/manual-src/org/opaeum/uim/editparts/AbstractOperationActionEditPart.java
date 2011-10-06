package org.opaeum.uim.editparts;

import org.eclipse.draw2d.IFigure;
import org.opaeum.uim.figures.ActionFigure;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.EMFGraphNodeEditPart;

public class AbstractOperationActionEditPart extends EMFGraphNodeEditPart{
	public AbstractOperationActionEditPart(GraphNode obj){
		super(obj);
	}
	protected IFigure createFigure(){
		return new ActionFigure();
	}
	public void refreshVisuals(){
//		Operation oper = UmlUimLinks.getInstance(getEObject()).getOperation((OperationAction) getEObject());
//		Label actionFigure = ((ActionFigure) getFigure()).getActionFigure();
//		if(oper == null){
//			actionFigure.setText("Select operation");
//		}else{
//			actionFigure.setText(oper.getName());
//		}
		super.refreshVisuals();
	}
}
