package org.opaeum.uimodeler.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.infra.core.extension.commands.PerspectiveContextDependence;
import org.opaeum.uim.UserInterface;

public class UserInterfaceCreationCondition extends PerspectiveContextDependence{
	public boolean create(EObject selectedElement){
		if(super.create(selectedElement)){
			return selectedElement instanceof UserInterface;
		}
		return false;
	}
}
