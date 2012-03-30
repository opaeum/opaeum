package org.opaeum.rap.wizards.contacts;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;


public class ClassSelectionEditingSupport extends EditingSupport{
	private static final long serialVersionUID = -1137190915118810955L;
	private TableViewer viewer;
	public ClassSelectionEditingSupport(TableViewer viewer,int index){
		super(viewer);
		this.viewer = viewer;
		this.index = index;
	}
	private int index;
	CheckboxCellEditor cellEditor;
	@Override
	protected CellEditor getCellEditor(Object element){
		if(cellEditor==null){
			cellEditor=new CheckboxCellEditor(viewer.getTable());
		}
		return cellEditor;
	}
	@Override
	protected boolean canEdit(Object element){
		return true;
	}
	@Override
	protected Object getValue(Object element){
		return ((SelectionForContact)element).getSelection().get(index);
	}
	@Override
	protected void setValue(Object element,Object value){
		((SelectionForContact)element).getSelection().set(index,(Boolean) value);
		viewer.refresh(true);
	}
}
