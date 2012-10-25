package org.opaeum.uimodeler.util;
import org.eclipse.papyrus.infra.gmfdiag.navigation.CreateDiagramWithNavigationHandler;


public class CreateUserInterfaceDiagramWithNavigationHandler extends CreateDiagramWithNavigationHandler {

	public CreateUserInterfaceDiagramWithNavigationHandler() {
		super(new CreatePageDiagramCommand(), new UserInterfaceCreationCondition());
	}

}
