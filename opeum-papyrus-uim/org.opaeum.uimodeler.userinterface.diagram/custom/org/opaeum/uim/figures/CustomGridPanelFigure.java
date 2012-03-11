package org.opaeum.uim.figures;

import java.util.Iterator;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

/**
 * @generated NOT
 */
public class CustomGridPanelFigure extends AbstractPanelFigure implements ISWTFigure{
	/**
	 * @generated NOT
	 */
	public CustomGridPanelFigure(Composite parent,Dimension cornerDimensions){
		super(parent,cornerDimensions);
	}
	@Override
	protected int getColumnCount(){
		return 2;
	}
	@Override
	public void markForRepaint(){
		// TODO Auto-generated method stub
		
	}
}