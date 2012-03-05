/**
 */
package org.opaeum.uim.action;

import org.opaeum.uim.UmlReference;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operation Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An operation action is typically represented as a button and executes a parameterless operation. Most often used to transition in statemachines. It is made available as a single-click button action which allows the user to execute the operation without navigating to an input form, as would be the case with a OperationNavigation
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.action.OperationAction#getPopup <em>Popup</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.action.ActionPackage#getOperationAction()
 * @model
 * @generated
 */
public interface OperationAction extends UimAction, UmlReference {

	/**
	 * Returns the value of the '<em><b>Popup</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.action.OperationActionPopup#getOperationAction <em>Operation Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Popup</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Popup</em>' reference.
	 * @see #setPopup(OperationActionPopup)
	 * @see org.opaeum.uim.action.ActionPackage#getOperationAction_Popup()
	 * @see org.opaeum.uim.action.OperationActionPopup#getOperationAction
	 * @model opposite="operationAction"
	 * @generated
	 */
	OperationActionPopup getPopup();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.action.OperationAction#getPopup <em>Popup</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Popup</em>' reference.
	 * @see #getPopup()
	 * @generated
	 */
	void setPopup(OperationActionPopup value);
} // OperationAction
