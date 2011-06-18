/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim.security;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.nakeduml.uim.UimPackage;

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
 * @see org.nakeduml.uim.security.SecurityFactory
 * @model kind="package"
 * @generated
 */
public interface SecurityPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "security";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://nakeduml.org/uimetamodel/security/1.0";

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
	SecurityPackage eINSTANCE = org.nakeduml.uim.security.impl.SecurityPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.security.impl.SecureObjectImpl <em>Secure Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.security.impl.SecureObjectImpl
	 * @see org.nakeduml.uim.security.impl.SecurityPackageImpl#getSecureObject()
	 * @generated
	 */
	int SECURE_OBJECT = 1;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECURE_OBJECT__VISIBILITY = 0;

	/**
	 * The number of structural features of the '<em>Secure Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECURE_OBJECT_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.security.impl.EditableSecureObjectImpl <em>Editable Secure Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.security.impl.EditableSecureObjectImpl
	 * @see org.nakeduml.uim.security.impl.SecurityPackageImpl#getEditableSecureObject()
	 * @generated
	 */
	int EDITABLE_SECURE_OBJECT = 0;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITABLE_SECURE_OBJECT__VISIBILITY = SECURE_OBJECT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Editability</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITABLE_SECURE_OBJECT__EDITABILITY = SECURE_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Editable Secure Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDITABLE_SECURE_OBJECT_FEATURE_COUNT = SECURE_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.security.impl.RequiredRoleImpl <em>Required Role</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.security.impl.RequiredRoleImpl
	 * @see org.nakeduml.uim.security.impl.SecurityPackageImpl#getRequiredRole()
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
	 * The number of structural features of the '<em>Required Role</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIRED_ROLE_FEATURE_COUNT = UimPackage.UML_REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.security.impl.WorkspaceSecurityConstraintImpl <em>Workspace Security Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.security.impl.WorkspaceSecurityConstraintImpl
	 * @see org.nakeduml.uim.security.impl.SecurityPackageImpl#getWorkspaceSecurityConstraint()
	 * @generated
	 */
	int WORKSPACE_SECURITY_CONSTRAINT = 3;

	/**
	 * The feature id for the '<em><b>Requires Group Ownership</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKSPACE_SECURITY_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP = 0;

	/**
	 * The feature id for the '<em><b>Requires Ownership</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKSPACE_SECURITY_CONSTRAINT__REQUIRES_OWNERSHIP = 1;

	/**
	 * The feature id for the '<em><b>Required Roles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKSPACE_SECURITY_CONSTRAINT__REQUIRED_ROLES = 2;

	/**
	 * The number of structural features of the '<em>Workspace Security Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKSPACE_SECURITY_CONSTRAINT_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.nakeduml.uim.security.impl.SecurityConstraintImpl <em>Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nakeduml.uim.security.impl.SecurityConstraintImpl
	 * @see org.nakeduml.uim.security.impl.SecurityPackageImpl#getSecurityConstraint()
	 * @generated
	 */
	int SECURITY_CONSTRAINT = 4;

	/**
	 * The feature id for the '<em><b>Requires Group Ownership</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECURITY_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP = WORKSPACE_SECURITY_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP;

	/**
	 * The feature id for the '<em><b>Requires Ownership</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECURITY_CONSTRAINT__REQUIRES_OWNERSHIP = WORKSPACE_SECURITY_CONSTRAINT__REQUIRES_OWNERSHIP;

	/**
	 * The feature id for the '<em><b>Required Roles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECURITY_CONSTRAINT__REQUIRED_ROLES = WORKSPACE_SECURITY_CONSTRAINT__REQUIRED_ROLES;

	/**
	 * The feature id for the '<em><b>Inherit From Parent</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECURITY_CONSTRAINT__INHERIT_FROM_PARENT = WORKSPACE_SECURITY_CONSTRAINT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECURITY_CONSTRAINT_FEATURE_COUNT = WORKSPACE_SECURITY_CONSTRAINT_FEATURE_COUNT + 1;


	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.security.EditableSecureObject <em>Editable Secure Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Editable Secure Object</em>'.
	 * @see org.nakeduml.uim.security.EditableSecureObject
	 * @generated
	 */
	EClass getEditableSecureObject();

	/**
	 * Returns the meta object for the containment reference '{@link org.nakeduml.uim.security.EditableSecureObject#getEditability <em>Editability</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Editability</em>'.
	 * @see org.nakeduml.uim.security.EditableSecureObject#getEditability()
	 * @see #getEditableSecureObject()
	 * @generated
	 */
	EReference getEditableSecureObject_Editability();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.security.SecureObject <em>Secure Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Secure Object</em>'.
	 * @see org.nakeduml.uim.security.SecureObject
	 * @generated
	 */
	EClass getSecureObject();

	/**
	 * Returns the meta object for the containment reference '{@link org.nakeduml.uim.security.SecureObject#getVisibility <em>Visibility</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Visibility</em>'.
	 * @see org.nakeduml.uim.security.SecureObject#getVisibility()
	 * @see #getSecureObject()
	 * @generated
	 */
	EReference getSecureObject_Visibility();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.security.RequiredRole <em>Required Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Required Role</em>'.
	 * @see org.nakeduml.uim.security.RequiredRole
	 * @generated
	 */
	EClass getRequiredRole();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.security.WorkspaceSecurityConstraint <em>Workspace Security Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Workspace Security Constraint</em>'.
	 * @see org.nakeduml.uim.security.WorkspaceSecurityConstraint
	 * @generated
	 */
	EClass getWorkspaceSecurityConstraint();

	/**
	 * Returns the meta object for the attribute '{@link org.nakeduml.uim.security.WorkspaceSecurityConstraint#isRequiresGroupOwnership <em>Requires Group Ownership</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Requires Group Ownership</em>'.
	 * @see org.nakeduml.uim.security.WorkspaceSecurityConstraint#isRequiresGroupOwnership()
	 * @see #getWorkspaceSecurityConstraint()
	 * @generated
	 */
	EAttribute getWorkspaceSecurityConstraint_RequiresGroupOwnership();

	/**
	 * Returns the meta object for the attribute '{@link org.nakeduml.uim.security.WorkspaceSecurityConstraint#isRequiresOwnership <em>Requires Ownership</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Requires Ownership</em>'.
	 * @see org.nakeduml.uim.security.WorkspaceSecurityConstraint#isRequiresOwnership()
	 * @see #getWorkspaceSecurityConstraint()
	 * @generated
	 */
	EAttribute getWorkspaceSecurityConstraint_RequiresOwnership();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nakeduml.uim.security.WorkspaceSecurityConstraint#getRequiredRoles <em>Required Roles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Required Roles</em>'.
	 * @see org.nakeduml.uim.security.WorkspaceSecurityConstraint#getRequiredRoles()
	 * @see #getWorkspaceSecurityConstraint()
	 * @generated
	 */
	EReference getWorkspaceSecurityConstraint_RequiredRoles();

	/**
	 * Returns the meta object for class '{@link org.nakeduml.uim.security.SecurityConstraint <em>Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Constraint</em>'.
	 * @see org.nakeduml.uim.security.SecurityConstraint
	 * @generated
	 */
	EClass getSecurityConstraint();

	/**
	 * Returns the meta object for the attribute '{@link org.nakeduml.uim.security.SecurityConstraint#isInheritFromParent <em>Inherit From Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Inherit From Parent</em>'.
	 * @see org.nakeduml.uim.security.SecurityConstraint#isInheritFromParent()
	 * @see #getSecurityConstraint()
	 * @generated
	 */
	EAttribute getSecurityConstraint_InheritFromParent();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SecurityFactory getSecurityFactory();

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
		 * The meta object literal for the '{@link org.nakeduml.uim.security.impl.EditableSecureObjectImpl <em>Editable Secure Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.security.impl.EditableSecureObjectImpl
		 * @see org.nakeduml.uim.security.impl.SecurityPackageImpl#getEditableSecureObject()
		 * @generated
		 */
		EClass EDITABLE_SECURE_OBJECT = eINSTANCE.getEditableSecureObject();

		/**
		 * The meta object literal for the '<em><b>Editability</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EDITABLE_SECURE_OBJECT__EDITABILITY = eINSTANCE.getEditableSecureObject_Editability();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.security.impl.SecureObjectImpl <em>Secure Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.security.impl.SecureObjectImpl
		 * @see org.nakeduml.uim.security.impl.SecurityPackageImpl#getSecureObject()
		 * @generated
		 */
		EClass SECURE_OBJECT = eINSTANCE.getSecureObject();

		/**
		 * The meta object literal for the '<em><b>Visibility</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SECURE_OBJECT__VISIBILITY = eINSTANCE.getSecureObject_Visibility();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.security.impl.RequiredRoleImpl <em>Required Role</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.security.impl.RequiredRoleImpl
		 * @see org.nakeduml.uim.security.impl.SecurityPackageImpl#getRequiredRole()
		 * @generated
		 */
		EClass REQUIRED_ROLE = eINSTANCE.getRequiredRole();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.security.impl.WorkspaceSecurityConstraintImpl <em>Workspace Security Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.security.impl.WorkspaceSecurityConstraintImpl
		 * @see org.nakeduml.uim.security.impl.SecurityPackageImpl#getWorkspaceSecurityConstraint()
		 * @generated
		 */
		EClass WORKSPACE_SECURITY_CONSTRAINT = eINSTANCE.getWorkspaceSecurityConstraint();

		/**
		 * The meta object literal for the '<em><b>Requires Group Ownership</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORKSPACE_SECURITY_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP = eINSTANCE.getWorkspaceSecurityConstraint_RequiresGroupOwnership();

		/**
		 * The meta object literal for the '<em><b>Requires Ownership</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORKSPACE_SECURITY_CONSTRAINT__REQUIRES_OWNERSHIP = eINSTANCE.getWorkspaceSecurityConstraint_RequiresOwnership();

		/**
		 * The meta object literal for the '<em><b>Required Roles</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORKSPACE_SECURITY_CONSTRAINT__REQUIRED_ROLES = eINSTANCE.getWorkspaceSecurityConstraint_RequiredRoles();

		/**
		 * The meta object literal for the '{@link org.nakeduml.uim.security.impl.SecurityConstraintImpl <em>Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nakeduml.uim.security.impl.SecurityConstraintImpl
		 * @see org.nakeduml.uim.security.impl.SecurityPackageImpl#getSecurityConstraint()
		 * @generated
		 */
		EClass SECURITY_CONSTRAINT = eINSTANCE.getSecurityConstraint();

		/**
		 * The meta object literal for the '<em><b>Inherit From Parent</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SECURITY_CONSTRAINT__INHERIT_FROM_PARENT = eINSTANCE.getSecurityConstraint_InheritFromParent();

	}

} //SecurityPackage
