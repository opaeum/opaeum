package org.opaeum.uim.figures;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.opaeum.uimodeler.common.figures.CustomUimActionFigure;
import org.opaeum.uimodeler.common.figures.UimDataTableComposite;

public abstract class CustomActionColumnFigure extends CustomUimActionFigure{
	private UimDataTableComposite dataTableComposite;
	public CustomActionColumnFigure(UimDataTableComposite comp){
		super(comp.getFirstRow());
		this.dataTableComposite = comp;
		Table table = dataTableComposite.getTable();
		column = new TableColumn(table, SWT.LEFT);
		comp.markForShot();
	}
	private TableColumn column;
	@Override
	public void setLabelText(String string){
		String string2 = string == null || string.length() == 0 ? "NewAction" : string;
		if(column.getText() == null || !column.getText().equals(string2)){
			column.setText(string2);
			super.setLabelText(string2);
			dataTableComposite.markForShot();
		}
	}
	@Override
	protected void createContents(){
	}
}
