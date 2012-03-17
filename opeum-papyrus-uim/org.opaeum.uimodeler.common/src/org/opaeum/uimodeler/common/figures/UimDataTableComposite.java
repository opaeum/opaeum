package org.opaeum.uimodeler.common.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.wb.os.OSSupport;

public final class UimDataTableComposite extends Composite{
	private Table table;
	private Composite firstRow;
	private Composite actionBar;
	private Composite displayedContent;
	public static final int ROW_HEIGHT = 34;
	public UimDataTableComposite(Composite parent,int style){
		super(parent, style);
		setLayout(prepareLayout(1));
		addFirstRow();
		addDisplayedContent();
		// layout();
	}
	private void addDisplayedContent(){
		displayedContent = new Composite(this, SWT.NONE);
		displayedContent.setLayout(prepareLayout(1));
		displayedContent.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
		this.table = new Table(displayedContent, SWT.CHECK);
		getTable().setHeaderVisible(true);
		getTable().setLinesVisible(true);
		getTable().addListener(SWT.MeasureItem, new Listener(){
			public void handleEvent(Event event){
				// height cannot be per row so simply set
				event.height = 29;
			}
		});
		table.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
		this.actionBar = new Composite(displayedContent, SWT.BORDER);
		this.actionBar.setLayout(prepareLayout(30));
		actionBar.setData(OSSupport.WBP_NEED_IMAGE, Boolean.TRUE);
		actionBar.setLayoutData(new GridData(GridData.FILL,GridData.CENTER,true,false));
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
//					getTable().setData(key, value);
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
//		System.out.println("UimDataTableComposite.prepareForPaint()");
//		int tableWidth = bnds.width;
//		int tableHeight = bnds.height;
//		org.eclipse.swt.graphics.Rectangle tableBounds = getTable().getBounds();
//		if(tableBounds.width != tableWidth && tableBounds.height != tableHeight){
//			getFirstRow().setBackground(ColorConstants.red);
//			actionBar.setBackground(ColorConstants.blue);
//			getTable().setData(OSSupport.WBP_NEED_IMAGE, Boolean.TRUE);
//			for(Control control:getFirstRow().getChildren()){
//				control.setData(OSSupport.WBP_NEED_IMAGE, Boolean.TRUE);
//			}
//			for(Control control:actionBar.getChildren()){
//				control.setData(OSSupport.WBP_NEED_IMAGE, Boolean.TRUE);
//			}
//		}
//		recalculateColumns();
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
		actionBar.layout();
	}
	public Composite getFirstRow(){
		return firstRow;
	}
	public Table getTable(){
		return table;
	}
	public Composite getDisplayedContent(){
		return displayedContent;
	}
	public Composite getActionBar(){
		return actionBar;
	}
}