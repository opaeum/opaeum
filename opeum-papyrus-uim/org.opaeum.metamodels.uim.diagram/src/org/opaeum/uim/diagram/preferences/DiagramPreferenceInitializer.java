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
		LinkToOperationPreferencePage.initDefaults(store);
		GridPanelPreferencePage.initDefaults(store);
		UimDataTablePreferencePage.initDefaults(store);
		UimFieldPreferencePage.initDefaults(store);
		LinkToEntityPreferencePage.initDefaults(store);
		BuiltInActionPreferencePage.initDefaults(store);
		VerticalPanelPreferencePage.initDefaults(store);
		OperationActionPreferencePage.initDefaults(store);
		HorizontalPanelPreferencePage.initDefaults(store);
		TransitionActionPreferencePage.initDefaults(store);
	}
	/**
	 * @generated
	 */
	protected IPreferenceStore getPreferenceStore(){
		return UimDiagramEditorPlugin.getInstance().getPreferenceStore();
	}
}
