package org.opaeum.uim.diagram.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.opaeum.uim.diagram.part.UimDiagramEditorPlugin;

/**
 * @generated
 */
public class DiagramPreferenceInitializer extends AbstractPreferenceInitializer{
	/**
	 * @generated
	 */
	public void initializeDefaultPreferences(){
		IPreferenceStore store = getPreferenceStore();
		BuiltInActionPreferencePage.initDefaults(store);
		VerticalPanelPreferencePage.initDefaults(store);
		GridPanelPreferencePage.initDefaults(store);
		HorizontalPanelPreferencePage.initDefaults(store);
		UimFieldPreferencePage.initDefaults(store);
	}
	/**
	 * @generated
	 */
	protected IPreferenceStore getPreferenceStore(){
		return UimDiagramEditorPlugin.getInstance().getPreferenceStore();
	}
}
