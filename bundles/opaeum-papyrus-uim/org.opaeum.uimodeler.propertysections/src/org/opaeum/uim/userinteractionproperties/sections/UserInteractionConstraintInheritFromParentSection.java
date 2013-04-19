package org.opaeum.uim.userinteractionproperties.sections;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.opaeum.eclipse.uml.propertysections.base.AbstractBooleanPropertySection;
import org.opaeum.uim.constraint.ConstraintPackage;


public class UserInteractionConstraintInheritFromParentSection extends AbstractBooleanPropertySection{

	public String getLabelText(){
		return "InheritFromParent";
	}

	protected EStructuralFeature getFeature(){
		return ConstraintPackage.eINSTANCE.getUserInteractionConstraint_InheritFromParent();
	}

	@Override
	protected Boolean getDefaultValue(){
		return true;
	}
}