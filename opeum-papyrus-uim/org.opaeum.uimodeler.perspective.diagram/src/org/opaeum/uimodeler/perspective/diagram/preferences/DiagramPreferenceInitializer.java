package org.opaeum.uimodeler.perspective.diagram.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.opaeum.uimodeler.perspective.diagram.part.PerspectiveConfigurationDiagramEditorPlugin;

/**
 * @generated
 */
public class DiagramPreferenceInitializer extends AbstractPreferenceInitializer{
	/**
	 * @generated
	 */
	public void initializeDefaultPreferences(){
		IPreferenceStore store = getPreferenceStore();
		EditorConfigurationPreferencePage.initDefaults(store);
		ExplorerConfigurationPreferencePage.initDefaults(store);
		PropertiesConfigurationPreferencePage.initDefaults(store);
	}
	/**
	 * @generated
	 */
	protected IPreferenceStore getPreferenceStore(){
		return PerspectiveConfigurationDiagramEditorPlugin.getInstance().getPreferenceStore();
	}
}
