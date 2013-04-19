package org.opaeum.runtime.jface.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public interface IWorkbenchPart{
	String getPartName();

	Object getAdapter(Class<?> class1);
	void createPartControl(Composite c);
	Control getPartControl();
}
