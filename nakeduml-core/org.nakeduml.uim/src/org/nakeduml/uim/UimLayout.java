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
 * A representation of the model object '<em><b>Layout</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.nakeduml.uim.UimLayout#getParent <em>Parent</em>}</li>
 *   <li>{@link org.nakeduml.uim.UimLayout#getChildren <em>Children</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.nakeduml.uim.UimPackage#getUimLayout()
 * @model
 * @generated
 */
public interface UimLayout extends UimContainer {
	/**
	 * Returns the value of the '<em><b>Parent</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.nakeduml.uim.LayoutContainer#getLayout <em>Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent</em>' container reference.
	 * @see #setParent(LayoutContainer)
	 * @see org.nakeduml.uim.UimPackage#getUimLayout_Parent()
	 * @see org.nakeduml.uim.LayoutContainer#getLayout
	 * @model opposite="layout" transient="false"
	 * @generated
	 */
	LayoutContainer getParent();

	/**
	 * Sets the value of the '{@link org.nakeduml.uim.UimLayout#getParent <em>Parent</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent</em>' container reference.
	 * @see #getParent()
	 * @generated
	 */
	void setParent(LayoutContainer value);

	/**
	 * Returns the value of the '<em><b>Children</b></em>' containment reference list.
	 * The list contents are of type {@link org.nakeduml.uim.OutlayableComponent}.
	 * It is bidirectional and its opposite is '{@link org.nakeduml.uim.OutlayableComponent#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Children</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Children</em>' containment reference list.
	 * @see org.nakeduml.uim.UimPackage#getUimLayout_Children()
	 * @see org.nakeduml.uim.OutlayableComponent#getParent
	 * @model opposite="parent" containment="true"
	 * @generated
	 */
	EList<OutlayableComponent> getChildren();

} // UimLayout