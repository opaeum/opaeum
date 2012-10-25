package org.opaeum.uimodeler.common.figures;
import org.eclipse.draw2d.IFigure;
import org.eclipse.swt.widgets.Control;
import org.opaeum.uim.swt.IUimWidget;


public interface ISWTFigure extends IFigure{
	IUimWidget getWidget();

	void setLabelText(String string);
	
}
