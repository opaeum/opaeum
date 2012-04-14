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
import org.opaeum.uim.constraint.ConstraintPackage;
import org.opaeum.uim.constraint.impl.ConstraintPackageImpl;
import org.opaeum.uim.control.ControlPackage;
import org.opaeum.uim.control.impl.ControlPackageImpl;
import org.opaeum.uim.cube.CubePackage;
import org.opaeum.uim.cube.impl.CubePackageImpl;
import org.opaeum.uim.editor.EditorPackage;
import org.opaeum.uim.editor.impl.EditorPackageImpl;
import org.opaeum.uim.impl.UimPackageImpl;
import org.opaeum.uim.panel.PanelPackage;
import org.opaeum.uim.panel.impl.PanelPackageImpl;
import org.opaeum.uim.perspective.EditorConfiguration;
import org.opaeum.uim.perspective.ExplorerClassConfiguration;
import org.opaeum.uim.perspective.ExplorerConfiguration;
import org.opaeum.uim.perspective.ExplorerPropertyConfiguration;
import org.opaeum.uim.perspective.HiddenClass;
import org.opaeum.uim.perspective.HiddenCompositeProperty;
import org.opaeum.uim.perspective.PerspectiveFactory;
import org.opaeum.uim.perspective.PerspectivePackage;
import org.opaeum.uim.perspective.PositionInPerspective;
import org.opaeum.uim.perspective.PropertiesConfiguration;
import org.opaeum.uim.perspective.UimPerspective;
import org.opaeum.uim.perspective.ViewAllocation;
import org.opaeum.uim.perspective.ViewKind;
import org.opaeum.uim.perspective.VisibleNonCompositeProperty;
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
	private EClass uimPerspectiveEClass = null;

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
	private EClass explorerClassConfigurationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass explorerPropertyConfigurationEClass = null;

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
	public EClass getUimPerspective() {
		return uimPerspectiveEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUimPerspective_ExplorerConfiguration() {
		return (EReference)uimPerspectiveEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUimPerspective_EditorConfiguration() {
		return (EReference)uimPerspectiveEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUimPerspective_PropertiesConfiguration() {
		return (EReference)uimPerspectiveEClass.getEStructuralFeatures().get(2);
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
	public EReference getExplorerConfiguration_ConfiguredClasses() {
		return (EReference)explorerConfigurationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExplorerClassConfiguration() {
		return explorerClassConfigurationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExplorerClassConfiguration_ExplorerConfiguration() {
		return (EReference)explorerClassConfigurationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExplorerClassConfiguration_IsVisible() {
		return (EAttribute)explorerClassConfigurationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExplorerClassConfiguration_ConfiguredProperties() {
		return (EReference)explorerClassConfigurationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExplorerPropertyConfiguration() {
		return explorerPropertyConfigurationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExplorerPropertyConfiguration_IsVisible() {
		return (EAttribute)explorerPropertyConfigurationEClass.getEStructuralFeatures().get(0);
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
		uimPerspectiveEClass = createEClass(UIM_PERSPECTIVE);
		createEReference(uimPerspectiveEClass, UIM_PERSPECTIVE__EXPLORER_CONFIGURATION);
		createEReference(uimPerspectiveEClass, UIM_PERSPECTIVE__EDITOR_CONFIGURATION);
		createEReference(uimPerspectiveEClass, UIM_PERSPECTIVE__PROPERTIES_CONFIGURATION);

		viewAllocationEClass = createEClass(VIEW_ALLOCATION);
		createEAttribute(viewAllocationEClass, VIEW_ALLOCATION__WIDTH);
		createEAttribute(viewAllocationEClass, VIEW_ALLOCATION__HEIGHT);
		createEAttribute(viewAllocationEClass, VIEW_ALLOCATION__POSITION);

		explorerConfigurationEClass = createEClass(EXPLORER_CONFIGURATION);
		createEReference(explorerConfigurationEClass, EXPLORER_CONFIGURATION__CONFIGURED_CLASSES);

		explorerClassConfigurationEClass = createEClass(EXPLORER_CLASS_CONFIGURATION);
		createEReference(explorerClassConfigurationEClass, EXPLORER_CLASS_CONFIGURATION__EXPLORER_CONFIGURATION);
		createEAttribute(explorerClassConfigurationEClass, EXPLORER_CLASS_CONFIGURATION__IS_VISIBLE);
		createEReference(explorerClassConfigurationEClass, EXPLORER_CLASS_CONFIGURATION__CONFIGURED_PROPERTIES);

		explorerPropertyConfigurationEClass = createEClass(EXPLORER_PROPERTY_CONFIGURATION);
		createEAttribute(explorerPropertyConfigurationEClass, EXPLORER_PROPERTY_CONFIGURATION__IS_VISIBLE);

		editorConfigurationEClass = createEClass(EDITOR_CONFIGURATION);

		propertiesConfigurationEClass = createEClass(PROPERTIES_CONFIGURATION);

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
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);
		UimPackage theUimPackage = (UimPackage)EPackage.Registry.INSTANCE.getEPackage(UimPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		explorerConfigurationEClass.getESuperTypes().add(this.getViewAllocation());
		explorerClassConfigurationEClass.getESuperTypes().add(theUimPackage.getUmlReference());
		explorerPropertyConfigurationEClass.getESuperTypes().add(theUimPackage.getUmlReference());
		editorConfigurationEClass.getESuperTypes().add(this.getViewAllocation());
		propertiesConfigurationEClass.getESuperTypes().add(this.getViewAllocation());

		// Initialize classes and features; add operations and parameters
		initEClass(uimPerspectiveEClass, UimPerspective.class, "UimPerspective", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUimPerspective_ExplorerConfiguration(), this.getExplorerConfiguration(), null, "explorerConfiguration", null, 0, 1, UimPerspective.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUimPerspective_EditorConfiguration(), this.getEditorConfiguration(), null, "editorConfiguration", null, 0, 1, UimPerspective.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUimPerspective_PropertiesConfiguration(), this.getPropertiesConfiguration(), null, "propertiesConfiguration", null, 0, 1, UimPerspective.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(viewAllocationEClass, ViewAllocation.class, "ViewAllocation", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getViewAllocation_Width(), ecorePackage.getEIntegerObject(), "width", null, 0, 1, ViewAllocation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getViewAllocation_Height(), theEcorePackage.getEIntegerObject(), "height", null, 0, 1, ViewAllocation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getViewAllocation_Position(), this.getPositionInPerspective(), "position", null, 0, 1, ViewAllocation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(explorerConfigurationEClass, ExplorerConfiguration.class, "ExplorerConfiguration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getExplorerConfiguration_ConfiguredClasses(), this.getExplorerClassConfiguration(), this.getExplorerClassConfiguration_ExplorerConfiguration(), "configuredClasses", null, 0, -1, ExplorerConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(explorerClassConfigurationEClass, ExplorerClassConfiguration.class, "ExplorerClassConfiguration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getExplorerClassConfiguration_ExplorerConfiguration(), this.getExplorerConfiguration(), this.getExplorerConfiguration_ConfiguredClasses(), "explorerConfiguration", null, 1, 1, ExplorerClassConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getExplorerClassConfiguration_IsVisible(), theEcorePackage.getEBooleanObject(), "isVisible", null, 0, 1, ExplorerClassConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getExplorerClassConfiguration_ConfiguredProperties(), this.getExplorerPropertyConfiguration(), null, "configuredProperties", null, 0, -1, ExplorerClassConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(explorerPropertyConfigurationEClass, ExplorerPropertyConfiguration.class, "ExplorerPropertyConfiguration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getExplorerPropertyConfiguration_IsVisible(), theEcorePackage.getEBooleanObject(), "isVisible", null, 0, 1, ExplorerPropertyConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(editorConfigurationEClass, EditorConfiguration.class, "EditorConfiguration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(propertiesConfigurationEClass, PropertiesConfiguration.class, "PropertiesConfiguration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

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
