package org.opaeum.uimodeler.cubequery.diagram;


import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.infra.core.extension.commands.PerspectiveContextDependence;
import org.opaeum.uim.cube.CubeQuery;

public class CubeQueryCreationCondition extends PerspectiveContextDependence{
	public boolean create(EObject selectedElement){
		if(super.create(selectedElement)){
			return selectedElement instanceof CubeQuery;
		}
		return false;
	}
}
