/**
 */
package org.opaeum.uim.perspective;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.opaeum.uim.perspective.PerspectivePackage
 * @generated
 */
public interface PerspectiveFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	PerspectiveFactory eINSTANCE = org.opaeum.uim.perspective.impl.PerspectiveFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Configuration</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Configuration</em>'.
	 * @generated
	 */
	PerspectiveConfiguration createPerspectiveConfiguration();

	/**
	 * Returns a new object of class '<em>Explorer Configuration</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Explorer Configuration</em>'.
	 * @generated
	 */
	ExplorerConfiguration createExplorerConfiguration();

	/**
	 * Returns a new object of class '<em>Explorer Class Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Explorer Class Constraint</em>'.
	 * @generated
	 */
	ExplorerClassConstraint createExplorerClassConstraint();

	/**
	 * Returns a new object of class '<em>Explorer Property Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Explorer Property Constraint</em>'.
	 * @generated
	 */
	ExplorerPropertyConstraint createExplorerPropertyConstraint();

	/**
	 * Returns a new object of class '<em>Editor Configuration</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Editor Configuration</em>'.
	 * @generated
	 */
	EditorConfiguration createEditorConfiguration();

	/**
	 * Returns a new object of class '<em>Properties Configuration</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Properties Configuration</em>'.
	 * @generated
	 */
	PropertiesConfiguration createPropertiesConfiguration();

	/**
	 * Returns a new object of class '<em>Explorer Operation Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Explorer Operation Constraint</em>'.
	 * @generated
	 */
	ExplorerOperationConstraint createExplorerOperationConstraint();

	/**
	 * Returns a new object of class '<em>Explorer Behavior Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Explorer Behavior Constraint</em>'.
	 * @generated
	 */
	ExplorerBehaviorConstraint createExplorerBehaviorConstraint();

	/**
	 * Returns a new object of class '<em>Inbox Configuration</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Inbox Configuration</em>'.
	 * @generated
	 */
	InboxConfiguration createInboxConfiguration();

	/**
	 * Returns a new object of class '<em>Outbox Configuration</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Outbox Configuration</em>'.
	 * @generated
	 */
	OutboxConfiguration createOutboxConfiguration();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	PerspectivePackage getPerspectivePackage();

} //PerspectiveFactory
