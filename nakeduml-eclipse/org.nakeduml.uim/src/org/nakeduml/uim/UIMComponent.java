/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.nakeduml.uim.UIMComponent#getSecurityOnVisibility <em>Security On Visibility</em>}</li>
 *   <li>{@link org.nakeduml.uim.UIMComponent#getParent <em>Parent</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.nakeduml.uim.UIMPackage#getUIMComponent()
 * @model abstract="true"
 * @generated
 */
public interface UIMComponent extends UserInteractionElement {
	/**
	 * Returns the value of the '<em><b>Security On Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Security On Visibility</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Security On Visibility</em>' containment reference.
	 * @see #setSecurityOnVisibility(ChildSecurityConstraint)
	 * @see org.nakeduml.uim.UIMPackage#getUIMComponent_SecurityOnVisibility()
	 * @model containment="true"
	 * @generated
	 */
	ChildSecurityConstraint getSecurityOnVisibility();

	/**
	 * Sets the value of the '{@link org.nakeduml.uim.UIMComponent#getSecurityOnVisibility <em>Security On Visibility</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Security On Visibility</em>' containment reference.
	 * @see #getSecurityOnVisibility()
	 * @generated
	 */
	void setSecurityOnVisibility(ChildSecurityConstraint value);

	/**
	 * Returns the value of the '<em><b>Parent</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.nakeduml.uim.UIMContainer#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent</em>' container reference.
	 * @see #setParent(UIMContainer)
	 * @see org.nakeduml.uim.UIMPackage#getUIMComponent_Parent()
	 * @see org.nakeduml.uim.UIMContainer#getChildren
	 * @model opposite="children" transient="false"
	 * @generated
	 */
	UIMContainer getParent();

	/**
	 * Sets the value of the '{@link org.nakeduml.uim.UIMComponent#getParent <em>Parent</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent</em>' container reference.
	 * @see #getParent()
	 * @generated
	 */
	void setParent(UIMContainer value);

} // UIMComponent
