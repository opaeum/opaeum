/**
 */
package org.opaeum.uim.model;

import org.opaeum.uim.editor.BehaviorExecutionEditor;
import org.opaeum.uim.wizard.BehaviorInvocationWizard;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Behavior User Interaction Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.model.BehaviorUserInteractionModel#getInvocationWizard <em>Invocation Wizard</em>}</li>
 *   <li>{@link org.opaeum.uim.model.BehaviorUserInteractionModel#getEditor <em>Editor</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.model.ModelPackage#getBehaviorUserInteractionModel()
 * @model
 * @generated
 */
public interface BehaviorUserInteractionModel extends AbstractUserInteractionModel {
	/**
	 * Returns the value of the '<em><b>Invocation Wizard</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.wizard.BehaviorInvocationWizard#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Invocation Wizard</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Invocation Wizard</em>' containment reference.
	 * @see #setInvocationWizard(BehaviorInvocationWizard)
	 * @see org.opaeum.uim.model.ModelPackage#getBehaviorUserInteractionModel_InvocationWizard()
	 * @see org.opaeum.uim.wizard.BehaviorInvocationWizard#getModel
	 * @model opposite="model" containment="true"
	 * @generated
	 */
	BehaviorInvocationWizard getInvocationWizard();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.model.BehaviorUserInteractionModel#getInvocationWizard <em>Invocation Wizard</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Invocation Wizard</em>' containment reference.
	 * @see #getInvocationWizard()
	 * @generated
	 */
	void setInvocationWizard(BehaviorInvocationWizard value);

	/**
	 * Returns the value of the '<em><b>Editor</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.editor.BehaviorExecutionEditor#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Editor</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Editor</em>' containment reference.
	 * @see #setEditor(BehaviorExecutionEditor)
	 * @see org.opaeum.uim.model.ModelPackage#getBehaviorUserInteractionModel_Editor()
	 * @see org.opaeum.uim.editor.BehaviorExecutionEditor#getModel
	 * @model opposite="model" containment="true"
	 * @generated
	 */
	BehaviorExecutionEditor getEditor();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.model.BehaviorUserInteractionModel#getEditor <em>Editor</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Editor</em>' containment reference.
	 * @see #getEditor()
	 * @generated
	 */
	void setEditor(BehaviorExecutionEditor value);

} // BehaviorUserInteractionModel
