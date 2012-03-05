/**
 */
package org.opaeum.uim.constraint;

import org.opaeum.uim.UmlReference;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Required State</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.constraint.RequiredState#getConstraint <em>Constraint</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.constraint.ConstraintPackage#getRequiredState()
 * @model
 * @generated
 */
public interface RequiredState extends UmlReference {
	/**
	 * Returns the value of the '<em><b>Constraint</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.constraint.RootUserInteractionConstraint#getRequiredStates <em>Required States</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Constraint</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Constraint</em>' container reference.
	 * @see #setConstraint(RootUserInteractionConstraint)
	 * @see org.opaeum.uim.constraint.ConstraintPackage#getRequiredState_Constraint()
	 * @see org.opaeum.uim.constraint.RootUserInteractionConstraint#getRequiredStates
	 * @model opposite="requiredStates" required="true" transient="false"
	 * @generated
	 */
	RootUserInteractionConstraint getConstraint();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.constraint.RequiredState#getConstraint <em>Constraint</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Constraint</em>' container reference.
	 * @see #getConstraint()
	 * @generated
	 */
	void setConstraint(RootUserInteractionConstraint value);

} // RequiredState
