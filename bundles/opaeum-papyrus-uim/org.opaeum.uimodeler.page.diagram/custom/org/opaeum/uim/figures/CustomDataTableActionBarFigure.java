package org.opaeum.uim.figures;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.gmf.runtime.diagram.ui.figures.ShapeCompartmentFigure;
import org.eclipse.gmf.runtime.draw2d.ui.figures.OneLineBorder;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.IMapMode;
import org.eclipse.swt.widgets.Composite;
import org.opaeum.uim.swt.DataTableActionBarComposite;
import org.opaeum.uim.swt.IUimWidget;
import org.opaeum.uimodeler.common.UimFigureUtil;
import org.opaeum.uimodeler.common.figures.ISWTFigure;
import org.opaeum.uimodeler.common.figures.UimDataTableComposite;

public  class CustomDataTableActionBarFigure extends ShapeCompartmentFigure implements ISWTFigure{
	private DataTableActionBarComposite widget;
	public CustomDataTableActionBarFigure(String title,IMapMode mm, Composite composite){
		super(title, mm);
		UimDataTableComposite parent=(UimDataTableComposite) composite;
		this.widget=parent.getActionBar();
		remove(getTextPane());
		remove(scrollPane);
		widget.setData(UimFigureUtil.FIGURE, this);
		setLayoutManager(new StackLayout());
		add(scrollPane);
		setBorder(new OneLineBorder(mm.DPtoLP(1), PositionConstants.TOP));
		setOpaque(false);
	}
	@Override
	protected void layout(){
		setBackgroundColor(getParent().getBackgroundColor());
		super.layout();
	}
	@Override
	public IUimWidget getWidget(){
		return widget;
	}
	@Override
	public void setLabelText(String string){
	}
	@Override
	protected void paintClientArea(Graphics graphics){
		super.paintClientArea(graphics);
	}
}