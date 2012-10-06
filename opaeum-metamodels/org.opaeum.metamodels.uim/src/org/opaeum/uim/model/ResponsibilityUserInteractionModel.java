/**
 */
package org.opaeum.uim.model;

import org.opaeum.uim.UmlReference;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.editor.ResponsibilityViewer;
import org.opaeum.uim.wizard.ResponsibilityInvocationWizard;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Responsibility User Interaction Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.model.ResponsibilityUserInteractionModel#getInvocationWizard <em>Invocation Wizard</em>}</li>
 *   <li>{@link org.opaeum.uim.model.ResponsibilityUserInteractionModel#getViewer <em>Viewer</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.model.ModelPackage#getResponsibilityUserInteractionModel()
 * @model
 * @generated
 */
public interface ResponsibilityUserInteractionModel extends AbstractUserInteractionModel {
	/**
	 * Returns the value of the '<em><b>Invocation Wizard</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.wizard.ResponsibilityInvocationWizard#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Invocation Wizard</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Invocation Wizard</em>' containment reference.
	 * @see #setInvocationWizard(ResponsibilityInvocationWizard)
	 * @see org.opaeum.uim.model.ModelPackage#getResponsibilityUserInteractionModel_InvocationWizard()
	 * @see org.opaeum.uim.wizard.ResponsibilityInvocationWizard#getModel
	 * @model opposite="model" containment="true" required="true"
	 * @generated
	 */
	ResponsibilityInvocationWizard getInvocationWizard();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.model.ResponsibilityUserInteractionModel#getInvocationWizard <em>Invocation Wizard</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Invocation Wizard</em>' containment reference.
	 * @see #getInvocationWizard()
	 * @generated
	 */
	void setInvocationWizard(ResponsibilityInvocationWizard value);

	/**
	 * Returns the value of the '<em><b>Viewer</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.editor.ResponsibilityViewer#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Viewer</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Viewer</em>' containment reference.
	 * @see #setViewer(ResponsibilityViewer)
	 * @see org.opaeum.uim.model.ModelPackage#getResponsibilityUserInteractionModel_Viewer()
	 * @see org.opaeum.uim.editor.ResponsibilityViewer#getModel
	 * @model opposite="model" containment="true" required="true"
	 * @generated
	 */
	ResponsibilityViewer getViewer();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.model.ResponsibilityUserInteractionModel#getViewer <em>Viewer</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Viewer</em>' containment reference.
	 * @see #getViewer()
	 * @generated
	 */
	void setViewer(ResponsibilityViewer value);

} // ResponsibilityUserInteractionModel
