package org.opaeum.uim.editparts;

import org.eclipse.draw2d.IFigure;
import org.opaeum.uim.figures.ActionFigure;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.EMFGraphNodeEditPart;

public class AbstractBuiltInActionEditPart extends EMFGraphNodeEditPart{
	public AbstractBuiltInActionEditPart(GraphNode obj){
		super(obj);
	}
	protected IFigure createFigure(){
		ActionFigure actionFigure = new ActionFigure();
		new DirectEditHelper(actionFigure, this);
		return actionFigure;
	}
	public void refreshVisuals(){
//		ActionKind kind = ((BuiltInAction) getEObject()).getKind();
//		Label actionFigure = ((ActionFigure) getFigure()).getActionFigure();
//		if(kind == null){
//			actionFigure.setText("Select Kind");
//		}else{
//			actionFigure.setText(kind.getName());
//		}
		super.refreshVisuals();
	}
}
