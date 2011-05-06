/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package org.nakeduml.uim.classform.preferences;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.nakeduml.uim.classform.ClassFormPlugin;
import org.topcased.facilities.preferences.AbstractTopcasedPreferencePage;

/**
 * This class represents a preference page that is contributed to the Preferences dialog. This page is used to modify
 * preferences only. They are stored in the preference store that belongs to the main plug-in class. That way,
 * preferences can be accessed directly via the preference store.
 *
 * @generated
 */
public class ClassFormDiagramPreferencePage extends AbstractTopcasedPreferencePage{
	/**
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 * @generated
	 */
	public void init(IWorkbench workbench){
		// Do nothing
	}
	/**
	 * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
	 * @generated
	 */
	protected Control createContents(Composite parent){
		return null;
	}
	/**
	 * @see org.topcased.facilities.preferences.AbstractTopcasedPreferencePage#getBundleId()
	 *
	 * @generated
	 */
	protected String getBundleId(){
		return ClassFormPlugin.getId();
	}
}
