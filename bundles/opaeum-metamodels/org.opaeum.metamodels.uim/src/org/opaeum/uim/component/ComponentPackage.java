/**
 */
package org.opaeum.uim.component;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.constraint.ConstraintPackage;

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
 * @see org.opaeum.uim.component.ComponentFactory
 * @model kind="package"
 * @generated
 */
public interface ComponentPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "component";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://opaeum.org/uimetamodel/component/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "comp";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ComponentPackage eINSTANCE = org.opaeum.uim.component.impl.ComponentPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.opaeum.uim.component.impl.UimFieldImpl <em>Uim Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.component.impl.UimFieldImpl
	 * @see org.opaeum.uim.component.impl.ComponentPackageImpl#getUimField()
	 * @generated
	 */
	int UIM_FIELD = 0;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FIELD__VISIBILITY = ConstraintPackage.EDITABLE_CONSTRAINED_OBJECT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FIELD__EDITABILITY = ConstraintPackage.EDITABLE_CONSTRAINED_OBJECT__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FIELD__NAME = ConstraintPackage.EDITABLE_CONSTRAINED_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FIELD__UNDER_USER_CONTROL = ConstraintPackage.EDITABLE_CONSTRAINED_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Label Override</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FIELD__LABEL_OVERRIDE = ConstraintPackage.EDITABLE_CONSTRAINED_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Preferred Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FIELD__PREFERRED_WIDTH = ConstraintPackage.EDITABLE_CONSTRAINED_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Preferred Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FIELD__PREFERRED_HEIGHT = ConstraintPackage.EDITABLE_CONSTRAINED_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Fill Horizontally</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FIELD__FILL_HORIZONTALLY = ConstraintPackage.EDITABLE_CONSTRAINED_OBJECT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Fill Vertically</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FIELD__FILL_VERTICALLY = ConstraintPackage.EDITABLE_CONSTRAINED_OBJECT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Control</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FIELD__CONTROL = ConstraintPackage.EDITABLE_CONSTRAINED_OBJECT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Control Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FIELD__CONTROL_KIND = ConstraintPackage.EDITABLE_CONSTRAINED_OBJECT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Minimum Label Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FIELD__MINIMUM_LABEL_WIDTH = ConstraintPackage.EDITABLE_CONSTRAINED_OBJECT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Binding</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FIELD__BINDING = ConstraintPackage.EDITABLE_CONSTRAINED_OBJECT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Orientation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FIELD__ORIENTATION = ConstraintPackage.EDITABLE_CONSTRAINED_OBJECT_FEATURE_COUNT + 11;

	/**
	 * The number of structural features of the '<em>Uim Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FIELD_FEATURE_COUNT = ConstraintPackage.EDITABLE_CONSTRAINED_OBJECT_FEATURE_COUNT + 12;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.component.impl.UimComponentImpl <em>Uim Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.component.impl.UimComponentImpl
	 * @see org.opaeum.uim.component.impl.ComponentPackageImpl#getUimComponent()
	 * @generated
	 */
	int UIM_COMPONENT = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_COMPONENT__NAME = UimPackage.USER_INTERACTION_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_COMPONENT__UNDER_USER_CONTROL = UimPackage.USER_INTERACTION_ELEMENT__UNDER_USER_CONTROL;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_COMPONENT__VISIBILITY = UimPackage.USER_INTERACTION_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Label Override</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_COMPONENT__LABEL_OVERRIDE = UimPackage.USER_INTERACTION_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Uim Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_COMPONENT_FEATURE_COUNT = UimPackage.USER_INTERACTION_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.component.impl.MasterComponentImpl <em>Master Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.component.impl.MasterComponentImpl
	 * @see org.opaeum.uim.component.impl.ComponentPackageImpl#getMasterComponent()
	 * @generated
	 */
	int MASTER_COMPONENT = 4;

	/**
	 * The feature id for the '<em><b>Detail Components</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MASTER_COMPONENT__DETAIL_COMPONENTS = 0;

	/**
	 * The number of structural features of the '<em>Master Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MASTER_COMPONENT_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.component.impl.UimDataTableImpl <em>Uim Data Table</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.component.impl.UimDataTableImpl
	 * @see org.opaeum.uim.component.impl.ComponentPackageImpl#getUimDataTable()
	 * @generated
	 */
	int UIM_DATA_TABLE = 2;

	/**
	 * The feature id for the '<em><b>Detail Components</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_TABLE__DETAIL_COMPONENTS = MASTER_COMPONENT__DETAIL_COMPONENTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_TABLE__NAME = MASTER_COMPONENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_TABLE__UNDER_USER_CONTROL = MASTER_COMPONENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_TABLE__VISIBILITY = MASTER_COMPONENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Label Override</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_TABLE__LABEL_OVERRIDE = MASTER_COMPONENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_TABLE__EDITABILITY = MASTER_COMPONENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_TABLE__CHILDREN = MASTER_COMPONENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Preferred Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_TABLE__PREFERRED_WIDTH = MASTER_COMPONENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Preferred Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_TABLE__PREFERRED_HEIGHT = MASTER_COMPONENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Fill Horizontally</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_TABLE__FILL_HORIZONTALLY = MASTER_COMPONENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Fill Vertically</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_TABLE__FILL_VERTICALLY = MASTER_COMPONENT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Binding</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_TABLE__BINDING = MASTER_COMPONENT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Actions On Multiple Selection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_TABLE__ACTIONS_ON_MULTIPLE_SELECTION = MASTER_COMPONENT_FEATURE_COUNT + 11;

	/**
	 * The number of structural features of the '<em>Uim Data Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_TABLE_FEATURE_COUNT = MASTER_COMPONENT_FEATURE_COUNT + 12;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.component.impl.UimContainerImpl <em>Uim Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.component.impl.UimContainerImpl
	 * @see org.opaeum.uim.component.impl.ComponentPackageImpl#getUimContainer()
	 * @generated
	 */
	int UIM_CONTAINER = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_CONTAINER__NAME = UIM_COMPONENT__NAME;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_CONTAINER__UNDER_USER_CONTROL = UIM_COMPONENT__UNDER_USER_CONTROL;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_CONTAINER__VISIBILITY = UIM_COMPONENT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Label Override</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_CONTAINER__LABEL_OVERRIDE = UIM_COMPONENT__LABEL_OVERRIDE;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_CONTAINER__EDITABILITY = UIM_COMPONENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_CONTAINER__CHILDREN = UIM_COMPONENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Uim Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_CONTAINER_FEATURE_COUNT = UIM_COMPONENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.component.impl.MasterTreeImpl <em>Master Tree</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.component.impl.MasterTreeImpl
	 * @see org.opaeum.uim.component.impl.ComponentPackageImpl#getMasterTree()
	 * @generated
	 */
	int MASTER_TREE = 5;

	/**
	 * The feature id for the '<em><b>Detail Components</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MASTER_TREE__DETAIL_COMPONENTS = MASTER_COMPONENT__DETAIL_COMPONENTS;

	/**
	 * The number of structural features of the '<em>Master Tree</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MASTER_TREE_FEATURE_COUNT = MASTER_COMPONENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.component.impl.DetailComponentImpl <em>Detail Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.component.impl.DetailComponentImpl
	 * @see org.opaeum.uim.component.impl.ComponentPackageImpl#getDetailComponent()
	 * @generated
	 */
	int DETAIL_COMPONENT = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DETAIL_COMPONENT__NAME = UIM_COMPONENT__NAME;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DETAIL_COMPONENT__UNDER_USER_CONTROL = UIM_COMPONENT__UNDER_USER_CONTROL;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DETAIL_COMPONENT__VISIBILITY = UIM_COMPONENT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Label Override</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DETAIL_COMPONENT__LABEL_OVERRIDE = UIM_COMPONENT__LABEL_OVERRIDE;

	/**
	 * The feature id for the '<em><b>Master Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DETAIL_COMPONENT__MASTER_COMPONENT = UIM_COMPONENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Panels For Classes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DETAIL_COMPONENT__PANELS_FOR_CLASSES = UIM_COMPONENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Detail Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DETAIL_COMPONENT_FEATURE_COUNT = UIM_COMPONENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.component.impl.PanelForClassImpl <em>Panel For Class</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.component.impl.PanelForClassImpl
	 * @see org.opaeum.uim.component.impl.ComponentPackageImpl#getPanelForClass()
	 * @generated
	 */
	int PANEL_FOR_CLASS = 7;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANEL_FOR_CLASS__UML_ELEMENT_UID = UimPackage.UML_REFERENCE__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Detail Component</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANEL_FOR_CLASS__DETAIL_COMPONENT = UimPackage.UML_REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Panel</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANEL_FOR_CLASS__PANEL = UimPackage.UML_REFERENCE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Panel For Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANEL_FOR_CLASS_FEATURE_COUNT = UimPackage.UML_REFERENCE_FEATURE_COUNT + 2;


	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.component.UimField <em>Uim Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Uim Field</em>'.
	 * @see org.opaeum.uim.component.UimField
	 * @generated
	 */
	EClass getUimField();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.component.UimField#getControl <em>Control</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Control</em>'.
	 * @see org.opaeum.uim.component.UimField#getControl()
	 * @see #getUimField()
	 * @generated
	 */
	EReference getUimField_Control();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.uim.component.UimField#getControlKind <em>Control Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Control Kind</em>'.
	 * @see org.opaeum.uim.component.UimField#getControlKind()
	 * @see #getUimField()
	 * @generated
	 */
	EAttribute getUimField_ControlKind();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.uim.component.UimField#getMinimumLabelWidth <em>Minimum Label Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Minimum Label Width</em>'.
	 * @see org.opaeum.uim.component.UimField#getMinimumLabelWidth()
	 * @see #getUimField()
	 * @generated
	 */
	EAttribute getUimField_MinimumLabelWidth();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.component.UimField#getBinding <em>Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Binding</em>'.
	 * @see org.opaeum.uim.component.UimField#getBinding()
	 * @see #getUimField()
	 * @generated
	 */
	EReference getUimField_Binding();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.uim.component.UimField#getOrientation <em>Orientation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Orientation</em>'.
	 * @see org.opaeum.uim.component.UimField#getOrientation()
	 * @see #getUimField()
	 * @generated
	 */
	EAttribute getUimField_Orientation();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.component.UimComponent <em>Uim Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Uim Component</em>'.
	 * @see org.opaeum.uim.component.UimComponent
	 * @generated
	 */
	EClass getUimComponent();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.component.UimDataTable <em>Uim Data Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Uim Data Table</em>'.
	 * @see org.opaeum.uim.component.UimDataTable
	 * @generated
	 */
	EClass getUimDataTable();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.component.UimDataTable#getBinding <em>Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Binding</em>'.
	 * @see org.opaeum.uim.component.UimDataTable#getBinding()
	 * @see #getUimDataTable()
	 * @generated
	 */
	EReference getUimDataTable_Binding();

	/**
	 * Returns the meta object for the containment reference list '{@link org.opaeum.uim.component.UimDataTable#getActionsOnMultipleSelection <em>Actions On Multiple Selection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Actions On Multiple Selection</em>'.
	 * @see org.opaeum.uim.component.UimDataTable#getActionsOnMultipleSelection()
	 * @see #getUimDataTable()
	 * @generated
	 */
	EReference getUimDataTable_ActionsOnMultipleSelection();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.component.UimContainer <em>Uim Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Uim Container</em>'.
	 * @see org.opaeum.uim.component.UimContainer
	 * @generated
	 */
	EClass getUimContainer();

	/**
	 * Returns the meta object for the containment reference list '{@link org.opaeum.uim.component.UimContainer#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Children</em>'.
	 * @see org.opaeum.uim.component.UimContainer#getChildren()
	 * @see #getUimContainer()
	 * @generated
	 */
	EReference getUimContainer_Children();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.component.MasterComponent <em>Master Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Master Component</em>'.
	 * @see org.opaeum.uim.component.MasterComponent
	 * @generated
	 */
	EClass getMasterComponent();

	/**
	 * Returns the meta object for the reference list '{@link org.opaeum.uim.component.MasterComponent#getDetailComponents <em>Detail Components</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Detail Components</em>'.
	 * @see org.opaeum.uim.component.MasterComponent#getDetailComponents()
	 * @see #getMasterComponent()
	 * @generated
	 */
	EReference getMasterComponent_DetailComponents();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.component.MasterTree <em>Master Tree</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Master Tree</em>'.
	 * @see org.opaeum.uim.component.MasterTree
	 * @generated
	 */
	EClass getMasterTree();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.component.DetailComponent <em>Detail Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Detail Component</em>'.
	 * @see org.opaeum.uim.component.DetailComponent
	 * @generated
	 */
	EClass getDetailComponent();

	/**
	 * Returns the meta object for the reference '{@link org.opaeum.uim.component.DetailComponent#getMasterComponent <em>Master Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Master Component</em>'.
	 * @see org.opaeum.uim.component.DetailComponent#getMasterComponent()
	 * @see #getDetailComponent()
	 * @generated
	 */
	EReference getDetailComponent_MasterComponent();

	/**
	 * Returns the meta object for the containment reference list '{@link org.opaeum.uim.component.DetailComponent#getPanelsForClasses <em>Panels For Classes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Panels For Classes</em>'.
	 * @see org.opaeum.uim.component.DetailComponent#getPanelsForClasses()
	 * @see #getDetailComponent()
	 * @generated
	 */
	EReference getDetailComponent_PanelsForClasses();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.component.PanelForClass <em>Panel For Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Panel For Class</em>'.
	 * @see org.opaeum.uim.component.PanelForClass
	 * @generated
	 */
	EClass getPanelForClass();

	/**
	 * Returns the meta object for the container reference '{@link org.opaeum.uim.component.PanelForClass#getDetailComponent <em>Detail Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Detail Component</em>'.
	 * @see org.opaeum.uim.component.PanelForClass#getDetailComponent()
	 * @see #getPanelForClass()
	 * @generated
	 */
	EReference getPanelForClass_DetailComponent();

	/**
	 * Returns the meta object for the reference '{@link org.opaeum.uim.component.PanelForClass#getPanel <em>Panel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Panel</em>'.
	 * @see org.opaeum.uim.component.PanelForClass#getPanel()
	 * @see #getPanelForClass()
	 * @generated
	 */
	EReference getPanelForClass_Panel();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ComponentFactory getComponentFactory();

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
		 * The meta object literal for the '{@link org.opaeum.uim.component.impl.UimFieldImpl <em>Uim Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.component.impl.UimFieldImpl
		 * @see org.opaeum.uim.component.impl.ComponentPackageImpl#getUimField()
		 * @generated
		 */
		EClass UIM_FIELD = eINSTANCE.getUimField();

		/**
		 * The meta object literal for the '<em><b>Control</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UIM_FIELD__CONTROL = eINSTANCE.getUimField_Control();

		/**
		 * The meta object literal for the '<em><b>Control Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UIM_FIELD__CONTROL_KIND = eINSTANCE.getUimField_ControlKind();

		/**
		 * The meta object literal for the '<em><b>Minimum Label Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UIM_FIELD__MINIMUM_LABEL_WIDTH = eINSTANCE.getUimField_MinimumLabelWidth();

		/**
		 * The meta object literal for the '<em><b>Binding</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UIM_FIELD__BINDING = eINSTANCE.getUimField_Binding();

		/**
		 * The meta object literal for the '<em><b>Orientation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UIM_FIELD__ORIENTATION = eINSTANCE.getUimField_Orientation();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.component.impl.UimComponentImpl <em>Uim Component</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.component.impl.UimComponentImpl
		 * @see org.opaeum.uim.component.impl.ComponentPackageImpl#getUimComponent()
		 * @generated
		 */
		EClass UIM_COMPONENT = eINSTANCE.getUimComponent();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.component.impl.UimDataTableImpl <em>Uim Data Table</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.component.impl.UimDataTableImpl
		 * @see org.opaeum.uim.component.impl.ComponentPackageImpl#getUimDataTable()
		 * @generated
		 */
		EClass UIM_DATA_TABLE = eINSTANCE.getUimDataTable();

		/**
		 * The meta object literal for the '<em><b>Binding</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UIM_DATA_TABLE__BINDING = eINSTANCE.getUimDataTable_Binding();

		/**
		 * The meta object literal for the '<em><b>Actions On Multiple Selection</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UIM_DATA_TABLE__ACTIONS_ON_MULTIPLE_SELECTION = eINSTANCE.getUimDataTable_ActionsOnMultipleSelection();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.component.impl.UimContainerImpl <em>Uim Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.component.impl.UimContainerImpl
		 * @see org.opaeum.uim.component.impl.ComponentPackageImpl#getUimContainer()
		 * @generated
		 */
		EClass UIM_CONTAINER = eINSTANCE.getUimContainer();

		/**
		 * The meta object literal for the '<em><b>Children</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UIM_CONTAINER__CHILDREN = eINSTANCE.getUimContainer_Children();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.component.impl.MasterComponentImpl <em>Master Component</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.component.impl.MasterComponentImpl
		 * @see org.opaeum.uim.component.impl.ComponentPackageImpl#getMasterComponent()
		 * @generated
		 */
		EClass MASTER_COMPONENT = eINSTANCE.getMasterComponent();

		/**
		 * The meta object literal for the '<em><b>Detail Components</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MASTER_COMPONENT__DETAIL_COMPONENTS = eINSTANCE.getMasterComponent_DetailComponents();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.component.impl.MasterTreeImpl <em>Master Tree</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.component.impl.MasterTreeImpl
		 * @see org.opaeum.uim.component.impl.ComponentPackageImpl#getMasterTree()
		 * @generated
		 */
		EClass MASTER_TREE = eINSTANCE.getMasterTree();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.component.impl.DetailComponentImpl <em>Detail Component</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.component.impl.DetailComponentImpl
		 * @see org.opaeum.uim.component.impl.ComponentPackageImpl#getDetailComponent()
		 * @generated
		 */
		EClass DETAIL_COMPONENT = eINSTANCE.getDetailComponent();

		/**
		 * The meta object literal for the '<em><b>Master Component</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DETAIL_COMPONENT__MASTER_COMPONENT = eINSTANCE.getDetailComponent_MasterComponent();

		/**
		 * The meta object literal for the '<em><b>Panels For Classes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DETAIL_COMPONENT__PANELS_FOR_CLASSES = eINSTANCE.getDetailComponent_PanelsForClasses();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.component.impl.PanelForClassImpl <em>Panel For Class</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.component.impl.PanelForClassImpl
		 * @see org.opaeum.uim.component.impl.ComponentPackageImpl#getPanelForClass()
		 * @generated
		 */
		EClass PANEL_FOR_CLASS = eINSTANCE.getPanelForClass();

		/**
		 * The meta object literal for the '<em><b>Detail Component</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANEL_FOR_CLASS__DETAIL_COMPONENT = eINSTANCE.getPanelForClass_DetailComponent();

		/**
		 * The meta object literal for the '<em><b>Panel</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANEL_FOR_CLASS__PANEL = eINSTANCE.getPanelForClass_Panel();

	}

} //ComponentPackage
