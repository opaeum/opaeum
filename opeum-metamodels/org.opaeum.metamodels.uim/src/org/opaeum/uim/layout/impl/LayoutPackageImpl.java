/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opeum.uim.layout.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.opeum.uim.UimPackage;
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
import org.opeum.uim.impl.UimPackageImpl;
import org.opeum.uim.layout.LayoutContainer;
import org.opeum.uim.layout.LayoutFactory;
import org.opeum.uim.layout.LayoutPackage;
import org.opeum.uim.layout.OutlayableComponent;
import org.opeum.uim.layout.UimBorderLayout;
import org.opeum.uim.layout.UimColumnLayout;
import org.opeum.uim.layout.UimFullLayout;
import org.opeum.uim.layout.UimGridLayout;
import org.opeum.uim.layout.UimLayout;
import org.opeum.uim.layout.UimToolbarLayout;
import org.opeum.uim.layout.UimXYLayout;
import org.opeum.uim.security.SecurityPackage;
import org.opeum.uim.security.impl.SecurityPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class LayoutPackageImpl extends EPackageImpl implements LayoutPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass uimColumnLayoutEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass uimFullLayoutEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass uimXYLayoutEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass uimBorderLayoutEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass uimToolbarLayoutEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass outlayableComponentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass uimLayoutEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass layoutContainerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass uimGridLayoutEClass = null;

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
	 * @see org.opeum.uim.layout.LayoutPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private LayoutPackageImpl() {
		super(eNS_URI, LayoutFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link LayoutPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static LayoutPackage init() {
		if (isInited) return (LayoutPackage)EPackage.Registry.INSTANCE.getEPackage(LayoutPackage.eNS_URI);

		// Obtain or create and register package
		LayoutPackageImpl theLayoutPackage = (LayoutPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof LayoutPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new LayoutPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		UimPackageImpl theUimPackage = (UimPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UimPackage.eNS_URI) instanceof UimPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UimPackage.eNS_URI) : UimPackage.eINSTANCE);
		ControlPackageImpl theControlPackage = (ControlPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ControlPackage.eNS_URI) instanceof ControlPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ControlPackage.eNS_URI) : ControlPackage.eINSTANCE);
		FolderPackageImpl theFolderPackage = (FolderPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(FolderPackage.eNS_URI) instanceof FolderPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(FolderPackage.eNS_URI) : FolderPackage.eINSTANCE);
		FormPackageImpl theFormPackage = (FormPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(FormPackage.eNS_URI) instanceof FormPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(FormPackage.eNS_URI) : FormPackage.eINSTANCE);
		BindingPackageImpl theBindingPackage = (BindingPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(BindingPackage.eNS_URI) instanceof BindingPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(BindingPackage.eNS_URI) : BindingPackage.eINSTANCE);
		SecurityPackageImpl theSecurityPackage = (SecurityPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(SecurityPackage.eNS_URI) instanceof SecurityPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(SecurityPackage.eNS_URI) : SecurityPackage.eINSTANCE);
		ActionPackageImpl theActionPackage = (ActionPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ActionPackage.eNS_URI) instanceof ActionPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ActionPackage.eNS_URI) : ActionPackage.eINSTANCE);

		// Create package meta-data objects
		theLayoutPackage.createPackageContents();
		theUimPackage.createPackageContents();
		theControlPackage.createPackageContents();
		theFolderPackage.createPackageContents();
		theFormPackage.createPackageContents();
		theBindingPackage.createPackageContents();
		theSecurityPackage.createPackageContents();
		theActionPackage.createPackageContents();

		// Initialize created meta-data
		theLayoutPackage.initializePackageContents();
		theUimPackage.initializePackageContents();
		theControlPackage.initializePackageContents();
		theFolderPackage.initializePackageContents();
		theFormPackage.initializePackageContents();
		theBindingPackage.initializePackageContents();
		theSecurityPackage.initializePackageContents();
		theActionPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theLayoutPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(LayoutPackage.eNS_URI, theLayoutPackage);
		return theLayoutPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUimColumnLayout() {
		return uimColumnLayoutEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUimFullLayout() {
		return uimFullLayoutEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUimXYLayout() {
		return uimXYLayoutEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUimBorderLayout() {
		return uimBorderLayoutEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUimBorderLayout_Horizontal() {
		return (EAttribute)uimBorderLayoutEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUimToolbarLayout() {
		return uimToolbarLayoutEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOutlayableComponent() {
		return outlayableComponentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOutlayableComponent_Parent() {
		return (EReference)outlayableComponentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUimLayout() {
		return uimLayoutEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUimLayout_Parent() {
		return (EReference)uimLayoutEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUimLayout_Children() {
		return (EReference)uimLayoutEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLayoutContainer() {
		return layoutContainerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLayoutContainer_Layout() {
		return (EReference)layoutContainerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUimGridLayout() {
		return uimGridLayoutEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUimGridLayout_NumberOfColumns() {
		return (EAttribute)uimGridLayoutEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LayoutFactory getLayoutFactory() {
		return (LayoutFactory)getEFactoryInstance();
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
		uimColumnLayoutEClass = createEClass(UIM_COLUMN_LAYOUT);

		uimFullLayoutEClass = createEClass(UIM_FULL_LAYOUT);

		uimXYLayoutEClass = createEClass(UIM_XY_LAYOUT);

		uimBorderLayoutEClass = createEClass(UIM_BORDER_LAYOUT);
		createEAttribute(uimBorderLayoutEClass, UIM_BORDER_LAYOUT__HORIZONTAL);

		uimToolbarLayoutEClass = createEClass(UIM_TOOLBAR_LAYOUT);

		outlayableComponentEClass = createEClass(OUTLAYABLE_COMPONENT);
		createEReference(outlayableComponentEClass, OUTLAYABLE_COMPONENT__PARENT);

		uimLayoutEClass = createEClass(UIM_LAYOUT);
		createEReference(uimLayoutEClass, UIM_LAYOUT__PARENT);
		createEReference(uimLayoutEClass, UIM_LAYOUT__CHILDREN);

		layoutContainerEClass = createEClass(LAYOUT_CONTAINER);
		createEReference(layoutContainerEClass, LAYOUT_CONTAINER__LAYOUT);

		uimGridLayoutEClass = createEClass(UIM_GRID_LAYOUT);
		createEAttribute(uimGridLayoutEClass, UIM_GRID_LAYOUT__NUMBER_OF_COLUMNS);
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
		uimColumnLayoutEClass.getESuperTypes().add(this.getUimLayout());
		uimFullLayoutEClass.getESuperTypes().add(this.getUimLayout());
		uimXYLayoutEClass.getESuperTypes().add(this.getUimLayout());
		uimBorderLayoutEClass.getESuperTypes().add(this.getUimLayout());
		uimToolbarLayoutEClass.getESuperTypes().add(this.getUimLayout());
		outlayableComponentEClass.getESuperTypes().add(theUimPackage.getUimComponent());
		uimLayoutEClass.getESuperTypes().add(theUimPackage.getUimContainer());
		layoutContainerEClass.getESuperTypes().add(theUimPackage.getUimContainer());
		uimGridLayoutEClass.getESuperTypes().add(this.getUimLayout());

		// Initialize classes and features; add operations and parameters
		initEClass(uimColumnLayoutEClass, UimColumnLayout.class, "UimColumnLayout", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimFullLayoutEClass, UimFullLayout.class, "UimFullLayout", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimXYLayoutEClass, UimXYLayout.class, "UimXYLayout", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimBorderLayoutEClass, UimBorderLayout.class, "UimBorderLayout", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUimBorderLayout_Horizontal(), theEcorePackage.getEBoolean(), "horizontal", null, 0, 1, UimBorderLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimToolbarLayoutEClass, UimToolbarLayout.class, "UimToolbarLayout", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(outlayableComponentEClass, OutlayableComponent.class, "OutlayableComponent", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOutlayableComponent_Parent(), this.getUimLayout(), this.getUimLayout_Children(), "parent", null, 0, 1, OutlayableComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimLayoutEClass, UimLayout.class, "UimLayout", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUimLayout_Parent(), this.getLayoutContainer(), this.getLayoutContainer_Layout(), "parent", null, 0, 1, UimLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUimLayout_Children(), this.getOutlayableComponent(), this.getOutlayableComponent_Parent(), "children", null, 0, -1, UimLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(layoutContainerEClass, LayoutContainer.class, "LayoutContainer", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getLayoutContainer_Layout(), this.getUimLayout(), this.getUimLayout_Parent(), "layout", null, 0, 1, LayoutContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimGridLayoutEClass, UimGridLayout.class, "UimGridLayout", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUimGridLayout_NumberOfColumns(), theEcorePackage.getEIntegerObject(), "numberOfColumns", null, 0, 1, UimGridLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
	}

} //LayoutPackageImpl
