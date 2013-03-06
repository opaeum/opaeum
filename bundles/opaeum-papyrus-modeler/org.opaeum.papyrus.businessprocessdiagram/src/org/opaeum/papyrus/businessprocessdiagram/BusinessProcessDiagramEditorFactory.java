package org.opaeum.papyrus.businessprocessdiagram;

import org.eclipse.papyrus.infra.gmfdiag.common.GmfEditorFactory;
public class BusinessProcessDiagramEditorFactory extends GmfEditorFactory {
	public BusinessProcessDiagramEditorFactory() {
		super(UmlActivityDiagramForMultiEditor.class, org.eclipse.papyrus.uml.diagram.activity.edit.parts.ActivityDiagramEditPart.MODEL_ID);
	}
}
