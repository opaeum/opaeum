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
 *   <li>{@link org.opaeum.uim.action.OperationButton#getPopup <em>Popup</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.action.ActionPackage#getOperationButton()
 * @model
 * @generated
 */
public interface OperationButton extends UimAction, UmlReference {

	/**
	 * Returns the value of the '<em><b>Popup</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.action.OperationPopup#getOperationAction <em>Operation Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Popup</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Popup</em>' containment reference.
	 * @see #setPopup(OperationPopup)
	 * @see org.opaeum.uim.action.ActionPackage#getOperationButton_Popup()
	 * @see org.opaeum.uim.action.OperationPopup#getOperationAction
	 * @model opposite="operationAction" containment="true"
	 * @generated
	 */
	OperationPopup getPopup();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.action.OperationButton#getPopup <em>Popup</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Popup</em>' containment reference.
	 * @see #getPopup()
	 * @generated
	 */
	void setPopup(OperationPopup value);
} // OperationButton
