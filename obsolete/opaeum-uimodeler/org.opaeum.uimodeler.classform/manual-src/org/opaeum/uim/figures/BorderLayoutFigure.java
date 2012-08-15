package org.opaeum.uim.figures;

import org.eclipse.draw2d.XYLayout;

public class BorderLayoutFigure extends AbstractLayoutFigure{
	public BorderLayoutFigure(){
		super();
		getContentPane().setLayoutManager(new XYLayout());
	}
}
