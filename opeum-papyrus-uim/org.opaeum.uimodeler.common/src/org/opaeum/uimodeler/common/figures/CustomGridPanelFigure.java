package org.opaeum.uimodeler.common.figures;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.widgets.Composite;

public class CustomGridPanelFigure extends AbstractPanelFigure implements ISWTFigure{
	public CustomGridPanelFigure(Composite parent,Dimension cornerDimensions){
		super(parent,cornerDimensions);
	}
	@Override
	protected int getColumnCount(){
		return 2;
	}
	@Override
	public void markForRepaint(){
		
	}
}