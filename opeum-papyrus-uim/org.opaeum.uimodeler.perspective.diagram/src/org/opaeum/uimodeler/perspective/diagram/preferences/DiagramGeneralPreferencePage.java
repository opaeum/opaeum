package org.opaeum.uimodeler.perspective.diagram.preferences;

import org.eclipse.papyrus.infra.gmfdiag.preferences.pages.DiagramPreferencePage;
import org.opaeum.uimodeler.perspective.diagram.edit.parts.UimPerspectiveEditPart;
import org.opaeum.uimodeler.perspective.diagram.part.UimPerspectiveDiagramEditorPlugin;

/**
 * @generated
 */
public class DiagramGeneralPreferencePage extends DiagramPreferencePage{
	/**
	 * @generated
	 */
	public DiagramGeneralPreferencePage(){
		setPreferenceStore(UimPerspectiveDiagramEditorPlugin.getInstance().getPreferenceStore());
		setPreferenceKey(UimPerspectiveEditPart.MODEL_ID);
	}
}
