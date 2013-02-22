package org.opaeum.uim.userinteractionproperties.core;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.opaeum.eclipse.uml.propertysections.base.AbstractMultiFeaturePropertySection;
import org.opaeum.eclipse.uml.propertysections.subsections.BooleanSubsection;
import org.opaeum.uim.constraint.ConstrainedObject;
import org.opaeum.uim.constraint.ConstraintFactory;
import org.opaeum.uim.constraint.ConstraintPackage;
import org.opaeum.uim.constraint.UserInteractionConstraint;

public abstract class AbstractUserInteractionConstraintBooleanFeaturesSection extends AbstractMultiFeaturePropertySection{
	private BooleanSubsection inheritFromParent;
	protected abstract EReference getConstraintContainingFeature();
	private BooleanSubsection requiresGroupOwnership;
	private BooleanSubsection requiresOwnership;
	public AbstractUserInteractionConstraintBooleanFeaturesSection(){
		super();
		inheritFromParent = createBoolean(ConstraintPackage.eINSTANCE.getUserInteractionConstraint_InheritFromParent(), "Inherit from parent", 140);
		inheritFromParent.setDefaultValue(true);
		requiresGroupOwnership = createBoolean(ConstraintPackage.eINSTANCE.getRootUserInteractionConstraint_RequiresGroupOwnership(), "Requires Group Ownership",
				180);
		requiresGroupOwnership.setDefaultValue(true);
		requiresOwnership = createBoolean(ConstraintPackage.eINSTANCE.getRootUserInteractionConstraint_RequiresOwnership(), "Requires User Ownership", 180);
	}
	@Override
	public BooleanSubsection createBoolean(EStructuralFeature feature,String labelText,int labelWidth){
		BooleanSubsection result = new BooleanSubsection(this){
			@Override
			protected Command buildCommand(EObject selection,EObject featureOwner){
				if(featureOwner == null){
					featureOwner = ConstraintFactory.eINSTANCE.createUserInteractionConstraint();
					Command cmd = SetCommand.create(getEditingDomain(), selection, getConstraintContainingFeature(), featureOwner);
					getEditingDomain().getCommandStack().execute(cmd);
				}
				return super.buildCommand(selection, featureOwner);
			}
		};
		populateSubsection(result, feature, labelText, labelWidth, 25);
		result.setDefaultValue(false);
		return result;
	}
	@Override
	public UserInteractionConstraint getFeatureOwner(EObject e){
		return (UserInteractionConstraint) ((ConstrainedObject) e).eGet(getConstraintContainingFeature());
	}
	@Override
	public String getLabelText(){
		return "Rules";
	}
}