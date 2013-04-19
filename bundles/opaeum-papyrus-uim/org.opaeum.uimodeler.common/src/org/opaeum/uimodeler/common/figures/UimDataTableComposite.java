package org.opaeum.uimodeler.common.figures;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.TableColumn;
import org.opaeum.uim.swt.IUimWidget;
import org.opaeum.uim.swt.TableAndActionBarComposite;
import org.opaeum.uim.swt.UimSwtUtil;

public final class UimDataTableComposite extends TableAndActionBarComposite{
	private FirstTableRow firstRow;
	public static final int ROW_HEIGHT = 34;
	public UimDataTableComposite(Composite parent,int style){
		super(parent, style);
		addFirstRow();
	}
	@Override
	public Point computeSize(int wHint,int hHint,boolean changed){
		Point size = super.computeSize(wHint, hHint, changed);
		Point firstRowSize = firstRow.computeSize(wHint, ROW_HEIGHT);
		Point point = new Point(Math.max(firstRowSize.x + 10, size.x), Math.max(300, size.y));
		return point;
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
		getFirstRow().setBounds(new Rectangle(0, getTable().getHeaderHeight(), getSize().x, ROW_HEIGHT));
		Control[] children = getFirstRow().getChildren();
		for(Control control:children){
			((GridData) control.getLayoutData()).verticalAlignment = SWT.FILL;
		}
		getFirstRow().layout();
		recalculateColumns();
		super.layout();
		getFirstRow().setBounds(new Rectangle(0, getTable().getHeaderHeight(), getSize().x, ROW_HEIGHT));
		for(Control control:children){
			((GridData) control.getLayoutData()).verticalAlignment = SWT.FILL;
		}
		getFirstRow().layout();
		// getDisplayedContent().setBounds(new Rectangle(0, 0, getSize().x-2, getSize().y));
		// getDisplayedContent().layout();
		// getFirstRow().getParent(). setBounds(new Rectangle(0, getDisplayedContent().getTable().getHeaderHeight(), getSize().x, ROW_HEIGHT));
	}
	private void addFirstRow(){
		this.firstRow = new FirstTableRow(this, SWT.NONE);
		firstRow.setLayout(prepareLayout(30));
		firstRow.setLayoutData(new GridData(0, 0));
	}
	public FirstTableRow getFirstRow(){
		return firstRow;
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
				
			}
			i++;
		}
		markForShot();
	}
	@Override
	public void markForShot(){
		super.markForShot();
		for(Control control:getFirstRow().getChildren()){
			if(control instanceof IUimWidget){
				((IUimWidget) control).markForShot();
			}
		}
	}
	public int getHeaderHeight(){
		return getTable().getHeaderHeight();
	}
	public Control[] getColumnControls(){
		return getFirstRow().getChildren();
	}
	public void removeColumn(int i){
		getTable().getColumns()[i].dispose();
		getColumnControls()[i].dispose();
	}
	@Override
	public Object getData(String key){
		if(key.equals(UimSwtUtil.WBP_NEED_IMAGE)){
			return null;
		}
		return super.getData(key);
	}
}