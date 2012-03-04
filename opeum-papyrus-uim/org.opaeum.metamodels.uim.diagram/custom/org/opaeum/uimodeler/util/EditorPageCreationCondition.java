package org.opaeum.uimodeler.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.infra.core.extension.commands.PerspectiveContextDependence;
import org.opaeum.uim.editor.EditorPage;

public class EditorPageCreationCondition extends PerspectiveContextDependence{
	public boolean create(EObject selectedElement){
		if(super.create(selectedElement)){
			return selectedElement instanceof EditorPage;
		}
		return false;
	}
}
