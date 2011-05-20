/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package org.nakeduml.uim.editparts;

import org.eclipse.draw2d.IFigure;
import org.nakeduml.uim.figures.UimDataTableFigure;
import org.topcased.modeler.di.model.GraphNode;

public class AbstractUimDataTableEditPart extends BoundEditPart{
	public AbstractUimDataTableEditPart(GraphNode obj){
		super(obj);
	}
	protected IFigure createFigure(){
		return new UimDataTableFigure();
	}
}