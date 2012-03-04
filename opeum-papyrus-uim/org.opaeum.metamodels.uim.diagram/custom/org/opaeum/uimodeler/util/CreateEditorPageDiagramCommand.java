package org.opaeum.uimodeler.util;

import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.papyrus.infra.gmfdiag.common.AbstractPapyrusGmfCreateDiagramCommandHandler;
import org.opaeum.uim.diagram.edit.parts.EditorPageEditPart;
import org.opaeum.uim.diagram.part.UimDiagramEditorPlugin;
public class CreateEditorPageDiagramCommand extends AbstractPapyrusGmfCreateDiagramCommandHandler {

	@Override
	protected String getDefaultDiagramName() {
		return "Editor Page";
	}

	@Override
	protected String getDiagramNotationID() {
		return EditorPageEditPart.MODEL_ID;
	}

	@Override
	protected PreferencesHint getPreferenceHint() {
		return UimDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT;
	}

}
