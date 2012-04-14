/**
 */
package org.opaeum.uim.cube.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.opaeum.uim.cube.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CubeFactoryImpl extends EFactoryImpl implements CubeFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CubeFactory init() {
		try {
			CubeFactory theCubeFactory = (CubeFactory)EPackage.Registry.INSTANCE.getEFactory("http://opaeum.org/uimetamodel/cube/1.0"); 
			if (theCubeFactory != null) {
				return theCubeFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new CubeFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CubeFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case CubePackage.CUBE_QUERY: return createCubeQuery();
			case CubePackage.AXIS_ENTRY: return createAxisEntry();
			case CubePackage.DIMENSION_BINDING: return createDimensionBinding();
			case CubePackage.LEVEL_PROPERTY: return createLevelProperty();
			case CubePackage.ROW_AXIS_ENTRY: return createRowAxisEntry();
			case CubePackage.COLUMN_AXIS_ENTRY: return createColumnAxisEntry();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CubeQuery createCubeQuery() {
		CubeQueryImpl cubeQuery = new CubeQueryImpl();
		return cubeQuery;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AxisEntry createAxisEntry() {
		AxisEntryImpl axisEntry = new AxisEntryImpl();
		return axisEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DimensionBinding createDimensionBinding() {
		DimensionBindingImpl dimensionBinding = new DimensionBindingImpl();
		return dimensionBinding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LevelProperty createLevelProperty() {
		LevelPropertyImpl levelProperty = new LevelPropertyImpl();
		return levelProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RowAxisEntry createRowAxisEntry() {
		RowAxisEntryImpl rowAxisEntry = new RowAxisEntryImpl();
		return rowAxisEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ColumnAxisEntry createColumnAxisEntry() {
		ColumnAxisEntryImpl columnAxisEntry = new ColumnAxisEntryImpl();
		return columnAxisEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CubePackage getCubePackage() {
		return (CubePackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static CubePackage getPackage() {
		return CubePackage.eINSTANCE;
	}

} //CubeFactoryImpl
