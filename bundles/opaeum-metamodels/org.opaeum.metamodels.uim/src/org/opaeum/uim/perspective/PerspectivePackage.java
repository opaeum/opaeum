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
	 * The meta object id for the '{@link org.opaeum.uim.perspective.impl.ExplorerConfigurationImpl <em>Explorer Configuration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.perspective.impl.ExplorerConfigurationImpl
	 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getExplorerConfiguration()
	 * @generated
	 */
	int EXPLORER_CONFIGURATION = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_CONFIGURATION__NAME = VIEW_ALLOCATION__NAME;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_CONFIGURATION__UNDER_USER_CONTROL = VIEW_ALLOCATION__UNDER_USER_CONTROL;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_CONFIGURATION__WIDTH = VIEW_ALLOCATION__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_CONFIGURATION__HEIGHT = VIEW_ALLOCATION__HEIGHT;

	/**
	 * The feature id for the '<em><b>Position</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_CONFIGURATION__POSITION = VIEW_ALLOCATION__POSITION;

	/**
	 * The feature id for the '<em><b>Classes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_CONFIGURATION__CLASSES = VIEW_ALLOCATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Explorer Configuration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_CONFIGURATION_FEATURE_COUNT = VIEW_ALLOCATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.perspective.impl.ExplorerClassConstraintImpl <em>Explorer Class Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.perspective.impl.ExplorerClassConstraintImpl
	 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getExplorerClassConstraint()
	 * @generated
	 */
	int EXPLORER_CLASS_CONSTRAINT = 3;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.perspective.impl.ExplorerConstraintImpl <em>Explorer Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.perspective.impl.ExplorerConstraintImpl
	 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getExplorerConstraint()
	 * @generated
	 */
	int EXPLORER_CONSTRAINT = 7;

	/**
	 * The feature id for the '<em><b>Requires Group Ownership</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP = ConstraintPackage.USER_INTERACTION_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP;

	/**
	 * The feature id for the '<em><b>Requires Ownership</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_CONSTRAINT__REQUIRES_OWNERSHIP = ConstraintPackage.USER_INTERACTION_CONSTRAINT__REQUIRES_OWNERSHIP;

	/**
	 * The feature id for the '<em><b>Required Roles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_CONSTRAINT__REQUIRED_ROLES = ConstraintPackage.USER_INTERACTION_CONSTRAINT__REQUIRED_ROLES;

	/**
	 * The feature id for the '<em><b>Open To Public</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_CONSTRAINT__OPEN_TO_PUBLIC = ConstraintPackage.USER_INTERACTION_CONSTRAINT__OPEN_TO_PUBLIC;

	/**
	 * The feature id for the '<em><b>Required States</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_CONSTRAINT__REQUIRED_STATES = ConstraintPackage.USER_INTERACTION_CONSTRAINT__REQUIRED_STATES;

	/**
	 * The feature id for the '<em><b>Inherit From Parent</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_CONSTRAINT__INHERIT_FROM_PARENT = ConstraintPackage.USER_INTERACTION_CONSTRAINT__INHERIT_FROM_PARENT;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_CONSTRAINT__UML_ELEMENT_UID = ConstraintPackage.USER_INTERACTION_CONSTRAINT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_CONSTRAINT__NAME = ConstraintPackage.USER_INTERACTION_CONSTRAINT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_CONSTRAINT__UNDER_USER_CONTROL = ConstraintPackage.USER_INTERACTION_CONSTRAINT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Label Override</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_CONSTRAINT__LABEL_OVERRIDE = ConstraintPackage.USER_INTERACTION_CONSTRAINT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_CONSTRAINT__HIDDEN = ConstraintPackage.USER_INTERACTION_CONSTRAINT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Explorer Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_CONSTRAINT_FEATURE_COUNT = ConstraintPackage.USER_INTERACTION_CONSTRAINT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Requires Group Ownership</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_CLASS_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP = EXPLORER_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP;

	/**
	 * The feature id for the '<em><b>Requires Ownership</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_CLASS_CONSTRAINT__REQUIRES_OWNERSHIP = EXPLORER_CONSTRAINT__REQUIRES_OWNERSHIP;

	/**
	 * The feature id for the '<em><b>Required Roles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_CLASS_CONSTRAINT__REQUIRED_ROLES = EXPLORER_CONSTRAINT__REQUIRED_ROLES;

	/**
	 * The feature id for the '<em><b>Open To Public</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_CLASS_CONSTRAINT__OPEN_TO_PUBLIC = EXPLORER_CONSTRAINT__OPEN_TO_PUBLIC;

	/**
	 * The feature id for the '<em><b>Required States</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_CLASS_CONSTRAINT__REQUIRED_STATES = EXPLORER_CONSTRAINT__REQUIRED_STATES;

	/**
	 * The feature id for the '<em><b>Inherit From Parent</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_CLASS_CONSTRAINT__INHERIT_FROM_PARENT = EXPLORER_CONSTRAINT__INHERIT_FROM_PARENT;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_CLASS_CONSTRAINT__UML_ELEMENT_UID = EXPLORER_CONSTRAINT__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_CLASS_CONSTRAINT__NAME = EXPLORER_CONSTRAINT__NAME;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_CLASS_CONSTRAINT__UNDER_USER_CONTROL = EXPLORER_CONSTRAINT__UNDER_USER_CONTROL;

	/**
	 * The feature id for the '<em><b>Label Override</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_CLASS_CONSTRAINT__LABEL_OVERRIDE = EXPLORER_CONSTRAINT__LABEL_OVERRIDE;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_CLASS_CONSTRAINT__HIDDEN = EXPLORER_CONSTRAINT__HIDDEN;

	/**
	 * The feature id for the '<em><b>Explorer Configuration</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_CLASS_CONSTRAINT__EXPLORER_CONFIGURATION = EXPLORER_CONSTRAINT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_CLASS_CONSTRAINT__PROPERTIES = EXPLORER_CONSTRAINT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>New Object Constraint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_CLASS_CONSTRAINT__NEW_OBJECT_CONSTRAINT = EXPLORER_CONSTRAINT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Behaviors</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_CLASS_CONSTRAINT__BEHAVIORS = EXPLORER_CONSTRAINT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_CLASS_CONSTRAINT__OPERATIONS = EXPLORER_CONSTRAINT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Explorer Class Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_CLASS_CONSTRAINT_FEATURE_COUNT = EXPLORER_CONSTRAINT_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.perspective.impl.ExplorerPropertyConstraintImpl <em>Explorer Property Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.perspective.impl.ExplorerPropertyConstraintImpl
	 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getExplorerPropertyConstraint()
	 * @generated
	 */
	int EXPLORER_PROPERTY_CONSTRAINT = 4;

	/**
	 * The feature id for the '<em><b>Requires Group Ownership</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_PROPERTY_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP = EXPLORER_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP;

	/**
	 * The feature id for the '<em><b>Requires Ownership</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_PROPERTY_CONSTRAINT__REQUIRES_OWNERSHIP = EXPLORER_CONSTRAINT__REQUIRES_OWNERSHIP;

	/**
	 * The feature id for the '<em><b>Required Roles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_PROPERTY_CONSTRAINT__REQUIRED_ROLES = EXPLORER_CONSTRAINT__REQUIRED_ROLES;

	/**
	 * The feature id for the '<em><b>Open To Public</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_PROPERTY_CONSTRAINT__OPEN_TO_PUBLIC = EXPLORER_CONSTRAINT__OPEN_TO_PUBLIC;

	/**
	 * The feature id for the '<em><b>Required States</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_PROPERTY_CONSTRAINT__REQUIRED_STATES = EXPLORER_CONSTRAINT__REQUIRED_STATES;

	/**
	 * The feature id for the '<em><b>Inherit From Parent</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_PROPERTY_CONSTRAINT__INHERIT_FROM_PARENT = EXPLORER_CONSTRAINT__INHERIT_FROM_PARENT;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_PROPERTY_CONSTRAINT__UML_ELEMENT_UID = EXPLORER_CONSTRAINT__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_PROPERTY_CONSTRAINT__NAME = EXPLORER_CONSTRAINT__NAME;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_PROPERTY_CONSTRAINT__UNDER_USER_CONTROL = EXPLORER_CONSTRAINT__UNDER_USER_CONTROL;

	/**
	 * The feature id for the '<em><b>Label Override</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_PROPERTY_CONSTRAINT__LABEL_OVERRIDE = EXPLORER_CONSTRAINT__LABEL_OVERRIDE;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_PROPERTY_CONSTRAINT__HIDDEN = EXPLORER_CONSTRAINT__HIDDEN;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_PROPERTY_CONSTRAINT__OWNER = EXPLORER_CONSTRAINT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Explorer Property Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_PROPERTY_CONSTRAINT_FEATURE_COUNT = EXPLORER_CONSTRAINT_FEATURE_COUNT + 1;

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
	 * The meta object id for the '{@link org.opaeum.uim.perspective.impl.ExplorerOperationConstraintImpl <em>Explorer Operation Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.perspective.impl.ExplorerOperationConstraintImpl
	 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getExplorerOperationConstraint()
	 * @generated
	 */
	int EXPLORER_OPERATION_CONSTRAINT = 8;

	/**
	 * The feature id for the '<em><b>Requires Group Ownership</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_OPERATION_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP = EXPLORER_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP;

	/**
	 * The feature id for the '<em><b>Requires Ownership</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_OPERATION_CONSTRAINT__REQUIRES_OWNERSHIP = EXPLORER_CONSTRAINT__REQUIRES_OWNERSHIP;

	/**
	 * The feature id for the '<em><b>Required Roles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_OPERATION_CONSTRAINT__REQUIRED_ROLES = EXPLORER_CONSTRAINT__REQUIRED_ROLES;

	/**
	 * The feature id for the '<em><b>Open To Public</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_OPERATION_CONSTRAINT__OPEN_TO_PUBLIC = EXPLORER_CONSTRAINT__OPEN_TO_PUBLIC;

	/**
	 * The feature id for the '<em><b>Required States</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_OPERATION_CONSTRAINT__REQUIRED_STATES = EXPLORER_CONSTRAINT__REQUIRED_STATES;

	/**
	 * The feature id for the '<em><b>Inherit From Parent</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_OPERATION_CONSTRAINT__INHERIT_FROM_PARENT = EXPLORER_CONSTRAINT__INHERIT_FROM_PARENT;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_OPERATION_CONSTRAINT__UML_ELEMENT_UID = EXPLORER_CONSTRAINT__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_OPERATION_CONSTRAINT__NAME = EXPLORER_CONSTRAINT__NAME;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_OPERATION_CONSTRAINT__UNDER_USER_CONTROL = EXPLORER_CONSTRAINT__UNDER_USER_CONTROL;

	/**
	 * The feature id for the '<em><b>Label Override</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_OPERATION_CONSTRAINT__LABEL_OVERRIDE = EXPLORER_CONSTRAINT__LABEL_OVERRIDE;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_OPERATION_CONSTRAINT__HIDDEN = EXPLORER_CONSTRAINT__HIDDEN;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_OPERATION_CONSTRAINT__OWNER = EXPLORER_CONSTRAINT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Explorer Operation Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_OPERATION_CONSTRAINT_FEATURE_COUNT = EXPLORER_CONSTRAINT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.perspective.impl.ExplorerBehaviorConstraintImpl <em>Explorer Behavior Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.perspective.impl.ExplorerBehaviorConstraintImpl
	 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getExplorerBehaviorConstraint()
	 * @generated
	 */
	int EXPLORER_BEHAVIOR_CONSTRAINT = 9;

	/**
	 * The feature id for the '<em><b>Requires Group Ownership</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_BEHAVIOR_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP = EXPLORER_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP;

	/**
	 * The feature id for the '<em><b>Requires Ownership</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_BEHAVIOR_CONSTRAINT__REQUIRES_OWNERSHIP = EXPLORER_CONSTRAINT__REQUIRES_OWNERSHIP;

	/**
	 * The feature id for the '<em><b>Required Roles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_BEHAVIOR_CONSTRAINT__REQUIRED_ROLES = EXPLORER_CONSTRAINT__REQUIRED_ROLES;

	/**
	 * The feature id for the '<em><b>Open To Public</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_BEHAVIOR_CONSTRAINT__OPEN_TO_PUBLIC = EXPLORER_CONSTRAINT__OPEN_TO_PUBLIC;

	/**
	 * The feature id for the '<em><b>Required States</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_BEHAVIOR_CONSTRAINT__REQUIRED_STATES = EXPLORER_CONSTRAINT__REQUIRED_STATES;

	/**
	 * The feature id for the '<em><b>Inherit From Parent</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_BEHAVIOR_CONSTRAINT__INHERIT_FROM_PARENT = EXPLORER_CONSTRAINT__INHERIT_FROM_PARENT;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_BEHAVIOR_CONSTRAINT__UML_ELEMENT_UID = EXPLORER_CONSTRAINT__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_BEHAVIOR_CONSTRAINT__NAME = EXPLORER_CONSTRAINT__NAME;

	/**
	 * The feature id for the '<em><b>Under User Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_BEHAVIOR_CONSTRAINT__UNDER_USER_CONTROL = EXPLORER_CONSTRAINT__UNDER_USER_CONTROL;

	/**
	 * The feature id for the '<em><b>Label Override</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_BEHAVIOR_CONSTRAINT__LABEL_OVERRIDE = EXPLORER_CONSTRAINT__LABEL_OVERRIDE;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_BEHAVIOR_CONSTRAINT__HIDDEN = EXPLORER_CONSTRAINT__HIDDEN;

	/**
	 * The feature id for the '<em><b>Invocation Constraint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_BEHAVIOR_CONSTRAINT__INVOCATION_CONSTRAINT = EXPLORER_CONSTRAINT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_BEHAVIOR_CONSTRAINT__OWNER = EXPLORER_CONSTRAINT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Explorer Behavior Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPLORER_BEHAVIOR_CONSTRAINT_FEATURE_COUNT = EXPLORER_CONSTRAINT_FEATURE_COUNT + 2;

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
	 * The meta object id for the '{@link org.opaeum.uim.perspective.PositionInPerspective <em>Position In Perspective</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.perspective.PositionInPerspective
	 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getPositionInPerspective()
	 * @generated
	 */
	int POSITION_IN_PERSPECTIVE = 12;


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
	 * Returns the meta object for class '{@link org.opaeum.uim.perspective.ExplorerConfiguration <em>Explorer Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Explorer Configuration</em>'.
	 * @see org.opaeum.uim.perspective.ExplorerConfiguration
	 * @generated
	 */
	EClass getExplorerConfiguration();

	/**
	 * Returns the meta object for the containment reference list '{@link org.opaeum.uim.perspective.ExplorerConfiguration#getClasses <em>Classes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Classes</em>'.
	 * @see org.opaeum.uim.perspective.ExplorerConfiguration#getClasses()
	 * @see #getExplorerConfiguration()
	 * @generated
	 */
	EReference getExplorerConfiguration_Classes();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.perspective.ExplorerClassConstraint <em>Explorer Class Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Explorer Class Constraint</em>'.
	 * @see org.opaeum.uim.perspective.ExplorerClassConstraint
	 * @generated
	 */
	EClass getExplorerClassConstraint();

	/**
	 * Returns the meta object for the container reference '{@link org.opaeum.uim.perspective.ExplorerClassConstraint#getExplorerConfiguration <em>Explorer Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Explorer Configuration</em>'.
	 * @see org.opaeum.uim.perspective.ExplorerClassConstraint#getExplorerConfiguration()
	 * @see #getExplorerClassConstraint()
	 * @generated
	 */
	EReference getExplorerClassConstraint_ExplorerConfiguration();

	/**
	 * Returns the meta object for the containment reference list '{@link org.opaeum.uim.perspective.ExplorerClassConstraint#getProperties <em>Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Properties</em>'.
	 * @see org.opaeum.uim.perspective.ExplorerClassConstraint#getProperties()
	 * @see #getExplorerClassConstraint()
	 * @generated
	 */
	EReference getExplorerClassConstraint_Properties();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.perspective.ExplorerClassConstraint#getNewObjectConstraint <em>New Object Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>New Object Constraint</em>'.
	 * @see org.opaeum.uim.perspective.ExplorerClassConstraint#getNewObjectConstraint()
	 * @see #getExplorerClassConstraint()
	 * @generated
	 */
	EReference getExplorerClassConstraint_NewObjectConstraint();

	/**
	 * Returns the meta object for the containment reference list '{@link org.opaeum.uim.perspective.ExplorerClassConstraint#getBehaviors <em>Behaviors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Behaviors</em>'.
	 * @see org.opaeum.uim.perspective.ExplorerClassConstraint#getBehaviors()
	 * @see #getExplorerClassConstraint()
	 * @generated
	 */
	EReference getExplorerClassConstraint_Behaviors();

	/**
	 * Returns the meta object for the containment reference list '{@link org.opaeum.uim.perspective.ExplorerClassConstraint#getOperations <em>Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Operations</em>'.
	 * @see org.opaeum.uim.perspective.ExplorerClassConstraint#getOperations()
	 * @see #getExplorerClassConstraint()
	 * @generated
	 */
	EReference getExplorerClassConstraint_Operations();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.perspective.ExplorerPropertyConstraint <em>Explorer Property Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Explorer Property Constraint</em>'.
	 * @see org.opaeum.uim.perspective.ExplorerPropertyConstraint
	 * @generated
	 */
	EClass getExplorerPropertyConstraint();

	/**
	 * Returns the meta object for the container reference '{@link org.opaeum.uim.perspective.ExplorerPropertyConstraint#getOwner <em>Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owner</em>'.
	 * @see org.opaeum.uim.perspective.ExplorerPropertyConstraint#getOwner()
	 * @see #getExplorerPropertyConstraint()
	 * @generated
	 */
	EReference getExplorerPropertyConstraint_Owner();

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
	 * Returns the meta object for class '{@link org.opaeum.uim.perspective.ExplorerConstraint <em>Explorer Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Explorer Constraint</em>'.
	 * @see org.opaeum.uim.perspective.ExplorerConstraint
	 * @generated
	 */
	EClass getExplorerConstraint();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.uim.perspective.ExplorerConstraint#isHidden <em>Hidden</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Hidden</em>'.
	 * @see org.opaeum.uim.perspective.ExplorerConstraint#isHidden()
	 * @see #getExplorerConstraint()
	 * @generated
	 */
	EAttribute getExplorerConstraint_Hidden();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.perspective.ExplorerOperationConstraint <em>Explorer Operation Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Explorer Operation Constraint</em>'.
	 * @see org.opaeum.uim.perspective.ExplorerOperationConstraint
	 * @generated
	 */
	EClass getExplorerOperationConstraint();

	/**
	 * Returns the meta object for the container reference '{@link org.opaeum.uim.perspective.ExplorerOperationConstraint#getOwner <em>Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owner</em>'.
	 * @see org.opaeum.uim.perspective.ExplorerOperationConstraint#getOwner()
	 * @see #getExplorerOperationConstraint()
	 * @generated
	 */
	EReference getExplorerOperationConstraint_Owner();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.perspective.ExplorerBehaviorConstraint <em>Explorer Behavior Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Explorer Behavior Constraint</em>'.
	 * @see org.opaeum.uim.perspective.ExplorerBehaviorConstraint
	 * @generated
	 */
	EClass getExplorerBehaviorConstraint();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.perspective.ExplorerBehaviorConstraint#getInvocationConstraint <em>Invocation Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Invocation Constraint</em>'.
	 * @see org.opaeum.uim.perspective.ExplorerBehaviorConstraint#getInvocationConstraint()
	 * @see #getExplorerBehaviorConstraint()
	 * @generated
	 */
	EReference getExplorerBehaviorConstraint_InvocationConstraint();

	/**
	 * Returns the meta object for the container reference '{@link org.opaeum.uim.perspective.ExplorerBehaviorConstraint#getOwner <em>Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owner</em>'.
	 * @see org.opaeum.uim.perspective.ExplorerBehaviorConstraint#getOwner()
	 * @see #getExplorerBehaviorConstraint()
	 * @generated
	 */
	EReference getExplorerBehaviorConstraint_Owner();

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
		 * The meta object literal for the '{@link org.opaeum.uim.perspective.impl.ExplorerConfigurationImpl <em>Explorer Configuration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.perspective.impl.ExplorerConfigurationImpl
		 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getExplorerConfiguration()
		 * @generated
		 */
		EClass EXPLORER_CONFIGURATION = eINSTANCE.getExplorerConfiguration();

		/**
		 * The meta object literal for the '<em><b>Classes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPLORER_CONFIGURATION__CLASSES = eINSTANCE.getExplorerConfiguration_Classes();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.perspective.impl.ExplorerClassConstraintImpl <em>Explorer Class Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.perspective.impl.ExplorerClassConstraintImpl
		 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getExplorerClassConstraint()
		 * @generated
		 */
		EClass EXPLORER_CLASS_CONSTRAINT = eINSTANCE.getExplorerClassConstraint();

		/**
		 * The meta object literal for the '<em><b>Explorer Configuration</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPLORER_CLASS_CONSTRAINT__EXPLORER_CONFIGURATION = eINSTANCE.getExplorerClassConstraint_ExplorerConfiguration();

		/**
		 * The meta object literal for the '<em><b>Properties</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPLORER_CLASS_CONSTRAINT__PROPERTIES = eINSTANCE.getExplorerClassConstraint_Properties();

		/**
		 * The meta object literal for the '<em><b>New Object Constraint</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPLORER_CLASS_CONSTRAINT__NEW_OBJECT_CONSTRAINT = eINSTANCE.getExplorerClassConstraint_NewObjectConstraint();

		/**
		 * The meta object literal for the '<em><b>Behaviors</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPLORER_CLASS_CONSTRAINT__BEHAVIORS = eINSTANCE.getExplorerClassConstraint_Behaviors();

		/**
		 * The meta object literal for the '<em><b>Operations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPLORER_CLASS_CONSTRAINT__OPERATIONS = eINSTANCE.getExplorerClassConstraint_Operations();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.perspective.impl.ExplorerPropertyConstraintImpl <em>Explorer Property Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.perspective.impl.ExplorerPropertyConstraintImpl
		 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getExplorerPropertyConstraint()
		 * @generated
		 */
		EClass EXPLORER_PROPERTY_CONSTRAINT = eINSTANCE.getExplorerPropertyConstraint();

		/**
		 * The meta object literal for the '<em><b>Owner</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPLORER_PROPERTY_CONSTRAINT__OWNER = eINSTANCE.getExplorerPropertyConstraint_Owner();

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
		 * The meta object literal for the '{@link org.opaeum.uim.perspective.impl.ExplorerConstraintImpl <em>Explorer Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.perspective.impl.ExplorerConstraintImpl
		 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getExplorerConstraint()
		 * @generated
		 */
		EClass EXPLORER_CONSTRAINT = eINSTANCE.getExplorerConstraint();

		/**
		 * The meta object literal for the '<em><b>Hidden</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXPLORER_CONSTRAINT__HIDDEN = eINSTANCE.getExplorerConstraint_Hidden();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.perspective.impl.ExplorerOperationConstraintImpl <em>Explorer Operation Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.perspective.impl.ExplorerOperationConstraintImpl
		 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getExplorerOperationConstraint()
		 * @generated
		 */
		EClass EXPLORER_OPERATION_CONSTRAINT = eINSTANCE.getExplorerOperationConstraint();

		/**
		 * The meta object literal for the '<em><b>Owner</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPLORER_OPERATION_CONSTRAINT__OWNER = eINSTANCE.getExplorerOperationConstraint_Owner();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.perspective.impl.ExplorerBehaviorConstraintImpl <em>Explorer Behavior Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.perspective.impl.ExplorerBehaviorConstraintImpl
		 * @see org.opaeum.uim.perspective.impl.PerspectivePackageImpl#getExplorerBehaviorConstraint()
		 * @generated
		 */
		EClass EXPLORER_BEHAVIOR_CONSTRAINT = eINSTANCE.getExplorerBehaviorConstraint();

		/**
		 * The meta object literal for the '<em><b>Invocation Constraint</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPLORER_BEHAVIOR_CONSTRAINT__INVOCATION_CONSTRAINT = eINSTANCE.getExplorerBehaviorConstraint_InvocationConstraint();

		/**
		 * The meta object literal for the '<em><b>Owner</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPLORER_BEHAVIOR_CONSTRAINT__OWNER = eINSTANCE.getExplorerBehaviorConstraint_Owner();

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
