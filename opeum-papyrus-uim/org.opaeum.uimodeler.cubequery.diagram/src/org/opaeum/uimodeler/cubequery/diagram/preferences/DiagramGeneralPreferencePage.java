package org.opaeum.uimodeler.cubequery.diagram.preferences;

import org.eclipse.papyrus.infra.gmfdiag.preferences.pages.DiagramPreferencePage;
import org.opaeum.uimodeler.cubequery.diagram.edit.parts.CubeQueryEditPart;
import org.opaeum.uimodeler.cubequery.diagram.part.UimCubeQueryDiagramEditorPlugin;

/**
 * @generated
 */
public class DiagramGeneralPreferencePage extends DiagramPreferencePage{
	/**
	 * @generated
	 */
	public DiagramGeneralPreferencePage(){
		setPreferenceStore(UimCubeQueryDiagramEditorPlugin.getInstance().getPreferenceStore());
		setPreferenceKey(CubeQueryEditPart.MODEL_ID);
	}
}
