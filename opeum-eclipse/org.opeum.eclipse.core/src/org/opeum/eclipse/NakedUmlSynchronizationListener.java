package org.opeum.eclipse;

import java.util.Set;

import org.opeum.metamodel.core.INakedElement;
import org.opeum.metamodel.workspace.INakedModelWorkspace;


public interface NakedUmlSynchronizationListener{
	public void synchronizationComplete(INakedModelWorkspace workspace, Set<INakedElement> affectedElements);
}
