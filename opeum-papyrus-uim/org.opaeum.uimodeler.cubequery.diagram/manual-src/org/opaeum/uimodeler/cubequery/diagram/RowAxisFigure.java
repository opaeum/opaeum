package org.opaeum.uimodeler.cubequery.diagram;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.opaeum.uimodeler.cubequery.diagram.edit.parts.RowAxisEntryEditPart.RowAxisEntryFigure;

public class RowAxisFigure extends DefaultSizeNodeFigure{

	public RowAxisFigure(Dimension defSize){
		super(defSize);
	}

	public RowAxisFigure(int width,int height){
		super(width, height);
	}
	public RowAxisEntryFigure getRowAxisEntryFigure(){
		return (RowAxisEntryFigure)getChildren().get(0);
	}

}
