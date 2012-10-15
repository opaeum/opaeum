package org.opaeum.eclipse.uml.propertysections.subsections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;

public interface IDetailsSubsection<T extends EObject> extends ISelectionChangedListener{
	public abstract void setEditingDomain(EditingDomain mixedEditDomain);
	public abstract void setEnabled(boolean enabled);
	public abstract void selectionChanged(SelectionChangedEvent event);
	public abstract void setLayoutData(Object data);
	public abstract void dispose();
}