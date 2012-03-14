package org.opaeum.metamodels.uim.actionbar.diagram;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.infra.core.extension.commands.PerspectiveContextDependence;
import org.opaeum.uim.AbstractActionBar;
import org.opaeum.uim.editor.AbstractEditor;

public class AbstractActionBarCreationCondition extends PerspectiveContextDependence{
	public boolean create(EObject selectedElement){
		System.out.println("AbstractActionBarCreationCondition.create()");
//		if(super.create(selectedElement)){
//			return selectedElement instanceof AbstractEditor;
//		}
		return true;
	}
}
