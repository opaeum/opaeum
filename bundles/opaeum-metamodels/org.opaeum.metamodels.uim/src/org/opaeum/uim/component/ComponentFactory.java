/**
 */
package org.opaeum.uim.component;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.opaeum.uim.component.ComponentPackage
 * @generated
 */
public interface ComponentFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ComponentFactory eINSTANCE = org.opaeum.uim.component.impl.ComponentFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Uim Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Uim Field</em>'.
	 * @generated
	 */
	UimField createUimField();

	/**
	 * Returns a new object of class '<em>Uim Data Table</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Uim Data Table</em>'.
	 * @generated
	 */
	UimDataTable createUimDataTable();

	/**
	 * Returns a new object of class '<em>Master Tree</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Master Tree</em>'.
	 * @generated
	 */
	MasterTree createMasterTree();

	/**
	 * Returns a new object of class '<em>Detail Component</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Detail Component</em>'.
	 * @generated
	 */
	DetailComponent createDetailComponent();

	/**
	 * Returns a new object of class '<em>Panel For Class</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Panel For Class</em>'.
	 * @generated
	 */
	PanelForClass createPanelForClass();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ComponentPackage getComponentPackage();

} //ComponentFactory
