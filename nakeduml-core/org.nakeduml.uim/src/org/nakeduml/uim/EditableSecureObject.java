/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Editable Secure Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.nakeduml.uim.EditableSecureObject#getEditability <em>Editability</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.nakeduml.uim.UimPackage#getEditableSecureObject()
 * @model
 * @generated
 */
public interface EditableSecureObject extends SecureObject {
	/**
	 * Returns the value of the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Editability</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Editability</em>' containment reference.
	 * @see #setEditability(SecurityConstraint)
	 * @see org.nakeduml.uim.UimPackage#getEditableSecureObject_Editability()
	 * @model containment="true"
	 * @generated
	 */
	SecurityConstraint getEditability();

	/**
	 * Sets the value of the '{@link org.nakeduml.uim.EditableSecureObject#getEditability <em>Editability</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Editability</em>' containment reference.
	 * @see #getEditability()
	 * @generated
	 */
	void setEditability(SecurityConstraint value);

} // EditableSecureObject
