/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package org.nakeduml.uim.editparts;


import org.eclipse.draw2d.IFigure;
import org.nakeduml.uim.figures.UIMDataTableFigure;
import org.topcased.modeler.di.model.GraphNode;

public class AbstractUIMDataTableEditPart extends BoundEditPart {
	public AbstractUIMDataTableEditPart(GraphNode obj) {
		super(obj);
	}

	protected IFigure createFigure() {

		return new UIMDataTableFigure();
	}
}