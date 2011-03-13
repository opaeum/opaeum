package org.nakeduml.uim.editparts;


import org.eclipse.draw2d.IFigure;
import org.eclipse.uml2.uml.Operation;
import org.nakeduml.uim.OperationAction;
import org.nakeduml.uim.figures.ActionFigure;
import org.topcased.draw2d.figures.Label;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.EMFGraphNodeEditPart;

public class AbstractOperationActionEditPart extends EMFGraphNodeEditPart {
	public AbstractOperationActionEditPart(GraphNode obj) {
		super(obj);
	}

	protected IFigure createFigure() {
		return new ActionFigure();
	}

	public void refreshVisuals() {
		Operation oper = ((OperationAction) getEObject()).getOperation();
		Label actionFigure = ((ActionFigure) getFigure()).getActionFigure();
		if (oper == null) {
			actionFigure.setText("Select operation");
		} else {
			actionFigure.setText(oper.getName());
		}
		super.refreshVisuals();
	}
}
