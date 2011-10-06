/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opeum.uim.security;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Secure Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opeum.uim.security.SecureObject#getVisibility <em>Visibility</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opeum.uim.security.SecurityPackage#getSecureObject()
 * @model
 * @generated
 */
public interface SecureObject extends EObject {
	/**
	 * Returns the value of the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Visibility</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Visibility</em>' containment reference.
	 * @see #setVisibility(SecurityConstraint)
	 * @see org.opeum.uim.security.SecurityPackage#getSecureObject_Visibility()
	 * @model containment="true"
	 * @generated
	 */
	SecurityConstraint getVisibility();

	/**
	 * Sets the value of the '{@link org.opeum.uim.security.SecureObject#getVisibility <em>Visibility</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Visibility</em>' containment reference.
	 * @see #getVisibility()
	 * @generated
	 */
	void setVisibility(SecurityConstraint value);

} // SecureObject
