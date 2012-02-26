package org.opaeum.topcased.propertysections.property;

import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Property;

public class FirstEndSubsettedPropertiesSection extends PropertySubsettedPropertySection{

	@Override
	protected Property getProperty(){
		return ((Association)getEObject()).getMemberEnds().get(0);
	}
}
