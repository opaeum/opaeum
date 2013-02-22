package org.opaeum.uim.userinteractionproperties.core;


import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.SetCommand;
import org.opaeum.uim.constraint.ConstraintFactory;
import org.opaeum.uim.constraint.UserInteractionConstraint;

public abstract class AbstractRequiredRolesSection extends CommonRequiredRolesSection{
	protected abstract EReference getConstraintFeature();
	@Override
	protected EObject createFeatureOwner(EObject currentObject,CompoundCommand cc){
		UserInteractionConstraint uic = ConstraintFactory.eINSTANCE.createUserInteractionConstraint();
		cc.append(SetCommand.create(getEditingDomain(), currentObject, getConstraintFeature(), uic));
		return uic;
	}
}
