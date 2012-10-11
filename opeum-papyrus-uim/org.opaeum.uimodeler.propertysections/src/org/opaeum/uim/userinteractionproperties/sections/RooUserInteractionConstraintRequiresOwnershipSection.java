package org.opaeum.uim.userinteractionproperties.sections;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.opaeum.uim.constraint.ConstraintPackage;
import org.topcased.tabbedproperties.sections.AbstractBooleanPropertySection;

/**
 * A Section used to edit a boolean Feature with a Checkbox.
 *
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class RooUserInteractionConstraintRequiresOwnershipSection extends AbstractBooleanPropertySection{
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.AbstractOpaeumPropertySection#getLabelText()
	 * @generated
	 */
	public String getLabelText(){
		return "RequiresOwnership";
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see org.topcased.tabbedproperties.sections.AbstractOpaeumPropertySection#getFeature()
	 * @generated
	 */
	protected EStructuralFeature getFeature(){
		return ConstraintPackage.eINSTANCE.getRootUserInteractionConstraint_RequiresOwnership();
	}
}