package org.opaeum.uimodeler.perspective.diagram;


import org.eclipse.papyrus.infra.gmfdiag.common.GmfEditorFactory;
import org.opaeum.uimodeler.perspective.diagram.edit.parts.PerspectiveConfigurationEditPart;

public class PerspectiveDiagramEditorFactory extends GmfEditorFactory {

	public PerspectiveDiagramEditorFactory() {
		super(PerspectiveDiagramForMultiEditor.class, PerspectiveConfigurationEditPart.MODEL_ID);

	}

}