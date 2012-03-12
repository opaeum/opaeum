package org.opaeum.uimodeler.util;

import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramActionBarContributor;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchPage;
public class UimDiagramActionBarContributor extends DiagramActionBarContributor {

	protected Class getEditorClass() {
		return DiagramDocumentEditor.class;
	}
	protected String getEditorId() {
		return "org.opeum.uimodeler.UMLDiagramEditorID";
	}

	public void init(IActionBars bars, IWorkbenchPage page) {
		super.init(bars, page);
	}
}
