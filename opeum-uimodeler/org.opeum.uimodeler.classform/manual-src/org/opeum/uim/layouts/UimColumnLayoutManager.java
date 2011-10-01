package org.opeum.uim.layouts;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.opeum.uim.figures.ColumnFigure;
import org.opeum.uim.figures.UimFieldFigure;

public class UimColumnLayoutManager extends XYLayout implements IUimLayoutManager{
	@Override
	public void layout(IFigure parent){
		List<ColumnFigure> children = (List<ColumnFigure>) parent.getChildren();
		Collection<Rectangle> boundsColl = (Collection<Rectangle>) constraints.values();
		for(ColumnFigure figure:children){
			Rectangle bounds = (Rectangle) constraints.get(figure);
			bounds.y = 0;
			Rectangle constraint = (Rectangle) figure.getLayoutManager().getConstraint(figure.getFirstChild());
			String s = ((UimFieldFigure) figure.getFirstChild()).getLabel().getText();
			int deltaFromCurrentPosition = Math.abs(figure.getBounds().x-constraint.x);
			if(constraint.x > 0 && deltaFromCurrentPosition>children.size()*2){
				bounds.x = constraint.x;
			}
			bounds.width = figure.getMinimumSize().width;
			bounds.height = parent.getSize().height;
		}
		layout(boundsColl, parent.getParent().getSize());
		super.layout(parent);
	}
	private Collection<Rectangle> getSortedBounds(Collection<Rectangle> bounds){
		TreeSet<Rectangle> results = new TreeSet<Rectangle>(new Comparator<Rectangle>(){
			@Override
			public int compare(Rectangle arg0,Rectangle arg1){
				return arg0.x - arg1.x;
			}
		});
		results.addAll(bounds);
		return results;
	}
	@Override
	public void layout(Collection<Rectangle> values,Dimension parentSize){
		Rectangle previous = null;
		int totalWidth=0;
		for(Rectangle current:getSortedBounds(values)){
			if(previous == null){
				current.x = 0;
			}else{
				current.x = previous.x + previous.width;
			}
			totalWidth+=current.width;
			current.y = 0;
			previous = current;
		}
		if(totalWidth!=parentSize.width){
			for(Rectangle rectangle:values){
				rectangle.x=rectangle.x*parentSize.width/totalWidth;
				rectangle.width=rectangle.width*parentSize.width/totalWidth;
			}
		}
	}
}
