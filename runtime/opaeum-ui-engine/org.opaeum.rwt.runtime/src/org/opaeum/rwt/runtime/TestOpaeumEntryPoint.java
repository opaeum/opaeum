package org.opaeum.rwt.runtime;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TableViewerFocusCellManager;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.rap.rwt.application.EntryPoint;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class TestOpaeumEntryPoint implements EntryPoint{

	@Override
	public int createUI(){
  	System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		
    // Create a maximized top-level shell without trimmings that represents the main "page"
    Display display = new Display();
    final Shell page = new Shell( display, SWT.NO_TRIM );
    page.setMaximized( true );
    page.setLayout( new GridLayout() );
 
    // Create contents of main shell
    final Label label = new Label( page, SWT.NONE );
    label.setText( "Hello" );
    TableViewer tv = new TableViewer(page);
    tv.setContentProvider(new ArrayContentProvider());
    tv.getTable().setHeaderVisible(true);
    TableViewerColumn c = new TableViewerColumn(tv,SWT.None);
    c.getColumn().setText("Bla");
    c.getColumn().setWidth(100);
    c.setLabelProvider(new CellLabelProvider(){
			@Override
			public void update(ViewerCell cell){
				cell.setText((String)cell.getViewerRow().getElement());
				System.out.println(cell.getViewerRow().getElement());
				// TODO Auto-generated method stub
			}
		});
    tv.setInput(new Object[]{"asdf","asdf","asdf"});
    final Button button = new Button( page, SWT.PUSH );
    button.setText( "World" );
    button.addSelectionListener(new SelectionListener(){
			@Override
			public void widgetSelected(SelectionEvent e){
				 label.setText("Hlo" + label.getText());
				 button.setText(label.getText());
					page.layout();
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e){
			}
		});
 
    // Open the top-level shell and run the main loop to process events
    page.layout();
    page.open();
    return 0;
	}
}
