package org.opaeum.bpmn2.diagram.figure;

import java.util.Collection;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

public class FullLayoutManager extends XYLayout {
	@SuppressWarnings("unchecked")
	@Override
	public void layout(IFigure parent){
		Collection<Rectangle> values = this.constraints.values();
		layout(values,parent.getSize());
		super.layout(parent);
	}

	public void layout(Collection<Rectangle> values, Dimension parentSize){
		for(Rectangle figure:values){
			figure.x=2;
			figure.y=2;
			figure.width= parentSize.width-4;
			figure.height=parentSize.height-4;
		}
	}
}