/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim.form.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.nakeduml.uim.UimPackage;
import org.nakeduml.uim.action.ActionPackage;
import org.nakeduml.uim.action.impl.ActionPackageImpl;
import org.nakeduml.uim.binding.BindingPackage;
import org.nakeduml.uim.binding.impl.BindingPackageImpl;
import org.nakeduml.uim.control.ControlPackage;
import org.nakeduml.uim.control.impl.ControlPackageImpl;
import org.nakeduml.uim.folder.FolderPackage;
import org.nakeduml.uim.folder.impl.FolderPackageImpl;
import org.nakeduml.uim.form.ActionTaskForm;
import org.nakeduml.uim.form.ClassForm;
import org.nakeduml.uim.form.DetailPanel;
import org.nakeduml.uim.form.FormFactory;
import org.nakeduml.uim.form.FormPackage;
import org.nakeduml.uim.form.FormPanel;
import org.nakeduml.uim.form.OperationInvocationForm;
import org.nakeduml.uim.form.OperationTaskForm;
import org.nakeduml.uim.form.StateForm;
import org.nakeduml.uim.form.UimForm;
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
public class FormPackageImpl extends EPackageImpl implements FormPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass formPanelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass actionTaskFormEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stateFormEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass classFormEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass uimFormEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass operationTaskFormEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass operationInvocationFormEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass detailPanelEClass = null;

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
	 * @see org.nakeduml.uim.form.FormPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private FormPackageImpl() {
		super(eNS_URI, FormFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link FormPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static FormPackage init() {
		if (isInited) return (FormPackage)EPackage.Registry.INSTANCE.getEPackage(FormPackage.eNS_URI);

		// Obtain or create and register package
		FormPackageImpl theFormPackage = (FormPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof FormPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new FormPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		UimPackageImpl theUimPackage = (UimPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UimPackage.eNS_URI) instanceof UimPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UimPackage.eNS_URI) : UimPackage.eINSTANCE);
		LayoutPackageImpl theLayoutPackage = (LayoutPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(LayoutPackage.eNS_URI) instanceof LayoutPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(LayoutPackage.eNS_URI) : LayoutPackage.eINSTANCE);
		ControlPackageImpl theControlPackage = (ControlPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ControlPackage.eNS_URI) instanceof ControlPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ControlPackage.eNS_URI) : ControlPackage.eINSTANCE);
		FolderPackageImpl theFolderPackage = (FolderPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(FolderPackage.eNS_URI) instanceof FolderPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(FolderPackage.eNS_URI) : FolderPackage.eINSTANCE);
		BindingPackageImpl theBindingPackage = (BindingPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(BindingPackage.eNS_URI) instanceof BindingPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(BindingPackage.eNS_URI) : BindingPackage.eINSTANCE);
		SecurityPackageImpl theSecurityPackage = (SecurityPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(SecurityPackage.eNS_URI) instanceof SecurityPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(SecurityPackage.eNS_URI) : SecurityPackage.eINSTANCE);
		ActionPackageImpl theActionPackage = (ActionPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ActionPackage.eNS_URI) instanceof ActionPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ActionPackage.eNS_URI) : ActionPackage.eINSTANCE);

		// Create package meta-data objects
		theFormPackage.createPackageContents();
		theUimPackage.createPackageContents();
		theLayoutPackage.createPackageContents();
		theControlPackage.createPackageContents();
		theFolderPackage.createPackageContents();
		theBindingPackage.createPackageContents();
		theSecurityPackage.createPackageContents();
		theActionPackage.createPackageContents();

		// Initialize created meta-data
		theFormPackage.initializePackageContents();
		theUimPackage.initializePackageContents();
		theLayoutPackage.initializePackageContents();
		theControlPackage.initializePackageContents();
		theFolderPackage.initializePackageContents();
		theBindingPackage.initializePackageContents();
		theSecurityPackage.initializePackageContents();
		theActionPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theFormPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(FormPackage.eNS_URI, theFormPackage);
		return theFormPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFormPanel() {
		return formPanelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFormPanel_Form() {
		return (EReference)formPanelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getActionTaskForm() {
		return actionTaskFormEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActionTaskForm_Folder() {
		return (EReference)actionTaskFormEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStateForm() {
		return stateFormEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStateForm_Folder() {
		return (EReference)stateFormEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getClassForm() {
		return classFormEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassForm_Folder() {
		return (EReference)classFormEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUimForm() {
		return uimFormEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUimForm_Panel() {
		return (EReference)uimFormEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOperationTaskForm() {
		return operationTaskFormEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOperationTaskForm_Folder() {
		return (EReference)operationTaskFormEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOperationInvocationForm() {
		return operationInvocationFormEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOperationInvocationForm_Folder() {
		return (EReference)operationInvocationFormEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDetailPanel() {
		return detailPanelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDetailPanel_MasterComponent() {
		return (EReference)detailPanelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormFactory getFormFactory() {
		return (FormFactory)getEFactoryInstance();
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
		formPanelEClass = createEClass(FORM_PANEL);
		createEReference(formPanelEClass, FORM_PANEL__FORM);

		actionTaskFormEClass = createEClass(ACTION_TASK_FORM);
		createEReference(actionTaskFormEClass, ACTION_TASK_FORM__FOLDER);

		stateFormEClass = createEClass(STATE_FORM);
		createEReference(stateFormEClass, STATE_FORM__FOLDER);

		classFormEClass = createEClass(CLASS_FORM);
		createEReference(classFormEClass, CLASS_FORM__FOLDER);

		uimFormEClass = createEClass(UIM_FORM);
		createEReference(uimFormEClass, UIM_FORM__PANEL);

		operationTaskFormEClass = createEClass(OPERATION_TASK_FORM);
		createEReference(operationTaskFormEClass, OPERATION_TASK_FORM__FOLDER);

		operationInvocationFormEClass = createEClass(OPERATION_INVOCATION_FORM);
		createEReference(operationInvocationFormEClass, OPERATION_INVOCATION_FORM__FOLDER);

		detailPanelEClass = createEClass(DETAIL_PANEL);
		createEReference(detailPanelEClass, DETAIL_PANEL__MASTER_COMPONENT);
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
		LayoutPackage theLayoutPackage = (LayoutPackage)EPackage.Registry.INSTANCE.getEPackage(LayoutPackage.eNS_URI);
		FolderPackage theFolderPackage = (FolderPackage)EPackage.Registry.INSTANCE.getEPackage(FolderPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		formPanelEClass.getESuperTypes().add(theUimPackage.getUmlReference());
		formPanelEClass.getESuperTypes().add(theLayoutPackage.getLayoutContainer());
		actionTaskFormEClass.getESuperTypes().add(this.getFormPanel());
		stateFormEClass.getESuperTypes().add(this.getFormPanel());
		classFormEClass.getESuperTypes().add(this.getFormPanel());
		uimFormEClass.getESuperTypes().add(theUimPackage.getUserInteractionElement());
		operationTaskFormEClass.getESuperTypes().add(this.getFormPanel());
		operationInvocationFormEClass.getESuperTypes().add(this.getFormPanel());
		detailPanelEClass.getESuperTypes().add(this.getUimForm());

		// Initialize classes and features; add operations and parameters
		initEClass(formPanelEClass, FormPanel.class, "FormPanel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFormPanel_Form(), this.getUimForm(), this.getUimForm_Panel(), "form", null, 0, 1, FormPanel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(actionTaskFormEClass, ActionTaskForm.class, "ActionTaskForm", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getActionTaskForm_Folder(), theFolderPackage.getActivityFolder(), null, "folder", null, 0, 1, ActionTaskForm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stateFormEClass, StateForm.class, "StateForm", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStateForm_Folder(), theFolderPackage.getStateMachineFolder(), null, "folder", null, 1, 1, StateForm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(classFormEClass, ClassForm.class, "ClassForm", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getClassForm_Folder(), theFolderPackage.getOperationContainingFolder(), null, "folder", null, 1, 1, ClassForm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimFormEClass, UimForm.class, "UimForm", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUimForm_Panel(), this.getFormPanel(), this.getFormPanel_Form(), "panel", null, 0, 1, UimForm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(uimFormEClass, theFolderPackage.getAbstractFormFolder(), "getFolder", 1, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(operationTaskFormEClass, OperationTaskForm.class, "OperationTaskForm", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOperationTaskForm_Folder(), theFolderPackage.getEntityFolder(), null, "folder", null, 1, 1, OperationTaskForm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(operationInvocationFormEClass, OperationInvocationForm.class, "OperationInvocationForm", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOperationInvocationForm_Folder(), theFolderPackage.getOperationContainingFolder(), null, "folder", null, 1, 1, OperationInvocationForm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(detailPanelEClass, DetailPanel.class, "DetailPanel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDetailPanel_MasterComponent(), theUimPackage.getMasterComponent(), theUimPackage.getMasterComponent_DetailPanels(), "masterComponent", null, 0, 1, DetailPanel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
		  (classFormEClass, 
		   source, 
		   new String[] {
			 "kind", "mixed"
		   });	
	}

} //FormPackageImpl
