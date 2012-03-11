/**
 */
package org.opaeum.uim.binding;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.opaeum.uim.binding.BindingPackage
 * @generated
 */
public interface BindingFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	BindingFactory eINSTANCE = org.opaeum.uim.binding.impl.BindingFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Lookup Binding</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Lookup Binding</em>'.
	 * @generated
	 */
	LookupBinding createLookupBinding();

	/**
	 * Returns a new object of class '<em>Navigation Binding</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Navigation Binding</em>'.
	 * @generated
	 */
	NavigationBinding createNavigationBinding();

	/**
	 * Returns a new object of class '<em>Table Binding</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Table Binding</em>'.
	 * @generated
	 */
	TableBinding createTableBinding();

	/**
	 * Returns a new object of class '<em>Field Binding</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Field Binding</em>'.
	 * @generated
	 */
	FieldBinding createFieldBinding();

	/**
	 * Returns a new object of class '<em>Property Ref</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Property Ref</em>'.
	 * @generated
	 */
	PropertyRef createPropertyRef();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	BindingPackage getBindingPackage();

} //BindingFactory
