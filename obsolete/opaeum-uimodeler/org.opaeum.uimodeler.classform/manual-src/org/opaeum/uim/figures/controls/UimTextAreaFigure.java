package org.opaeum.uim.figures.controls;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gmf.runtime.draw2d.ui.figures.RectangularDropShadowLineBorder;
import org.topcased.draw2d.figures.ILabel;
import org.topcased.draw2d.figures.Label;

public class UimTextAreaFigure extends Label implements IControlFigure{
	public UimTextAreaFigure(String string){
		super(string);
		init();
	}
	public UimTextAreaFigure(){
		super();
		init();
	}
	private void init(){
		setBorder(new RectangularDropShadowLineBorder(1));
		minSize = new Dimension(100, 100);
	}
	@Override
	public ILabel getBindingLabel(){
		return this;
	}
}
