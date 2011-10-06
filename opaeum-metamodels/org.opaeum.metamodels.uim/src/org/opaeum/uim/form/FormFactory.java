/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opaeum.uim.form;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.opaeum.uim.form.FormPackage
 * @generated
 */
public interface FormFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	FormFactory eINSTANCE = org.opaeum.uim.form.impl.FormFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Panel</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Panel</em>'.
	 * @generated
	 */
	FormPanel createFormPanel();

	/**
	 * Returns a new object of class '<em>Action Task Form</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Action Task Form</em>'.
	 * @generated
	 */
	ActionTaskForm createActionTaskForm();

	/**
	 * Returns a new object of class '<em>State Form</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>State Form</em>'.
	 * @generated
	 */
	StateForm createStateForm();

	/**
	 * Returns a new object of class '<em>Class Form</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Class Form</em>'.
	 * @generated
	 */
	ClassForm createClassForm();

	/**
	 * Returns a new object of class '<em>Uim Form</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Uim Form</em>'.
	 * @generated
	 */
	UimForm createUimForm();

	/**
	 * Returns a new object of class '<em>Operation Task Form</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Operation Task Form</em>'.
	 * @generated
	 */
	OperationTaskForm createOperationTaskForm();

	/**
	 * Returns a new object of class '<em>Operation Invocation Form</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Operation Invocation Form</em>'.
	 * @generated
	 */
	OperationInvocationForm createOperationInvocationForm();

	/**
	 * Returns a new object of class '<em>Detail Panel</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Detail Panel</em>'.
	 * @generated
	 */
	DetailPanel createDetailPanel();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	FormPackage getFormPackage();

} //FormFactory
