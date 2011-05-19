/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package org.nakeduml.uim.modeleditor.preferences;

import org.eclipse.core.runtime.Preferences;
import org.nakeduml.uim.modeleditor.UIMPlugin;
import org.nakeduml.uim.modeleditor.editor.UIMEditor;
import org.topcased.modeler.preferences.AbstractTopcasedPreferenceInitializer;

/**
 * Initialize preferences to all UIM diagrams.
 * @generated
 */
public class AllDiagramPreferenceInitializer extends AbstractTopcasedPreferenceInitializer{
	/**
	 * @see org.topcased.modeler.preferences.AbstractTopcasedPreferenceInitializer#getPreferences()
	 * @generated
	 */
	@Override
	protected Preferences getPreferences(){
		return UIMPlugin.getDefault().getPluginPreferences();
	}
	/**
	 * @see org.topcased.modeler.preferences.AbstractTopcasedPreferenceInitializer#getEditorId()
	 * @generated
	 */
	protected String getEditorId(){
		return UIMEditor.EDITOR_ID;
	}
}