package org.opaeum.uim.userinteractionproperties.sections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.opaeum.uim.constraint.ConstrainedObject;
import org.opaeum.uim.constraint.ConstraintPackage;
import org.opaeum.uim.userinteractionproperties.common.AbstractRequiredRolesSection;

public class VisibilityRequiredRolesSection extends AbstractRequiredRolesSection{
	@Override
	protected EReference getConstraintContainingFeature(){
		return ConstraintPackage.eINSTANCE.getConstrainedObject_Visibility();
	}
	protected EReference getFeature(){
		return ConstraintPackage.eINSTANCE.getRootUserInteractionConstraint_RequiredRoles();
	}
	@Override
	protected EObject getFeatureOwner(EObject e){
		return ((ConstrainedObject)e).getVisibility();
	}
}
