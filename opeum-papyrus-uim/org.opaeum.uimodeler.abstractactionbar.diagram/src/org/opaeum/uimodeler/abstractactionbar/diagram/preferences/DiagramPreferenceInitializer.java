package org.opaeum.uimodeler.abstractactionbar.diagram.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.opaeum.uimodeler.abstractactionbar.diagram.part.UimDiagramEditorPlugin;

/**
 * @generated
 */
public class DiagramPreferenceInitializer extends AbstractPreferenceInitializer{
	/**
	 * @generated
	 */
	public void initializeDefaultPreferences(){
		IPreferenceStore store = getPreferenceStore();
		EditorActionBarPreferencePage.initDefaults(store);
		BuiltInLinkPreferencePage.initDefaults(store);
		LinkToQueryPreferencePage.initDefaults(store);
		BuiltInActionButtonPreferencePage.initDefaults(store);
		TransitionButtonPreferencePage.initDefaults(store);
		OperationButtonPreferencePage.initDefaults(store);
	}
	/**
	 * @generated
	 */
	protected IPreferenceStore getPreferenceStore(){
		return UimDiagramEditorPlugin.getInstance().getPreferenceStore();
	}
}
