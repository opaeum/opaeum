package org.opeum.uim.editparts;

import org.eclipse.draw2d.IFigure;
import org.opeum.uim.action.NavigationToOperation;
import org.opeum.uim.figures.NavigationToOperationFigure;
import org.topcased.modeler.di.model.GraphNode;

public class AbstractNavigationToOperationEditPart extends BoundEditPart{
	public AbstractNavigationToOperationEditPart(GraphNode obj){
		super(obj);
	}
	protected IFigure createFigure(){
		return new NavigationToOperationFigure();
	}
	public void refreshVisuals(){
		super.refreshVisuals();
		NavigationToOperationFigure ntef = (NavigationToOperationFigure) getFigure();
		NavigationToOperation nte = (NavigationToOperation) getEObject();
		String formName = nte.getToForm() == null ? "Select Form" : nte.getToForm().getName();
		ntef.getToFormFigure().setText(formName);
	}
}