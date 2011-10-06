package org.opaeum.eclipse.context;

import org.eclipse.core.runtime.IProgressMonitor;
import org.opaeum.eclipse.NakedUmlSynchronizationListener;

public interface NakedUmlContextListener extends NakedUmlSynchronizationListener{

	void onSave(IProgressMonitor monitor);

	void onClose(boolean save);
}
