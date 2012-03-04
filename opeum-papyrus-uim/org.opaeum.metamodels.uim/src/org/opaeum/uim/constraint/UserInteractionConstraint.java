/**
 */
package org.opaeum.uim.constraint;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>User Interaction Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.constraint.UserInteractionConstraint#isInheritFromParent <em>Inherit From Parent</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.constraint.ConstraintPackage#getUserInteractionConstraint()
 * @model
 * @generated
 */
public interface UserInteractionConstraint extends RootUserInteractionConstraint {
	/**
	 * Returns the value of the '<em><b>Inherit From Parent</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inherit From Parent</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inherit From Parent</em>' attribute.
	 * @see #setInheritFromParent(boolean)
	 * @see org.opaeum.uim.constraint.ConstraintPackage#getUserInteractionConstraint_InheritFromParent()
	 * @model default="true"
	 * @generated
	 */
	boolean isInheritFromParent();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.constraint.UserInteractionConstraint#isInheritFromParent <em>Inherit From Parent</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inherit From Parent</em>' attribute.
	 * @see #isInheritFromParent()
	 * @generated
	 */
	void setInheritFromParent(boolean value);

} // UserInteractionConstraint
