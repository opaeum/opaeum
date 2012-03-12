/**
 */
package org.opaeum.uim;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.opaeum.uim.constraint.ConstraintPackage;
import org.opaeum.uim.panel.PanelPackage;

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
 * @see org.opaeum.uim.UimFactory
 * @model kind="package"
 * @generated
 */
public interface UimPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "uim";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://opaeum.org/uimetamodel/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "uim";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	UimPackage eINSTANCE = org.opaeum.uim.impl.UimPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.opaeum.uim.impl.UimFieldImpl <em>Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.impl.UimFieldImpl
	 * @see org.opaeum.uim.impl.UimPackageImpl#getUimField()
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
	 * The feature id for the '<em><b>Preferred Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FIELD__PREFERRED_WIDTH = ConstraintPackage.EDITABLE_CONSTRAINED_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Preferred Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FIELD__PREFERRED_HEIGHT = ConstraintPackage.EDITABLE_CONSTRAINED_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Fill Horizontally</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FIELD__FILL_HORIZONTALLY = ConstraintPackage.EDITABLE_CONSTRAINED_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Fill Vertically</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FIELD__FILL_VERTICALLY = ConstraintPackage.EDITABLE_CONSTRAINED_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Control</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FIELD__CONTROL = ConstraintPackage.EDITABLE_CONSTRAINED_OBJECT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Control Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FIELD__CONTROL_KIND = ConstraintPackage.EDITABLE_CONSTRAINED_OBJECT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Minimum Label Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FIELD__MINIMUM_LABEL_WIDTH = ConstraintPackage.EDITABLE_CONSTRAINED_OBJECT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Binding</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FIELD__BINDING = ConstraintPackage.EDITABLE_CONSTRAINED_OBJECT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Orientation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FIELD__ORIENTATION = ConstraintPackage.EDITABLE_CONSTRAINED_OBJECT_FEATURE_COUNT + 9;

	/**
	 * The number of structural features of the '<em>Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FIELD_FEATURE_COUNT = ConstraintPackage.EDITABLE_CONSTRAINED_OBJECT_FEATURE_COUNT + 10;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.impl.UserInteractionElementImpl <em>User Interaction Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.impl.UserInteractionElementImpl
	 * @see org.opaeum.uim.impl.UimPackageImpl#getUserInteractionElement()
	 * @generated
	 */
	int USER_INTERACTION_ELEMENT = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERACTION_ELEMENT__NAME = 0;

	/**
	 * The number of structural features of the '<em>User Interaction Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERACTION_ELEMENT_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.impl.UimComponentImpl <em>Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.impl.UimComponentImpl
	 * @see org.opaeum.uim.impl.UimPackageImpl#getUimComponent()
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
	int UIM_COMPONENT__NAME = USER_INTERACTION_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_COMPONENT__VISIBILITY = USER_INTERACTION_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_COMPONENT_FEATURE_COUNT = USER_INTERACTION_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.impl.MasterComponentImpl <em>Master Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.impl.MasterComponentImpl
	 * @see org.opaeum.uim.impl.UimPackageImpl#getMasterComponent()
	 * @generated
	 */
	int MASTER_COMPONENT = 5;

	/**
	 * The feature id for the '<em><b>Detail Panels</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MASTER_COMPONENT__DETAIL_PANELS = 0;

	/**
	 * The number of structural features of the '<em>Master Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MASTER_COMPONENT_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.impl.UimDataTableImpl <em>Data Table</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.impl.UimDataTableImpl
	 * @see org.opaeum.uim.impl.UimPackageImpl#getUimDataTable()
	 * @generated
	 */
	int UIM_DATA_TABLE = 3;

	/**
	 * The feature id for the '<em><b>Detail Panels</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_TABLE__DETAIL_PANELS = MASTER_COMPONENT__DETAIL_PANELS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_TABLE__NAME = MASTER_COMPONENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_TABLE__VISIBILITY = MASTER_COMPONENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_TABLE__EDITABILITY = MASTER_COMPONENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_TABLE__CHILDREN = MASTER_COMPONENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Preferred Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_TABLE__PREFERRED_WIDTH = MASTER_COMPONENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Preferred Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_TABLE__PREFERRED_HEIGHT = MASTER_COMPONENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Fill Horizontally</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_TABLE__FILL_HORIZONTALLY = MASTER_COMPONENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Fill Vertically</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_TABLE__FILL_VERTICALLY = MASTER_COMPONENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Binding</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_TABLE__BINDING = MASTER_COMPONENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Actions On Multiple Selection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_TABLE__ACTIONS_ON_MULTIPLE_SELECTION = MASTER_COMPONENT_FEATURE_COUNT + 9;

	/**
	 * The number of structural features of the '<em>Data Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_TABLE_FEATURE_COUNT = MASTER_COMPONENT_FEATURE_COUNT + 10;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.impl.UimContainerImpl <em>Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.impl.UimContainerImpl
	 * @see org.opaeum.uim.impl.UimPackageImpl#getUimContainer()
	 * @generated
	 */
	int UIM_CONTAINER = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_CONTAINER__NAME = UIM_COMPONENT__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_CONTAINER__VISIBILITY = UIM_COMPONENT__VISIBILITY;

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
	 * The number of structural features of the '<em>Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_CONTAINER_FEATURE_COUNT = UIM_COMPONENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.impl.UmlReferenceImpl <em>Uml Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.impl.UmlReferenceImpl
	 * @see org.opaeum.uim.impl.UimPackageImpl#getUmlReference()
	 * @generated
	 */
	int UML_REFERENCE = 6;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UML_REFERENCE__UML_ELEMENT_UID = 0;

	/**
	 * The number of structural features of the '<em>Uml Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UML_REFERENCE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.impl.ObjectSelectorTreeImpl <em>Object Selector Tree</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.impl.ObjectSelectorTreeImpl
	 * @see org.opaeum.uim.impl.UimPackageImpl#getObjectSelectorTree()
	 * @generated
	 */
	int OBJECT_SELECTOR_TREE = 7;

	/**
	 * The feature id for the '<em><b>Detail Panels</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_SELECTOR_TREE__DETAIL_PANELS = MASTER_COMPONENT__DETAIL_PANELS;

	/**
	 * The number of structural features of the '<em>Object Selector Tree</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_SELECTOR_TREE_FEATURE_COUNT = MASTER_COMPONENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.impl.DetailComponentImpl <em>Detail Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.impl.DetailComponentImpl
	 * @see org.opaeum.uim.impl.UimPackageImpl#getDetailComponent()
	 * @generated
	 */
	int DETAIL_COMPONENT = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DETAIL_COMPONENT__NAME = UIM_COMPONENT__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DETAIL_COMPONENT__VISIBILITY = UIM_COMPONENT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Master Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DETAIL_COMPONENT__MASTER_COMPONENT = UIM_COMPONENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Panel Classes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DETAIL_COMPONENT__PANEL_CLASSES = UIM_COMPONENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Panel</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DETAIL_COMPONENT__PANEL = UIM_COMPONENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Detail Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DETAIL_COMPONENT_FEATURE_COUNT = UIM_COMPONENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.impl.UserInterfaceEntryPointImpl <em>User Interface Entry Point</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.impl.UserInterfaceEntryPointImpl
	 * @see org.opaeum.uim.impl.UimPackageImpl#getUserInterfaceEntryPoint()
	 * @generated
	 */
	int USER_INTERFACE_ENTRY_POINT = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERFACE_ENTRY_POINT__NAME = USER_INTERACTION_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERFACE_ENTRY_POINT__EDITABILITY = USER_INTERACTION_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERFACE_ENTRY_POINT__VISIBILITY = USER_INTERACTION_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>User Interface Entry Point</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERFACE_ENTRY_POINT_FEATURE_COUNT = USER_INTERACTION_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.impl.UserInterfaceImpl <em>User Interface</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.impl.UserInterfaceImpl
	 * @see org.opaeum.uim.impl.UimPackageImpl#getUserInterface()
	 * @generated
	 */
	int USER_INTERFACE = 11;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERFACE__NAME = USER_INTERACTION_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERFACE__UML_ELEMENT_UID = USER_INTERACTION_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Panel</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERFACE__PANEL = USER_INTERACTION_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>User Interface</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERFACE_FEATURE_COUNT = USER_INTERACTION_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.impl.PageImpl <em>Page</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.impl.PageImpl
	 * @see org.opaeum.uim.impl.UimPackageImpl#getPage()
	 * @generated
	 */
	int PAGE = 10;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__NAME = USER_INTERFACE__NAME;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__UML_ELEMENT_UID = USER_INTERFACE__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Panel</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__PANEL = USER_INTERFACE__PANEL;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__VISIBILITY = USER_INTERFACE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__EDITABILITY = USER_INTERFACE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Page</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_FEATURE_COUNT = USER_INTERFACE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.impl.PanelClassImpl <em>Panel Class</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.impl.PanelClassImpl
	 * @see org.opaeum.uim.impl.UimPackageImpl#getPanelClass()
	 * @generated
	 */
	int PANEL_CLASS = 12;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANEL_CLASS__UML_ELEMENT_UID = UML_REFERENCE__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Detail Component</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANEL_CLASS__DETAIL_COMPONENT = UML_REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Panel</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANEL_CLASS__PANEL = UML_REFERENCE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Panel Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANEL_CLASS_FEATURE_COUNT = UML_REFERENCE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.impl.ClassUserInteractionModelImpl <em>Class User Interaction Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.impl.ClassUserInteractionModelImpl
	 * @see org.opaeum.uim.impl.UimPackageImpl#getClassUserInteractionModel()
	 * @generated
	 */
	int CLASS_USER_INTERACTION_MODEL = 13;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_USER_INTERACTION_MODEL__UML_ELEMENT_UID = UML_REFERENCE__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_USER_INTERACTION_MODEL__NAME = UML_REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Primary Editor</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_USER_INTERACTION_MODEL__PRIMARY_EDITOR = UML_REFERENCE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Secondary Editors</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_USER_INTERACTION_MODEL__SECONDARY_EDITORS = UML_REFERENCE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>New Object Wizard</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_USER_INTERACTION_MODEL__NEW_OBJECT_WIZARD = UML_REFERENCE_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Class User Interaction Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_USER_INTERACTION_MODEL_FEATURE_COUNT = UML_REFERENCE_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.impl.ResponsibilityUserInteractionModelImpl <em>Responsibility User Interaction Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.impl.ResponsibilityUserInteractionModelImpl
	 * @see org.opaeum.uim.impl.UimPackageImpl#getResponsibilityUserInteractionModel()
	 * @generated
	 */
	int RESPONSIBILITY_USER_INTERACTION_MODEL = 14;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBILITY_USER_INTERACTION_MODEL__NAME = USER_INTERACTION_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBILITY_USER_INTERACTION_MODEL__UML_ELEMENT_UID = USER_INTERACTION_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Invocation Wizard</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBILITY_USER_INTERACTION_MODEL__INVOCATION_WIZARD = USER_INTERACTION_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Task Editor</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBILITY_USER_INTERACTION_MODEL__TASK_EDITOR = USER_INTERACTION_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Responsibility User Interaction Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBILITY_USER_INTERACTION_MODEL_FEATURE_COUNT = USER_INTERACTION_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.impl.AbstractActionBarImpl <em>Abstract Action Bar</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.impl.AbstractActionBarImpl
	 * @see org.opaeum.uim.impl.UimPackageImpl#getAbstractActionBar()
	 * @generated
	 */
	int ABSTRACT_ACTION_BAR = 15;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ACTION_BAR__NAME = PanelPackage.ABSTRACT_PANEL__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ACTION_BAR__VISIBILITY = PanelPackage.ABSTRACT_PANEL__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ACTION_BAR__EDITABILITY = PanelPackage.ABSTRACT_PANEL__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ACTION_BAR__CHILDREN = PanelPackage.ABSTRACT_PANEL__CHILDREN;

	/**
	 * The number of structural features of the '<em>Abstract Action Bar</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ACTION_BAR_FEATURE_COUNT = PanelPackage.ABSTRACT_PANEL_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.impl.PageContainerImpl <em>Page Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.impl.PageContainerImpl
	 * @see org.opaeum.uim.impl.UimPackageImpl#getPageContainer()
	 * @generated
	 */
	int PAGE_CONTAINER = 16;

	/**
	 * The number of structural features of the '<em>Page Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_CONTAINER_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.Orientation <em>Orientation</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.Orientation
	 * @see org.opaeum.uim.impl.UimPackageImpl#getOrientation()
	 * @generated
	 */
	int ORIENTATION = 17;


	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.UimField <em>Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Field</em>'.
	 * @see org.opaeum.uim.UimField
	 * @generated
	 */
	EClass getUimField();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.UimField#getControl <em>Control</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Control</em>'.
	 * @see org.opaeum.uim.UimField#getControl()
	 * @see #getUimField()
	 * @generated
	 */
	EReference getUimField_Control();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.uim.UimField#getControlKind <em>Control Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Control Kind</em>'.
	 * @see org.opaeum.uim.UimField#getControlKind()
	 * @see #getUimField()
	 * @generated
	 */
	EAttribute getUimField_ControlKind();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.uim.UimField#getMinimumLabelWidth <em>Minimum Label Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Minimum Label Width</em>'.
	 * @see org.opaeum.uim.UimField#getMinimumLabelWidth()
	 * @see #getUimField()
	 * @generated
	 */
	EAttribute getUimField_MinimumLabelWidth();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.UimField#getBinding <em>Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Binding</em>'.
	 * @see org.opaeum.uim.UimField#getBinding()
	 * @see #getUimField()
	 * @generated
	 */
	EReference getUimField_Binding();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.uim.UimField#getOrientation <em>Orientation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Orientation</em>'.
	 * @see org.opaeum.uim.UimField#getOrientation()
	 * @see #getUimField()
	 * @generated
	 */
	EAttribute getUimField_Orientation();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.UimComponent <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component</em>'.
	 * @see org.opaeum.uim.UimComponent
	 * @generated
	 */
	EClass getUimComponent();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.UserInteractionElement <em>User Interaction Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User Interaction Element</em>'.
	 * @see org.opaeum.uim.UserInteractionElement
	 * @generated
	 */
	EClass getUserInteractionElement();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.uim.UserInteractionElement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.opaeum.uim.UserInteractionElement#getName()
	 * @see #getUserInteractionElement()
	 * @generated
	 */
	EAttribute getUserInteractionElement_Name();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.UimDataTable <em>Data Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Table</em>'.
	 * @see org.opaeum.uim.UimDataTable
	 * @generated
	 */
	EClass getUimDataTable();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.UimDataTable#getBinding <em>Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Binding</em>'.
	 * @see org.opaeum.uim.UimDataTable#getBinding()
	 * @see #getUimDataTable()
	 * @generated
	 */
	EReference getUimDataTable_Binding();

	/**
	 * Returns the meta object for the containment reference list '{@link org.opaeum.uim.UimDataTable#getActionsOnMultipleSelection <em>Actions On Multiple Selection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Actions On Multiple Selection</em>'.
	 * @see org.opaeum.uim.UimDataTable#getActionsOnMultipleSelection()
	 * @see #getUimDataTable()
	 * @generated
	 */
	EReference getUimDataTable_ActionsOnMultipleSelection();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.UimContainer <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container</em>'.
	 * @see org.opaeum.uim.UimContainer
	 * @generated
	 */
	EClass getUimContainer();

	/**
	 * Returns the meta object for the containment reference list '{@link org.opaeum.uim.UimContainer#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Children</em>'.
	 * @see org.opaeum.uim.UimContainer#getChildren()
	 * @see #getUimContainer()
	 * @generated
	 */
	EReference getUimContainer_Children();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.MasterComponent <em>Master Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Master Component</em>'.
	 * @see org.opaeum.uim.MasterComponent
	 * @generated
	 */
	EClass getMasterComponent();

	/**
	 * Returns the meta object for the reference list '{@link org.opaeum.uim.MasterComponent#getDetailPanels <em>Detail Panels</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Detail Panels</em>'.
	 * @see org.opaeum.uim.MasterComponent#getDetailPanels()
	 * @see #getMasterComponent()
	 * @generated
	 */
	EReference getMasterComponent_DetailPanels();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.UmlReference <em>Uml Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Uml Reference</em>'.
	 * @see org.opaeum.uim.UmlReference
	 * @generated
	 */
	EClass getUmlReference();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.uim.UmlReference#getUmlElementUid <em>Uml Element Uid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uml Element Uid</em>'.
	 * @see org.opaeum.uim.UmlReference#getUmlElementUid()
	 * @see #getUmlReference()
	 * @generated
	 */
	EAttribute getUmlReference_UmlElementUid();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.ObjectSelectorTree <em>Object Selector Tree</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Object Selector Tree</em>'.
	 * @see org.opaeum.uim.ObjectSelectorTree
	 * @generated
	 */
	EClass getObjectSelectorTree();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.DetailComponent <em>Detail Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Detail Component</em>'.
	 * @see org.opaeum.uim.DetailComponent
	 * @generated
	 */
	EClass getDetailComponent();

	/**
	 * Returns the meta object for the reference '{@link org.opaeum.uim.DetailComponent#getMasterComponent <em>Master Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Master Component</em>'.
	 * @see org.opaeum.uim.DetailComponent#getMasterComponent()
	 * @see #getDetailComponent()
	 * @generated
	 */
	EReference getDetailComponent_MasterComponent();

	/**
	 * Returns the meta object for the containment reference list '{@link org.opaeum.uim.DetailComponent#getPanelClasses <em>Panel Classes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Panel Classes</em>'.
	 * @see org.opaeum.uim.DetailComponent#getPanelClasses()
	 * @see #getDetailComponent()
	 * @generated
	 */
	EReference getDetailComponent_PanelClasses();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.DetailComponent#getPanel <em>Panel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Panel</em>'.
	 * @see org.opaeum.uim.DetailComponent#getPanel()
	 * @see #getDetailComponent()
	 * @generated
	 */
	EReference getDetailComponent_Panel();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.UserInterfaceEntryPoint <em>User Interface Entry Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User Interface Entry Point</em>'.
	 * @see org.opaeum.uim.UserInterfaceEntryPoint
	 * @generated
	 */
	EClass getUserInterfaceEntryPoint();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.UserInterfaceEntryPoint#getEditability <em>Editability</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Editability</em>'.
	 * @see org.opaeum.uim.UserInterfaceEntryPoint#getEditability()
	 * @see #getUserInterfaceEntryPoint()
	 * @generated
	 */
	EReference getUserInterfaceEntryPoint_Editability();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.UserInterfaceEntryPoint#getVisibility <em>Visibility</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Visibility</em>'.
	 * @see org.opaeum.uim.UserInterfaceEntryPoint#getVisibility()
	 * @see #getUserInterfaceEntryPoint()
	 * @generated
	 */
	EReference getUserInterfaceEntryPoint_Visibility();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.Page <em>Page</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Page</em>'.
	 * @see org.opaeum.uim.Page
	 * @generated
	 */
	EClass getPage();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.UserInterface <em>User Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User Interface</em>'.
	 * @see org.opaeum.uim.UserInterface
	 * @generated
	 */
	EClass getUserInterface();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.UserInterface#getPanel <em>Panel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Panel</em>'.
	 * @see org.opaeum.uim.UserInterface#getPanel()
	 * @see #getUserInterface()
	 * @generated
	 */
	EReference getUserInterface_Panel();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.PanelClass <em>Panel Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Panel Class</em>'.
	 * @see org.opaeum.uim.PanelClass
	 * @generated
	 */
	EClass getPanelClass();

	/**
	 * Returns the meta object for the container reference '{@link org.opaeum.uim.PanelClass#getDetailComponent <em>Detail Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Detail Component</em>'.
	 * @see org.opaeum.uim.PanelClass#getDetailComponent()
	 * @see #getPanelClass()
	 * @generated
	 */
	EReference getPanelClass_DetailComponent();

	/**
	 * Returns the meta object for the reference '{@link org.opaeum.uim.PanelClass#getPanel <em>Panel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Panel</em>'.
	 * @see org.opaeum.uim.PanelClass#getPanel()
	 * @see #getPanelClass()
	 * @generated
	 */
	EReference getPanelClass_Panel();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.ClassUserInteractionModel <em>Class User Interaction Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Class User Interaction Model</em>'.
	 * @see org.opaeum.uim.ClassUserInteractionModel
	 * @generated
	 */
	EClass getClassUserInteractionModel();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.ClassUserInteractionModel#getPrimaryEditor <em>Primary Editor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Primary Editor</em>'.
	 * @see org.opaeum.uim.ClassUserInteractionModel#getPrimaryEditor()
	 * @see #getClassUserInteractionModel()
	 * @generated
	 */
	EReference getClassUserInteractionModel_PrimaryEditor();

	/**
	 * Returns the meta object for the containment reference list '{@link org.opaeum.uim.ClassUserInteractionModel#getSecondaryEditors <em>Secondary Editors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Secondary Editors</em>'.
	 * @see org.opaeum.uim.ClassUserInteractionModel#getSecondaryEditors()
	 * @see #getClassUserInteractionModel()
	 * @generated
	 */
	EReference getClassUserInteractionModel_SecondaryEditors();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.ClassUserInteractionModel#getNewObjectWizard <em>New Object Wizard</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>New Object Wizard</em>'.
	 * @see org.opaeum.uim.ClassUserInteractionModel#getNewObjectWizard()
	 * @see #getClassUserInteractionModel()
	 * @generated
	 */
	EReference getClassUserInteractionModel_NewObjectWizard();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.ResponsibilityUserInteractionModel <em>Responsibility User Interaction Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Responsibility User Interaction Model</em>'.
	 * @see org.opaeum.uim.ResponsibilityUserInteractionModel
	 * @generated
	 */
	EClass getResponsibilityUserInteractionModel();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.ResponsibilityUserInteractionModel#getInvocationWizard <em>Invocation Wizard</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Invocation Wizard</em>'.
	 * @see org.opaeum.uim.ResponsibilityUserInteractionModel#getInvocationWizard()
	 * @see #getResponsibilityUserInteractionModel()
	 * @generated
	 */
	EReference getResponsibilityUserInteractionModel_InvocationWizard();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.ResponsibilityUserInteractionModel#getTaskEditor <em>Task Editor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Task Editor</em>'.
	 * @see org.opaeum.uim.ResponsibilityUserInteractionModel#getTaskEditor()
	 * @see #getResponsibilityUserInteractionModel()
	 * @generated
	 */
	EReference getResponsibilityUserInteractionModel_TaskEditor();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.AbstractActionBar <em>Abstract Action Bar</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Action Bar</em>'.
	 * @see org.opaeum.uim.AbstractActionBar
	 * @generated
	 */
	EClass getAbstractActionBar();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.PageContainer <em>Page Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Page Container</em>'.
	 * @see org.opaeum.uim.PageContainer
	 * @generated
	 */
	EClass getPageContainer();

	/**
	 * Returns the meta object for enum '{@link org.opaeum.uim.Orientation <em>Orientation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Orientation</em>'.
	 * @see org.opaeum.uim.Orientation
	 * @generated
	 */
	EEnum getOrientation();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	UimFactory getUimFactory();

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
		 * The meta object literal for the '{@link org.opaeum.uim.impl.UimFieldImpl <em>Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.impl.UimFieldImpl
		 * @see org.opaeum.uim.impl.UimPackageImpl#getUimField()
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
		 * The meta object literal for the '{@link org.opaeum.uim.impl.UimComponentImpl <em>Component</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.impl.UimComponentImpl
		 * @see org.opaeum.uim.impl.UimPackageImpl#getUimComponent()
		 * @generated
		 */
		EClass UIM_COMPONENT = eINSTANCE.getUimComponent();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.impl.UserInteractionElementImpl <em>User Interaction Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.impl.UserInteractionElementImpl
		 * @see org.opaeum.uim.impl.UimPackageImpl#getUserInteractionElement()
		 * @generated
		 */
		EClass USER_INTERACTION_ELEMENT = eINSTANCE.getUserInteractionElement();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USER_INTERACTION_ELEMENT__NAME = eINSTANCE.getUserInteractionElement_Name();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.impl.UimDataTableImpl <em>Data Table</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.impl.UimDataTableImpl
		 * @see org.opaeum.uim.impl.UimPackageImpl#getUimDataTable()
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
		 * The meta object literal for the '{@link org.opaeum.uim.impl.UimContainerImpl <em>Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.impl.UimContainerImpl
		 * @see org.opaeum.uim.impl.UimPackageImpl#getUimContainer()
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
		 * The meta object literal for the '{@link org.opaeum.uim.impl.MasterComponentImpl <em>Master Component</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.impl.MasterComponentImpl
		 * @see org.opaeum.uim.impl.UimPackageImpl#getMasterComponent()
		 * @generated
		 */
		EClass MASTER_COMPONENT = eINSTANCE.getMasterComponent();

		/**
		 * The meta object literal for the '<em><b>Detail Panels</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MASTER_COMPONENT__DETAIL_PANELS = eINSTANCE.getMasterComponent_DetailPanels();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.impl.UmlReferenceImpl <em>Uml Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.impl.UmlReferenceImpl
		 * @see org.opaeum.uim.impl.UimPackageImpl#getUmlReference()
		 * @generated
		 */
		EClass UML_REFERENCE = eINSTANCE.getUmlReference();

		/**
		 * The meta object literal for the '<em><b>Uml Element Uid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UML_REFERENCE__UML_ELEMENT_UID = eINSTANCE.getUmlReference_UmlElementUid();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.impl.ObjectSelectorTreeImpl <em>Object Selector Tree</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.impl.ObjectSelectorTreeImpl
		 * @see org.opaeum.uim.impl.UimPackageImpl#getObjectSelectorTree()
		 * @generated
		 */
		EClass OBJECT_SELECTOR_TREE = eINSTANCE.getObjectSelectorTree();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.impl.DetailComponentImpl <em>Detail Component</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.impl.DetailComponentImpl
		 * @see org.opaeum.uim.impl.UimPackageImpl#getDetailComponent()
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
		 * The meta object literal for the '<em><b>Panel Classes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DETAIL_COMPONENT__PANEL_CLASSES = eINSTANCE.getDetailComponent_PanelClasses();

		/**
		 * The meta object literal for the '<em><b>Panel</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DETAIL_COMPONENT__PANEL = eINSTANCE.getDetailComponent_Panel();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.impl.UserInterfaceEntryPointImpl <em>User Interface Entry Point</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.impl.UserInterfaceEntryPointImpl
		 * @see org.opaeum.uim.impl.UimPackageImpl#getUserInterfaceEntryPoint()
		 * @generated
		 */
		EClass USER_INTERFACE_ENTRY_POINT = eINSTANCE.getUserInterfaceEntryPoint();

		/**
		 * The meta object literal for the '<em><b>Editability</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USER_INTERFACE_ENTRY_POINT__EDITABILITY = eINSTANCE.getUserInterfaceEntryPoint_Editability();

		/**
		 * The meta object literal for the '<em><b>Visibility</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USER_INTERFACE_ENTRY_POINT__VISIBILITY = eINSTANCE.getUserInterfaceEntryPoint_Visibility();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.impl.PageImpl <em>Page</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.impl.PageImpl
		 * @see org.opaeum.uim.impl.UimPackageImpl#getPage()
		 * @generated
		 */
		EClass PAGE = eINSTANCE.getPage();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.impl.UserInterfaceImpl <em>User Interface</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.impl.UserInterfaceImpl
		 * @see org.opaeum.uim.impl.UimPackageImpl#getUserInterface()
		 * @generated
		 */
		EClass USER_INTERFACE = eINSTANCE.getUserInterface();

		/**
		 * The meta object literal for the '<em><b>Panel</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USER_INTERFACE__PANEL = eINSTANCE.getUserInterface_Panel();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.impl.PanelClassImpl <em>Panel Class</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.impl.PanelClassImpl
		 * @see org.opaeum.uim.impl.UimPackageImpl#getPanelClass()
		 * @generated
		 */
		EClass PANEL_CLASS = eINSTANCE.getPanelClass();

		/**
		 * The meta object literal for the '<em><b>Detail Component</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANEL_CLASS__DETAIL_COMPONENT = eINSTANCE.getPanelClass_DetailComponent();

		/**
		 * The meta object literal for the '<em><b>Panel</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANEL_CLASS__PANEL = eINSTANCE.getPanelClass_Panel();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.impl.ClassUserInteractionModelImpl <em>Class User Interaction Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.impl.ClassUserInteractionModelImpl
		 * @see org.opaeum.uim.impl.UimPackageImpl#getClassUserInteractionModel()
		 * @generated
		 */
		EClass CLASS_USER_INTERACTION_MODEL = eINSTANCE.getClassUserInteractionModel();

		/**
		 * The meta object literal for the '<em><b>Primary Editor</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS_USER_INTERACTION_MODEL__PRIMARY_EDITOR = eINSTANCE.getClassUserInteractionModel_PrimaryEditor();

		/**
		 * The meta object literal for the '<em><b>Secondary Editors</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS_USER_INTERACTION_MODEL__SECONDARY_EDITORS = eINSTANCE.getClassUserInteractionModel_SecondaryEditors();

		/**
		 * The meta object literal for the '<em><b>New Object Wizard</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS_USER_INTERACTION_MODEL__NEW_OBJECT_WIZARD = eINSTANCE.getClassUserInteractionModel_NewObjectWizard();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.impl.ResponsibilityUserInteractionModelImpl <em>Responsibility User Interaction Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.impl.ResponsibilityUserInteractionModelImpl
		 * @see org.opaeum.uim.impl.UimPackageImpl#getResponsibilityUserInteractionModel()
		 * @generated
		 */
		EClass RESPONSIBILITY_USER_INTERACTION_MODEL = eINSTANCE.getResponsibilityUserInteractionModel();

		/**
		 * The meta object literal for the '<em><b>Invocation Wizard</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESPONSIBILITY_USER_INTERACTION_MODEL__INVOCATION_WIZARD = eINSTANCE.getResponsibilityUserInteractionModel_InvocationWizard();

		/**
		 * The meta object literal for the '<em><b>Task Editor</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESPONSIBILITY_USER_INTERACTION_MODEL__TASK_EDITOR = eINSTANCE.getResponsibilityUserInteractionModel_TaskEditor();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.impl.AbstractActionBarImpl <em>Abstract Action Bar</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.impl.AbstractActionBarImpl
		 * @see org.opaeum.uim.impl.UimPackageImpl#getAbstractActionBar()
		 * @generated
		 */
		EClass ABSTRACT_ACTION_BAR = eINSTANCE.getAbstractActionBar();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.impl.PageContainerImpl <em>Page Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.impl.PageContainerImpl
		 * @see org.opaeum.uim.impl.UimPackageImpl#getPageContainer()
		 * @generated
		 */
		EClass PAGE_CONTAINER = eINSTANCE.getPageContainer();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.Orientation <em>Orientation</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.Orientation
		 * @see org.opaeum.uim.impl.UimPackageImpl#getOrientation()
		 * @generated
		 */
		EEnum ORIENTATION = eINSTANCE.getOrientation();

	}

} //UimPackage
