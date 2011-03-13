package org.nakeduml.uim.layouts;

import java.util.Collection;
import java.util.Comparator;
import java.util.TreeSet;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;

public class GridLayout extends XYLayout {

	public int numColumns;

	public GridLayout(int i) {
		numColumns = i;
	}

	@Override
	public void layout(IFigure parent) {
		Rectangle previous = null;
		int count = 0;
		int[] width = new int[numColumns];
		int rows = constraints.size() / numColumns;
		rows = constraints.size() % numColumns > 0 ? ++rows : rows;// Extra row
		int[] height = new int[rows];
		for (Rectangle current : getChildrenBounds(parent)) {
			if (width[count % numColumns] < current.width) {
				width[count % numColumns] = current.width;
			}
			if (height[count / numColumns] < current.height) {
				height[count / numColumns] = current.height;
			}
			count++;
		}
		count = 0;
		int currentY = 0;
		for (Rectangle current : getChildrenBounds(parent)) {
			if (count % numColumns == 0) {
				current.x = 0;
			} else {
				current.x = previous.x + width[(count % numColumns) - 1];
			}
			
			current.y = currentY;
			if (count % numColumns == numColumns - 1) {
				currentY = currentY + height[count / numColumns];
			}
			if(current.x+current.width>parent.getSize().width){
				current.width=parent.getSize().width-current.x-2;
			}
			if(current.y+current.height>parent.getSize().height){
				current.height=parent.getSize().height-current.y-2;
			}
			count++;
			previous = current;
		}
		super.layout(parent);
	}

	private Collection<Rectangle> getChildrenBounds(IFigure parent) {
		TreeSet<Rectangle> results = new TreeSet<Rectangle>(
				new Comparator<Rectangle>() {

					@Override
					public int compare(Rectangle arg0, Rectangle arg1) {
						return (arg0.x + (arg0.y * 100))
								- (arg1.x + (arg1.y * 100));
					}
				});
		results.addAll(super.constraints.values());
		return results;
		// List<Rectangle> bounds = new ArrayList<Rectangle>();
		// for(IFigure f:(List<IFigure>) parent.getChildren()){
		// bounds.add((Rectangle) getConstraint(f));
		// }
		// return bounds;

	}

}
