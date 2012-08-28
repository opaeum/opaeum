package org.opaeum.eclipse.uml.editingsupport;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;

public abstract class EditingDomainEditingSupport extends EditingSupport{
	protected EditingDomain editingDomain;
	ColumnViewer viewer;

	public EditingDomainEditingSupport(ColumnViewer viewer){
		super(viewer);
		this.viewer=viewer;
	}

	public void setEditingDomain(EditingDomain editingDomain){
		this.editingDomain=editingDomain;
	}
}