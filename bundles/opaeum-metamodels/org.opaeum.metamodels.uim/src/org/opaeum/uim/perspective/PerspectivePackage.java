/**
 */
package org.opaeum.uim.perspective;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
 * @see org.opaeum.uim.perspective.PerspectiveFactory
 * @model kind="package"
 * @generated
 */
public interface PerspectivePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "perspective";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://opaeum.org/uimetamodel/perspective/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "persp";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	PerspectivePackage eINSTANCE = org.opaeum.uim.perspective.impl.PerspectivePackageImpl.init();

	/**
	 * The meta object id for the '{@link org.opaeum.uim.perspective.impl.PerspectiveConfigurationImpl <em>Configuration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.perspective.impl.PerspectiveConfigurationImpl
	 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getPerspectiveConfiguration()
	 * @generated
	 */
	int PERSPECTIVE_CONFIGURATION = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSPECTIVE_CONFIGURATION__NAME = UimPackage.USER_INTERACTION_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSPECTIVE_CONFIGURATION__UNDER_USER_CONTROL = UimPackage.USER_INTERACTION_ELEMENT__UNDER_USER_CONTROL;

	/**
	 * The feature id for the '<em><b>Explorer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSPECTIVE_CONFIGURATION__EXPLORER = UimPackage.USER_INTERACTION_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Editor</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSPECTIVE_CONFIGURATION__EDITOR = UimPackage.USER_INTERACTION_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSPECTIVE_CONFIGURATION__PROPERTIES = UimPackage.USER_INTERACTION_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Inbox</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSPECTIVE_CONFIGURATION__INBOX = UimPackage.USER_INTERACTION_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Outbox</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSPECTIVE_CONFIGURATION__OUTBOX = UimPackage.USER_INTERACTION_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Configuration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSPECTIVE_CONFIGURATION_FEATURE_COUNT = UimPackage.USER_INTERACTION_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.perspective.impl.ViewAllocationImpl <em>View Allocation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.perspective.impl.ViewAllocationImpl
	 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getViewAllocation()
	 * @generated
	 */
	int VIEW_ALLOCATION = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_ALLOCATION__NAME = UimPackage.USER_INTERACTION_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_ALLOCATION__UNDER_USER_CONTROL = UimPackage.USER_INTERACTION_ELEMENT__UNDER_USER_CONTROL;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_ALLOCATION__WIDTH = UimPackage.USER_INTERACTION_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_ALLOCATION__HEIGHT = UimPackage.USER_INTERACTION_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Position</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_ALLOCATION__POSITION = UimPackage.USER_INTERACTION_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>View Allocation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_ALLOCATION_FEATURE_COUNT = UimPackage.USER_INTERACTION_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.perspective.impl.NavigatorConfigurationImpl <em>Navigator Configuration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.perspective.impl.NavigatorConfigurationImpl
	 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getNavigatorConfiguration()
	 * @generated
	 */
	int NAVIGATOR_CONFIGURATION = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATOR_CONFIGURATION__NAME = VIEW_ALLOCATION__NAME;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATOR_CONFIGURATION__UNDER_USER_CONTROL = VIEW_ALLOCATION__UNDER_USER_CONTROL;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATOR_CONFIGURATION__WIDTH = VIEW_ALLOCATION__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATOR_CONFIGURATION__HEIGHT = VIEW_ALLOCATION__HEIGHT;

	/**
	 * The feature id for the '<em><b>Position</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATOR_CONFIGURATION__POSITION = VIEW_ALLOCATION__POSITION;

	/**
	 * The feature id for the '<em><b>Classes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATOR_CONFIGURATION__CLASSES = VIEW_ALLOCATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Navigator Configuration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATOR_CONFIGURATION_FEATURE_COUNT = VIEW_ALLOCATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.perspective.impl.NavigationConstraintImpl <em>Navigation Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.perspective.impl.NavigationConstraintImpl
	 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getNavigationConstraint()
	 * @generated
	 */
	int NAVIGATION_CONSTRAINT = 7;

	/**
	 * The feature id for the '<em><b>Requires Group Ownership</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP = ConstraintPackage.USER_INTERACTION_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP;

	/**
	 * The feature id for the '<em><b>Requires Ownership</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_CONSTRAINT__REQUIRES_OWNERSHIP = ConstraintPackage.USER_INTERACTION_CONSTRAINT__REQUIRES_OWNERSHIP;

	/**
	 * The feature id for the '<em><b>Required Roles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_CONSTRAINT__REQUIRED_ROLES = ConstraintPackage.USER_INTERACTION_CONSTRAINT__REQUIRED_ROLES;

	/**
	 * The feature id for the '<em><b>Open To Public</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_CONSTRAINT__OPEN_TO_PUBLIC = ConstraintPackage.USER_INTERACTION_CONSTRAINT__OPEN_TO_PUBLIC;

	/**
	 * The feature id for the '<em><b>Required States</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_CONSTRAINT__REQUIRED_STATES = ConstraintPackage.USER_INTERACTION_CONSTRAINT__REQUIRED_STATES;

	/**
	 * The feature id for the '<em><b>Inherit From Parent</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_CONSTRAINT__INHERIT_FROM_PARENT = ConstraintPackage.USER_INTERACTION_CONSTRAINT__INHERIT_FROM_PARENT;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_CONSTRAINT__NAME = ConstraintPackage.USER_INTERACTION_CONSTRAINT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_CONSTRAINT__UNDER_USER_CONTROL = ConstraintPackage.USER_INTERACTION_CONSTRAINT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_CONSTRAINT__UML_ELEMENT_UID = ConstraintPackage.USER_INTERACTION_CONSTRAINT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Label Override</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_CONSTRAINT__LABEL_OVERRIDE = ConstraintPackage.USER_INTERACTION_CONSTRAINT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_CONSTRAINT__HIDDEN = ConstraintPackage.USER_INTERACTION_CONSTRAINT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Navigation Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_CONSTRAINT_FEATURE_COUNT = ConstraintPackage.USER_INTERACTION_CONSTRAINT_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.perspective.impl.ClassNavigationConstraintImpl <em>Class Navigation Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.perspective.impl.ClassNavigationConstraintImpl
	 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getClassNavigationConstraint()
	 * @generated
	 */
	int CLASS_NAVIGATION_CONSTRAINT = 3;

	/**
	 * The feature id for the '<em><b>Requires Group Ownership</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_NAVIGATION_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP = NAVIGATION_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP;

	/**
	 * The feature id for the '<em><b>Requires Ownership</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_NAVIGATION_CONSTRAINT__REQUIRES_OWNERSHIP = NAVIGATION_CONSTRAINT__REQUIRES_OWNERSHIP;

	/**
	 * The feature id for the '<em><b>Required Roles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_NAVIGATION_CONSTRAINT__REQUIRED_ROLES = NAVIGATION_CONSTRAINT__REQUIRED_ROLES;

	/**
	 * The feature id for the '<em><b>Open To Public</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_NAVIGATION_CONSTRAINT__OPEN_TO_PUBLIC = NAVIGATION_CONSTRAINT__OPEN_TO_PUBLIC;

	/**
	 * The feature id for the '<em><b>Required States</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_NAVIGATION_CONSTRAINT__REQUIRED_STATES = NAVIGATION_CONSTRAINT__REQUIRED_STATES;

	/**
	 * The feature id for the '<em><b>Inherit From Parent</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_NAVIGATION_CONSTRAINT__INHERIT_FROM_PARENT = NAVIGATION_CONSTRAINT__INHERIT_FROM_PARENT;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_NAVIGATION_CONSTRAINT__NAME = NAVIGATION_CONSTRAINT__NAME;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_NAVIGATION_CONSTRAINT__UNDER_USER_CONTROL = NAVIGATION_CONSTRAINT__UNDER_USER_CONTROL;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_NAVIGATION_CONSTRAINT__UML_ELEMENT_UID = NAVIGATION_CONSTRAINT__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Label Override</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_NAVIGATION_CONSTRAINT__LABEL_OVERRIDE = NAVIGATION_CONSTRAINT__LABEL_OVERRIDE;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_NAVIGATION_CONSTRAINT__HIDDEN = NAVIGATION_CONSTRAINT__HIDDEN;

	/**
	 * The feature id for the '<em><b>Explorer Configuration</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_NAVIGATION_CONSTRAINT__EXPLORER_CONFIGURATION = NAVIGATION_CONSTRAINT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_NAVIGATION_CONSTRAINT__PROPERTIES = NAVIGATION_CONSTRAINT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Behaviors</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_NAVIGATION_CONSTRAINT__BEHAVIORS = NAVIGATION_CONSTRAINT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_NAVIGATION_CONSTRAINT__OPERATIONS = NAVIGATION_CONSTRAINT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Class Navigation Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_NAVIGATION_CONSTRAINT_FEATURE_COUNT = NAVIGATION_CONSTRAINT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.perspective.impl.PropertyNavigationConstraintImpl <em>Property Navigation Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.perspective.impl.PropertyNavigationConstraintImpl
	 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getPropertyNavigationConstraint()
	 * @generated
	 */
	int PROPERTY_NAVIGATION_CONSTRAINT = 4;

	/**
	 * The feature id for the '<em><b>Requires Group Ownership</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_NAVIGATION_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP = NAVIGATION_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP;

	/**
	 * The feature id for the '<em><b>Requires Ownership</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_NAVIGATION_CONSTRAINT__REQUIRES_OWNERSHIP = NAVIGATION_CONSTRAINT__REQUIRES_OWNERSHIP;

	/**
	 * The feature id for the '<em><b>Required Roles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_NAVIGATION_CONSTRAINT__REQUIRED_ROLES = NAVIGATION_CONSTRAINT__REQUIRED_ROLES;

	/**
	 * The feature id for the '<em><b>Open To Public</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_NAVIGATION_CONSTRAINT__OPEN_TO_PUBLIC = NAVIGATION_CONSTRAINT__OPEN_TO_PUBLIC;

	/**
	 * The feature id for the '<em><b>Required States</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_NAVIGATION_CONSTRAINT__REQUIRED_STATES = NAVIGATION_CONSTRAINT__REQUIRED_STATES;

	/**
	 * The feature id for the '<em><b>Inherit From Parent</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_NAVIGATION_CONSTRAINT__INHERIT_FROM_PARENT = NAVIGATION_CONSTRAINT__INHERIT_FROM_PARENT;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_NAVIGATION_CONSTRAINT__NAME = NAVIGATION_CONSTRAINT__NAME;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_NAVIGATION_CONSTRAINT__UNDER_USER_CONTROL = NAVIGATION_CONSTRAINT__UNDER_USER_CONTROL;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_NAVIGATION_CONSTRAINT__UML_ELEMENT_UID = NAVIGATION_CONSTRAINT__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Label Override</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_NAVIGATION_CONSTRAINT__LABEL_OVERRIDE = NAVIGATION_CONSTRAINT__LABEL_OVERRIDE;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_NAVIGATION_CONSTRAINT__HIDDEN = NAVIGATION_CONSTRAINT__HIDDEN;

	/**
	 * The feature id for the '<em><b>Remove Constraint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_NAVIGATION_CONSTRAINT__REMOVE_CONSTRAINT = NAVIGATION_CONSTRAINT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Add Constraint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_NAVIGATION_CONSTRAINT__ADD_CONSTRAINT = NAVIGATION_CONSTRAINT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_NAVIGATION_CONSTRAINT__OWNER = NAVIGATION_CONSTRAINT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Property Navigation Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_NAVIGATION_CONSTRAINT_FEATURE_COUNT = NAVIGATION_CONSTRAINT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.perspective.impl.EditorConfigurationImpl <em>Editor Configuration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.perspective.impl.EditorConfigurationImpl
	 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getEditorConfiguration()
	 * @generated
	 */
	int EDITOR_CONFIGURATION = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITOR_CONFIGURATION__NAME = VIEW_ALLOCATION__NAME;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITOR_CONFIGURATION__UNDER_USER_CONTROL = VIEW_ALLOCATION__UNDER_USER_CONTROL;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITOR_CONFIGURATION__WIDTH = VIEW_ALLOCATION__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITOR_CONFIGURATION__HEIGHT = VIEW_ALLOCATION__HEIGHT;

	/**
	 * The feature id for the '<em><b>Position</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITOR_CONFIGURATION__POSITION = VIEW_ALLOCATION__POSITION;

	/**
	 * The number of structural features of the '<em>Editor Configuration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITOR_CONFIGURATION_FEATURE_COUNT = VIEW_ALLOCATION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.perspective.impl.PropertiesConfigurationImpl <em>Properties Configuration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.perspective.impl.PropertiesConfigurationImpl
	 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getPropertiesConfiguration()
	 * @generated
	 */
	int PROPERTIES_CONFIGURATION = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTIES_CONFIGURATION__NAME = VIEW_ALLOCATION__NAME;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTIES_CONFIGURATION__UNDER_USER_CONTROL = VIEW_ALLOCATION__UNDER_USER_CONTROL;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTIES_CONFIGURATION__WIDTH = VIEW_ALLOCATION__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTIES_CONFIGURATION__HEIGHT = VIEW_ALLOCATION__HEIGHT;

	/**
	 * The feature id for the '<em><b>Position</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTIES_CONFIGURATION__POSITION = VIEW_ALLOCATION__POSITION;

	/**
	 * The number of structural features of the '<em>Properties Configuration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTIES_CONFIGURATION_FEATURE_COUNT = VIEW_ALLOCATION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.perspective.impl.OperationNavigationConstraintImpl <em>Operation Navigation Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.perspective.impl.OperationNavigationConstraintImpl
	 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getOperationNavigationConstraint()
	 * @generated
	 */
	int OPERATION_NAVIGATION_CONSTRAINT = 8;

	/**
	 * The feature id for the '<em><b>Requires Group Ownership</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NAVIGATION_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP = NAVIGATION_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP;

	/**
	 * The feature id for the '<em><b>Requires Ownership</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NAVIGATION_CONSTRAINT__REQUIRES_OWNERSHIP = NAVIGATION_CONSTRAINT__REQUIRES_OWNERSHIP;

	/**
	 * The feature id for the '<em><b>Required Roles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NAVIGATION_CONSTRAINT__REQUIRED_ROLES = NAVIGATION_CONSTRAINT__REQUIRED_ROLES;

	/**
	 * The feature id for the '<em><b>Open To Public</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NAVIGATION_CONSTRAINT__OPEN_TO_PUBLIC = NAVIGATION_CONSTRAINT__OPEN_TO_PUBLIC;

	/**
	 * The feature id for the '<em><b>Required States</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NAVIGATION_CONSTRAINT__REQUIRED_STATES = NAVIGATION_CONSTRAINT__REQUIRED_STATES;

	/**
	 * The feature id for the '<em><b>Inherit From Parent</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NAVIGATION_CONSTRAINT__INHERIT_FROM_PARENT = NAVIGATION_CONSTRAINT__INHERIT_FROM_PARENT;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NAVIGATION_CONSTRAINT__NAME = NAVIGATION_CONSTRAINT__NAME;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NAVIGATION_CONSTRAINT__UNDER_USER_CONTROL = NAVIGATION_CONSTRAINT__UNDER_USER_CONTROL;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NAVIGATION_CONSTRAINT__UML_ELEMENT_UID = NAVIGATION_CONSTRAINT__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Label Override</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NAVIGATION_CONSTRAINT__LABEL_OVERRIDE = NAVIGATION_CONSTRAINT__LABEL_OVERRIDE;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NAVIGATION_CONSTRAINT__HIDDEN = NAVIGATION_CONSTRAINT__HIDDEN;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NAVIGATION_CONSTRAINT__OWNER = NAVIGATION_CONSTRAINT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Invocation Constraint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NAVIGATION_CONSTRAINT__INVOCATION_CONSTRAINT = NAVIGATION_CONSTRAINT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NAVIGATION_CONSTRAINT__PARAMETERS = NAVIGATION_CONSTRAINT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Operation Navigation Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_NAVIGATION_CONSTRAINT_FEATURE_COUNT = NAVIGATION_CONSTRAINT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.perspective.impl.BehaviorNavigationConstraintImpl <em>Behavior Navigation Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.perspective.impl.BehaviorNavigationConstraintImpl
	 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getBehaviorNavigationConstraint()
	 * @generated
	 */
	int BEHAVIOR_NAVIGATION_CONSTRAINT = 9;

	/**
	 * The feature id for the '<em><b>Requires Group Ownership</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_NAVIGATION_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP = NAVIGATION_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP;

	/**
	 * The feature id for the '<em><b>Requires Ownership</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_NAVIGATION_CONSTRAINT__REQUIRES_OWNERSHIP = NAVIGATION_CONSTRAINT__REQUIRES_OWNERSHIP;

	/**
	 * The feature id for the '<em><b>Required Roles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_NAVIGATION_CONSTRAINT__REQUIRED_ROLES = NAVIGATION_CONSTRAINT__REQUIRED_ROLES;

	/**
	 * The feature id for the '<em><b>Open To Public</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_NAVIGATION_CONSTRAINT__OPEN_TO_PUBLIC = NAVIGATION_CONSTRAINT__OPEN_TO_PUBLIC;

	/**
	 * The feature id for the '<em><b>Required States</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_NAVIGATION_CONSTRAINT__REQUIRED_STATES = NAVIGATION_CONSTRAINT__REQUIRED_STATES;

	/**
	 * The feature id for the '<em><b>Inherit From Parent</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_NAVIGATION_CONSTRAINT__INHERIT_FROM_PARENT = NAVIGATION_CONSTRAINT__INHERIT_FROM_PARENT;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_NAVIGATION_CONSTRAINT__NAME = NAVIGATION_CONSTRAINT__NAME;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_NAVIGATION_CONSTRAINT__UNDER_USER_CONTROL = NAVIGATION_CONSTRAINT__UNDER_USER_CONTROL;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_NAVIGATION_CONSTRAINT__UML_ELEMENT_UID = NAVIGATION_CONSTRAINT__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Label Override</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_NAVIGATION_CONSTRAINT__LABEL_OVERRIDE = NAVIGATION_CONSTRAINT__LABEL_OVERRIDE;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_NAVIGATION_CONSTRAINT__HIDDEN = NAVIGATION_CONSTRAINT__HIDDEN;

	/**
	 * The feature id for the '<em><b>Invocation Constraint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_NAVIGATION_CONSTRAINT__INVOCATION_CONSTRAINT = NAVIGATION_CONSTRAINT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_NAVIGATION_CONSTRAINT__OWNER = NAVIGATION_CONSTRAINT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_NAVIGATION_CONSTRAINT__PARAMETERS = NAVIGATION_CONSTRAINT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Behavior Navigation Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_NAVIGATION_CONSTRAINT_FEATURE_COUNT = NAVIGATION_CONSTRAINT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.perspective.impl.InboxConfigurationImpl <em>Inbox Configuration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.perspective.impl.InboxConfigurationImpl
	 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getInboxConfiguration()
	 * @generated
	 */
	int INBOX_CONFIGURATION = 10;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INBOX_CONFIGURATION__NAME = VIEW_ALLOCATION__NAME;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INBOX_CONFIGURATION__UNDER_USER_CONTROL = VIEW_ALLOCATION__UNDER_USER_CONTROL;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INBOX_CONFIGURATION__WIDTH = VIEW_ALLOCATION__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INBOX_CONFIGURATION__HEIGHT = VIEW_ALLOCATION__HEIGHT;

	/**
	 * The feature id for the '<em><b>Position</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INBOX_CONFIGURATION__POSITION = VIEW_ALLOCATION__POSITION;

	/**
	 * The number of structural features of the '<em>Inbox Configuration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INBOX_CONFIGURATION_FEATURE_COUNT = VIEW_ALLOCATION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.perspective.impl.OutboxConfigurationImpl <em>Outbox Configuration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.perspective.impl.OutboxConfigurationImpl
	 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getOutboxConfiguration()
	 * @generated
	 */
	int OUTBOX_CONFIGURATION = 11;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTBOX_CONFIGURATION__NAME = VIEW_ALLOCATION__NAME;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTBOX_CONFIGURATION__UNDER_USER_CONTROL = VIEW_ALLOCATION__UNDER_USER_CONTROL;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTBOX_CONFIGURATION__WIDTH = VIEW_ALLOCATION__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTBOX_CONFIGURATION__HEIGHT = VIEW_ALLOCATION__HEIGHT;

	/**
	 * The feature id for the '<em><b>Position</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTBOX_CONFIGURATION__POSITION = VIEW_ALLOCATION__POSITION;

	/**
	 * The number of structural features of the '<em>Outbox Configuration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTBOX_CONFIGURATION_FEATURE_COUNT = VIEW_ALLOCATION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.perspective.impl.MultiplicityElementNavigationConstraintImpl <em>Multiplicity Element Navigation Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.perspective.impl.MultiplicityElementNavigationConstraintImpl
	 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getMultiplicityElementNavigationConstraint()
	 * @generated
	 */
	int MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT = 13;

	/**
	 * The feature id for the '<em><b>Requires Group Ownership</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP = NAVIGATION_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP;

	/**
	 * The feature id for the '<em><b>Requires Ownership</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT__REQUIRES_OWNERSHIP = NAVIGATION_CONSTRAINT__REQUIRES_OWNERSHIP;

	/**
	 * The feature id for the '<em><b>Required Roles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT__REQUIRED_ROLES = NAVIGATION_CONSTRAINT__REQUIRED_ROLES;

	/**
	 * The feature id for the '<em><b>Open To Public</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT__OPEN_TO_PUBLIC = NAVIGATION_CONSTRAINT__OPEN_TO_PUBLIC;

	/**
	 * The feature id for the '<em><b>Required States</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT__REQUIRED_STATES = NAVIGATION_CONSTRAINT__REQUIRED_STATES;

	/**
	 * The feature id for the '<em><b>Inherit From Parent</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT__INHERIT_FROM_PARENT = NAVIGATION_CONSTRAINT__INHERIT_FROM_PARENT;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT__NAME = NAVIGATION_CONSTRAINT__NAME;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT__UNDER_USER_CONTROL = NAVIGATION_CONSTRAINT__UNDER_USER_CONTROL;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT__UML_ELEMENT_UID = NAVIGATION_CONSTRAINT__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Label Override</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT__LABEL_OVERRIDE = NAVIGATION_CONSTRAINT__LABEL_OVERRIDE;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT__HIDDEN = NAVIGATION_CONSTRAINT__HIDDEN;

	/**
	 * The feature id for the '<em><b>Remove Constraint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT__REMOVE_CONSTRAINT = NAVIGATION_CONSTRAINT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Add Constraint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT__ADD_CONSTRAINT = NAVIGATION_CONSTRAINT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Multiplicity Element Navigation Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT_FEATURE_COUNT = NAVIGATION_CONSTRAINT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.perspective.impl.ParameterNavigationConstraintImpl <em>Parameter Navigation Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.perspective.impl.ParameterNavigationConstraintImpl
	 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getParameterNavigationConstraint()
	 * @generated
	 */
	int PARAMETER_NAVIGATION_CONSTRAINT = 12;

	/**
	 * The feature id for the '<em><b>Requires Group Ownership</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_NAVIGATION_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP = MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP;

	/**
	 * The feature id for the '<em><b>Requires Ownership</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_NAVIGATION_CONSTRAINT__REQUIRES_OWNERSHIP = MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT__REQUIRES_OWNERSHIP;

	/**
	 * The feature id for the '<em><b>Required Roles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_NAVIGATION_CONSTRAINT__REQUIRED_ROLES = MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT__REQUIRED_ROLES;

	/**
	 * The feature id for the '<em><b>Open To Public</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_NAVIGATION_CONSTRAINT__OPEN_TO_PUBLIC = MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT__OPEN_TO_PUBLIC;

	/**
	 * The feature id for the '<em><b>Required States</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_NAVIGATION_CONSTRAINT__REQUIRED_STATES = MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT__REQUIRED_STATES;

	/**
	 * The feature id for the '<em><b>Inherit From Parent</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_NAVIGATION_CONSTRAINT__INHERIT_FROM_PARENT = MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT__INHERIT_FROM_PARENT;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_NAVIGATION_CONSTRAINT__NAME = MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT__NAME;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_NAVIGATION_CONSTRAINT__UNDER_USER_CONTROL = MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT__UNDER_USER_CONTROL;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_NAVIGATION_CONSTRAINT__UML_ELEMENT_UID = MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Label Override</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_NAVIGATION_CONSTRAINT__LABEL_OVERRIDE = MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT__LABEL_OVERRIDE;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_NAVIGATION_CONSTRAINT__HIDDEN = MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT__HIDDEN;

	/**
	 * The feature id for the '<em><b>Remove Constraint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_NAVIGATION_CONSTRAINT__REMOVE_CONSTRAINT = MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT__REMOVE_CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Add Constraint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_NAVIGATION_CONSTRAINT__ADD_CONSTRAINT = MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT__ADD_CONSTRAINT;

	/**
	 * The number of structural features of the '<em>Parameter Navigation Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_NAVIGATION_CONSTRAINT_FEATURE_COUNT = MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.perspective.PositionInPerspective <em>Position In Perspective</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.perspective.PositionInPerspective
	 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getPositionInPerspective()
	 * @generated
	 */
	int POSITION_IN_PERSPECTIVE = 14;


	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.perspective.PerspectiveConfiguration <em>Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Configuration</em>'.
	 * @see org.opaeum.uim.perspective.PerspectiveConfiguration
	 * @generated
	 */
	EClass getPerspectiveConfiguration();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.perspective.PerspectiveConfiguration#getExplorer <em>Explorer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Explorer</em>'.
	 * @see org.opaeum.uim.perspective.PerspectiveConfiguration#getExplorer()
	 * @see #getPerspectiveConfiguration()
	 * @generated
	 */
	EReference getPerspectiveConfiguration_Explorer();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.perspective.PerspectiveConfiguration#getEditor <em>Editor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Editor</em>'.
	 * @see org.opaeum.uim.perspective.PerspectiveConfiguration#getEditor()
	 * @see #getPerspectiveConfiguration()
	 * @generated
	 */
	EReference getPerspectiveConfiguration_Editor();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.perspective.PerspectiveConfiguration#getProperties <em>Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Properties</em>'.
	 * @see org.opaeum.uim.perspective.PerspectiveConfiguration#getProperties()
	 * @see #getPerspectiveConfiguration()
	 * @generated
	 */
	EReference getPerspectiveConfiguration_Properties();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.perspective.PerspectiveConfiguration#getInbox <em>Inbox</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Inbox</em>'.
	 * @see org.opaeum.uim.perspective.PerspectiveConfiguration#getInbox()
	 * @see #getPerspectiveConfiguration()
	 * @generated
	 */
	EReference getPerspectiveConfiguration_Inbox();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.perspective.PerspectiveConfiguration#getOutbox <em>Outbox</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Outbox</em>'.
	 * @see org.opaeum.uim.perspective.PerspectiveConfiguration#getOutbox()
	 * @see #getPerspectiveConfiguration()
	 * @generated
	 */
	EReference getPerspectiveConfiguration_Outbox();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.perspective.ViewAllocation <em>View Allocation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>View Allocation</em>'.
	 * @see org.opaeum.uim.perspective.ViewAllocation
	 * @generated
	 */
	EClass getViewAllocation();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.uim.perspective.ViewAllocation#getWidth <em>Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Width</em>'.
	 * @see org.opaeum.uim.perspective.ViewAllocation#getWidth()
	 * @see #getViewAllocation()
	 * @generated
	 */
	EAttribute getViewAllocation_Width();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.uim.perspective.ViewAllocation#getHeight <em>Height</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Height</em>'.
	 * @see org.opaeum.uim.perspective.ViewAllocation#getHeight()
	 * @see #getViewAllocation()
	 * @generated
	 */
	EAttribute getViewAllocation_Height();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.uim.perspective.ViewAllocation#getPosition <em>Position</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Position</em>'.
	 * @see org.opaeum.uim.perspective.ViewAllocation#getPosition()
	 * @see #getViewAllocation()
	 * @generated
	 */
	EAttribute getViewAllocation_Position();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.perspective.NavigatorConfiguration <em>Navigator Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Navigator Configuration</em>'.
	 * @see org.opaeum.uim.perspective.NavigatorConfiguration
	 * @generated
	 */
	EClass getNavigatorConfiguration();

	/**
	 * Returns the meta object for the containment reference list '{@link org.opaeum.uim.perspective.NavigatorConfiguration#getClasses <em>Classes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Classes</em>'.
	 * @see org.opaeum.uim.perspective.NavigatorConfiguration#getClasses()
	 * @see #getNavigatorConfiguration()
	 * @generated
	 */
	EReference getNavigatorConfiguration_Classes();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.perspective.ClassNavigationConstraint <em>Class Navigation Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Class Navigation Constraint</em>'.
	 * @see org.opaeum.uim.perspective.ClassNavigationConstraint
	 * @generated
	 */
	EClass getClassNavigationConstraint();

	/**
	 * Returns the meta object for the container reference '{@link org.opaeum.uim.perspective.ClassNavigationConstraint#getExplorerConfiguration <em>Explorer Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Explorer Configuration</em>'.
	 * @see org.opaeum.uim.perspective.ClassNavigationConstraint#getExplorerConfiguration()
	 * @see #getClassNavigationConstraint()
	 * @generated
	 */
	EReference getClassNavigationConstraint_ExplorerConfiguration();

	/**
	 * Returns the meta object for the containment reference list '{@link org.opaeum.uim.perspective.ClassNavigationConstraint#getProperties <em>Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Properties</em>'.
	 * @see org.opaeum.uim.perspective.ClassNavigationConstraint#getProperties()
	 * @see #getClassNavigationConstraint()
	 * @generated
	 */
	EReference getClassNavigationConstraint_Properties();

	/**
	 * Returns the meta object for the containment reference list '{@link org.opaeum.uim.perspective.ClassNavigationConstraint#getBehaviors <em>Behaviors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Behaviors</em>'.
	 * @see org.opaeum.uim.perspective.ClassNavigationConstraint#getBehaviors()
	 * @see #getClassNavigationConstraint()
	 * @generated
	 */
	EReference getClassNavigationConstraint_Behaviors();

	/**
	 * Returns the meta object for the containment reference list '{@link org.opaeum.uim.perspective.ClassNavigationConstraint#getOperations <em>Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Operations</em>'.
	 * @see org.opaeum.uim.perspective.ClassNavigationConstraint#getOperations()
	 * @see #getClassNavigationConstraint()
	 * @generated
	 */
	EReference getClassNavigationConstraint_Operations();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.perspective.PropertyNavigationConstraint <em>Property Navigation Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Property Navigation Constraint</em>'.
	 * @see org.opaeum.uim.perspective.PropertyNavigationConstraint
	 * @generated
	 */
	EClass getPropertyNavigationConstraint();

	/**
	 * Returns the meta object for the container reference '{@link org.opaeum.uim.perspective.PropertyNavigationConstraint#getOwner <em>Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owner</em>'.
	 * @see org.opaeum.uim.perspective.PropertyNavigationConstraint#getOwner()
	 * @see #getPropertyNavigationConstraint()
	 * @generated
	 */
	EReference getPropertyNavigationConstraint_Owner();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.perspective.EditorConfiguration <em>Editor Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Editor Configuration</em>'.
	 * @see org.opaeum.uim.perspective.EditorConfiguration
	 * @generated
	 */
	EClass getEditorConfiguration();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.perspective.PropertiesConfiguration <em>Properties Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Properties Configuration</em>'.
	 * @see org.opaeum.uim.perspective.PropertiesConfiguration
	 * @generated
	 */
	EClass getPropertiesConfiguration();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.perspective.NavigationConstraint <em>Navigation Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Navigation Constraint</em>'.
	 * @see org.opaeum.uim.perspective.NavigationConstraint
	 * @generated
	 */
	EClass getNavigationConstraint();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.uim.perspective.NavigationConstraint#isHidden <em>Hidden</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Hidden</em>'.
	 * @see org.opaeum.uim.perspective.NavigationConstraint#isHidden()
	 * @see #getNavigationConstraint()
	 * @generated
	 */
	EAttribute getNavigationConstraint_Hidden();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.perspective.OperationNavigationConstraint <em>Operation Navigation Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation Navigation Constraint</em>'.
	 * @see org.opaeum.uim.perspective.OperationNavigationConstraint
	 * @generated
	 */
	EClass getOperationNavigationConstraint();

	/**
	 * Returns the meta object for the container reference '{@link org.opaeum.uim.perspective.OperationNavigationConstraint#getOwner <em>Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owner</em>'.
	 * @see org.opaeum.uim.perspective.OperationNavigationConstraint#getOwner()
	 * @see #getOperationNavigationConstraint()
	 * @generated
	 */
	EReference getOperationNavigationConstraint_Owner();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.perspective.OperationNavigationConstraint#getInvocationConstraint <em>Invocation Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Invocation Constraint</em>'.
	 * @see org.opaeum.uim.perspective.OperationNavigationConstraint#getInvocationConstraint()
	 * @see #getOperationNavigationConstraint()
	 * @generated
	 */
	EReference getOperationNavigationConstraint_InvocationConstraint();

	/**
	 * Returns the meta object for the containment reference list '{@link org.opaeum.uim.perspective.OperationNavigationConstraint#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parameters</em>'.
	 * @see org.opaeum.uim.perspective.OperationNavigationConstraint#getParameters()
	 * @see #getOperationNavigationConstraint()
	 * @generated
	 */
	EReference getOperationNavigationConstraint_Parameters();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.perspective.BehaviorNavigationConstraint <em>Behavior Navigation Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Behavior Navigation Constraint</em>'.
	 * @see org.opaeum.uim.perspective.BehaviorNavigationConstraint
	 * @generated
	 */
	EClass getBehaviorNavigationConstraint();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.perspective.BehaviorNavigationConstraint#getInvocationConstraint <em>Invocation Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Invocation Constraint</em>'.
	 * @see org.opaeum.uim.perspective.BehaviorNavigationConstraint#getInvocationConstraint()
	 * @see #getBehaviorNavigationConstraint()
	 * @generated
	 */
	EReference getBehaviorNavigationConstraint_InvocationConstraint();

	/**
	 * Returns the meta object for the container reference '{@link org.opaeum.uim.perspective.BehaviorNavigationConstraint#getOwner <em>Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owner</em>'.
	 * @see org.opaeum.uim.perspective.BehaviorNavigationConstraint#getOwner()
	 * @see #getBehaviorNavigationConstraint()
	 * @generated
	 */
	EReference getBehaviorNavigationConstraint_Owner();

	/**
	 * Returns the meta object for the containment reference list '{@link org.opaeum.uim.perspective.BehaviorNavigationConstraint#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parameters</em>'.
	 * @see org.opaeum.uim.perspective.BehaviorNavigationConstraint#getParameters()
	 * @see #getBehaviorNavigationConstraint()
	 * @generated
	 */
	EReference getBehaviorNavigationConstraint_Parameters();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.perspective.InboxConfiguration <em>Inbox Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Inbox Configuration</em>'.
	 * @see org.opaeum.uim.perspective.InboxConfiguration
	 * @generated
	 */
	EClass getInboxConfiguration();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.perspective.OutboxConfiguration <em>Outbox Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Outbox Configuration</em>'.
	 * @see org.opaeum.uim.perspective.OutboxConfiguration
	 * @generated
	 */
	EClass getOutboxConfiguration();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.perspective.ParameterNavigationConstraint <em>Parameter Navigation Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parameter Navigation Constraint</em>'.
	 * @see org.opaeum.uim.perspective.ParameterNavigationConstraint
	 * @generated
	 */
	EClass getParameterNavigationConstraint();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.perspective.MultiplicityElementNavigationConstraint <em>Multiplicity Element Navigation Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Multiplicity Element Navigation Constraint</em>'.
	 * @see org.opaeum.uim.perspective.MultiplicityElementNavigationConstraint
	 * @generated
	 */
	EClass getMultiplicityElementNavigationConstraint();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.perspective.MultiplicityElementNavigationConstraint#getRemoveConstraint <em>Remove Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Remove Constraint</em>'.
	 * @see org.opaeum.uim.perspective.MultiplicityElementNavigationConstraint#getRemoveConstraint()
	 * @see #getMultiplicityElementNavigationConstraint()
	 * @generated
	 */
	EReference getMultiplicityElementNavigationConstraint_RemoveConstraint();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.perspective.MultiplicityElementNavigationConstraint#getAddConstraint <em>Add Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Add Constraint</em>'.
	 * @see org.opaeum.uim.perspective.MultiplicityElementNavigationConstraint#getAddConstraint()
	 * @see #getMultiplicityElementNavigationConstraint()
	 * @generated
	 */
	EReference getMultiplicityElementNavigationConstraint_AddConstraint();

	/**
	 * Returns the meta object for enum '{@link org.opaeum.uim.perspective.PositionInPerspective <em>Position In Perspective</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Position In Perspective</em>'.
	 * @see org.opaeum.uim.perspective.PositionInPerspective
	 * @generated
	 */
	EEnum getPositionInPerspective();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	PerspectiveFactory getPerspectiveFactory();

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
		 * The meta object literal for the '{@link org.opaeum.uim.perspective.impl.PerspectiveConfigurationImpl <em>Configuration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.perspective.impl.PerspectiveConfigurationImpl
		 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getPerspectiveConfiguration()
		 * @generated
		 */
		EClass PERSPECTIVE_CONFIGURATION = eINSTANCE.getPerspectiveConfiguration();

		/**
		 * The meta object literal for the '<em><b>Explorer</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PERSPECTIVE_CONFIGURATION__EXPLORER = eINSTANCE.getPerspectiveConfiguration_Explorer();

		/**
		 * The meta object literal for the '<em><b>Editor</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PERSPECTIVE_CONFIGURATION__EDITOR = eINSTANCE.getPerspectiveConfiguration_Editor();

		/**
		 * The meta object literal for the '<em><b>Properties</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PERSPECTIVE_CONFIGURATION__PROPERTIES = eINSTANCE.getPerspectiveConfiguration_Properties();

		/**
		 * The meta object literal for the '<em><b>Inbox</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PERSPECTIVE_CONFIGURATION__INBOX = eINSTANCE.getPerspectiveConfiguration_Inbox();

		/**
		 * The meta object literal for the '<em><b>Outbox</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PERSPECTIVE_CONFIGURATION__OUTBOX = eINSTANCE.getPerspectiveConfiguration_Outbox();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.perspective.impl.ViewAllocationImpl <em>View Allocation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.perspective.impl.ViewAllocationImpl
		 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getViewAllocation()
		 * @generated
		 */
		EClass VIEW_ALLOCATION = eINSTANCE.getViewAllocation();

		/**
		 * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VIEW_ALLOCATION__WIDTH = eINSTANCE.getViewAllocation_Width();

		/**
		 * The meta object literal for the '<em><b>Height</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VIEW_ALLOCATION__HEIGHT = eINSTANCE.getViewAllocation_Height();

		/**
		 * The meta object literal for the '<em><b>Position</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VIEW_ALLOCATION__POSITION = eINSTANCE.getViewAllocation_Position();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.perspective.impl.NavigatorConfigurationImpl <em>Navigator Configuration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.perspective.impl.NavigatorConfigurationImpl
		 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getNavigatorConfiguration()
		 * @generated
		 */
		EClass NAVIGATOR_CONFIGURATION = eINSTANCE.getNavigatorConfiguration();

		/**
		 * The meta object literal for the '<em><b>Classes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NAVIGATOR_CONFIGURATION__CLASSES = eINSTANCE.getNavigatorConfiguration_Classes();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.perspective.impl.ClassNavigationConstraintImpl <em>Class Navigation Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.perspective.impl.ClassNavigationConstraintImpl
		 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getClassNavigationConstraint()
		 * @generated
		 */
		EClass CLASS_NAVIGATION_CONSTRAINT = eINSTANCE.getClassNavigationConstraint();

		/**
		 * The meta object literal for the '<em><b>Explorer Configuration</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS_NAVIGATION_CONSTRAINT__EXPLORER_CONFIGURATION = eINSTANCE.getClassNavigationConstraint_ExplorerConfiguration();

		/**
		 * The meta object literal for the '<em><b>Properties</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS_NAVIGATION_CONSTRAINT__PROPERTIES = eINSTANCE.getClassNavigationConstraint_Properties();

		/**
		 * The meta object literal for the '<em><b>Behaviors</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS_NAVIGATION_CONSTRAINT__BEHAVIORS = eINSTANCE.getClassNavigationConstraint_Behaviors();

		/**
		 * The meta object literal for the '<em><b>Operations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS_NAVIGATION_CONSTRAINT__OPERATIONS = eINSTANCE.getClassNavigationConstraint_Operations();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.perspective.impl.PropertyNavigationConstraintImpl <em>Property Navigation Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.perspective.impl.PropertyNavigationConstraintImpl
		 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getPropertyNavigationConstraint()
		 * @generated
		 */
		EClass PROPERTY_NAVIGATION_CONSTRAINT = eINSTANCE.getPropertyNavigationConstraint();

		/**
		 * The meta object literal for the '<em><b>Owner</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROPERTY_NAVIGATION_CONSTRAINT__OWNER = eINSTANCE.getPropertyNavigationConstraint_Owner();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.perspective.impl.EditorConfigurationImpl <em>Editor Configuration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.perspective.impl.EditorConfigurationImpl
		 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getEditorConfiguration()
		 * @generated
		 */
		EClass EDITOR_CONFIGURATION = eINSTANCE.getEditorConfiguration();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.perspective.impl.PropertiesConfigurationImpl <em>Properties Configuration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.perspective.impl.PropertiesConfigurationImpl
		 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getPropertiesConfiguration()
		 * @generated
		 */
		EClass PROPERTIES_CONFIGURATION = eINSTANCE.getPropertiesConfiguration();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.perspective.impl.NavigationConstraintImpl <em>Navigation Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.perspective.impl.NavigationConstraintImpl
		 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getNavigationConstraint()
		 * @generated
		 */
		EClass NAVIGATION_CONSTRAINT = eINSTANCE.getNavigationConstraint();

		/**
		 * The meta object literal for the '<em><b>Hidden</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAVIGATION_CONSTRAINT__HIDDEN = eINSTANCE.getNavigationConstraint_Hidden();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.perspective.impl.OperationNavigationConstraintImpl <em>Operation Navigation Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.perspective.impl.OperationNavigationConstraintImpl
		 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getOperationNavigationConstraint()
		 * @generated
		 */
		EClass OPERATION_NAVIGATION_CONSTRAINT = eINSTANCE.getOperationNavigationConstraint();

		/**
		 * The meta object literal for the '<em><b>Owner</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION_NAVIGATION_CONSTRAINT__OWNER = eINSTANCE.getOperationNavigationConstraint_Owner();

		/**
		 * The meta object literal for the '<em><b>Invocation Constraint</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION_NAVIGATION_CONSTRAINT__INVOCATION_CONSTRAINT = eINSTANCE.getOperationNavigationConstraint_InvocationConstraint();

		/**
		 * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION_NAVIGATION_CONSTRAINT__PARAMETERS = eINSTANCE.getOperationNavigationConstraint_Parameters();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.perspective.impl.BehaviorNavigationConstraintImpl <em>Behavior Navigation Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.perspective.impl.BehaviorNavigationConstraintImpl
		 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getBehaviorNavigationConstraint()
		 * @generated
		 */
		EClass BEHAVIOR_NAVIGATION_CONSTRAINT = eINSTANCE.getBehaviorNavigationConstraint();

		/**
		 * The meta object literal for the '<em><b>Invocation Constraint</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BEHAVIOR_NAVIGATION_CONSTRAINT__INVOCATION_CONSTRAINT = eINSTANCE.getBehaviorNavigationConstraint_InvocationConstraint();

		/**
		 * The meta object literal for the '<em><b>Owner</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BEHAVIOR_NAVIGATION_CONSTRAINT__OWNER = eINSTANCE.getBehaviorNavigationConstraint_Owner();

		/**
		 * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BEHAVIOR_NAVIGATION_CONSTRAINT__PARAMETERS = eINSTANCE.getBehaviorNavigationConstraint_Parameters();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.perspective.impl.InboxConfigurationImpl <em>Inbox Configuration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.perspective.impl.InboxConfigurationImpl
		 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getInboxConfiguration()
		 * @generated
		 */
		EClass INBOX_CONFIGURATION = eINSTANCE.getInboxConfiguration();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.perspective.impl.OutboxConfigurationImpl <em>Outbox Configuration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.perspective.impl.OutboxConfigurationImpl
		 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getOutboxConfiguration()
		 * @generated
		 */
		EClass OUTBOX_CONFIGURATION = eINSTANCE.getOutboxConfiguration();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.perspective.impl.ParameterNavigationConstraintImpl <em>Parameter Navigation Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.perspective.impl.ParameterNavigationConstraintImpl
		 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getParameterNavigationConstraint()
		 * @generated
		 */
		EClass PARAMETER_NAVIGATION_CONSTRAINT = eINSTANCE.getParameterNavigationConstraint();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.perspective.impl.MultiplicityElementNavigationConstraintImpl <em>Multiplicity Element Navigation Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.perspective.impl.MultiplicityElementNavigationConstraintImpl
		 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getMultiplicityElementNavigationConstraint()
		 * @generated
		 */
		EClass MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT = eINSTANCE.getMultiplicityElementNavigationConstraint();

		/**
		 * The meta object literal for the '<em><b>Remove Constraint</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT__REMOVE_CONSTRAINT = eINSTANCE.getMultiplicityElementNavigationConstraint_RemoveConstraint();

		/**
		 * The meta object literal for the '<em><b>Add Constraint</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT__ADD_CONSTRAINT = eINSTANCE.getMultiplicityElementNavigationConstraint_AddConstraint();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.perspective.PositionInPerspective <em>Position In Perspective</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.perspective.PositionInPerspective
		 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getPositionInPerspective()
		 * @generated
		 */
		EEnum POSITION_IN_PERSPECTIVE = eINSTANCE.getPositionInPerspective();

	}

} //PerspectivePackage
