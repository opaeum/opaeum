/**
 */
package org.opaeum.uim.model;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.opaeum.uim.model.ModelPackage
 * @generated
 */
public interface ModelFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ModelFactory eINSTANCE = org.opaeum.uim.model.impl.ModelFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Class User Interaction Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Class User Interaction Model</em>'.
	 * @generated
	 */
	ClassUserInteractionModel createClassUserInteractionModel();

	/**
	 * Returns a new object of class '<em>Responsibility User Interaction Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Responsibility User Interaction Model</em>'.
	 * @generated
	 */
	ResponsibilityUserInteractionModel createResponsibilityUserInteractionModel();

	/**
	 * Returns a new object of class '<em>Behavior User Interaction Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Behavior User Interaction Model</em>'.
	 * @generated
	 */
	BehaviorUserInteractionModel createBehaviorUserInteractionModel();

	/**
	 * Returns a new object of class '<em>Query Invoker</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Query Invoker</em>'.
	 * @generated
	 */
	QueryInvoker createQueryInvoker();

	/**
	 * Returns a new object of class '<em>Operation Invocation Wizard</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Operation Invocation Wizard</em>'.
	 * @generated
	 */
	OperationInvocationWizard createOperationInvocationWizard();

	/**
	 * Returns a new object of class '<em>Embedded Task Editor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Embedded Task Editor</em>'.
	 * @generated
	 */
	EmbeddedTaskEditor createEmbeddedTaskEditor();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ModelPackage getModelPackage();

} //ModelFactory
