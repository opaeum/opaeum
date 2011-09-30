package org.opeum.uim.figures;

import org.opeum.uim.layouts.StackLayout;
import org.topcased.draw2d.figures.ContainerFigure;

public class LayoutContainingFigure extends ContainerFigure{
	public LayoutContainingFigure(){
		super();
		getContentPane().setLayoutManager(new StackLayout());
		// getContentPane().setBorder(null);
	}
}
