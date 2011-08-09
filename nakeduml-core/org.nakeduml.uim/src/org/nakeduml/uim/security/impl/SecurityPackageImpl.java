/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim.security.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.nakeduml.uim.UimPackage;
import org.nakeduml.uim.action.ActionPackage;
import org.nakeduml.uim.action.impl.ActionPackageImpl;
import org.nakeduml.uim.binding.BindingPackage;
import org.nakeduml.uim.binding.impl.BindingPackageImpl;
import org.nakeduml.uim.control.ControlPackage;
import org.nakeduml.uim.control.impl.ControlPackageImpl;
import org.nakeduml.uim.folder.FolderPackage;
import org.nakeduml.uim.folder.impl.FolderPackageImpl;
import org.nakeduml.uim.form.FormPackage;
import org.nakeduml.uim.form.impl.FormPackageImpl;
import org.nakeduml.uim.impl.UimPackageImpl;
import org.nakeduml.uim.layout.LayoutPackage;
import org.nakeduml.uim.layout.impl.LayoutPackageImpl;
import org.nakeduml.uim.security.EditableSecureObject;
import org.nakeduml.uim.security.RequiredRole;
import org.nakeduml.uim.security.SecureObject;
import org.nakeduml.uim.security.SecurityConstraint;
import org.nakeduml.uim.security.SecurityFactory;
import org.nakeduml.uim.security.SecurityPackage;
import org.nakeduml.uim.security.WorkspaceSecurityConstraint;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SecurityPackageImpl extends EPackageImpl implements SecurityPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass editableSecureObjectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass secureObjectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass requiredRoleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass workspaceSecurityConstraintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass securityConstraintEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.nakeduml.uim.security.SecurityPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private SecurityPackageImpl() {
		super(eNS_URI, SecurityFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link SecurityPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static SecurityPackage init() {
		if (isInited) return (SecurityPackage)EPackage.Registry.INSTANCE.getEPackage(SecurityPackage.eNS_URI);

		// Obtain or create and register package
		SecurityPackageImpl theSecurityPackage = (SecurityPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof SecurityPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new SecurityPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		UimPackageImpl theUimPackage = (UimPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UimPackage.eNS_URI) instanceof UimPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UimPackage.eNS_URI) : UimPackage.eINSTANCE);
		LayoutPackageImpl theLayoutPackage = (LayoutPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(LayoutPackage.eNS_URI) instanceof LayoutPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(LayoutPackage.eNS_URI) : LayoutPackage.eINSTANCE);
		ControlPackageImpl theControlPackage = (ControlPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ControlPackage.eNS_URI) instanceof ControlPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ControlPackage.eNS_URI) : ControlPackage.eINSTANCE);
		FolderPackageImpl theFolderPackage = (FolderPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(FolderPackage.eNS_URI) instanceof FolderPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(FolderPackage.eNS_URI) : FolderPackage.eINSTANCE);
		FormPackageImpl theFormPackage = (FormPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(FormPackage.eNS_URI) instanceof FormPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(FormPackage.eNS_URI) : FormPackage.eINSTANCE);
		BindingPackageImpl theBindingPackage = (BindingPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(BindingPackage.eNS_URI) instanceof BindingPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(BindingPackage.eNS_URI) : BindingPackage.eINSTANCE);
		ActionPackageImpl theActionPackage = (ActionPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ActionPackage.eNS_URI) instanceof ActionPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ActionPackage.eNS_URI) : ActionPackage.eINSTANCE);

		// Create package meta-data objects
		theSecurityPackage.createPackageContents();
		theUimPackage.createPackageContents();
		theLayoutPackage.createPackageContents();
		theControlPackage.createPackageContents();
		theFolderPackage.createPackageContents();
		theFormPackage.createPackageContents();
		theBindingPackage.createPackageContents();
		theActionPackage.createPackageContents();

		// Initialize created meta-data
		theSecurityPackage.initializePackageContents();
		theUimPackage.initializePackageContents();
		theLayoutPackage.initializePackageContents();
		theControlPackage.initializePackageContents();
		theFolderPackage.initializePackageContents();
		theFormPackage.initializePackageContents();
		theBindingPackage.initializePackageContents();
		theActionPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theSecurityPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(SecurityPackage.eNS_URI, theSecurityPackage);
		return theSecurityPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEditableSecureObject() {
		return editableSecureObjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEditableSecureObject_Editability() {
		return (EReference)editableSecureObjectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSecureObject() {
		return secureObjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSecureObject_Visibility() {
		return (EReference)secureObjectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRequiredRole() {
		return requiredRoleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWorkspaceSecurityConstraint() {
		return workspaceSecurityConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkspaceSecurityConstraint_RequiresGroupOwnership() {
		return (EAttribute)workspaceSecurityConstraintEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkspaceSecurityConstraint_RequiresOwnership() {
		return (EAttribute)workspaceSecurityConstraintEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkspaceSecurityConstraint_RequiredRoles() {
		return (EReference)workspaceSecurityConstraintEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSecurityConstraint() {
		return securityConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSecurityConstraint_InheritFromParent() {
		return (EAttribute)securityConstraintEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SecurityFactory getSecurityFactory() {
		return (SecurityFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		editableSecureObjectEClass = createEClass(EDITABLE_SECURE_OBJECT);
		createEReference(editableSecureObjectEClass, EDITABLE_SECURE_OBJECT__EDITABILITY);

		secureObjectEClass = createEClass(SECURE_OBJECT);
		createEReference(secureObjectEClass, SECURE_OBJECT__VISIBILITY);

		requiredRoleEClass = createEClass(REQUIRED_ROLE);

		workspaceSecurityConstraintEClass = createEClass(WORKSPACE_SECURITY_CONSTRAINT);
		createEAttribute(workspaceSecurityConstraintEClass, WORKSPACE_SECURITY_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP);
		createEAttribute(workspaceSecurityConstraintEClass, WORKSPACE_SECURITY_CONSTRAINT__REQUIRES_OWNERSHIP);
		createEReference(workspaceSecurityConstraintEClass, WORKSPACE_SECURITY_CONSTRAINT__REQUIRED_ROLES);

		securityConstraintEClass = createEClass(SECURITY_CONSTRAINT);
		createEAttribute(securityConstraintEClass, SECURITY_CONSTRAINT__INHERIT_FROM_PARENT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		UimPackage theUimPackage = (UimPackage)EPackage.Registry.INSTANCE.getEPackage(UimPackage.eNS_URI);
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		editableSecureObjectEClass.getESuperTypes().add(this.getSecureObject());
		requiredRoleEClass.getESuperTypes().add(theUimPackage.getUmlReference());
		securityConstraintEClass.getESuperTypes().add(this.getWorkspaceSecurityConstraint());

		// Initialize classes and features; add operations and parameters
		initEClass(editableSecureObjectEClass, EditableSecureObject.class, "EditableSecureObject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEditableSecureObject_Editability(), this.getSecurityConstraint(), null, "editability", null, 0, 1, EditableSecureObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(secureObjectEClass, SecureObject.class, "SecureObject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSecureObject_Visibility(), this.getSecurityConstraint(), null, "visibility", null, 0, 1, SecureObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(requiredRoleEClass, RequiredRole.class, "RequiredRole", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(workspaceSecurityConstraintEClass, WorkspaceSecurityConstraint.class, "WorkspaceSecurityConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWorkspaceSecurityConstraint_RequiresGroupOwnership(), theEcorePackage.getEBoolean(), "requiresGroupOwnership", null, 0, 1, WorkspaceSecurityConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkspaceSecurityConstraint_RequiresOwnership(), theEcorePackage.getEBoolean(), "requiresOwnership", null, 0, 1, WorkspaceSecurityConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWorkspaceSecurityConstraint_RequiredRoles(), this.getRequiredRole(), null, "requiredRoles", null, 0, -1, WorkspaceSecurityConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(securityConstraintEClass, SecurityConstraint.class, "SecurityConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSecurityConstraint_InheritFromParent(), theEcorePackage.getEBoolean(), "inheritFromParent", "true", 0, 1, SecurityConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
	}

} //SecurityPackageImpl
