package org.opaeum.eclipse.newchild;

import java.util.Set;

import org.eclipse.emf.ecore.EReference;

public interface ICreateChildActionProvider{

	public abstract Set<EReference> getControlledFeatures();

	public abstract Set<? extends ICreateChildAction> getActions();
}
