package org.opaeum.eclipse.uml.propertysections.property;

import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Property;
import org.opaeum.eclipse.uml.propertysections.core.MultiplicityElementFeaturesSection;

public class FirstEndMultiplicityElemementFeaturesSection extends MultiplicityElementFeaturesSection{

	@Override
	protected Property getProperty(){
		Association a=(Association) getEObject();
		return a.getMemberEnds().get(0);
	}
}
