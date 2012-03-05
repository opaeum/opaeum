package org.opaeum.uim.diagram.preferences;

import org.eclipse.papyrus.infra.gmfdiag.preferences.pages.DiagramPreferencePage;
import org.opaeum.uim.diagram.edit.parts.UserInterfaceEditPart;
import org.opaeum.uim.diagram.part.UimDiagramEditorPlugin;

/**
 * @generated
 */
public class DiagramGeneralPreferencePage extends DiagramPreferencePage{
	/**
	 * @generated
	 */
	public DiagramGeneralPreferencePage(){
		setPreferenceStore(UimDiagramEditorPlugin.getInstance().getPreferenceStore());
		setPreferenceKey(UserInterfaceEditPart.MODEL_ID);
	}
}
