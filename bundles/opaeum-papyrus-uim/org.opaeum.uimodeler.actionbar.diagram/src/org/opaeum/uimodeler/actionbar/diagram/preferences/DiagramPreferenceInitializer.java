package org.opaeum.uimodeler.actionbar.diagram.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.opaeum.uimodeler.actionbar.diagram.part.UimDiagramEditorPlugin;

/**
 * @generated
 */
public class DiagramPreferenceInitializer extends AbstractPreferenceInitializer{
	/**
	 * @generated
	 */
	public void initializeDefaultPreferences(){
		IPreferenceStore store = getPreferenceStore();
		ActionBarPreferencePage.initDefaults(store);
		BuiltInLinkPreferencePage.initDefaults(store);
		LinkToQueryPreferencePage.initDefaults(store);
		BuiltInActionButtonPreferencePage.initDefaults(store);
		TransitionButtonPreferencePage.initDefaults(store);
		InvocationButtonPreferencePage.initDefaults(store);
	}
	/**
	 * @generated
	 */
	protected IPreferenceStore getPreferenceStore(){
		return UimDiagramEditorPlugin.getInstance().getPreferenceStore();
	}
}
