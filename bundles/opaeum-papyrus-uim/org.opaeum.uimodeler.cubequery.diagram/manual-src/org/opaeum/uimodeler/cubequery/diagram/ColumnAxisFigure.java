package org.opaeum.uimodeler.cubequery.diagram;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.opaeum.uimodeler.cubequery.diagram.edit.parts.ColumnAxisEntryEditPart.ColumnAxisEntryFigure;

public class ColumnAxisFigure extends DefaultSizeNodeFigure{
	public ColumnAxisFigure(Dimension defSize){
		super(defSize);
	}

	public ColumnAxisFigure(int width,int height){
		super(width, height);
	}


	public ColumnAxisEntryFigure getColumnAxisEntryFigure(){
		return (ColumnAxisEntryFigure)getChildren().get(0);
	}
}
