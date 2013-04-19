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
	 * Returns a new object of class '<em>Navigator Configuration</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Navigator Configuration</em>'.
	 * @generated
	 */
	NavigatorConfiguration createNavigatorConfiguration();

	/**
	 * Returns a new object of class '<em>Class Navigation Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Class Navigation Constraint</em>'.
	 * @generated
	 */
	ClassNavigationConstraint createClassNavigationConstraint();

	/**
	 * Returns a new object of class '<em>Property Navigation Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Property Navigation Constraint</em>'.
	 * @generated
	 */
	PropertyNavigationConstraint createPropertyNavigationConstraint();

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
	 * Returns a new object of class '<em>Navigation Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Navigation Constraint</em>'.
	 * @generated
	 */
	NavigationConstraint createNavigationConstraint();

	/**
	 * Returns a new object of class '<em>Operation Navigation Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Operation Navigation Constraint</em>'.
	 * @generated
	 */
	OperationNavigationConstraint createOperationNavigationConstraint();

	/**
	 * Returns a new object of class '<em>Behavior Navigation Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Behavior Navigation Constraint</em>'.
	 * @generated
	 */
	BehaviorNavigationConstraint createBehaviorNavigationConstraint();

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
	 * Returns a new object of class '<em>Parameter Navigation Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Parameter Navigation Constraint</em>'.
	 * @generated
	 */
	ParameterNavigationConstraint createParameterNavigationConstraint();

	/**
	 * Returns a new object of class '<em>Multiplicity Element Navigation Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Multiplicity Element Navigation Constraint</em>'.
	 * @generated
	 */
	MultiplicityElementNavigationConstraint createMultiplicityElementNavigationConstraint();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	PerspectivePackage getPerspectivePackage();

} //PerspectiveFactory
