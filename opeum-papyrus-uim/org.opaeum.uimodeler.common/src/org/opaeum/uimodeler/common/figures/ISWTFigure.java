package org.opaeum.uimodeler.common.figures;
import org.eclipse.draw2d.IFigure;
import org.eclipse.swt.widgets.Control;


public interface ISWTFigure extends IFigure{
	Control getWidget();

	void setLabelText(String string);
	
}
