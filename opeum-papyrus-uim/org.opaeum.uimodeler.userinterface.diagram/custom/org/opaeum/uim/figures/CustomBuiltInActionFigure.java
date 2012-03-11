package org.opaeum.uim.figures;

import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.swt.widgets.Composite;

public class CustomBuiltInActionFigure extends CustomUimActionFigure implements IBuiltInActionFigure{
	private WrappingLabel fFigureBuiltInActionNameFigure;
	public CustomBuiltInActionFigure(Composite parent){
		super(parent);
	}
	@Override
	protected void createContents(){
		fFigureBuiltInActionNameFigure = new WrappingLabel();
		fFigureBuiltInActionNameFigure.setText("<...>");
		this.add(fFigureBuiltInActionNameFigure);
	}
	public WrappingLabel getFigureBuiltInActionNameFigure(){
		return fFigureBuiltInActionNameFigure;
	}
	
}