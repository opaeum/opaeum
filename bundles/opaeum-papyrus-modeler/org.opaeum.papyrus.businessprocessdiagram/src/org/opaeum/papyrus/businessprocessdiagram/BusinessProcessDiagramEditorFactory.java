package org.opaeum.papyrus.businessprocessdiagram;

import org.eclipse.papyrus.infra.gmfdiag.common.GmfEditorFactory;
public class BusinessProcessDiagramEditorFactory extends GmfEditorFactory {
	public BusinessProcessDiagramEditorFactory() {
		super(UmlActivityDiagramForMultiEditor.class, BusinessProcessDiagramEditPart.MODEL_ID);
	}
}
