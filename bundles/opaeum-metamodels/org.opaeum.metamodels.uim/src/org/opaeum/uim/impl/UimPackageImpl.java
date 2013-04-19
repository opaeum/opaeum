/**
 */
package org.opaeum.uim.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.opaeum.uim.IgnoredElement;
import org.opaeum.uim.LabelContainer;
import org.opaeum.uim.LabeledElement;
import org.opaeum.uim.Labels;
import org.opaeum.uim.Page;
import org.opaeum.uim.PageOrdering;
import org.opaeum.uim.UimFactory;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.UserInterfaceRoot;
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
import org.opaeum.uim.model.ModelPackage;
import org.opaeum.uim.model.impl.ModelPackageImpl;
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
public class UimPackageImpl extends EPackageImpl implements UimPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass userInteractionElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass umlReferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass userInterfaceRootEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass labelsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass labeledElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ignoredElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pageOrderingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass labelContainerEClass = null;

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
	 * @see org.opaeum.uim.UimPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private UimPackageImpl() {
		super(eNS_URI, UimFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link UimPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static UimPackage init() {
		if (isInited) return (UimPackage)EPackage.Registry.INSTANCE.getEPackage(UimPackage.eNS_URI);

		// Obtain or create and register package
		UimPackageImpl theUimPackage = (UimPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof UimPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new UimPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		ControlPackageImpl theControlPackage = (ControlPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ControlPackage.eNS_URI) instanceof ControlPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ControlPackage.eNS_URI) : ControlPackage.eINSTANCE);
		EditorPackageImpl theEditorPackage = (EditorPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(EditorPackage.eNS_URI) instanceof EditorPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(EditorPackage.eNS_URI) : EditorPackage.eINSTANCE);
		BindingPackageImpl theBindingPackage = (BindingPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(BindingPackage.eNS_URI) instanceof BindingPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(BindingPackage.eNS_URI) : BindingPackage.eINSTANCE);
		ConstraintPackageImpl theConstraintPackage = (ConstraintPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ConstraintPackage.eNS_URI) instanceof ConstraintPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ConstraintPackage.eNS_URI) : ConstraintPackage.eINSTANCE);
		ActionPackageImpl theActionPackage = (ActionPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ActionPackage.eNS_URI) instanceof ActionPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ActionPackage.eNS_URI) : ActionPackage.eINSTANCE);
		PanelPackageImpl thePanelPackage = (PanelPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(PanelPackage.eNS_URI) instanceof PanelPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(PanelPackage.eNS_URI) : PanelPackage.eINSTANCE);
		WizardPackageImpl theWizardPackage = (WizardPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(WizardPackage.eNS_URI) instanceof WizardPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(WizardPackage.eNS_URI) : WizardPackage.eINSTANCE);
		PerspectivePackageImpl thePerspectivePackage = (PerspectivePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(PerspectivePackage.eNS_URI) instanceof PerspectivePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(PerspectivePackage.eNS_URI) : PerspectivePackage.eINSTANCE);
		CubePackageImpl theCubePackage = (CubePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CubePackage.eNS_URI) instanceof CubePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CubePackage.eNS_URI) : CubePackage.eINSTANCE);
		ModelPackageImpl theModelPackage = (ModelPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI) instanceof ModelPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI) : ModelPackage.eINSTANCE);
		ComponentPackageImpl theComponentPackage = (ComponentPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ComponentPackage.eNS_URI) instanceof ComponentPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ComponentPackage.eNS_URI) : ComponentPackage.eINSTANCE);

		// Create package meta-data objects
		theUimPackage.createPackageContents();
		theControlPackage.createPackageContents();
		theEditorPackage.createPackageContents();
		theBindingPackage.createPackageContents();
		theConstraintPackage.createPackageContents();
		theActionPackage.createPackageContents();
		thePanelPackage.createPackageContents();
		theWizardPackage.createPackageContents();
		thePerspectivePackage.createPackageContents();
		theCubePackage.createPackageContents();
		theModelPackage.createPackageContents();
		theComponentPackage.createPackageContents();

		// Initialize created meta-data
		theUimPackage.initializePackageContents();
		theControlPackage.initializePackageContents();
		theEditorPackage.initializePackageContents();
		theBindingPackage.initializePackageContents();
		theConstraintPackage.initializePackageContents();
		theActionPackage.initializePackageContents();
		thePanelPackage.initializePackageContents();
		theWizardPackage.initializePackageContents();
		thePerspectivePackage.initializePackageContents();
		theCubePackage.initializePackageContents();
		theModelPackage.initializePackageContents();
		theComponentPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theUimPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(UimPackage.eNS_URI, theUimPackage);
		return theUimPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUserInteractionElement() {
		return userInteractionElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUserInteractionElement_Name() {
		return (EAttribute)userInteractionElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUserInteractionElement_UnderUserControl() {
		return (EAttribute)userInteractionElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUmlReference() {
		return umlReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUmlReference_UmlElementUid() {
		return (EAttribute)umlReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUserInterfaceRoot() {
		return userInterfaceRootEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUserInterfaceRoot_Editability() {
		return (EReference)userInterfaceRootEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUserInterfaceRoot_Visibility() {
		return (EReference)userInterfaceRootEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUserInterfaceRoot_IgnoredElements() {
		return (EReference)userInterfaceRootEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUserInterfaceRoot_SuperUserInterfaces() {
		return (EReference)userInterfaceRootEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUserInterfaceRoot_PageOrdering() {
		return (EReference)userInterfaceRootEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPage() {
		return pageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPage_Panel() {
		return (EReference)pageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLabels() {
		return labelsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLabeledElement() {
		return labeledElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIgnoredElement() {
		return ignoredElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIgnoredElement_UserInterfaceRoot() {
		return (EReference)ignoredElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPageOrdering() {
		return pageOrderingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPageOrdering_Page() {
		return (EReference)pageOrderingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPageOrdering_Position() {
		return (EAttribute)pageOrderingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLabelContainer() {
		return labelContainerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLabelContainer_LabelOverride() {
		return (EReference)labelContainerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimFactory getUimFactory() {
		return (UimFactory)getEFactoryInstance();
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
		userInteractionElementEClass = createEClass(USER_INTERACTION_ELEMENT);
		createEAttribute(userInteractionElementEClass, USER_INTERACTION_ELEMENT__NAME);
		createEAttribute(userInteractionElementEClass, USER_INTERACTION_ELEMENT__UNDER_USER_CONTROL);

		umlReferenceEClass = createEClass(UML_REFERENCE);
		createEAttribute(umlReferenceEClass, UML_REFERENCE__UML_ELEMENT_UID);

		userInterfaceRootEClass = createEClass(USER_INTERFACE_ROOT);
		createEReference(userInterfaceRootEClass, USER_INTERFACE_ROOT__EDITABILITY);
		createEReference(userInterfaceRootEClass, USER_INTERFACE_ROOT__VISIBILITY);
		createEReference(userInterfaceRootEClass, USER_INTERFACE_ROOT__IGNORED_ELEMENTS);
		createEReference(userInterfaceRootEClass, USER_INTERFACE_ROOT__SUPER_USER_INTERFACES);
		createEReference(userInterfaceRootEClass, USER_INTERFACE_ROOT__PAGE_ORDERING);

		pageEClass = createEClass(PAGE);
		createEReference(pageEClass, PAGE__PANEL);

		labelsEClass = createEClass(LABELS);

		labeledElementEClass = createEClass(LABELED_ELEMENT);

		ignoredElementEClass = createEClass(IGNORED_ELEMENT);
		createEReference(ignoredElementEClass, IGNORED_ELEMENT__USER_INTERFACE_ROOT);

		pageOrderingEClass = createEClass(PAGE_ORDERING);
		createEReference(pageOrderingEClass, PAGE_ORDERING__PAGE);
		createEAttribute(pageOrderingEClass, PAGE_ORDERING__POSITION);

		labelContainerEClass = createEClass(LABEL_CONTAINER);
		createEReference(labelContainerEClass, LABEL_CONTAINER__LABEL_OVERRIDE);
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
		ControlPackage theControlPackage = (ControlPackage)EPackage.Registry.INSTANCE.getEPackage(ControlPackage.eNS_URI);
		EditorPackage theEditorPackage = (EditorPackage)EPackage.Registry.INSTANCE.getEPackage(EditorPackage.eNS_URI);
		BindingPackage theBindingPackage = (BindingPackage)EPackage.Registry.INSTANCE.getEPackage(BindingPackage.eNS_URI);
		ConstraintPackage theConstraintPackage = (ConstraintPackage)EPackage.Registry.INSTANCE.getEPackage(ConstraintPackage.eNS_URI);
		ActionPackage theActionPackage = (ActionPackage)EPackage.Registry.INSTANCE.getEPackage(ActionPackage.eNS_URI);
		PanelPackage thePanelPackage = (PanelPackage)EPackage.Registry.INSTANCE.getEPackage(PanelPackage.eNS_URI);
		WizardPackage theWizardPackage = (WizardPackage)EPackage.Registry.INSTANCE.getEPackage(WizardPackage.eNS_URI);
		PerspectivePackage thePerspectivePackage = (PerspectivePackage)EPackage.Registry.INSTANCE.getEPackage(PerspectivePackage.eNS_URI);
		CubePackage theCubePackage = (CubePackage)EPackage.Registry.INSTANCE.getEPackage(CubePackage.eNS_URI);
		ModelPackage theModelPackage = (ModelPackage)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI);
		ComponentPackage theComponentPackage = (ComponentPackage)EPackage.Registry.INSTANCE.getEPackage(ComponentPackage.eNS_URI);
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

		// Add subpackages
		getESubpackages().add(theControlPackage);
		getESubpackages().add(theEditorPackage);
		getESubpackages().add(theBindingPackage);
		getESubpackages().add(theConstraintPackage);
		getESubpackages().add(theActionPackage);
		getESubpackages().add(thePanelPackage);
		getESubpackages().add(theWizardPackage);
		getESubpackages().add(thePerspectivePackage);
		getESubpackages().add(theCubePackage);
		getESubpackages().add(theModelPackage);
		getESubpackages().add(theComponentPackage);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		userInterfaceRootEClass.getESuperTypes().add(this.getUserInteractionElement());
		userInterfaceRootEClass.getESuperTypes().add(this.getLabeledElement());
		pageEClass.getESuperTypes().add(theConstraintPackage.getEditableConstrainedObject());
		pageEClass.getESuperTypes().add(this.getLabeledElement());
		labelsEClass.getESuperTypes().add(theEcorePackage.getEAnnotation());
		labeledElementEClass.getESuperTypes().add(this.getUserInteractionElement());
		labeledElementEClass.getESuperTypes().add(this.getUmlReference());
		labeledElementEClass.getESuperTypes().add(this.getLabelContainer());
		ignoredElementEClass.getESuperTypes().add(this.getUmlReference());
		pageOrderingEClass.getESuperTypes().add(this.getLabeledElement());

		// Initialize classes and features; add operations and parameters
		initEClass(userInteractionElementEClass, UserInteractionElement.class, "UserInteractionElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUserInteractionElement_Name(), theEcorePackage.getEString(), "name", "", 0, 1, UserInteractionElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUserInteractionElement_UnderUserControl(), theEcorePackage.getEBoolean(), "underUserControl", "false", 0, 1, UserInteractionElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(umlReferenceEClass, UmlReference.class, "UmlReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUmlReference_UmlElementUid(), theEcorePackage.getEString(), "umlElementUid", null, 0, 1, UmlReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(userInterfaceRootEClass, UserInterfaceRoot.class, "UserInterfaceRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUserInterfaceRoot_Editability(), theConstraintPackage.getRootUserInteractionConstraint(), null, "editability", null, 0, 1, UserInterfaceRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUserInterfaceRoot_Visibility(), theConstraintPackage.getRootUserInteractionConstraint(), null, "visibility", null, 0, 1, UserInterfaceRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUserInterfaceRoot_IgnoredElements(), this.getIgnoredElement(), this.getIgnoredElement_UserInterfaceRoot(), "ignoredElements", null, 0, -1, UserInterfaceRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUserInterfaceRoot_SuperUserInterfaces(), this.getUserInterfaceRoot(), null, "superUserInterfaces", null, 0, -1, UserInterfaceRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUserInterfaceRoot_PageOrdering(), this.getPageOrdering(), null, "pageOrdering", null, 0, -1, UserInterfaceRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(userInterfaceRootEClass, this.getPage(), "getPages", 0, -1, IS_UNIQUE, IS_ORDERED);

		initEClass(pageEClass, Page.class, "Page", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPage_Panel(), thePanelPackage.getAbstractPanel(), null, "panel", null, 1, 1, Page.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(labelsEClass, Labels.class, "Labels", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(labeledElementEClass, LabeledElement.class, "LabeledElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(ignoredElementEClass, IgnoredElement.class, "IgnoredElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIgnoredElement_UserInterfaceRoot(), this.getUserInterfaceRoot(), this.getUserInterfaceRoot_IgnoredElements(), "userInterfaceRoot", null, 0, 1, IgnoredElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(pageOrderingEClass, PageOrdering.class, "PageOrdering", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPageOrdering_Page(), this.getPage(), null, "page", null, 0, 1, PageOrdering.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPageOrdering_Position(), theEcorePackage.getEInt(), "position", null, 0, 1, PageOrdering.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(labelContainerEClass, LabelContainer.class, "LabelContainer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getLabelContainer_LabelOverride(), this.getLabels(), null, "labelOverride", null, 0, 1, LabelContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //UimPackageImpl
