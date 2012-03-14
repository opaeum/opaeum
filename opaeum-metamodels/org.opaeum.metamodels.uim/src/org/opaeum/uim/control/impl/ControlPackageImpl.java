/**
 */
package org.opaeum.uim.control.impl;

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
import org.opaeum.uim.control.ControlFactory;
import org.opaeum.uim.control.ControlKind;
import org.opaeum.uim.control.ControlPackage;
import org.opaeum.uim.control.UimCheckBox;
import org.opaeum.uim.control.UimControl;
import org.opaeum.uim.control.UimDatePopup;
import org.opaeum.uim.control.UimDateScroller;
import org.opaeum.uim.control.UimDateTimePopup;
import org.opaeum.uim.control.UimDropdown;
import org.opaeum.uim.control.UimLabel;
import org.opaeum.uim.control.UimLinkControl;
import org.opaeum.uim.control.UimListBox;
import org.opaeum.uim.control.UimLookup;
import org.opaeum.uim.control.UimNumberScroller;
import org.opaeum.uim.control.UimPopupSearch;
import org.opaeum.uim.control.UimRadioButton;
import org.opaeum.uim.control.UimSelectionTable;
import org.opaeum.uim.control.UimText;
import org.opaeum.uim.control.UimTextArea;
import org.opaeum.uim.control.UimToggleButton;
import org.opaeum.uim.control.UimTreeView;
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
public class ControlPackageImpl extends EPackageImpl implements ControlPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass uimNumberScrollerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass uimToggleButtonEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass uimPopupSearchEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass uimDropdownEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass uimCheckBoxEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass uimLookupEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass uimTextAreaEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass uimTextEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass uimDatePopupEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass uimListBoxEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass uimControlEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass uimTreeViewEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass uimLinkControlEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass uimDateScrollerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass uimSelectionTableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass uimRadioButtonEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass uimLabelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass uimDateTimePopupEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum controlKindEEnum = null;

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
	 * @see org.opaeum.uim.control.ControlPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ControlPackageImpl() {
		super(eNS_URI, ControlFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link ControlPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ControlPackage init() {
		if (isInited) return (ControlPackage)EPackage.Registry.INSTANCE.getEPackage(ControlPackage.eNS_URI);

		// Obtain or create and register package
		ControlPackageImpl theControlPackage = (ControlPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ControlPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ControlPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		UimPackageImpl theUimPackage = (UimPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UimPackage.eNS_URI) instanceof UimPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UimPackage.eNS_URI) : UimPackage.eINSTANCE);
		EditorPackageImpl theEditorPackage = (EditorPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(EditorPackage.eNS_URI) instanceof EditorPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(EditorPackage.eNS_URI) : EditorPackage.eINSTANCE);
		BindingPackageImpl theBindingPackage = (BindingPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(BindingPackage.eNS_URI) instanceof BindingPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(BindingPackage.eNS_URI) : BindingPackage.eINSTANCE);
		ConstraintPackageImpl theConstraintPackage = (ConstraintPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ConstraintPackage.eNS_URI) instanceof ConstraintPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ConstraintPackage.eNS_URI) : ConstraintPackage.eINSTANCE);
		ActionPackageImpl theActionPackage = (ActionPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ActionPackage.eNS_URI) instanceof ActionPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ActionPackage.eNS_URI) : ActionPackage.eINSTANCE);
		PanelPackageImpl thePanelPackage = (PanelPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(PanelPackage.eNS_URI) instanceof PanelPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(PanelPackage.eNS_URI) : PanelPackage.eINSTANCE);
		WizardPackageImpl theWizardPackage = (WizardPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(WizardPackage.eNS_URI) instanceof WizardPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(WizardPackage.eNS_URI) : WizardPackage.eINSTANCE);
		PerspectivePackageImpl thePerspectivePackage = (PerspectivePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(PerspectivePackage.eNS_URI) instanceof PerspectivePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(PerspectivePackage.eNS_URI) : PerspectivePackage.eINSTANCE);

		// Create package meta-data objects
		theControlPackage.createPackageContents();
		theUimPackage.createPackageContents();
		theEditorPackage.createPackageContents();
		theBindingPackage.createPackageContents();
		theConstraintPackage.createPackageContents();
		theActionPackage.createPackageContents();
		thePanelPackage.createPackageContents();
		theWizardPackage.createPackageContents();
		thePerspectivePackage.createPackageContents();

		// Initialize created meta-data
		theControlPackage.initializePackageContents();
		theUimPackage.initializePackageContents();
		theEditorPackage.initializePackageContents();
		theBindingPackage.initializePackageContents();
		theConstraintPackage.initializePackageContents();
		theActionPackage.initializePackageContents();
		thePanelPackage.initializePackageContents();
		theWizardPackage.initializePackageContents();
		thePerspectivePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theControlPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ControlPackage.eNS_URI, theControlPackage);
		return theControlPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUimNumberScroller() {
		return uimNumberScrollerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUimToggleButton() {
		return uimToggleButtonEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUimPopupSearch() {
		return uimPopupSearchEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUimDropdown() {
		return uimDropdownEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUimCheckBox() {
		return uimCheckBoxEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUimLookup() {
		return uimLookupEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUimLookup_LookupSource() {
		return (EReference)uimLookupEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUimTextArea() {
		return uimTextAreaEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUimTextArea_Rows() {
		return (EAttribute)uimTextAreaEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUimText() {
		return uimTextEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUimDatePopup() {
		return uimDatePopupEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUimListBox() {
		return uimListBoxEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUimListBox_Rows() {
		return (EAttribute)uimListBoxEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUimControl() {
		return uimControlEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUimControl_MimumWidth() {
		return (EAttribute)uimControlEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUimControl_Field() {
		return (EReference)uimControlEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUimControl_MinimumHeight() {
		return (EAttribute)uimControlEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUimTreeView() {
		return uimTreeViewEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUimLinkControl() {
		return uimLinkControlEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUimLinkControl_EditorToOpen() {
		return (EReference)uimLinkControlEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUimDateScroller() {
		return uimDateScrollerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUimSelectionTable() {
		return uimSelectionTableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUimRadioButton() {
		return uimRadioButtonEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUimLabel() {
		return uimLabelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUimDateTimePopup() {
		return uimDateTimePopupEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getControlKind() {
		return controlKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ControlFactory getControlFactory() {
		return (ControlFactory)getEFactoryInstance();
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
		uimNumberScrollerEClass = createEClass(UIM_NUMBER_SCROLLER);

		uimToggleButtonEClass = createEClass(UIM_TOGGLE_BUTTON);

		uimPopupSearchEClass = createEClass(UIM_POPUP_SEARCH);

		uimDropdownEClass = createEClass(UIM_DROPDOWN);

		uimCheckBoxEClass = createEClass(UIM_CHECK_BOX);

		uimLookupEClass = createEClass(UIM_LOOKUP);
		createEReference(uimLookupEClass, UIM_LOOKUP__LOOKUP_SOURCE);

		uimTextAreaEClass = createEClass(UIM_TEXT_AREA);
		createEAttribute(uimTextAreaEClass, UIM_TEXT_AREA__ROWS);

		uimTextEClass = createEClass(UIM_TEXT);

		uimDatePopupEClass = createEClass(UIM_DATE_POPUP);

		uimListBoxEClass = createEClass(UIM_LIST_BOX);
		createEAttribute(uimListBoxEClass, UIM_LIST_BOX__ROWS);

		uimControlEClass = createEClass(UIM_CONTROL);
		createEAttribute(uimControlEClass, UIM_CONTROL__MIMUM_WIDTH);
		createEReference(uimControlEClass, UIM_CONTROL__FIELD);
		createEAttribute(uimControlEClass, UIM_CONTROL__MINIMUM_HEIGHT);

		uimTreeViewEClass = createEClass(UIM_TREE_VIEW);

		uimLinkControlEClass = createEClass(UIM_LINK_CONTROL);
		createEReference(uimLinkControlEClass, UIM_LINK_CONTROL__EDITOR_TO_OPEN);

		uimDateScrollerEClass = createEClass(UIM_DATE_SCROLLER);

		uimSelectionTableEClass = createEClass(UIM_SELECTION_TABLE);

		uimRadioButtonEClass = createEClass(UIM_RADIO_BUTTON);

		uimLabelEClass = createEClass(UIM_LABEL);

		uimDateTimePopupEClass = createEClass(UIM_DATE_TIME_POPUP);

		// Create enums
		controlKindEEnum = createEEnum(CONTROL_KIND);
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
		BindingPackage theBindingPackage = (BindingPackage)EPackage.Registry.INSTANCE.getEPackage(BindingPackage.eNS_URI);
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);
		UimPackage theUimPackage = (UimPackage)EPackage.Registry.INSTANCE.getEPackage(UimPackage.eNS_URI);
		EditorPackage theEditorPackage = (EditorPackage)EPackage.Registry.INSTANCE.getEPackage(EditorPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		uimNumberScrollerEClass.getESuperTypes().add(this.getUimControl());
		uimToggleButtonEClass.getESuperTypes().add(this.getUimControl());
		uimPopupSearchEClass.getESuperTypes().add(this.getUimLookup());
		uimDropdownEClass.getESuperTypes().add(this.getUimLookup());
		uimCheckBoxEClass.getESuperTypes().add(this.getUimControl());
		uimLookupEClass.getESuperTypes().add(this.getUimControl());
		uimTextAreaEClass.getESuperTypes().add(this.getUimControl());
		uimTextEClass.getESuperTypes().add(this.getUimControl());
		uimDatePopupEClass.getESuperTypes().add(this.getUimControl());
		uimListBoxEClass.getESuperTypes().add(this.getUimLookup());
		uimTreeViewEClass.getESuperTypes().add(this.getUimLookup());
		uimLinkControlEClass.getESuperTypes().add(this.getUimControl());
		uimDateScrollerEClass.getESuperTypes().add(this.getUimControl());
		uimSelectionTableEClass.getESuperTypes().add(this.getUimControl());
		uimRadioButtonEClass.getESuperTypes().add(this.getUimControl());
		uimLabelEClass.getESuperTypes().add(this.getUimControl());
		uimDateTimePopupEClass.getESuperTypes().add(this.getUimControl());

		// Initialize classes and features; add operations and parameters
		initEClass(uimNumberScrollerEClass, UimNumberScroller.class, "UimNumberScroller", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimToggleButtonEClass, UimToggleButton.class, "UimToggleButton", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimPopupSearchEClass, UimPopupSearch.class, "UimPopupSearch", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimDropdownEClass, UimDropdown.class, "UimDropdown", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimCheckBoxEClass, UimCheckBox.class, "UimCheckBox", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimLookupEClass, UimLookup.class, "UimLookup", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUimLookup_LookupSource(), theBindingPackage.getLookupBinding(), theBindingPackage.getLookupBinding_Lookup(), "lookupSource", null, 0, 1, UimLookup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimTextAreaEClass, UimTextArea.class, "UimTextArea", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUimTextArea_Rows(), theEcorePackage.getEIntegerObject(), "rows", null, 0, 1, UimTextArea.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimTextEClass, UimText.class, "UimText", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimDatePopupEClass, UimDatePopup.class, "UimDatePopup", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimListBoxEClass, UimListBox.class, "UimListBox", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUimListBox_Rows(), theEcorePackage.getEIntegerObject(), "rows", null, 0, 1, UimListBox.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimControlEClass, UimControl.class, "UimControl", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUimControl_MimumWidth(), ecorePackage.getEString(), "mimumWidth", null, 0, 1, UimControl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUimControl_Field(), theUimPackage.getUimField(), theUimPackage.getUimField_Control(), "field", null, 0, 1, UimControl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUimControl_MinimumHeight(), theEcorePackage.getEIntegerObject(), "minimumHeight", null, 0, 1, UimControl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimTreeViewEClass, UimTreeView.class, "UimTreeView", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimLinkControlEClass, UimLinkControl.class, "UimLinkControl", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUimLinkControl_EditorToOpen(), theEditorPackage.getClassEditor(), null, "editorToOpen", null, 0, 1, UimLinkControl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimDateScrollerEClass, UimDateScroller.class, "UimDateScroller", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimSelectionTableEClass, UimSelectionTable.class, "UimSelectionTable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimRadioButtonEClass, UimRadioButton.class, "UimRadioButton", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimLabelEClass, UimLabel.class, "UimLabel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimDateTimePopupEClass, UimDateTimePopup.class, "UimDateTimePopup", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Initialize enums and add enum literals
		initEEnum(controlKindEEnum, ControlKind.class, "ControlKind");
		addEEnumLiteral(controlKindEEnum, ControlKind.DATE_POPUP);
		addEEnumLiteral(controlKindEEnum, ControlKind.DROPDOWN);
		addEEnumLiteral(controlKindEEnum, ControlKind.TEXT);
		addEEnumLiteral(controlKindEEnum, ControlKind.TEXT_AREA);
		addEEnumLiteral(controlKindEEnum, ControlKind.LIST_BOX);
		addEEnumLiteral(controlKindEEnum, ControlKind.CHECK_BOX);
		addEEnumLiteral(controlKindEEnum, ControlKind.NUMBER_SCROLLER);
		addEEnumLiteral(controlKindEEnum, ControlKind.TREE_VIEW);
		addEEnumLiteral(controlKindEEnum, ControlKind.POPUP_SEARCH);
		addEEnumLiteral(controlKindEEnum, ControlKind.TOGGLE_BUTTON);
		addEEnumLiteral(controlKindEEnum, ControlKind.SELECTION_TABLE);
		addEEnumLiteral(controlKindEEnum, ControlKind.LINK);
		addEEnumLiteral(controlKindEEnum, ControlKind.RADIO_BUTTON);
		addEEnumLiteral(controlKindEEnum, ControlKind.LABEL);
		addEEnumLiteral(controlKindEEnum, ControlKind.DATE_SCROLLER);
		addEEnumLiteral(controlKindEEnum, ControlKind.DATE_TIME_POPUP);
	}

} //ControlPackageImpl
