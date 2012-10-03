package org.opaeum.uimodeler.abstractactionbar.diagram.preferences;

import org.eclipse.papyrus.infra.gmfdiag.preferences.pages.DiagramPreferencePage;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.AbstractEditorEditPart;
import org.opaeum.uimodeler.abstractactionbar.diagram.part.UimDiagramEditorPlugin;

/**
 * @generated
 */
public class DiagramGeneralPreferencePage extends DiagramPreferencePage{
	/**
	 * @generated
	 */
	public DiagramGeneralPreferencePage(){
		setPreferenceStore(UimDiagramEditorPlugin.getInstance().getPreferenceStore());
		setPreferenceKey(AbstractEditorEditPart.MODEL_ID);
	}
}
