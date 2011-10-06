/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opeum.uim.layout;

import org.opeum.uim.UimContainer;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opeum.uim.layout.LayoutContainer#getLayout <em>Layout</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opeum.uim.layout.LayoutPackage#getLayoutContainer()
 * @model abstract="true"
 * @generated
 */
public interface LayoutContainer extends UimContainer {
	/**
	 * Returns the value of the '<em><b>Layout</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.opeum.uim.layout.UimLayout#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Layout</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Layout</em>' containment reference.
	 * @see #setLayout(UimLayout)
	 * @see org.opeum.uim.layout.LayoutPackage#getLayoutContainer_Layout()
	 * @see org.opeum.uim.layout.UimLayout#getParent
	 * @model opposite="parent" containment="true"
	 * @generated
	 */
	UimLayout getLayout();

	/**
	 * Sets the value of the '{@link org.opeum.uim.layout.LayoutContainer#getLayout <em>Layout</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Layout</em>' containment reference.
	 * @see #getLayout()
	 * @generated
	 */
	void setLayout(UimLayout value);

} // LayoutContainer
