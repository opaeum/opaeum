package org.opaeum.uimodeler.util;
import org.eclipse.papyrus.infra.gmfdiag.navigation.CreateDiagramWithNavigationHandler;


public class CreateEditorPageDiagramWithNavigationHandler extends CreateDiagramWithNavigationHandler {

	public CreateEditorPageDiagramWithNavigationHandler() {
		super(new CreateUserInterfaceDiagramCommand(), new UserInterfaceCreationCondition());
	}

}
