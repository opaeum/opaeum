package org.opaeum.uim.userinteractionproperties.common;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
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
	protected BooleanSubsection inheritFromParent;
	protected BooleanSubsection requiresGroupOwnership;
	protected BooleanSubsection requiresOwnership;
	protected abstract EReference getConstraintContainingFeature();
	public AbstractUserInteractionConstraintBooleanFeaturesSection(){
		super();
		createBooleanSubsections();
	}
	protected UserInteractionConstraint createConstraint(){
		return ConstraintFactory.eINSTANCE.createUserInteractionConstraint();
	}
	protected void createBooleanSubsections(){
		inheritFromParent = createBoolean(ConstraintPackage.eINSTANCE.getUserInteractionConstraint_InheritFromParent(), "Inherit from parent", 140);
		inheritFromParent.setDefaultValue(false);
		requiresGroupOwnership = createBoolean(ConstraintPackage.eINSTANCE.getRootUserInteractionConstraint_RequiresGroupOwnership(), "Requires Group Ownership",
				180);
		requiresGroupOwnership.setDefaultValue(true);
		requiresOwnership = createBoolean(ConstraintPackage.eINSTANCE.getRootUserInteractionConstraint_RequiresOwnership(), "Requires User Ownership", 180);
	}
	@Override
	protected void handleModelChanged(Notification msg){
		super.handleModelChanged(msg);
		if(msg.getFeatureID(UserInteractionConstraint.class) == ConstraintPackage.USER_INTERACTION_CONSTRAINT__INHERIT_FROM_PARENT
				&& msg.getNotifier().equals(getFeatureOwner(getSelectedObject()))){
			isRefreshing = true;
			populateControls();
			isRefreshing = false;
		}
	}
	public void populateControls(){
		super.populateControls();
		if(getFeatureOwner(getSelectedObject()) != null){
			UserInteractionConstraint uic = (UserInteractionConstraint) getFeatureOwner(getSelectedObject());
			requiresGroupOwnership.setEnabled(!uic.isInheritFromParent());
			requiresOwnership.setEnabled(!uic.isInheritFromParent());
		}
	}
	@Override
	public BooleanSubsection createBoolean(EStructuralFeature feature,String labelText,int labelWidth){
		BooleanSubsection result = new BooleanSubsection(this){
			@Override
			protected Command buildCommand(EObject selection,EObject featureOwner){
				EObject constraintContainer = getConstraintContainer(selection);
				if(constraintContainer!=null && featureOwner == null){
					Command cmd = SetCommand.create(getEditingDomain(), constraintContainer, getConstraintContainingFeature(), featureOwner=createConstraint());
					getEditingDomain().getCommandStack().execute(cmd);
					hookModelListener();
					AbstractUserInteractionConstraintBooleanFeaturesSection.this.addListener();
				}
				return super.buildCommand(selection, featureOwner);
			}
		};
		populateSubsection(result, feature, labelText, labelWidth, 25);
		result.setDefaultValue(false);
		return result;
	}
	protected EObject getConstraintContainer(EObject selection){
		return selection;
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