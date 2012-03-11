package org.opaeum.metamodels.uim.actionbar.diagram;
import org.eclipse.papyrus.infra.gmfdiag.navigation.CreateDiagramWithNavigationHandler;


public class CreateAbstractActionBarDiagramWithNavigationHandler extends CreateDiagramWithNavigationHandler {

	public CreateAbstractActionBarDiagramWithNavigationHandler() {
		super(new CreateAbstractActionBarDiagramCommand(), new AbstractActionBarCreationCondition());
	}

}
