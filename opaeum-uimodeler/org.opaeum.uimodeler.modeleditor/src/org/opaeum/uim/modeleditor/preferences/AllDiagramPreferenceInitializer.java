/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package org.opaeum.uim.modeleditor.preferences;

import org.eclipse.core.runtime.Preferences;
import org.opaeum.uim.modeleditor.UimPlugin;
import org.opaeum.uim.modeleditor.editor.UimEditor;
import org.topcased.modeler.preferences.AbstractTopcasedPreferenceInitializer;

/**
 * Initialize preferences to all Uim diagrams.
 * @generated
 */
public class AllDiagramPreferenceInitializer extends AbstractTopcasedPreferenceInitializer{
	/**
	 * @see org.topcased.modeler.preferences.AbstractTopcasedPreferenceInitializer#getPreferences()
	 * @generated
	 */
	@Override
	protected Preferences getPreferences(){
		return UimPlugin.getDefault().getPluginPreferences();
	}
	/**
	 * @see org.topcased.modeler.preferences.AbstractTopcasedPreferenceInitializer#getEditorId()
	 * @generated
	 */
	protected String getEditorId(){
		return UimEditor.EDITOR_ID;
	}
}