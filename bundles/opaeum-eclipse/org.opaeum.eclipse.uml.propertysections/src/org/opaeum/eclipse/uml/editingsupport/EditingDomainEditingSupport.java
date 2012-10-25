package org.opaeum.eclipse.uml.editingsupport;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ICellEditorListener;

public abstract class EditingDomainEditingSupport extends EditingSupport implements ICellEditorListener{
	protected EditingDomain editingDomain;
	protected ColumnViewer viewer;
	protected String name;
	protected int width;
	protected boolean canEdit = true;
	private Set<OpaeumCellEditorListener> listeners = new HashSet<OpaeumCellEditorListener>();
	public EditingDomainEditingSupport(ColumnViewer viewer,String name,int width){
		super(viewer);
		this.width = width;
		this.name = name;
		this.viewer = viewer;
	}
	public void addCellEditorListener(OpaeumCellEditorListener l){
		listeners.add(l);
	}
	public void removeCellEditorListener(OpaeumCellEditorListener l){
		listeners.remove(l);
	}
	public EditingDomainEditingSupport(ColumnViewer viewer,String name){
		this(viewer, name, 100);
	}
	@Override
	public void cancelEditor(){
		for(ICellEditorListener cel:listeners){
			cel.cancelEditor();
		}
	}
	protected void deactivated(){
		for(OpaeumCellEditorListener l:listeners){
			l.deactivated();
		}
	}
	protected void activated(){
		for(OpaeumCellEditorListener l:listeners){
			l.activated();
		}
	}

	public String getName(){
		return name;
	}
	public int getWidth(){
		return width;
	}
	public void editorValueChanged(boolean oldValidState,boolean newValidState){
		for(ICellEditorListener cel:listeners){
			cel.editorValueChanged(oldValidState, newValidState);
		}
	}
	public void applyEditorValue(){
		for(ICellEditorListener cel:listeners){
			cel.applyEditorValue();
		}
	}
	@Override
	protected boolean canEdit(Object element){
		return canEdit;
	}
	public void setEditable(boolean b){
		canEdit = b;
	}
	public void setEditingDomain(EditingDomain editingDomain){
		this.editingDomain = editingDomain;
	}
	public abstract CellLabelProvider getLabelProvider();
	
}