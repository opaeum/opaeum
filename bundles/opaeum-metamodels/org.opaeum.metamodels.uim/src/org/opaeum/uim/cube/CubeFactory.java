/**
 */
package org.opaeum.uim.cube;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.opaeum.uim.cube.CubePackage
 * @generated
 */
public interface CubeFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	CubeFactory eINSTANCE = org.opaeum.uim.cube.impl.CubeFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Query</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Query</em>'.
	 * @generated
	 */
	CubeQuery createCubeQuery();

	/**
	 * Returns a new object of class '<em>Dimension Binding</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Dimension Binding</em>'.
	 * @generated
	 */
	DimensionBinding createDimensionBinding();

	/**
	 * Returns a new object of class '<em>Level Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Level Property</em>'.
	 * @generated
	 */
	LevelProperty createLevelProperty();

	/**
	 * Returns a new object of class '<em>Row Axis Entry</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Row Axis Entry</em>'.
	 * @generated
	 */
	RowAxisEntry createRowAxisEntry();

	/**
	 * Returns a new object of class '<em>Column Axis Entry</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Column Axis Entry</em>'.
	 * @generated
	 */
	ColumnAxisEntry createColumnAxisEntry();

	/**
	 * Returns a new object of class '<em>Measure Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Measure Property</em>'.
	 * @generated
	 */
	MeasureProperty createMeasureProperty();

	/**
	 * Returns a new object of class '<em>Query Editor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Query Editor</em>'.
	 * @generated
	 */
	CubeQueryEditor createCubeQueryEditor();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	CubePackage getCubePackage();

} //CubeFactory
