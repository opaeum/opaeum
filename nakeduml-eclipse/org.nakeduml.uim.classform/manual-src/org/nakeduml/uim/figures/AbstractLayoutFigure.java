package org.nakeduml.uim.figures;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.topcased.draw2d.figures.IContainerFigure;

public class AbstractLayoutFigure extends Figure implements IContainerFigure {
	public AbstractLayoutFigure() {
		super();
	}

	@Override
	public IFigure getContentPane() {
		return this;
	}

}
