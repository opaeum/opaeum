package org.opaeum.uim.userinteractionproperties.sections;

import org.eclipse.emf.ecore.EReference;
import org.opaeum.uim.constraint.ConstrainedObject;
import org.opaeum.uim.constraint.ConstraintPackage;
import org.opaeum.uim.constraint.UserInteractionConstraint;

public class VisibilityRequiredRolesSection extends AbstractRequiredRolesSection{
	@Override
	protected EReference getConstraintFeature(){
		return ConstraintPackage.eINSTANCE.getConstrainedObject_Visibility();
	}
	@Override
	protected UserInteractionConstraint getUserInteractionConstraint(){
		return ((ConstrainedObject)getEObject()).getVisibility();
	}
}
