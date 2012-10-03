/**
 */
package org.opaeum.uim.wizard;

import org.opaeum.uim.model.BehaviorUserInteractionModel;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Behavior Invocation Wizard</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.wizard.BehaviorInvocationWizard#getModel <em>Model</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.wizard.WizardPackage#getBehaviorInvocationWizard()
 * @model
 * @generated
 */
public interface BehaviorInvocationWizard extends InvocationWizard {
	/**
	 * Returns the value of the '<em><b>Model</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.model.BehaviorUserInteractionModel#getInvocationWizard <em>Invocation Wizard</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Model</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Model</em>' container reference.
	 * @see #setModel(BehaviorUserInteractionModel)
	 * @see org.opaeum.uim.wizard.WizardPackage#getBehaviorInvocationWizard_Model()
	 * @see org.opaeum.uim.model.BehaviorUserInteractionModel#getInvocationWizard
	 * @model opposite="invocationWizard" transient="false"
	 * @generated
	 */
	BehaviorUserInteractionModel getModel();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.wizard.BehaviorInvocationWizard#getModel <em>Model</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model</em>' container reference.
	 * @see #getModel()
	 * @generated
	 */
	void setModel(BehaviorUserInteractionModel value);

} // BehaviorInvocationWizard
