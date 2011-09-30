package org.nakeduml.topcased.uml.editor;

import org.eclipse.core.runtime.IProgressMonitor;

public interface NakedUmlContextListener extends NakedUmlSynchronizationListener{

	void onSave(IProgressMonitor monitor);

	void onClose(boolean save);
}
