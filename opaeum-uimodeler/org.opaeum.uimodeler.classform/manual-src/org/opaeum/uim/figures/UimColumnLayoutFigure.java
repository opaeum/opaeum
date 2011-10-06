package org.opaeum.uim.figures;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.SimpleLoweredBorder;
import org.eclipse.draw2d.SimpleRaisedBorder;
import org.eclipse.draw2d.geometry.Rectangle;
import org.opaeum.uim.layouts.UimColumnLayoutManager;


public class UimColumnLayoutFigure extends AbstractLayoutFigure{

	private Map<IFigure,Figure> columnMap = new HashMap<IFigure,Figure>();
	public UimColumnLayoutFigure(){
		super();
		setLayoutManager(new UimColumnLayoutManager());

	}

	@Override
	public void add(IFigure figure,Object constraint,int index){
		Figure column = new ColumnFigure();
		//MOve to 
		column.setOpaque(false);
		column.setLayoutManager(new org.eclipse.draw2d.XYLayout());
		SimpleLoweredBorder border2 = new SimpleLoweredBorder(5);
		column.setBorder(border2);
		if(constraint==null){
			constraint=new Rectangle(getChildren().size(),0,100,100);
		}
		if(figure instanceof UimFieldFigure){
			UimFieldFigure fieldFigure = (UimFieldFigure) figure;
			fieldFigure.setVertical(true);
			fieldFigure.getLabel().setBorder(new SimpleRaisedBorder(5));
			fieldFigure.getLabel().setColorSelectedLabel(ColorConstants.buttonDarker);
			fieldFigure.getLabel().setColorUnselectedLabel(ColorConstants.button);
			fieldFigure.getLabel().setSelected(false);
			
		}
		super.add(column, constraint, index);
		column.add(figure,new Rectangle());
		columnMap.put(figure,column);
	}

	@Override
	public void remove(IFigure figure){
		
		Figure column = columnMap.get(figure);
		column.remove(figure);
		super.remove(column);
		columnMap.remove(figure);
	}
	
}
