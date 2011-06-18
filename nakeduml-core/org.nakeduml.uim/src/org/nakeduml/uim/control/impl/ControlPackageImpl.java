/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim.control.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.nakeduml.uim.UimPackage;

import org.nakeduml.uim.action.ActionPackage;

import org.nakeduml.uim.action.impl.ActionPackageImpl;

import org.nakeduml.uim.binding.BindingPackage;

import org.nakeduml.uim.binding.impl.BindingPackageImpl;

import org.nakeduml.uim.control.ControlFactory;
import org.nakeduml.uim.control.ControlKind;
import org.nakeduml.uim.control.ControlPackage;
import org.nakeduml.uim.control.UimCheckBox;
import org.nakeduml.uim.control.UimControl;
import org.nakeduml.uim.control.UimDatePopup;
import org.nakeduml.uim.control.UimDropdown;
import org.nakeduml.uim.control.UimLookup;
import org.nakeduml.uim.control.UimMultiSelectListBox;
import org.nakeduml.uim.control.UimMultiSelectPopupSearch;
import org.nakeduml.uim.control.UimMultiSelectTreeView;
import org.nakeduml.uim.control.UimNumberScroller;
import org.nakeduml.uim.control.UimSingleSelectListBox;
import org.nakeduml.uim.control.UimSingleSelectPopupSearch;
import org.nakeduml.uim.control.UimSingleSelectTreeView;
import org.nakeduml.uim.control.UimText;
import org.nakeduml.uim.control.UimTextArea;
import org.nakeduml.uim.control.UimToggleButton;

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
	private EClass uimSingleSelectPopupSearchEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass uimMultiSelectPopupSearchEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass uimMultiSelectTreeViewEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass uimMultiSelectListBoxEClass = null;

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
	private EClass uimSingleSelectListBoxEClass = null;

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
	private EClass uimSingleSelectTreeViewEClass = null;

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
	 * @see org.nakeduml.uim.control.ControlPackage#eNS_URI
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
		LayoutPackageImpl theLayoutPackage = (LayoutPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(LayoutPackage.eNS_URI) instanceof LayoutPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(LayoutPackage.eNS_URI) : LayoutPackage.eINSTANCE);
		FolderPackageImpl theFolderPackage = (FolderPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(FolderPackage.eNS_URI) instanceof FolderPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(FolderPackage.eNS_URI) : FolderPackage.eINSTANCE);
		FormPackageImpl theFormPackage = (FormPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(FormPackage.eNS_URI) instanceof FormPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(FormPackage.eNS_URI) : FormPackage.eINSTANCE);
		BindingPackageImpl theBindingPackage = (BindingPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(BindingPackage.eNS_URI) instanceof BindingPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(BindingPackage.eNS_URI) : BindingPackage.eINSTANCE);
		SecurityPackageImpl theSecurityPackage = (SecurityPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(SecurityPackage.eNS_URI) instanceof SecurityPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(SecurityPackage.eNS_URI) : SecurityPackage.eINSTANCE);
		ActionPackageImpl theActionPackage = (ActionPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ActionPackage.eNS_URI) instanceof ActionPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ActionPackage.eNS_URI) : ActionPackage.eINSTANCE);

		// Create package meta-data objects
		theControlPackage.createPackageContents();
		theUimPackage.createPackageContents();
		theLayoutPackage.createPackageContents();
		theFolderPackage.createPackageContents();
		theFormPackage.createPackageContents();
		theBindingPackage.createPackageContents();
		theSecurityPackage.createPackageContents();
		theActionPackage.createPackageContents();

		// Initialize created meta-data
		theControlPackage.initializePackageContents();
		theUimPackage.initializePackageContents();
		theLayoutPackage.initializePackageContents();
		theFolderPackage.initializePackageContents();
		theFormPackage.initializePackageContents();
		theBindingPackage.initializePackageContents();
		theSecurityPackage.initializePackageContents();
		theActionPackage.initializePackageContents();

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
	public EClass getUimSingleSelectPopupSearch() {
		return uimSingleSelectPopupSearchEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUimMultiSelectPopupSearch() {
		return uimMultiSelectPopupSearchEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUimMultiSelectTreeView() {
		return uimMultiSelectTreeViewEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUimMultiSelectListBox() {
		return uimMultiSelectListBoxEClass;
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
	public EClass getUimSingleSelectListBox() {
		return uimSingleSelectListBoxEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUimSingleSelectListBox_Rows() {
		return (EAttribute)uimSingleSelectListBoxEClass.getEStructuralFeatures().get(0);
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
	public EAttribute getUimControl_Width() {
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
	public EClass getUimSingleSelectTreeView() {
		return uimSingleSelectTreeViewEClass;
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

		uimSingleSelectPopupSearchEClass = createEClass(UIM_SINGLE_SELECT_POPUP_SEARCH);

		uimMultiSelectPopupSearchEClass = createEClass(UIM_MULTI_SELECT_POPUP_SEARCH);

		uimMultiSelectTreeViewEClass = createEClass(UIM_MULTI_SELECT_TREE_VIEW);

		uimMultiSelectListBoxEClass = createEClass(UIM_MULTI_SELECT_LIST_BOX);

		uimDropdownEClass = createEClass(UIM_DROPDOWN);

		uimCheckBoxEClass = createEClass(UIM_CHECK_BOX);

		uimLookupEClass = createEClass(UIM_LOOKUP);
		createEReference(uimLookupEClass, UIM_LOOKUP__LOOKUP_SOURCE);

		uimTextAreaEClass = createEClass(UIM_TEXT_AREA);
		createEAttribute(uimTextAreaEClass, UIM_TEXT_AREA__ROWS);

		uimTextEClass = createEClass(UIM_TEXT);

		uimDatePopupEClass = createEClass(UIM_DATE_POPUP);

		uimSingleSelectListBoxEClass = createEClass(UIM_SINGLE_SELECT_LIST_BOX);
		createEAttribute(uimSingleSelectListBoxEClass, UIM_SINGLE_SELECT_LIST_BOX__ROWS);

		uimControlEClass = createEClass(UIM_CONTROL);
		createEAttribute(uimControlEClass, UIM_CONTROL__WIDTH);
		createEReference(uimControlEClass, UIM_CONTROL__FIELD);

		uimSingleSelectTreeViewEClass = createEClass(UIM_SINGLE_SELECT_TREE_VIEW);

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

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		uimNumberScrollerEClass.getESuperTypes().add(this.getUimControl());
		uimToggleButtonEClass.getESuperTypes().add(this.getUimControl());
		uimSingleSelectPopupSearchEClass.getESuperTypes().add(this.getUimLookup());
		uimMultiSelectPopupSearchEClass.getESuperTypes().add(this.getUimLookup());
		uimMultiSelectTreeViewEClass.getESuperTypes().add(this.getUimLookup());
		uimMultiSelectListBoxEClass.getESuperTypes().add(this.getUimLookup());
		uimDropdownEClass.getESuperTypes().add(this.getUimLookup());
		uimCheckBoxEClass.getESuperTypes().add(this.getUimControl());
		uimLookupEClass.getESuperTypes().add(this.getUimControl());
		uimTextAreaEClass.getESuperTypes().add(this.getUimControl());
		uimTextEClass.getESuperTypes().add(this.getUimControl());
		uimDatePopupEClass.getESuperTypes().add(this.getUimControl());
		uimSingleSelectListBoxEClass.getESuperTypes().add(this.getUimLookup());
		uimSingleSelectTreeViewEClass.getESuperTypes().add(this.getUimLookup());

		// Initialize classes and features; add operations and parameters
		initEClass(uimNumberScrollerEClass, UimNumberScroller.class, "UimNumberScroller", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimToggleButtonEClass, UimToggleButton.class, "UimToggleButton", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimSingleSelectPopupSearchEClass, UimSingleSelectPopupSearch.class, "UimSingleSelectPopupSearch", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimMultiSelectPopupSearchEClass, UimMultiSelectPopupSearch.class, "UimMultiSelectPopupSearch", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimMultiSelectTreeViewEClass, UimMultiSelectTreeView.class, "UimMultiSelectTreeView", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimMultiSelectListBoxEClass, UimMultiSelectListBox.class, "UimMultiSelectListBox", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimDropdownEClass, UimDropdown.class, "UimDropdown", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimCheckBoxEClass, UimCheckBox.class, "UimCheckBox", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimLookupEClass, UimLookup.class, "UimLookup", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUimLookup_LookupSource(), theBindingPackage.getLookupBinding(), theBindingPackage.getLookupBinding_Lookup(), "lookupSource", null, 0, 1, UimLookup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimTextAreaEClass, UimTextArea.class, "UimTextArea", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUimTextArea_Rows(), theEcorePackage.getEIntegerObject(), "rows", null, 0, 1, UimTextArea.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimTextEClass, UimText.class, "UimText", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimDatePopupEClass, UimDatePopup.class, "UimDatePopup", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimSingleSelectListBoxEClass, UimSingleSelectListBox.class, "UimSingleSelectListBox", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUimSingleSelectListBox_Rows(), theEcorePackage.getEIntegerObject(), "rows", null, 0, 1, UimSingleSelectListBox.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimControlEClass, UimControl.class, "UimControl", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUimControl_Width(), ecorePackage.getEString(), "width", null, 0, 1, UimControl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUimControl_Field(), theUimPackage.getUimField(), theUimPackage.getUimField_Control(), "field", null, 0, 1, UimControl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimSingleSelectTreeViewEClass, UimSingleSelectTreeView.class, "UimSingleSelectTreeView", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Initialize enums and add enum literals
		initEEnum(controlKindEEnum, ControlKind.class, "ControlKind");
		addEEnumLiteral(controlKindEEnum, ControlKind.DATE_POPUP);
		addEEnumLiteral(controlKindEEnum, ControlKind.DROPDOWN);
		addEEnumLiteral(controlKindEEnum, ControlKind.TEXT);
		addEEnumLiteral(controlKindEEnum, ControlKind.TEXT_AREA);
		addEEnumLiteral(controlKindEEnum, ControlKind.SINGLE_SELECT_LIST_BOX);
		addEEnumLiteral(controlKindEEnum, ControlKind.CHECK_BOX);
		addEEnumLiteral(controlKindEEnum, ControlKind.NUMBER_SCROLLER);
		addEEnumLiteral(controlKindEEnum, ControlKind.SINGLE_SELECT_TREE_VIEW);
		addEEnumLiteral(controlKindEEnum, ControlKind.MULTI_SELECT_TREE_VIEW);
		addEEnumLiteral(controlKindEEnum, ControlKind.MULTI_SELECT_LIST_BOX);
		addEEnumLiteral(controlKindEEnum, ControlKind.SINGLE_SELECT_POPUP_SEARCH);
		addEEnumLiteral(controlKindEEnum, ControlKind.MULTI_SELECT_POPUP_SEARCH);
		addEEnumLiteral(controlKindEEnum, ControlKind.TOGGLE_BUTTON);
	}

} //ControlPackageImpl
