package org.opaeum.uim.userinteractionproperties.common;


import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.SetCommand;
import org.opaeum.uim.constraint.ConstraintFactory;
import org.opaeum.uim.constraint.UserInteractionConstraint;

public abstract class AbstractRequiredRolesSection extends CommonRequiredRolesSection{
	protected abstract EReference getConstraintContainingFeature();
	@Override
	protected EObject createFeatureOwner(EObject currentObject,CompoundCommand cc){
		UserInteractionConstraint uic = createConstraint();
		cc.append(SetCommand.create(getEditingDomain(), getConstraintContainer(currentObject), getConstraintContainingFeature(), uic));
		return uic;
	}
	protected UserInteractionConstraint createConstraint(){
		return ConstraintFactory.eINSTANCE.createUserInteractionConstraint();
	}
	public EObject getConstraintContainer(EObject currentObject){
		return currentObject;
	}
}
