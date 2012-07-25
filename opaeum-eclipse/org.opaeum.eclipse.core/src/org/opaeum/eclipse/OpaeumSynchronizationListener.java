package org.opaeum.eclipse;

import java.util.Set;

import org.eclipse.uml2.uml.Element;
import org.opaeum.metamodel.workspace.ModelWorkspace;


public interface OpaeumSynchronizationListener{
	public void synchronizationComplete(ModelWorkspace workspace, Set<Element> affectedElements);
}
