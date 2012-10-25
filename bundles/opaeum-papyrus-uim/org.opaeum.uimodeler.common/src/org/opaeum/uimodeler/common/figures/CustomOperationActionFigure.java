package org.opaeum.uimodeler.common.figures;

import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.swt.widgets.Composite;

public class CustomOperationActionFigure extends CustomUimActionFigure implements IBuiltInActionFigure{
	private WrappingLabel fFigureBuiltInActionNameFigure;
	public CustomOperationActionFigure(Composite parent){
		super(parent);
	}
	@Override
	protected void createContents(){
		fFigureBuiltInActionNameFigure = new WrappingLabel();
		fFigureBuiltInActionNameFigure.setText("<...>");
		this.add(fFigureBuiltInActionNameFigure);
	}
	public WrappingLabel getFigureInvocationButtonNameFigure(){
		return fFigureBuiltInActionNameFigure;
	}
}