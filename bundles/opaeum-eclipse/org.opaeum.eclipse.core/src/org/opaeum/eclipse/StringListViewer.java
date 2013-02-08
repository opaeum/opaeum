package org.opaeum.eclipse;

import java.util.Arrays;
import java.util.Collection;

import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Widget;

public class StringListViewer extends StructuredViewer{
	Composite listAndButtons;
	Button btnAdd;
	Button btnRemove;
	List list;
	public StringListViewer(Composite parent,int style){
		listAndButtons = new Composite(parent, style);
		listAndButtons.setLayout(new GridLayout(2,false));
		list = new List(listAndButtons, SWT.MULTI);
		list.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,1,2));
		btnAdd = new Button(listAndButtons, SWT.PUSH);
		btnAdd.setText("Add");
		btnAdd.addSelectionListener(new SelectionListener(){
			@Override
			public void widgetSelected(SelectionEvent e){
				InputDialog id = new InputDialog(listAndButtons.getShell(), "Additional Persistent Class", "Enter Persistent Class Name", "", null);
				if(id.open() == Window.OK){
					list.add(id.getValue());
				}
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e){
			}
		});
		btnAdd.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, false, true,1,1));
		btnRemove=new Button(listAndButtons, SWT.PUSH);
		btnRemove.setText("Remove");
		btnRemove.addSelectionListener(new SelectionListener(){
			@Override
			public void widgetSelected(SelectionEvent e){
				list.remove(list.getSelectionIndices());
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e){
			}
		});
		btnRemove.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, false, true,1,1));
	}
	@Override
	public Control getControl(){
		return listAndButtons;
	}
	@Override
	protected Widget doFindInputItem(Object element){
		return list;
	}
	@Override
	protected Widget doFindItem(Object element){
		return list;
	}
	@Override
	protected void doUpdateItem(Widget item,Object element,boolean fullMap){
	}
	@Override
	protected java.util.List getSelectionFromWidget(){
		return Arrays.asList(list.getItems());
	}
	@Override
	protected void internalRefresh(Object element){
	}
	@Override
	protected void inputChanged(Object input,Object oldInput){
		Object[] sortedChildren = getSortedChildren(getInput());
		for(Object object:sortedChildren){
			list.add(object.toString());
		}
	}
	@Override
	public void reveal(Object element){
	}
	@SuppressWarnings({"rawtypes","unchecked"})
	@Override
	protected void setSelectionToWidget(java.util.List l,boolean reveal){
		list.setItems((String[]) l.toArray(new String[l.size()]));
	}
	public Collection<String> getStrings(){
		return Arrays.asList(list.getItems());
	}
}
