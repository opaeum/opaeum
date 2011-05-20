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
import org.nakeduml.uim.NavigationBinding;
import org.nakeduml.uim.NavigationToEntity;
import org.nakeduml.uim.NavigationToOperation;
import org.nakeduml.uim.OperationAction;
import org.nakeduml.uim.OperationInvocationForm;
import org.nakeduml.uim.OperationTaskForm;
import org.nakeduml.uim.PackageFolder;
import org.nakeduml.uim.PropertyRef;
import org.nakeduml.uim.RequiredRole;
import org.nakeduml.uim.SecureObject;
import org.nakeduml.uim.SecurityConstraint;
import org.nakeduml.uim.StateForm;
import org.nakeduml.uim.StateMachineFolder;
import org.nakeduml.uim.TableBinding;
import org.nakeduml.uim.TransitionAction;
import org.nakeduml.uim.UimBorderLayout;
import org.nakeduml.uim.UimCheckBox;
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
import org.nakeduml.uim.UimNumberScroller;
import org.nakeduml.uim.UimPackage;
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
import org.nakeduml.uim.UserInteractionModel;
import org.nakeduml.uim.UserInteractionWorkspace;
import org.nakeduml.uim.WorkspaceSecurityConstraint;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class UimFactoryImpl extends EFactoryImpl implements UimFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static UimFactory init() {
		try {
			UimFactory theUimFactory = (UimFactory)EPackage.Registry.INSTANCE.getEFactory("http://nakeduml.org/uimetamodel/1.0"); 
			if (theUimFactory != null) {
				return theUimFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new UimFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimFactoryImpl() {
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
			case UimPackage.UIM_FORM: return createUimForm();
			case UimPackage.USER_INTERACTION_MODEL: return createUserInteractionModel();
			case UimPackage.UIM_FIELD: return createUimField();
			case UimPackage.CLASS_FORM: return createClassForm();
			case UimPackage.STATE_FORM: return createStateForm();
			case UimPackage.WORKSPACE_SECURITY_CONSTRAINT: return createWorkspaceSecurityConstraint();
			case UimPackage.OPERATION_INVOCATION_FORM: return createOperationInvocationForm();
			case UimPackage.OPERATION_ACTION: return createOperationAction();
			case UimPackage.NAVIGATION_TO_OPERATION: return createNavigationToOperation();
			case UimPackage.BUILT_IN_ACTION: return createBuiltInAction();
			case UimPackage.UIM_CONTROL: return createUimControl();
			case UimPackage.NAVIGATION_TO_ENTITY: return createNavigationToEntity();
			case UimPackage.TRANSITION_ACTION: return createTransitionAction();
			case UimPackage.OPERATION_TASK_FORM: return createOperationTaskForm();
			case UimPackage.ACTION_TASK_FORM: return createActionTaskForm();
			case UimPackage.SECURITY_CONSTRAINT: return createSecurityConstraint();
			case UimPackage.UIM_GRID_LAYOUT: return createUimGridLayout();
			case UimPackage.UIM_DATA_TABLE: return createUimDataTable();
			case UimPackage.PROPERTY_REF: return createPropertyRef();
			case UimPackage.UIM_DATA_COLUMN: return createUimDataColumn();
			case UimPackage.TABLE_BINDING: return createTableBinding();
			case UimPackage.FIELD_BINDING: return createFieldBinding();
			case UimPackage.FORM_PANEL: return createFormPanel();
			case UimPackage.STATE_MACHINE_FOLDER: return createStateMachineFolder();
			case UimPackage.ENTITY_FOLDER: return createEntityFolder();
			case UimPackage.ACTIVITY_FOLDER: return createActivityFolder();
			case UimPackage.NAVIGATION_BINDING: return createNavigationBinding();
			case UimPackage.DETAIL_PANEL: return createDetailPanel();
			case UimPackage.PACKAGE_FOLDER: return createPackageFolder();
			case UimPackage.UIM_TAB_PANEL: return createUimTabPanel();
			case UimPackage.UIM_TAB: return createUimTab();
			case UimPackage.UIM_CHECK_BOX: return createUimCheckBox();
			case UimPackage.UIM_LOOKUP: return createUimLookup();
			case UimPackage.LOOKUP_BINDING: return createLookupBinding();
			case UimPackage.UIM_TEXT: return createUimText();
			case UimPackage.UIM_TEXT_AREA: return createUimTextArea();
			case UimPackage.UIM_DROPDOWN: return createUimDropdown();
			case UimPackage.UIM_DATE_POPUP: return createUimDatePopup();
			case UimPackage.UIM_SINGLE_SELECT_LIST_BOX: return createUimSingleSelectListBox();
			case UimPackage.UIM_CONTAINER: return createUimContainer();
			case UimPackage.UIM_SINGLE_SELECT_TREE_VIEW: return createUimSingleSelectTreeView();
			case UimPackage.UIM_LAYOUT: return createUimLayout();
			case UimPackage.UIM_TOOLBAR_LAYOUT: return createUimToolbarLayout();
			case UimPackage.UIM_BORDER_LAYOUT: return createUimBorderLayout();
			case UimPackage.UIM_XY_LAYOUT: return createUimXYLayout();
			case UimPackage.UIM_MULTI_SELECT_TREE_VIEW: return createUimMultiSelectTreeView();
			case UimPackage.UIM_MULTI_SELECT_LIST_BOX: return createUimMultiSelectListBox();
			case UimPackage.UIM_MULTI_SELECT_POPUP_SEARCH: return createUimMultiSelectPopupSearch();
			case UimPackage.UIM_SINGLE_SELECT_POPUP_SEARCH: return createUimSingleSelectPopupSearch();
			case UimPackage.UIM_TOGGLE_BUTTON: return createUimToggleButton();
			case UimPackage.UIM_NUMBER_SCROLLER: return createUimNumberScroller();
			case UimPackage.UML_REFERENCE: return createUmlReference();
			case UimPackage.USER_INTERACTION_WORKSPACE: return createUserInteractionWorkspace();
			case UimPackage.REQUIRED_ROLE: return createRequiredRole();
			case UimPackage.SECURE_OBJECT: return createSecureObject();
			case UimPackage.EDITABLE_SECURE_OBJECT: return createEditableSecureObject();
			case UimPackage.UIM_FULL_LAYOUT: return createUimFullLayout();
			case UimPackage.UIM_PANEL: return createUimPanel();
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
			case UimPackage.ACTION_KIND:
				return createActionKindFromString(eDataType, initialValue);
			case UimPackage.CONTROL_KIND:
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
			case UimPackage.ACTION_KIND:
				return convertActionKindToString(eDataType, instanceValue);
			case UimPackage.CONTROL_KIND:
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
	public UimForm createUimForm() {
		UimFormImpl uimForm = new UimFormImpl();
		return uimForm;
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
	public UimField createUimField() {
		UimFieldImpl uimField = new UimFieldImpl();
		return uimField;
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
	public WorkspaceSecurityConstraint createWorkspaceSecurityConstraint() {
		WorkspaceSecurityConstraintImpl workspaceSecurityConstraint = new WorkspaceSecurityConstraintImpl();
		return workspaceSecurityConstraint;
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
	public UimControl createUimControl() {
		UimControlImpl uimControl = new UimControlImpl();
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
	public SecurityConstraint createSecurityConstraint() {
		SecurityConstraintImpl securityConstraint = new SecurityConstraintImpl();
		return securityConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimGridLayout createUimGridLayout() {
		UimGridLayoutImpl uimGridLayout = new UimGridLayoutImpl();
		return uimGridLayout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimDataTable createUimDataTable() {
		UimDataTableImpl uimDataTable = new UimDataTableImpl();
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
	public UimDataColumn createUimDataColumn() {
		UimDataColumnImpl uimDataColumn = new UimDataColumnImpl();
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
	public UimTabPanel createUimTabPanel() {
		UimTabPanelImpl uimTabPanel = new UimTabPanelImpl();
		return uimTabPanel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimTab createUimTab() {
		UimTabImpl uimTab = new UimTabImpl();
		return uimTab;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimCheckBox createUimCheckBox() {
		UimCheckBoxImpl uimCheckBox = new UimCheckBoxImpl();
		return uimCheckBox;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimLookup createUimLookup() {
		UimLookupImpl uimLookup = new UimLookupImpl();
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
	public UimText createUimText() {
		UimTextImpl uimText = new UimTextImpl();
		return uimText;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimTextArea createUimTextArea() {
		UimTextAreaImpl uimTextArea = new UimTextAreaImpl();
		return uimTextArea;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimDropdown createUimDropdown() {
		UimDropdownImpl uimDropdown = new UimDropdownImpl();
		return uimDropdown;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimDatePopup createUimDatePopup() {
		UimDatePopupImpl uimDatePopup = new UimDatePopupImpl();
		return uimDatePopup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimSingleSelectListBox createUimSingleSelectListBox() {
		UimSingleSelectListBoxImpl uimSingleSelectListBox = new UimSingleSelectListBoxImpl();
		return uimSingleSelectListBox;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimContainer createUimContainer() {
		UimContainerImpl uimContainer = new UimContainerImpl();
		return uimContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimSingleSelectTreeView createUimSingleSelectTreeView() {
		UimSingleSelectTreeViewImpl uimSingleSelectTreeView = new UimSingleSelectTreeViewImpl();
		return uimSingleSelectTreeView;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimLayout createUimLayout() {
		UimLayoutImpl uimLayout = new UimLayoutImpl();
		return uimLayout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimToolbarLayout createUimToolbarLayout() {
		UimToolbarLayoutImpl uimToolbarLayout = new UimToolbarLayoutImpl();
		return uimToolbarLayout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimBorderLayout createUimBorderLayout() {
		UimBorderLayoutImpl uimBorderLayout = new UimBorderLayoutImpl();
		return uimBorderLayout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimXYLayout createUimXYLayout() {
		UimXYLayoutImpl uimXYLayout = new UimXYLayoutImpl();
		return uimXYLayout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimMultiSelectTreeView createUimMultiSelectTreeView() {
		UimMultiSelectTreeViewImpl uimMultiSelectTreeView = new UimMultiSelectTreeViewImpl();
		return uimMultiSelectTreeView;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimMultiSelectListBox createUimMultiSelectListBox() {
		UimMultiSelectListBoxImpl uimMultiSelectListBox = new UimMultiSelectListBoxImpl();
		return uimMultiSelectListBox;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimMultiSelectPopupSearch createUimMultiSelectPopupSearch() {
		UimMultiSelectPopupSearchImpl uimMultiSelectPopupSearch = new UimMultiSelectPopupSearchImpl();
		return uimMultiSelectPopupSearch;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimSingleSelectPopupSearch createUimSingleSelectPopupSearch() {
		UimSingleSelectPopupSearchImpl uimSingleSelectPopupSearch = new UimSingleSelectPopupSearchImpl();
		return uimSingleSelectPopupSearch;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimToggleButton createUimToggleButton() {
		UimToggleButtonImpl uimToggleButton = new UimToggleButtonImpl();
		return uimToggleButton;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimNumberScroller createUimNumberScroller() {
		UimNumberScrollerImpl uimNumberScroller = new UimNumberScrollerImpl();
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
	public UserInteractionWorkspace createUserInteractionWorkspace() {
		UserInteractionWorkspaceImpl userInteractionWorkspace = new UserInteractionWorkspaceImpl();
		return userInteractionWorkspace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RequiredRole createRequiredRole() {
		RequiredRoleImpl requiredRole = new RequiredRoleImpl();
		return requiredRole;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SecureObject createSecureObject() {
		SecureObjectImpl secureObject = new SecureObjectImpl();
		return secureObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EditableSecureObject createEditableSecureObject() {
		EditableSecureObjectImpl editableSecureObject = new EditableSecureObjectImpl();
		return editableSecureObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimFullLayout createUimFullLayout() {
		UimFullLayoutImpl uimFullLayout = new UimFullLayoutImpl();
		return uimFullLayout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimPanel createUimPanel() {
		UimPanelImpl uimPanel = new UimPanelImpl();
		return uimPanel;
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
	public UimPackage getUimPackage() {
		return (UimPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static UimPackage getPackage() {
		return UimPackage.eINSTANCE;
	}

} //UimFactoryImpl
