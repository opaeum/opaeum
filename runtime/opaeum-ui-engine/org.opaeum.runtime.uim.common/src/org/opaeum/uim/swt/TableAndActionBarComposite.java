package org.opaeum.uim.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;

public class TableAndActionBarComposite extends Composite implements IUimWidget{
	private DataTableActionBarComposite actionBar;
	private Table table;
	public TableAndActionBarComposite(Composite parent,int style){
		super(parent, style);
		addDisplayedContent();
	}
	private GridLayout prepareLayout(int columns){
		GridLayout gl = new GridLayout(columns, false);
		gl.marginHeight = 0;
		gl.marginWidth = 0;
		gl.verticalSpacing = 0;
		gl.horizontalSpacing = 0;
		return gl;
	}
	private void addDisplayedContent(){
		setLayout(prepareLayout(1));
		setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
		this.table = new Table(this, SWT.CHECK);
		table.addListener(41, new Listener(){
			public void handleEvent(Event event){
				// height cannot be per row so simply set
				event.height = 27;
			}
		});
		getTable().setHeaderVisible(true);
		getTable().setLinesVisible(true);
		table.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
		this.actionBar = new  DataTableActionBarComposite(this, SWT.BORDER);
		this.actionBar.setLayout(prepareLayout(10));
		GridData actionBarData = new GridData(GridData.FILL, GridData.END, true, false);
		actionBarData.heightHint = 30;
		actionBar.setLayoutData(actionBarData);
	}
	public Table getTable(){
		return table;
	}
	public DataTableActionBarComposite getActionBar(){
		return actionBar;
	}
	@Override
	public void markForShot(){
		table.setData(UimConstants.WBP_NEED_IMAGE,Boolean.TRUE);
		actionBar.markForShot();
	}
}
