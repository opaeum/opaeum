package org.opaeum.uim.userinteractionproperties.sections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.opaeum.uim.constraint.ConstrainedObject;
import org.opaeum.uim.constraint.ConstraintPackage;
import org.opaeum.uim.userinteractionproperties.common.AbstractRequiredStatesSection;

public class VisibilityRequiredStatesSection extends AbstractRequiredStatesSection{
	@Override
	protected EReference getConstraintContainingFeature(){
		return ConstraintPackage.eINSTANCE.getConstrainedObject_Visibility();
	}
	protected EReference getFeature(){
		return ConstraintPackage.eINSTANCE.getRootUserInteractionConstraint_RequiredStates();
	}
	@Override
	protected EObject getFeatureOwner(EObject e){
		return ((ConstrainedObject)e).getVisibility();
	}
}
