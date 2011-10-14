package org.opaeum.eclipse.context;

import org.eclipse.core.runtime.IProgressMonitor;
import org.opaeum.eclipse.OpaeumContextSynchronizationListener;

public interface NakedUmlContextListener extends OpaeumContextSynchronizationListener{

	void onSave(IProgressMonitor monitor);

	void onClose(boolean save);
}
