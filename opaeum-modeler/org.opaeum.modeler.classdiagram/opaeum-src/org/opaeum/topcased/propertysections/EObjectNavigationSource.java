package org.opaeum.topcased.propertysections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.ui.IWorkbenchPage;
import org.opaeum.topcased.uml.editor.OpaeumEditor;

public interface EObjectNavigationSource{
	EObject getEObjectToGoTo();
	CLabel getLabelCombo();
	IWorkbenchPage getActivePage();
}