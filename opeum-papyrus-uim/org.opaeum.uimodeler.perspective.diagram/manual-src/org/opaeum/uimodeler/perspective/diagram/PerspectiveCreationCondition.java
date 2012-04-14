package org.opaeum.uimodeler.perspective.diagram;


import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.infra.core.extension.commands.PerspectiveContextDependence;
import org.opaeum.uim.UserInterface;
import org.opaeum.uim.cube.CubeQuery;
import org.opaeum.uim.perspective.UimPerspective;

public class PerspectiveCreationCondition extends PerspectiveContextDependence{
	public boolean create(EObject selectedElement){
		if(super.create(selectedElement)){
			return selectedElement instanceof UimPerspective;
		}
		return false;
	}
}
