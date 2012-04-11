package org.opaeum.uim.figures;

import org.eclipse.draw2d.Graphics;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.wb.os.OSSupport;
import org.opaeum.uimodeler.common.figures.CustomBuiltInLinkFigure;
import org.opaeum.uimodeler.common.figures.UimDataTableComposite;

public class CustomBuiltInLinkColumnFigure extends CustomBuiltInLinkFigure{
	private UimDataTableComposite dataTableComposite;
	private TableColumn column;
	public CustomBuiltInLinkColumnFigure(UimDataTableComposite comp){
		super(comp.getFirstRow());
		this.dataTableComposite = comp;
		Table table = dataTableComposite.getTable();
		column = new TableColumn(table, SWT.LEFT);
		dataTableComposite.markTableForRepait();
	}
	public void paint(Graphics graphics){
		super.paint(graphics);
	}
	@Override
	public void setLabelText(String string){
		String newVal = string == null || string.length() == 0 ? "NewLink" : string;
		if(column.getText() == null || !column.getText().equals(string)){
			column.setText(newVal);
			dataTableComposite.markTableForRepait();
			super.setLabelText(string);
		}
	}
}
