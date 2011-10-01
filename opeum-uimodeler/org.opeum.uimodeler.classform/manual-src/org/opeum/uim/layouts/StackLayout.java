package org.opeum.uim.layouts;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;

public class StackLayout extends XYLayout{
	public int activeChild;
	@SuppressWarnings("unchecked")
	public void layout(IFigure figure){
		Rectangle r = figure.getClientArea();
		List<IFigure> children = figure.getChildren();
		IFigure child;
		for(int i = 0;i < children.size();i++){
			child = children.get(i);
			if(i == activeChild){
				child.setVisible(true);
				child.setBounds(r);
			}else{
				child.setVisible(false);
				child.setBounds(new Rectangle(r.x, r.y, 0, 0));
			}
		}
	}
}
