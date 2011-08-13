package org.nakeduml.topcased.propertysections;

import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Property;

public class FirstEndRedefinedPropertySection extends PropertyRedefinedPropertySection{

	@Override
	protected Property getProperty(){
		return ((Association)getEObject()).getMemberEnds().get(0);
	}
}
