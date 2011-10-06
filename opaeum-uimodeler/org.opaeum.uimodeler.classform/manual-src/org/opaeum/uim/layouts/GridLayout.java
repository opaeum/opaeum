package org.opaeum.uim.layouts;

import java.util.Collection;
import java.util.Comparator;
import java.util.TreeSet;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

public class GridLayout extends XYLayout implements IUimLayoutManager{
	private static final int PADDING = 2;
	public int numColumns;
	public GridLayout(int i){
		numColumns = i;
	}
	@Override
	public void layout(IFigure parent){
		Collection<Rectangle> childrenBounds = getChildrenBounds(parent);
		Dimension parentSize = parent.getSize();
		layout(childrenBounds, parentSize);
		super.layout(parent);
	}
	public void layout(Collection<Rectangle> childrenBounds,Dimension parentSize){
		Rectangle previous = null;
		int count = 0;
		int[] width = new int[numColumns];
		int rows = childrenBounds.size() / numColumns;
		rows = childrenBounds.size() % numColumns > 0 ? ++rows : rows;// Extra row
		int[] height = new int[rows];
		for(Rectangle current:childrenBounds){
			if(width[count % numColumns] < current.width){
				width[count % numColumns] = current.width;
			}
			if(height[count / numColumns] < current.height){
				height[count / numColumns] = current.height;
			}
			count++;
		}
		if(parentSize.width > 0 && parentSize.height > 0){
			// Tab panels set size to 0
			adjustSize(width, parentSize.width);
			adjustSize(height, parentSize.height);
		}
		count = 0;
		int currentY = PADDING;
		for(Rectangle current:childrenBounds){
			if(count % numColumns == 0){
				current.x = 2;
			}else{
				current.x = previous.x + width[(count % numColumns) - 1] + PADDING;
			}
			current.width = width[count % numColumns];
			current.y = currentY;
			current.height = height[count / numColumns];
			if(count % numColumns == numColumns - 1){
				currentY = currentY + height[count / numColumns] + PADDING;
			}
			count++;
			previous = current;
		}
	}
	public void adjustSize(int[] width,int width2){
		int totalWidth = 0;
		for(int i:width){
			totalWidth += i;
			totalWidth += PADDING;
		}
		float ratio = width2 / (float) totalWidth;
		if(ratio > 0.8 && ratio < 1.2){
			for(int i = 0;i < width.length;i++){
				width[i] = Math.round(width[i] * ratio);
			}
		}
	}
	@SuppressWarnings("unchecked")
	private Collection<Rectangle> getChildrenBounds(IFigure parent){
		TreeSet<Rectangle> results = new TreeSet<Rectangle>(new Comparator<Rectangle>(){
			@Override
			public int compare(Rectangle arg0,Rectangle arg1){
				return (arg0.x + (arg0.y * 100)) - (arg1.x + (arg1.y * 100));
			}
		});
		results.addAll(super.constraints.values());
		return results;
	}
}
