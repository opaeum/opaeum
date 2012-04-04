/**
 */
package org.opaeum.uim.editor.impl;

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
import org.opaeum.uim.constraint.ConstraintPackage;
import org.opaeum.uim.constraint.impl.ConstraintPackageImpl;
import org.opaeum.uim.control.ControlPackage;
import org.opaeum.uim.control.impl.ControlPackageImpl;
import org.opaeum.uim.editor.AbstractEditor;
import org.opaeum.uim.editor.ActionTaskEditor;
import org.opaeum.uim.editor.ClassEditor;
import org.opaeum.uim.editor.EditorActionBar;
import org.opaeum.uim.editor.EditorFactory;
import org.opaeum.uim.editor.EditorPackage;
import org.opaeum.uim.editor.EditorPage;
import org.opaeum.uim.editor.MenuConfiguration;
import org.opaeum.uim.editor.QueryInvocationEditor;
import org.opaeum.uim.editor.ResponsibilityTaskEditor;
import org.opaeum.uim.editor.VisibleOperation;
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
public class EditorPackageImpl extends EPackageImpl implements EditorPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractEditorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass actionTaskEditorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass classEditorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass responsibilityTaskEditorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass queryInvocationEditorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass editorPageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass editorActionBarEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass menuConfigurationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass visibleOperationEClass = null;

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
	 * @see org.opaeum.uim.editor.EditorPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private EditorPackageImpl() {
		super(eNS_URI, EditorFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link EditorPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static EditorPackage init() {
		if (isInited) return (EditorPackage)EPackage.Registry.INSTANCE.getEPackage(EditorPackage.eNS_URI);

		// Obtain or create and register package
		EditorPackageImpl theEditorPackage = (EditorPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof EditorPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new EditorPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		UimPackageImpl theUimPackage = (UimPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UimPackage.eNS_URI) instanceof UimPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UimPackage.eNS_URI) : UimPackage.eINSTANCE);
		ControlPackageImpl theControlPackage = (ControlPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ControlPackage.eNS_URI) instanceof ControlPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ControlPackage.eNS_URI) : ControlPackage.eINSTANCE);
		BindingPackageImpl theBindingPackage = (BindingPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(BindingPackage.eNS_URI) instanceof BindingPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(BindingPackage.eNS_URI) : BindingPackage.eINSTANCE);
		ConstraintPackageImpl theConstraintPackage = (ConstraintPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ConstraintPackage.eNS_URI) instanceof ConstraintPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ConstraintPackage.eNS_URI) : ConstraintPackage.eINSTANCE);
		ActionPackageImpl theActionPackage = (ActionPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ActionPackage.eNS_URI) instanceof ActionPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ActionPackage.eNS_URI) : ActionPackage.eINSTANCE);
		PanelPackageImpl thePanelPackage = (PanelPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(PanelPackage.eNS_URI) instanceof PanelPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(PanelPackage.eNS_URI) : PanelPackage.eINSTANCE);
		WizardPackageImpl theWizardPackage = (WizardPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(WizardPackage.eNS_URI) instanceof WizardPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(WizardPackage.eNS_URI) : WizardPackage.eINSTANCE);
		PerspectivePackageImpl thePerspectivePackage = (PerspectivePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(PerspectivePackage.eNS_URI) instanceof PerspectivePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(PerspectivePackage.eNS_URI) : PerspectivePackage.eINSTANCE);

		// Create package meta-data objects
		theEditorPackage.createPackageContents();
		theUimPackage.createPackageContents();
		theControlPackage.createPackageContents();
		theBindingPackage.createPackageContents();
		theConstraintPackage.createPackageContents();
		theActionPackage.createPackageContents();
		thePanelPackage.createPackageContents();
		theWizardPackage.createPackageContents();
		thePerspectivePackage.createPackageContents();

		// Initialize created meta-data
		theEditorPackage.initializePackageContents();
		theUimPackage.initializePackageContents();
		theControlPackage.initializePackageContents();
		theBindingPackage.initializePackageContents();
		theConstraintPackage.initializePackageContents();
		theActionPackage.initializePackageContents();
		thePanelPackage.initializePackageContents();
		theWizardPackage.initializePackageContents();
		thePerspectivePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theEditorPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(EditorPackage.eNS_URI, theEditorPackage);
		return theEditorPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractEditor() {
		return abstractEditorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractEditor_ActionBar() {
		return (EReference)abstractEditorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractEditor_MenuConfiguration() {
		return (EReference)abstractEditorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractEditor_Pages() {
		return (EReference)abstractEditorEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getActionTaskEditor() {
		return actionTaskEditorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getClassEditor() {
		return classEditorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getResponsibilityTaskEditor() {
		return responsibilityTaskEditorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getQueryInvocationEditor() {
		return queryInvocationEditorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEditorPage() {
		return editorPageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEditorPage_Editor() {
		return (EReference)editorPageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEditorActionBar() {
		return editorActionBarEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMenuConfiguration() {
		return menuConfigurationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMenuConfiguration_Editor() {
		return (EReference)menuConfigurationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMenuConfiguration_VisibleOperations() {
		return (EReference)menuConfigurationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVisibleOperation() {
		return visibleOperationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVisibleOperation_MenuConfiguration() {
		return (EReference)visibleOperationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EditorFactory getEditorFactory() {
		return (EditorFactory)getEFactoryInstance();
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
		abstractEditorEClass = createEClass(ABSTRACT_EDITOR);
		createEReference(abstractEditorEClass, ABSTRACT_EDITOR__ACTION_BAR);
		createEReference(abstractEditorEClass, ABSTRACT_EDITOR__MENU_CONFIGURATION);
		createEReference(abstractEditorEClass, ABSTRACT_EDITOR__PAGES);

		actionTaskEditorEClass = createEClass(ACTION_TASK_EDITOR);

		classEditorEClass = createEClass(CLASS_EDITOR);

		responsibilityTaskEditorEClass = createEClass(RESPONSIBILITY_TASK_EDITOR);

		queryInvocationEditorEClass = createEClass(QUERY_INVOCATION_EDITOR);

		editorPageEClass = createEClass(EDITOR_PAGE);
		createEReference(editorPageEClass, EDITOR_PAGE__EDITOR);

		editorActionBarEClass = createEClass(EDITOR_ACTION_BAR);

		menuConfigurationEClass = createEClass(MENU_CONFIGURATION);
		createEReference(menuConfigurationEClass, MENU_CONFIGURATION__EDITOR);
		createEReference(menuConfigurationEClass, MENU_CONFIGURATION__VISIBLE_OPERATIONS);

		visibleOperationEClass = createEClass(VISIBLE_OPERATION);
		createEReference(visibleOperationEClass, VISIBLE_OPERATION__MENU_CONFIGURATION);
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

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		abstractEditorEClass.getESuperTypes().add(theUimPackage.getUmlReference());
		abstractEditorEClass.getESuperTypes().add(theUimPackage.getUserInterfaceEntryPoint());
		actionTaskEditorEClass.getESuperTypes().add(this.getAbstractEditor());
		actionTaskEditorEClass.getESuperTypes().add(theUimPackage.getUimRootElement());
		classEditorEClass.getESuperTypes().add(this.getAbstractEditor());
		responsibilityTaskEditorEClass.getESuperTypes().add(this.getAbstractEditor());
		queryInvocationEditorEClass.getESuperTypes().add(this.getAbstractEditor());
		queryInvocationEditorEClass.getESuperTypes().add(theUimPackage.getUimRootElement());
		editorPageEClass.getESuperTypes().add(theUimPackage.getPage());
		editorActionBarEClass.getESuperTypes().add(theUimPackage.getAbstractActionBar());
		visibleOperationEClass.getESuperTypes().add(theUimPackage.getUmlReference());

		// Initialize classes and features; add operations and parameters
		initEClass(abstractEditorEClass, AbstractEditor.class, "AbstractEditor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAbstractEditor_ActionBar(), this.getEditorActionBar(), null, "actionBar", null, 0, 1, AbstractEditor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAbstractEditor_MenuConfiguration(), this.getMenuConfiguration(), this.getMenuConfiguration_Editor(), "menuConfiguration", null, 0, 1, AbstractEditor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAbstractEditor_Pages(), this.getEditorPage(), this.getEditorPage_Editor(), "pages", null, 0, -1, AbstractEditor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(actionTaskEditorEClass, ActionTaskEditor.class, "ActionTaskEditor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(classEditorEClass, ClassEditor.class, "ClassEditor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(responsibilityTaskEditorEClass, ResponsibilityTaskEditor.class, "ResponsibilityTaskEditor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(queryInvocationEditorEClass, QueryInvocationEditor.class, "QueryInvocationEditor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(editorPageEClass, EditorPage.class, "EditorPage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEditorPage_Editor(), this.getAbstractEditor(), this.getAbstractEditor_Pages(), "editor", null, 1, 1, EditorPage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(editorActionBarEClass, EditorActionBar.class, "EditorActionBar", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(menuConfigurationEClass, MenuConfiguration.class, "MenuConfiguration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMenuConfiguration_Editor(), this.getAbstractEditor(), this.getAbstractEditor_MenuConfiguration(), "editor", null, 1, 1, MenuConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMenuConfiguration_VisibleOperations(), this.getVisibleOperation(), this.getVisibleOperation_MenuConfiguration(), "visibleOperations", null, 0, -1, MenuConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(visibleOperationEClass, VisibleOperation.class, "VisibleOperation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getVisibleOperation_MenuConfiguration(), this.getMenuConfiguration(), this.getMenuConfiguration_VisibleOperations(), "menuConfiguration", null, 1, 1, VisibleOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create annotations
		// http:///org/eclipse/emf/ecore/util/ExtendedMetaData
		createExtendedMetaDataAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createExtendedMetaDataAnnotations() {
		String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";		
		addAnnotation
		  (classEditorEClass, 
		   source, 
		   new String[] {
			 "kind", "mixed"
		   });	
	}

} //EditorPackageImpl
