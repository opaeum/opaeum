package org.opaeum.uimodeler.common.figures;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
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
		getActionBar().setData(OSSupport.WBP_NEED_IMAGE, Boolean.TRUE);
		getTable().setData(OSSupport.WBP_NEED_IMAGE, Boolean.TRUE);
	}
	private GridLayout prepareLayout(int columns){
		GridLayout gl = new GridLayout(columns, false);
		gl.marginHeight = 0;
		gl.marginWidth = 0;
		gl.verticalSpacing = 0;
		gl.horizontalSpacing = 0;
		return gl;
	}
	private void addFirstRow(){
		this.firstRow = new Composite(this, SWT.NONE){
			@Override
			public void setData(String key,Object value){
				if(key.equals(OSSupport.WBP_NEED_IMAGE) && Boolean.TRUE.equals(value)){
					// getTable().setData(key, value);
				}
				super.setData(key, value);
			}
		};
		GridData firstRowData = new GridData(10, ROW_HEIGHT);
		firstRowData.grabExcessHorizontalSpace = true;
		firstRowData.horizontalAlignment = GridData.FILL;
		firstRow.setLayoutData(firstRowData);
		firstRow.setLayout(prepareLayout(30));
		firstRow.setData(OSSupport.WBP_NEED_IMAGE, Boolean.TRUE);
	}
	public void prepareForPaint(Rectangle bnds){
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
	public void recalculateColumns(){
		int i = 0;
		TableColumn[] columns = getTable().getColumns();
		getTable().setItemCount(5);
		getTable().getItems()[0].setText(new String[columns.length]);
		getTable().getItems()[1].setText(new String[columns.length]);
		getTable().getItems()[2].setText(new String[columns.length]);
		getTable().getItems()[3].setText(new String[columns.length]);
		getTable().getItems()[4].setText(new String[columns.length]);
		getActionBar().layout();
		if(getTable().getData(OSSupport.WBP_NEED_IMAGE) != null){
			getFirstRow().layout();
			for(Control control:getFirstRow().getChildren()){
				getTable().getItems()[0].setText(i, columns[i].getText() + "1");
				getTable().getItems()[1].setText(i, columns[i].getText() + "2");
				getTable().getItems()[2].setText(i, columns[i].getText() + "3");
				getTable().getItems()[3].setText(i, columns[i].getText() + "4");
				getTable().getItems()[4].setText(i, columns[i].getText() + "5");
				columns[i++].setWidth(control.getSize().x);
			}
			getTable().layout();
		}
	}
}