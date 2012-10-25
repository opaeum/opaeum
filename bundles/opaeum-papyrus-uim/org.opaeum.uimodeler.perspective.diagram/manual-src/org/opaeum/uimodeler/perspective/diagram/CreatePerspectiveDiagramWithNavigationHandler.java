package org.opaeum.uimodeler.perspective.diagram;

import org.eclipse.papyrus.infra.gmfdiag.navigation.CreateDiagramWithNavigationHandler;


public class CreatePerspectiveDiagramWithNavigationHandler extends CreateDiagramWithNavigationHandler {

	public CreatePerspectiveDiagramWithNavigationHandler() {
		super(new CreatePerspectiveDiagramCommand(), new PerspectiveCreationCondition());
	}

}
