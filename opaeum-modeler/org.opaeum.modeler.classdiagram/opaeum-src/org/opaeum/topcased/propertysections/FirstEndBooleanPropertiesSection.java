package org.opaeum.topcased.propertysections;

import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Property;

public class FirstEndBooleanPropertiesSection extends PropertyBooleanFeaturesSection{

	@Override
	protected Property getProperty(){
		Association ass=(Association) getEObject();
		return ass.getMemberEnds().get(0);
	}
}
