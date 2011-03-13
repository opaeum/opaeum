package org.nakeduml.uim.editparts;


import org.eclipse.draw2d.IFigure;
import org.nakeduml.uim.UIMGridLayout;
import org.nakeduml.uim.figures.UIMGridLayoutFigure;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.EMFGraphNodeEditPart;

public class AbstractUIMGridLayoutEditPart extends EMFGraphNodeEditPart {
	public AbstractUIMGridLayoutEditPart(GraphNode obj) {
		super(obj);
	}

	protected IFigure createFigure() {
		return new UIMGridLayoutFigure();
	}

	public void refreshVisuals() {
		((UIMGridLayoutFigure) getFigure()).setNoOfColumns(((UIMGridLayout) getEObject())
				.getNumberOfColumns());
		super.refreshVisuals();
	}
}
