package org.opaeum.uimodeler.common.figures;

import org.opaeum.uim.swt.IUimFieldComposite;

public interface IUimFieldFigure extends ISWTFigure{

	void setMinimumLabelWidth(Integer minimumLabelWidth);

	IUimFieldComposite getComposite();

	void setMinimumLabelHeigh(Integer newValue);

}
