package org.opaeum.modeler.compositestructurediagram;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.topcased.modeler.uml.compositestructuresdiagram.figures.PropertyFigure;

public class BusinessStructurePropertyFigure extends PropertyFigure{
	List<String> providedInterfaces = new ArrayList<String>();
	public void removeProvidedInterface(String n){
		providedInterfaces.remove(n);
	}
	public void addProvidedInterface(String n){
		providedInterfaces.add(n);
	}
	@Override
	public void paint(Graphics graphics){
		super.paint(graphics);
		int i = 0;
		Point middle = new Point(getBounds().x + getBounds().width / 2, getBounds().y + getBounds().height / 2);
		for(String string:providedInterfaces){
			Point p1 = new Point(getBounds().x + i * 15, getBounds().y);
			Point p2 = new Point(p1.x - Math.abs(p1.x - middle.x), p1.y - Math.abs(p1.y - middle.y));
			graphics.drawLine(p1, p2);
			graphics.fillOval(p2.x - 5, p2.y - 5, 10, 10);
			graphics.drawOval(p2.x - 5, p2.y - 5, 10, 10);
			graphics.drawText(string, p2);
			i++;
		}
	}
}
