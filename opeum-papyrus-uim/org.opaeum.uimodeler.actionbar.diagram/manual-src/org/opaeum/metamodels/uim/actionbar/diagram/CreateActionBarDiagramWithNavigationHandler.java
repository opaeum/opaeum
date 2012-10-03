package org.opaeum.metamodels.uim.actionbar.diagram;
import org.eclipse.papyrus.infra.gmfdiag.navigation.CreateDiagramWithNavigationHandler;


public class CreateActionBarDiagramWithNavigationHandler extends CreateDiagramWithNavigationHandler {

	public CreateActionBarDiagramWithNavigationHandler() {
		super(new CreateActionBarDiagramCommand(), new ActionBarCreationCondition());
	}

}
