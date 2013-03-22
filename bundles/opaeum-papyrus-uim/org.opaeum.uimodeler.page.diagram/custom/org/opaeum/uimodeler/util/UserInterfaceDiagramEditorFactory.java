package org.opaeum.uimodeler.util;

import org.eclipse.papyrus.infra.gmfdiag.common.GmfEditorFactory;
import org.opaeum.uimodeler.page.diagram.edit.parts.PageEditPart;

public class UserInterfaceDiagramEditorFactory extends GmfEditorFactory {

	public UserInterfaceDiagramEditorFactory() {
		super(UserInterfaceDiagramForMultiEditor.class, PageEditPart.MODEL_ID);

	}

}