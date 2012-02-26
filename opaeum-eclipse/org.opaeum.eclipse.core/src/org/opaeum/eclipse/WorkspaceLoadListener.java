package org.opaeum.eclipse;

import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.metamodel.workspace.INakedModelWorkspace;

public interface WorkspaceLoadListener{
	public void workspaceLoaded(EmfWorkspace workspace);
}
