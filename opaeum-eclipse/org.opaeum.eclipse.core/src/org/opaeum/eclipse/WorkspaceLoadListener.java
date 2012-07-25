package org.opaeum.eclipse;

import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.metamodel.workspace.ModelWorkspace;

public interface WorkspaceLoadListener{
	public void workspaceLoaded(EmfWorkspace workspace);
}
