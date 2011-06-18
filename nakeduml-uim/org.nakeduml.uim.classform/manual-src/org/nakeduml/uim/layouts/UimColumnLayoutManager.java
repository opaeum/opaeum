package org.nakeduml.uim.layouts;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

public class UimColumnLayoutManager extends XYLayout implements IUimLayoutManager{
	@Override
	public void layout(IFigure parent){
		List<IFigure> children = (List<IFigure>) parent.getChildren();
		Collection<Rectangle> boundsColl = (Collection<Rectangle>) constraints.values();
		layout(boundsColl,parent.getSize());
		for(IFigure figure:children){
			Rectangle bounds = (Rectangle) constraints.get(figure);
			bounds.y = 0;
			bounds.height = parent.getSize().height - 10;
		}
		super.layout(parent);
	}
	private Collection<Rectangle> getSortedBounds(Collection<Rectangle> bounds){
		TreeSet<Rectangle> results = new TreeSet<Rectangle>(new Comparator<Rectangle>(){
			@Override
			public int compare(Rectangle arg0,Rectangle arg1){
				// TODO Auto-generated method stub
				return arg0.x - arg1.x;
			}
		});
		results.addAll(bounds);
		return results;
	}
	@Override
	public void layout(Collection<Rectangle> values,Dimension parentSize){
		Rectangle previous = null;
		for(Rectangle current:getSortedBounds(values)){
			if(previous == null){
				current.x = 0;
			}else{
				current.x = previous.x + previous.width;
			}
			current.y=0;
			previous = current;
		}
	}
}
