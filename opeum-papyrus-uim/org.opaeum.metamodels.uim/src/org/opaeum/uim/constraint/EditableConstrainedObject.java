/**
 */
package org.opaeum.uim.constraint;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Editable Constrained Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.constraint.EditableConstrainedObject#getEditability <em>Editability</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.constraint.ConstraintPackage#getEditableConstrainedObject()
 * @model
 * @generated
 */
public interface EditableConstrainedObject extends ConstrainedObject {
	/**
	 * Returns the value of the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Editability</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Editability</em>' containment reference.
	 * @see #setEditability(UserInteractionConstraint)
	 * @see org.opaeum.uim.constraint.ConstraintPackage#getEditableConstrainedObject_Editability()
	 * @model containment="true"
	 * @generated
	 */
	UserInteractionConstraint getEditability();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.constraint.EditableConstrainedObject#getEditability <em>Editability</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Editability</em>' containment reference.
	 * @see #getEditability()
	 * @generated
	 */
	void setEditability(UserInteractionConstraint value);

} // EditableConstrainedObject
