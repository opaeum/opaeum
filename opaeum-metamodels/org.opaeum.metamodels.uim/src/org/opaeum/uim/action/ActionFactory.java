/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opaeum.uim.action;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.opaeum.uim.action.ActionPackage
 * @generated
 */
public interface ActionFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ActionFactory eINSTANCE = org.opaeum.uim.action.impl.ActionFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Built In Action</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Built In Action</em>'.
	 * @generated
	 */
	BuiltInAction createBuiltInAction();

	/**
	 * Returns a new object of class '<em>Transition Action</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Transition Action</em>'.
	 * @generated
	 */
	TransitionAction createTransitionAction();

	/**
	 * Returns a new object of class '<em>Navigation To Operation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Navigation To Operation</em>'.
	 * @generated
	 */
	NavigationToOperation createNavigationToOperation();

	/**
	 * Returns a new object of class '<em>Operation Action</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Operation Action</em>'.
	 * @generated
	 */
	OperationAction createOperationAction();

	/**
	 * Returns a new object of class '<em>Navigation To Entity</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Navigation To Entity</em>'.
	 * @generated
	 */
	NavigationToEntity createNavigationToEntity();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ActionPackage getActionPackage();

} //ActionFactory
