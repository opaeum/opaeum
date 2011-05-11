/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim.util;

import java.util.List;


import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.nakeduml.uim.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.nakeduml.uim.UIMPackage
 * @generated
 */
public class UIMSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static UIMPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UIMSwitch() {
		if (modelPackage == null) {
			modelPackage = UIMPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case UIMPackage.UIM_FORM: {
				UIMForm uimForm = (UIMForm)theEObject;
				T result = caseUIMForm(uimForm);
				if (result == null) result = caseUserInteractionElement(uimForm);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.USER_INTERACTION_MODEL: {
				UserInteractionModel userInteractionModel = (UserInteractionModel)theEObject;
				T result = caseUserInteractionModel(userInteractionModel);
				if (result == null) result = caseAbstractFolder(userInteractionModel);
				if (result == null) result = caseUmlReference(userInteractionModel);
				if (result == null) result = caseUserInteractionElement(userInteractionModel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.ABSTRACT_FORM_FOLDER: {
				AbstractFormFolder abstractFormFolder = (AbstractFormFolder)theEObject;
				T result = caseAbstractFormFolder(abstractFormFolder);
				if (result == null) result = caseAbstractFolder(abstractFormFolder);
				if (result == null) result = caseUserInteractionElement(abstractFormFolder);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.ABSTRACT_FOLDER: {
				AbstractFolder abstractFolder = (AbstractFolder)theEObject;
				T result = caseAbstractFolder(abstractFolder);
				if (result == null) result = caseUserInteractionElement(abstractFolder);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.UIM_FIELD: {
				UIMField uimField = (UIMField)theEObject;
				T result = caseUIMField(uimField);
				if (result == null) result = caseUIMComponent(uimField);
				if (result == null) result = caseUserInteractionElement(uimField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.UIM_NAVIGATION: {
				UIMNavigation uimNavigation = (UIMNavigation)theEObject;
				T result = caseUIMNavigation(uimNavigation);
				if (result == null) result = caseUIMComponent(uimNavigation);
				if (result == null) result = caseUserInteractionElement(uimNavigation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.UIM_PANEL: {
				UIMPanel uimPanel = (UIMPanel)theEObject;
				T result = caseUIMPanel(uimPanel);
				if (result == null) result = caseUIMContainer(uimPanel);
				if (result == null) result = caseUIMComponent(uimPanel);
				if (result == null) result = caseUserInteractionElement(uimPanel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.CLASS_FORM: {
				ClassForm classForm = (ClassForm)theEObject;
				T result = caseClassForm(classForm);
				if (result == null) result = caseUIMForm(classForm);
				if (result == null) result = caseUmlReference(classForm);
				if (result == null) result = caseUserInteractionElement(classForm);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.STATE_FORM: {
				StateForm stateForm = (StateForm)theEObject;
				T result = caseStateForm(stateForm);
				if (result == null) result = caseUIMForm(stateForm);
				if (result == null) result = caseUmlReference(stateForm);
				if (result == null) result = caseUserInteractionElement(stateForm);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.MODEL_SECURITY_CONSTRAINT: {
				ModelSecurityConstraint modelSecurityConstraint = (ModelSecurityConstraint)theEObject;
				T result = caseModelSecurityConstraint(modelSecurityConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.OPERATION_INVOCATION_FORM: {
				OperationInvocationForm operationInvocationForm = (OperationInvocationForm)theEObject;
				T result = caseOperationInvocationForm(operationInvocationForm);
				if (result == null) result = caseUIMForm(operationInvocationForm);
				if (result == null) result = caseUmlReference(operationInvocationForm);
				if (result == null) result = caseUserInteractionElement(operationInvocationForm);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.UIM_COMPONENT: {
				UIMComponent uimComponent = (UIMComponent)theEObject;
				T result = caseUIMComponent(uimComponent);
				if (result == null) result = caseUserInteractionElement(uimComponent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.USER_INTERACTION_ELEMENT: {
				UserInteractionElement userInteractionElement = (UserInteractionElement)theEObject;
				T result = caseUserInteractionElement(userInteractionElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.OPERATION_ACTION: {
				OperationAction operationAction = (OperationAction)theEObject;
				T result = caseOperationAction(operationAction);
				if (result == null) result = caseUIMAction(operationAction);
				if (result == null) result = caseUmlReference(operationAction);
				if (result == null) result = caseUIMComponent(operationAction);
				if (result == null) result = caseUserInteractionElement(operationAction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.NAVIGATION_TO_OPERATION: {
				NavigationToOperation navigationToOperation = (NavigationToOperation)theEObject;
				T result = caseNavigationToOperation(navigationToOperation);
				if (result == null) result = caseUIMNavigation(navigationToOperation);
				if (result == null) result = caseUmlReference(navigationToOperation);
				if (result == null) result = caseUIMComponent(navigationToOperation);
				if (result == null) result = caseUserInteractionElement(navigationToOperation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.BUILT_IN_ACTION: {
				BuiltInAction builtInAction = (BuiltInAction)theEObject;
				T result = caseBuiltInAction(builtInAction);
				if (result == null) result = caseUIMAction(builtInAction);
				if (result == null) result = caseUIMComponent(builtInAction);
				if (result == null) result = caseUserInteractionElement(builtInAction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.UIM_CONTROL: {
				UIMControl uimControl = (UIMControl)theEObject;
				T result = caseUIMControl(uimControl);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.NAVIGATION_TO_ENTITY: {
				NavigationToEntity navigationToEntity = (NavigationToEntity)theEObject;
				T result = caseNavigationToEntity(navigationToEntity);
				if (result == null) result = caseUIMNavigation(navigationToEntity);
				if (result == null) result = caseUmlReference(navigationToEntity);
				if (result == null) result = caseUIMComponent(navigationToEntity);
				if (result == null) result = caseUserInteractionElement(navigationToEntity);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.TRANSITION_ACTION: {
				TransitionAction transitionAction = (TransitionAction)theEObject;
				T result = caseTransitionAction(transitionAction);
				if (result == null) result = caseUIMAction(transitionAction);
				if (result == null) result = caseUmlReference(transitionAction);
				if (result == null) result = caseUIMComponent(transitionAction);
				if (result == null) result = caseUserInteractionElement(transitionAction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.OPERATION_TASK_FORM: {
				OperationTaskForm operationTaskForm = (OperationTaskForm)theEObject;
				T result = caseOperationTaskForm(operationTaskForm);
				if (result == null) result = caseUIMForm(operationTaskForm);
				if (result == null) result = caseUmlReference(operationTaskForm);
				if (result == null) result = caseUserInteractionElement(operationTaskForm);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.ACTION_TASK_FORM: {
				ActionTaskForm actionTaskForm = (ActionTaskForm)theEObject;
				T result = caseActionTaskForm(actionTaskForm);
				if (result == null) result = caseUIMForm(actionTaskForm);
				if (result == null) result = caseUmlReference(actionTaskForm);
				if (result == null) result = caseUserInteractionElement(actionTaskForm);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.UIM_ACTION: {
				UIMAction uimAction = (UIMAction)theEObject;
				T result = caseUIMAction(uimAction);
				if (result == null) result = caseUIMComponent(uimAction);
				if (result == null) result = caseUserInteractionElement(uimAction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.CHILD_SECURITY_CONSTRAINT: {
				ChildSecurityConstraint childSecurityConstraint = (ChildSecurityConstraint)theEObject;
				T result = caseChildSecurityConstraint(childSecurityConstraint);
				if (result == null) result = caseModelSecurityConstraint(childSecurityConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.UIM_GRID_LAYOUT: {
				UIMGridLayout uimGridLayout = (UIMGridLayout)theEObject;
				T result = caseUIMGridLayout(uimGridLayout);
				if (result == null) result = caseUIMLayout(uimGridLayout);
				if (result == null) result = caseUIMContainer(uimGridLayout);
				if (result == null) result = caseUIMComponent(uimGridLayout);
				if (result == null) result = caseUserInteractionElement(uimGridLayout);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.UIM_DATA_TABLE: {
				UIMDataTable uimDataTable = (UIMDataTable)theEObject;
				T result = caseUIMDataTable(uimDataTable);
				if (result == null) result = caseMasterComponent(uimDataTable);
				if (result == null) result = caseUIMContainer(uimDataTable);
				if (result == null) result = caseUIMComponent(uimDataTable);
				if (result == null) result = caseUserInteractionElement(uimDataTable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.UIM_BINDING: {
				UIMBinding uimBinding = (UIMBinding)theEObject;
				T result = caseUIMBinding(uimBinding);
				if (result == null) result = caseUmlReference(uimBinding);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.PROPERTY_REF: {
				PropertyRef propertyRef = (PropertyRef)theEObject;
				T result = casePropertyRef(propertyRef);
				if (result == null) result = caseUmlReference(propertyRef);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.UIM_DATA_COLUMN: {
				UIMDataColumn uimDataColumn = (UIMDataColumn)theEObject;
				T result = caseUIMDataColumn(uimDataColumn);
				if (result == null) result = caseUIMPanel(uimDataColumn);
				if (result == null) result = caseUIMContainer(uimDataColumn);
				if (result == null) result = caseUIMComponent(uimDataColumn);
				if (result == null) result = caseUserInteractionElement(uimDataColumn);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.TABLE_BINDING: {
				TableBinding tableBinding = (TableBinding)theEObject;
				T result = caseTableBinding(tableBinding);
				if (result == null) result = caseUIMBinding(tableBinding);
				if (result == null) result = caseUmlReference(tableBinding);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.FIELD_BINDING: {
				FieldBinding fieldBinding = (FieldBinding)theEObject;
				T result = caseFieldBinding(fieldBinding);
				if (result == null) result = caseUIMBinding(fieldBinding);
				if (result == null) result = caseUmlReference(fieldBinding);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.FORM_PANEL: {
				FormPanel formPanel = (FormPanel)theEObject;
				T result = caseFormPanel(formPanel);
				if (result == null) result = caseUIMContainer(formPanel);
				if (result == null) result = caseUIMComponent(formPanel);
				if (result == null) result = caseUserInteractionElement(formPanel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.STATE_MACHINE_FOLDER: {
				StateMachineFolder stateMachineFolder = (StateMachineFolder)theEObject;
				T result = caseStateMachineFolder(stateMachineFolder);
				if (result == null) result = caseOperationContainingFolder(stateMachineFolder);
				if (result == null) result = caseUmlReference(stateMachineFolder);
				if (result == null) result = caseAbstractFormFolder(stateMachineFolder);
				if (result == null) result = caseAbstractFolder(stateMachineFolder);
				if (result == null) result = caseUserInteractionElement(stateMachineFolder);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.ENTITY_FOLDER: {
				EntityFolder entityFolder = (EntityFolder)theEObject;
				T result = caseEntityFolder(entityFolder);
				if (result == null) result = caseOperationContainingFolder(entityFolder);
				if (result == null) result = caseUmlReference(entityFolder);
				if (result == null) result = caseAbstractFormFolder(entityFolder);
				if (result == null) result = caseAbstractFolder(entityFolder);
				if (result == null) result = caseUserInteractionElement(entityFolder);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.ACTIVITY_FOLDER: {
				ActivityFolder activityFolder = (ActivityFolder)theEObject;
				T result = caseActivityFolder(activityFolder);
				if (result == null) result = caseAbstractFormFolder(activityFolder);
				if (result == null) result = caseUmlReference(activityFolder);
				if (result == null) result = caseAbstractFolder(activityFolder);
				if (result == null) result = caseUserInteractionElement(activityFolder);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.OPERATION_CONTAINING_FOLDER: {
				OperationContainingFolder operationContainingFolder = (OperationContainingFolder)theEObject;
				T result = caseOperationContainingFolder(operationContainingFolder);
				if (result == null) result = caseAbstractFormFolder(operationContainingFolder);
				if (result == null) result = caseAbstractFolder(operationContainingFolder);
				if (result == null) result = caseUserInteractionElement(operationContainingFolder);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.NAVIGATION_BINDING: {
				NavigationBinding navigationBinding = (NavigationBinding)theEObject;
				T result = caseNavigationBinding(navigationBinding);
				if (result == null) result = caseUIMBinding(navigationBinding);
				if (result == null) result = caseUmlReference(navigationBinding);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.DETAIL_PANEL: {
				DetailPanel detailPanel = (DetailPanel)theEObject;
				T result = caseDetailPanel(detailPanel);
				if (result == null) result = caseUIMPanel(detailPanel);
				if (result == null) result = caseUIMContainer(detailPanel);
				if (result == null) result = caseUIMComponent(detailPanel);
				if (result == null) result = caseUserInteractionElement(detailPanel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.PACKAGE_FOLDER: {
				PackageFolder packageFolder = (PackageFolder)theEObject;
				T result = casePackageFolder(packageFolder);
				if (result == null) result = caseAbstractFormFolder(packageFolder);
				if (result == null) result = caseUmlReference(packageFolder);
				if (result == null) result = caseAbstractFolder(packageFolder);
				if (result == null) result = caseUserInteractionElement(packageFolder);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.UIM_TAB_PANEL: {
				UIMTabPanel uimTabPanel = (UIMTabPanel)theEObject;
				T result = caseUIMTabPanel(uimTabPanel);
				if (result == null) result = caseUIMContainer(uimTabPanel);
				if (result == null) result = caseUIMComponent(uimTabPanel);
				if (result == null) result = caseUserInteractionElement(uimTabPanel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.UIM_TAB: {
				UIMTab uimTab = (UIMTab)theEObject;
				T result = caseUIMTab(uimTab);
				if (result == null) result = caseUIMPanel(uimTab);
				if (result == null) result = caseUIMContainer(uimTab);
				if (result == null) result = caseUIMComponent(uimTab);
				if (result == null) result = caseUserInteractionElement(uimTab);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.UIM_CHECK_BOX: {
				UIMCheckBox uimCheckBox = (UIMCheckBox)theEObject;
				T result = caseUIMCheckBox(uimCheckBox);
				if (result == null) result = caseUIMControl(uimCheckBox);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.UIM_LOOKUP: {
				UIMLookup uimLookup = (UIMLookup)theEObject;
				T result = caseUIMLookup(uimLookup);
				if (result == null) result = caseUIMControl(uimLookup);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.LOOKUP_BINDING: {
				LookupBinding lookupBinding = (LookupBinding)theEObject;
				T result = caseLookupBinding(lookupBinding);
				if (result == null) result = caseUIMBinding(lookupBinding);
				if (result == null) result = caseUmlReference(lookupBinding);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.UIM_TEXT: {
				UIMText uimText = (UIMText)theEObject;
				T result = caseUIMText(uimText);
				if (result == null) result = caseUIMControl(uimText);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.UIM_TEXT_AREA: {
				UIMTextArea uimTextArea = (UIMTextArea)theEObject;
				T result = caseUIMTextArea(uimTextArea);
				if (result == null) result = caseUIMControl(uimTextArea);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.UIM_DROPDOWN: {
				UIMDropdown uimDropdown = (UIMDropdown)theEObject;
				T result = caseUIMDropdown(uimDropdown);
				if (result == null) result = caseUIMLookup(uimDropdown);
				if (result == null) result = caseUIMControl(uimDropdown);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.UIM_DATE_POPUP: {
				UIMDatePopup uimDatePopup = (UIMDatePopup)theEObject;
				T result = caseUIMDatePopup(uimDatePopup);
				if (result == null) result = caseUIMControl(uimDatePopup);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.UIM_SINGLE_SELECT_LIST_BOX: {
				UIMSingleSelectListBox uimSingleSelectListBox = (UIMSingleSelectListBox)theEObject;
				T result = caseUIMSingleSelectListBox(uimSingleSelectListBox);
				if (result == null) result = caseUIMLookup(uimSingleSelectListBox);
				if (result == null) result = caseUIMControl(uimSingleSelectListBox);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.UIM_CONTAINER: {
				UIMContainer uimContainer = (UIMContainer)theEObject;
				T result = caseUIMContainer(uimContainer);
				if (result == null) result = caseUIMComponent(uimContainer);
				if (result == null) result = caseUserInteractionElement(uimContainer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.UIM_SINGLE_SELECT_TREE_VIEW: {
				UIMSingleSelectTreeView uimSingleSelectTreeView = (UIMSingleSelectTreeView)theEObject;
				T result = caseUIMSingleSelectTreeView(uimSingleSelectTreeView);
				if (result == null) result = caseMasterComponent(uimSingleSelectTreeView);
				if (result == null) result = caseUIMLookup(uimSingleSelectTreeView);
				if (result == null) result = caseUIMControl(uimSingleSelectTreeView);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.MASTER_COMPONENT: {
				MasterComponent masterComponent = (MasterComponent)theEObject;
				T result = caseMasterComponent(masterComponent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.UIM_LAYOUT: {
				UIMLayout uimLayout = (UIMLayout)theEObject;
				T result = caseUIMLayout(uimLayout);
				if (result == null) result = caseUIMContainer(uimLayout);
				if (result == null) result = caseUIMComponent(uimLayout);
				if (result == null) result = caseUserInteractionElement(uimLayout);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.UIM_TOOLBAR_LAYOUT: {
				UIMToolbarLayout uimToolbarLayout = (UIMToolbarLayout)theEObject;
				T result = caseUIMToolbarLayout(uimToolbarLayout);
				if (result == null) result = caseUIMLayout(uimToolbarLayout);
				if (result == null) result = caseUIMContainer(uimToolbarLayout);
				if (result == null) result = caseUIMComponent(uimToolbarLayout);
				if (result == null) result = caseUserInteractionElement(uimToolbarLayout);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.UIM_BORDER_LAYOUT: {
				UIMBorderLayout uimBorderLayout = (UIMBorderLayout)theEObject;
				T result = caseUIMBorderLayout(uimBorderLayout);
				if (result == null) result = caseUIMLayout(uimBorderLayout);
				if (result == null) result = caseUIMContainer(uimBorderLayout);
				if (result == null) result = caseUIMComponent(uimBorderLayout);
				if (result == null) result = caseUserInteractionElement(uimBorderLayout);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.UIM_XY_LAYOUT: {
				UIMXYLayout uimxyLayout = (UIMXYLayout)theEObject;
				T result = caseUIMXYLayout(uimxyLayout);
				if (result == null) result = caseUIMLayout(uimxyLayout);
				if (result == null) result = caseUIMContainer(uimxyLayout);
				if (result == null) result = caseUIMComponent(uimxyLayout);
				if (result == null) result = caseUserInteractionElement(uimxyLayout);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.UIM_MULTI_SELECT_TREE_VIEW: {
				UIMMultiSelectTreeView uimMultiSelectTreeView = (UIMMultiSelectTreeView)theEObject;
				T result = caseUIMMultiSelectTreeView(uimMultiSelectTreeView);
				if (result == null) result = caseUIMLookup(uimMultiSelectTreeView);
				if (result == null) result = caseUIMControl(uimMultiSelectTreeView);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.UIM_MULTI_SELECT_LIST_BOX: {
				UIMMultiSelectListBox uimMultiSelectListBox = (UIMMultiSelectListBox)theEObject;
				T result = caseUIMMultiSelectListBox(uimMultiSelectListBox);
				if (result == null) result = caseUIMLookup(uimMultiSelectListBox);
				if (result == null) result = caseUIMControl(uimMultiSelectListBox);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.UIM_MULTI_SELECT_POPUP_SEARCH: {
				UIMMultiSelectPopupSearch uimMultiSelectPopupSearch = (UIMMultiSelectPopupSearch)theEObject;
				T result = caseUIMMultiSelectPopupSearch(uimMultiSelectPopupSearch);
				if (result == null) result = caseUIMLookup(uimMultiSelectPopupSearch);
				if (result == null) result = caseUIMControl(uimMultiSelectPopupSearch);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.UIM_SINGLE_SELECT_POPUP_SEARCH: {
				UIMSingleSelectPopupSearch uimSingleSelectPopupSearch = (UIMSingleSelectPopupSearch)theEObject;
				T result = caseUIMSingleSelectPopupSearch(uimSingleSelectPopupSearch);
				if (result == null) result = caseUIMLookup(uimSingleSelectPopupSearch);
				if (result == null) result = caseUIMControl(uimSingleSelectPopupSearch);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.UIM_TOGGLE_BUTTON: {
				UIMToggleButton uimToggleButton = (UIMToggleButton)theEObject;
				T result = caseUIMToggleButton(uimToggleButton);
				if (result == null) result = caseUIMControl(uimToggleButton);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.UIM_NUMBER_SCROLLER: {
				UIMNumberScroller uimNumberScroller = (UIMNumberScroller)theEObject;
				T result = caseUIMNumberScroller(uimNumberScroller);
				if (result == null) result = caseUIMControl(uimNumberScroller);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIMPackage.UML_REFERENCE: {
				UmlReference umlReference = (UmlReference)theEObject;
				T result = caseUmlReference(umlReference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Form</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Form</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUIMForm(UIMForm object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>User Interaction Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>User Interaction Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUserInteractionModel(UserInteractionModel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Form Folder</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Form Folder</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractFormFolder(AbstractFormFolder object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Folder</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Folder</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractFolder(AbstractFolder object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Field</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Field</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUIMField(UIMField object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Navigation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Navigation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUIMNavigation(UIMNavigation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Panel</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Panel</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUIMPanel(UIMPanel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Class Form</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Class Form</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseClassForm(ClassForm object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>State Form</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>State Form</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStateForm(StateForm object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Model Security Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Model Security Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModelSecurityConstraint(ModelSecurityConstraint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Operation Invocation Form</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Operation Invocation Form</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOperationInvocationForm(OperationInvocationForm object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Component</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Component</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUIMComponent(UIMComponent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>User Interaction Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>User Interaction Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUserInteractionElement(UserInteractionElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Operation Action</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Operation Action</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOperationAction(OperationAction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Navigation To Operation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Navigation To Operation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNavigationToOperation(NavigationToOperation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Built In Action</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Built In Action</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBuiltInAction(BuiltInAction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Control</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Control</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUIMControl(UIMControl object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Navigation To Entity</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Navigation To Entity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNavigationToEntity(NavigationToEntity object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Transition Action</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Transition Action</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTransitionAction(TransitionAction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Operation Task Form</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Operation Task Form</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOperationTaskForm(OperationTaskForm object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Action Task Form</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Action Task Form</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActionTaskForm(ActionTaskForm object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Action</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Action</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUIMAction(UIMAction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Child Security Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Child Security Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseChildSecurityConstraint(ChildSecurityConstraint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Grid Layout</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Grid Layout</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUIMGridLayout(UIMGridLayout object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Data Table</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Data Table</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUIMDataTable(UIMDataTable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Binding</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Binding</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUIMBinding(UIMBinding object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Property Ref</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Property Ref</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePropertyRef(PropertyRef object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Data Column</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Data Column</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUIMDataColumn(UIMDataColumn object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Table Binding</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Table Binding</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTableBinding(TableBinding object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Field Binding</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Field Binding</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFieldBinding(FieldBinding object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Form Panel</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Form Panel</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFormPanel(FormPanel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>State Machine Folder</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>State Machine Folder</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStateMachineFolder(StateMachineFolder object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Entity Folder</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Entity Folder</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEntityFolder(EntityFolder object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Activity Folder</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Activity Folder</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActivityFolder(ActivityFolder object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Operation Containing Folder</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Operation Containing Folder</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOperationContainingFolder(OperationContainingFolder object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Navigation Binding</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Navigation Binding</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNavigationBinding(NavigationBinding object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Detail Panel</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Detail Panel</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDetailPanel(DetailPanel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Package Folder</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Package Folder</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePackageFolder(PackageFolder object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tab Panel</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tab Panel</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUIMTabPanel(UIMTabPanel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tab</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tab</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUIMTab(UIMTab object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Check Box</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Check Box</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUIMCheckBox(UIMCheckBox object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Lookup</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Lookup</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUIMLookup(UIMLookup object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Lookup Binding</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Lookup Binding</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLookupBinding(LookupBinding object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Text</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Text</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUIMText(UIMText object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Text Area</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Text Area</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUIMTextArea(UIMTextArea object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Dropdown</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Dropdown</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUIMDropdown(UIMDropdown object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Date Popup</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Date Popup</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUIMDatePopup(UIMDatePopup object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Single Select List Box</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Single Select List Box</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUIMSingleSelectListBox(UIMSingleSelectListBox object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Container</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUIMContainer(UIMContainer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Single Select Tree View</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Single Select Tree View</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUIMSingleSelectTreeView(UIMSingleSelectTreeView object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Master Component</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Master Component</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMasterComponent(MasterComponent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Layout</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Layout</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUIMLayout(UIMLayout object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Toolbar Layout</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Toolbar Layout</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUIMToolbarLayout(UIMToolbarLayout object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Border Layout</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Border Layout</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUIMBorderLayout(UIMBorderLayout object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>XY Layout</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>XY Layout</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUIMXYLayout(UIMXYLayout object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Multi Select Tree View</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Multi Select Tree View</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUIMMultiSelectTreeView(UIMMultiSelectTreeView object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Multi Select List Box</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Multi Select List Box</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUIMMultiSelectListBox(UIMMultiSelectListBox object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Multi Select Popup Search</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Multi Select Popup Search</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUIMMultiSelectPopupSearch(UIMMultiSelectPopupSearch object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Single Select Popup Search</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Single Select Popup Search</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUIMSingleSelectPopupSearch(UIMSingleSelectPopupSearch object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Toggle Button</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Toggle Button</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUIMToggleButton(UIMToggleButton object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Number Scroller</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Number Scroller</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUIMNumberScroller(UIMNumberScroller object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Uml Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Uml Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUmlReference(UmlReference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public T defaultCase(EObject object) {
		return null;
	}

} //UIMSwitch
