package org.opaeum.uimodeler.util;

import org.eclipse.papyrus.infra.gmfdiag.common.GmfEditorFactory;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UserInterfaceEditPart;

public class UserInterfaceDiagramEditorFactory extends GmfEditorFactory {

	public UserInterfaceDiagramEditorFactory() {
		super(UserInterfaceDiagramForMultiEditor.class, UserInterfaceEditPart.MODEL_ID);

	}

}