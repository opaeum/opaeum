package org.opaeum.uimodeler.cubequery.diagram;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;

public class MeasurePropertyFigure extends DefaultSizeNodeFigure{

	public MeasurePropertyFigure(Dimension defSize){
		super(defSize);
	}

	public MeasurePropertyFigure(int width,int height){
		super(width, height);
	}
}
