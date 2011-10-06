package org.opaeum.eclipse;

import java.util.Set;

import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.workspace.INakedModelWorkspace;


public interface NakedUmlSynchronizationListener{
	public void synchronizationComplete(INakedModelWorkspace workspace, Set<INakedElement> affectedElements);
}
