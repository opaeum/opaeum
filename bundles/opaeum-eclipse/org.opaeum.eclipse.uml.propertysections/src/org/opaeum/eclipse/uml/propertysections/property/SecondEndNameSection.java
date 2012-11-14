package org.opaeum.eclipse.uml.propertysections.property;

import org.eclipse.emf.ecore.EObject;
import org.opaeum.eclipse.EmfAssociationUtil;
import org.opaeum.eclipse.uml.propertysections.core.NamedElementNameSection;

public class SecondEndNameSection extends NamedElementNameSection{
	@Override
	public EObject getFeatureOwner(EObject selection){
		return EmfAssociationUtil.getSecondEnd(selection);
	}
}
