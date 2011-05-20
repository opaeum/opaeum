/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.nakeduml.uim.AbstractFolder;
import org.nakeduml.uim.AbstractFormFolder;
import org.nakeduml.uim.ActionKind;
import org.nakeduml.uim.ActionTaskForm;
import org.nakeduml.uim.ActivityFolder;
import org.nakeduml.uim.BuiltInAction;
import org.nakeduml.uim.ClassForm;
import org.nakeduml.uim.ControlKind;
import org.nakeduml.uim.DetailPanel;
import org.nakeduml.uim.EditableSecureObject;
import org.nakeduml.uim.EntityFolder;
import org.nakeduml.uim.FieldBinding;
import org.nakeduml.uim.FormPanel;
import org.nakeduml.uim.LayoutContainer;
import org.nakeduml.uim.LookupBinding;
import org.nakeduml.uim.MasterComponent;
import org.nakeduml.uim.NavigationBinding;
import org.nakeduml.uim.NavigationToEntity;
import org.nakeduml.uim.NavigationToOperation;
import org.nakeduml.uim.OperationAction;
import org.nakeduml.uim.OperationContainingFolder;
import org.nakeduml.uim.OperationInvocationForm;
import org.nakeduml.uim.OperationTaskForm;
import org.nakeduml.uim.OutlayableComponent;
import org.nakeduml.uim.PackageFolder;
import org.nakeduml.uim.PropertyRef;
import org.nakeduml.uim.RequiredRole;
import org.nakeduml.uim.SecureObject;
import org.nakeduml.uim.SecurityConstraint;
import org.nakeduml.uim.StateForm;
import org.nakeduml.uim.StateMachineFolder;
import org.nakeduml.uim.TableBinding;
import org.nakeduml.uim.TransitionAction;
import org.nakeduml.uim.UimAction;
import org.nakeduml.uim.UimBinding;
import org.nakeduml.uim.UimBorderLayout;
import org.nakeduml.uim.UimCheckBox;
import org.nakeduml.uim.UimComponent;
import org.nakeduml.uim.UimContainer;
import org.nakeduml.uim.UimControl;
import org.nakeduml.uim.UimDataColumn;
import org.nakeduml.uim.UimDataTable;
import org.nakeduml.uim.UimDatePopup;
import org.nakeduml.uim.UimDropdown;
import org.nakeduml.uim.UimFactory;
import org.nakeduml.uim.UimField;
import org.nakeduml.uim.UimForm;
import org.nakeduml.uim.UimFullLayout;
import org.nakeduml.uim.UimGridLayout;
import org.nakeduml.uim.UimLayout;
import org.nakeduml.uim.UimLookup;
import org.nakeduml.uim.UimMultiSelectListBox;
import org.nakeduml.uim.UimMultiSelectPopupSearch;
import org.nakeduml.uim.UimMultiSelectTreeView;
import org.nakeduml.uim.UimNavigation;
import org.nakeduml.uim.UimNumberScroller;
import org.nakeduml.uim.UimPackage;
import org.nakeduml.uim.UimPanel;
import org.nakeduml.uim.UimSingleSelectListBox;
import org.nakeduml.uim.UimSingleSelectPopupSearch;
import org.nakeduml.uim.UimSingleSelectTreeView;
import org.nakeduml.uim.UimTab;
import org.nakeduml.uim.UimTabPanel;
import org.nakeduml.uim.UimText;
import org.nakeduml.uim.UimTextArea;
import org.nakeduml.uim.UimToggleButton;
import org.nakeduml.uim.UimToolbarLayout;
import org.nakeduml.uim.UimXYLayout;
import org.nakeduml.uim.UmlReference;
import org.nakeduml.uim.UserInteractionElement;
import org.nakeduml.uim.UserInteractionModel;
import org.nakeduml.uim.UserInteractionWorkspace;
import org.nakeduml.uim.WorkspaceSecurityConstraint;

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
	private EClass uimFormEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass userInteractionModelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractFormFolderEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractFolderEClass = null;

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
	private EClass uimNavigationEClass = null;

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
	private EClass classFormEClass = null;

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
	private EClass workspaceSecurityConstraintEClass = null;

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
	private EClass operationActionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass navigationToOperationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass builtInActionEClass = null;

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
	private EClass navigationToEntityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass transitionActionEClass = null;

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
	private EClass actionTaskFormEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass uimActionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass securityConstraintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass uimGridLayoutEClass = null;

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
	private EClass uimBindingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass propertyRefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass uimDataColumnEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tableBindingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fieldBindingEClass = null;

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
	private EClass stateMachineFolderEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass entityFolderEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass activityFolderEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass operationContainingFolderEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass navigationBindingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass detailPanelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass packageFolderEClass = null;

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
	private EClass lookupBindingEClass = null;

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
	private EClass uimTextAreaEClass = null;

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
	private EClass uimContainerEClass = null;

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
	private EClass masterComponentEClass = null;

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
	private EClass uimToolbarLayoutEClass = null;

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
	private EClass uimXYLayoutEClass = null;

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
	private EClass uimMultiSelectPopupSearchEClass = null;

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
	private EClass uimToggleButtonEClass = null;

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
	private EClass umlReferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass userInteractionWorkspaceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass requiredRoleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass secureObjectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass editableSecureObjectEClass = null;

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
	private EClass outlayableComponentEClass = null;

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
	private EEnum actionKindEEnum = null;

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
	 * @see org.nakeduml.uim.UimPackage#eNS_URI
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

		// Create package meta-data objects
		theUimPackage.createPackageContents();

		// Initialize created meta-data
		theUimPackage.initializePackageContents();

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
	public EClass getUserInteractionModel() {
		return userInteractionModelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractFormFolder() {
		return abstractFormFolderEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractFormFolder_Parent() {
		return (EReference)abstractFormFolderEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractFolder() {
		return abstractFolderEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractFolder_Children() {
		return (EReference)abstractFolderEClass.getEStructuralFeatures().get(0);
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
	public EClass getUimNavigation() {
		return uimNavigationEClass;
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
	public EClass getWorkspaceSecurityConstraint() {
		return workspaceSecurityConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkspaceSecurityConstraint_RequiresGroupOwnership() {
		return (EAttribute)workspaceSecurityConstraintEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWorkspaceSecurityConstraint_RequiresOwnership() {
		return (EAttribute)workspaceSecurityConstraintEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkspaceSecurityConstraint_RequiredRoles() {
		return (EReference)workspaceSecurityConstraintEClass.getEStructuralFeatures().get(2);
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
	public EClass getOperationAction() {
		return operationActionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNavigationToOperation() {
		return navigationToOperationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNavigationToOperation_ToForm() {
		return (EReference)navigationToOperationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBuiltInAction() {
		return builtInActionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBuiltInAction_Kind() {
		return (EAttribute)builtInActionEClass.getEStructuralFeatures().get(0);
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
	public EClass getNavigationToEntity() {
		return navigationToEntityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNavigationToEntity_ToForm() {
		return (EReference)navigationToEntityEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNavigationToEntity_Binding() {
		return (EReference)navigationToEntityEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTransitionAction() {
		return transitionActionEClass;
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
	public EClass getUimAction() {
		return uimActionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSecurityConstraint() {
		return securityConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSecurityConstraint_InheritFromParent() {
		return (EAttribute)securityConstraintEClass.getEStructuralFeatures().get(0);
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
	public EReference getUimDataTable_Children() {
		return (EReference)uimDataTableEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUimBinding() {
		return uimBindingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUimBinding_Next() {
		return (EReference)uimBindingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPropertyRef() {
		return propertyRefEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPropertyRef_Binding() {
		return (EReference)propertyRefEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPropertyRef_Previous() {
		return (EReference)propertyRefEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPropertyRef_Next() {
		return (EReference)propertyRefEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUimDataColumn() {
		return uimDataColumnEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUimDataColumn_Width() {
		return (EAttribute)uimDataColumnEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUimDataColumn_Parent() {
		return (EReference)uimDataColumnEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTableBinding() {
		return tableBindingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTableBinding_Table() {
		return (EReference)tableBindingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFieldBinding() {
		return fieldBindingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFieldBinding_Field() {
		return (EReference)fieldBindingEClass.getEStructuralFeatures().get(0);
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
	public EClass getStateMachineFolder() {
		return stateMachineFolderEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEntityFolder() {
		return entityFolderEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getActivityFolder() {
		return activityFolderEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOperationContainingFolder() {
		return operationContainingFolderEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNavigationBinding() {
		return navigationBindingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNavigationBinding_Navigation() {
		return (EReference)navigationBindingEClass.getEStructuralFeatures().get(0);
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
	public EClass getPackageFolder() {
		return packageFolderEClass;
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
	public EAttribute getUimTabPanel_ActiveTabIndex() {
		return (EAttribute)uimTabPanelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUimTabPanel_Children() {
		return (EReference)uimTabPanelEClass.getEStructuralFeatures().get(1);
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
	public EClass getLookupBinding() {
		return lookupBindingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLookupBinding_Lookup() {
		return (EReference)lookupBindingEClass.getEStructuralFeatures().get(0);
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
	public EClass getUimDropdown() {
		return uimDropdownEClass;
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
	public EClass getUimContainer() {
		return uimContainerEClass;
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
	public EClass getUimToolbarLayout() {
		return uimToolbarLayoutEClass;
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
	public EClass getUimXYLayout() {
		return uimXYLayoutEClass;
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
	public EClass getUimMultiSelectPopupSearch() {
		return uimMultiSelectPopupSearchEClass;
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
	public EClass getUimToggleButton() {
		return uimToggleButtonEClass;
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
	public EClass getUserInteractionWorkspace() {
		return userInteractionWorkspaceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUserInteractionWorkspace_Visibility() {
		return (EReference)userInteractionWorkspaceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUserInteractionWorkspace_Editability() {
		return (EReference)userInteractionWorkspaceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRequiredRole() {
		return requiredRoleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSecureObject() {
		return secureObjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSecureObject_Visibility() {
		return (EReference)secureObjectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEditableSecureObject() {
		return editableSecureObjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEditableSecureObject_Editability() {
		return (EReference)editableSecureObjectEClass.getEStructuralFeatures().get(0);
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
	public EClass getUimPanel() {
		return uimPanelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getActionKind() {
		return actionKindEEnum;
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
		uimFormEClass = createEClass(UIM_FORM);
		createEReference(uimFormEClass, UIM_FORM__PANEL);

		userInteractionModelEClass = createEClass(USER_INTERACTION_MODEL);

		abstractFormFolderEClass = createEClass(ABSTRACT_FORM_FOLDER);
		createEReference(abstractFormFolderEClass, ABSTRACT_FORM_FOLDER__PARENT);

		abstractFolderEClass = createEClass(ABSTRACT_FOLDER);
		createEReference(abstractFolderEClass, ABSTRACT_FOLDER__CHILDREN);

		uimFieldEClass = createEClass(UIM_FIELD);
		createEReference(uimFieldEClass, UIM_FIELD__CONTROL);
		createEAttribute(uimFieldEClass, UIM_FIELD__CONTROL_KIND);
		createEAttribute(uimFieldEClass, UIM_FIELD__LABEL_WIDTH);
		createEReference(uimFieldEClass, UIM_FIELD__BINDING);

		uimNavigationEClass = createEClass(UIM_NAVIGATION);

		layoutContainerEClass = createEClass(LAYOUT_CONTAINER);
		createEReference(layoutContainerEClass, LAYOUT_CONTAINER__LAYOUT);

		classFormEClass = createEClass(CLASS_FORM);
		createEReference(classFormEClass, CLASS_FORM__FOLDER);

		stateFormEClass = createEClass(STATE_FORM);
		createEReference(stateFormEClass, STATE_FORM__FOLDER);

		workspaceSecurityConstraintEClass = createEClass(WORKSPACE_SECURITY_CONSTRAINT);
		createEAttribute(workspaceSecurityConstraintEClass, WORKSPACE_SECURITY_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP);
		createEAttribute(workspaceSecurityConstraintEClass, WORKSPACE_SECURITY_CONSTRAINT__REQUIRES_OWNERSHIP);
		createEReference(workspaceSecurityConstraintEClass, WORKSPACE_SECURITY_CONSTRAINT__REQUIRED_ROLES);

		operationInvocationFormEClass = createEClass(OPERATION_INVOCATION_FORM);
		createEReference(operationInvocationFormEClass, OPERATION_INVOCATION_FORM__FOLDER);

		uimComponentEClass = createEClass(UIM_COMPONENT);

		userInteractionElementEClass = createEClass(USER_INTERACTION_ELEMENT);
		createEAttribute(userInteractionElementEClass, USER_INTERACTION_ELEMENT__NAME);

		operationActionEClass = createEClass(OPERATION_ACTION);

		navigationToOperationEClass = createEClass(NAVIGATION_TO_OPERATION);
		createEReference(navigationToOperationEClass, NAVIGATION_TO_OPERATION__TO_FORM);

		builtInActionEClass = createEClass(BUILT_IN_ACTION);
		createEAttribute(builtInActionEClass, BUILT_IN_ACTION__KIND);

		uimControlEClass = createEClass(UIM_CONTROL);
		createEAttribute(uimControlEClass, UIM_CONTROL__WIDTH);
		createEReference(uimControlEClass, UIM_CONTROL__FIELD);

		navigationToEntityEClass = createEClass(NAVIGATION_TO_ENTITY);
		createEReference(navigationToEntityEClass, NAVIGATION_TO_ENTITY__TO_FORM);
		createEReference(navigationToEntityEClass, NAVIGATION_TO_ENTITY__BINDING);

		transitionActionEClass = createEClass(TRANSITION_ACTION);

		operationTaskFormEClass = createEClass(OPERATION_TASK_FORM);
		createEReference(operationTaskFormEClass, OPERATION_TASK_FORM__FOLDER);

		actionTaskFormEClass = createEClass(ACTION_TASK_FORM);
		createEReference(actionTaskFormEClass, ACTION_TASK_FORM__FOLDER);

		uimActionEClass = createEClass(UIM_ACTION);

		securityConstraintEClass = createEClass(SECURITY_CONSTRAINT);
		createEAttribute(securityConstraintEClass, SECURITY_CONSTRAINT__INHERIT_FROM_PARENT);

		uimGridLayoutEClass = createEClass(UIM_GRID_LAYOUT);
		createEAttribute(uimGridLayoutEClass, UIM_GRID_LAYOUT__NUMBER_OF_COLUMNS);

		uimDataTableEClass = createEClass(UIM_DATA_TABLE);
		createEReference(uimDataTableEClass, UIM_DATA_TABLE__BINDING);
		createEReference(uimDataTableEClass, UIM_DATA_TABLE__CHILDREN);

		uimBindingEClass = createEClass(UIM_BINDING);
		createEReference(uimBindingEClass, UIM_BINDING__NEXT);

		propertyRefEClass = createEClass(PROPERTY_REF);
		createEReference(propertyRefEClass, PROPERTY_REF__BINDING);
		createEReference(propertyRefEClass, PROPERTY_REF__PREVIOUS);
		createEReference(propertyRefEClass, PROPERTY_REF__NEXT);

		uimDataColumnEClass = createEClass(UIM_DATA_COLUMN);
		createEAttribute(uimDataColumnEClass, UIM_DATA_COLUMN__WIDTH);
		createEReference(uimDataColumnEClass, UIM_DATA_COLUMN__PARENT);

		tableBindingEClass = createEClass(TABLE_BINDING);
		createEReference(tableBindingEClass, TABLE_BINDING__TABLE);

		fieldBindingEClass = createEClass(FIELD_BINDING);
		createEReference(fieldBindingEClass, FIELD_BINDING__FIELD);

		formPanelEClass = createEClass(FORM_PANEL);
		createEReference(formPanelEClass, FORM_PANEL__FORM);

		stateMachineFolderEClass = createEClass(STATE_MACHINE_FOLDER);

		entityFolderEClass = createEClass(ENTITY_FOLDER);

		activityFolderEClass = createEClass(ACTIVITY_FOLDER);

		operationContainingFolderEClass = createEClass(OPERATION_CONTAINING_FOLDER);

		navigationBindingEClass = createEClass(NAVIGATION_BINDING);
		createEReference(navigationBindingEClass, NAVIGATION_BINDING__NAVIGATION);

		detailPanelEClass = createEClass(DETAIL_PANEL);
		createEReference(detailPanelEClass, DETAIL_PANEL__MASTER_COMPONENT);

		packageFolderEClass = createEClass(PACKAGE_FOLDER);

		uimTabPanelEClass = createEClass(UIM_TAB_PANEL);
		createEAttribute(uimTabPanelEClass, UIM_TAB_PANEL__ACTIVE_TAB_INDEX);
		createEReference(uimTabPanelEClass, UIM_TAB_PANEL__CHILDREN);

		uimTabEClass = createEClass(UIM_TAB);
		createEReference(uimTabEClass, UIM_TAB__PARENT);

		uimCheckBoxEClass = createEClass(UIM_CHECK_BOX);

		uimLookupEClass = createEClass(UIM_LOOKUP);
		createEReference(uimLookupEClass, UIM_LOOKUP__LOOKUP_SOURCE);

		lookupBindingEClass = createEClass(LOOKUP_BINDING);
		createEReference(lookupBindingEClass, LOOKUP_BINDING__LOOKUP);

		uimTextEClass = createEClass(UIM_TEXT);

		uimTextAreaEClass = createEClass(UIM_TEXT_AREA);
		createEAttribute(uimTextAreaEClass, UIM_TEXT_AREA__ROWS);

		uimDropdownEClass = createEClass(UIM_DROPDOWN);

		uimDatePopupEClass = createEClass(UIM_DATE_POPUP);

		uimSingleSelectListBoxEClass = createEClass(UIM_SINGLE_SELECT_LIST_BOX);
		createEAttribute(uimSingleSelectListBoxEClass, UIM_SINGLE_SELECT_LIST_BOX__ROWS);

		uimContainerEClass = createEClass(UIM_CONTAINER);

		uimSingleSelectTreeViewEClass = createEClass(UIM_SINGLE_SELECT_TREE_VIEW);

		masterComponentEClass = createEClass(MASTER_COMPONENT);
		createEReference(masterComponentEClass, MASTER_COMPONENT__DETAIL_PANELS);

		uimLayoutEClass = createEClass(UIM_LAYOUT);
		createEReference(uimLayoutEClass, UIM_LAYOUT__PARENT);
		createEReference(uimLayoutEClass, UIM_LAYOUT__CHILDREN);

		uimToolbarLayoutEClass = createEClass(UIM_TOOLBAR_LAYOUT);

		uimBorderLayoutEClass = createEClass(UIM_BORDER_LAYOUT);
		createEAttribute(uimBorderLayoutEClass, UIM_BORDER_LAYOUT__HORIZONTAL);

		uimXYLayoutEClass = createEClass(UIM_XY_LAYOUT);

		uimMultiSelectTreeViewEClass = createEClass(UIM_MULTI_SELECT_TREE_VIEW);

		uimMultiSelectListBoxEClass = createEClass(UIM_MULTI_SELECT_LIST_BOX);

		uimMultiSelectPopupSearchEClass = createEClass(UIM_MULTI_SELECT_POPUP_SEARCH);

		uimSingleSelectPopupSearchEClass = createEClass(UIM_SINGLE_SELECT_POPUP_SEARCH);

		uimToggleButtonEClass = createEClass(UIM_TOGGLE_BUTTON);

		uimNumberScrollerEClass = createEClass(UIM_NUMBER_SCROLLER);

		umlReferenceEClass = createEClass(UML_REFERENCE);
		createEAttribute(umlReferenceEClass, UML_REFERENCE__UML_ELEMENT_UID);

		userInteractionWorkspaceEClass = createEClass(USER_INTERACTION_WORKSPACE);
		createEReference(userInteractionWorkspaceEClass, USER_INTERACTION_WORKSPACE__VISIBILITY);
		createEReference(userInteractionWorkspaceEClass, USER_INTERACTION_WORKSPACE__EDITABILITY);

		requiredRoleEClass = createEClass(REQUIRED_ROLE);

		secureObjectEClass = createEClass(SECURE_OBJECT);
		createEReference(secureObjectEClass, SECURE_OBJECT__VISIBILITY);

		editableSecureObjectEClass = createEClass(EDITABLE_SECURE_OBJECT);
		createEReference(editableSecureObjectEClass, EDITABLE_SECURE_OBJECT__EDITABILITY);

		uimFullLayoutEClass = createEClass(UIM_FULL_LAYOUT);

		outlayableComponentEClass = createEClass(OUTLAYABLE_COMPONENT);
		createEReference(outlayableComponentEClass, OUTLAYABLE_COMPONENT__PARENT);

		uimPanelEClass = createEClass(UIM_PANEL);

		// Create enums
		actionKindEEnum = createEEnum(ACTION_KIND);
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
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		uimFormEClass.getESuperTypes().add(this.getUserInteractionElement());
		userInteractionModelEClass.getESuperTypes().add(this.getUmlReference());
		userInteractionModelEClass.getESuperTypes().add(this.getAbstractFormFolder());
		abstractFormFolderEClass.getESuperTypes().add(this.getAbstractFolder());
		abstractFormFolderEClass.getESuperTypes().add(this.getEditableSecureObject());
		abstractFolderEClass.getESuperTypes().add(this.getUserInteractionElement());
		abstractFolderEClass.getESuperTypes().add(this.getUmlReference());
		uimFieldEClass.getESuperTypes().add(this.getEditableSecureObject());
		uimFieldEClass.getESuperTypes().add(this.getOutlayableComponent());
		uimNavigationEClass.getESuperTypes().add(this.getOutlayableComponent());
		layoutContainerEClass.getESuperTypes().add(this.getUimContainer());
		classFormEClass.getESuperTypes().add(this.getFormPanel());
		stateFormEClass.getESuperTypes().add(this.getFormPanel());
		operationInvocationFormEClass.getESuperTypes().add(this.getFormPanel());
		uimComponentEClass.getESuperTypes().add(this.getUserInteractionElement());
		uimComponentEClass.getESuperTypes().add(this.getSecureObject());
		operationActionEClass.getESuperTypes().add(this.getUimAction());
		operationActionEClass.getESuperTypes().add(this.getUmlReference());
		navigationToOperationEClass.getESuperTypes().add(this.getUimNavigation());
		navigationToOperationEClass.getESuperTypes().add(this.getUmlReference());
		builtInActionEClass.getESuperTypes().add(this.getUimAction());
		navigationToEntityEClass.getESuperTypes().add(this.getUimNavigation());
		navigationToEntityEClass.getESuperTypes().add(this.getUmlReference());
		transitionActionEClass.getESuperTypes().add(this.getUimAction());
		transitionActionEClass.getESuperTypes().add(this.getUmlReference());
		operationTaskFormEClass.getESuperTypes().add(this.getFormPanel());
		actionTaskFormEClass.getESuperTypes().add(this.getFormPanel());
		uimActionEClass.getESuperTypes().add(this.getOutlayableComponent());
		securityConstraintEClass.getESuperTypes().add(this.getWorkspaceSecurityConstraint());
		uimGridLayoutEClass.getESuperTypes().add(this.getUimLayout());
		uimDataTableEClass.getESuperTypes().add(this.getMasterComponent());
		uimDataTableEClass.getESuperTypes().add(this.getUimContainer());
		uimDataTableEClass.getESuperTypes().add(this.getOutlayableComponent());
		uimBindingEClass.getESuperTypes().add(this.getUmlReference());
		propertyRefEClass.getESuperTypes().add(this.getUmlReference());
		uimDataColumnEClass.getESuperTypes().add(this.getLayoutContainer());
		tableBindingEClass.getESuperTypes().add(this.getUimBinding());
		fieldBindingEClass.getESuperTypes().add(this.getUimBinding());
		formPanelEClass.getESuperTypes().add(this.getUmlReference());
		formPanelEClass.getESuperTypes().add(this.getLayoutContainer());
		stateMachineFolderEClass.getESuperTypes().add(this.getOperationContainingFolder());
		entityFolderEClass.getESuperTypes().add(this.getOperationContainingFolder());
		activityFolderEClass.getESuperTypes().add(this.getAbstractFormFolder());
		operationContainingFolderEClass.getESuperTypes().add(this.getAbstractFormFolder());
		navigationBindingEClass.getESuperTypes().add(this.getUimBinding());
		detailPanelEClass.getESuperTypes().add(this.getUimPanel());
		packageFolderEClass.getESuperTypes().add(this.getAbstractFormFolder());
		uimTabPanelEClass.getESuperTypes().add(this.getUimContainer());
		uimTabPanelEClass.getESuperTypes().add(this.getOutlayableComponent());
		uimTabEClass.getESuperTypes().add(this.getLayoutContainer());
		uimCheckBoxEClass.getESuperTypes().add(this.getUimControl());
		uimLookupEClass.getESuperTypes().add(this.getUimControl());
		lookupBindingEClass.getESuperTypes().add(this.getUimBinding());
		uimTextEClass.getESuperTypes().add(this.getUimControl());
		uimTextAreaEClass.getESuperTypes().add(this.getUimControl());
		uimDropdownEClass.getESuperTypes().add(this.getUimLookup());
		uimDatePopupEClass.getESuperTypes().add(this.getUimControl());
		uimSingleSelectListBoxEClass.getESuperTypes().add(this.getUimLookup());
		uimContainerEClass.getESuperTypes().add(this.getUimComponent());
		uimContainerEClass.getESuperTypes().add(this.getEditableSecureObject());
		uimSingleSelectTreeViewEClass.getESuperTypes().add(this.getMasterComponent());
		uimSingleSelectTreeViewEClass.getESuperTypes().add(this.getUimLookup());
		uimLayoutEClass.getESuperTypes().add(this.getUimContainer());
		uimToolbarLayoutEClass.getESuperTypes().add(this.getUimLayout());
		uimBorderLayoutEClass.getESuperTypes().add(this.getUimLayout());
		uimXYLayoutEClass.getESuperTypes().add(this.getUimLayout());
		uimMultiSelectTreeViewEClass.getESuperTypes().add(this.getUimLookup());
		uimMultiSelectListBoxEClass.getESuperTypes().add(this.getUimLookup());
		uimMultiSelectPopupSearchEClass.getESuperTypes().add(this.getUimLookup());
		uimSingleSelectPopupSearchEClass.getESuperTypes().add(this.getUimLookup());
		uimToggleButtonEClass.getESuperTypes().add(this.getUimControl());
		uimNumberScrollerEClass.getESuperTypes().add(this.getUimControl());
		userInteractionWorkspaceEClass.getESuperTypes().add(this.getAbstractFolder());
		requiredRoleEClass.getESuperTypes().add(this.getUmlReference());
		editableSecureObjectEClass.getESuperTypes().add(this.getSecureObject());
		uimFullLayoutEClass.getESuperTypes().add(this.getUimLayout());
		outlayableComponentEClass.getESuperTypes().add(this.getUimComponent());
		uimPanelEClass.getESuperTypes().add(this.getLayoutContainer());
		uimPanelEClass.getESuperTypes().add(this.getOutlayableComponent());

		// Initialize classes and features; add operations and parameters
		initEClass(uimFormEClass, UimForm.class, "UimForm", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUimForm_Panel(), this.getFormPanel(), this.getFormPanel_Form(), "panel", null, 0, 1, UimForm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(uimFormEClass, this.getAbstractFormFolder(), "getFolder", 1, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(userInteractionModelEClass, UserInteractionModel.class, "UserInteractionModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(abstractFormFolderEClass, AbstractFormFolder.class, "AbstractFormFolder", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAbstractFormFolder_Parent(), this.getAbstractFolder(), this.getAbstractFolder_Children(), "parent", null, 0, 1, AbstractFormFolder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(abstractFolderEClass, AbstractFolder.class, "AbstractFolder", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAbstractFolder_Children(), this.getAbstractFormFolder(), this.getAbstractFormFolder_Parent(), "children", null, 0, -1, AbstractFolder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimFieldEClass, UimField.class, "UimField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUimField_Control(), this.getUimControl(), this.getUimControl_Field(), "control", null, 1, 1, UimField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUimField_ControlKind(), this.getControlKind(), "controlKind", null, 0, 1, UimField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUimField_LabelWidth(), theEcorePackage.getEIntegerObject(), "labelWidth", "200", 0, 1, UimField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUimField_Binding(), this.getFieldBinding(), this.getFieldBinding_Field(), "binding", null, 1, 1, UimField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimNavigationEClass, UimNavigation.class, "UimNavigation", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(layoutContainerEClass, LayoutContainer.class, "LayoutContainer", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getLayoutContainer_Layout(), this.getUimLayout(), this.getUimLayout_Parent(), "layout", null, 0, 1, LayoutContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(classFormEClass, ClassForm.class, "ClassForm", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getClassForm_Folder(), this.getOperationContainingFolder(), null, "folder", null, 1, 1, ClassForm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stateFormEClass, StateForm.class, "StateForm", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStateForm_Folder(), this.getStateMachineFolder(), null, "folder", null, 1, 1, StateForm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(workspaceSecurityConstraintEClass, WorkspaceSecurityConstraint.class, "WorkspaceSecurityConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWorkspaceSecurityConstraint_RequiresGroupOwnership(), theEcorePackage.getEBoolean(), "requiresGroupOwnership", null, 0, 1, WorkspaceSecurityConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkspaceSecurityConstraint_RequiresOwnership(), theEcorePackage.getEBoolean(), "requiresOwnership", null, 0, 1, WorkspaceSecurityConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWorkspaceSecurityConstraint_RequiredRoles(), this.getRequiredRole(), null, "requiredRoles", null, 0, -1, WorkspaceSecurityConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(operationInvocationFormEClass, OperationInvocationForm.class, "OperationInvocationForm", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOperationInvocationForm_Folder(), this.getOperationContainingFolder(), null, "folder", null, 1, 1, OperationInvocationForm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimComponentEClass, UimComponent.class, "UimComponent", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		addEOperation(uimComponentEClass, this.getUimContainer(), "getParent", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(userInteractionElementEClass, UserInteractionElement.class, "UserInteractionElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUserInteractionElement_Name(), theEcorePackage.getEString(), "name", null, 0, 1, UserInteractionElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(operationActionEClass, OperationAction.class, "OperationAction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(navigationToOperationEClass, NavigationToOperation.class, "NavigationToOperation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getNavigationToOperation_ToForm(), this.getOperationInvocationForm(), null, "toForm", null, 0, 1, NavigationToOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(builtInActionEClass, BuiltInAction.class, "BuiltInAction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBuiltInAction_Kind(), this.getActionKind(), "kind", null, 0, 1, BuiltInAction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimControlEClass, UimControl.class, "UimControl", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUimControl_Width(), ecorePackage.getEString(), "width", null, 0, 1, UimControl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUimControl_Field(), this.getUimField(), this.getUimField_Control(), "field", null, 0, 1, UimControl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(navigationToEntityEClass, NavigationToEntity.class, "NavigationToEntity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getNavigationToEntity_ToForm(), this.getClassForm(), null, "toForm", null, 0, 1, NavigationToEntity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNavigationToEntity_Binding(), this.getNavigationBinding(), this.getNavigationBinding_Navigation(), "binding", null, 0, 1, NavigationToEntity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(transitionActionEClass, TransitionAction.class, "TransitionAction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(operationTaskFormEClass, OperationTaskForm.class, "OperationTaskForm", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOperationTaskForm_Folder(), this.getEntityFolder(), null, "folder", null, 1, 1, OperationTaskForm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(actionTaskFormEClass, ActionTaskForm.class, "ActionTaskForm", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getActionTaskForm_Folder(), this.getActivityFolder(), null, "folder", null, 0, 1, ActionTaskForm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimActionEClass, UimAction.class, "UimAction", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(securityConstraintEClass, SecurityConstraint.class, "SecurityConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSecurityConstraint_InheritFromParent(), theEcorePackage.getEBoolean(), "inheritFromParent", "true", 0, 1, SecurityConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimGridLayoutEClass, UimGridLayout.class, "UimGridLayout", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUimGridLayout_NumberOfColumns(), theEcorePackage.getEIntegerObject(), "numberOfColumns", null, 0, 1, UimGridLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimDataTableEClass, UimDataTable.class, "UimDataTable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUimDataTable_Binding(), this.getTableBinding(), this.getTableBinding_Table(), "binding", null, 0, 1, UimDataTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUimDataTable_Children(), this.getUimDataColumn(), this.getUimDataColumn_Parent(), "children", null, 0, -1, UimDataTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimBindingEClass, UimBinding.class, "UimBinding", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUimBinding_Next(), this.getPropertyRef(), this.getPropertyRef_Binding(), "next", null, 0, 1, UimBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(propertyRefEClass, PropertyRef.class, "PropertyRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPropertyRef_Binding(), this.getUimBinding(), this.getUimBinding_Next(), "binding", null, 0, 1, PropertyRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPropertyRef_Previous(), this.getPropertyRef(), this.getPropertyRef_Next(), "previous", null, 0, 1, PropertyRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPropertyRef_Next(), this.getPropertyRef(), this.getPropertyRef_Previous(), "next", null, 0, 1, PropertyRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimDataColumnEClass, UimDataColumn.class, "UimDataColumn", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUimDataColumn_Width(), theEcorePackage.getEIntegerObject(), "width", null, 0, 1, UimDataColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUimDataColumn_Parent(), this.getUimDataTable(), this.getUimDataTable_Children(), "parent", null, 0, 1, UimDataColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tableBindingEClass, TableBinding.class, "TableBinding", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTableBinding_Table(), this.getUimDataTable(), this.getUimDataTable_Binding(), "table", null, 1, 1, TableBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(fieldBindingEClass, FieldBinding.class, "FieldBinding", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFieldBinding_Field(), this.getUimField(), this.getUimField_Binding(), "field", null, 0, 1, FieldBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(formPanelEClass, FormPanel.class, "FormPanel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFormPanel_Form(), this.getUimForm(), this.getUimForm_Panel(), "form", null, 0, 1, FormPanel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stateMachineFolderEClass, StateMachineFolder.class, "StateMachineFolder", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(entityFolderEClass, EntityFolder.class, "EntityFolder", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(activityFolderEClass, ActivityFolder.class, "ActivityFolder", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(operationContainingFolderEClass, OperationContainingFolder.class, "OperationContainingFolder", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(navigationBindingEClass, NavigationBinding.class, "NavigationBinding", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getNavigationBinding_Navigation(), this.getNavigationToEntity(), this.getNavigationToEntity_Binding(), "navigation", null, 1, 1, NavigationBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(detailPanelEClass, DetailPanel.class, "DetailPanel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDetailPanel_MasterComponent(), this.getMasterComponent(), this.getMasterComponent_DetailPanels(), "masterComponent", null, 0, 1, DetailPanel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(packageFolderEClass, PackageFolder.class, "PackageFolder", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimTabPanelEClass, UimTabPanel.class, "UimTabPanel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUimTabPanel_ActiveTabIndex(), theEcorePackage.getEIntegerObject(), "activeTabIndex", null, 0, 1, UimTabPanel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUimTabPanel_Children(), this.getUimTab(), this.getUimTab_Parent(), "children", null, 0, -1, UimTabPanel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimTabEClass, UimTab.class, "UimTab", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUimTab_Parent(), this.getUimTabPanel(), this.getUimTabPanel_Children(), "parent", null, 1, 1, UimTab.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimCheckBoxEClass, UimCheckBox.class, "UimCheckBox", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimLookupEClass, UimLookup.class, "UimLookup", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUimLookup_LookupSource(), this.getLookupBinding(), this.getLookupBinding_Lookup(), "lookupSource", null, 0, 1, UimLookup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(lookupBindingEClass, LookupBinding.class, "LookupBinding", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getLookupBinding_Lookup(), this.getUimLookup(), this.getUimLookup_LookupSource(), "lookup", null, 0, 1, LookupBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimTextEClass, UimText.class, "UimText", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimTextAreaEClass, UimTextArea.class, "UimTextArea", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUimTextArea_Rows(), theEcorePackage.getEIntegerObject(), "rows", null, 0, 1, UimTextArea.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimDropdownEClass, UimDropdown.class, "UimDropdown", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimDatePopupEClass, UimDatePopup.class, "UimDatePopup", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimSingleSelectListBoxEClass, UimSingleSelectListBox.class, "UimSingleSelectListBox", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUimSingleSelectListBox_Rows(), theEcorePackage.getEIntegerObject(), "rows", null, 0, 1, UimSingleSelectListBox.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimContainerEClass, UimContainer.class, "UimContainer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimSingleSelectTreeViewEClass, UimSingleSelectTreeView.class, "UimSingleSelectTreeView", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(masterComponentEClass, MasterComponent.class, "MasterComponent", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMasterComponent_DetailPanels(), this.getDetailPanel(), this.getDetailPanel_MasterComponent(), "detailPanels", null, 0, -1, MasterComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimLayoutEClass, UimLayout.class, "UimLayout", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUimLayout_Parent(), this.getLayoutContainer(), this.getLayoutContainer_Layout(), "parent", null, 0, 1, UimLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUimLayout_Children(), this.getOutlayableComponent(), this.getOutlayableComponent_Parent(), "children", null, 0, -1, UimLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimToolbarLayoutEClass, UimToolbarLayout.class, "UimToolbarLayout", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimBorderLayoutEClass, UimBorderLayout.class, "UimBorderLayout", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUimBorderLayout_Horizontal(), theEcorePackage.getEBoolean(), "horizontal", null, 0, 1, UimBorderLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimXYLayoutEClass, UimXYLayout.class, "UimXYLayout", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimMultiSelectTreeViewEClass, UimMultiSelectTreeView.class, "UimMultiSelectTreeView", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimMultiSelectListBoxEClass, UimMultiSelectListBox.class, "UimMultiSelectListBox", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimMultiSelectPopupSearchEClass, UimMultiSelectPopupSearch.class, "UimMultiSelectPopupSearch", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimSingleSelectPopupSearchEClass, UimSingleSelectPopupSearch.class, "UimSingleSelectPopupSearch", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimToggleButtonEClass, UimToggleButton.class, "UimToggleButton", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimNumberScrollerEClass, UimNumberScroller.class, "UimNumberScroller", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(umlReferenceEClass, UmlReference.class, "UmlReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUmlReference_UmlElementUid(), theEcorePackage.getEString(), "umlElementUid", null, 0, 1, UmlReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(userInteractionWorkspaceEClass, UserInteractionWorkspace.class, "UserInteractionWorkspace", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUserInteractionWorkspace_Visibility(), this.getWorkspaceSecurityConstraint(), null, "visibility", null, 0, 1, UserInteractionWorkspace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUserInteractionWorkspace_Editability(), this.getWorkspaceSecurityConstraint(), null, "editability", null, 0, 1, UserInteractionWorkspace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(requiredRoleEClass, RequiredRole.class, "RequiredRole", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(secureObjectEClass, SecureObject.class, "SecureObject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSecureObject_Visibility(), this.getSecurityConstraint(), null, "visibility", null, 0, 1, SecureObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(editableSecureObjectEClass, EditableSecureObject.class, "EditableSecureObject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEditableSecureObject_Editability(), this.getSecurityConstraint(), null, "editability", null, 0, 1, EditableSecureObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimFullLayoutEClass, UimFullLayout.class, "UimFullLayout", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(outlayableComponentEClass, OutlayableComponent.class, "OutlayableComponent", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOutlayableComponent_Parent(), this.getUimLayout(), this.getUimLayout_Children(), "parent", null, 0, 1, OutlayableComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimPanelEClass, UimPanel.class, "UimPanel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Initialize enums and add enum literals
		initEEnum(actionKindEEnum, ActionKind.class, "ActionKind");
		addEEnumLiteral(actionKindEEnum, ActionKind.UPDATE);
		addEEnumLiteral(actionKindEEnum, ActionKind.DELETE);
		addEEnumLiteral(actionKindEEnum, ActionKind.BACK);
		addEEnumLiteral(actionKindEEnum, ActionKind.EXECUTE_OPERATION);
		addEEnumLiteral(actionKindEEnum, ActionKind.POSTPONE_TASK);
		addEEnumLiteral(actionKindEEnum, ActionKind.COMPLETE_TASK);
		addEEnumLiteral(actionKindEEnum, ActionKind.RETURN_TASK);
		addEEnumLiteral(actionKindEEnum, ActionKind.CREATE);
		addEEnumLiteral(actionKindEEnum, ActionKind.FORWARD_TASK);

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

		// Create resource
		createResource(eNS_URI);

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
		  (getAbstractFolder_Children(), 
		   source, 
		   new String[] {
			 "wildcards", "",
			 "name", ""
		   });				
		addAnnotation
		  (classFormEClass, 
		   source, 
		   new String[] {
			 "kind", "mixed"
		   });									
	}

} //UimPackageImpl
