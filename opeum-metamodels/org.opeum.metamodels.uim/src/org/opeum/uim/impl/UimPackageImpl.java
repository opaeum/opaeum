/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opeum.uim.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.opeum.uim.MasterComponent;
import org.opeum.uim.ObjectSelectorTree;
import org.opeum.uim.UimComponent;
import org.opeum.uim.UimContainer;
import org.opeum.uim.UimDataTable;
import org.opeum.uim.UimFactory;
import org.opeum.uim.UimField;
import org.opeum.uim.UimPackage;
import org.opeum.uim.UimPanel;
import org.opeum.uim.UimTab;
import org.opeum.uim.UimTabPanel;
import org.opeum.uim.UmlReference;
import org.opeum.uim.UserInteractionElement;
import org.opeum.uim.action.ActionPackage;
import org.opeum.uim.action.impl.ActionPackageImpl;
import org.opeum.uim.binding.BindingPackage;
import org.opeum.uim.binding.impl.BindingPackageImpl;
import org.opeum.uim.control.ControlPackage;
import org.opeum.uim.control.impl.ControlPackageImpl;
import org.opeum.uim.folder.FolderPackage;
import org.opeum.uim.folder.impl.FolderPackageImpl;
import org.opeum.uim.form.FormPackage;
import org.opeum.uim.form.impl.FormPackageImpl;
import org.opeum.uim.layout.LayoutPackage;
import org.opeum.uim.layout.impl.LayoutPackageImpl;
import org.opeum.uim.security.SecurityPackage;
import org.opeum.uim.security.impl.SecurityPackageImpl;

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
	private EClass uimTabPanelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass uimTabEClass = null;

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
	private EClass uimPanelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass objectSelectorTreeEClass = null;

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
	 * @see org.opeum.uim.UimPackage#eNS_URI
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
		LayoutPackageImpl theLayoutPackage = (LayoutPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(LayoutPackage.eNS_URI) instanceof LayoutPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(LayoutPackage.eNS_URI) : LayoutPackage.eINSTANCE);
		ControlPackageImpl theControlPackage = (ControlPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ControlPackage.eNS_URI) instanceof ControlPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ControlPackage.eNS_URI) : ControlPackage.eINSTANCE);
		FolderPackageImpl theFolderPackage = (FolderPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(FolderPackage.eNS_URI) instanceof FolderPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(FolderPackage.eNS_URI) : FolderPackage.eINSTANCE);
		FormPackageImpl theFormPackage = (FormPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(FormPackage.eNS_URI) instanceof FormPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(FormPackage.eNS_URI) : FormPackage.eINSTANCE);
		BindingPackageImpl theBindingPackage = (BindingPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(BindingPackage.eNS_URI) instanceof BindingPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(BindingPackage.eNS_URI) : BindingPackage.eINSTANCE);
		SecurityPackageImpl theSecurityPackage = (SecurityPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(SecurityPackage.eNS_URI) instanceof SecurityPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(SecurityPackage.eNS_URI) : SecurityPackage.eINSTANCE);
		ActionPackageImpl theActionPackage = (ActionPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ActionPackage.eNS_URI) instanceof ActionPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ActionPackage.eNS_URI) : ActionPackage.eINSTANCE);

		// Create package meta-data objects
		theUimPackage.createPackageContents();
		theLayoutPackage.createPackageContents();
		theControlPackage.createPackageContents();
		theFolderPackage.createPackageContents();
		theFormPackage.createPackageContents();
		theBindingPackage.createPackageContents();
		theSecurityPackage.createPackageContents();
		theActionPackage.createPackageContents();

		// Initialize created meta-data
		theUimPackage.initializePackageContents();
		theLayoutPackage.initializePackageContents();
		theControlPackage.initializePackageContents();
		theFolderPackage.initializePackageContents();
		theFormPackage.initializePackageContents();
		theBindingPackage.initializePackageContents();
		theSecurityPackage.initializePackageContents();
		theActionPackage.initializePackageContents();

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
	public EAttribute getUimField_LabelWidth() {
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
	public EClass getUimTabPanel() {
		return uimTabPanelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUimTabPanel_Children() {
		return (EReference)uimTabPanelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUimTabPanel_ActiveTabIndex() {
		return (EAttribute)uimTabPanelEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUimTab() {
		return uimTabEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUimTab_Parent() {
		return (EReference)uimTabEClass.getEStructuralFeatures().get(0);
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
	public EClass getUimPanel() {
		return uimPanelEClass;
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
		createEAttribute(uimFieldEClass, UIM_FIELD__LABEL_WIDTH);
		createEReference(uimFieldEClass, UIM_FIELD__BINDING);

		uimComponentEClass = createEClass(UIM_COMPONENT);

		userInteractionElementEClass = createEClass(USER_INTERACTION_ELEMENT);
		createEAttribute(userInteractionElementEClass, USER_INTERACTION_ELEMENT__NAME);

		uimDataTableEClass = createEClass(UIM_DATA_TABLE);
		createEReference(uimDataTableEClass, UIM_DATA_TABLE__BINDING);

		uimTabPanelEClass = createEClass(UIM_TAB_PANEL);
		createEReference(uimTabPanelEClass, UIM_TAB_PANEL__CHILDREN);
		createEAttribute(uimTabPanelEClass, UIM_TAB_PANEL__ACTIVE_TAB_INDEX);

		uimTabEClass = createEClass(UIM_TAB);
		createEReference(uimTabEClass, UIM_TAB__PARENT);

		uimContainerEClass = createEClass(UIM_CONTAINER);

		masterComponentEClass = createEClass(MASTER_COMPONENT);
		createEReference(masterComponentEClass, MASTER_COMPONENT__DETAIL_PANELS);

		umlReferenceEClass = createEClass(UML_REFERENCE);
		createEAttribute(umlReferenceEClass, UML_REFERENCE__UML_ELEMENT_UID);

		uimPanelEClass = createEClass(UIM_PANEL);

		objectSelectorTreeEClass = createEClass(OBJECT_SELECTOR_TREE);
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
		ControlPackage theControlPackage = (ControlPackage)EPackage.Registry.INSTANCE.getEPackage(ControlPackage.eNS_URI);
		FolderPackage theFolderPackage = (FolderPackage)EPackage.Registry.INSTANCE.getEPackage(FolderPackage.eNS_URI);
		FormPackage theFormPackage = (FormPackage)EPackage.Registry.INSTANCE.getEPackage(FormPackage.eNS_URI);
		BindingPackage theBindingPackage = (BindingPackage)EPackage.Registry.INSTANCE.getEPackage(BindingPackage.eNS_URI);
		SecurityPackage theSecurityPackage = (SecurityPackage)EPackage.Registry.INSTANCE.getEPackage(SecurityPackage.eNS_URI);
		ActionPackage theActionPackage = (ActionPackage)EPackage.Registry.INSTANCE.getEPackage(ActionPackage.eNS_URI);
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

		// Add subpackages
		getESubpackages().add(theLayoutPackage);
		getESubpackages().add(theControlPackage);
		getESubpackages().add(theFolderPackage);
		getESubpackages().add(theFormPackage);
		getESubpackages().add(theBindingPackage);
		getESubpackages().add(theSecurityPackage);
		getESubpackages().add(theActionPackage);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		uimFieldEClass.getESuperTypes().add(theSecurityPackage.getEditableSecureObject());
		uimFieldEClass.getESuperTypes().add(theLayoutPackage.getOutlayableComponent());
		uimComponentEClass.getESuperTypes().add(this.getUserInteractionElement());
		uimComponentEClass.getESuperTypes().add(theSecurityPackage.getSecureObject());
		uimDataTableEClass.getESuperTypes().add(this.getMasterComponent());
		uimDataTableEClass.getESuperTypes().add(theLayoutPackage.getOutlayableComponent());
		uimDataTableEClass.getESuperTypes().add(theLayoutPackage.getLayoutContainer());
		uimTabPanelEClass.getESuperTypes().add(this.getUimContainer());
		uimTabPanelEClass.getESuperTypes().add(theLayoutPackage.getOutlayableComponent());
		uimTabEClass.getESuperTypes().add(theLayoutPackage.getLayoutContainer());
		uimContainerEClass.getESuperTypes().add(this.getUimComponent());
		uimContainerEClass.getESuperTypes().add(theSecurityPackage.getEditableSecureObject());
		uimPanelEClass.getESuperTypes().add(theLayoutPackage.getLayoutContainer());
		uimPanelEClass.getESuperTypes().add(theLayoutPackage.getOutlayableComponent());
		objectSelectorTreeEClass.getESuperTypes().add(this.getMasterComponent());
		objectSelectorTreeEClass.getESuperTypes().add(theLayoutPackage.getOutlayableComponent());

		// Initialize classes and features; add operations and parameters
		initEClass(uimFieldEClass, UimField.class, "UimField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUimField_Control(), theControlPackage.getUimControl(), theControlPackage.getUimControl_Field(), "control", null, 1, 1, UimField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUimField_ControlKind(), theControlPackage.getControlKind(), "controlKind", null, 0, 1, UimField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUimField_LabelWidth(), theEcorePackage.getEIntegerObject(), "labelWidth", "200", 0, 1, UimField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUimField_Binding(), theBindingPackage.getFieldBinding(), theBindingPackage.getFieldBinding_Field(), "binding", null, 1, 1, UimField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimComponentEClass, UimComponent.class, "UimComponent", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		addEOperation(uimComponentEClass, this.getUimContainer(), "getParent", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(userInteractionElementEClass, UserInteractionElement.class, "UserInteractionElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUserInteractionElement_Name(), theEcorePackage.getEString(), "name", null, 0, 1, UserInteractionElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimDataTableEClass, UimDataTable.class, "UimDataTable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUimDataTable_Binding(), theBindingPackage.getTableBinding(), theBindingPackage.getTableBinding_Table(), "binding", null, 0, 1, UimDataTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimTabPanelEClass, UimTabPanel.class, "UimTabPanel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUimTabPanel_Children(), this.getUimTab(), this.getUimTab_Parent(), "children", null, 0, -1, UimTabPanel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUimTabPanel_ActiveTabIndex(), theEcorePackage.getEIntegerObject(), "activeTabIndex", null, 0, 1, UimTabPanel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimTabEClass, UimTab.class, "UimTab", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUimTab_Parent(), this.getUimTabPanel(), this.getUimTabPanel_Children(), "parent", null, 1, 1, UimTab.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimContainerEClass, UimContainer.class, "UimContainer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(masterComponentEClass, MasterComponent.class, "MasterComponent", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMasterComponent_DetailPanels(), theFormPackage.getDetailPanel(), theFormPackage.getDetailPanel_MasterComponent(), "detailPanels", null, 0, -1, MasterComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(umlReferenceEClass, UmlReference.class, "UmlReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUmlReference_UmlElementUid(), theEcorePackage.getEString(), "umlElementUid", null, 0, 1, UmlReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimPanelEClass, UimPanel.class, "UimPanel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(objectSelectorTreeEClass, ObjectSelectorTree.class, "ObjectSelectorTree", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //UimPackageImpl
