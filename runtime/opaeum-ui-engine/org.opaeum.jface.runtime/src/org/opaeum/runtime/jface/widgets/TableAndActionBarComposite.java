package org.opaeum.runtime.jface.widgets;

import org.eclipse.nebula.widgets.pagination.collections.PageResultContentProvider;
import org.eclipse.nebula.widgets.pagination.renderers.navigation.ResultAndNavigationPageGraphicsRendererFactory;
import org.eclipse.nebula.widgets.pagination.table.PageableTable;
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
	private PageableTable pageableTable;
	private SortablePageableCheckboxTableViewer checkboxTableViewer;
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
	public SortablePageableCheckboxTableViewer getViewer(){
		return this.checkboxTableViewer;
	}
	private void addDisplayedContent(){
		setLayout(prepareLayout(1));
		setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
		int pageSize = 10;
		setPageableTable(new PageableTable(this, SWT.BORDER, SWT.BORDER | SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL, pageSize,
				PageResultContentProvider.getInstance(), ResultAndNavigationPageGraphicsRendererFactory.getBlueFactory(), null){
			@Override
			protected Table createWidget(Composite parent){
				Table table = createTable(parent);
				table.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
				this.viewer = checkboxTableViewer = new SortablePageableCheckboxTableViewer(table, this);
				return table;
			}
			@Override
			protected int getTableStyle(){
				return SWT.BORDER | SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CHECK;
			}
		});
		getPageableTable().setPageLoader(checkboxTableViewer);
		getPageableTable().setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
		this.table = getPageableTable().getViewer().getTable();
		table.addListener(41, new Listener(){
			public void handleEvent(Event event){
				// height cannot be per row so simply set
				event.height = 27;
			}
		});
		getTable().setHeaderVisible(true);
		getTable().setLinesVisible(true);
		getPageableTable().setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
		this.actionBar = new Composite(this, SWT.BORDER);
		this.actionBar.setLayout(prepareLayout(10));
		GridData actionBarData = new GridData(GridData.FILL, GridData.END, true, false);
		actionBarData.heightHint = 30;
		actionBar.setLayoutData(actionBarData);
	}
	public Table getTable(){
		return table;
	}
	public Composite getActionBar(){
		return actionBar;
	}
	public PageableTable getPageableTable(){
		return pageableTable;
	}
	public void setPageableTable(PageableTable pageableTable){
		this.pageableTable = pageableTable;
	}
}