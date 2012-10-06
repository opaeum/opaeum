/**
 */
package org.opaeum.uim.editor;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.opaeum.uim.UimPackage;
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
 * @see org.opaeum.uim.editor.EditorFactory
 * @model kind="package"
 * @generated
 */
public interface EditorPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "editor";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://opaeum.org/uimetamodel/editor/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "editor";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	EditorPackage eINSTANCE = org.opaeum.uim.editor.impl.EditorPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.opaeum.uim.editor.impl.AbstractEditorImpl <em>Abstract Editor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.editor.impl.AbstractEditorImpl
	 * @see org.opaeum.uim.editor.impl.EditorPackageImpl#getAbstractEditor()
	 * @generated
	 */
	int ABSTRACT_EDITOR = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EDITOR__NAME = UimPackage.USER_INTERFACE_ROOT__NAME;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EDITOR__UNDER_USER_CONTROL = UimPackage.USER_INTERFACE_ROOT__UNDER_USER_CONTROL;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EDITOR__UML_ELEMENT_UID = UimPackage.USER_INTERFACE_ROOT__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Label Override</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EDITOR__LABEL_OVERRIDE = UimPackage.USER_INTERFACE_ROOT__LABEL_OVERRIDE;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EDITOR__EDITABILITY = UimPackage.USER_INTERFACE_ROOT__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EDITOR__VISIBILITY = UimPackage.USER_INTERFACE_ROOT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Ignored Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EDITOR__IGNORED_ELEMENTS = UimPackage.USER_INTERFACE_ROOT__IGNORED_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Super User Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EDITOR__SUPER_USER_INTERFACES = UimPackage.USER_INTERFACE_ROOT__SUPER_USER_INTERFACES;

	/**
	 * The feature id for the '<em><b>Page Ordering</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EDITOR__PAGE_ORDERING = UimPackage.USER_INTERFACE_ROOT__PAGE_ORDERING;

	/**
	 * The feature id for the '<em><b>Sub User Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EDITOR__SUB_USER_INTERFACES = UimPackage.USER_INTERFACE_ROOT__SUB_USER_INTERFACES;

	/**
	 * The feature id for the '<em><b>Pages</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EDITOR__PAGES = UimPackage.USER_INTERFACE_ROOT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Action Bar</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EDITOR__ACTION_BAR = UimPackage.USER_INTERFACE_ROOT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Abstract Editor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_EDITOR_FEATURE_COUNT = UimPackage.USER_INTERFACE_ROOT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.editor.impl.InstanceEditorImpl <em>Instance Editor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.editor.impl.InstanceEditorImpl
	 * @see org.opaeum.uim.editor.impl.EditorPackageImpl#getInstanceEditor()
	 * @generated
	 */
	int INSTANCE_EDITOR = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_EDITOR__NAME = ABSTRACT_EDITOR__NAME;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_EDITOR__UNDER_USER_CONTROL = ABSTRACT_EDITOR__UNDER_USER_CONTROL;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_EDITOR__UML_ELEMENT_UID = ABSTRACT_EDITOR__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Label Override</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_EDITOR__LABEL_OVERRIDE = ABSTRACT_EDITOR__LABEL_OVERRIDE;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_EDITOR__EDITABILITY = ABSTRACT_EDITOR__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_EDITOR__VISIBILITY = ABSTRACT_EDITOR__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Ignored Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_EDITOR__IGNORED_ELEMENTS = ABSTRACT_EDITOR__IGNORED_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Super User Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_EDITOR__SUPER_USER_INTERFACES = ABSTRACT_EDITOR__SUPER_USER_INTERFACES;

	/**
	 * The feature id for the '<em><b>Page Ordering</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_EDITOR__PAGE_ORDERING = ABSTRACT_EDITOR__PAGE_ORDERING;

	/**
	 * The feature id for the '<em><b>Sub User Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_EDITOR__SUB_USER_INTERFACES = ABSTRACT_EDITOR__SUB_USER_INTERFACES;

	/**
	 * The feature id for the '<em><b>Pages</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_EDITOR__PAGES = ABSTRACT_EDITOR__PAGES;

	/**
	 * The feature id for the '<em><b>Action Bar</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_EDITOR__ACTION_BAR = ABSTRACT_EDITOR__ACTION_BAR;

	/**
	 * The feature id for the '<em><b>Menu Configuration</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_EDITOR__MENU_CONFIGURATION = ABSTRACT_EDITOR_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Instance Editor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_EDITOR_FEATURE_COUNT = ABSTRACT_EDITOR_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.editor.impl.BehaviorExecutionEditorImpl <em>Behavior Execution Editor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.editor.impl.BehaviorExecutionEditorImpl
	 * @see org.opaeum.uim.editor.impl.EditorPackageImpl#getBehaviorExecutionEditor()
	 * @generated
	 */
	int BEHAVIOR_EXECUTION_EDITOR = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_EXECUTION_EDITOR__NAME = INSTANCE_EDITOR__NAME;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_EXECUTION_EDITOR__UNDER_USER_CONTROL = INSTANCE_EDITOR__UNDER_USER_CONTROL;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_EXECUTION_EDITOR__UML_ELEMENT_UID = INSTANCE_EDITOR__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Label Override</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_EXECUTION_EDITOR__LABEL_OVERRIDE = INSTANCE_EDITOR__LABEL_OVERRIDE;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_EXECUTION_EDITOR__EDITABILITY = INSTANCE_EDITOR__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_EXECUTION_EDITOR__VISIBILITY = INSTANCE_EDITOR__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Ignored Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_EXECUTION_EDITOR__IGNORED_ELEMENTS = INSTANCE_EDITOR__IGNORED_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Super User Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_EXECUTION_EDITOR__SUPER_USER_INTERFACES = INSTANCE_EDITOR__SUPER_USER_INTERFACES;

	/**
	 * The feature id for the '<em><b>Page Ordering</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_EXECUTION_EDITOR__PAGE_ORDERING = INSTANCE_EDITOR__PAGE_ORDERING;

	/**
	 * The feature id for the '<em><b>Sub User Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_EXECUTION_EDITOR__SUB_USER_INTERFACES = INSTANCE_EDITOR__SUB_USER_INTERFACES;

	/**
	 * The feature id for the '<em><b>Pages</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_EXECUTION_EDITOR__PAGES = INSTANCE_EDITOR__PAGES;

	/**
	 * The feature id for the '<em><b>Action Bar</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_EXECUTION_EDITOR__ACTION_BAR = INSTANCE_EDITOR__ACTION_BAR;

	/**
	 * The feature id for the '<em><b>Menu Configuration</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_EXECUTION_EDITOR__MENU_CONFIGURATION = INSTANCE_EDITOR__MENU_CONFIGURATION;

	/**
	 * The feature id for the '<em><b>Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_EXECUTION_EDITOR__MODEL = INSTANCE_EDITOR_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Behavior Execution Editor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_EXECUTION_EDITOR_FEATURE_COUNT = INSTANCE_EDITOR_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.editor.impl.EditorPageImpl <em>Page</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.editor.impl.EditorPageImpl
	 * @see org.opaeum.uim.editor.impl.EditorPackageImpl#getEditorPage()
	 * @generated
	 */
	int EDITOR_PAGE = 3;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITOR_PAGE__VISIBILITY = UimPackage.PAGE__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITOR_PAGE__EDITABILITY = UimPackage.PAGE__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITOR_PAGE__UML_ELEMENT_UID = UimPackage.PAGE__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITOR_PAGE__NAME = UimPackage.PAGE__NAME;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITOR_PAGE__UNDER_USER_CONTROL = UimPackage.PAGE__UNDER_USER_CONTROL;

	/**
	 * The feature id for the '<em><b>Label Override</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITOR_PAGE__LABEL_OVERRIDE = UimPackage.PAGE__LABEL_OVERRIDE;

	/**
	 * The feature id for the '<em><b>Panel</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITOR_PAGE__PANEL = UimPackage.PAGE__PANEL;

	/**
	 * The feature id for the '<em><b>Editor</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITOR_PAGE__EDITOR = UimPackage.PAGE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Page</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITOR_PAGE_FEATURE_COUNT = UimPackage.PAGE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.editor.impl.ActionBarImpl <em>Action Bar</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.editor.impl.ActionBarImpl
	 * @see org.opaeum.uim.editor.impl.EditorPackageImpl#getActionBar()
	 * @generated
	 */
	int ACTION_BAR = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_BAR__NAME = PanelPackage.ABSTRACT_PANEL__NAME;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_BAR__UNDER_USER_CONTROL = PanelPackage.ABSTRACT_PANEL__UNDER_USER_CONTROL;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_BAR__VISIBILITY = PanelPackage.ABSTRACT_PANEL__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_BAR__EDITABILITY = PanelPackage.ABSTRACT_PANEL__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_BAR__CHILDREN = PanelPackage.ABSTRACT_PANEL__CHILDREN;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_BAR__UML_ELEMENT_UID = PanelPackage.ABSTRACT_PANEL__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Label Override</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_BAR__LABEL_OVERRIDE = PanelPackage.ABSTRACT_PANEL__LABEL_OVERRIDE;

	/**
	 * The feature id for the '<em><b>Editor</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_BAR__EDITOR = PanelPackage.ABSTRACT_PANEL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Action Bar</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_BAR_FEATURE_COUNT = PanelPackage.ABSTRACT_PANEL_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.editor.impl.MenuConfigurationImpl <em>Menu Configuration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.editor.impl.MenuConfigurationImpl
	 * @see org.opaeum.uim.editor.impl.EditorPackageImpl#getMenuConfiguration()
	 * @generated
	 */
	int MENU_CONFIGURATION = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENU_CONFIGURATION__NAME = UimPackage.USER_INTERACTION_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENU_CONFIGURATION__UNDER_USER_CONTROL = UimPackage.USER_INTERACTION_ELEMENT__UNDER_USER_CONTROL;

	/**
	 * The feature id for the '<em><b>Editor</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENU_CONFIGURATION__EDITOR = UimPackage.USER_INTERACTION_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENU_CONFIGURATION__OPERATIONS = UimPackage.USER_INTERACTION_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Menu Configuration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENU_CONFIGURATION_FEATURE_COUNT = UimPackage.USER_INTERACTION_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.editor.impl.OperationMenuItemImpl <em>Operation Menu Item</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.editor.impl.OperationMenuItemImpl
	 * @see org.opaeum.uim.editor.impl.EditorPackageImpl#getOperationMenuItem()
	 * @generated
	 */
	int OPERATION_MENU_ITEM = 6;

	/**
	 * The feature id for the '<em><b>Requires Group Ownership</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_MENU_ITEM__REQUIRES_GROUP_OWNERSHIP = ConstraintPackage.USER_INTERACTION_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP;

	/**
	 * The feature id for the '<em><b>Requires Ownership</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_MENU_ITEM__REQUIRES_OWNERSHIP = ConstraintPackage.USER_INTERACTION_CONSTRAINT__REQUIRES_OWNERSHIP;

	/**
	 * The feature id for the '<em><b>Required Roles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_MENU_ITEM__REQUIRED_ROLES = ConstraintPackage.USER_INTERACTION_CONSTRAINT__REQUIRED_ROLES;

	/**
	 * The feature id for the '<em><b>Open To Public</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_MENU_ITEM__OPEN_TO_PUBLIC = ConstraintPackage.USER_INTERACTION_CONSTRAINT__OPEN_TO_PUBLIC;

	/**
	 * The feature id for the '<em><b>Required States</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_MENU_ITEM__REQUIRED_STATES = ConstraintPackage.USER_INTERACTION_CONSTRAINT__REQUIRED_STATES;

	/**
	 * The feature id for the '<em><b>Inherit From Parent</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_MENU_ITEM__INHERIT_FROM_PARENT = ConstraintPackage.USER_INTERACTION_CONSTRAINT__INHERIT_FROM_PARENT;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_MENU_ITEM__UML_ELEMENT_UID = ConstraintPackage.USER_INTERACTION_CONSTRAINT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_MENU_ITEM__NAME = ConstraintPackage.USER_INTERACTION_CONSTRAINT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_MENU_ITEM__UNDER_USER_CONTROL = ConstraintPackage.USER_INTERACTION_CONSTRAINT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Label Override</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_MENU_ITEM__LABEL_OVERRIDE = ConstraintPackage.USER_INTERACTION_CONSTRAINT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Menu Configuration</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_MENU_ITEM__MENU_CONFIGURATION = ConstraintPackage.USER_INTERACTION_CONSTRAINT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_MENU_ITEM__HIDDEN = ConstraintPackage.USER_INTERACTION_CONSTRAINT_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Operation Menu Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_MENU_ITEM_FEATURE_COUNT = ConstraintPackage.USER_INTERACTION_CONSTRAINT_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.editor.impl.ResponsibilityViewerImpl <em>Responsibility Viewer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.editor.impl.ResponsibilityViewerImpl
	 * @see org.opaeum.uim.editor.impl.EditorPackageImpl#getResponsibilityViewer()
	 * @generated
	 */
	int RESPONSIBILITY_VIEWER = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBILITY_VIEWER__NAME = ABSTRACT_EDITOR__NAME;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBILITY_VIEWER__UNDER_USER_CONTROL = ABSTRACT_EDITOR__UNDER_USER_CONTROL;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBILITY_VIEWER__UML_ELEMENT_UID = ABSTRACT_EDITOR__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Label Override</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBILITY_VIEWER__LABEL_OVERRIDE = ABSTRACT_EDITOR__LABEL_OVERRIDE;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBILITY_VIEWER__EDITABILITY = ABSTRACT_EDITOR__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBILITY_VIEWER__VISIBILITY = ABSTRACT_EDITOR__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Ignored Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBILITY_VIEWER__IGNORED_ELEMENTS = ABSTRACT_EDITOR__IGNORED_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Super User Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBILITY_VIEWER__SUPER_USER_INTERFACES = ABSTRACT_EDITOR__SUPER_USER_INTERFACES;

	/**
	 * The feature id for the '<em><b>Page Ordering</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBILITY_VIEWER__PAGE_ORDERING = ABSTRACT_EDITOR__PAGE_ORDERING;

	/**
	 * The feature id for the '<em><b>Sub User Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBILITY_VIEWER__SUB_USER_INTERFACES = ABSTRACT_EDITOR__SUB_USER_INTERFACES;

	/**
	 * The feature id for the '<em><b>Pages</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBILITY_VIEWER__PAGES = ABSTRACT_EDITOR__PAGES;

	/**
	 * The feature id for the '<em><b>Action Bar</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBILITY_VIEWER__ACTION_BAR = ABSTRACT_EDITOR__ACTION_BAR;

	/**
	 * The feature id for the '<em><b>Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBILITY_VIEWER__MODEL = ABSTRACT_EDITOR_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Responsibility Viewer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBILITY_VIEWER_FEATURE_COUNT = ABSTRACT_EDITOR_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.editor.impl.QueryResultPageImpl <em>Query Result Page</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.editor.impl.QueryResultPageImpl
	 * @see org.opaeum.uim.editor.impl.EditorPackageImpl#getQueryResultPage()
	 * @generated
	 */
	int QUERY_RESULT_PAGE = 8;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_RESULT_PAGE__VISIBILITY = UimPackage.PAGE__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_RESULT_PAGE__EDITABILITY = UimPackage.PAGE__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_RESULT_PAGE__UML_ELEMENT_UID = UimPackage.PAGE__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_RESULT_PAGE__NAME = UimPackage.PAGE__NAME;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_RESULT_PAGE__UNDER_USER_CONTROL = UimPackage.PAGE__UNDER_USER_CONTROL;

	/**
	 * The feature id for the '<em><b>Label Override</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_RESULT_PAGE__LABEL_OVERRIDE = UimPackage.PAGE__LABEL_OVERRIDE;

	/**
	 * The feature id for the '<em><b>Panel</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_RESULT_PAGE__PANEL = UimPackage.PAGE__PANEL;

	/**
	 * The number of structural features of the '<em>Query Result Page</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_RESULT_PAGE_FEATURE_COUNT = UimPackage.PAGE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.editor.impl.ObjectEditorImpl <em>Object Editor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.editor.impl.ObjectEditorImpl
	 * @see org.opaeum.uim.editor.impl.EditorPackageImpl#getObjectEditor()
	 * @generated
	 */
	int OBJECT_EDITOR = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_EDITOR__NAME = INSTANCE_EDITOR__NAME;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_EDITOR__UNDER_USER_CONTROL = INSTANCE_EDITOR__UNDER_USER_CONTROL;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_EDITOR__UML_ELEMENT_UID = INSTANCE_EDITOR__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Label Override</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_EDITOR__LABEL_OVERRIDE = INSTANCE_EDITOR__LABEL_OVERRIDE;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_EDITOR__EDITABILITY = INSTANCE_EDITOR__EDITABILITY;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_EDITOR__VISIBILITY = INSTANCE_EDITOR__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Ignored Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_EDITOR__IGNORED_ELEMENTS = INSTANCE_EDITOR__IGNORED_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Super User Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_EDITOR__SUPER_USER_INTERFACES = INSTANCE_EDITOR__SUPER_USER_INTERFACES;

	/**
	 * The feature id for the '<em><b>Page Ordering</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_EDITOR__PAGE_ORDERING = INSTANCE_EDITOR__PAGE_ORDERING;

	/**
	 * The feature id for the '<em><b>Sub User Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_EDITOR__SUB_USER_INTERFACES = INSTANCE_EDITOR__SUB_USER_INTERFACES;

	/**
	 * The feature id for the '<em><b>Pages</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_EDITOR__PAGES = INSTANCE_EDITOR__PAGES;

	/**
	 * The feature id for the '<em><b>Action Bar</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_EDITOR__ACTION_BAR = INSTANCE_EDITOR__ACTION_BAR;

	/**
	 * The feature id for the '<em><b>Menu Configuration</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_EDITOR__MENU_CONFIGURATION = INSTANCE_EDITOR__MENU_CONFIGURATION;

	/**
	 * The feature id for the '<em><b>Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_EDITOR__MODEL = INSTANCE_EDITOR_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Object Editor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_EDITOR_FEATURE_COUNT = INSTANCE_EDITOR_FEATURE_COUNT + 1;


	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.editor.AbstractEditor <em>Abstract Editor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Editor</em>'.
	 * @see org.opaeum.uim.editor.AbstractEditor
	 * @generated
	 */
	EClass getAbstractEditor();

	/**
	 * Returns the meta object for the containment reference list '{@link org.opaeum.uim.editor.AbstractEditor#getPages <em>Pages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Pages</em>'.
	 * @see org.opaeum.uim.editor.AbstractEditor#getPages()
	 * @see #getAbstractEditor()
	 * @generated
	 */
	EReference getAbstractEditor_Pages();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.editor.AbstractEditor#getActionBar <em>Action Bar</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Action Bar</em>'.
	 * @see org.opaeum.uim.editor.AbstractEditor#getActionBar()
	 * @see #getAbstractEditor()
	 * @generated
	 */
	EReference getAbstractEditor_ActionBar();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.editor.InstanceEditor <em>Instance Editor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Instance Editor</em>'.
	 * @see org.opaeum.uim.editor.InstanceEditor
	 * @generated
	 */
	EClass getInstanceEditor();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.editor.InstanceEditor#getMenuConfiguration <em>Menu Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Menu Configuration</em>'.
	 * @see org.opaeum.uim.editor.InstanceEditor#getMenuConfiguration()
	 * @see #getInstanceEditor()
	 * @generated
	 */
	EReference getInstanceEditor_MenuConfiguration();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.editor.BehaviorExecutionEditor <em>Behavior Execution Editor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Behavior Execution Editor</em>'.
	 * @see org.opaeum.uim.editor.BehaviorExecutionEditor
	 * @generated
	 */
	EClass getBehaviorExecutionEditor();

	/**
	 * Returns the meta object for the container reference '{@link org.opaeum.uim.editor.BehaviorExecutionEditor#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Model</em>'.
	 * @see org.opaeum.uim.editor.BehaviorExecutionEditor#getModel()
	 * @see #getBehaviorExecutionEditor()
	 * @generated
	 */
	EReference getBehaviorExecutionEditor_Model();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.editor.EditorPage <em>Page</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Page</em>'.
	 * @see org.opaeum.uim.editor.EditorPage
	 * @generated
	 */
	EClass getEditorPage();

	/**
	 * Returns the meta object for the container reference '{@link org.opaeum.uim.editor.EditorPage#getEditor <em>Editor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Editor</em>'.
	 * @see org.opaeum.uim.editor.EditorPage#getEditor()
	 * @see #getEditorPage()
	 * @generated
	 */
	EReference getEditorPage_Editor();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.editor.ActionBar <em>Action Bar</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Action Bar</em>'.
	 * @see org.opaeum.uim.editor.ActionBar
	 * @generated
	 */
	EClass getActionBar();

	/**
	 * Returns the meta object for the container reference '{@link org.opaeum.uim.editor.ActionBar#getEditor <em>Editor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Editor</em>'.
	 * @see org.opaeum.uim.editor.ActionBar#getEditor()
	 * @see #getActionBar()
	 * @generated
	 */
	EReference getActionBar_Editor();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.editor.MenuConfiguration <em>Menu Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Menu Configuration</em>'.
	 * @see org.opaeum.uim.editor.MenuConfiguration
	 * @generated
	 */
	EClass getMenuConfiguration();

	/**
	 * Returns the meta object for the container reference '{@link org.opaeum.uim.editor.MenuConfiguration#getEditor <em>Editor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Editor</em>'.
	 * @see org.opaeum.uim.editor.MenuConfiguration#getEditor()
	 * @see #getMenuConfiguration()
	 * @generated
	 */
	EReference getMenuConfiguration_Editor();

	/**
	 * Returns the meta object for the containment reference list '{@link org.opaeum.uim.editor.MenuConfiguration#getOperations <em>Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Operations</em>'.
	 * @see org.opaeum.uim.editor.MenuConfiguration#getOperations()
	 * @see #getMenuConfiguration()
	 * @generated
	 */
	EReference getMenuConfiguration_Operations();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.editor.OperationMenuItem <em>Operation Menu Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation Menu Item</em>'.
	 * @see org.opaeum.uim.editor.OperationMenuItem
	 * @generated
	 */
	EClass getOperationMenuItem();

	/**
	 * Returns the meta object for the container reference '{@link org.opaeum.uim.editor.OperationMenuItem#getMenuConfiguration <em>Menu Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Menu Configuration</em>'.
	 * @see org.opaeum.uim.editor.OperationMenuItem#getMenuConfiguration()
	 * @see #getOperationMenuItem()
	 * @generated
	 */
	EReference getOperationMenuItem_MenuConfiguration();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.uim.editor.OperationMenuItem#isHidden <em>Hidden</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Hidden</em>'.
	 * @see org.opaeum.uim.editor.OperationMenuItem#isHidden()
	 * @see #getOperationMenuItem()
	 * @generated
	 */
	EAttribute getOperationMenuItem_Hidden();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.editor.ResponsibilityViewer <em>Responsibility Viewer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Responsibility Viewer</em>'.
	 * @see org.opaeum.uim.editor.ResponsibilityViewer
	 * @generated
	 */
	EClass getResponsibilityViewer();

	/**
	 * Returns the meta object for the container reference '{@link org.opaeum.uim.editor.ResponsibilityViewer#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Model</em>'.
	 * @see org.opaeum.uim.editor.ResponsibilityViewer#getModel()
	 * @see #getResponsibilityViewer()
	 * @generated
	 */
	EReference getResponsibilityViewer_Model();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.editor.QueryResultPage <em>Query Result Page</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Query Result Page</em>'.
	 * @see org.opaeum.uim.editor.QueryResultPage
	 * @generated
	 */
	EClass getQueryResultPage();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.editor.ObjectEditor <em>Object Editor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Object Editor</em>'.
	 * @see org.opaeum.uim.editor.ObjectEditor
	 * @generated
	 */
	EClass getObjectEditor();

	/**
	 * Returns the meta object for the container reference '{@link org.opaeum.uim.editor.ObjectEditor#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Model</em>'.
	 * @see org.opaeum.uim.editor.ObjectEditor#getModel()
	 * @see #getObjectEditor()
	 * @generated
	 */
	EReference getObjectEditor_Model();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	EditorFactory getEditorFactory();

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
		 * The meta object literal for the '{@link org.opaeum.uim.editor.impl.AbstractEditorImpl <em>Abstract Editor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.editor.impl.AbstractEditorImpl
		 * @see org.opaeum.uim.editor.impl.EditorPackageImpl#getAbstractEditor()
		 * @generated
		 */
		EClass ABSTRACT_EDITOR = eINSTANCE.getAbstractEditor();

		/**
		 * The meta object literal for the '<em><b>Pages</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_EDITOR__PAGES = eINSTANCE.getAbstractEditor_Pages();

		/**
		 * The meta object literal for the '<em><b>Action Bar</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_EDITOR__ACTION_BAR = eINSTANCE.getAbstractEditor_ActionBar();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.editor.impl.InstanceEditorImpl <em>Instance Editor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.editor.impl.InstanceEditorImpl
		 * @see org.opaeum.uim.editor.impl.EditorPackageImpl#getInstanceEditor()
		 * @generated
		 */
		EClass INSTANCE_EDITOR = eINSTANCE.getInstanceEditor();

		/**
		 * The meta object literal for the '<em><b>Menu Configuration</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INSTANCE_EDITOR__MENU_CONFIGURATION = eINSTANCE.getInstanceEditor_MenuConfiguration();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.editor.impl.BehaviorExecutionEditorImpl <em>Behavior Execution Editor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.editor.impl.BehaviorExecutionEditorImpl
		 * @see org.opaeum.uim.editor.impl.EditorPackageImpl#getBehaviorExecutionEditor()
		 * @generated
		 */
		EClass BEHAVIOR_EXECUTION_EDITOR = eINSTANCE.getBehaviorExecutionEditor();

		/**
		 * The meta object literal for the '<em><b>Model</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BEHAVIOR_EXECUTION_EDITOR__MODEL = eINSTANCE.getBehaviorExecutionEditor_Model();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.editor.impl.EditorPageImpl <em>Page</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.editor.impl.EditorPageImpl
		 * @see org.opaeum.uim.editor.impl.EditorPackageImpl#getEditorPage()
		 * @generated
		 */
		EClass EDITOR_PAGE = eINSTANCE.getEditorPage();

		/**
		 * The meta object literal for the '<em><b>Editor</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EDITOR_PAGE__EDITOR = eINSTANCE.getEditorPage_Editor();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.editor.impl.ActionBarImpl <em>Action Bar</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.editor.impl.ActionBarImpl
		 * @see org.opaeum.uim.editor.impl.EditorPackageImpl#getActionBar()
		 * @generated
		 */
		EClass ACTION_BAR = eINSTANCE.getActionBar();

		/**
		 * The meta object literal for the '<em><b>Editor</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION_BAR__EDITOR = eINSTANCE.getActionBar_Editor();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.editor.impl.MenuConfigurationImpl <em>Menu Configuration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.editor.impl.MenuConfigurationImpl
		 * @see org.opaeum.uim.editor.impl.EditorPackageImpl#getMenuConfiguration()
		 * @generated
		 */
		EClass MENU_CONFIGURATION = eINSTANCE.getMenuConfiguration();

		/**
		 * The meta object literal for the '<em><b>Editor</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MENU_CONFIGURATION__EDITOR = eINSTANCE.getMenuConfiguration_Editor();

		/**
		 * The meta object literal for the '<em><b>Operations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MENU_CONFIGURATION__OPERATIONS = eINSTANCE.getMenuConfiguration_Operations();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.editor.impl.OperationMenuItemImpl <em>Operation Menu Item</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.editor.impl.OperationMenuItemImpl
		 * @see org.opaeum.uim.editor.impl.EditorPackageImpl#getOperationMenuItem()
		 * @generated
		 */
		EClass OPERATION_MENU_ITEM = eINSTANCE.getOperationMenuItem();

		/**
		 * The meta object literal for the '<em><b>Menu Configuration</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION_MENU_ITEM__MENU_CONFIGURATION = eINSTANCE.getOperationMenuItem_MenuConfiguration();

		/**
		 * The meta object literal for the '<em><b>Hidden</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OPERATION_MENU_ITEM__HIDDEN = eINSTANCE.getOperationMenuItem_Hidden();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.editor.impl.ResponsibilityViewerImpl <em>Responsibility Viewer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.editor.impl.ResponsibilityViewerImpl
		 * @see org.opaeum.uim.editor.impl.EditorPackageImpl#getResponsibilityViewer()
		 * @generated
		 */
		EClass RESPONSIBILITY_VIEWER = eINSTANCE.getResponsibilityViewer();

		/**
		 * The meta object literal for the '<em><b>Model</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESPONSIBILITY_VIEWER__MODEL = eINSTANCE.getResponsibilityViewer_Model();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.editor.impl.QueryResultPageImpl <em>Query Result Page</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.editor.impl.QueryResultPageImpl
		 * @see org.opaeum.uim.editor.impl.EditorPackageImpl#getQueryResultPage()
		 * @generated
		 */
		EClass QUERY_RESULT_PAGE = eINSTANCE.getQueryResultPage();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.editor.impl.ObjectEditorImpl <em>Object Editor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.editor.impl.ObjectEditorImpl
		 * @see org.opaeum.uim.editor.impl.EditorPackageImpl#getObjectEditor()
		 * @generated
		 */
		EClass OBJECT_EDITOR = eINSTANCE.getObjectEditor();

		/**
		 * The meta object literal for the '<em><b>Model</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OBJECT_EDITOR__MODEL = eINSTANCE.getObjectEditor_Model();

	}

} //EditorPackage
