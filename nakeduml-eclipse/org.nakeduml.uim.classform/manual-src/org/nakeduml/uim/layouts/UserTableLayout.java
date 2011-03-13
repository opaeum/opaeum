package org.nakeduml.uim.layouts;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;

public class UserTableLayout extends XYLayout {

	@Override
	public void layout(IFigure parent) {
		List<IFigure> children = (List<IFigure>)parent.getChildren();
        Rectangle previous=null;
        for(Rectangle current:getSortedBounds()){
        	if(previous==null){
        		current.x=0;
        	}else{
        		current.x=previous.x+previous.width;
        	}
        	previous=current;
        }
		for(IFigure figure:children){
            Rectangle bounds = (Rectangle) constraints.get(figure);
            bounds.y=0;      
            bounds.height=parent.getSize().height-10;
            
		}
		super.layout(parent);
	}

	private Collection<Rectangle> getSortedBounds() {
		TreeSet<Rectangle> results = new TreeSet<Rectangle>(new Comparator<Rectangle>() {

			@Override
			public int compare(Rectangle arg0, Rectangle arg1) {
				// TODO Auto-generated method stub
				return arg0.x-arg1.x;
			}
		});
		results.addAll((Collection<Rectangle>)constraints.values());
		return results;
	}
	

}
