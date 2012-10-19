package org.opaeum.uim.figures;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.opaeum.uim.swt.ColumnComposite;
import org.opaeum.uim.swt.IUimFieldComposite;
import org.opaeum.uimodeler.common.figures.IUimFieldFigure;
import org.opaeum.uimodeler.common.figures.UimDataTableComposite;
import org.opaeum.uimodeler.common.figures.UimFigureUtil;

public class CustomFieldColumnFigure extends RectangleFigure implements IUimFieldFigure{
	private WrappingLabel fColumnNameFigure;
	private ColumnComposite composite;
	private TableColumn column;
	private UimDataTableComposite dataTableComposite;
	public CustomFieldColumnFigure(UimDataTableComposite comp){
		this.dataTableComposite = comp;
		FlowLayout layoutThis = new FlowLayout();
		layoutThis.setStretchMinorAxis(false);
		layoutThis.setMinorAlignment(FlowLayout.ALIGN_CENTER);
		layoutThis.setMajorAlignment(FlowLayout.ALIGN_CENTER);
		layoutThis.setMajorSpacing(5);
		layoutThis.setMinorSpacing(5);
		layoutThis.setHorizontal(true);
		this.setLayoutManager(layoutThis);
		// createContents();
		Table table = comp.getTable();
		column = new TableColumn(table, SWT.LEFT);
		this.composite = new ColumnComposite(comp.getFirstRow(), SWT.NONE);
		getComposite().setData(UimFigureUtil.FIGURE, this);
	}
	private void createContents(){
		fColumnNameFigure = new WrappingLabel();
		fColumnNameFigure.setText("<...>");
		this.add(fColumnNameFigure);
	}
	public WrappingLabel getColumnNameFigure(){
		return fColumnNameFigure;
	}
	public void paint(Graphics graphics){
		Point copy = ((Figure) getParent()).getLocation().getCopy();
		graphics.drawImage((Image) getWidget().getData(UimFigureUtil.OPAEUM_IMAGE), copy.x, copy.y);
	}
	@Override
	public Control getWidget(){
		return composite;
	}
	@Override
	public void setLabelText(String string){
		if(column.getText() == null || !column.getText().equals(string)){
			column.setText(string);
			dataTableComposite.markTableForRepait();
		}
	}
	@Override
	public void setMinimumLabelWidth(Integer minimumLabelWidth){
		// TODO Auto-generated method stub
	}
	@Override
	public IUimFieldComposite getComposite(){
		return composite;
	}
	@Override
	public void setMinimumLabelHeigh(Integer newValue){
	}
}