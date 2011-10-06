package org.opeum.uim.figures.controls;

import org.eclipse.draw2d.IFigure;
import org.topcased.draw2d.figures.ILabel;

public interface IControlFigure extends IFigure{
	ILabel getBindingLabel();
}
