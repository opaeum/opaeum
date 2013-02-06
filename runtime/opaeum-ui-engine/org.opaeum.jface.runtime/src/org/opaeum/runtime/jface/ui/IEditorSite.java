package org.opaeum.runtime.jface.ui;

import org.eclipse.jface.viewers.ISelectionProvider;

public interface IEditorSite{

	IWorkbenchPage getPage();
	IWorkbenchWindow getActiveWorkbenchWindow();
}
