/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim.security;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.nakeduml.uim.security.SecurityConstraint#isInheritFromParent <em>Inherit From Parent</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.nakeduml.uim.security.SecurityPackage#getSecurityConstraint()
 * @model
 * @generated
 */
public interface SecurityConstraint extends WorkspaceSecurityConstraint {
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
	 * @see org.nakeduml.uim.security.SecurityPackage#getSecurityConstraint_InheritFromParent()
	 * @model default="true"
	 * @generated
	 */
	boolean isInheritFromParent();

	/**
	 * Sets the value of the '{@link org.nakeduml.uim.security.SecurityConstraint#isInheritFromParent <em>Inherit From Parent</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inherit From Parent</em>' attribute.
	 * @see #isInheritFromParent()
	 * @generated
	 */
	void setInheritFromParent(boolean value);

} // SecurityConstraint
