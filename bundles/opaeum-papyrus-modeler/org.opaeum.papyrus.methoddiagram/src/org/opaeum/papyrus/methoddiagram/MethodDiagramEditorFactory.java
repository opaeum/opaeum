package org.opaeum.papyrus.methoddiagram;

import org.eclipse.papyrus.infra.gmfdiag.common.GmfEditorFactory;
import org.eclipse.papyrus.uml.diagram.activity.UmlActivityDiagramForMultiEditor;
public class MethodDiagramEditorFactory extends GmfEditorFactory {
	public MethodDiagramEditorFactory() {
		super(UmlActivityDiagramForMultiEditor.class, MethodDiagramEditPart.MODEL_ID);
	}
}
