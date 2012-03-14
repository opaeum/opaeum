package org.opaeum.uimodeler.common.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
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
	public Composite actionBar;
	{
		;
		addControlListener(new ControlListener(){
			@Override
			public void controlResized(ControlEvent e){
			}
			@Override
			public void controlMoved(ControlEvent e){
			}
		});
		setTable(new Table(this, SWT.CHECK));
		getTable().setHeaderVisible(true);
		getTable().setLinesVisible(true);
		getTable().addListener(SWT.MeasureItem, new Listener(){
			public void handleEvent(Event event){
				// height cannot be per row so simply set
				event.height = 29;
			}
		});
		this.setFirstRow(new Composite(this, SWT.NONE){
			@Override
			public void setData(String key,Object value){
				if(key.equals(OSSupport.WBP_NEED_IMAGE) && Boolean.TRUE.equals(value)){
					getTable().setData(key, value);
				}
				super.setData(key, value);
			}
		});
		this.actionBar = new Composite(this, SWT.BORDER);
		org.eclipse.swt.layout.GridLayout gl = new org.eclipse.swt.layout.GridLayout(30, false);
		this.getFirstRow().setLayout(gl);
		gl.marginHeight = 0;
		gl.marginWidth = 0;
		gl.verticalSpacing = 0;
		gl.horizontalSpacing = 0;
		this.actionBar.setLayout(gl);
		getFirstRow().setData(OSSupport.WBP_NEED_IMAGE, Boolean.TRUE);
		actionBar.setData(OSSupport.WBP_NEED_IMAGE, Boolean.TRUE);
		getTable().setData(OSSupport.WBP_NEED_IMAGE, Boolean.TRUE);
	}
	public UimDataTableComposite(Composite parent,int style){
		super(parent, style);
	}
	public void prepareForPaint(Rectangle bnds){
		int tableWidth = bnds.width - 16;
		int tableHeight = bnds.height - 10;
		org.eclipse.swt.graphics.Rectangle tableBounds = getTable().getBounds();
		int rowHeight = 34;
		if(tableBounds.width != tableWidth && tableBounds.height != tableHeight){
			int compositeWidth = tableWidth;
			int compositeHeight = tableHeight + 68;
			setSize(compositeWidth, compositeHeight);
			getFirstRow().setBounds(0, 0, getSize().x - 4, rowHeight);
			getFirstRow().setBackground(ColorConstants.red);
			getTable().setBounds(0, getFirstRow().getBounds().height, tableWidth, tableHeight-70);
			actionBar.setBackground(ColorConstants.blue);
			getTable().setData(OSSupport.WBP_NEED_IMAGE, Boolean.TRUE);
			for(Control control:getFirstRow().getChildren()){
				control.setData(OSSupport.WBP_NEED_IMAGE, Boolean.TRUE);
			}
			for(Control control:actionBar.getChildren()){
				control.setData(OSSupport.WBP_NEED_IMAGE, Boolean.TRUE);
			}
		}
		actionBar.setBounds(0, tableHeight-24/*VOODOO*/, getSize().x - 4, rowHeight);
		recalculateColumns();
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
	public void setFirstRow(Composite firstRow){
		this.firstRow = firstRow;
	}
	public Table getTable(){
		return table;
	}
	public void setTable(Table table){
		this.table = table;
	}
}