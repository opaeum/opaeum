package org.opaeum.uimodeler.common.figures;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.wb.os.OSSupport;
import org.opaeum.uim.swt.TableAndActionBarComposite;

public final class UimDataTableComposite extends Composite{
	private Composite firstRow;
	private TableAndActionBarComposite displayedContent;
	public static final int ROW_HEIGHT = 34;
	public UimDataTableComposite(Composite parent,int style){
		super(parent, style);
		setLayout(prepareLayout(1));
		addFirstRow();
		addDisplayedContent();
	}
	private void addDisplayedContent(){
		displayedContent = new TableAndActionBarComposite(this, SWT.NONE);
		displayedContent.setLayout(prepareLayout(1));
		displayedContent.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
	}
	private GridLayout prepareLayout(int columns){
		GridLayout gl = new GridLayout(columns, false);
		gl.marginHeight = 0;
		gl.marginWidth = 0;
		gl.verticalSpacing = 0;
		gl.horizontalSpacing = 0;
		return gl;
	}
	@Override
	public void layout(){
		super.layout();
		getFirstRow().layout();
		getDisplayedContent().layout();
		recalculateColumns();
		getTable().layout();
		getActionBar().layout();
	}
	private void addFirstRow(){
		this.firstRow = new Composite(this, SWT.NONE);
		GridData firstRowData = new GridData(10, ROW_HEIGHT);
		firstRowData.grabExcessHorizontalSpace = true;
		firstRowData.horizontalAlignment = GridData.FILL;
		firstRow.setLayoutData(firstRowData);
		firstRow.setLayout(prepareLayout(30));
	}
	public Composite getFirstRow(){
		return firstRow;
	}
	public Table getTable(){
		return displayedContent.getTable();
	}
	public Composite getDisplayedContent(){
		return displayedContent;
	}
	public Composite getActionBar(){
		return displayedContent.getActionBar();
	}
	@Override
	public boolean print(GC gc){
		return super.print(gc);
	}
	private void recalculateColumns(){
		int i = 0;
		TableColumn[] columns = getTable().getColumns();
		getTable().setItemCount(5);
		getTable().getItems()[0].setText(new String[columns.length]);
		getTable().getItems()[1].setText(new String[columns.length]);
		getTable().getItems()[2].setText(new String[columns.length]);
		getTable().getItems()[3].setText(new String[columns.length]);
		getTable().getItems()[4].setText(new String[columns.length]);
		for(Control control:getFirstRow().getChildren()){
			getTable().getItems()[0].setText(i, columns[i].getText() + "1");
			getTable().getItems()[1].setText(i, columns[i].getText() + "2");
			getTable().getItems()[2].setText(i, columns[i].getText() + "3");
			getTable().getItems()[3].setText(i, columns[i].getText() + "4");
			getTable().getItems()[4].setText(i, columns[i].getText() + "5");
			if(columns[i].getWidth() != control.getSize().x){
				columns[i].setWidth(control.getSize().x);
				markTableForRepait();
			}
			i++;
		}
	}
	public void markTableForRepait(){
		getTable().setData(OSSupport.WBP_NEED_IMAGE, Boolean.TRUE);
	}
}