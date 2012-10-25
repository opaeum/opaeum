package org.opaeum.metamodels.uim.actionbar.diagram;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.infra.core.extension.commands.PerspectiveContextDependence;

public class ActionBarCreationCondition extends PerspectiveContextDependence{
	public boolean create(EObject selectedElement){
		return true;
	}
}
