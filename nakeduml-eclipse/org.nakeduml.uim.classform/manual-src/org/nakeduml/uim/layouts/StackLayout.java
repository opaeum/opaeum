package org.nakeduml.uim.layouts;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;

public class StackLayout extends XYLayout {
	public int activeChild;

	public void layout(IFigure figure) {
		Rectangle r = figure.getClientArea();
		List children = figure.getChildren();
		IFigure child;
		for (int i = 0; i < children.size(); i++) {
			child = (IFigure) children.get(i);
			if (i == activeChild) {
				child.setBounds(r);
			} else {
				child.setBounds(new Rectangle(0, 0, 0, 0));
			}
		}
	}
}
