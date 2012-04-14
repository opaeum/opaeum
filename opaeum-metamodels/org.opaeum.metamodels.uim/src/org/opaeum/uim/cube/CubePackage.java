/**
 */
package org.opaeum.uim.cube;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.opaeum.uim.UimPackage;

import org.opaeum.uim.binding.BindingPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.opaeum.uim.cube.CubeFactory
 * @model kind="package"
 * @generated
 */
public interface CubePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "cube";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://opaeum.org/uimetamodel/cube/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "cube";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	CubePackage eINSTANCE = org.opaeum.uim.cube.impl.CubePackageImpl.init();

	/**
	 * The meta object id for the '{@link org.opaeum.uim.cube.impl.CubeQueryImpl <em>Query</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.cube.impl.CubeQueryImpl
	 * @see org.opaeum.uim.cube.impl.CubePackageImpl#getCubeQuery()
	 * @generated
	 */
	int CUBE_QUERY = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_QUERY__NAME = UimPackage.USER_INTERACTION_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_QUERY__UNDER_USER_CONTROL = UimPackage.USER_INTERACTION_ELEMENT__UNDER_USER_CONTROL;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_QUERY__VISIBILITY = UimPackage.USER_INTERACTION_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_QUERY__UML_ELEMENT_UID = UimPackage.USER_INTERACTION_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Column Axis</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_QUERY__COLUMN_AXIS = UimPackage.USER_INTERACTION_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Row Axis</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_QUERY__ROW_AXIS = UimPackage.USER_INTERACTION_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Query</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_QUERY_FEATURE_COUNT = UimPackage.USER_INTERACTION_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.cube.impl.AxisEntryImpl <em>Axis Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.cube.impl.AxisEntryImpl
	 * @see org.opaeum.uim.cube.impl.CubePackageImpl#getAxisEntry()
	 * @generated
	 */
	int AXIS_ENTRY = 1;

	/**
	 * The feature id for the '<em><b>Dimension Binding</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AXIS_ENTRY__DIMENSION_BINDING = 0;

	/**
	 * The feature id for the '<em><b>Level Property</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AXIS_ENTRY__LEVEL_PROPERTY = 1;

	/**
	 * The number of structural features of the '<em>Axis Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AXIS_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.cube.impl.DimensionBindingImpl <em>Dimension Binding</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.cube.impl.DimensionBindingImpl
	 * @see org.opaeum.uim.cube.impl.CubePackageImpl#getDimensionBinding()
	 * @generated
	 */
	int DIMENSION_BINDING = 2;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_BINDING__UML_ELEMENT_UID = BindingPackage.UIM_BINDING__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Next</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_BINDING__NEXT = BindingPackage.UIM_BINDING__NEXT;

	/**
	 * The number of structural features of the '<em>Dimension Binding</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_BINDING_FEATURE_COUNT = BindingPackage.UIM_BINDING_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.cube.impl.LevelPropertyImpl <em>Level Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.cube.impl.LevelPropertyImpl
	 * @see org.opaeum.uim.cube.impl.CubePackageImpl#getLevelProperty()
	 * @generated
	 */
	int LEVEL_PROPERTY = 3;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL_PROPERTY__UML_ELEMENT_UID = UimPackage.UML_REFERENCE__UML_ELEMENT_UID;

	/**
	 * The number of structural features of the '<em>Level Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL_PROPERTY_FEATURE_COUNT = UimPackage.UML_REFERENCE_FEATURE_COUNT + 0;


	/**
	 * The meta object id for the '{@link org.opaeum.uim.cube.impl.RowAxisEntryImpl <em>Row Axis Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.cube.impl.RowAxisEntryImpl
	 * @see org.opaeum.uim.cube.impl.CubePackageImpl#getRowAxisEntry()
	 * @generated
	 */
	int ROW_AXIS_ENTRY = 4;

	/**
	 * The feature id for the '<em><b>Dimension Binding</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROW_AXIS_ENTRY__DIMENSION_BINDING = AXIS_ENTRY__DIMENSION_BINDING;

	/**
	 * The feature id for the '<em><b>Level Property</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROW_AXIS_ENTRY__LEVEL_PROPERTY = AXIS_ENTRY__LEVEL_PROPERTY;

	/**
	 * The number of structural features of the '<em>Row Axis Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROW_AXIS_ENTRY_FEATURE_COUNT = AXIS_ENTRY_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.cube.impl.ColumnAxisEntryImpl <em>Column Axis Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.cube.impl.ColumnAxisEntryImpl
	 * @see org.opaeum.uim.cube.impl.CubePackageImpl#getColumnAxisEntry()
	 * @generated
	 */
	int COLUMN_AXIS_ENTRY = 5;

	/**
	 * The feature id for the '<em><b>Dimension Binding</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN_AXIS_ENTRY__DIMENSION_BINDING = AXIS_ENTRY__DIMENSION_BINDING;

	/**
	 * The feature id for the '<em><b>Level Property</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN_AXIS_ENTRY__LEVEL_PROPERTY = AXIS_ENTRY__LEVEL_PROPERTY;

	/**
	 * The number of structural features of the '<em>Column Axis Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN_AXIS_ENTRY_FEATURE_COUNT = AXIS_ENTRY_FEATURE_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.cube.CubeQuery <em>Query</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Query</em>'.
	 * @see org.opaeum.uim.cube.CubeQuery
	 * @generated
	 */
	EClass getCubeQuery();

	/**
	 * Returns the meta object for the containment reference list '{@link org.opaeum.uim.cube.CubeQuery#getColumnAxis <em>Column Axis</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Column Axis</em>'.
	 * @see org.opaeum.uim.cube.CubeQuery#getColumnAxis()
	 * @see #getCubeQuery()
	 * @generated
	 */
	EReference getCubeQuery_ColumnAxis();

	/**
	 * Returns the meta object for the containment reference list '{@link org.opaeum.uim.cube.CubeQuery#getRowAxis <em>Row Axis</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Row Axis</em>'.
	 * @see org.opaeum.uim.cube.CubeQuery#getRowAxis()
	 * @see #getCubeQuery()
	 * @generated
	 */
	EReference getCubeQuery_RowAxis();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.cube.AxisEntry <em>Axis Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Axis Entry</em>'.
	 * @see org.opaeum.uim.cube.AxisEntry
	 * @generated
	 */
	EClass getAxisEntry();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.cube.AxisEntry#getDimensionBinding <em>Dimension Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Dimension Binding</em>'.
	 * @see org.opaeum.uim.cube.AxisEntry#getDimensionBinding()
	 * @see #getAxisEntry()
	 * @generated
	 */
	EReference getAxisEntry_DimensionBinding();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.cube.AxisEntry#getLevelProperty <em>Level Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Level Property</em>'.
	 * @see org.opaeum.uim.cube.AxisEntry#getLevelProperty()
	 * @see #getAxisEntry()
	 * @generated
	 */
	EReference getAxisEntry_LevelProperty();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.cube.DimensionBinding <em>Dimension Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Dimension Binding</em>'.
	 * @see org.opaeum.uim.cube.DimensionBinding
	 * @generated
	 */
	EClass getDimensionBinding();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.cube.LevelProperty <em>Level Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Level Property</em>'.
	 * @see org.opaeum.uim.cube.LevelProperty
	 * @generated
	 */
	EClass getLevelProperty();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.cube.RowAxisEntry <em>Row Axis Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Row Axis Entry</em>'.
	 * @see org.opaeum.uim.cube.RowAxisEntry
	 * @generated
	 */
	EClass getRowAxisEntry();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.cube.ColumnAxisEntry <em>Column Axis Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Column Axis Entry</em>'.
	 * @see org.opaeum.uim.cube.ColumnAxisEntry
	 * @generated
	 */
	EClass getColumnAxisEntry();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	CubeFactory getCubeFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.opaeum.uim.cube.impl.CubeQueryImpl <em>Query</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.cube.impl.CubeQueryImpl
		 * @see org.opaeum.uim.cube.impl.CubePackageImpl#getCubeQuery()
		 * @generated
		 */
		EClass CUBE_QUERY = eINSTANCE.getCubeQuery();

		/**
		 * The meta object literal for the '<em><b>Column Axis</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CUBE_QUERY__COLUMN_AXIS = eINSTANCE.getCubeQuery_ColumnAxis();

		/**
		 * The meta object literal for the '<em><b>Row Axis</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CUBE_QUERY__ROW_AXIS = eINSTANCE.getCubeQuery_RowAxis();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.cube.impl.AxisEntryImpl <em>Axis Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.cube.impl.AxisEntryImpl
		 * @see org.opaeum.uim.cube.impl.CubePackageImpl#getAxisEntry()
		 * @generated
		 */
		EClass AXIS_ENTRY = eINSTANCE.getAxisEntry();

		/**
		 * The meta object literal for the '<em><b>Dimension Binding</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AXIS_ENTRY__DIMENSION_BINDING = eINSTANCE.getAxisEntry_DimensionBinding();

		/**
		 * The meta object literal for the '<em><b>Level Property</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AXIS_ENTRY__LEVEL_PROPERTY = eINSTANCE.getAxisEntry_LevelProperty();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.cube.impl.DimensionBindingImpl <em>Dimension Binding</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.cube.impl.DimensionBindingImpl
		 * @see org.opaeum.uim.cube.impl.CubePackageImpl#getDimensionBinding()
		 * @generated
		 */
		EClass DIMENSION_BINDING = eINSTANCE.getDimensionBinding();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.cube.impl.LevelPropertyImpl <em>Level Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.cube.impl.LevelPropertyImpl
		 * @see org.opaeum.uim.cube.impl.CubePackageImpl#getLevelProperty()
		 * @generated
		 */
		EClass LEVEL_PROPERTY = eINSTANCE.getLevelProperty();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.cube.impl.RowAxisEntryImpl <em>Row Axis Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.cube.impl.RowAxisEntryImpl
		 * @see org.opaeum.uim.cube.impl.CubePackageImpl#getRowAxisEntry()
		 * @generated
		 */
		EClass ROW_AXIS_ENTRY = eINSTANCE.getRowAxisEntry();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.cube.impl.ColumnAxisEntryImpl <em>Column Axis Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.cube.impl.ColumnAxisEntryImpl
		 * @see org.opaeum.uim.cube.impl.CubePackageImpl#getColumnAxisEntry()
		 * @generated
		 */
		EClass COLUMN_AXIS_ENTRY = eINSTANCE.getColumnAxisEntry();

	}

} //CubePackage
