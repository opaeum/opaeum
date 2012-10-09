package org.opaeum.eclipse.uml.propertysections.property;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Association;
import org.opaeum.eclipse.EmfAssociationUtil;

public class SecondEndBooleanPropertiesSection extends PropertyBooleanFeaturesSection{
	@Override
	public EObject getFeatureOwner(EObject selection){
		return EmfAssociationUtil.getSecondEnd(selection);
	}
}
