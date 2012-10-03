package org.opaeum.uimodeler.perspective.diagram;


import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.infra.core.extension.commands.PerspectiveContextDependence;
import org.opaeum.uim.perspective.PerspectiveConfiguration;

public class PerspectiveCreationCondition extends PerspectiveContextDependence{
	public boolean create(EObject selectedElement){
		if(super.create(selectedElement)){
			return selectedElement instanceof PerspectiveConfiguration;
		}
		return false;
	}
}
