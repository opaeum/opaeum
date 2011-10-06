/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opaeum.uim;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.opaeum.uim.layout.LayoutPackage;
import org.opaeum.uim.security.SecurityPackage;

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
	int UIM_FIELD__VISIBILITY = SecurityPackage.EDITABLE_SECURE_OBJECT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FIELD__EDITABILITY = SecurityPackage.EDITABLE_SECURE_OBJECT__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FIELD__NAME = SecurityPackage.EDITABLE_SECURE_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FIELD__PARENT = SecurityPackage.EDITABLE_SECURE_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Control</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FIELD__CONTROL = SecurityPackage.EDITABLE_SECURE_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Control Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FIELD__CONTROL_KIND = SecurityPackage.EDITABLE_SECURE_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Label Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FIELD__LABEL_WIDTH = SecurityPackage.EDITABLE_SECURE_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Binding</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FIELD__BINDING = SecurityPackage.EDITABLE_SECURE_OBJECT_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_FIELD_FEATURE_COUNT = SecurityPackage.EDITABLE_SECURE_OBJECT_FEATURE_COUNT + 6;

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
	 * The meta object id for the '{@link org.opaeum.uim.MasterComponent <em>Master Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.MasterComponent
	 * @see org.opaeum.uim.impl.UimPackageImpl#getMasterComponent()
	 * @generated
	 */
	int MASTER_COMPONENT = 7;

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
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_TABLE__PARENT = MASTER_COMPONENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_TABLE__EDITABILITY = MASTER_COMPONENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Layout</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_TABLE__LAYOUT = MASTER_COMPONENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Binding</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_TABLE__BINDING = MASTER_COMPONENT_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Data Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_DATA_TABLE_FEATURE_COUNT = MASTER_COMPONENT_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.impl.UimContainerImpl <em>Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.impl.UimContainerImpl
	 * @see org.opaeum.uim.impl.UimPackageImpl#getUimContainer()
	 * @generated
	 */
	int UIM_CONTAINER = 6;

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
	 * The number of structural features of the '<em>Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_CONTAINER_FEATURE_COUNT = UIM_COMPONENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.impl.UimTabPanelImpl <em>Tab Panel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.impl.UimTabPanelImpl
	 * @see org.opaeum.uim.impl.UimPackageImpl#getUimTabPanel()
	 * @generated
	 */
	int UIM_TAB_PANEL = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TAB_PANEL__NAME = UIM_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TAB_PANEL__VISIBILITY = UIM_CONTAINER__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TAB_PANEL__EDITABILITY = UIM_CONTAINER__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TAB_PANEL__PARENT = UIM_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TAB_PANEL__CHILDREN = UIM_CONTAINER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Active Tab Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TAB_PANEL__ACTIVE_TAB_INDEX = UIM_CONTAINER_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Tab Panel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TAB_PANEL_FEATURE_COUNT = UIM_CONTAINER_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.impl.UimTabImpl <em>Tab</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.impl.UimTabImpl
	 * @see org.opaeum.uim.impl.UimPackageImpl#getUimTab()
	 * @generated
	 */
	int UIM_TAB = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TAB__NAME = LayoutPackage.LAYOUT_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TAB__VISIBILITY = LayoutPackage.LAYOUT_CONTAINER__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TAB__EDITABILITY = LayoutPackage.LAYOUT_CONTAINER__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Layout</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TAB__LAYOUT = LayoutPackage.LAYOUT_CONTAINER__LAYOUT;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TAB__PARENT = LayoutPackage.LAYOUT_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Tab</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_TAB_FEATURE_COUNT = LayoutPackage.LAYOUT_CONTAINER_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.impl.UmlReferenceImpl <em>Uml Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.impl.UmlReferenceImpl
	 * @see org.opaeum.uim.impl.UimPackageImpl#getUmlReference()
	 * @generated
	 */
	int UML_REFERENCE = 8;

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
	 * The meta object id for the '{@link org.opaeum.uim.impl.UimPanelImpl <em>Panel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.impl.UimPanelImpl
	 * @see org.opaeum.uim.impl.UimPackageImpl#getUimPanel()
	 * @generated
	 */
	int UIM_PANEL = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_PANEL__NAME = LayoutPackage.LAYOUT_CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_PANEL__VISIBILITY = LayoutPackage.LAYOUT_CONTAINER__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_PANEL__EDITABILITY = LayoutPackage.LAYOUT_CONTAINER__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Layout</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_PANEL__LAYOUT = LayoutPackage.LAYOUT_CONTAINER__LAYOUT;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_PANEL__PARENT = LayoutPackage.LAYOUT_CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Panel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UIM_PANEL_FEATURE_COUNT = LayoutPackage.LAYOUT_CONTAINER_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.impl.ObjectSelectorTreeImpl <em>Object Selector Tree</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.impl.ObjectSelectorTreeImpl
	 * @see org.opaeum.uim.impl.UimPackageImpl#getObjectSelectorTree()
	 * @generated
	 */
	int OBJECT_SELECTOR_TREE = 10;

	/**
	 * The feature id for the '<em><b>Detail Panels</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_SELECTOR_TREE__DETAIL_PANELS = MASTER_COMPONENT__DETAIL_PANELS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_SELECTOR_TREE__NAME = MASTER_COMPONENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_SELECTOR_TREE__VISIBILITY = MASTER_COMPONENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_SELECTOR_TREE__PARENT = MASTER_COMPONENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Object Selector Tree</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_SELECTOR_TREE_FEATURE_COUNT = MASTER_COMPONENT_FEATURE_COUNT + 3;


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
	 * Returns the meta object for the attribute '{@link org.opaeum.uim.UimField#getLabelWidth <em>Label Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Label Width</em>'.
	 * @see org.opaeum.uim.UimField#getLabelWidth()
	 * @see #getUimField()
	 * @generated
	 */
	EAttribute getUimField_LabelWidth();

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
	 * Returns the meta object for class '{@link org.opaeum.uim.UimTabPanel <em>Tab Panel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tab Panel</em>'.
	 * @see org.opaeum.uim.UimTabPanel
	 * @generated
	 */
	EClass getUimTabPanel();

	/**
	 * Returns the meta object for the containment reference list '{@link org.opaeum.uim.UimTabPanel#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Children</em>'.
	 * @see org.opaeum.uim.UimTabPanel#getChildren()
	 * @see #getUimTabPanel()
	 * @generated
	 */
	EReference getUimTabPanel_Children();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.uim.UimTabPanel#getActiveTabIndex <em>Active Tab Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Active Tab Index</em>'.
	 * @see org.opaeum.uim.UimTabPanel#getActiveTabIndex()
	 * @see #getUimTabPanel()
	 * @generated
	 */
	EAttribute getUimTabPanel_ActiveTabIndex();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.UimTab <em>Tab</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tab</em>'.
	 * @see org.opaeum.uim.UimTab
	 * @generated
	 */
	EClass getUimTab();

	/**
	 * Returns the meta object for the container reference '{@link org.opaeum.uim.UimTab#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Parent</em>'.
	 * @see org.opaeum.uim.UimTab#getParent()
	 * @see #getUimTab()
	 * @generated
	 */
	EReference getUimTab_Parent();

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
	 * Returns the meta object for class '{@link org.opaeum.uim.UimPanel <em>Panel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Panel</em>'.
	 * @see org.opaeum.uim.UimPanel
	 * @generated
	 */
	EClass getUimPanel();

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
		 * The meta object literal for the '<em><b>Label Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UIM_FIELD__LABEL_WIDTH = eINSTANCE.getUimField_LabelWidth();

		/**
		 * The meta object literal for the '<em><b>Binding</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UIM_FIELD__BINDING = eINSTANCE.getUimField_Binding();

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
		 * The meta object literal for the '{@link org.opaeum.uim.impl.UimTabPanelImpl <em>Tab Panel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.impl.UimTabPanelImpl
		 * @see org.opaeum.uim.impl.UimPackageImpl#getUimTabPanel()
		 * @generated
		 */
		EClass UIM_TAB_PANEL = eINSTANCE.getUimTabPanel();

		/**
		 * The meta object literal for the '<em><b>Children</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UIM_TAB_PANEL__CHILDREN = eINSTANCE.getUimTabPanel_Children();

		/**
		 * The meta object literal for the '<em><b>Active Tab Index</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UIM_TAB_PANEL__ACTIVE_TAB_INDEX = eINSTANCE.getUimTabPanel_ActiveTabIndex();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.impl.UimTabImpl <em>Tab</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.impl.UimTabImpl
		 * @see org.opaeum.uim.impl.UimPackageImpl#getUimTab()
		 * @generated
		 */
		EClass UIM_TAB = eINSTANCE.getUimTab();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UIM_TAB__PARENT = eINSTANCE.getUimTab_Parent();

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
		 * The meta object literal for the '{@link org.opaeum.uim.MasterComponent <em>Master Component</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.MasterComponent
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
		 * The meta object literal for the '{@link org.opaeum.uim.impl.UimPanelImpl <em>Panel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.impl.UimPanelImpl
		 * @see org.opaeum.uim.impl.UimPackageImpl#getUimPanel()
		 * @generated
		 */
		EClass UIM_PANEL = eINSTANCE.getUimPanel();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.impl.ObjectSelectorTreeImpl <em>Object Selector Tree</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.impl.ObjectSelectorTreeImpl
		 * @see org.opaeum.uim.impl.UimPackageImpl#getObjectSelectorTree()
		 * @generated
		 */
		EClass OBJECT_SELECTOR_TREE = eINSTANCE.getObjectSelectorTree();

	}

} //UimPackage
