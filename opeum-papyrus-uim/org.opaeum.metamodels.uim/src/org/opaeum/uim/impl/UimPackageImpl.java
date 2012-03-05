/**
 */
package org.opaeum.uim.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.opaeum.uim.DetailComponent;
import org.opaeum.uim.DetailPanel;
import org.opaeum.uim.MasterComponent;
import org.opaeum.uim.ObjectSelectorTree;
import org.opaeum.uim.Orientation;
import org.opaeum.uim.Page;
import org.opaeum.uim.PanelClass;
import org.opaeum.uim.UimComponent;
import org.opaeum.uim.UimContainer;
import org.opaeum.uim.UimDataTable;
import org.opaeum.uim.UimFactory;
import org.opaeum.uim.UimField;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.UserInterface;
import org.opaeum.uim.UserInterfaceEntryPoint;
import org.opaeum.uim.action.ActionPackage;
import org.opaeum.uim.action.impl.ActionPackageImpl;
import org.opaeum.uim.binding.BindingPackage;
import org.opaeum.uim.binding.impl.BindingPackageImpl;
import org.opaeum.uim.constraint.ConstraintPackage;
import org.opaeum.uim.constraint.impl.ConstraintPackageImpl;
import org.opaeum.uim.control.ControlPackage;
import org.opaeum.uim.control.impl.ControlPackageImpl;
import org.opaeum.uim.editor.EditorPackage;
import org.opaeum.uim.editor.impl.EditorPackageImpl;
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
	private EClass uimFieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass uimComponentEClass = null;

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
	private EClass uimDataTableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass uimContainerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass masterComponentEClass = null;

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
	private EClass objectSelectorTreeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass detailComponentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass userInterfaceEntryPointEClass = null;

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
	private EClass userInterfaceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass panelClassEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum orientationEEnum = null;

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
	public EClass getUimField() {
		return uimFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUimField_Control() {
		return (EReference)uimFieldEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUimField_ControlKind() {
		return (EAttribute)uimFieldEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUimField_MinimumLabelWidth() {
		return (EAttribute)uimFieldEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUimField_Binding() {
		return (EReference)uimFieldEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUimField_Orientation() {
		return (EAttribute)uimFieldEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUimComponent() {
		return uimComponentEClass;
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
	public EClass getUimDataTable() {
		return uimDataTableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUimDataTable_Binding() {
		return (EReference)uimDataTableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUimContainer() {
		return uimContainerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUimContainer_Children() {
		return (EReference)uimContainerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMasterComponent() {
		return masterComponentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMasterComponent_DetailPanels() {
		return (EReference)masterComponentEClass.getEStructuralFeatures().get(0);
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
	public EClass getObjectSelectorTree() {
		return objectSelectorTreeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDetailComponent() {
		return detailComponentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDetailComponent_MasterComponent() {
		return (EReference)detailComponentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDetailComponent_PanelClasses() {
		return (EReference)detailComponentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDetailComponent_Panel() {
		return (EReference)detailComponentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUserInterfaceEntryPoint() {
		return userInterfaceEntryPointEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUserInterfaceEntryPoint_Editability() {
		return (EReference)userInterfaceEntryPointEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUserInterfaceEntryPoint_Visibility() {
		return (EReference)userInterfaceEntryPointEClass.getEStructuralFeatures().get(1);
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
	public EClass getUserInterface() {
		return userInterfaceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUserInterface_Panel() {
		return (EReference)userInterfaceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPanelClass() {
		return panelClassEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPanelClass_DetailComponent() {
		return (EReference)panelClassEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPanelClass_Panel() {
		return (EReference)panelClassEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getOrientation() {
		return orientationEEnum;
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
		uimFieldEClass = createEClass(UIM_FIELD);
		createEReference(uimFieldEClass, UIM_FIELD__CONTROL);
		createEAttribute(uimFieldEClass, UIM_FIELD__CONTROL_KIND);
		createEAttribute(uimFieldEClass, UIM_FIELD__MINIMUM_LABEL_WIDTH);
		createEReference(uimFieldEClass, UIM_FIELD__BINDING);
		createEAttribute(uimFieldEClass, UIM_FIELD__ORIENTATION);

		uimComponentEClass = createEClass(UIM_COMPONENT);

		userInteractionElementEClass = createEClass(USER_INTERACTION_ELEMENT);
		createEAttribute(userInteractionElementEClass, USER_INTERACTION_ELEMENT__NAME);

		uimDataTableEClass = createEClass(UIM_DATA_TABLE);
		createEReference(uimDataTableEClass, UIM_DATA_TABLE__BINDING);

		uimContainerEClass = createEClass(UIM_CONTAINER);
		createEReference(uimContainerEClass, UIM_CONTAINER__CHILDREN);

		masterComponentEClass = createEClass(MASTER_COMPONENT);
		createEReference(masterComponentEClass, MASTER_COMPONENT__DETAIL_PANELS);

		umlReferenceEClass = createEClass(UML_REFERENCE);
		createEAttribute(umlReferenceEClass, UML_REFERENCE__UML_ELEMENT_UID);

		objectSelectorTreeEClass = createEClass(OBJECT_SELECTOR_TREE);

		detailComponentEClass = createEClass(DETAIL_COMPONENT);
		createEReference(detailComponentEClass, DETAIL_COMPONENT__MASTER_COMPONENT);
		createEReference(detailComponentEClass, DETAIL_COMPONENT__PANEL_CLASSES);
		createEReference(detailComponentEClass, DETAIL_COMPONENT__PANEL);

		userInterfaceEntryPointEClass = createEClass(USER_INTERFACE_ENTRY_POINT);
		createEReference(userInterfaceEntryPointEClass, USER_INTERFACE_ENTRY_POINT__EDITABILITY);
		createEReference(userInterfaceEntryPointEClass, USER_INTERFACE_ENTRY_POINT__VISIBILITY);

		pageEClass = createEClass(PAGE);

		userInterfaceEClass = createEClass(USER_INTERFACE);
		createEReference(userInterfaceEClass, USER_INTERFACE__PANEL);

		panelClassEClass = createEClass(PANEL_CLASS);
		createEReference(panelClassEClass, PANEL_CLASS__DETAIL_COMPONENT);
		createEReference(panelClassEClass, PANEL_CLASS__PANEL);

		// Create enums
		orientationEEnum = createEEnum(ORIENTATION);
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

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		uimFieldEClass.getESuperTypes().add(theConstraintPackage.getEditableConstrainedObject());
		uimFieldEClass.getESuperTypes().add(this.getUimComponent());
		uimComponentEClass.getESuperTypes().add(this.getUserInteractionElement());
		uimComponentEClass.getESuperTypes().add(theConstraintPackage.getConstrainedObject());
		uimDataTableEClass.getESuperTypes().add(this.getMasterComponent());
		uimDataTableEClass.getESuperTypes().add(this.getUimContainer());
		uimContainerEClass.getESuperTypes().add(this.getUimComponent());
		uimContainerEClass.getESuperTypes().add(theConstraintPackage.getEditableConstrainedObject());
		objectSelectorTreeEClass.getESuperTypes().add(this.getMasterComponent());
		detailComponentEClass.getESuperTypes().add(this.getUimComponent());
		userInterfaceEntryPointEClass.getESuperTypes().add(this.getUserInteractionElement());
		pageEClass.getESuperTypes().add(this.getUserInterface());
		pageEClass.getESuperTypes().add(theConstraintPackage.getEditableConstrainedObject());
		userInterfaceEClass.getESuperTypes().add(this.getUserInteractionElement());
		panelClassEClass.getESuperTypes().add(this.getUmlReference());

		// Initialize classes and features; add operations and parameters
		initEClass(uimFieldEClass, UimField.class, "UimField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUimField_Control(), theControlPackage.getUimControl(), theControlPackage.getUimControl_Field(), "control", null, 1, 1, UimField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUimField_ControlKind(), theControlPackage.getControlKind(), "controlKind", null, 0, 1, UimField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUimField_MinimumLabelWidth(), theEcorePackage.getEIntegerObject(), "minimumLabelWidth", "200", 0, 1, UimField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUimField_Binding(), theBindingPackage.getFieldBinding(), theBindingPackage.getFieldBinding_Field(), "binding", null, 1, 1, UimField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUimField_Orientation(), this.getOrientation(), "orientation", null, 0, 1, UimField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimComponentEClass, UimComponent.class, "UimComponent", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		addEOperation(uimComponentEClass, this.getUimContainer(), "getParent", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(userInteractionElementEClass, UserInteractionElement.class, "UserInteractionElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUserInteractionElement_Name(), theEcorePackage.getEString(), "name", null, 0, 1, UserInteractionElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimDataTableEClass, UimDataTable.class, "UimDataTable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUimDataTable_Binding(), theBindingPackage.getTableBinding(), theBindingPackage.getTableBinding_Table(), "binding", null, 0, 1, UimDataTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimContainerEClass, UimContainer.class, "UimContainer", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUimContainer_Children(), this.getUimComponent(), null, "children", null, 0, -1, UimContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(masterComponentEClass, MasterComponent.class, "MasterComponent", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMasterComponent_DetailPanels(), this.getDetailComponent(), this.getDetailComponent_MasterComponent(), "detailPanels", null, 0, -1, MasterComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(umlReferenceEClass, UmlReference.class, "UmlReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUmlReference_UmlElementUid(), theEcorePackage.getEString(), "umlElementUid", null, 0, 1, UmlReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(objectSelectorTreeEClass, ObjectSelectorTree.class, "ObjectSelectorTree", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(detailComponentEClass, DetailComponent.class, "DetailComponent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDetailComponent_MasterComponent(), this.getMasterComponent(), this.getMasterComponent_DetailPanels(), "masterComponent", null, 1, 1, DetailComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDetailComponent_PanelClasses(), this.getPanelClass(), this.getPanelClass_DetailComponent(), "panelClasses", null, 0, -1, DetailComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDetailComponent_Panel(), thePanelPackage.getAbstractPanel(), null, "panel", null, 1, 1, DetailComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(userInterfaceEntryPointEClass, UserInterfaceEntryPoint.class, "UserInterfaceEntryPoint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUserInterfaceEntryPoint_Editability(), theConstraintPackage.getRootUserInteractionConstraint(), null, "editability", null, 0, 1, UserInterfaceEntryPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUserInterfaceEntryPoint_Visibility(), theConstraintPackage.getRootUserInteractionConstraint(), null, "visibility", null, 0, 1, UserInterfaceEntryPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(pageEClass, Page.class, "Page", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(userInterfaceEClass, UserInterface.class, "UserInterface", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUserInterface_Panel(), thePanelPackage.getAbstractPanel(), null, "panel", null, 1, 1, UserInterface.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(panelClassEClass, PanelClass.class, "PanelClass", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPanelClass_DetailComponent(), this.getDetailComponent(), this.getDetailComponent_PanelClasses(), "detailComponent", null, 1, 1, PanelClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPanelClass_Panel(), thePanelPackage.getAbstractPanel(), null, "panel", null, 1, 1, PanelClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(orientationEEnum, Orientation.class, "Orientation");
		addEEnumLiteral(orientationEEnum, Orientation.HORIZONTAL);
		addEEnumLiteral(orientationEEnum, Orientation.VERTICAL);

		// Create resource
		createResource(eNS_URI);
	}

} //UimPackageImpl
