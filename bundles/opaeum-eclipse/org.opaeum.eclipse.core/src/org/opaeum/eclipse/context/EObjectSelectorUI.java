package org.opaeum.eclipse.context;

import org.eclipse.emf.ecore.EObject;

public interface EObjectSelectorUI{
	public void gotoEObject(EObject key);

	public void pushSelection(EObject eObject);

	public EObject popSelection();
}
