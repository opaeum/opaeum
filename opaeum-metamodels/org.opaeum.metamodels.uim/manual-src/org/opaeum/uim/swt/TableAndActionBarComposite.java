package org.opaeum.uim.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;

public class TableAndActionBarComposite extends Composite{
	private Composite actionBar;
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
		getTable().setHeaderVisible(true);
		getTable().setLinesVisible(true);
		table.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
		this.actionBar = new Composite(this, SWT.BORDER);
		this.actionBar.setLayout(prepareLayout(30));
		actionBar.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
	}
	public Table getTable(){
		return table;
	}
	public Composite getActionBar(){
		return actionBar;
	}
}