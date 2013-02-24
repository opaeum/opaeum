package org.opaeum.uim.userinteractionproperties.sections;

import org.eclipse.emf.ecore.EReference;
import org.opaeum.uim.constraint.ConstraintPackage;
import org.opaeum.uim.userinteractionproperties.common.AbstractUserInteractionConstraintBooleanFeaturesSection;

public class VisibilityUserInteractionConstraintBooleanFeaturesSection extends AbstractUserInteractionConstraintBooleanFeaturesSection{
	@Override
	protected EReference getConstraintContainingFeature(){
		EReference v = ConstraintPackage.eINSTANCE.getConstrainedObject_Visibility();
		return v;
	}
}
