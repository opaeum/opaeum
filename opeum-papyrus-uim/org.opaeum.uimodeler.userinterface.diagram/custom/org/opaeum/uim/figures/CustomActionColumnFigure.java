package org.opaeum.uim.figures;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.wb.os.OSSupport;

public abstract class CustomActionColumnFigure extends CustomUimActionFigure{
	public CustomActionColumnFigure(Composite comp){
		super((Composite) comp.getChildren()[1]);
		Table table = ((UimDataTableComposite) comp).table;
		column = new TableColumn(table, SWT.LEFT);
		getWidget().addDisposeListener(new DisposeListener(){
			@Override
			public void widgetDisposed(DisposeEvent e){
				column.dispose();
			}
		});
		table.setData(OSSupport.WBP_NEED_IMAGE, Boolean.TRUE);
		table.layout();
	}
	private TableColumn column;
	public void paint(Graphics graphics){
		Point copy = ((Figure) getParent()).getLocation().getCopy();
		graphics.drawImage((Image) getWidget().getData("OPAEUM_IMAGE"), copy.x + 1, copy.y);
	}
	@Override
	public void setLabelText(String string){
		column.setText(string==null || string.length()==0?"NewAction":string);
		column.getParent().setData(OSSupport.WBP_NEED_IMAGE, Boolean.TRUE);
		column.getParent().layout();
		super.setLabelText(string);
		((UimDataTableComposite)column.getParent().getParent()).recalculateColumns();			
	}
	@Override
	protected void createContents(){
		// TODO Auto-generated method stub
		
	}
	@Override
	public void markForRepaint(){
		// TODO Auto-generated method stub
		super.markForRepaint();
	}
}
