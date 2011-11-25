package org.opaeum.eclipse;

import java.util.Set;

import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.workspace.INakedModelWorkspace;


public interface OpaeumSynchronizationListener{
	public void synchronizationComplete(INakedModelWorkspace workspace, Set<INakedElement> affectedElements);
}
