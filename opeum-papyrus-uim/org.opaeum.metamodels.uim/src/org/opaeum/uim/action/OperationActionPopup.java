/**
 */
package org.opaeum.uim.action;

import org.opaeum.uim.UserInterface;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operation Action Popup</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.action.OperationActionPopup#getOperationAction <em>Operation Action</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.action.ActionPackage#getOperationActionPopup()
 * @model
 * @generated
 */
public interface OperationActionPopup extends UserInterface {
	/**
	 * Returns the value of the '<em><b>Operation Action</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.action.OperationAction#getPopup <em>Popup</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operation Action</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operation Action</em>' reference.
	 * @see #setOperationAction(OperationAction)
	 * @see org.opaeum.uim.action.ActionPackage#getOperationActionPopup_OperationAction()
	 * @see org.opaeum.uim.action.OperationAction#getPopup
	 * @model opposite="popup"
	 * @generated
	 */
	OperationAction getOperationAction();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.action.OperationActionPopup#getOperationAction <em>Operation Action</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operation Action</em>' reference.
	 * @see #getOperationAction()
	 * @generated
	 */
	void setOperationAction(OperationAction value);

} // OperationActionPopup
