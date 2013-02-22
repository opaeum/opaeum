package org.opaeum.uim.userinteractionproperties.navigation;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.uml2.uml.Element;
import org.opaeum.uim.perspective.ExplorerClassConstraint;
import org.opaeum.uim.perspective.ExplorerConstraint;
import org.opaeum.uim.userinteractionproperties.core.AbstractUserInteractionConstraintBooleanFeaturesSection;
import org.opaeum.uimodeler.common.IExplorerMap;

public class NavigableElementConstraintBooleanFeaturesSection extends AbstractUserInteractionConstraintBooleanFeaturesSection{
	@Override
	protected EReference getConstraintContainingFeature(){
		throw new IllegalStateException();
	}
	@Override
	public ExplorerConstraint getFeatureOwner(EObject nextObject){
		return ((IExplorerMap)nextObject.eResource().getResourceSet()).getConstraintFor((Element)nextObject);
	}

}
