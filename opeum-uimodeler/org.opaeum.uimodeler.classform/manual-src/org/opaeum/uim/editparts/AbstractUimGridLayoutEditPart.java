package org.opeum.uim.editparts;

import org.eclipse.draw2d.IFigure;
import org.opeum.uim.figures.UimGridLayoutFigure;
import org.opeum.uim.layout.UimGridLayout;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.EMFGraphNodeEditPart;

public class AbstractUimGridLayoutEditPart extends EMFGraphNodeEditPart{
	public AbstractUimGridLayoutEditPart(GraphNode obj){
		super(obj);
	}
	protected IFigure createFigure(){
		return new UimGridLayoutFigure();
	}
	public void refreshVisuals(){
		((UimGridLayoutFigure) getFigure()).setNoOfColumns(((UimGridLayout) getEObject()).getNumberOfColumns());
		super.refreshVisuals();
	}
}
