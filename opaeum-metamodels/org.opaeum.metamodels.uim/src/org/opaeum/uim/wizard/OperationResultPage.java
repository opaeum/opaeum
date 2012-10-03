/**
 */
package org.opaeum.uim.wizard;

import org.opaeum.uim.Page;
import org.opaeum.uim.model.OperationInvocationWizard;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operation Result Page</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.wizard.OperationResultPage#getWizard <em>Wizard</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.wizard.WizardPackage#getOperationResultPage()
 * @model
 * @generated
 */
public interface OperationResultPage extends Page {
	/**
	 * Returns the value of the '<em><b>Wizard</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.model.OperationInvocationWizard#getResultPage <em>Result Page</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Wizard</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Wizard</em>' container reference.
	 * @see #setWizard(OperationInvocationWizard)
	 * @see org.opaeum.uim.wizard.WizardPackage#getOperationResultPage_Wizard()
	 * @see org.opaeum.uim.model.OperationInvocationWizard#getResultPage
	 * @model opposite="resultPage" transient="false"
	 * @generated
	 */
	OperationInvocationWizard getWizard();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.wizard.OperationResultPage#getWizard <em>Wizard</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Wizard</em>' container reference.
	 * @see #getWizard()
	 * @generated
	 */
	void setWizard(OperationInvocationWizard value);

} // OperationResultPage
