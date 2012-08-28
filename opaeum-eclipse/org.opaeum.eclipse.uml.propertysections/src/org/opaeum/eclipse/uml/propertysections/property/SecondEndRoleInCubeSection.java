package org.opaeum.eclipse.uml.propertysections.property;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.TypedElement;

public class SecondEndRoleInCubeSection extends PropertyRoleInCubeSection{
	@Override
	protected TypedElement getTypedElementFrom(EObject object){
		Association ass = (Association) object;
		if(ass.getMemberEnds().size() >= 2){
			return ass.getMemberEnds().get(1);
		}else{
			return null;
		}
	}
}
