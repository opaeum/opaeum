/*******************************************************************************
 * 
 ******************************************************************************/
package org.opeum.uim.classform.preferences;

import org.opeum.uim.modeleditor.UimPlugin;
import org.topcased.modeler.preferences.AbstractNodePreferencePage;

/**
 * This class represents a preference page that is contributed to the Preferences or Property dialog. This page is used to modify
 * preferences only. They are stored in the preference store that belongs to the main plug-in class. That way,
 * preferences can be accessed directly via the preference store.
 *
 * @generated
 */
public class UimFieldPreferencePage extends AbstractNodePreferencePage{
	/**
	 * @see org.topcased.modeler.preferences.AbstractNodePreferencePage#getNodeBackgroundColor()
	 * @generated
	 */
	protected String getNodeBackgroundColor(){
		return ClassFormDiagramPreferenceConstants.UIMFIELD_DEFAULT_BACKGROUND_COLOR;
	}
	/**
	 * @see org.topcased.modeler.preferences.AbstractNodePreferencePage#getNodeFont()
	 * @generated
	 */
	protected String getNodeFont(){
		return ClassFormDiagramPreferenceConstants.UIMFIELD_DEFAULT_FONT;
	}
	/**
	 * @see org.topcased.modeler.preferences.AbstractNodePreferencePage#getNodeForegroundColor()
	 * @generated
	 */
	protected String getNodeForegroundColor(){
		return ClassFormDiagramPreferenceConstants.UIMFIELD_DEFAULT_FOREGROUND_COLOR;
	}
	/**
	 * @see org.topcased.facilities.preferences.AbstractTopcasedPreferencePage#getBundleId()
	 * @generated
	 */
	protected String getBundleId(){
		return UimPlugin.getId();
	}
}
