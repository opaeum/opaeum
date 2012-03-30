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
	 * Returns a new object of class '<em>Uim Perspective</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Uim Perspective</em>'.
	 * @generated
	 */
	UimPerspective createUimPerspective();

	/**
	 * Returns a new object of class '<em>View Allocation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>View Allocation</em>'.
	 * @generated
	 */
	ViewAllocation createViewAllocation();

	/**
	 * Returns a new object of class '<em>Explorer Configuration</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Explorer Configuration</em>'.
	 * @generated
	 */
	ExplorerConfiguration createExplorerConfiguration();

	/**
	 * Returns a new object of class '<em>Explorer Class Configuration</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Explorer Class Configuration</em>'.
	 * @generated
	 */
	ExplorerClassConfiguration createExplorerClassConfiguration();

	/**
	 * Returns a new object of class '<em>Explorer Property Configuration</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Explorer Property Configuration</em>'.
	 * @generated
	 */
	ExplorerPropertyConfiguration createExplorerPropertyConfiguration();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	PerspectivePackage getPerspectivePackage();

} //PerspectiveFactory
