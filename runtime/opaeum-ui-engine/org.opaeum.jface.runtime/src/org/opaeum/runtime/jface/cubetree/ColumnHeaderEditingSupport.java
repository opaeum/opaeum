package org.opaeum.runtime.jface.cubetree;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public abstract class ColumnHeaderEditingSupport extends EditingSupport{
	public ColumnHeaderEditingSupport(ColumnViewer viewer){
		super(viewer);
	}
	@Override
	protected void setValue(Object element,Object value){
	}
	@Override
	protected Object getValue(Object element){
		return "";
	}
	protected abstract void flipExpandedState(ColumnHeaderRow row);
	@Override
	protected CellEditor getCellEditor(final Object element){
		return new CellEditor(){
			//Some bug in RAP causes this method to be called multiple times on one request
			long lastActivationTime = 0;
			@Override
			public void activate(){
				if(System.currentTimeMillis() - lastActivationTime > 10000){
					lastActivationTime =System.currentTimeMillis();
					flipExpandedState((ColumnHeaderRow) element);
				}
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
	}
	@Override
	protected boolean canEdit(Object element){
		return element instanceof ColumnHeaderRow;
	}
}