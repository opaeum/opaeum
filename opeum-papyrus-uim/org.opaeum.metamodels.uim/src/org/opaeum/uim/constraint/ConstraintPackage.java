/**
 */
package org.opaeum.uim.constraint;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.opaeum.uim.UimPackage;

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
 * @see org.opaeum.uim.constraint.ConstraintFactory
 * @model kind="package"
 * @generated
 */
public interface ConstraintPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "constraint";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://opaeum.org/uimetamodel/security/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "usec";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ConstraintPackage eINSTANCE = org.opaeum.uim.constraint.impl.ConstraintPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.opaeum.uim.constraint.impl.ConstrainedObjectImpl <em>Constrained Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.constraint.impl.ConstrainedObjectImpl
	 * @see org.opaeum.uim.constraint.impl.ConstraintPackageImpl#getConstrainedObject()
	 * @generated
	 */
	int CONSTRAINED_OBJECT = 1;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINED_OBJECT__VISIBILITY = 0;

	/**
	 * The number of structural features of the '<em>Constrained Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINED_OBJECT_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.constraint.impl.EditableConstrainedObjectImpl <em>Editable Constrained Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.constraint.impl.EditableConstrainedObjectImpl
	 * @see org.opaeum.uim.constraint.impl.ConstraintPackageImpl#getEditableConstrainedObject()
	 * @generated
	 */
	int EDITABLE_CONSTRAINED_OBJECT = 0;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITABLE_CONSTRAINED_OBJECT__VISIBILITY = CONSTRAINED_OBJECT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITABLE_CONSTRAINED_OBJECT__EDITABILITY = CONSTRAINED_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Editable Constrained Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITABLE_CONSTRAINED_OBJECT_FEATURE_COUNT = CONSTRAINED_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.constraint.impl.RequiredRoleImpl <em>Required Role</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.constraint.impl.RequiredRoleImpl
	 * @see org.opaeum.uim.constraint.impl.ConstraintPackageImpl#getRequiredRole()
	 * @generated
	 */
	int REQUIRED_ROLE = 2;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIRED_ROLE__UML_ELEMENT_UID = UimPackage.UML_REFERENCE__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIRED_ROLE__CONSTRAINT = UimPackage.UML_REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Required Role</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIRED_ROLE_FEATURE_COUNT = UimPackage.UML_REFERENCE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.constraint.impl.RootUserInteractionConstraintImpl <em>Root User Interaction Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.constraint.impl.RootUserInteractionConstraintImpl
	 * @see org.opaeum.uim.constraint.impl.ConstraintPackageImpl#getRootUserInteractionConstraint()
	 * @generated
	 */
	int ROOT_USER_INTERACTION_CONSTRAINT = 3;

	/**
	 * The feature id for the '<em><b>Requires Group Ownership</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOT_USER_INTERACTION_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP = 0;

	/**
	 * The feature id for the '<em><b>Requires Ownership</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOT_USER_INTERACTION_CONSTRAINT__REQUIRES_OWNERSHIP = 1;

	/**
	 * The feature id for the '<em><b>Required Roles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOT_USER_INTERACTION_CONSTRAINT__REQUIRED_ROLES = 2;

	/**
	 * The feature id for the '<em><b>Open To Public</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOT_USER_INTERACTION_CONSTRAINT__OPEN_TO_PUBLIC = 3;

	/**
	 * The feature id for the '<em><b>Required States</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOT_USER_INTERACTION_CONSTRAINT__REQUIRED_STATES = 4;

	/**
	 * The number of structural features of the '<em>Root User Interaction Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOT_USER_INTERACTION_CONSTRAINT_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.constraint.impl.UserInteractionConstraintImpl <em>User Interaction Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.constraint.impl.UserInteractionConstraintImpl
	 * @see org.opaeum.uim.constraint.impl.ConstraintPackageImpl#getUserInteractionConstraint()
	 * @generated
	 */
	int USER_INTERACTION_CONSTRAINT = 4;

	/**
	 * The feature id for the '<em><b>Requires Group Ownership</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERACTION_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP = ROOT_USER_INTERACTION_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP;

	/**
	 * The feature id for the '<em><b>Requires Ownership</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERACTION_CONSTRAINT__REQUIRES_OWNERSHIP = ROOT_USER_INTERACTION_CONSTRAINT__REQUIRES_OWNERSHIP;

	/**
	 * The feature id for the '<em><b>Required Roles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERACTION_CONSTRAINT__REQUIRED_ROLES = ROOT_USER_INTERACTION_CONSTRAINT__REQUIRED_ROLES;

	/**
	 * The feature id for the '<em><b>Open To Public</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERACTION_CONSTRAINT__OPEN_TO_PUBLIC = ROOT_USER_INTERACTION_CONSTRAINT__OPEN_TO_PUBLIC;

	/**
	 * The feature id for the '<em><b>Required States</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERACTION_CONSTRAINT__REQUIRED_STATES = ROOT_USER_INTERACTION_CONSTRAINT__REQUIRED_STATES;

	/**
	 * The feature id for the '<em><b>Inherit From Parent</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERACTION_CONSTRAINT__INHERIT_FROM_PARENT = ROOT_USER_INTERACTION_CONSTRAINT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>User Interaction Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_INTERACTION_CONSTRAINT_FEATURE_COUNT = ROOT_USER_INTERACTION_CONSTRAINT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.opaeum.uim.constraint.impl.RequiredStateImpl <em>Required State</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.uim.constraint.impl.RequiredStateImpl
	 * @see org.opaeum.uim.constraint.impl.ConstraintPackageImpl#getRequiredState()
	 * @generated
	 */
	int REQUIRED_STATE = 5;

	/**
	 * The feature id for the '<em><b>Uml Element Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIRED_STATE__UML_ELEMENT_UID = UimPackage.UML_REFERENCE__UML_ELEMENT_UID;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIRED_STATE__CONSTRAINT = UimPackage.UML_REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Required State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIRED_STATE_FEATURE_COUNT = UimPackage.UML_REFERENCE_FEATURE_COUNT + 1;


	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.constraint.EditableConstrainedObject <em>Editable Constrained Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Editable Constrained Object</em>'.
	 * @see org.opaeum.uim.constraint.EditableConstrainedObject
	 * @generated
	 */
	EClass getEditableConstrainedObject();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.constraint.EditableConstrainedObject#getEditability <em>Editability</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Editability</em>'.
	 * @see org.opaeum.uim.constraint.EditableConstrainedObject#getEditability()
	 * @see #getEditableConstrainedObject()
	 * @generated
	 */
	EReference getEditableConstrainedObject_Editability();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.constraint.ConstrainedObject <em>Constrained Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Constrained Object</em>'.
	 * @see org.opaeum.uim.constraint.ConstrainedObject
	 * @generated
	 */
	EClass getConstrainedObject();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.uim.constraint.ConstrainedObject#getVisibility <em>Visibility</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Visibility</em>'.
	 * @see org.opaeum.uim.constraint.ConstrainedObject#getVisibility()
	 * @see #getConstrainedObject()
	 * @generated
	 */
	EReference getConstrainedObject_Visibility();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.constraint.RequiredRole <em>Required Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Required Role</em>'.
	 * @see org.opaeum.uim.constraint.RequiredRole
	 * @generated
	 */
	EClass getRequiredRole();

	/**
	 * Returns the meta object for the container reference '{@link org.opaeum.uim.constraint.RequiredRole#getConstraint <em>Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Constraint</em>'.
	 * @see org.opaeum.uim.constraint.RequiredRole#getConstraint()
	 * @see #getRequiredRole()
	 * @generated
	 */
	EReference getRequiredRole_Constraint();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.constraint.RootUserInteractionConstraint <em>Root User Interaction Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Root User Interaction Constraint</em>'.
	 * @see org.opaeum.uim.constraint.RootUserInteractionConstraint
	 * @generated
	 */
	EClass getRootUserInteractionConstraint();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.uim.constraint.RootUserInteractionConstraint#isRequiresGroupOwnership <em>Requires Group Ownership</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Requires Group Ownership</em>'.
	 * @see org.opaeum.uim.constraint.RootUserInteractionConstraint#isRequiresGroupOwnership()
	 * @see #getRootUserInteractionConstraint()
	 * @generated
	 */
	EAttribute getRootUserInteractionConstraint_RequiresGroupOwnership();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.uim.constraint.RootUserInteractionConstraint#isRequiresOwnership <em>Requires Ownership</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Requires Ownership</em>'.
	 * @see org.opaeum.uim.constraint.RootUserInteractionConstraint#isRequiresOwnership()
	 * @see #getRootUserInteractionConstraint()
	 * @generated
	 */
	EAttribute getRootUserInteractionConstraint_RequiresOwnership();

	/**
	 * Returns the meta object for the containment reference list '{@link org.opaeum.uim.constraint.RootUserInteractionConstraint#getRequiredRoles <em>Required Roles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Required Roles</em>'.
	 * @see org.opaeum.uim.constraint.RootUserInteractionConstraint#getRequiredRoles()
	 * @see #getRootUserInteractionConstraint()
	 * @generated
	 */
	EReference getRootUserInteractionConstraint_RequiredRoles();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.uim.constraint.RootUserInteractionConstraint#getOpenToPublic <em>Open To Public</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Open To Public</em>'.
	 * @see org.opaeum.uim.constraint.RootUserInteractionConstraint#getOpenToPublic()
	 * @see #getRootUserInteractionConstraint()
	 * @generated
	 */
	EAttribute getRootUserInteractionConstraint_OpenToPublic();

	/**
	 * Returns the meta object for the containment reference list '{@link org.opaeum.uim.constraint.RootUserInteractionConstraint#getRequiredStates <em>Required States</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Required States</em>'.
	 * @see org.opaeum.uim.constraint.RootUserInteractionConstraint#getRequiredStates()
	 * @see #getRootUserInteractionConstraint()
	 * @generated
	 */
	EReference getRootUserInteractionConstraint_RequiredStates();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.constraint.UserInteractionConstraint <em>User Interaction Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User Interaction Constraint</em>'.
	 * @see org.opaeum.uim.constraint.UserInteractionConstraint
	 * @generated
	 */
	EClass getUserInteractionConstraint();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.uim.constraint.UserInteractionConstraint#isInheritFromParent <em>Inherit From Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Inherit From Parent</em>'.
	 * @see org.opaeum.uim.constraint.UserInteractionConstraint#isInheritFromParent()
	 * @see #getUserInteractionConstraint()
	 * @generated
	 */
	EAttribute getUserInteractionConstraint_InheritFromParent();

	/**
	 * Returns the meta object for class '{@link org.opaeum.uim.constraint.RequiredState <em>Required State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Required State</em>'.
	 * @see org.opaeum.uim.constraint.RequiredState
	 * @generated
	 */
	EClass getRequiredState();

	/**
	 * Returns the meta object for the container reference '{@link org.opaeum.uim.constraint.RequiredState#getConstraint <em>Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Constraint</em>'.
	 * @see org.opaeum.uim.constraint.RequiredState#getConstraint()
	 * @see #getRequiredState()
	 * @generated
	 */
	EReference getRequiredState_Constraint();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ConstraintFactory getConstraintFactory();

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
		 * The meta object literal for the '{@link org.opaeum.uim.constraint.impl.EditableConstrainedObjectImpl <em>Editable Constrained Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.constraint.impl.EditableConstrainedObjectImpl
		 * @see org.opaeum.uim.constraint.impl.ConstraintPackageImpl#getEditableConstrainedObject()
		 * @generated
		 */
		EClass EDITABLE_CONSTRAINED_OBJECT = eINSTANCE.getEditableConstrainedObject();

		/**
		 * The meta object literal for the '<em><b>Editability</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EDITABLE_CONSTRAINED_OBJECT__EDITABILITY = eINSTANCE.getEditableConstrainedObject_Editability();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.constraint.impl.ConstrainedObjectImpl <em>Constrained Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.constraint.impl.ConstrainedObjectImpl
		 * @see org.opaeum.uim.constraint.impl.ConstraintPackageImpl#getConstrainedObject()
		 * @generated
		 */
		EClass CONSTRAINED_OBJECT = eINSTANCE.getConstrainedObject();

		/**
		 * The meta object literal for the '<em><b>Visibility</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONSTRAINED_OBJECT__VISIBILITY = eINSTANCE.getConstrainedObject_Visibility();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.constraint.impl.RequiredRoleImpl <em>Required Role</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.constraint.impl.RequiredRoleImpl
		 * @see org.opaeum.uim.constraint.impl.ConstraintPackageImpl#getRequiredRole()
		 * @generated
		 */
		EClass REQUIRED_ROLE = eINSTANCE.getRequiredRole();

		/**
		 * The meta object literal for the '<em><b>Constraint</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REQUIRED_ROLE__CONSTRAINT = eINSTANCE.getRequiredRole_Constraint();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.constraint.impl.RootUserInteractionConstraintImpl <em>Root User Interaction Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.constraint.impl.RootUserInteractionConstraintImpl
		 * @see org.opaeum.uim.constraint.impl.ConstraintPackageImpl#getRootUserInteractionConstraint()
		 * @generated
		 */
		EClass ROOT_USER_INTERACTION_CONSTRAINT = eINSTANCE.getRootUserInteractionConstraint();

		/**
		 * The meta object literal for the '<em><b>Requires Group Ownership</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROOT_USER_INTERACTION_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP = eINSTANCE.getRootUserInteractionConstraint_RequiresGroupOwnership();

		/**
		 * The meta object literal for the '<em><b>Requires Ownership</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROOT_USER_INTERACTION_CONSTRAINT__REQUIRES_OWNERSHIP = eINSTANCE.getRootUserInteractionConstraint_RequiresOwnership();

		/**
		 * The meta object literal for the '<em><b>Required Roles</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROOT_USER_INTERACTION_CONSTRAINT__REQUIRED_ROLES = eINSTANCE.getRootUserInteractionConstraint_RequiredRoles();

		/**
		 * The meta object literal for the '<em><b>Open To Public</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROOT_USER_INTERACTION_CONSTRAINT__OPEN_TO_PUBLIC = eINSTANCE.getRootUserInteractionConstraint_OpenToPublic();

		/**
		 * The meta object literal for the '<em><b>Required States</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROOT_USER_INTERACTION_CONSTRAINT__REQUIRED_STATES = eINSTANCE.getRootUserInteractionConstraint_RequiredStates();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.constraint.impl.UserInteractionConstraintImpl <em>User Interaction Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.constraint.impl.UserInteractionConstraintImpl
		 * @see org.opaeum.uim.constraint.impl.ConstraintPackageImpl#getUserInteractionConstraint()
		 * @generated
		 */
		EClass USER_INTERACTION_CONSTRAINT = eINSTANCE.getUserInteractionConstraint();

		/**
		 * The meta object literal for the '<em><b>Inherit From Parent</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USER_INTERACTION_CONSTRAINT__INHERIT_FROM_PARENT = eINSTANCE.getUserInteractionConstraint_InheritFromParent();

		/**
		 * The meta object literal for the '{@link org.opaeum.uim.constraint.impl.RequiredStateImpl <em>Required State</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.uim.constraint.impl.RequiredStateImpl
		 * @see org.opaeum.uim.constraint.impl.ConstraintPackageImpl#getRequiredState()
		 * @generated
		 */
		EClass REQUIRED_STATE = eINSTANCE.getRequiredState();

		/**
		 * The meta object literal for the '<em><b>Constraint</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REQUIRED_STATE__CONSTRAINT = eINSTANCE.getRequiredState_Constraint();

	}

} //ConstraintPackage
