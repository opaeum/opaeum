package org.opaeum.uim.userinteractionproperties.sections;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.opaeum.uim.constraint.ConstraintPackage;
import org.topcased.tabbedproperties.sections.AbstractBooleanPropertySection;


public class UserInteractionConstraintInheritFromParentSection extends AbstractBooleanPropertySection{

	protected String getLabelText(){
		return "InheritFromParent";
	}

	protected EStructuralFeature getFeature(){
		return ConstraintPackage.eINSTANCE.getUserInteractionConstraint_InheritFromParent();
	}
}