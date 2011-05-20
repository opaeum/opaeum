package org.nakeduml.uim.layouts;

import java.util.Collection;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

public class FullLayoutManager extends XYLayout implements IUimLayoutManager{
	@Override
	public void layout(IFigure parent){
		Collection<Rectangle> values = this.constraints.values();
		layout(values,parent.getSize());
		super.layout(parent);
	}

	public void layout(Collection<Rectangle> values, Dimension parentSize){
		for(Rectangle figure:values){
			figure.x=1;
			figure.y=1;
			figure.width= parentSize.width-2;
			figure.height=parentSize.height-2;
		}
	}
}