package org.opaeum.uim.figures;
import org.eclipse.draw2d.IFigure;
import org.eclipse.swt.widgets.Widget;


public interface ISWTFigure extends IFigure{
	Widget getWidget();

	void setLabelText(String string);
	
	void markForRepaint();
}
