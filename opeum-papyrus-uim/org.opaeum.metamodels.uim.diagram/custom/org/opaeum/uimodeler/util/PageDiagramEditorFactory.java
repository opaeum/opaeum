package org.opaeum.uimodeler.util;

import org.eclipse.papyrus.infra.gmfdiag.common.GmfEditorFactory;
import org.opaeum.uim.diagram.edit.parts.EditorPageEditPart;

public class PageDiagramEditorFactory extends GmfEditorFactory {

	public PageDiagramEditorFactory() {
		super(EditorPageDiagramForMultiEditor.class, EditorPageEditPart.MODEL_ID);

	}

}