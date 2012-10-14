package org.opaeum.uim.userinteractionproperties.sections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.opaeum.uim.constraint.ConstraintPackage;
import org.opaeum.uim.constraint.EditableConstrainedObject;
import org.opaeum.uim.userinteractionproperties.core.AbstractRequiredRolesSection;

public class EditabilityRequiredRolesSection extends AbstractRequiredRolesSection{
	@Override
	protected EReference getConstraintFeature(){
		return ConstraintPackage.eINSTANCE.getEditableConstrainedObject_Editability();
	}
	protected EReference getFeature(){
		return ConstraintPackage.eINSTANCE.getRootUserInteractionConstraint_RequiredRoles();
	}
	@Override
	protected EObject getFeatureOwner(EObject e){
		return ((EditableConstrainedObject)e).getEditability();
	}
}
