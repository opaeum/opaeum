package org.opaeum.topcased.propertysections;

import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Property;

public class SecondEndSubsettedPropertiesSection extends PropertySubsettedPropertySection{

	@Override
	protected Property getProperty(){
		return ((Association)getEObject()).getMemberEnds().get(1);
	}
}
