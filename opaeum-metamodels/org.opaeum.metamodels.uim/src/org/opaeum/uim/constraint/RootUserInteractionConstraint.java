/**
 */
package org.opaeum.uim.constraint;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Root User Interaction Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.constraint.RootUserInteractionConstraint#isRequiresGroupOwnership <em>Requires Group Ownership</em>}</li>
 *   <li>{@link org.opaeum.uim.constraint.RootUserInteractionConstraint#isRequiresOwnership <em>Requires Ownership</em>}</li>
 *   <li>{@link org.opaeum.uim.constraint.RootUserInteractionConstraint#getRequiredRoles <em>Required Roles</em>}</li>
 *   <li>{@link org.opaeum.uim.constraint.RootUserInteractionConstraint#getOpenToPublic <em>Open To Public</em>}</li>
 *   <li>{@link org.opaeum.uim.constraint.RootUserInteractionConstraint#getRequiredStates <em>Required States</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.constraint.ConstraintPackage#getRootUserInteractionConstraint()
 * @model
 * @generated
 */
public interface RootUserInteractionConstraint extends EObject {
	/**
	 * Returns the value of the '<em><b>Requires Group Ownership</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Requires Group Ownership</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Requires Group Ownership</em>' attribute.
	 * @see #setRequiresGroupOwnership(boolean)
	 * @see org.opaeum.uim.constraint.ConstraintPackage#getRootUserInteractionConstraint_RequiresGroupOwnership()
	 * @model
	 * @generated
	 */
	boolean isRequiresGroupOwnership();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.constraint.RootUserInteractionConstraint#isRequiresGroupOwnership <em>Requires Group Ownership</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Requires Group Ownership</em>' attribute.
	 * @see #isRequiresGroupOwnership()
	 * @generated
	 */
	void setRequiresGroupOwnership(boolean value);

	/**
	 * Returns the value of the '<em><b>Requires Ownership</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Requires Ownership</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Requires Ownership</em>' attribute.
	 * @see #setRequiresOwnership(boolean)
	 * @see org.opaeum.uim.constraint.ConstraintPackage#getRootUserInteractionConstraint_RequiresOwnership()
	 * @model
	 * @generated
	 */
	boolean isRequiresOwnership();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.constraint.RootUserInteractionConstraint#isRequiresOwnership <em>Requires Ownership</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Requires Ownership</em>' attribute.
	 * @see #isRequiresOwnership()
	 * @generated
	 */
	void setRequiresOwnership(boolean value);

	/**
	 * Returns the value of the '<em><b>Required Roles</b></em>' containment reference list.
	 * The list contents are of type {@link org.opaeum.uim.constraint.RequiredRole}.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.constraint.RequiredRole#getConstraint <em>Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Required Roles</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Required Roles</em>' containment reference list.
	 * @see org.opaeum.uim.constraint.ConstraintPackage#getRootUserInteractionConstraint_RequiredRoles()
	 * @see org.opaeum.uim.constraint.RequiredRole#getConstraint
	 * @model opposite="constraint" containment="true"
	 * @generated
	 */
	EList<RequiredRole> getRequiredRoles();

	/**
	 * Returns the value of the '<em><b>Open To Public</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Open To Public</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Open To Public</em>' attribute.
	 * @see #setOpenToPublic(Boolean)
	 * @see org.opaeum.uim.constraint.ConstraintPackage#getRootUserInteractionConstraint_OpenToPublic()
	 * @model
	 * @generated
	 */
	Boolean getOpenToPublic();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.constraint.RootUserInteractionConstraint#getOpenToPublic <em>Open To Public</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Open To Public</em>' attribute.
	 * @see #getOpenToPublic()
	 * @generated
	 */
	void setOpenToPublic(Boolean value);

	/**
	 * Returns the value of the '<em><b>Required States</b></em>' containment reference list.
	 * The list contents are of type {@link org.opaeum.uim.constraint.RequiredState}.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.constraint.RequiredState#getConstraint <em>Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Required States</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Required States</em>' containment reference list.
	 * @see org.opaeum.uim.constraint.ConstraintPackage#getRootUserInteractionConstraint_RequiredStates()
	 * @see org.opaeum.uim.constraint.RequiredState#getConstraint
	 * @model opposite="constraint" containment="true"
	 * @generated
	 */
	EList<RequiredState> getRequiredStates();

} // RootUserInteractionConstraint
