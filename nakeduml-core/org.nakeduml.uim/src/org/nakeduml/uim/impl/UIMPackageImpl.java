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
import org.eclipse.uml2.uml.UMLPackage;
import org.nakeduml.uim.AbstractFolder;
import org.nakeduml.uim.AbstractFormFolder;
import org.nakeduml.uim.ActionKind;
import org.nakeduml.uim.ActionTaskForm;
import org.nakeduml.uim.ActivityFolder;
import org.nakeduml.uim.BuiltInAction;
import org.nakeduml.uim.ChildSecurityConstraint;
import org.nakeduml.uim.ClassForm;
import org.nakeduml.uim.ControlKind;
import org.nakeduml.uim.DetailPanel;
import org.nakeduml.uim.EntityFolder;
import org.nakeduml.uim.FieldBinding;
import org.nakeduml.uim.FormPanel;
import org.nakeduml.uim.LookupBinding;
import org.nakeduml.uim.MasterComponent;
import org.nakeduml.uim.ModelSecurityConstraint;
import org.nakeduml.uim.NavigationBinding;
import org.nakeduml.uim.NavigationToEntity;
import org.nakeduml.uim.NavigationToOperation;
import org.nakeduml.uim.OperationAction;
import org.nakeduml.uim.OperationContainingFolder;
import org.nakeduml.uim.OperationInvocationForm;
import org.nakeduml.uim.OperationTaskForm;
import org.nakeduml.uim.PackageFolder;
import org.nakeduml.uim.PropertyRef;
import org.nakeduml.uim.StateForm;
import org.nakeduml.uim.StateMachineFolder;
import org.nakeduml.uim.TableBinding;
import org.nakeduml.uim.TransitionAction;
import org.nakeduml.uim.UIMAction;
import org.nakeduml.uim.UIMBinding;
import org.nakeduml.uim.UIMBorderLayout;
import org.nakeduml.uim.UIMCheckBox;
import org.nakeduml.uim.UIMComponent;
import org.nakeduml.uim.UIMContainer;
import org.nakeduml.uim.UIMControl;
import org.nakeduml.uim.UIMDataColumn;
import org.nakeduml.uim.UIMDataTable;
import org.nakeduml.uim.UIMDatePopup;
import org.nakeduml.uim.UIMDropdown;
import org.nakeduml.uim.UIMFactory;
import org.nakeduml.uim.UIMField;
import org.nakeduml.uim.UIMForm;
import org.nakeduml.uim.UIMGridLayout;
import org.nakeduml.uim.UIMLayout;
import org.nakeduml.uim.UIMLookup;
import org.nakeduml.uim.UIMMultiSelectListBox;
import org.nakeduml.uim.UIMMultiSelectPopupSearch;
import org.nakeduml.uim.UIMMultiSelectTreeView;
import org.nakeduml.uim.UIMNavigation;
import org.nakeduml.uim.UIMNumberScroller;
import org.nakeduml.uim.UIMPackage;
import org.nakeduml.uim.UIMPanel;
import org.nakeduml.uim.UIMSingleSelectListBox;
import org.nakeduml.uim.UIMSingleSelectPopupSearch;
import org.nakeduml.uim.UIMSingleSelectTreeView;
import org.nakeduml.uim.UIMTab;
import org.nakeduml.uim.UIMTabPanel;
import org.nakeduml.uim.UIMText;
import org.nakeduml.uim.UIMTextArea;
import org.nakeduml.uim.UIMToggleButton;
import org.nakeduml.uim.UIMToolbarLayout;
import org.nakeduml.uim.UIMXYLayout;
import org.nakeduml.uim.UserInteractionElement;
import org.nakeduml.uim.UserInteractionModel;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class UIMPackageImpl extends EPackageImpl implements UIMPackage {
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
	private EClass uimPanelEClass = null;

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
	private EClass modelSecurityConstraintEClass = null;

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
	private EClass childSecurityConstraintEClass = null;

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
	private EClass uimxyLayoutEClass = null;

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
	 * @see org.nakeduml.uim.UIMPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private UIMPackageImpl() {
		super(eNS_URI, UIMFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link UIMPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static UIMPackage init() {
		if (isInited) return (UIMPackage)EPackage.Registry.INSTANCE.getEPackage(UIMPackage.eNS_URI);

		// Obtain or create and register package
		UIMPackageImpl theUIMPackage = (UIMPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof UIMPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new UIMPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		UMLPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theUIMPackage.createPackageContents();

		// Initialize created meta-data
		theUIMPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theUIMPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(UIMPackage.eNS_URI, theUIMPackage);
		return theUIMPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUIMForm() {
		return uimFormEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUIMForm_Panel() {
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
	public EReference getUserInteractionModel_UmlModel() {
		return (EReference)userInteractionModelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUserInteractionModel_SecurityOnVisibility() {
		return (EReference)userInteractionModelEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUserInteractionModel_SecuirytOnEditability() {
		return (EReference)userInteractionModelEClass.getEStructuralFeatures().get(2);
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
	public EReference getAbstractFormFolder_SecurityOnVisibility() {
		return (EReference)abstractFormFolderEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractFormFolder_SecurityOnEditability() {
		return (EReference)abstractFormFolderEClass.getEStructuralFeatures().get(2);
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
	public EClass getUIMField() {
		return uimFieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUIMField_Control() {
		return (EReference)uimFieldEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUIMField_ControlKind() {
		return (EAttribute)uimFieldEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUIMField_LabelWidth() {
		return (EAttribute)uimFieldEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUIMField_Binding() {
		return (EReference)uimFieldEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUIMField_SecurityOnEditability() {
		return (EReference)uimFieldEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUIMNavigation() {
		return uimNavigationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUIMPanel() {
		return uimPanelEClass;
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
	public EReference getStateForm_State() {
		return (EReference)stateFormEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStateForm_Folder() {
		return (EReference)stateFormEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModelSecurityConstraint() {
		return modelSecurityConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModelSecurityConstraint_RequiredRoles() {
		return (EReference)modelSecurityConstraintEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelSecurityConstraint_RequiresGroupOwnership() {
		return (EAttribute)modelSecurityConstraintEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelSecurityConstraint_RequiresOwnership() {
		return (EAttribute)modelSecurityConstraintEClass.getEStructuralFeatures().get(2);
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
	public EReference getOperationInvocationForm_Operation() {
		return (EReference)operationInvocationFormEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOperationInvocationForm_Folder() {
		return (EReference)operationInvocationFormEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUIMComponent() {
		return uimComponentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUIMComponent_SecurityOnVisibility() {
		return (EReference)uimComponentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUIMComponent_Parent() {
		return (EReference)uimComponentEClass.getEStructuralFeatures().get(1);
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
	public EReference getOperationAction_Operation() {
		return (EReference)operationActionEClass.getEStructuralFeatures().get(0);
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
	public EClass getUIMControl() {
		return uimControlEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUIMControl_Width() {
		return (EAttribute)uimControlEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUIMControl_Field() {
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
	public EReference getTransitionAction_Transition() {
		return (EReference)transitionActionEClass.getEStructuralFeatures().get(0);
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
	public EReference getOperationTaskForm_Operation() {
		return (EReference)operationTaskFormEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOperationTaskForm_Folder() {
		return (EReference)operationTaskFormEClass.getEStructuralFeatures().get(1);
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
	public EReference getActionTaskForm_Action() {
		return (EReference)actionTaskFormEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActionTaskForm_Folder() {
		return (EReference)actionTaskFormEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUIMAction() {
		return uimActionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getChildSecurityConstraint() {
		return childSecurityConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getChildSecurityConstraint_InheritFromParent() {
		return (EAttribute)childSecurityConstraintEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUIMGridLayout() {
		return uimGridLayoutEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUIMGridLayout_NumberOfColumns() {
		return (EAttribute)uimGridLayoutEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUIMDataTable() {
		return uimDataTableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUIMDataTable_Binding() {
		return (EReference)uimDataTableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUIMBinding() {
		return uimBindingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUIMBinding_Next() {
		return (EReference)uimBindingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUIMBinding_Element() {
		return (EReference)uimBindingEClass.getEStructuralFeatures().get(1);
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
	public EReference getPropertyRef_Property() {
		return (EReference)propertyRefEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUIMDataColumn() {
		return uimDataColumnEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUIMDataColumn_Width() {
		return (EAttribute)uimDataColumnEClass.getEStructuralFeatures().get(0);
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
	public EReference getStateMachineFolder_StateForms() {
		return (EReference)stateMachineFolderEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStateMachineFolder_StateMachine() {
		return (EReference)stateMachineFolderEClass.getEStructuralFeatures().get(1);
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
	public EReference getEntityFolder_OperationTaskForms() {
		return (EReference)entityFolderEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEntityFolder_Entity() {
		return (EReference)entityFolderEClass.getEStructuralFeatures().get(1);
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
	public EReference getActivityFolder_ActionTaskForms() {
		return (EReference)activityFolderEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActivityFolder_Activity() {
		return (EReference)activityFolderEClass.getEStructuralFeatures().get(1);
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
	public EReference getOperationContainingFolder_OperationInvocationForms() {
		return (EReference)operationContainingFolderEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOperationContainingFolder_ClassForm() {
		return (EReference)operationContainingFolderEClass.getEStructuralFeatures().get(1);
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
	public EReference getPackageFolder_UmlPackage() {
		return (EReference)packageFolderEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUIMTabPanel() {
		return uimTabPanelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUIMTabPanel_ActiveTabIndex() {
		return (EAttribute)uimTabPanelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUIMTab() {
		return uimTabEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUIMCheckBox() {
		return uimCheckBoxEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUIMLookup() {
		return uimLookupEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUIMLookup_LookupSource() {
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
	public EClass getUIMText() {
		return uimTextEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUIMTextArea() {
		return uimTextAreaEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUIMTextArea_Rows() {
		return (EAttribute)uimTextAreaEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUIMDropdown() {
		return uimDropdownEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUIMDatePopup() {
		return uimDatePopupEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUIMSingleSelectListBox() {
		return uimSingleSelectListBoxEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUIMSingleSelectListBox_Rows() {
		return (EAttribute)uimSingleSelectListBoxEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUIMContainer() {
		return uimContainerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUIMContainer_SecurityOnEditability() {
		return (EReference)uimContainerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUIMContainer_Children() {
		return (EReference)uimContainerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUIMSingleSelectTreeView() {
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
	public EClass getUIMLayout() {
		return uimLayoutEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUIMToolbarLayout() {
		return uimToolbarLayoutEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUIMBorderLayout() {
		return uimBorderLayoutEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUIMBorderLayout_Horizontal() {
		return (EAttribute)uimBorderLayoutEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUIMXYLayout() {
		return uimxyLayoutEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUIMMultiSelectTreeView() {
		return uimMultiSelectTreeViewEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUIMMultiSelectListBox() {
		return uimMultiSelectListBoxEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUIMMultiSelectPopupSearch() {
		return uimMultiSelectPopupSearchEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUIMSingleSelectPopupSearch() {
		return uimSingleSelectPopupSearchEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUIMToggleButton() {
		return uimToggleButtonEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUIMNumberScroller() {
		return uimNumberScrollerEClass;
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
	public UIMFactory getUIMFactory() {
		return (UIMFactory)getEFactoryInstance();
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
		createEReference(userInteractionModelEClass, USER_INTERACTION_MODEL__UML_MODEL);
		createEReference(userInteractionModelEClass, USER_INTERACTION_MODEL__SECURITY_ON_VISIBILITY);
		createEReference(userInteractionModelEClass, USER_INTERACTION_MODEL__SECUIRYT_ON_EDITABILITY);

		abstractFormFolderEClass = createEClass(ABSTRACT_FORM_FOLDER);
		createEReference(abstractFormFolderEClass, ABSTRACT_FORM_FOLDER__PARENT);
		createEReference(abstractFormFolderEClass, ABSTRACT_FORM_FOLDER__SECURITY_ON_VISIBILITY);
		createEReference(abstractFormFolderEClass, ABSTRACT_FORM_FOLDER__SECURITY_ON_EDITABILITY);

		abstractFolderEClass = createEClass(ABSTRACT_FOLDER);
		createEReference(abstractFolderEClass, ABSTRACT_FOLDER__CHILDREN);

		uimFieldEClass = createEClass(UIM_FIELD);
		createEReference(uimFieldEClass, UIM_FIELD__CONTROL);
		createEAttribute(uimFieldEClass, UIM_FIELD__CONTROL_KIND);
		createEAttribute(uimFieldEClass, UIM_FIELD__LABEL_WIDTH);
		createEReference(uimFieldEClass, UIM_FIELD__BINDING);
		createEReference(uimFieldEClass, UIM_FIELD__SECURITY_ON_EDITABILITY);

		uimNavigationEClass = createEClass(UIM_NAVIGATION);

		uimPanelEClass = createEClass(UIM_PANEL);

		classFormEClass = createEClass(CLASS_FORM);
		createEReference(classFormEClass, CLASS_FORM__FOLDER);

		stateFormEClass = createEClass(STATE_FORM);
		createEReference(stateFormEClass, STATE_FORM__STATE);
		createEReference(stateFormEClass, STATE_FORM__FOLDER);

		modelSecurityConstraintEClass = createEClass(MODEL_SECURITY_CONSTRAINT);
		createEReference(modelSecurityConstraintEClass, MODEL_SECURITY_CONSTRAINT__REQUIRED_ROLES);
		createEAttribute(modelSecurityConstraintEClass, MODEL_SECURITY_CONSTRAINT__REQUIRES_GROUP_OWNERSHIP);
		createEAttribute(modelSecurityConstraintEClass, MODEL_SECURITY_CONSTRAINT__REQUIRES_OWNERSHIP);

		operationInvocationFormEClass = createEClass(OPERATION_INVOCATION_FORM);
		createEReference(operationInvocationFormEClass, OPERATION_INVOCATION_FORM__OPERATION);
		createEReference(operationInvocationFormEClass, OPERATION_INVOCATION_FORM__FOLDER);

		uimComponentEClass = createEClass(UIM_COMPONENT);
		createEReference(uimComponentEClass, UIM_COMPONENT__SECURITY_ON_VISIBILITY);
		createEReference(uimComponentEClass, UIM_COMPONENT__PARENT);

		userInteractionElementEClass = createEClass(USER_INTERACTION_ELEMENT);
		createEAttribute(userInteractionElementEClass, USER_INTERACTION_ELEMENT__NAME);

		operationActionEClass = createEClass(OPERATION_ACTION);
		createEReference(operationActionEClass, OPERATION_ACTION__OPERATION);

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
		createEReference(transitionActionEClass, TRANSITION_ACTION__TRANSITION);

		operationTaskFormEClass = createEClass(OPERATION_TASK_FORM);
		createEReference(operationTaskFormEClass, OPERATION_TASK_FORM__OPERATION);
		createEReference(operationTaskFormEClass, OPERATION_TASK_FORM__FOLDER);

		actionTaskFormEClass = createEClass(ACTION_TASK_FORM);
		createEReference(actionTaskFormEClass, ACTION_TASK_FORM__ACTION);
		createEReference(actionTaskFormEClass, ACTION_TASK_FORM__FOLDER);

		uimActionEClass = createEClass(UIM_ACTION);

		childSecurityConstraintEClass = createEClass(CHILD_SECURITY_CONSTRAINT);
		createEAttribute(childSecurityConstraintEClass, CHILD_SECURITY_CONSTRAINT__INHERIT_FROM_PARENT);

		uimGridLayoutEClass = createEClass(UIM_GRID_LAYOUT);
		createEAttribute(uimGridLayoutEClass, UIM_GRID_LAYOUT__NUMBER_OF_COLUMNS);

		uimDataTableEClass = createEClass(UIM_DATA_TABLE);
		createEReference(uimDataTableEClass, UIM_DATA_TABLE__BINDING);

		uimBindingEClass = createEClass(UIM_BINDING);
		createEReference(uimBindingEClass, UIM_BINDING__NEXT);
		createEReference(uimBindingEClass, UIM_BINDING__ELEMENT);

		propertyRefEClass = createEClass(PROPERTY_REF);
		createEReference(propertyRefEClass, PROPERTY_REF__BINDING);
		createEReference(propertyRefEClass, PROPERTY_REF__PREVIOUS);
		createEReference(propertyRefEClass, PROPERTY_REF__NEXT);
		createEReference(propertyRefEClass, PROPERTY_REF__PROPERTY);

		uimDataColumnEClass = createEClass(UIM_DATA_COLUMN);
		createEAttribute(uimDataColumnEClass, UIM_DATA_COLUMN__WIDTH);

		tableBindingEClass = createEClass(TABLE_BINDING);
		createEReference(tableBindingEClass, TABLE_BINDING__TABLE);

		fieldBindingEClass = createEClass(FIELD_BINDING);
		createEReference(fieldBindingEClass, FIELD_BINDING__FIELD);

		formPanelEClass = createEClass(FORM_PANEL);
		createEReference(formPanelEClass, FORM_PANEL__FORM);

		stateMachineFolderEClass = createEClass(STATE_MACHINE_FOLDER);
		createEReference(stateMachineFolderEClass, STATE_MACHINE_FOLDER__STATE_FORMS);
		createEReference(stateMachineFolderEClass, STATE_MACHINE_FOLDER__STATE_MACHINE);

		entityFolderEClass = createEClass(ENTITY_FOLDER);
		createEReference(entityFolderEClass, ENTITY_FOLDER__OPERATION_TASK_FORMS);
		createEReference(entityFolderEClass, ENTITY_FOLDER__ENTITY);

		activityFolderEClass = createEClass(ACTIVITY_FOLDER);
		createEReference(activityFolderEClass, ACTIVITY_FOLDER__ACTION_TASK_FORMS);
		createEReference(activityFolderEClass, ACTIVITY_FOLDER__ACTIVITY);

		operationContainingFolderEClass = createEClass(OPERATION_CONTAINING_FOLDER);
		createEReference(operationContainingFolderEClass, OPERATION_CONTAINING_FOLDER__OPERATION_INVOCATION_FORMS);
		createEReference(operationContainingFolderEClass, OPERATION_CONTAINING_FOLDER__CLASS_FORM);

		navigationBindingEClass = createEClass(NAVIGATION_BINDING);
		createEReference(navigationBindingEClass, NAVIGATION_BINDING__NAVIGATION);

		detailPanelEClass = createEClass(DETAIL_PANEL);
		createEReference(detailPanelEClass, DETAIL_PANEL__MASTER_COMPONENT);

		packageFolderEClass = createEClass(PACKAGE_FOLDER);
		createEReference(packageFolderEClass, PACKAGE_FOLDER__UML_PACKAGE);

		uimTabPanelEClass = createEClass(UIM_TAB_PANEL);
		createEAttribute(uimTabPanelEClass, UIM_TAB_PANEL__ACTIVE_TAB_INDEX);

		uimTabEClass = createEClass(UIM_TAB);

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
		createEReference(uimContainerEClass, UIM_CONTAINER__SECURITY_ON_EDITABILITY);
		createEReference(uimContainerEClass, UIM_CONTAINER__CHILDREN);

		uimSingleSelectTreeViewEClass = createEClass(UIM_SINGLE_SELECT_TREE_VIEW);

		masterComponentEClass = createEClass(MASTER_COMPONENT);
		createEReference(masterComponentEClass, MASTER_COMPONENT__DETAIL_PANELS);

		uimLayoutEClass = createEClass(UIM_LAYOUT);

		uimToolbarLayoutEClass = createEClass(UIM_TOOLBAR_LAYOUT);

		uimBorderLayoutEClass = createEClass(UIM_BORDER_LAYOUT);
		createEAttribute(uimBorderLayoutEClass, UIM_BORDER_LAYOUT__HORIZONTAL);

		uimxyLayoutEClass = createEClass(UIM_XY_LAYOUT);

		uimMultiSelectTreeViewEClass = createEClass(UIM_MULTI_SELECT_TREE_VIEW);

		uimMultiSelectListBoxEClass = createEClass(UIM_MULTI_SELECT_LIST_BOX);

		uimMultiSelectPopupSearchEClass = createEClass(UIM_MULTI_SELECT_POPUP_SEARCH);

		uimSingleSelectPopupSearchEClass = createEClass(UIM_SINGLE_SELECT_POPUP_SEARCH);

		uimToggleButtonEClass = createEClass(UIM_TOGGLE_BUTTON);

		uimNumberScrollerEClass = createEClass(UIM_NUMBER_SCROLLER);

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
		UMLPackage theUMLPackage = (UMLPackage)EPackage.Registry.INSTANCE.getEPackage(UMLPackage.eNS_URI);
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		uimFormEClass.getESuperTypes().add(this.getUserInteractionElement());
		userInteractionModelEClass.getESuperTypes().add(this.getAbstractFolder());
		abstractFormFolderEClass.getESuperTypes().add(this.getAbstractFolder());
		abstractFolderEClass.getESuperTypes().add(this.getUserInteractionElement());
		uimFieldEClass.getESuperTypes().add(this.getUIMComponent());
		uimNavigationEClass.getESuperTypes().add(this.getUIMComponent());
		uimPanelEClass.getESuperTypes().add(this.getUIMContainer());
		classFormEClass.getESuperTypes().add(this.getUIMForm());
		stateFormEClass.getESuperTypes().add(this.getUIMForm());
		operationInvocationFormEClass.getESuperTypes().add(this.getUIMForm());
		uimComponentEClass.getESuperTypes().add(this.getUserInteractionElement());
		operationActionEClass.getESuperTypes().add(this.getUIMAction());
		navigationToOperationEClass.getESuperTypes().add(this.getUIMNavigation());
		builtInActionEClass.getESuperTypes().add(this.getUIMAction());
		navigationToEntityEClass.getESuperTypes().add(this.getUIMNavigation());
		transitionActionEClass.getESuperTypes().add(this.getUIMAction());
		operationTaskFormEClass.getESuperTypes().add(this.getUIMForm());
		actionTaskFormEClass.getESuperTypes().add(this.getUIMForm());
		uimActionEClass.getESuperTypes().add(this.getUIMComponent());
		childSecurityConstraintEClass.getESuperTypes().add(this.getModelSecurityConstraint());
		uimGridLayoutEClass.getESuperTypes().add(this.getUIMLayout());
		uimDataTableEClass.getESuperTypes().add(this.getMasterComponent());
		uimDataTableEClass.getESuperTypes().add(this.getUIMContainer());
		uimDataColumnEClass.getESuperTypes().add(this.getUIMPanel());
		tableBindingEClass.getESuperTypes().add(this.getUIMBinding());
		fieldBindingEClass.getESuperTypes().add(this.getUIMBinding());
		formPanelEClass.getESuperTypes().add(this.getUIMContainer());
		stateMachineFolderEClass.getESuperTypes().add(this.getOperationContainingFolder());
		entityFolderEClass.getESuperTypes().add(this.getOperationContainingFolder());
		activityFolderEClass.getESuperTypes().add(this.getAbstractFormFolder());
		operationContainingFolderEClass.getESuperTypes().add(this.getAbstractFormFolder());
		navigationBindingEClass.getESuperTypes().add(this.getUIMBinding());
		detailPanelEClass.getESuperTypes().add(this.getUIMPanel());
		packageFolderEClass.getESuperTypes().add(this.getAbstractFormFolder());
		uimTabPanelEClass.getESuperTypes().add(this.getUIMContainer());
		uimTabEClass.getESuperTypes().add(this.getUIMPanel());
		uimCheckBoxEClass.getESuperTypes().add(this.getUIMControl());
		uimLookupEClass.getESuperTypes().add(this.getUIMControl());
		lookupBindingEClass.getESuperTypes().add(this.getUIMBinding());
		uimTextEClass.getESuperTypes().add(this.getUIMControl());
		uimTextAreaEClass.getESuperTypes().add(this.getUIMControl());
		uimDropdownEClass.getESuperTypes().add(this.getUIMLookup());
		uimDatePopupEClass.getESuperTypes().add(this.getUIMControl());
		uimSingleSelectListBoxEClass.getESuperTypes().add(this.getUIMLookup());
		uimContainerEClass.getESuperTypes().add(this.getUIMComponent());
		uimSingleSelectTreeViewEClass.getESuperTypes().add(this.getMasterComponent());
		uimSingleSelectTreeViewEClass.getESuperTypes().add(this.getUIMLookup());
		uimLayoutEClass.getESuperTypes().add(this.getUIMContainer());
		uimToolbarLayoutEClass.getESuperTypes().add(this.getUIMLayout());
		uimBorderLayoutEClass.getESuperTypes().add(this.getUIMLayout());
		uimxyLayoutEClass.getESuperTypes().add(this.getUIMLayout());
		uimMultiSelectTreeViewEClass.getESuperTypes().add(this.getUIMLookup());
		uimMultiSelectListBoxEClass.getESuperTypes().add(this.getUIMLookup());
		uimMultiSelectPopupSearchEClass.getESuperTypes().add(this.getUIMLookup());
		uimSingleSelectPopupSearchEClass.getESuperTypes().add(this.getUIMLookup());
		uimToggleButtonEClass.getESuperTypes().add(this.getUIMControl());
		uimNumberScrollerEClass.getESuperTypes().add(this.getUIMControl());

		// Initialize classes and features; add operations and parameters
		initEClass(uimFormEClass, UIMForm.class, "UIMForm", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUIMForm_Panel(), this.getFormPanel(), this.getFormPanel_Form(), "panel", null, 0, 1, UIMForm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(uimFormEClass, this.getAbstractFormFolder(), "getFolder", 1, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(userInteractionModelEClass, UserInteractionModel.class, "UserInteractionModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUserInteractionModel_UmlModel(), theUMLPackage.getModel(), null, "umlModel", null, 0, 1, UserInteractionModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUserInteractionModel_SecurityOnVisibility(), this.getModelSecurityConstraint(), null, "securityOnVisibility", null, 0, 1, UserInteractionModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUserInteractionModel_SecuirytOnEditability(), this.getModelSecurityConstraint(), null, "secuirytOnEditability", null, 0, 1, UserInteractionModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(abstractFormFolderEClass, AbstractFormFolder.class, "AbstractFormFolder", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAbstractFormFolder_Parent(), this.getAbstractFolder(), this.getAbstractFolder_Children(), "parent", null, 0, 1, AbstractFormFolder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAbstractFormFolder_SecurityOnVisibility(), this.getChildSecurityConstraint(), null, "securityOnVisibility", null, 0, 1, AbstractFormFolder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAbstractFormFolder_SecurityOnEditability(), this.getChildSecurityConstraint(), null, "securityOnEditability", null, 0, 1, AbstractFormFolder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(abstractFolderEClass, AbstractFolder.class, "AbstractFolder", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAbstractFolder_Children(), this.getAbstractFormFolder(), this.getAbstractFormFolder_Parent(), "children", null, 0, -1, AbstractFolder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimFieldEClass, UIMField.class, "UIMField", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUIMField_Control(), this.getUIMControl(), this.getUIMControl_Field(), "control", null, 1, 1, UIMField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUIMField_ControlKind(), this.getControlKind(), "controlKind", null, 0, 1, UIMField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUIMField_LabelWidth(), theUMLPackage.getInteger(), "labelWidth", null, 0, 1, UIMField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUIMField_Binding(), this.getFieldBinding(), this.getFieldBinding_Field(), "binding", null, 1, 1, UIMField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUIMField_SecurityOnEditability(), this.getChildSecurityConstraint(), null, "securityOnEditability", null, 0, 1, UIMField.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimNavigationEClass, UIMNavigation.class, "UIMNavigation", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimPanelEClass, UIMPanel.class, "UIMPanel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(classFormEClass, ClassForm.class, "ClassForm", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getClassForm_Folder(), this.getOperationContainingFolder(), this.getOperationContainingFolder_ClassForm(), "folder", null, 1, 1, ClassForm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stateFormEClass, StateForm.class, "StateForm", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStateForm_State(), theUMLPackage.getState(), null, "state", null, 0, 1, StateForm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStateForm_Folder(), this.getStateMachineFolder(), this.getStateMachineFolder_StateForms(), "folder", null, 1, 1, StateForm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(modelSecurityConstraintEClass, ModelSecurityConstraint.class, "ModelSecurityConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getModelSecurityConstraint_RequiredRoles(), theUMLPackage.getInterface(), null, "requiredRoles", null, 0, -1, ModelSecurityConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModelSecurityConstraint_RequiresGroupOwnership(), theEcorePackage.getEBoolean(), "requiresGroupOwnership", null, 0, 1, ModelSecurityConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModelSecurityConstraint_RequiresOwnership(), theEcorePackage.getEBoolean(), "requiresOwnership", null, 0, 1, ModelSecurityConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(operationInvocationFormEClass, OperationInvocationForm.class, "OperationInvocationForm", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOperationInvocationForm_Operation(), theUMLPackage.getOperation(), null, "operation", null, 0, 1, OperationInvocationForm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOperationInvocationForm_Folder(), this.getOperationContainingFolder(), this.getOperationContainingFolder_OperationInvocationForms(), "folder", null, 1, 1, OperationInvocationForm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimComponentEClass, UIMComponent.class, "UIMComponent", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUIMComponent_SecurityOnVisibility(), this.getChildSecurityConstraint(), null, "securityOnVisibility", null, 0, 1, UIMComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUIMComponent_Parent(), this.getUIMContainer(), this.getUIMContainer_Children(), "parent", null, 0, 1, UIMComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(userInteractionElementEClass, UserInteractionElement.class, "UserInteractionElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUserInteractionElement_Name(), theEcorePackage.getEString(), "name", null, 0, 1, UserInteractionElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(operationActionEClass, OperationAction.class, "OperationAction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOperationAction_Operation(), theUMLPackage.getOperation(), null, "operation", null, 0, 1, OperationAction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(navigationToOperationEClass, NavigationToOperation.class, "NavigationToOperation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getNavigationToOperation_ToForm(), this.getOperationInvocationForm(), null, "toForm", null, 0, 1, NavigationToOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(builtInActionEClass, BuiltInAction.class, "BuiltInAction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBuiltInAction_Kind(), this.getActionKind(), "kind", null, 0, 1, BuiltInAction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimControlEClass, UIMControl.class, "UIMControl", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUIMControl_Width(), theUMLPackage.getString(), "width", null, 0, 1, UIMControl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUIMControl_Field(), this.getUIMField(), this.getUIMField_Control(), "field", null, 0, 1, UIMControl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(navigationToEntityEClass, NavigationToEntity.class, "NavigationToEntity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getNavigationToEntity_ToForm(), this.getClassForm(), null, "toForm", null, 0, 1, NavigationToEntity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNavigationToEntity_Binding(), this.getNavigationBinding(), this.getNavigationBinding_Navigation(), "binding", null, 0, 1, NavigationToEntity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(transitionActionEClass, TransitionAction.class, "TransitionAction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTransitionAction_Transition(), theUMLPackage.getTransition(), null, "transition", null, 0, 1, TransitionAction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(operationTaskFormEClass, OperationTaskForm.class, "OperationTaskForm", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOperationTaskForm_Operation(), theUMLPackage.getOperation(), null, "operation", null, 0, 1, OperationTaskForm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOperationTaskForm_Folder(), this.getEntityFolder(), this.getEntityFolder_OperationTaskForms(), "folder", null, 1, 1, OperationTaskForm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(actionTaskFormEClass, ActionTaskForm.class, "ActionTaskForm", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getActionTaskForm_Action(), theUMLPackage.getOpaqueAction(), null, "action", null, 0, 1, ActionTaskForm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActionTaskForm_Folder(), this.getActivityFolder(), this.getActivityFolder_ActionTaskForms(), "folder", null, 0, 1, ActionTaskForm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimActionEClass, UIMAction.class, "UIMAction", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(childSecurityConstraintEClass, ChildSecurityConstraint.class, "ChildSecurityConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getChildSecurityConstraint_InheritFromParent(), theEcorePackage.getEBoolean(), "inheritFromParent", "true", 0, 1, ChildSecurityConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimGridLayoutEClass, UIMGridLayout.class, "UIMGridLayout", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUIMGridLayout_NumberOfColumns(), theUMLPackage.getInteger(), "numberOfColumns", null, 0, 1, UIMGridLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimDataTableEClass, UIMDataTable.class, "UIMDataTable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUIMDataTable_Binding(), this.getTableBinding(), this.getTableBinding_Table(), "binding", null, 0, 1, UIMDataTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimBindingEClass, UIMBinding.class, "UIMBinding", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUIMBinding_Next(), this.getPropertyRef(), this.getPropertyRef_Binding(), "next", null, 0, 1, UIMBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUIMBinding_Element(), theUMLPackage.getTypedElement(), null, "element", null, 0, 1, UIMBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(propertyRefEClass, PropertyRef.class, "PropertyRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPropertyRef_Binding(), this.getUIMBinding(), this.getUIMBinding_Next(), "binding", null, 0, 1, PropertyRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPropertyRef_Previous(), this.getPropertyRef(), this.getPropertyRef_Next(), "previous", null, 0, 1, PropertyRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPropertyRef_Next(), this.getPropertyRef(), this.getPropertyRef_Previous(), "next", null, 0, 1, PropertyRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPropertyRef_Property(), theUMLPackage.getProperty(), null, "property", null, 0, 1, PropertyRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimDataColumnEClass, UIMDataColumn.class, "UIMDataColumn", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUIMDataColumn_Width(), theUMLPackage.getInteger(), "width", null, 0, 1, UIMDataColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tableBindingEClass, TableBinding.class, "TableBinding", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTableBinding_Table(), this.getUIMDataTable(), this.getUIMDataTable_Binding(), "table", null, 1, 1, TableBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(fieldBindingEClass, FieldBinding.class, "FieldBinding", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFieldBinding_Field(), this.getUIMField(), this.getUIMField_Binding(), "field", null, 0, 1, FieldBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(formPanelEClass, FormPanel.class, "FormPanel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFormPanel_Form(), this.getUIMForm(), this.getUIMForm_Panel(), "form", null, 0, 1, FormPanel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stateMachineFolderEClass, StateMachineFolder.class, "StateMachineFolder", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStateMachineFolder_StateForms(), this.getStateForm(), this.getStateForm_Folder(), "stateForms", null, 0, -1, StateMachineFolder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStateMachineFolder_StateMachine(), theUMLPackage.getStateMachine(), null, "stateMachine", null, 0, 1, StateMachineFolder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(entityFolderEClass, EntityFolder.class, "EntityFolder", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEntityFolder_OperationTaskForms(), this.getOperationTaskForm(), this.getOperationTaskForm_Folder(), "operationTaskForms", null, 0, -1, EntityFolder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEntityFolder_Entity(), theUMLPackage.getClass_(), null, "entity", null, 0, 1, EntityFolder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(activityFolderEClass, ActivityFolder.class, "ActivityFolder", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getActivityFolder_ActionTaskForms(), this.getActionTaskForm(), this.getActionTaskForm_Folder(), "actionTaskForms", null, 0, -1, ActivityFolder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActivityFolder_Activity(), theUMLPackage.getActivity(), null, "activity", null, 0, 1, ActivityFolder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(operationContainingFolderEClass, OperationContainingFolder.class, "OperationContainingFolder", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOperationContainingFolder_OperationInvocationForms(), this.getOperationInvocationForm(), this.getOperationInvocationForm_Folder(), "operationInvocationForms", null, 0, -1, OperationContainingFolder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOperationContainingFolder_ClassForm(), this.getClassForm(), this.getClassForm_Folder(), "classForm", null, 0, -1, OperationContainingFolder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(operationContainingFolderEClass, theUMLPackage.getClass_(), "getRepresentedClass", 1, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(navigationBindingEClass, NavigationBinding.class, "NavigationBinding", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getNavigationBinding_Navigation(), this.getNavigationToEntity(), this.getNavigationToEntity_Binding(), "navigation", null, 1, 1, NavigationBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(detailPanelEClass, DetailPanel.class, "DetailPanel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDetailPanel_MasterComponent(), this.getMasterComponent(), this.getMasterComponent_DetailPanels(), "masterComponent", null, 0, 1, DetailPanel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(packageFolderEClass, PackageFolder.class, "PackageFolder", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPackageFolder_UmlPackage(), theUMLPackage.getPackage(), null, "umlPackage", null, 0, 1, PackageFolder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimTabPanelEClass, UIMTabPanel.class, "UIMTabPanel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUIMTabPanel_ActiveTabIndex(), theUMLPackage.getInteger(), "activeTabIndex", null, 0, 1, UIMTabPanel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimTabEClass, UIMTab.class, "UIMTab", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimCheckBoxEClass, UIMCheckBox.class, "UIMCheckBox", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimLookupEClass, UIMLookup.class, "UIMLookup", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUIMLookup_LookupSource(), this.getLookupBinding(), this.getLookupBinding_Lookup(), "lookupSource", null, 0, 1, UIMLookup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(lookupBindingEClass, LookupBinding.class, "LookupBinding", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getLookupBinding_Lookup(), this.getUIMLookup(), this.getUIMLookup_LookupSource(), "lookup", null, 0, 1, LookupBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimTextEClass, UIMText.class, "UIMText", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimTextAreaEClass, UIMTextArea.class, "UIMTextArea", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUIMTextArea_Rows(), theUMLPackage.getInteger(), "rows", null, 0, 1, UIMTextArea.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimDropdownEClass, UIMDropdown.class, "UIMDropdown", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimDatePopupEClass, UIMDatePopup.class, "UIMDatePopup", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimSingleSelectListBoxEClass, UIMSingleSelectListBox.class, "UIMSingleSelectListBox", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUIMSingleSelectListBox_Rows(), theUMLPackage.getInteger(), "rows", null, 0, 1, UIMSingleSelectListBox.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimContainerEClass, UIMContainer.class, "UIMContainer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUIMContainer_SecurityOnEditability(), this.getChildSecurityConstraint(), null, "securityOnEditability", null, 0, 1, UIMContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUIMContainer_Children(), this.getUIMComponent(), this.getUIMComponent_Parent(), "children", null, 0, -1, UIMContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimSingleSelectTreeViewEClass, UIMSingleSelectTreeView.class, "UIMSingleSelectTreeView", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(masterComponentEClass, MasterComponent.class, "MasterComponent", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMasterComponent_DetailPanels(), this.getDetailPanel(), this.getDetailPanel_MasterComponent(), "detailPanels", null, 0, -1, MasterComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimLayoutEClass, UIMLayout.class, "UIMLayout", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimToolbarLayoutEClass, UIMToolbarLayout.class, "UIMToolbarLayout", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimBorderLayoutEClass, UIMBorderLayout.class, "UIMBorderLayout", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUIMBorderLayout_Horizontal(), theUMLPackage.getBoolean(), "horizontal", null, 0, 1, UIMBorderLayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uimxyLayoutEClass, UIMXYLayout.class, "UIMXYLayout", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimMultiSelectTreeViewEClass, UIMMultiSelectTreeView.class, "UIMMultiSelectTreeView", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimMultiSelectListBoxEClass, UIMMultiSelectListBox.class, "UIMMultiSelectListBox", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimMultiSelectPopupSearchEClass, UIMMultiSelectPopupSearch.class, "UIMMultiSelectPopupSearch", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimSingleSelectPopupSearchEClass, UIMSingleSelectPopupSearch.class, "UIMSingleSelectPopupSearch", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimToggleButtonEClass, UIMToggleButton.class, "UIMToggleButton", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(uimNumberScrollerEClass, UIMNumberScroller.class, "UIMNumberScroller", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

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

} //UIMPackageImpl
