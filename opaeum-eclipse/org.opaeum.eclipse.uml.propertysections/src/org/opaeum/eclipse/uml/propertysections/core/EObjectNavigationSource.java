package org.opaeum.eclipse.uml.propertysections.core;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.ui.IWorkbenchPage;
import org.opaeum.eclipse.context.OpenUmlFile;

public interface EObjectNavigationSource{
	EObject getEObjectToGoTo();
	CLabel getLabelCombo();
	IWorkbenchPage getActivePage();
	OpenUmlFile getOpenUmlFile();
}