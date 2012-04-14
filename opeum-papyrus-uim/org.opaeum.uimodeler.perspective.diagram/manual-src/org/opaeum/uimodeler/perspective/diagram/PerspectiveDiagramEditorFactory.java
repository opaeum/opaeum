package org.opaeum.uimodeler.perspective.diagram;


import org.eclipse.papyrus.infra.gmfdiag.common.GmfEditorFactory;
import org.opaeum.uimodeler.perspective.diagram.edit.parts.UimPerspectiveEditPart;

public class PerspectiveDiagramEditorFactory extends GmfEditorFactory {

	public PerspectiveDiagramEditorFactory() {
		super(PerspectiveDiagramForMultiEditor.class, UimPerspectiveEditPart.MODEL_ID);

	}

}