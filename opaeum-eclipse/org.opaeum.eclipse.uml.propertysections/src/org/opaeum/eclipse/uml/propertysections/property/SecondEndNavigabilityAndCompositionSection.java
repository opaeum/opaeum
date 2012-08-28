package org.opaeum.eclipse.uml.propertysections.property;

import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Property;
import org.opaeum.eclipse.uml.propertysections.core.AssociationEndNavigabilityAndCompositionSection;

public class SecondEndNavigabilityAndCompositionSection extends AssociationEndNavigabilityAndCompositionSection{

	@Override
	protected Property getProperty(){
		Association ass=(Association) getEObject();
		return ass.getMemberEnds().get(1);
	}
}
