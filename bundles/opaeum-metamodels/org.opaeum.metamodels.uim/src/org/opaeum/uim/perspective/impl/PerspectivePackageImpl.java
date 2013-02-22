/**
 */
package org.opaeum.uim.perspective.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.action.ActionPackage;
import org.opaeum.uim.action.impl.ActionPackageImpl;
import org.opaeum.uim.binding.BindingPackage;
import org.opaeum.uim.binding.impl.BindingPackageImpl;
import org.opaeum.uim.component.ComponentPackage;
import org.opaeum.uim.component.impl.ComponentPackageImpl;
import org.opaeum.uim.constraint.ConstraintPackage;
import org.opaeum.uim.constraint.impl.ConstraintPackageImpl;
import org.opaeum.uim.control.ControlPackage;
import org.opaeum.uim.control.impl.ControlPackageImpl;
import org.opaeum.uim.cube.CubePackage;
import org.opaeum.uim.cube.impl.CubePackageImpl;
import org.opaeum.uim.editor.EditorPackage;
import org.opaeum.uim.editor.impl.EditorPackageImpl;
import org.opaeum.uim.impl.UimPackageImpl;
import org.opaeum.uim.model.ModelPackage;
import org.opaeum.uim.model.impl.ModelPackageImpl;
import org.opaeum.uim.panel.PanelPackage;
import org.opaeum.uim.panel.impl.PanelPackageImpl;
import org.opaeum.uim.perspective.EditorConfiguration;
import org.opaeum.uim.perspective.ExplorerBehaviorConstraint;
import org.opaeum.uim.perspective.ExplorerClassConstraint;
import org.opaeum.uim.perspective.ExplorerConfiguration;
import org.opaeum.uim.perspective.ExplorerConstraint;
import org.opaeum.uim.perspective.ExplorerOperationConstraint;
import org.opaeum.uim.perspective.ExplorerPropertyConstraint;
import org.opaeum.uim.perspective.InboxConfiguration;
import org.opaeum.uim.perspective.OutboxConfiguration;
import org.opaeum.uim.perspective.PerspectiveConfiguration;
import org.opaeum.uim.perspective.PerspectiveFactory;
import org.opaeum.uim.perspective.PerspectivePackage;
import org.opaeum.uim.perspective.PositionInPerspective;
import org.opaeum.uim.perspective.PropertiesConfiguration;
import org.opaeum.uim.perspective.ViewAllocation;
import org.opaeum.uim.wizard.WizardPackage;
import org.opaeum.uim.wizard.impl.WizardPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class PerspectivePackageImpl extends EPackageImpl implements PerspectivePackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass perspectiveConfigurationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass viewAllocationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass explorerConfigurationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass explorerClassConstraintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass explorerPropertyConstraintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass editorConfigurationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass propertiesConfigurationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass explorerConstraintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass explorerOperationConstraintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass explorerBehaviorConstraintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass inboxConfigurationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass outboxConfigurationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum positionInPerspectiveEEnum = null;

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
	 * @see org.opaeum.uim.perspective.PerspectivePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private PerspectivePackageImpl() {
		super(eNS_URI, PerspectiveFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link PerspectivePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static PerspectivePackage init() {
		if (isInited) return (PerspectivePackage)EPackage.Registry.INSTANCE.getEPackage(PerspectivePackage.eNS_URI);

		// Obtain or create and register package
		PerspectivePackageImpl thePerspectivePackage = (PerspectivePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof PerspectivePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new PerspectivePackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		UimPackageImpl theUimPackage = (UimPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UimPackage.eNS_URI) instanceof UimPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UimPackage.eNS_URI) : UimPackage.eINSTANCE);
		ControlPackageImpl theControlPackage = (ControlPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ControlPackage.eNS_URI) instanceof ControlPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ControlPackage.eNS_URI) : ControlPackage.eINSTANCE);
		EditorPackageImpl theEditorPackage = (EditorPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(EditorPackage.eNS_URI) instanceof EditorPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(EditorPackage.eNS_URI) : EditorPackage.eINSTANCE);
		BindingPackageImpl theBindingPackage = (BindingPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(BindingPackage.eNS_URI) instanceof BindingPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(BindingPackage.eNS_URI) : BindingPackage.eINSTANCE);
		ConstraintPackageImpl theConstraintPackage = (ConstraintPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ConstraintPackage.eNS_URI) instanceof ConstraintPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ConstraintPackage.eNS_URI) : ConstraintPackage.eINSTANCE);
		ActionPackageImpl theActionPackage = (ActionPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ActionPackage.eNS_URI) instanceof ActionPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ActionPackage.eNS_URI) : ActionPackage.eINSTANCE);
		PanelPackageImpl thePanelPackage = (PanelPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(PanelPackage.eNS_URI) instanceof PanelPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(PanelPackage.eNS_URI) : PanelPackage.eINSTANCE);
		WizardPackageImpl theWizardPackage = (WizardPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(WizardPackage.eNS_URI) instanceof WizardPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(WizardPackage.eNS_URI) : WizardPackage.eINSTANCE);
		CubePackageImpl theCubePackage = (CubePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CubePackage.eNS_URI) instanceof CubePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CubePackage.eNS_URI) : CubePackage.eINSTANCE);
		ModelPackageImpl theModelPackage = (ModelPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI) instanceof ModelPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI) : ModelPackage.eINSTANCE);
		ComponentPackageImpl theComponentPackage = (ComponentPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ComponentPackage.eNS_URI) instanceof ComponentPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ComponentPackage.eNS_URI) : ComponentPackage.eINSTANCE);

		// Create package meta-data objects
		thePerspectivePackage.createPackageContents();
		theUimPackage.createPackageContents();
		theControlPackage.createPackageContents();
		theEditorPackage.createPackageContents();
		theBindingPackage.createPackageContents();
		theConstraintPackage.createPackageContents();
		theActionPackage.createPackageContents();
		thePanelPackage.createPackageContents();
		theWizardPackage.createPackageContents();
		theCubePackage.createPackageContents();
		theModelPackage.createPackageContents();
		theComponentPackage.createPackageContents();

		// Initialize created meta-data
		thePerspectivePackage.initializePackageContents();
		theUimPackage.initializePackageContents();
		theControlPackage.initializePackageContents();
		theEditorPackage.initializePackageContents();
		theBindingPackage.initializePackageContents();
		theConstraintPackage.initializePackageContents();
		theActionPackage.initializePackageContents();
		thePanelPackage.initializePackageContents();
		theWizardPackage.initializePackageContents();
		theCubePackage.initializePackageContents();
		theModelPackage.initializePackageContents();
		theComponentPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		thePerspectivePackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(PerspectivePackage.eNS_URI, thePerspectivePackage);
		return thePerspectivePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPerspectiveConfiguration() {
		return perspectiveConfigurationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPerspectiveConfiguration_Explorer() {
		return (EReference)perspectiveConfigurationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPerspectiveConfiguration_Editor() {
		return (EReference)perspectiveConfigurationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPerspectiveConfiguration_Properties() {
		return (EReference)perspectiveConfigurationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPerspectiveConfiguration_Inbox() {
		return (EReference)perspectiveConfigurationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPerspectiveConfiguration_Outbox() {
		return (EReference)perspectiveConfigurationEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getViewAllocation() {
		return viewAllocationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getViewAllocation_Width() {
		return (EAttribute)viewAllocationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getViewAllocation_Height() {
		return (EAttribute)viewAllocationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getViewAllocation_Position() {
		return (EAttribute)viewAllocationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExplorerConfiguration() {
		return explorerConfigurationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExplorerConfiguration_Classes() {
		return (EReference)explorerConfigurationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExplorerClassConstraint() {
		return explorerClassConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExplorerClassConstraint_ExplorerConfiguration() {
		return (EReference)explorerClassConstraintEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExplorerClassConstraint_Properties() {
		return (EReference)explorerClassConstraintEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExplorerClassConstraint_NewObjectConstraint() {
		return (EReference)explorerClassConstraintEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExplorerClassConstraint_Behaviors() {
		return (EReference)explorerClassConstraintEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExplorerClassConstraint_Operations() {
		return (EReference)explorerClassConstraintEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExplorerPropertyConstraint() {
		return explorerPropertyConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExplorerPropertyConstraint_Owner() {
		return (EReference)explorerPropertyConstraintEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEditorConfiguration() {
		return editorConfigurationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPropertiesConfiguration() {
		return propertiesConfigurationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExplorerConstraint() {
		return explorerConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExplorerConstraint_Hidden() {
		return (EAttribute)explorerConstraintEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExplorerOperationConstraint() {
		return explorerOperationConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExplorerOperationConstraint_Owner() {
		return (EReference)explorerOperationConstraintEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExplorerBehaviorConstraint() {
		return explorerBehaviorConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExplorerBehaviorConstraint_InvocationConstraint() {
		return (EReference)explorerBehaviorConstraintEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExplorerBehaviorConstraint_Owner() {
		return (EReference)explorerBehaviorConstraintEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInboxConfiguration() {
		return inboxConfigurationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOutboxConfiguration() {
		return outboxConfigurationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getPositionInPerspective() {
		return positionInPerspectiveEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PerspectiveFactory getPerspectiveFactory() {
		return (PerspectiveFactory)getEFactoryInstance();
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
		perspectiveConfigurationEClass = createEClass(PERSPECTIVE_CONFIGURATION);
		createEReference(perspectiveConfigurationEClass, PERSPECTIVE_CONFIGURATION__EXPLORER);
		createEReference(perspectiveConfigurationEClass, PERSPECTIVE_CONFIGURATION__EDITOR);
		createEReference(perspectiveConfigurationEClass, PERSPECTIVE_CONFIGURATION__PROPERTIES);
		createEReference(perspectiveConfigurationEClass, PERSPECTIVE_CONFIGURATION__INBOX);
		createEReference(perspectiveConfigurationEClass, PERSPECTIVE_CONFIGURATION__OUTBOX);

		viewAllocationEClass = createEClass(VIEW_ALLOCATION);
		createEAttribute(viewAllocationEClass, VIEW_ALLOCATION__WIDTH);
		createEAttribute(viewAllocationEClass, VIEW_ALLOCATION__HEIGHT);
		createEAttribute(viewAllocationEClass, VIEW_ALLOCATION__POSITION);

		explorerConfigurationEClass = createEClass(EXPLORER_CONFIGURATION);
		createEReference(explorerConfigurationEClass, EXPLORER_CONFIGURATION__CLASSES);

		explorerClassConstraintEClass = createEClass(EXPLORER_CLASS_CONSTRAINT);
		createEReference(explorerClassConstraintEClass, EXPLORER_CLASS_CONSTRAINT__EXPLORER_CONFIGURATION);
		createEReference(explorerClassConstraintEClass, EXPLORER_CLASS_CONSTRAINT__PROPERTIES);
		createEReference(explorerClassConstraintEClass, EXPLORER_CLASS_CONSTRAINT__NEW_OBJECT_CONSTRAINT);
		createEReference(explorerClassConstraintEClass, EXPLORER_CLASS_CONSTRAINT__BEHAVIORS);
		createEReference(explorerClassConstraintEClass, EXPLORER_CLASS_CONSTRAINT__OPERATIONS);

		explorerPropertyConstraintEClass = createEClass(EXPLORER_PROPERTY_CONSTRAINT);
		createEReference(explorerPropertyConstraintEClass, EXPLORER_PROPERTY_CONSTRAINT__OWNER);

		editorConfigurationEClass = createEClass(EDITOR_CONFIGURATION);

		propertiesConfigurationEClass = createEClass(PROPERTIES_CONFIGURATION);

		explorerConstraintEClass = createEClass(EXPLORER_CONSTRAINT);
		createEAttribute(explorerConstraintEClass, EXPLORER_CONSTRAINT__HIDDEN);

		explorerOperationConstraintEClass = createEClass(EXPLORER_OPERATION_CONSTRAINT);
		createEReference(explorerOperationConstraintEClass, EXPLORER_OPERATION_CONSTRAINT__OWNER);

		explorerBehaviorConstraintEClass = createEClass(EXPLORER_BEHAVIOR_CONSTRAINT);
		createEReference(explorerBehaviorConstraintEClass, EXPLORER_BEHAVIOR_CONSTRAINT__INVOCATION_CONSTRAINT);
		createEReference(explorerBehaviorConstraintEClass, EXPLORER_BEHAVIOR_CONSTRAINT__OWNER);

		inboxConfigurationEClass = createEClass(INBOX_CONFIGURATION);

		outboxConfigurationEClass = createEClass(OUTBOX_CONFIGURATION);

		// Create enums
		positionInPerspectiveEEnum = createEEnum(POSITION_IN_PERSPECTIVE);
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
		ConstraintPackage theConstraintPackage = (ConstraintPackage)EPackage.Registry.INSTANCE.getEPackage(ConstraintPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		perspectiveConfigurationEClass.getESuperTypes().add(theUimPackage.getUserInteractionElement());
		viewAllocationEClass.getESuperTypes().add(theUimPackage.getUserInteractionElement());
		explorerConfigurationEClass.getESuperTypes().add(this.getViewAllocation());
		explorerClassConstraintEClass.getESuperTypes().add(this.getExplorerConstraint());
		explorerPropertyConstraintEClass.getESuperTypes().add(this.getExplorerConstraint());
		editorConfigurationEClass.getESuperTypes().add(this.getViewAllocation());
		propertiesConfigurationEClass.getESuperTypes().add(this.getViewAllocation());
		explorerConstraintEClass.getESuperTypes().add(theConstraintPackage.getUserInteractionConstraint());
		explorerConstraintEClass.getESuperTypes().add(theUimPackage.getLabeledElement());
		explorerOperationConstraintEClass.getESuperTypes().add(this.getExplorerConstraint());
		explorerBehaviorConstraintEClass.getESuperTypes().add(this.getExplorerConstraint());
		inboxConfigurationEClass.getESuperTypes().add(this.getViewAllocation());
		outboxConfigurationEClass.getESuperTypes().add(this.getViewAllocation());

		// Initialize classes and features; add operations and parameters
		initEClass(perspectiveConfigurationEClass, PerspectiveConfiguration.class, "PerspectiveConfiguration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPerspectiveConfiguration_Explorer(), this.getExplorerConfiguration(), null, "explorer", null, 0, 1, PerspectiveConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPerspectiveConfiguration_Editor(), this.getEditorConfiguration(), null, "editor", null, 0, 1, PerspectiveConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPerspectiveConfiguration_Properties(), this.getPropertiesConfiguration(), null, "properties", null, 0, 1, PerspectiveConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPerspectiveConfiguration_Inbox(), this.getInboxConfiguration(), null, "inbox", null, 0, 1, PerspectiveConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPerspectiveConfiguration_Outbox(), this.getOutboxConfiguration(), null, "outbox", null, 0, 1, PerspectiveConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(viewAllocationEClass, ViewAllocation.class, "ViewAllocation", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getViewAllocation_Width(), ecorePackage.getEIntegerObject(), "width", null, 0, 1, ViewAllocation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getViewAllocation_Height(), theEcorePackage.getEIntegerObject(), "height", null, 0, 1, ViewAllocation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getViewAllocation_Position(), this.getPositionInPerspective(), "position", null, 0, 1, ViewAllocation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(explorerConfigurationEClass, ExplorerConfiguration.class, "ExplorerConfiguration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getExplorerConfiguration_Classes(), this.getExplorerClassConstraint(), this.getExplorerClassConstraint_ExplorerConfiguration(), "classes", null, 0, -1, ExplorerConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(explorerClassConstraintEClass, ExplorerClassConstraint.class, "ExplorerClassConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getExplorerClassConstraint_ExplorerConfiguration(), this.getExplorerConfiguration(), this.getExplorerConfiguration_Classes(), "explorerConfiguration", null, 1, 1, ExplorerClassConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExplorerClassConstraint_Properties(), this.getExplorerPropertyConstraint(), this.getExplorerPropertyConstraint_Owner(), "properties", null, 0, -1, ExplorerClassConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExplorerClassConstraint_NewObjectConstraint(), theConstraintPackage.getUserInteractionConstraint(), null, "newObjectConstraint", null, 0, 1, ExplorerClassConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExplorerClassConstraint_Behaviors(), this.getExplorerBehaviorConstraint(), this.getExplorerBehaviorConstraint_Owner(), "behaviors", null, 0, -1, ExplorerClassConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExplorerClassConstraint_Operations(), this.getExplorerOperationConstraint(), this.getExplorerOperationConstraint_Owner(), "operations", null, 0, -1, ExplorerClassConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(explorerPropertyConstraintEClass, ExplorerPropertyConstraint.class, "ExplorerPropertyConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getExplorerPropertyConstraint_Owner(), this.getExplorerClassConstraint(), this.getExplorerClassConstraint_Properties(), "owner", null, 0, 1, ExplorerPropertyConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(editorConfigurationEClass, EditorConfiguration.class, "EditorConfiguration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(propertiesConfigurationEClass, PropertiesConfiguration.class, "PropertiesConfiguration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(explorerConstraintEClass, ExplorerConstraint.class, "ExplorerConstraint", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getExplorerConstraint_Hidden(), theEcorePackage.getEBoolean(), "hidden", null, 0, 1, ExplorerConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(explorerOperationConstraintEClass, ExplorerOperationConstraint.class, "ExplorerOperationConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getExplorerOperationConstraint_Owner(), this.getExplorerClassConstraint(), this.getExplorerClassConstraint_Operations(), "owner", null, 0, 1, ExplorerOperationConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(explorerBehaviorConstraintEClass, ExplorerBehaviorConstraint.class, "ExplorerBehaviorConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getExplorerBehaviorConstraint_InvocationConstraint(), theConstraintPackage.getUserInteractionConstraint(), null, "invocationConstraint", null, 0, 1, ExplorerBehaviorConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExplorerBehaviorConstraint_Owner(), this.getExplorerClassConstraint(), this.getExplorerClassConstraint_Behaviors(), "owner", null, 0, 1, ExplorerBehaviorConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(inboxConfigurationEClass, InboxConfiguration.class, "InboxConfiguration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(outboxConfigurationEClass, OutboxConfiguration.class, "OutboxConfiguration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Initialize enums and add enum literals
		initEEnum(positionInPerspectiveEEnum, PositionInPerspective.class, "PositionInPerspective");
		addEEnumLiteral(positionInPerspectiveEEnum, PositionInPerspective.LEFT);
		addEEnumLiteral(positionInPerspectiveEEnum, PositionInPerspective.RIGHT);
		addEEnumLiteral(positionInPerspectiveEEnum, PositionInPerspective.TOP);
		addEEnumLiteral(positionInPerspectiveEEnum, PositionInPerspective.BOTTOM);
		addEEnumLiteral(positionInPerspectiveEEnum, PositionInPerspective.RIGHT_TOP);
		addEEnumLiteral(positionInPerspectiveEEnum, PositionInPerspective.RIGHT_BOTTOM);
		addEEnumLiteral(positionInPerspectiveEEnum, PositionInPerspective.LEFT_TOP);
		addEEnumLiteral(positionInPerspectiveEEnum, PositionInPerspective.LEFT_BOTTOM);
		addEEnumLiteral(positionInPerspectiveEEnum, PositionInPerspective.TOP_RIGHT);
		addEEnumLiteral(positionInPerspectiveEEnum, PositionInPerspective.TOP_LEFT);
		addEEnumLiteral(positionInPerspectiveEEnum, PositionInPerspective.BOTTOM_RIGHT);
		addEEnumLiteral(positionInPerspectiveEEnum, PositionInPerspective.BOTTOM_LEFT);
	}

} //PerspectivePackageImpl
