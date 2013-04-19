package org.opaeum.papyrus.businessprocessdiagram;

import org.eclipse.papyrus.infra.gmfdiag.navigation.CreateDiagramWithNavigationHandler;
import org.eclipse.papyrus.uml.diagram.activity.ActivityDiagramCreationCondition;

public class CreateBusinessProcessDiagramWithNavigationHandler extends CreateDiagramWithNavigationHandler {

	public CreateBusinessProcessDiagramWithNavigationHandler() {
		super(new CreateBusinessProcessDiagramCommand(), new ActivityDiagramCreationCondition());
	}
}
