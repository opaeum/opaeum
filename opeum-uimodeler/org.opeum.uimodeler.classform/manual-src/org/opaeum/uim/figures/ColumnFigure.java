package org.opeum.uim.figures;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

public final class ColumnFigure extends Figure{
	@Override
	protected void layout(){
		IFigure firstChild = getFirstChild();
		Rectangle constraintInParent = (Rectangle) getParent().getLayoutManager().getConstraint(this);
		Rectangle constraint = (Rectangle) getLayoutManager().getConstraint(firstChild);
		constraint.x=0;
		constraint.y=0;
		constraint.height=80;
		constraint.width=constraintInParent.width;
		super.layout();
	}

	@Override
	public Dimension getMinimumSize(int width,int height){
		Dimension minimumSize = super.getMinimumSize(width,height);
		if(getFirstChild() instanceof UimFieldFigure){
			minimumSize.width=((UimFieldFigure)getFirstChild()).getLabelWidth()+2;
		}
		return minimumSize;
	}
	public IFigure getFirstChild(){
		return (IFigure)getChildren().get(0);
	}
}