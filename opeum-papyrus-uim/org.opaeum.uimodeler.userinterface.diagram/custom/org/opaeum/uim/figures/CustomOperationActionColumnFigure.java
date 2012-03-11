package org.opaeum.uim.figures;

import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.swt.widgets.Composite;

public class CustomOperationActionColumnFigure extends CustomActionColumnFigure{
	private WrappingLabel fFigureBuiltInActionNameFigure;
	public CustomOperationActionColumnFigure(Composite parent){
		super(parent);
	}
	@Override
	protected void createContents(){
		fFigureBuiltInActionNameFigure = new WrappingLabel();
		fFigureBuiltInActionNameFigure.setText("<...>");
		this.add(fFigureBuiltInActionNameFigure);
	}
	public WrappingLabel getFigureBuiltInActionColumnNameFigure(){
		return fFigureBuiltInActionNameFigure;
	}
}
