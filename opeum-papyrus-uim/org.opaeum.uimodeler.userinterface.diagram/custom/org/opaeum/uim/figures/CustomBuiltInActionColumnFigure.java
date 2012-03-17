package org.opaeum.uim.figures;

import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.swt.widgets.Composite;
import org.opaeum.uimodeler.common.figures.UimDataTableComposite;

public class CustomBuiltInActionColumnFigure extends CustomActionColumnFigure{
	private WrappingLabel fFigureBuiltInActionNameFigure;
	public CustomBuiltInActionColumnFigure(UimDataTableComposite parent){
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
