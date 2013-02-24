package org.opaeum.uim.userinteractionproperties.uml;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.uml2.uml.Element;
import org.opaeum.uim.constraint.UserInteractionConstraint;
import org.opaeum.uim.perspective.MultiplicityElementNavigationConstraint;
import org.opaeum.uim.perspective.PerspectiveFactory;
import org.opaeum.uim.perspective.PerspectivePackage;
import org.opaeum.uimodeler.common.IUIElementMapMap;

public class SecurityOnEditBooleanFeaturesSection extends NavigableElementConstraintBooleanFeaturesSection{
	@Override
	protected EReference getConstraintContainingFeature(){
		return PerspectivePackage.eINSTANCE.getMultiplicityElementNavigationConstraint_RemoveConstraint();
	}
	@Override
	public UserInteractionConstraint getFeatureOwner(EObject e){
		MultiplicityElementNavigationConstraint c = getConstraintContainer(e);
		return c.getRemoveConstraint();
	}
	public MultiplicityElementNavigationConstraint getConstraintContainer(EObject e){
		IUIElementMapMap explorerMap = (IUIElementMapMap) e.eResource().getResourceSet();
		MultiplicityElementNavigationConstraint c = (MultiplicityElementNavigationConstraint) explorerMap.getElementFor((Element) e);
		return c;
	}
	protected UserInteractionConstraint createConstraint(){
		return PerspectiveFactory.eINSTANCE.createNavigationConstraint();
	}

}
