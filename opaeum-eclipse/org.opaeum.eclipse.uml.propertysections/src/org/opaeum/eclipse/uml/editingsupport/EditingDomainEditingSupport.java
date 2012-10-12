package org.opaeum.eclipse.uml.editingsupport;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;

public abstract class EditingDomainEditingSupport extends EditingSupport{
	protected EditingDomain editingDomain;
	protected ColumnViewer viewer;
	protected String name;
	protected int width;
	public EditingDomainEditingSupport(ColumnViewer viewer, String name, int width){
		super(viewer);
		this.width=width;
		this.name=name;
		this.viewer=viewer;
	}
	
	public EditingDomainEditingSupport(ColumnViewer viewer, String name){
		this(viewer,name,100);
	}

	public String getName(){
		return name;
	}

	public int getWidth(){
		return width;
	}

	public void setEditingDomain(EditingDomain editingDomain){
		this.editingDomain=editingDomain;
	}
	public abstract CellLabelProvider getLabelProvider();
}