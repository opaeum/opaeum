/**
 */
package org.opaeum.uim.wizard;

import org.opaeum.uim.model.ResponsibilityUserInteractionModel;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Responsibility Invocation Wizard</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.wizard.ResponsibilityInvocationWizard#getModel <em>Model</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.wizard.WizardPackage#getResponsibilityInvocationWizard()
 * @model
 * @generated
 */
public interface ResponsibilityInvocationWizard extends InvocationWizard {
	/**
	 * Returns the value of the '<em><b>Model</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.model.ResponsibilityUserInteractionModel#getInvocationWizard <em>Invocation Wizard</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Model</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Model</em>' container reference.
	 * @see #setModel(ResponsibilityUserInteractionModel)
	 * @see org.opaeum.uim.wizard.WizardPackage#getResponsibilityInvocationWizard_Model()
	 * @see org.opaeum.uim.model.ResponsibilityUserInteractionModel#getInvocationWizard
	 * @model opposite="invocationWizard" transient="false"
	 * @generated
	 */
	ResponsibilityUserInteractionModel getModel();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.wizard.ResponsibilityInvocationWizard#getModel <em>Model</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model</em>' container reference.
	 * @see #getModel()
	 * @generated
	 */
	void setModel(ResponsibilityUserInteractionModel value);

} // ResponsibilityInvocationWizard
