/**
 */
package org.opaeum.uim;

import org.opaeum.uim.panel.AbstractPanel;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>User Interface</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.UserInterface#getPanel <em>Panel</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.UimPackage#getUserInterface()
 * @model
 * @generated
 */
public interface UserInterface extends UserInteractionElement {
	/**
	 * Returns the value of the '<em><b>Panel</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Panel</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Panel</em>' containment reference.
	 * @see #setPanel(AbstractPanel)
	 * @see org.opaeum.uim.UimPackage#getUserInterface_Panel()
	 * @model containment="true" required="true"
	 * @generated
	 */
	AbstractPanel getPanel();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.UserInterface#getPanel <em>Panel</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Panel</em>' containment reference.
	 * @see #getPanel()
	 * @generated
	 */
	void setPanel(AbstractPanel value);

} // UserInterface
