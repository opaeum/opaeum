/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Child Security Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.nakeduml.uim.ChildSecurityConstraint#isInheritFromParent <em>Inherit From Parent</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.nakeduml.uim.UIMPackage#getChildSecurityConstraint()
 * @model
 * @generated
 */
public interface ChildSecurityConstraint extends ModelSecurityConstraint {
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
	 * @see org.nakeduml.uim.UIMPackage#getChildSecurityConstraint_InheritFromParent()
	 * @model default="true"
	 * @generated
	 */
	boolean isInheritFromParent();

	/**
	 * Sets the value of the '{@link org.nakeduml.uim.ChildSecurityConstraint#isInheritFromParent <em>Inherit From Parent</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inherit From Parent</em>' attribute.
	 * @see #isInheritFromParent()
	 * @generated
	 */
	void setInheritFromParent(boolean value);

} // ChildSecurityConstraint
