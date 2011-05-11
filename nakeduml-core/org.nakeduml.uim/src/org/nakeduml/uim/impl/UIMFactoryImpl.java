/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim.impl;


import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.nakeduml.uim.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class UIMFactoryImpl extends EFactoryImpl implements UIMFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static UIMFactory init() {
		try {
			UIMFactory theUIMFactory = (UIMFactory)EPackage.Registry.INSTANCE.getEFactory("http://nakeduml.org/uimetamodel/1.0"); 
			if (theUIMFactory != null) {
				return theUIMFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new UIMFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UIMFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case UIMPackage.USER_INTERACTION_MODEL: return createUserInteractionModel();
			case UIMPackage.UIM_FIELD: return createUIMField();
			case UIMPackage.UIM_PANEL: return createUIMPanel();
			case UIMPackage.CLASS_FORM: return createClassForm();
			case UIMPackage.STATE_FORM: return createStateForm();
			case UIMPackage.MODEL_SECURITY_CONSTRAINT: return createModelSecurityConstraint();
			case UIMPackage.OPERATION_INVOCATION_FORM: return createOperationInvocationForm();
			case UIMPackage.OPERATION_ACTION: return createOperationAction();
			case UIMPackage.NAVIGATION_TO_OPERATION: return createNavigationToOperation();
			case UIMPackage.BUILT_IN_ACTION: return createBuiltInAction();
			case UIMPackage.UIM_CONTROL: return createUIMControl();
			case UIMPackage.NAVIGATION_TO_ENTITY: return createNavigationToEntity();
			case UIMPackage.TRANSITION_ACTION: return createTransitionAction();
			case UIMPackage.OPERATION_TASK_FORM: return createOperationTaskForm();
			case UIMPackage.ACTION_TASK_FORM: return createActionTaskForm();
			case UIMPackage.CHILD_SECURITY_CONSTRAINT: return createChildSecurityConstraint();
			case UIMPackage.UIM_GRID_LAYOUT: return createUIMGridLayout();
			case UIMPackage.UIM_DATA_TABLE: return createUIMDataTable();
			case UIMPackage.PROPERTY_REF: return createPropertyRef();
			case UIMPackage.UIM_DATA_COLUMN: return createUIMDataColumn();
			case UIMPackage.TABLE_BINDING: return createTableBinding();
			case UIMPackage.FIELD_BINDING: return createFieldBinding();
			case UIMPackage.FORM_PANEL: return createFormPanel();
			case UIMPackage.STATE_MACHINE_FOLDER: return createStateMachineFolder();
			case UIMPackage.ENTITY_FOLDER: return createEntityFolder();
			case UIMPackage.ACTIVITY_FOLDER: return createActivityFolder();
			case UIMPackage.NAVIGATION_BINDING: return createNavigationBinding();
			case UIMPackage.DETAIL_PANEL: return createDetailPanel();
			case UIMPackage.PACKAGE_FOLDER: return createPackageFolder();
			case UIMPackage.UIM_TAB_PANEL: return createUIMTabPanel();
			case UIMPackage.UIM_TAB: return createUIMTab();
			case UIMPackage.UIM_CHECK_BOX: return createUIMCheckBox();
			case UIMPackage.UIM_LOOKUP: return createUIMLookup();
			case UIMPackage.LOOKUP_BINDING: return createLookupBinding();
			case UIMPackage.UIM_TEXT: return createUIMText();
			case UIMPackage.UIM_TEXT_AREA: return createUIMTextArea();
			case UIMPackage.UIM_DROPDOWN: return createUIMDropdown();
			case UIMPackage.UIM_DATE_POPUP: return createUIMDatePopup();
			case UIMPackage.UIM_SINGLE_SELECT_LIST_BOX: return createUIMSingleSelectListBox();
			case UIMPackage.UIM_CONTAINER: return createUIMContainer();
			case UIMPackage.UIM_SINGLE_SELECT_TREE_VIEW: return createUIMSingleSelectTreeView();
			case UIMPackage.UIM_LAYOUT: return createUIMLayout();
			case UIMPackage.UIM_TOOLBAR_LAYOUT: return createUIMToolbarLayout();
			case UIMPackage.UIM_BORDER_LAYOUT: return createUIMBorderLayout();
			case UIMPackage.UIM_XY_LAYOUT: return createUIMXYLayout();
			case UIMPackage.UIM_MULTI_SELECT_TREE_VIEW: return createUIMMultiSelectTreeView();
			case UIMPackage.UIM_MULTI_SELECT_LIST_BOX: return createUIMMultiSelectListBox();
			case UIMPackage.UIM_MULTI_SELECT_POPUP_SEARCH: return createUIMMultiSelectPopupSearch();
			case UIMPackage.UIM_SINGLE_SELECT_POPUP_SEARCH: return createUIMSingleSelectPopupSearch();
			case UIMPackage.UIM_TOGGLE_BUTTON: return createUIMToggleButton();
			case UIMPackage.UIM_NUMBER_SCROLLER: return createUIMNumberScroller();
			case UIMPackage.UML_REFERENCE: return createUmlReference();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case UIMPackage.ACTION_KIND:
				return createActionKindFromString(eDataType, initialValue);
			case UIMPackage.CONTROL_KIND:
				return createControlKindFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case UIMPackage.ACTION_KIND:
				return convertActionKindToString(eDataType, instanceValue);
			case UIMPackage.CONTROL_KIND:
				return convertControlKindToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UserInteractionModel createUserInteractionModel() {
		UserInteractionModelImpl userInteractionModel = new UserInteractionModelImpl();
		return userInteractionModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UIMField createUIMField() {
		UIMFieldImpl uimField = new UIMFieldImpl();
		return uimField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UIMPanel createUIMPanel() {
		UIMPanelImpl uimPanel = new UIMPanelImpl();
		return uimPanel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassForm createClassForm() {
		ClassFormImpl classForm = new ClassFormImpl();
		return classForm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StateForm createStateForm() {
		StateFormImpl stateForm = new StateFormImpl();
		return stateForm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelSecurityConstraint createModelSecurityConstraint() {
		ModelSecurityConstraintImpl modelSecurityConstraint = new ModelSecurityConstraintImpl();
		return modelSecurityConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationInvocationForm createOperationInvocationForm() {
		OperationInvocationFormImpl operationInvocationForm = new OperationInvocationFormImpl();
		return operationInvocationForm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationAction createOperationAction() {
		OperationActionImpl operationAction = new OperationActionImpl();
		return operationAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NavigationToOperation createNavigationToOperation() {
		NavigationToOperationImpl navigationToOperation = new NavigationToOperationImpl();
		return navigationToOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BuiltInAction createBuiltInAction() {
		BuiltInActionImpl builtInAction = new BuiltInActionImpl();
		return builtInAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UIMControl createUIMControl() {
		UIMControlImpl uimControl = new UIMControlImpl();
		return uimControl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NavigationToEntity createNavigationToEntity() {
		NavigationToEntityImpl navigationToEntity = new NavigationToEntityImpl();
		return navigationToEntity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransitionAction createTransitionAction() {
		TransitionActionImpl transitionAction = new TransitionActionImpl();
		return transitionAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationTaskForm createOperationTaskForm() {
		OperationTaskFormImpl operationTaskForm = new OperationTaskFormImpl();
		return operationTaskForm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActionTaskForm createActionTaskForm() {
		ActionTaskFormImpl actionTaskForm = new ActionTaskFormImpl();
		return actionTaskForm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ChildSecurityConstraint createChildSecurityConstraint() {
		ChildSecurityConstraintImpl childSecurityConstraint = new ChildSecurityConstraintImpl();
		return childSecurityConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UIMGridLayout createUIMGridLayout() {
		UIMGridLayoutImpl uimGridLayout = new UIMGridLayoutImpl();
		return uimGridLayout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UIMDataTable createUIMDataTable() {
		UIMDataTableImpl uimDataTable = new UIMDataTableImpl();
		return uimDataTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertyRef createPropertyRef() {
		PropertyRefImpl propertyRef = new PropertyRefImpl();
		return propertyRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UIMDataColumn createUIMDataColumn() {
		UIMDataColumnImpl uimDataColumn = new UIMDataColumnImpl();
		return uimDataColumn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TableBinding createTableBinding() {
		TableBindingImpl tableBinding = new TableBindingImpl();
		return tableBinding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FieldBinding createFieldBinding() {
		FieldBindingImpl fieldBinding = new FieldBindingImpl();
		return fieldBinding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormPanel createFormPanel() {
		FormPanelImpl formPanel = new FormPanelImpl();
		return formPanel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StateMachineFolder createStateMachineFolder() {
		StateMachineFolderImpl stateMachineFolder = new StateMachineFolderImpl();
		return stateMachineFolder;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EntityFolder createEntityFolder() {
		EntityFolderImpl entityFolder = new EntityFolderImpl();
		return entityFolder;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityFolder createActivityFolder() {
		ActivityFolderImpl activityFolder = new ActivityFolderImpl();
		return activityFolder;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NavigationBinding createNavigationBinding() {
		NavigationBindingImpl navigationBinding = new NavigationBindingImpl();
		return navigationBinding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DetailPanel createDetailPanel() {
		DetailPanelImpl detailPanel = new DetailPanelImpl();
		return detailPanel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PackageFolder createPackageFolder() {
		PackageFolderImpl packageFolder = new PackageFolderImpl();
		return packageFolder;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UIMTabPanel createUIMTabPanel() {
		UIMTabPanelImpl uimTabPanel = new UIMTabPanelImpl();
		return uimTabPanel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UIMTab createUIMTab() {
		UIMTabImpl uimTab = new UIMTabImpl();
		return uimTab;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UIMCheckBox createUIMCheckBox() {
		UIMCheckBoxImpl uimCheckBox = new UIMCheckBoxImpl();
		return uimCheckBox;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UIMLookup createUIMLookup() {
		UIMLookupImpl uimLookup = new UIMLookupImpl();
		return uimLookup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LookupBinding createLookupBinding() {
		LookupBindingImpl lookupBinding = new LookupBindingImpl();
		return lookupBinding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UIMText createUIMText() {
		UIMTextImpl uimText = new UIMTextImpl();
		return uimText;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UIMTextArea createUIMTextArea() {
		UIMTextAreaImpl uimTextArea = new UIMTextAreaImpl();
		return uimTextArea;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UIMDropdown createUIMDropdown() {
		UIMDropdownImpl uimDropdown = new UIMDropdownImpl();
		return uimDropdown;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UIMDatePopup createUIMDatePopup() {
		UIMDatePopupImpl uimDatePopup = new UIMDatePopupImpl();
		return uimDatePopup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UIMSingleSelectListBox createUIMSingleSelectListBox() {
		UIMSingleSelectListBoxImpl uimSingleSelectListBox = new UIMSingleSelectListBoxImpl();
		return uimSingleSelectListBox;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UIMContainer createUIMContainer() {
		UIMContainerImpl uimContainer = new UIMContainerImpl();
		return uimContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UIMSingleSelectTreeView createUIMSingleSelectTreeView() {
		UIMSingleSelectTreeViewImpl uimSingleSelectTreeView = new UIMSingleSelectTreeViewImpl();
		return uimSingleSelectTreeView;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UIMLayout createUIMLayout() {
		UIMLayoutImpl uimLayout = new UIMLayoutImpl();
		return uimLayout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UIMToolbarLayout createUIMToolbarLayout() {
		UIMToolbarLayoutImpl uimToolbarLayout = new UIMToolbarLayoutImpl();
		return uimToolbarLayout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UIMBorderLayout createUIMBorderLayout() {
		UIMBorderLayoutImpl uimBorderLayout = new UIMBorderLayoutImpl();
		return uimBorderLayout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UIMXYLayout createUIMXYLayout() {
		UIMXYLayoutImpl uimxyLayout = new UIMXYLayoutImpl();
		return uimxyLayout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UIMMultiSelectTreeView createUIMMultiSelectTreeView() {
		UIMMultiSelectTreeViewImpl uimMultiSelectTreeView = new UIMMultiSelectTreeViewImpl();
		return uimMultiSelectTreeView;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UIMMultiSelectListBox createUIMMultiSelectListBox() {
		UIMMultiSelectListBoxImpl uimMultiSelectListBox = new UIMMultiSelectListBoxImpl();
		return uimMultiSelectListBox;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UIMMultiSelectPopupSearch createUIMMultiSelectPopupSearch() {
		UIMMultiSelectPopupSearchImpl uimMultiSelectPopupSearch = new UIMMultiSelectPopupSearchImpl();
		return uimMultiSelectPopupSearch;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UIMSingleSelectPopupSearch createUIMSingleSelectPopupSearch() {
		UIMSingleSelectPopupSearchImpl uimSingleSelectPopupSearch = new UIMSingleSelectPopupSearchImpl();
		return uimSingleSelectPopupSearch;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UIMToggleButton createUIMToggleButton() {
		UIMToggleButtonImpl uimToggleButton = new UIMToggleButtonImpl();
		return uimToggleButton;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UIMNumberScroller createUIMNumberScroller() {
		UIMNumberScrollerImpl uimNumberScroller = new UIMNumberScrollerImpl();
		return uimNumberScroller;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UmlReference createUmlReference() {
		UmlReferenceImpl umlReference = new UmlReferenceImpl();
		return umlReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActionKind createActionKindFromString(EDataType eDataType, String initialValue) {
		ActionKind result = ActionKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertActionKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ControlKind createControlKindFromString(EDataType eDataType, String initialValue) {
		ControlKind result = ControlKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertControlKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UIMPackage getUIMPackage() {
		return (UIMPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static UIMPackage getPackage() {
		return UIMPackage.eINSTANCE;
	}

} //UIMFactoryImpl
