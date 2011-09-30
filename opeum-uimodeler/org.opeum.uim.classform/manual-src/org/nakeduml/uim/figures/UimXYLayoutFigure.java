package org.opeum.uim.figures;

import org.eclipse.draw2d.XYLayout;

public class UimXYLayoutFigure extends AbstractLayoutFigure{
	public UimXYLayoutFigure(){
		super();
		getContentPane().setLayoutManager(new XYLayout());
	}
}
