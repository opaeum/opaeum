package org.nakeduml.uim.figures.controls;


import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gmf.runtime.draw2d.ui.figures.RectangularDropShadowLineBorder;
import org.nakeduml.uim.figures.IControlFigure;
import org.topcased.draw2d.figures.ILabel;
import org.topcased.draw2d.figures.Label;

public class UIMTextFigure extends Label implements IControlFigure {

	public UIMTextFigure(String string) {
		super(string);
		init();
	}


	public UIMTextFigure() {
		super();
		init();

	}

	private void init() {
		setBorder(new RectangularDropShadowLineBorder(1));
		minSize=new Dimension(10,10);
	}
	@Override
	public ILabel getBindingLabel() {
		return this;
	}

}
