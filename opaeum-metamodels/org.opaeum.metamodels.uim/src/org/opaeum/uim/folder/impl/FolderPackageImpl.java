/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opaeum.uim.folder.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.action.ActionPackage;
import org.opaeum.uim.action.impl.ActionPackageImpl;
import org.opaeum.uim.binding.BindingPackage;
import org.opaeum.uim.binding.impl.BindingPackageImpl;
import org.opaeum.uim.control.ControlPackage;
import org.opaeum.uim.control.impl.ControlPackageImpl;
import org.opaeum.uim.folder.AbstractFolder;
import org.opaeum.uim.folder.AbstractFormFolder;
import org.opaeum.uim.folder.ActivityFolder;
import org.opaeum.uim.folder.EntityFolder;
import org.opaeum.uim.folder.FolderFactory;
import org.opaeum.uim.folder.FolderPackage;
import org.opaeum.uim.folder.OperationContainingFolder;
import org.opaeum.uim.folder.PackageFolder;
import org.opaeum.uim.folder.StateMachineFolder;
import org.opaeum.uim.folder.UserInteractionModel;
import org.opaeum.uim.folder.UserInteractionWorkspace;
import org.opaeum.uim.form.FormPackage;
import org.opaeum.uim.form.impl.FormPackageImpl;
import org.opaeum.uim.impl.UimPackageImpl;
import org.opaeum.uim.layout.LayoutPackage;
import org.opaeum.uim.layout.impl.LayoutPackageImpl;
import org.opaeum.uim.security.SecurityPackage;
import org.opaeum.uim.security.impl.SecurityPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class FolderPackageImpl extends EPackageImpl implements FolderPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractFolderEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass activityFolderEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass entityFolderEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stateMachineFolderEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractFormFolderEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass packageFolderEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass operationContainingFolderEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass userInteractionModelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass userInteractionWorkspaceEClass = null;

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
	 * @see org.opaeum.uim.folder.FolderPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private FolderPackageImpl() {
		super(eNS_URI, FolderFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link FolderPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static FolderPackage init() {
		if (isInited) return (FolderPackage)EPackage.Registry.INSTANCE.getEPackage(FolderPackage.eNS_URI);

		// Obtain or create and register package
		FolderPackageImpl theFolderPackage = (FolderPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof FolderPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new FolderPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		UimPackageImpl theUimPackage = (UimPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UimPackage.eNS_URI) instanceof UimPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UimPackage.eNS_URI) : UimPackage.eINSTANCE);
		LayoutPackageImpl theLayoutPackage = (LayoutPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(LayoutPackage.eNS_URI) instanceof LayoutPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(LayoutPackage.eNS_URI) : LayoutPackage.eINSTANCE);
		ControlPackageImpl theControlPackage = (ControlPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ControlPackage.eNS_URI) instanceof ControlPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ControlPackage.eNS_URI) : ControlPackage.eINSTANCE);
		FormPackageImpl theFormPackage = (FormPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(FormPackage.eNS_URI) instanceof FormPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(FormPackage.eNS_URI) : FormPackage.eINSTANCE);
		BindingPackageImpl theBindingPackage = (BindingPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(BindingPackage.eNS_URI) instanceof BindingPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(BindingPackage.eNS_URI) : BindingPackage.eINSTANCE);
		SecurityPackageImpl theSecurityPackage = (SecurityPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(SecurityPackage.eNS_URI) instanceof SecurityPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(SecurityPackage.eNS_URI) : SecurityPackage.eINSTANCE);
		ActionPackageImpl theActionPackage = (ActionPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ActionPackage.eNS_URI) instanceof ActionPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ActionPackage.eNS_URI) : ActionPackage.eINSTANCE);

		// Create package meta-data objects
		theFolderPackage.createPackageContents();
		theUimPackage.createPackageContents();
		theLayoutPackage.createPackageContents();
		theControlPackage.createPackageContents();
		theFormPackage.createPackageContents();
		theBindingPackage.createPackageContents();
		theSecurityPackage.createPackageContents();
		theActionPackage.createPackageContents();

		// Initialize created meta-data
		theFolderPackage.initializePackageContents();
		theUimPackage.initializePackageContents();
		theLayoutPackage.initializePackageContents();
		theControlPackage.initializePackageContents();
		theFormPackage.initializePackageContents();
		theBindingPackage.initializePackageContents();
		theSecurityPackage.initializePackageContents();
		theActionPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theFolderPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(FolderPackage.eNS_URI, theFolderPackage);
		return theFolderPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractFolder() {
		return abstractFolderEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractFolder_Children() {
		return (EReference)abstractFolderEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getActivityFolder() {
		return activityFolderEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEntityFolder() {
		return entityFolderEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStateMachineFolder() {
		return stateMachineFolderEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractFormFolder() {
		return abstractFormFolderEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractFormFolder_Parent() {
		return (EReference)abstractFormFolderEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPackageFolder() {
		return packageFolderEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOperationContainingFolder() {
		return operationContainingFolderEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUserInteractionModel() {
		return userInteractionModelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUserInteractionWorkspace() {
		return userInteractionWorkspaceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUserInteractionWorkspace_Visibility() {
		return (EReference)userInteractionWorkspaceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUserInteractionWorkspace_Editability() {
		return (EReference)userInteractionWorkspaceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FolderFactory getFolderFactory() {
		return (FolderFactory)getEFactoryInstance();
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
		abstractFolderEClass = createEClass(ABSTRACT_FOLDER);
		createEReference(abstractFolderEClass, ABSTRACT_FOLDER__CHILDREN);

		activityFolderEClass = createEClass(ACTIVITY_FOLDER);

		entityFolderEClass = createEClass(ENTITY_FOLDER);

		stateMachineFolderEClass = createEClass(STATE_MACHINE_FOLDER);

		abstractFormFolderEClass = createEClass(ABSTRACT_FORM_FOLDER);
		createEReference(abstractFormFolderEClass, ABSTRACT_FORM_FOLDER__PARENT);

		packageFolderEClass = createEClass(PACKAGE_FOLDER);

		operationContainingFolderEClass = createEClass(OPERATION_CONTAINING_FOLDER);

		userInteractionModelEClass = createEClass(USER_INTERACTION_MODEL);

		userInteractionWorkspaceEClass = createEClass(USER_INTERACTION_WORKSPACE);
		createEReference(userInteractionWorkspaceEClass, USER_INTERACTION_WORKSPACE__VISIBILITY);
		createEReference(userInteractionWorkspaceEClass, USER_INTERACTION_WORKSPACE__EDITABILITY);
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
		SecurityPackage theSecurityPackage = (SecurityPackage)EPackage.Registry.INSTANCE.getEPackage(SecurityPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		abstractFolderEClass.getESuperTypes().add(theUimPackage.getUserInteractionElement());
		abstractFolderEClass.getESuperTypes().add(theUimPackage.getUmlReference());
		activityFolderEClass.getESuperTypes().add(this.getAbstractFormFolder());
		entityFolderEClass.getESuperTypes().add(this.getOperationContainingFolder());
		stateMachineFolderEClass.getESuperTypes().add(this.getOperationContainingFolder());
		abstractFormFolderEClass.getESuperTypes().add(this.getAbstractFolder());
		abstractFormFolderEClass.getESuperTypes().add(theSecurityPackage.getEditableSecureObject());
		packageFolderEClass.getESuperTypes().add(this.getAbstractFormFolder());
		operationContainingFolderEClass.getESuperTypes().add(this.getAbstractFormFolder());
		userInteractionModelEClass.getESuperTypes().add(theUimPackage.getUmlReference());
		userInteractionModelEClass.getESuperTypes().add(this.getAbstractFormFolder());
		userInteractionWorkspaceEClass.getESuperTypes().add(this.getAbstractFolder());

		// Initialize classes and features; add operations and parameters
		initEClass(abstractFolderEClass, AbstractFolder.class, "AbstractFolder", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAbstractFolder_Children(), this.getAbstractFormFolder(), this.getAbstractFormFolder_Parent(), "children", null, 0, -1, AbstractFolder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(activityFolderEClass, ActivityFolder.class, "ActivityFolder", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(entityFolderEClass, EntityFolder.class, "EntityFolder", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(stateMachineFolderEClass, StateMachineFolder.class, "StateMachineFolder", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(abstractFormFolderEClass, AbstractFormFolder.class, "AbstractFormFolder", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAbstractFormFolder_Parent(), this.getAbstractFolder(), this.getAbstractFolder_Children(), "parent", null, 0, 1, AbstractFormFolder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(packageFolderEClass, PackageFolder.class, "PackageFolder", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(operationContainingFolderEClass, OperationContainingFolder.class, "OperationContainingFolder", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(userInteractionModelEClass, UserInteractionModel.class, "UserInteractionModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(userInteractionWorkspaceEClass, UserInteractionWorkspace.class, "UserInteractionWorkspace", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUserInteractionWorkspace_Visibility(), theSecurityPackage.getWorkspaceSecurityConstraint(), null, "visibility", null, 0, 1, UserInteractionWorkspace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUserInteractionWorkspace_Editability(), theSecurityPackage.getWorkspaceSecurityConstraint(), null, "editability", null, 0, 1, UserInteractionWorkspace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create annotations
		// http:///org/eclipse/emf/ecore/util/ExtendedMetaData
		createExtendedMetaDataAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createExtendedMetaDataAnnotations() {
		String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";		
		addAnnotation
		  (getAbstractFolder_Children(), 
		   source, 
		   new String[] {
			 "wildcards", "",
			 "name", ""
		   });	
	}

} //FolderPackageImpl
