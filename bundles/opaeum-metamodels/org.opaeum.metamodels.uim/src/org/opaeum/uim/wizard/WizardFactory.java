/**
 */
package org.opaeum.uim.wizard;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.opaeum.uim.wizard.WizardPackage
 * @generated
 */
public interface WizardFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	WizardFactory eINSTANCE = org.opaeum.uim.wizard.impl.WizardFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>New Object Wizard</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>New Object Wizard</em>'.
	 * @generated
	 */
	NewObjectWizard createNewObjectWizard();

	/**
	 * Returns a new object of class '<em>Page</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Page</em>'.
	 * @generated
	 */
	WizardPage createWizardPage();

	/**
	 * Returns a new object of class '<em>Responsibility Invocation Wizard</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Responsibility Invocation Wizard</em>'.
	 * @generated
	 */
	ResponsibilityInvocationWizard createResponsibilityInvocationWizard();

	/**
	 * Returns a new object of class '<em>Behavior Invocation Wizard</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Behavior Invocation Wizard</em>'.
	 * @generated
	 */
	BehaviorInvocationWizard createBehaviorInvocationWizard();

	/**
	 * Returns a new object of class '<em>Operation Result Page</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Operation Result Page</em>'.
	 * @generated
	 */
	OperationResultPage createOperationResultPage();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	WizardPackage getWizardPackage();

} //WizardFactory
