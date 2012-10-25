/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package org.opaeum.bpmn2.modeleditor.preferences;

import org.eclipse.core.runtime.Preferences;
import org.opaeum.bpmn2.modeleditor.Bpmn2Plugin;
import org.opaeum.bpmn2.modeleditor.editor.Bpmn2Editor;
import org.topcased.modeler.preferences.AbstractTopcasedPreferenceInitializer;

/**
 * Initialize preferences to all Bpmn2 diagrams.
 * @generated
 */
public class AllDiagramPreferenceInitializer extends AbstractTopcasedPreferenceInitializer{
	/**
	 * @see org.topcased.modeler.preferences.AbstractTopcasedPreferenceInitializer#getPreferences()
	 * @generated
	 */
	@Override
	protected Preferences getPreferences(){
		return Bpmn2Plugin.getDefault().getPluginPreferences();
	}
	/**
	 * @see org.topcased.modeler.preferences.AbstractTopcasedPreferenceInitializer#getEditorId()
	 * @generated
	 */
	protected String getEditorId(){
		return Bpmn2Editor.EDITOR_ID;
	}
}