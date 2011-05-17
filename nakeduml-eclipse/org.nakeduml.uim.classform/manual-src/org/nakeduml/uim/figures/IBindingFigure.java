package org.nakeduml.uim.figures;

import org.eclipse.draw2d.IFigure;
import org.topcased.draw2d.figures.ILabel;

public interface IBindingFigure extends IFigure{
	ILabel getBindingLabel();
}
