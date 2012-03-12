package org.opaeum.uim.figures;

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
	Table table;
	Composite firstRow;
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
		table = new Table(this, SWT.CHECK);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.addListener(SWT.MeasureItem, new Listener(){
			public void handleEvent(Event event){
				// height cannot be per row so simply set
				event.height = 29;
			}
		});
		this.firstRow = new Composite(this, SWT.NONE){
			@Override
			public void setData(String key,Object value){
				if(key.equals(OSSupport.WBP_NEED_IMAGE) && Boolean.TRUE.equals(value)){
					table.setData(key, value);
				}
				super.setData(key, value);
			}
		};
		this.actionBar = new Composite(this, SWT.BORDER);
		org.eclipse.swt.layout.GridLayout gl = new org.eclipse.swt.layout.GridLayout(30, false);
		this.firstRow.setLayout(gl);
		gl.marginHeight = 0;
		gl.marginWidth = 0;
		gl.verticalSpacing = 0;
		gl.horizontalSpacing = 0;
		this.actionBar.setLayout(gl);
		firstRow.setData(OSSupport.WBP_NEED_IMAGE, Boolean.TRUE);
		actionBar.setData(OSSupport.WBP_NEED_IMAGE, Boolean.TRUE);
		table.setData(OSSupport.WBP_NEED_IMAGE, Boolean.TRUE);
	}
	UimDataTableComposite(Composite parent,int style){
		super(parent, style);
	}
	protected void prepareForPaint(Rectangle bnds){
		int tableWidth = bnds.width - 16;
		int tableHeight = bnds.height - 10;
		org.eclipse.swt.graphics.Rectangle tableBounds = table.getBounds();
		int rowHeight = 34;
		if(tableBounds.width != tableWidth && tableBounds.height != tableHeight){
			int compositeWidth = tableWidth;
			int compositeHeight = tableHeight + 68;
			setSize(compositeWidth, compositeHeight);
			firstRow.setBounds(0, 0, getSize().x - 4, rowHeight);
			firstRow.setBackground(ColorConstants.red);
			table.setBounds(0, firstRow.getBounds().height, tableWidth, tableHeight-70);
			actionBar.setBackground(ColorConstants.blue);
			table.setData(OSSupport.WBP_NEED_IMAGE, Boolean.TRUE);
			for(Control control:firstRow.getChildren()){
				control.setData(OSSupport.WBP_NEED_IMAGE, Boolean.TRUE);
			}
			for(Control control:actionBar.getChildren()){
				control.setData(OSSupport.WBP_NEED_IMAGE, Boolean.TRUE);
			}
		}
		actionBar.setBounds(0, tableHeight-24/*VOODOO*/, getSize().x - 4, rowHeight);
		recalculateColumns();
	}
	protected void recalculateColumns(){
		int i = 0;
		TableColumn[] columns = table.getColumns();
		table.setItemCount(5);
		table.getItems()[0].setText(new String[columns.length]);
		table.getItems()[1].setText(new String[columns.length]);
		table.getItems()[2].setText(new String[columns.length]);
		table.getItems()[3].setText(new String[columns.length]);
		table.getItems()[4].setText(new String[columns.length]);
		if(table.getData(OSSupport.WBP_NEED_IMAGE) != null){
			firstRow.layout();
			for(Control control:firstRow.getChildren()){
				table.getItems()[0].setText(i, columns[i].getText() + "1");
				table.getItems()[1].setText(i, columns[i].getText() + "2");
				table.getItems()[2].setText(i, columns[i].getText() + "3");
				table.getItems()[3].setText(i, columns[i].getText() + "4");
				table.getItems()[4].setText(i, columns[i].getText() + "5");
				columns[i++].setWidth(control.getSize().x);
			}
			table.layout();
		}
		actionBar.layout();
	}
}