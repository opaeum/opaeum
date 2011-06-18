package org.nakeduml.uim.editparts;

import org.eclipse.draw2d.IFigure;
import org.nakeduml.uim.action.ActionKind;
import org.nakeduml.uim.action.BuiltInAction;
import org.nakeduml.uim.figures.ActionFigure;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.EMFGraphNodeEditPart;

public class AbstractBuiltInActionEditPart extends EMFGraphNodeEditPart{
	public AbstractBuiltInActionEditPart(GraphNode obj){
		super(obj);
	}
	protected IFigure createFigure(){
		return new ActionFigure();
	}
	public void refreshVisuals(){
		ActionKind kind = ((BuiltInAction) getEObject()).getKind();
//		Label actionFigure = ((ActionFigure) getFigure()).getActionFigure();
//		if(kind == null){
//			actionFigure.setText("Select Kind");
//		}else{
//			actionFigure.setText(kind.getName());
//		}
		super.refreshVisuals();
	}
}
