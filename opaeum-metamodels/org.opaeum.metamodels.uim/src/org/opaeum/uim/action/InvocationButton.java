/**
 */
package org.opaeum.uim.action;

import org.opaeum.uim.LabeledElement;
import org.opaeum.uim.wizard.InvocationWizard;
import org.opaeum.uim.model.OperationInvocationWizard;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operation Button</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An operation action is typically represented as a button and executes a parameterless operation. Most often used to transition in statemachines. It is made available as a single-click button action which allows the user to execute the operation without navigating to an input form, as would be the case with a OperationNavigation
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.action.InvocationButton#getPopup <em>Popup</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.action.ActionPackage#getInvocationButton()
 * @model
 * @generated
 */
public interface InvocationButton extends AbstractActionButton, LabeledElement {
	/**
	 * Returns the value of the '<em><b>Popup</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Popup</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Popup</em>' reference.
	 * @see #setPopup(InvocationWizard)
	 * @see org.opaeum.uim.action.ActionPackage#getInvocationButton_Popup()
	 * @model
	 * @generated
	 */
	InvocationWizard getPopup();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.action.InvocationButton#getPopup <em>Popup</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Popup</em>' reference.
	 * @see #getPopup()
	 * @generated
	 */
	void setPopup(InvocationWizard value);

} // OperationButton
