/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tab Panel</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.nakeduml.uim.UIMTabPanel#getActiveTabIndex <em>Active Tab Index</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.nakeduml.uim.UIMPackage#getUIMTabPanel()
 * @model
 * @generated
 */
public interface UIMTabPanel extends UIMContainer {
	/**
	 * Returns the value of the '<em><b>Active Tab Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Active Tab Index</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Active Tab Index</em>' attribute.
	 * @see #setActiveTabIndex(int)
	 * @see org.nakeduml.uim.UIMPackage#getUIMTabPanel_ActiveTabIndex()
	 * @model dataType="org.eclipse.uml2.uml.Integer"
	 * @generated
	 */
	int getActiveTabIndex();

	/**
	 * Sets the value of the '{@link org.nakeduml.uim.UIMTabPanel#getActiveTabIndex <em>Active Tab Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Active Tab Index</em>' attribute.
	 * @see #getActiveTabIndex()
	 * @generated
	 */
	void setActiveTabIndex(int value);

} // UIMTabPanel
