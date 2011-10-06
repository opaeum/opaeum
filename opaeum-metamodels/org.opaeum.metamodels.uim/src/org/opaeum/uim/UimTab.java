/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opaeum.uim;

import org.opaeum.uim.layout.LayoutContainer;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tab</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.UimTab#getParent <em>Parent</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.UimPackage#getUimTab()
 * @model
 * @generated
 */
public interface UimTab extends LayoutContainer {
	/**
	 * Returns the value of the '<em><b>Parent</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.UimTabPanel#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent</em>' container reference.
	 * @see #setParent(UimTabPanel)
	 * @see org.opaeum.uim.UimPackage#getUimTab_Parent()
	 * @see org.opaeum.uim.UimTabPanel#getChildren
	 * @model opposite="children" required="true" transient="false"
	 * @generated
	 */
	UimTabPanel getParent();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.UimTab#getParent <em>Parent</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent</em>' container reference.
	 * @see #getParent()
	 * @generated
	 */
	void setParent(UimTabPanel value);

} // UimTab
