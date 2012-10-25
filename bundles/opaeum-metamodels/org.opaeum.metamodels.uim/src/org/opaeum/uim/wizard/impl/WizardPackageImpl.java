/**
 */
package org.opaeum.uim.wizard.impl;

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
import org.opaeum.uim.perspective.PerspectivePackage;
import org.opaeum.uim.perspective.impl.PerspectivePackageImpl;
import org.opaeum.uim.wizard.AbstractWizard;
import org.opaeum.uim.wizard.BehaviorInvocationWizard;
import org.opaeum.uim.wizard.InvocationWizard;
import org.opaeum.uim.wizard.NewObjectWizard;
import org.opaeum.uim.wizard.OperationResultPage;
import org.opaeum.uim.wizard.ResponsibilityInvocationWizard;
import org.opaeum.uim.wizard.WizardFactory;
import org.opaeum.uim.wizard.WizardPackage;
import org.opaeum.uim.wizard.WizardPage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class WizardPackageImpl extends EPackageImpl implements WizardPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractWizardEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass newObjectWizardEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass invocationWizardEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass wizardPageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass responsibilityInvocationWizardEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass behaviorInvocationWizardEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass operationResultPageEClass = null;

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
	 * @see org.opaeum.uim.wizard.WizardPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private WizardPackageImpl() {
		super(eNS_URI, WizardFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link WizardPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static WizardPackage init() {
		if (isInited) return (WizardPackage)EPackage.Registry.INSTANCE.getEPackage(WizardPackage.eNS_URI);

		// Obtain or create and register package
		WizardPackageImpl theWizardPackage = (WizardPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof WizardPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new WizardPackageImpl());

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
		PerspectivePackageImpl thePerspectivePackage = (PerspectivePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(PerspectivePackage.eNS_URI) instanceof PerspectivePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(PerspectivePackage.eNS_URI) : PerspectivePackage.eINSTANCE);
		CubePackageImpl theCubePackage = (CubePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CubePackage.eNS_URI) instanceof CubePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CubePackage.eNS_URI) : CubePackage.eINSTANCE);
		ModelPackageImpl theModelPackage = (ModelPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI) instanceof ModelPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI) : ModelPackage.eINSTANCE);
		ComponentPackageImpl theComponentPackage = (ComponentPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ComponentPackage.eNS_URI) instanceof ComponentPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ComponentPackage.eNS_URI) : ComponentPackage.eINSTANCE);

		// Create package meta-data objects
		theWizardPackage.createPackageContents();
		theUimPackage.createPackageContents();
		theControlPackage.createPackageContents();
		theEditorPackage.createPackageContents();
		theBindingPackage.createPackageContents();
		theConstraintPackage.createPackageContents();
		theActionPackage.createPackageContents();
		thePanelPackage.createPackageContents();
		thePerspectivePackage.createPackageContents();
		theCubePackage.createPackageContents();
		theModelPackage.createPackageContents();
		theComponentPackage.createPackageContents();

		// Initialize created meta-data
		theWizardPackage.initializePackageContents();
		theUimPackage.initializePackageContents();
		theControlPackage.initializePackageContents();
		theEditorPackage.initializePackageContents();
		theBindingPackage.initializePackageContents();
		theConstraintPackage.initializePackageContents();
		theActionPackage.initializePackageContents();
		thePanelPackage.initializePackageContents();
		thePerspectivePackage.initializePackageContents();
		theCubePackage.initializePackageContents();
		theModelPackage.initializePackageContents();
		theComponentPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theWizardPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(WizardPackage.eNS_URI, theWizardPackage);
		return theWizardPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractWizard() {
		return abstractWizardEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractWizard_Pages() {
		return (EReference)abstractWizardEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNewObjectWizard() {
		return newObjectWizardEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNewObjectWizard_Model() {
		return (EReference)newObjectWizardEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInvocationWizard() {
		return invocationWizardEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWizardPage() {
		return wizardPageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWizardPage_Wizard() {
		return (EReference)wizardPageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getResponsibilityInvocationWizard() {
		return responsibilityInvocationWizardEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getResponsibilityInvocationWizard_Model() {
		return (EReference)responsibilityInvocationWizardEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBehaviorInvocationWizard() {
		return behaviorInvocationWizardEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBehaviorInvocationWizard_Model() {
		return (EReference)behaviorInvocationWizardEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOperationResultPage() {
		return operationResultPageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOperationResultPage_Wizard() {
		return (EReference)operationResultPageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WizardFactory getWizardFactory() {
		return (WizardFactory)getEFactoryInstance();
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
		abstractWizardEClass = createEClass(ABSTRACT_WIZARD);
		createEReference(abstractWizardEClass, ABSTRACT_WIZARD__PAGES);

		newObjectWizardEClass = createEClass(NEW_OBJECT_WIZARD);
		createEReference(newObjectWizardEClass, NEW_OBJECT_WIZARD__MODEL);

		invocationWizardEClass = createEClass(INVOCATION_WIZARD);

		wizardPageEClass = createEClass(WIZARD_PAGE);
		createEReference(wizardPageEClass, WIZARD_PAGE__WIZARD);

		responsibilityInvocationWizardEClass = createEClass(RESPONSIBILITY_INVOCATION_WIZARD);
		createEReference(responsibilityInvocationWizardEClass, RESPONSIBILITY_INVOCATION_WIZARD__MODEL);

		behaviorInvocationWizardEClass = createEClass(BEHAVIOR_INVOCATION_WIZARD);
		createEReference(behaviorInvocationWizardEClass, BEHAVIOR_INVOCATION_WIZARD__MODEL);

		operationResultPageEClass = createEClass(OPERATION_RESULT_PAGE);
		createEReference(operationResultPageEClass, OPERATION_RESULT_PAGE__WIZARD);
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
		ModelPackage theModelPackage = (ModelPackage)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		abstractWizardEClass.getESuperTypes().add(theUimPackage.getUserInterfaceRoot());
		newObjectWizardEClass.getESuperTypes().add(this.getAbstractWizard());
		invocationWizardEClass.getESuperTypes().add(this.getAbstractWizard());
		wizardPageEClass.getESuperTypes().add(theUimPackage.getPage());
		responsibilityInvocationWizardEClass.getESuperTypes().add(this.getInvocationWizard());
		behaviorInvocationWizardEClass.getESuperTypes().add(this.getInvocationWizard());
		operationResultPageEClass.getESuperTypes().add(theUimPackage.getPage());

		// Initialize classes and features; add operations and parameters
		initEClass(abstractWizardEClass, AbstractWizard.class, "AbstractWizard", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAbstractWizard_Pages(), this.getWizardPage(), this.getWizardPage_Wizard(), "pages", null, 0, -1, AbstractWizard.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(newObjectWizardEClass, NewObjectWizard.class, "NewObjectWizard", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getNewObjectWizard_Model(), theModelPackage.getClassUserInteractionModel(), theModelPackage.getClassUserInteractionModel_NewObjectWizard(), "model", null, 0, 1, NewObjectWizard.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(invocationWizardEClass, InvocationWizard.class, "InvocationWizard", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(wizardPageEClass, WizardPage.class, "WizardPage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getWizardPage_Wizard(), this.getAbstractWizard(), this.getAbstractWizard_Pages(), "wizard", null, 0, 1, WizardPage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(responsibilityInvocationWizardEClass, ResponsibilityInvocationWizard.class, "ResponsibilityInvocationWizard", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getResponsibilityInvocationWizard_Model(), theModelPackage.getResponsibilityUserInteractionModel(), theModelPackage.getResponsibilityUserInteractionModel_InvocationWizard(), "model", null, 0, 1, ResponsibilityInvocationWizard.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(behaviorInvocationWizardEClass, BehaviorInvocationWizard.class, "BehaviorInvocationWizard", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBehaviorInvocationWizard_Model(), theModelPackage.getBehaviorUserInteractionModel(), theModelPackage.getBehaviorUserInteractionModel_InvocationWizard(), "model", null, 0, 1, BehaviorInvocationWizard.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(operationResultPageEClass, OperationResultPage.class, "OperationResultPage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOperationResultPage_Wizard(), theModelPackage.getOperationInvocationWizard(), theModelPackage.getOperationInvocationWizard_ResultPage(), "wizard", null, 0, 1, OperationResultPage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
	}

} //WizardPackageImpl
