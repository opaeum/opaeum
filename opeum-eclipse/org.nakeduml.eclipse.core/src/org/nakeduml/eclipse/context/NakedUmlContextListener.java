package org.opeum.eclipse.context;

import org.eclipse.core.runtime.IProgressMonitor;
import org.opeum.eclipse.NakedUmlSynchronizationListener;

public interface NakedUmlContextListener extends NakedUmlSynchronizationListener{

	void onSave(IProgressMonitor monitor);

	void onClose(boolean save);
}
