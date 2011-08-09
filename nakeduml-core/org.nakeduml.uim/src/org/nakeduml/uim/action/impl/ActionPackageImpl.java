/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim.action.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.nakeduml.uim.UimPackage;
import org.nakeduml.uim.action.ActionFactory;
import org.nakeduml.uim.action.ActionKind;
import org.nakeduml.uim.action.ActionPackage;
import org.nakeduml.uim.action.BuiltInAction;
import org.nakeduml.uim.action.NavigationToEntity;
import org.nakeduml.uim.action.NavigationToOperation;
import org.nakeduml.uim.action.OperationAction;
import org.nakeduml.uim.action.TransitionAction;
import org.nakeduml.uim.action.UimAction;
import org.nakeduml.uim.action.UimNavigation;
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
import org.nakeduml.uim.security.SecurityPackage;
import org.nakeduml.uim.security.impl.SecurityPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ActionPackageImpl extends EPackageImpl implements ActionPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass builtInActionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass uimActionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass transitionActionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass navigationToOperationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass operationActionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass uimNavigationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass navigationToEntityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum actionKindEEnum = null;

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
	 * @see org.nakeduml.uim.action.ActionPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ActionPackageImpl() {
		super(eNS_URI, ActionFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link ActionPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ActionPackage init() {
		if (isInited) return (ActionPackage)EPackage.Registry.INSTANCE.getEPackage(ActionPackage.eNS_URI);

		// Obtain or create and register package
		ActionPackageImpl theActionPackage = (ActionPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ActionPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ActionPackageImpl());

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
		SecurityPackageImpl theSecurityPackage = (SecurityPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(SecurityPackage.eNS_URI) instanceof SecurityPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(SecurityPackage.eNS_URI) : SecurityPackage.eINSTANCE);

		// Create package meta-data objects
		theActionPackage.createPackageContents();
		theUimPackage.createPackageContents();
		theLayoutPackage.createPackageContents();
		theControlPackage.createPackageContents();
		theFolderPackage.createPackageContents();
		theFormPackage.createPackageContents();
		theBindingPackage.createPackageContents();
		theSecurityPackage.createPackageContents();

		// Initialize created meta-data
		theActionPackage.initializePackageContents();
		theUimPackage.initializePackageContents();
		theLayoutPackage.initializePackageContents();
		theControlPackage.initializePackageContents();
		theFolderPackage.initializePackageContents();
		theFormPackage.initializePackageContents();
		theBindingPackage.initializePackageContents();
		theSecurityPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theActionPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ActionPackage.eNS_URI, theActionPackage);
		return theActionPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBuiltInAction() {
		return builtInActionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBuiltInAction_Kind() {
		return (EAttribute)builtInActionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUimAction() {
		return uimActionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTransitionAction() {
		return transitionActionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNavigationToOperation() {
		return navigationToOperationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNavigationToOperation_ToForm() {
		return (EReference)navigationToOperationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOperationAction() {
		return operationActionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUimNavigation() {
		return uimNavigationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNavigationToEntity() {
		return navigationToEntityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNavigationToEntity_ToForm() {
		return (EReference)navigationToEntityEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNavigationToEntity_Binding() {
		return (EReference)navigationToEntityEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getActionKind() {
		return actionKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActionFactory getActionFactory() {
		return (ActionFactory)getEFactoryInstance();
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
		builtInActionEClass = createEClass(BUILT_IN_ACTION);
		createEAttribute(builtInActionEClass, BUILT_IN_ACTION__KIND);

		uimActionEClass = createEClass(UIM_ACTION);

		transitionActionEClass = createEClass(TRANSITION_ACTION);

		navigationToOperationEClass = createEClass(NAVIGATION_TO_OPERATION);
		createEReference(navigationToOperationEClass, NAVIGATION_TO_OPERATION__TO_FORM);

		operationActionEClass = createEClass(OPERATION_ACTION);

		uimNavigationEClass = createEClass(UIM_NAVIGATION);

		navigationToEntityEClass = createEClass(NAVIGATION_TO_ENTITY);
		createEReference(navigationToEntityEClass, NAVIGATION_TO_ENTITY__TO_FORM);
		createEReference(navigationToEntityEClass, NAVIGATION_TO_ENTITY__BINDING);

		// Create enums
		actionKindEEnum = createEEnum(ACTION_KIND);
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
		LayoutPackage theLayoutPackage = (LayoutPackage)EPackage.Registry.INSTANCE.getEPackage(LayoutPackage.eNS_URI);
		UimPackage theUimPackage = (UimPackage)EPackage.Registry.INSTANCE.getEPackage(UimPackage.eNS_URI);
		FormPackage theFormPackage = (FormPackage)EPackage.Registry.INSTANCE.getEPackage(FormPackage.eNS_URI);
		BindingPackage theBindingPackage = (BindingPackage)EPackage.Registry.INSTANCE.getEPackage(BindingPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		builtInActionEClass.getESuperTypes().add(this.getUimAction());
		uimActionEClass.getESuperTypes().add(theLayoutPackage.getOutlayableComponent());
		transitionActionEClass.getESuperTypes().add(this.getUimAction());
		transitionActionEClass.getESuperTypes().add(theUimPackage.getUmlReference());
		navigationToOperationEClass.getESuperTypes().add(this.getUimNavigation());
		navigationToOperationEClass.getESuperTypes().add(theUimPackage.getUmlReference());
		operationActionEClass.getESuperTypes().add(this.getUimAction());
		operationActionEClass.getESuperTypes().add(theUimPackage.getUmlReference());
		uimNavigationEClass.getESuperTypes().add(theLayoutPackage.getOutlayableComponent());
		navigationToEntityEClass.getESuperTypes().add(this.getUimNavigation());
		navigationToEntityEClass.getESuperTypes().add(theUimPackage.getUmlReference());

		// Initialize classes and features; add operations and parameters
		initEClass(builtInActionEClass, BuiltInAction.class, "BuiltInAction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBuiltInAction_Kind(), this.getActionKind(), "kind", null, 0, 1, BuiltInAction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimActionEClass, UimAction.class, "UimAction", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(transitionActionEClass, TransitionAction.class, "TransitionAction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(navigationToOperationEClass, NavigationToOperation.class, "NavigationToOperation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getNavigationToOperation_ToForm(), theFormPackage.getOperationInvocationForm(), null, "toForm", null, 0, 1, NavigationToOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(operationActionEClass, OperationAction.class, "OperationAction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimNavigationEClass, UimNavigation.class, "UimNavigation", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(navigationToEntityEClass, NavigationToEntity.class, "NavigationToEntity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getNavigationToEntity_ToForm(), theFormPackage.getClassForm(), null, "toForm", null, 0, 1, NavigationToEntity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNavigationToEntity_Binding(), theBindingPackage.getNavigationBinding(), theBindingPackage.getNavigationBinding_Navigation(), "binding", null, 0, 1, NavigationToEntity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(actionKindEEnum, ActionKind.class, "ActionKind");
		addEEnumLiteral(actionKindEEnum, ActionKind.UPDATE);
		addEEnumLiteral(actionKindEEnum, ActionKind.DELETE);
		addEEnumLiteral(actionKindEEnum, ActionKind.BACK);
		addEEnumLiteral(actionKindEEnum, ActionKind.EXECUTE_OPERATION);
		addEEnumLiteral(actionKindEEnum, ActionKind.DELEGATE_TASK);
		addEEnumLiteral(actionKindEEnum, ActionKind.COMPLETE_TASK);
		addEEnumLiteral(actionKindEEnum, ActionKind.SUSPEND_TASK);
		addEEnumLiteral(actionKindEEnum, ActionKind.CREATE);
		addEEnumLiteral(actionKindEEnum, ActionKind.FORWARD_TASK);
		addEEnumLiteral(actionKindEEnum, ActionKind.CLAIM_TASK);
	}

} //ActionPackageImpl
