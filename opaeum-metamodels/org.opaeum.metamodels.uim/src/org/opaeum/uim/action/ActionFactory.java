/**
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
	 * Returns a new object of class '<em>Built In Action Button</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Built In Action Button</em>'.
	 * @generated
	 */
	BuiltInActionButton createBuiltInActionButton();

	/**
	 * Returns a new object of class '<em>Transition Button</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Transition Button</em>'.
	 * @generated
	 */
	TransitionButton createTransitionButton();

	/**
	 * Returns a new object of class '<em>Link To Query</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Link To Query</em>'.
	 * @generated
	 */
	LinkToQuery createLinkToQuery();

	/**
	 * Returns a new object of class '<em>Operation Button</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Operation Button</em>'.
	 * @generated
	 */
	OperationButton createOperationButton();

	/**
	 * Returns a new object of class '<em>Operation Popup</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Operation Popup</em>'.
	 * @generated
	 */
	OperationPopup createOperationPopup();

	/**
	 * Returns a new object of class '<em>Operation Popup Page</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Operation Popup Page</em>'.
	 * @generated
	 */
	OperationPopupPage createOperationPopupPage();

	/**
	 * Returns a new object of class '<em>Built In Link</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Built In Link</em>'.
	 * @generated
	 */
	BuiltInLink createBuiltInLink();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ActionPackage getActionPackage();

} //ActionFactory
