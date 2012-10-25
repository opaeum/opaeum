/**
 */
package org.opaeum.uim.constraint;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Constrained Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.constraint.ConstrainedObject#getVisibility <em>Visibility</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.constraint.ConstraintPackage#getConstrainedObject()
 * @model
 * @generated
 */
public interface ConstrainedObject extends EObject {
	/**
	 * Returns the value of the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Visibility</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Visibility</em>' containment reference.
	 * @see #setVisibility(UserInteractionConstraint)
	 * @see org.opaeum.uim.constraint.ConstraintPackage#getConstrainedObject_Visibility()
	 * @model containment="true"
	 * @generated
	 */
	UserInteractionConstraint getVisibility();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.constraint.ConstrainedObject#getVisibility <em>Visibility</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Visibility</em>' containment reference.
	 * @see #getVisibility()
	 * @generated
	 */
	void setVisibility(UserInteractionConstraint value);

} // ConstrainedObject
