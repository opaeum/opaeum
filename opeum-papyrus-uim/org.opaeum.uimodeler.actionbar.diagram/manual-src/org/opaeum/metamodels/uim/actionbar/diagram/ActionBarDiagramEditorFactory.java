package org.opaeum.metamodels.uim.actionbar.diagram;

import org.eclipse.papyrus.infra.gmfdiag.common.GmfEditorFactory;
import org.opaeum.uimodeler.actionbar.diagram.edit.parts.AbstractEditorEditPart;

public class ActionBarDiagramEditorFactory extends GmfEditorFactory {

	public ActionBarDiagramEditorFactory() {
		super(ActionBarDiagramForMultiEditor.class, AbstractEditorEditPart.MODEL_ID);

	}

}