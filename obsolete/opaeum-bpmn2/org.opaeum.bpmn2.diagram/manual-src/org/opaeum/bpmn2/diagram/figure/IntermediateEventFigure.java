package org.opaeum.bpmn2.diagram.figure;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

public class IntermediateEventFigure extends AbstractEventFigure{
	@Override
	protected void outlineShape(Graphics graphics){
		int lineWidth = Math.max(1,Math.round(getOutlineWidth()/3f));
		graphics.setLineWidth(lineWidth);
		Rectangle bounds2 = getBounds().getCopy();
		bounds2.height=bounds2.height-getOutlineWidth()*2;
		bounds2.width=bounds2.width-getOutlineWidth()*2;
		bounds2.y=bounds2.y+getOutlineWidth();
		bounds2.x=bounds2.x+getOutlineWidth();
		graphics.drawOval(bounds2);
		bounds2.height=bounds2.height-lineWidth*4;
		bounds2.width=bounds2.width-lineWidth*4;
		bounds2.y=bounds2.y+lineWidth*2;
		bounds2.x=bounds2.x+lineWidth*2;
		graphics.drawOval(bounds2);
	}

}
