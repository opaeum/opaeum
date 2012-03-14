package org.opaeum.uim.figures;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.widgets.Composite;
import org.opaeum.uimodeler.common.figures.AbstractPanelFigure;
import org.opaeum.uimodeler.common.figures.ISWTFigure;

/**
 * @generated NOT
 */
public class CustomTableActionBarFigure extends AbstractPanelFigure implements ISWTFigure{
	/**
	 * @generated NOT
	 */
	public CustomTableActionBarFigure(Composite parent,Dimension cornerDimensions){
		super(parent,cornerDimensions);
	}
	@Override
	protected int getColumnCount(){
		return 20;
	}
	@Override
	public void markForRepaint(){
		// TODO Auto-generated method stub
		
	}
}