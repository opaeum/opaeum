package org.opaeum.eclipse.context;

import org.eclipse.core.runtime.IProgressMonitor;
import org.opaeum.eclipse.OpaeumSynchronizationListener;

public interface OpaeumEclipseContextListener extends OpaeumSynchronizationListener{

	void onSave(IProgressMonitor monitor);

	void onClose(boolean save);
}
