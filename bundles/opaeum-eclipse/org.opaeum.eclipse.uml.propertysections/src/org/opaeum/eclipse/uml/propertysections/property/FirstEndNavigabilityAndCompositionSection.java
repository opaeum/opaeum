package org.opaeum.eclipse.uml.propertysections.property;

import org.eclipse.emf.ecore.EObject;
import org.opaeum.eclipse.EmfAssociationUtil;

public class FirstEndNavigabilityAndCompositionSection extends AssociationEndNavigabilityAndCompositionSection{

	@Override
	public EObject getFeatureOwner(EObject selection){
		return EmfAssociationUtil.getFirstEnd(selection);
	}
}
