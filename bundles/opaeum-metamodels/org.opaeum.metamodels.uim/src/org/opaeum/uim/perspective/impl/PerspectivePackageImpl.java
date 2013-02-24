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
import org.opaeum.uim.perspective.BehaviorNavigationConstraint;
import org.opaeum.uim.perspective.ClassNavigationConstraint;
import org.opaeum.uim.perspective.EditorConfiguration;
import org.opaeum.uim.perspective.InboxConfiguration;
import org.opaeum.uim.perspective.MultiplicityElementNavigationConstraint;
import org.opaeum.uim.perspective.NavigationConstraint;
import org.opaeum.uim.perspective.NavigatorConfiguration;
import org.opaeum.uim.perspective.OperationNavigationConstraint;
import org.opaeum.uim.perspective.OutboxConfiguration;
import org.opaeum.uim.perspective.ParameterNavigationConstraint;
import org.opaeum.uim.perspective.PerspectiveConfiguration;
import org.opaeum.uim.perspective.PerspectiveFactory;
import org.opaeum.uim.perspective.PerspectivePackage;
import org.opaeum.uim.perspective.PositionInPerspective;
import org.opaeum.uim.perspective.PropertiesConfiguration;
import org.opaeum.uim.perspective.PropertyNavigationConstraint;
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
	private EClass navigatorConfigurationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass classNavigationConstraintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass propertyNavigationConstraintEClass = null;

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
	private EClass navigationConstraintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass operationNavigationConstraintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass behaviorNavigationConstraintEClass = null;

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
	private EClass parameterNavigationConstraintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass multiplicityElementNavigationConstraintEClass = null;

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
	public EClass getNavigatorConfiguration() {
		return navigatorConfigurationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNavigatorConfiguration_Classes() {
		return (EReference)navigatorConfigurationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getClassNavigationConstraint() {
		return classNavigationConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassNavigationConstraint_ExplorerConfiguration() {
		return (EReference)classNavigationConstraintEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassNavigationConstraint_Properties() {
		return (EReference)classNavigationConstraintEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassNavigationConstraint_Behaviors() {
		return (EReference)classNavigationConstraintEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassNavigationConstraint_Operations() {
		return (EReference)classNavigationConstraintEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPropertyNavigationConstraint() {
		return propertyNavigationConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPropertyNavigationConstraint_Owner() {
		return (EReference)propertyNavigationConstraintEClass.getEStructuralFeatures().get(0);
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
	public EClass getNavigationConstraint() {
		return navigationConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNavigationConstraint_Hidden() {
		return (EAttribute)navigationConstraintEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOperationNavigationConstraint() {
		return operationNavigationConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOperationNavigationConstraint_Owner() {
		return (EReference)operationNavigationConstraintEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOperationNavigationConstraint_InvocationConstraint() {
		return (EReference)operationNavigationConstraintEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOperationNavigationConstraint_Parameters() {
		return (EReference)operationNavigationConstraintEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBehaviorNavigationConstraint() {
		return behaviorNavigationConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBehaviorNavigationConstraint_InvocationConstraint() {
		return (EReference)behaviorNavigationConstraintEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBehaviorNavigationConstraint_Owner() {
		return (EReference)behaviorNavigationConstraintEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBehaviorNavigationConstraint_Parameters() {
		return (EReference)behaviorNavigationConstraintEClass.getEStructuralFeatures().get(2);
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
	public EClass getParameterNavigationConstraint() {
		return parameterNavigationConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMultiplicityElementNavigationConstraint() {
		return multiplicityElementNavigationConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMultiplicityElementNavigationConstraint_RemoveConstraint() {
		return (EReference)multiplicityElementNavigationConstraintEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMultiplicityElementNavigationConstraint_AddConstraint() {
		return (EReference)multiplicityElementNavigationConstraintEClass.getEStructuralFeatures().get(1);
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

		navigatorConfigurationEClass = createEClass(NAVIGATOR_CONFIGURATION);
		createEReference(navigatorConfigurationEClass, NAVIGATOR_CONFIGURATION__CLASSES);

		classNavigationConstraintEClass = createEClass(CLASS_NAVIGATION_CONSTRAINT);
		createEReference(classNavigationConstraintEClass, CLASS_NAVIGATION_CONSTRAINT__EXPLORER_CONFIGURATION);
		createEReference(classNavigationConstraintEClass, CLASS_NAVIGATION_CONSTRAINT__PROPERTIES);
		createEReference(classNavigationConstraintEClass, CLASS_NAVIGATION_CONSTRAINT__BEHAVIORS);
		createEReference(classNavigationConstraintEClass, CLASS_NAVIGATION_CONSTRAINT__OPERATIONS);

		propertyNavigationConstraintEClass = createEClass(PROPERTY_NAVIGATION_CONSTRAINT);
		createEReference(propertyNavigationConstraintEClass, PROPERTY_NAVIGATION_CONSTRAINT__OWNER);

		editorConfigurationEClass = createEClass(EDITOR_CONFIGURATION);

		propertiesConfigurationEClass = createEClass(PROPERTIES_CONFIGURATION);

		navigationConstraintEClass = createEClass(NAVIGATION_CONSTRAINT);
		createEAttribute(navigationConstraintEClass, NAVIGATION_CONSTRAINT__HIDDEN);

		operationNavigationConstraintEClass = createEClass(OPERATION_NAVIGATION_CONSTRAINT);
		createEReference(operationNavigationConstraintEClass, OPERATION_NAVIGATION_CONSTRAINT__OWNER);
		createEReference(operationNavigationConstraintEClass, OPERATION_NAVIGATION_CONSTRAINT__INVOCATION_CONSTRAINT);
		createEReference(operationNavigationConstraintEClass, OPERATION_NAVIGATION_CONSTRAINT__PARAMETERS);

		behaviorNavigationConstraintEClass = createEClass(BEHAVIOR_NAVIGATION_CONSTRAINT);
		createEReference(behaviorNavigationConstraintEClass, BEHAVIOR_NAVIGATION_CONSTRAINT__INVOCATION_CONSTRAINT);
		createEReference(behaviorNavigationConstraintEClass, BEHAVIOR_NAVIGATION_CONSTRAINT__OWNER);
		createEReference(behaviorNavigationConstraintEClass, BEHAVIOR_NAVIGATION_CONSTRAINT__PARAMETERS);

		inboxConfigurationEClass = createEClass(INBOX_CONFIGURATION);

		outboxConfigurationEClass = createEClass(OUTBOX_CONFIGURATION);

		parameterNavigationConstraintEClass = createEClass(PARAMETER_NAVIGATION_CONSTRAINT);

		multiplicityElementNavigationConstraintEClass = createEClass(MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT);
		createEReference(multiplicityElementNavigationConstraintEClass, MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT__REMOVE_CONSTRAINT);
		createEReference(multiplicityElementNavigationConstraintEClass, MULTIPLICITY_ELEMENT_NAVIGATION_CONSTRAINT__ADD_CONSTRAINT);

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
		navigatorConfigurationEClass.getESuperTypes().add(this.getViewAllocation());
		classNavigationConstraintEClass.getESuperTypes().add(this.getNavigationConstraint());
		propertyNavigationConstraintEClass.getESuperTypes().add(this.getNavigationConstraint());
		propertyNavigationConstraintEClass.getESuperTypes().add(this.getMultiplicityElementNavigationConstraint());
		editorConfigurationEClass.getESuperTypes().add(this.getViewAllocation());
		propertiesConfigurationEClass.getESuperTypes().add(this.getViewAllocation());
		navigationConstraintEClass.getESuperTypes().add(theConstraintPackage.getUserInteractionConstraint());
		navigationConstraintEClass.getESuperTypes().add(theUimPackage.getLabeledElement());
		operationNavigationConstraintEClass.getESuperTypes().add(this.getNavigationConstraint());
		behaviorNavigationConstraintEClass.getESuperTypes().add(this.getNavigationConstraint());
		inboxConfigurationEClass.getESuperTypes().add(this.getViewAllocation());
		outboxConfigurationEClass.getESuperTypes().add(this.getViewAllocation());
		parameterNavigationConstraintEClass.getESuperTypes().add(this.getMultiplicityElementNavigationConstraint());
		multiplicityElementNavigationConstraintEClass.getESuperTypes().add(this.getNavigationConstraint());

		// Initialize classes and features; add operations and parameters
		initEClass(perspectiveConfigurationEClass, PerspectiveConfiguration.class, "PerspectiveConfiguration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPerspectiveConfiguration_Explorer(), this.getNavigatorConfiguration(), null, "explorer", null, 0, 1, PerspectiveConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPerspectiveConfiguration_Editor(), this.getEditorConfiguration(), null, "editor", null, 0, 1, PerspectiveConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPerspectiveConfiguration_Properties(), this.getPropertiesConfiguration(), null, "properties", null, 0, 1, PerspectiveConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPerspectiveConfiguration_Inbox(), this.getInboxConfiguration(), null, "inbox", null, 0, 1, PerspectiveConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPerspectiveConfiguration_Outbox(), this.getOutboxConfiguration(), null, "outbox", null, 0, 1, PerspectiveConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(viewAllocationEClass, ViewAllocation.class, "ViewAllocation", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getViewAllocation_Width(), ecorePackage.getEIntegerObject(), "width", null, 0, 1, ViewAllocation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getViewAllocation_Height(), theEcorePackage.getEIntegerObject(), "height", null, 0, 1, ViewAllocation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getViewAllocation_Position(), this.getPositionInPerspective(), "position", null, 0, 1, ViewAllocation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(navigatorConfigurationEClass, NavigatorConfiguration.class, "NavigatorConfiguration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getNavigatorConfiguration_Classes(), this.getClassNavigationConstraint(), this.getClassNavigationConstraint_ExplorerConfiguration(), "classes", null, 0, -1, NavigatorConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(classNavigationConstraintEClass, ClassNavigationConstraint.class, "ClassNavigationConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getClassNavigationConstraint_ExplorerConfiguration(), this.getNavigatorConfiguration(), this.getNavigatorConfiguration_Classes(), "explorerConfiguration", null, 1, 1, ClassNavigationConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getClassNavigationConstraint_Properties(), this.getPropertyNavigationConstraint(), this.getPropertyNavigationConstraint_Owner(), "properties", null, 0, -1, ClassNavigationConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getClassNavigationConstraint_Behaviors(), this.getBehaviorNavigationConstraint(), this.getBehaviorNavigationConstraint_Owner(), "behaviors", null, 0, -1, ClassNavigationConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getClassNavigationConstraint_Operations(), this.getOperationNavigationConstraint(), this.getOperationNavigationConstraint_Owner(), "operations", null, 0, -1, ClassNavigationConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(propertyNavigationConstraintEClass, PropertyNavigationConstraint.class, "PropertyNavigationConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPropertyNavigationConstraint_Owner(), this.getClassNavigationConstraint(), this.getClassNavigationConstraint_Properties(), "owner", null, 0, 1, PropertyNavigationConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(editorConfigurationEClass, EditorConfiguration.class, "EditorConfiguration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(propertiesConfigurationEClass, PropertiesConfiguration.class, "PropertiesConfiguration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(navigationConstraintEClass, NavigationConstraint.class, "NavigationConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNavigationConstraint_Hidden(), theEcorePackage.getEBoolean(), "hidden", null, 0, 1, NavigationConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(operationNavigationConstraintEClass, OperationNavigationConstraint.class, "OperationNavigationConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOperationNavigationConstraint_Owner(), this.getClassNavigationConstraint(), this.getClassNavigationConstraint_Operations(), "owner", null, 0, 1, OperationNavigationConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOperationNavigationConstraint_InvocationConstraint(), this.getNavigationConstraint(), null, "invocationConstraint", null, 0, 1, OperationNavigationConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOperationNavigationConstraint_Parameters(), this.getParameterNavigationConstraint(), null, "parameters", null, 0, -1, OperationNavigationConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(behaviorNavigationConstraintEClass, BehaviorNavigationConstraint.class, "BehaviorNavigationConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBehaviorNavigationConstraint_InvocationConstraint(), this.getNavigationConstraint(), null, "invocationConstraint", null, 0, 1, BehaviorNavigationConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBehaviorNavigationConstraint_Owner(), this.getClassNavigationConstraint(), this.getClassNavigationConstraint_Behaviors(), "owner", null, 0, 1, BehaviorNavigationConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBehaviorNavigationConstraint_Parameters(), this.getParameterNavigationConstraint(), null, "parameters", null, 0, -1, BehaviorNavigationConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(inboxConfigurationEClass, InboxConfiguration.class, "InboxConfiguration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(outboxConfigurationEClass, OutboxConfiguration.class, "OutboxConfiguration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(parameterNavigationConstraintEClass, ParameterNavigationConstraint.class, "ParameterNavigationConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(multiplicityElementNavigationConstraintEClass, MultiplicityElementNavigationConstraint.class, "MultiplicityElementNavigationConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMultiplicityElementNavigationConstraint_RemoveConstraint(), this.getNavigationConstraint(), null, "removeConstraint", null, 0, 1, MultiplicityElementNavigationConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMultiplicityElementNavigationConstraint_AddConstraint(), this.getNavigationConstraint(), null, "addConstraint", null, 0, 1, MultiplicityElementNavigationConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
