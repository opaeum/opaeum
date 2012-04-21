package org.opaeum.uimodeler.cubequery.diagram.edit.parts;

import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.layout.FreeFormLayoutEx;
import org.eclipse.swt.widgets.Display;
import org.opaeum.uimodeler.cubequery.diagram.ColumnAxisFigure;
import org.opaeum.uimodeler.cubequery.diagram.MeasurePropertyFigure;
import org.opaeum.uimodeler.cubequery.diagram.RowAxisFigure;

final class CubeLayout extends FreeFormLayoutEx{
	private final IFigure result;
	CubeLayout(IFigure result){
		this.result = result;
	}
	private int calculateTotalHeightOfColumnHeadings(IFigure parent){
		int result = 0;
		List<Figure> ch = parent.getChildren();
		for(Figure object:ch){
			if(object instanceof ColumnAxisFigure){
				result += object.getSize().height;
			}
		}
		return result;
	}
	private int calculateTotalWidthOfRowHeadings(IFigure parent){
		int result = 0;
		List<Figure> ch = parent.getChildren();
		for(Figure object:ch){
			if(object instanceof RowAxisFigure){
				result += object.getSize().width;
			}
		}
		return result;
	}
	@Override
	public void layout(IFigure parent){
		super.layout(parent);
		List<Figure> children2 = parent.getChildren();
		int columnLevelCount = 0;
		int rowLevelCount = 0;
		int measureCount = 0;
		int currentX = 0;
		int totalWidthOfRowHeadings = calculateTotalWidthOfRowHeadings(parent);
		int totalHeightOfColumnHeadings = calculateTotalHeightOfColumnHeadings(parent);
		int width = calcDisplayWidth(totalHeightOfColumnHeadings);
		int height = Display.getCurrent().getBounds().height - totalHeightOfColumnHeadings - 360;
		for(Figure object:children2){
			if(object instanceof ColumnAxisFigure){
				ColumnAxisFigure caf = (ColumnAxisFigure) object;
				caf.getColumnAxisEntryFigure().setExp(columnLevelCount);
				object.setMinimumSize(new Dimension(0, 0));
				object.setBounds(new Rectangle(totalWidthOfRowHeadings + 10, columnLevelCount * 30 + 5, width, object.getSize().height));
				columnLevelCount += Math.max(1, caf.getColumnAxisEntryFigure().getLabels().size());
			}else if(object instanceof RowAxisFigure){
				RowAxisFigure raf = (RowAxisFigure) object;
				raf.getRowAxisEntryFigure().setExp(rowLevelCount);
				Rectangle bounds = object.getBounds();
				object.setBounds(new Rectangle(currentX + 10, totalHeightOfColumnHeadings + 5, bounds.width, height));
				object.setMinimumSize(new Dimension(0, 0));// for some reason we need this
				rowLevelCount += Math.max(1, raf.getRowAxisEntryFigure().getLabels().size());
				currentX += bounds.width;
			}
		}
		int cellWidth = calculateCellWIdth(children2, columnLevelCount, width);
		for(Figure figure:children2){
			if(figure instanceof MeasurePropertyFigure){
				figure.setBounds(new Rectangle(totalWidthOfRowHeadings + 10 + (cellWidth * measureCount), totalHeightOfColumnHeadings + 5,
						cellWidth, height));
				measureCount++;
			}
		}
	}
	public int calculateCellWIdth(List<Figure> children2,int columnLevelCount,int width){
		int totalMeasureCount = 0;
		for(Figure object:children2){
			if(object instanceof MeasurePropertyFigure){
				totalMeasureCount++;
			}
		}
		int columnCount = (int) Math.round(Math.pow(2, columnLevelCount-1));
		int cellWidth = (width / columnCount) / Math.max(1, totalMeasureCount);
		return cellWidth;
	}
	public int calcDisplayWidth(int totalHeightOfColumnHeadings){
		int width = Display.getCurrent().getBounds().width - totalHeightOfColumnHeadings - 720;
		return width;
	}
}