/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim.layout;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.nakeduml.uim.layout.LayoutPackage
 * @generated
 */
public interface LayoutFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	LayoutFactory eINSTANCE = org.nakeduml.uim.layout.impl.LayoutFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Uim Column Layout</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Uim Column Layout</em>'.
	 * @generated
	 */
	UimColumnLayout createUimColumnLayout();

	/**
	 * Returns a new object of class '<em>Uim Full Layout</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Uim Full Layout</em>'.
	 * @generated
	 */
	UimFullLayout createUimFullLayout();

	/**
	 * Returns a new object of class '<em>Uim XY Layout</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Uim XY Layout</em>'.
	 * @generated
	 */
	UimXYLayout createUimXYLayout();

	/**
	 * Returns a new object of class '<em>Uim Border Layout</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Uim Border Layout</em>'.
	 * @generated
	 */
	UimBorderLayout createUimBorderLayout();

	/**
	 * Returns a new object of class '<em>Uim Toolbar Layout</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Uim Toolbar Layout</em>'.
	 * @generated
	 */
	UimToolbarLayout createUimToolbarLayout();

	/**
	 * Returns a new object of class '<em>Uim Layout</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Uim Layout</em>'.
	 * @generated
	 */
	UimLayout createUimLayout();

	/**
	 * Returns a new object of class '<em>Uim Grid Layout</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Uim Grid Layout</em>'.
	 * @generated
	 */
	UimGridLayout createUimGridLayout();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	LayoutPackage getLayoutPackage();

} //LayoutFactory
