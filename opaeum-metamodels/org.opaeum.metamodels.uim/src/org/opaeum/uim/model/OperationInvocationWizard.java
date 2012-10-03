/**
 */
package org.opaeum.uim.model;

import org.opaeum.uim.wizard.InvocationWizard;
import org.opaeum.uim.wizard.OperationResultPage;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operation Invocation Wizard</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.model.OperationInvocationWizard#getResultPage <em>Result Page</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.model.ModelPackage#getOperationInvocationWizard()
 * @model
 * @generated
 */
public interface OperationInvocationWizard extends InvocationWizard, AbstractUserInteractionModel {
	/**
	 * Returns the value of the '<em><b>Result Page</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.wizard.OperationResultPage#getWizard <em>Wizard</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Result Page</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Result Page</em>' containment reference.
	 * @see #setResultPage(OperationResultPage)
	 * @see org.opaeum.uim.model.ModelPackage#getOperationInvocationWizard_ResultPage()
	 * @see org.opaeum.uim.wizard.OperationResultPage#getWizard
	 * @model opposite="wizard" containment="true"
	 * @generated
	 */
	OperationResultPage getResultPage();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.model.OperationInvocationWizard#getResultPage <em>Result Page</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Result Page</em>' containment reference.
	 * @see #getResultPage()
	 * @generated
	 */
	void setResultPage(OperationResultPage value);

} // OperationInvocationWizard
