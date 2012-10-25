package org.opaeum.uimodeler.cubequery.diagram;

import org.eclipse.papyrus.infra.gmfdiag.navigation.CreateDiagramWithNavigationHandler;


public class CreateCubeQueryDiagramWithNavigationHandler extends CreateDiagramWithNavigationHandler {

	public CreateCubeQueryDiagramWithNavigationHandler() {
		super(new CreateCubeQueryDiagramCommand(), new CubeQueryCreationCondition());
	}

}
