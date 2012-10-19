package org.opaeum.uim.figures;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.wb.os.OSSupport;
import org.opaeum.uimodeler.common.figures.CustomUimActionFigure;
import org.opaeum.uimodeler.common.figures.UimDataTableComposite;
import org.opaeum.uimodeler.common.figures.UimFigureUtil;

public abstract class CustomActionColumnFigure extends CustomUimActionFigure{
	private UimDataTableComposite dataTableComposite;
	public CustomActionColumnFigure(UimDataTableComposite comp){
		super(comp.getFirstRow());
		this.dataTableComposite = comp;
		Table table = dataTableComposite.getTable();
		column = new TableColumn(table, SWT.LEFT);
		table.setData(OSSupport.WBP_NEED_IMAGE, Boolean.TRUE);
	}
	private TableColumn column;
	public void paint(Graphics graphics){
		Point copy = ((Figure) getParent()).getLocation().getCopy();
		graphics.drawImage((Image) getWidget().getData(UimFigureUtil.OPAEUM_IMAGE), copy.x + 1, copy.y);
	}
	@Override
	public void setLabelText(String string){
		String string2 = string == null || string.length() == 0 ? "NewAction" : string;
		if(column.getText() == null || !column.getText().equals(string2)){
			column.setText(string2);
			super.setLabelText(string2);
			dataTableComposite.markTableForRepait();
		}
	}
	@Override
	protected void createContents(){
	}
}
