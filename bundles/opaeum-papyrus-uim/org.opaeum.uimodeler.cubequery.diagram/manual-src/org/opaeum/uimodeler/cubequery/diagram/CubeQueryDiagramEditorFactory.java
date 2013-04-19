package org.opaeum.uimodeler.cubequery.diagram;


import org.eclipse.papyrus.infra.gmfdiag.common.GmfEditorFactory;
import org.opaeum.uimodeler.cubequery.diagram.edit.parts.CubeQueryEditPart;

public class CubeQueryDiagramEditorFactory extends GmfEditorFactory {

	public CubeQueryDiagramEditorFactory() {
		super(CubeQueryDiagramForMultiEditor.class, CubeQueryEditPart.MODEL_ID);

	}

}