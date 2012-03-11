/**
 */
package org.opaeum.uim.action.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.action.ActionFactory;
import org.opaeum.uim.action.ActionKind;
import org.opaeum.uim.action.ActionPackage;
import org.opaeum.uim.action.BuiltInAction;
import org.opaeum.uim.action.LinkToEntity;
import org.opaeum.uim.action.LinkToOperation;
import org.opaeum.uim.action.OperationAction;
import org.opaeum.uim.action.OperationActionPopup;
import org.opaeum.uim.action.TransitionAction;
import org.opaeum.uim.action.UimAction;
import org.opaeum.uim.action.UimLink;
import org.opaeum.uim.binding.BindingPackage;
import org.opaeum.uim.binding.impl.BindingPackageImpl;
import org.opaeum.uim.constraint.ConstraintPackage;
import org.opaeum.uim.constraint.impl.ConstraintPackageImpl;
import org.opaeum.uim.control.ControlPackage;
import org.opaeum.uim.control.impl.ControlPackageImpl;
import org.opaeum.uim.editor.EditorPackage;
import org.opaeum.uim.editor.impl.EditorPackageImpl;
import org.opaeum.uim.impl.UimPackageImpl;
import org.opaeum.uim.panel.PanelPackage;
import org.opaeum.uim.panel.impl.PanelPackageImpl;
import org.opaeum.uim.perspective.PerspectivePackage;
import org.opaeum.uim.perspective.impl.PerspectivePackageImpl;
import org.opaeum.uim.wizard.WizardPackage;
import org.opaeum.uim.wizard.impl.WizardPackageImpl;

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
	private EClass linkToOperationEClass = null;

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
	private EClass uimLinkEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass linkToEntityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass operationActionPopupEClass = null;

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
	 * @see org.opaeum.uim.action.ActionPackage#eNS_URI
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
		ControlPackageImpl theControlPackage = (ControlPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ControlPackage.eNS_URI) instanceof ControlPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ControlPackage.eNS_URI) : ControlPackage.eINSTANCE);
		EditorPackageImpl theEditorPackage = (EditorPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(EditorPackage.eNS_URI) instanceof EditorPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(EditorPackage.eNS_URI) : EditorPackage.eINSTANCE);
		BindingPackageImpl theBindingPackage = (BindingPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(BindingPackage.eNS_URI) instanceof BindingPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(BindingPackage.eNS_URI) : BindingPackage.eINSTANCE);
		ConstraintPackageImpl theConstraintPackage = (ConstraintPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ConstraintPackage.eNS_URI) instanceof ConstraintPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ConstraintPackage.eNS_URI) : ConstraintPackage.eINSTANCE);
		PanelPackageImpl thePanelPackage = (PanelPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(PanelPackage.eNS_URI) instanceof PanelPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(PanelPackage.eNS_URI) : PanelPackage.eINSTANCE);
		WizardPackageImpl theWizardPackage = (WizardPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(WizardPackage.eNS_URI) instanceof WizardPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(WizardPackage.eNS_URI) : WizardPackage.eINSTANCE);
		PerspectivePackageImpl thePerspectivePackage = (PerspectivePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(PerspectivePackage.eNS_URI) instanceof PerspectivePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(PerspectivePackage.eNS_URI) : PerspectivePackage.eINSTANCE);

		// Create package meta-data objects
		theActionPackage.createPackageContents();
		theUimPackage.createPackageContents();
		theControlPackage.createPackageContents();
		theEditorPackage.createPackageContents();
		theBindingPackage.createPackageContents();
		theConstraintPackage.createPackageContents();
		thePanelPackage.createPackageContents();
		theWizardPackage.createPackageContents();
		thePerspectivePackage.createPackageContents();

		// Initialize created meta-data
		theActionPackage.initializePackageContents();
		theUimPackage.initializePackageContents();
		theControlPackage.initializePackageContents();
		theEditorPackage.initializePackageContents();
		theBindingPackage.initializePackageContents();
		theConstraintPackage.initializePackageContents();
		thePanelPackage.initializePackageContents();
		theWizardPackage.initializePackageContents();
		thePerspectivePackage.initializePackageContents();

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
	public EClass getLinkToOperation() {
		return linkToOperationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLinkToOperation_ToForm() {
		return (EReference)linkToOperationEClass.getEStructuralFeatures().get(0);
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
	public EReference getOperationAction_Popup() {
		return (EReference)operationActionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUimLink() {
		return uimLinkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLinkToEntity() {
		return linkToEntityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLinkToEntity_Binding() {
		return (EReference)linkToEntityEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOperationActionPopup() {
		return operationActionPopupEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOperationActionPopup_OperationAction() {
		return (EReference)operationActionPopupEClass.getEStructuralFeatures().get(0);
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

		linkToOperationEClass = createEClass(LINK_TO_OPERATION);
		createEReference(linkToOperationEClass, LINK_TO_OPERATION__TO_FORM);

		operationActionEClass = createEClass(OPERATION_ACTION);
		createEReference(operationActionEClass, OPERATION_ACTION__POPUP);

		uimLinkEClass = createEClass(UIM_LINK);

		linkToEntityEClass = createEClass(LINK_TO_ENTITY);
		createEReference(linkToEntityEClass, LINK_TO_ENTITY__BINDING);

		operationActionPopupEClass = createEClass(OPERATION_ACTION_POPUP);
		createEReference(operationActionPopupEClass, OPERATION_ACTION_POPUP__OPERATION_ACTION);

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
		UimPackage theUimPackage = (UimPackage)EPackage.Registry.INSTANCE.getEPackage(UimPackage.eNS_URI);
		PanelPackage thePanelPackage = (PanelPackage)EPackage.Registry.INSTANCE.getEPackage(PanelPackage.eNS_URI);
		EditorPackage theEditorPackage = (EditorPackage)EPackage.Registry.INSTANCE.getEPackage(EditorPackage.eNS_URI);
		BindingPackage theBindingPackage = (BindingPackage)EPackage.Registry.INSTANCE.getEPackage(BindingPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		builtInActionEClass.getESuperTypes().add(this.getUimAction());
		uimActionEClass.getESuperTypes().add(theUimPackage.getUimComponent());
		uimActionEClass.getESuperTypes().add(thePanelPackage.getOutlayable());
		transitionActionEClass.getESuperTypes().add(this.getUimAction());
		transitionActionEClass.getESuperTypes().add(theUimPackage.getUmlReference());
		linkToOperationEClass.getESuperTypes().add(this.getUimLink());
		linkToOperationEClass.getESuperTypes().add(theUimPackage.getUmlReference());
		operationActionEClass.getESuperTypes().add(this.getUimAction());
		operationActionEClass.getESuperTypes().add(theUimPackage.getUmlReference());
		uimLinkEClass.getESuperTypes().add(theUimPackage.getUimComponent());
		uimLinkEClass.getESuperTypes().add(thePanelPackage.getOutlayable());
		linkToEntityEClass.getESuperTypes().add(this.getUimLink());
		linkToEntityEClass.getESuperTypes().add(theUimPackage.getUmlReference());
		operationActionPopupEClass.getESuperTypes().add(theUimPackage.getUserInterface());

		// Initialize classes and features; add operations and parameters
		initEClass(builtInActionEClass, BuiltInAction.class, "BuiltInAction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBuiltInAction_Kind(), this.getActionKind(), "kind", null, 0, 1, BuiltInAction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimActionEClass, UimAction.class, "UimAction", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(transitionActionEClass, TransitionAction.class, "TransitionAction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(linkToOperationEClass, LinkToOperation.class, "LinkToOperation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getLinkToOperation_ToForm(), theEditorPackage.getQueryInvocationEditor(), null, "toForm", null, 0, 1, LinkToOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(operationActionEClass, OperationAction.class, "OperationAction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOperationAction_Popup(), this.getOperationActionPopup(), this.getOperationActionPopup_OperationAction(), "popup", null, 0, 1, OperationAction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimLinkEClass, UimLink.class, "UimLink", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(linkToEntityEClass, LinkToEntity.class, "LinkToEntity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getLinkToEntity_Binding(), theBindingPackage.getNavigationBinding(), theBindingPackage.getNavigationBinding_Navigation(), "binding", null, 0, 1, LinkToEntity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(operationActionPopupEClass, OperationActionPopup.class, "OperationActionPopup", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOperationActionPopup_OperationAction(), this.getOperationAction(), this.getOperationAction_Popup(), "operationAction", null, 0, 1, OperationActionPopup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(actionKindEEnum, ActionKind.class, "ActionKind");
		addEEnumLiteral(actionKindEEnum, ActionKind.UPDATE);
		addEEnumLiteral(actionKindEEnum, ActionKind.DELETE);
		addEEnumLiteral(actionKindEEnum, ActionKind.EXECUTE_OPERATION);
		addEEnumLiteral(actionKindEEnum, ActionKind.DELEGATE_TASK);
		addEEnumLiteral(actionKindEEnum, ActionKind.COMPLETE_TASK);
		addEEnumLiteral(actionKindEEnum, ActionKind.SUSPEND_TASK);
		addEEnumLiteral(actionKindEEnum, ActionKind.FORWARD_TASK);
		addEEnumLiteral(actionKindEEnum, ActionKind.CLAIM_TASK);
	}

} //ActionPackageImpl
