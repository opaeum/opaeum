package org.opaeum.topcased.propertysections.property;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Property;

public class SecondEndRedefinedPropertySection extends PropertyRedefinedPropertySection{

	@Override
	protected EObject getFeatureOwner(EObject e){
		return ((Association)e).getMemberEnds().get(1);
	}
}
