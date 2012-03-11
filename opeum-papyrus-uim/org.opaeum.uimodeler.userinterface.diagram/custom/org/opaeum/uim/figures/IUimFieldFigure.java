package org.opaeum.uim.figures;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public interface IUimFieldFigure extends ISWTFigure{

	void setMinimumLabelWidth(Integer minimumLabelWidth);

	Composite getComposite();

	void setMinimumLabelHeigh(Integer newValue);

	void setControl(Control button);

	Control getControl();
}
