/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.nakeduml.uim.UIMContainer#getSecurityOnEditability <em>Security On Editability</em>}</li>
 *   <li>{@link org.nakeduml.uim.UIMContainer#getChildren <em>Children</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.nakeduml.uim.UIMPackage#getUIMContainer()
 * @model
 * @generated
 */
public interface UIMContainer extends UIMComponent {
	/**
	 * Returns the value of the '<em><b>Security On Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Security On Editability</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Security On Editability</em>' containment reference.
	 * @see #setSecurityOnEditability(ChildSecurityConstraint)
	 * @see org.nakeduml.uim.UIMPackage#getUIMContainer_SecurityOnEditability()
	 * @model containment="true"
	 * @generated
	 */
	ChildSecurityConstraint getSecurityOnEditability();

	/**
	 * Sets the value of the '{@link org.nakeduml.uim.UIMContainer#getSecurityOnEditability <em>Security On Editability</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Security On Editability</em>' containment reference.
	 * @see #getSecurityOnEditability()
	 * @generated
	 */
	void setSecurityOnEditability(ChildSecurityConstraint value);

	/**
	 * Returns the value of the '<em><b>Children</b></em>' containment reference list.
	 * The list contents are of type {@link org.nakeduml.uim.UIMComponent}.
	 * It is bidirectional and its opposite is '{@link org.nakeduml.uim.UIMComponent#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Children</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Children</em>' containment reference list.
	 * @see org.nakeduml.uim.UIMPackage#getUIMContainer_Children()
	 * @see org.nakeduml.uim.UIMComponent#getParent
	 * @model opposite="parent" containment="true"
	 * @generated
	 */
	EList<UIMComponent> getChildren();

} // UIMContainer
