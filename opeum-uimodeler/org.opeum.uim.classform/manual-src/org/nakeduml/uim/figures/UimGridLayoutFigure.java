package org.opeum.uim.figures;

import org.opeum.uim.layouts.GridLayout;

/**
 * The figure to display a User Interaction Diagram.
 * 
 * @generated NOT
 */
public class UimGridLayoutFigure extends AbstractLayoutFigure{
	private GridLayout gridLayout;
	public UimGridLayoutFigure(){
		super();
		gridLayout = new GridLayout(2);
		getContentPane().setLayoutManager(gridLayout);
	}
	public void setNoOfColumns(int i){
		i = Math.max(1, i);
		if(i != gridLayout.numColumns){
			gridLayout.numColumns = i;
			getContentPane().invalidate();
			invalidateTree();
		}
	}
}
