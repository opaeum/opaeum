package org.opeum.eclipse;

import java.util.Set;

import net.sf.opeum.metamodel.core.INakedElement;
import net.sf.opeum.metamodel.workspace.INakedModelWorkspace;


public interface NakedUmlSynchronizationListener{
	public void synchronizationComplete(INakedModelWorkspace workspace, Set<INakedElement> affectedElements);
}
