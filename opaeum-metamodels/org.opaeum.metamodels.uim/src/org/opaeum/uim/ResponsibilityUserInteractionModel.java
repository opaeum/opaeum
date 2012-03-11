/**
 */
package org.opaeum.uim;

import org.opaeum.uim.editor.ResponsibilityTaskEditor;
import org.opaeum.uim.wizard.InvokeResponsibilityWizard;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Responsibility User Interaction Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.ResponsibilityUserInteractionModel#getInvocationWizard <em>Invocation Wizard</em>}</li>
 *   <li>{@link org.opaeum.uim.ResponsibilityUserInteractionModel#getTaskEditor <em>Task Editor</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.UimPackage#getResponsibilityUserInteractionModel()
 * @model
 * @generated
 */
public interface ResponsibilityUserInteractionModel extends UserInteractionElement, UmlReference {
	/**
	 * Returns the value of the '<em><b>Invocation Wizard</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Invocation Wizard</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Invocation Wizard</em>' containment reference.
	 * @see #setInvocationWizard(InvokeResponsibilityWizard)
	 * @see org.opaeum.uim.UimPackage#getResponsibilityUserInteractionModel_InvocationWizard()
	 * @model containment="true" required="true"
	 * @generated
	 */
	InvokeResponsibilityWizard getInvocationWizard();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.ResponsibilityUserInteractionModel#getInvocationWizard <em>Invocation Wizard</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Invocation Wizard</em>' containment reference.
	 * @see #getInvocationWizard()
	 * @generated
	 */
	void setInvocationWizard(InvokeResponsibilityWizard value);

	/**
	 * Returns the value of the '<em><b>Task Editor</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Task Editor</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Task Editor</em>' containment reference.
	 * @see #setTaskEditor(ResponsibilityTaskEditor)
	 * @see org.opaeum.uim.UimPackage#getResponsibilityUserInteractionModel_TaskEditor()
	 * @model containment="true" required="true"
	 * @generated
	 */
	ResponsibilityTaskEditor getTaskEditor();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.ResponsibilityUserInteractionModel#getTaskEditor <em>Task Editor</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Task Editor</em>' containment reference.
	 * @see #getTaskEditor()
	 * @generated
	 */
	void setTaskEditor(ResponsibilityTaskEditor value);

} // ResponsibilityUserInteractionModel
