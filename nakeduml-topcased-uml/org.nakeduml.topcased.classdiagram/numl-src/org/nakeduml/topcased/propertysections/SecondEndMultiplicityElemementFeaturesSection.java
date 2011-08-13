package org.nakeduml.topcased.propertysections;

import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Property;

public class SecondEndMultiplicityElemementFeaturesSection extends MultiplicityElementFeaturesSection{

	@Override
	protected Property getProperty(){
		Association a=(Association) getEObject();
		return a.getMemberEnds().get(1);
	}
}
