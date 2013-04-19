package org.opaeum.eclipse.uml.propertysections.property;

import org.eclipse.emf.ecore.EObject;
import org.opaeum.eclipse.EmfAssociationUtil;

public class SecondEndDefaultValueSection extends PropertyDefaultValueSection{

	@Override
	protected EObject getFeatureOwner(EObject selection){
		return EmfAssociationUtil.getSecondEnd(selection);
	}
}
