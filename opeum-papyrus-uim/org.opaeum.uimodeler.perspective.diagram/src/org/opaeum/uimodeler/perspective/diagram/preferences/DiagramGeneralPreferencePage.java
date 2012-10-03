package org.opaeum.uimodeler.perspective.diagram.preferences;

import org.eclipse.papyrus.infra.gmfdiag.preferences.pages.DiagramPreferencePage;
import org.opaeum.uimodeler.perspective.diagram.edit.parts.PerspectiveConfigurationEditPart;
import org.opaeum.uimodeler.perspective.diagram.part.PerspectiveConfigurationDiagramEditorPlugin;

/**
 * @generated
 */
public class DiagramGeneralPreferencePage extends DiagramPreferencePage{
	/**
	 * @generated
	 */
	public DiagramGeneralPreferencePage(){
		setPreferenceStore(PerspectiveConfigurationDiagramEditorPlugin.getInstance().getPreferenceStore());
		setPreferenceKey(PerspectiveConfigurationEditPart.MODEL_ID);
	}
}
