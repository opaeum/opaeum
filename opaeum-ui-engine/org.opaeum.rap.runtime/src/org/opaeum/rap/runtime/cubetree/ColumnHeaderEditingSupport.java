package org.opaeum.rap.runtime.cubetree;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

public abstract class ColumnHeaderEditingSupport extends EditingSupport{
	public ColumnHeaderEditingSupport(ColumnViewer viewer){
		super(viewer);
	}
	@Override
	protected void setValue(Object element,Object value){
	}
	@Override
	protected Object getValue(Object element){
		return null;
	}
	protected abstract void flipExpandedState(ColumnHeaderRow row);
	@Override
	protected CellEditor getCellEditor(final Object element){
		new CellEditor(){
			@Override
			public void activate(){
				flipExpandedState((ColumnHeaderRow) element);
			}
			@Override
			protected void doSetValue(Object value){
			}
			@Override
			protected void doSetFocus(){
			}
			@Override
			protected Object doGetValue(){
				return "";
			}
			@Override
			protected Control createControl(Composite parent){
				return null;
			}
		};
		return null;
	}
	@Override
	protected boolean canEdit(Object element){
		return element instanceof ColumnHeaderRow;
	}
}