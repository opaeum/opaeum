/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opaeum.uim;

import org.eclipse.emf.common.util.EList;
import org.opaeum.uim.layout.OutlayableComponent;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tab Panel</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.UimTabPanel#getChildren <em>Children</em>}</li>
 *   <li>{@link org.opaeum.uim.UimTabPanel#getActiveTabIndex <em>Active Tab Index</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.UimPackage#getUimTabPanel()
 * @model
 * @generated
 */
public interface UimTabPanel extends UimContainer, OutlayableComponent {
	/**
	 * Returns the value of the '<em><b>Children</b></em>' containment reference list.
	 * The list contents are of type {@link org.opaeum.uim.UimTab}.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.UimTab#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Children</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Children</em>' containment reference list.
	 * @see org.opaeum.uim.UimPackage#getUimTabPanel_Children()
	 * @see org.opaeum.uim.UimTab#getParent
	 * @model opposite="parent" containment="true"
	 * @generated
	 */
	EList<UimTab> getChildren();

	/**
	 * Returns the value of the '<em><b>Active Tab Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Active Tab Index</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Active Tab Index</em>' attribute.
	 * @see #setActiveTabIndex(Integer)
	 * @see org.opaeum.uim.UimPackage#getUimTabPanel_ActiveTabIndex()
	 * @model
	 * @generated
	 */
	Integer getActiveTabIndex();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.UimTabPanel#getActiveTabIndex <em>Active Tab Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Active Tab Index</em>' attribute.
	 * @see #getActiveTabIndex()
	 * @generated
	 */
	void setActiveTabIndex(Integer value);

} // UimTabPanel
