package org.opaeum.eclipse.uml.propertysections.property;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Association;

public class FirstEndSubsettedPropertiesSection extends PropertySubsettedPropertySection{

	@Override
	protected EObject getFeatureOwner(EObject e){
		
		return ((Association)e).getMemberEnds().get(0);
	}
}
