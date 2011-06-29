package org.nakeduml.topcased.classdiagram.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Pattern;
import org.eclipse.swt.widgets.Display;

public class Gradient{
	public static void paintChildren(Graphics graphics, Figure figure){
		Rectangle originalBounds = figure.getBounds();
		Rectangle r = originalBounds.getCopy();
		r.x=(int) Math.round(r.x*graphics.getAbsoluteScale());
		r.y=(int) Math.round(r.y*graphics.getAbsoluteScale());
		r.height=(int) Math.round(r.height*graphics.getAbsoluteScale());
		r.width=(int) Math.round(r.width*graphics.getAbsoluteScale());
		Pattern bcPattern = new Pattern(Display.getCurrent(), r.x, r.y, r.x, r.y+r.height, figure.getBackgroundColor(), ColorConstants.white);
		graphics.setBackgroundPattern(bcPattern);
		graphics.setFillRule(SWT.FILL_EVEN_ODD);
		graphics.fillRectangle(originalBounds.getCopy());
		graphics.setBackgroundPattern(null);
		bcPattern.dispose();
	}
}
