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
 * @see org.nakeduml.uim.UimPackage
 * @generated
 */
public class UimSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static UimPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimSwitch() {
		if (modelPackage == null) {
			modelPackage = UimPackage.eINSTANCE;
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
			case UimPackage.UIM_FORM: {
				UimForm uimForm = (UimForm)theEObject;
				T result = caseUimForm(uimForm);
				if (result == null) result = caseUserInteractionElement(uimForm);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.USER_INTERACTION_MODEL: {
				UserInteractionModel userInteractionModel = (UserInteractionModel)theEObject;
				T result = caseUserInteractionModel(userInteractionModel);
				if (result == null) result = caseAbstractFormFolder(userInteractionModel);
				if (result == null) result = caseAbstractFolder(userInteractionModel);
				if (result == null) result = caseEditableSecureObject(userInteractionModel);
				if (result == null) result = caseUmlReference(userInteractionModel);
				if (result == null) result = caseUserInteractionElement(userInteractionModel);
				if (result == null) result = caseSecureObject(userInteractionModel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.ABSTRACT_FORM_FOLDER: {
				AbstractFormFolder abstractFormFolder = (AbstractFormFolder)theEObject;
				T result = caseAbstractFormFolder(abstractFormFolder);
				if (result == null) result = caseAbstractFolder(abstractFormFolder);
				if (result == null) result = caseEditableSecureObject(abstractFormFolder);
				if (result == null) result = caseUserInteractionElement(abstractFormFolder);
				if (result == null) result = caseUmlReference(abstractFormFolder);
				if (result == null) result = caseSecureObject(abstractFormFolder);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.ABSTRACT_FOLDER: {
				AbstractFolder abstractFolder = (AbstractFolder)theEObject;
				T result = caseAbstractFolder(abstractFolder);
				if (result == null) result = caseUserInteractionElement(abstractFolder);
				if (result == null) result = caseUmlReference(abstractFolder);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.UIM_FIELD: {
				UimField uimField = (UimField)theEObject;
				T result = caseUimField(uimField);
				if (result == null) result = caseEditableSecureObject(uimField);
				if (result == null) result = caseOutlayableComponent(uimField);
				if (result == null) result = caseUimComponent(uimField);
				if (result == null) result = caseSecureObject(uimField);
				if (result == null) result = caseUserInteractionElement(uimField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.UIM_NAVIGATION: {
				UimNavigation uimNavigation = (UimNavigation)theEObject;
				T result = caseUimNavigation(uimNavigation);
				if (result == null) result = caseOutlayableComponent(uimNavigation);
				if (result == null) result = caseUimComponent(uimNavigation);
				if (result == null) result = caseUserInteractionElement(uimNavigation);
				if (result == null) result = caseSecureObject(uimNavigation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.LAYOUT_CONTAINER: {
				LayoutContainer layoutContainer = (LayoutContainer)theEObject;
				T result = caseLayoutContainer(layoutContainer);
				if (result == null) result = caseUimContainer(layoutContainer);
				if (result == null) result = caseUimComponent(layoutContainer);
				if (result == null) result = caseEditableSecureObject(layoutContainer);
				if (result == null) result = caseUserInteractionElement(layoutContainer);
				if (result == null) result = caseSecureObject(layoutContainer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.CLASS_FORM: {
				ClassForm classForm = (ClassForm)theEObject;
				T result = caseClassForm(classForm);
				if (result == null) result = caseFormPanel(classForm);
				if (result == null) result = caseUmlReference(classForm);
				if (result == null) result = caseLayoutContainer(classForm);
				if (result == null) result = caseUimContainer(classForm);
				if (result == null) result = caseUimComponent(classForm);
				if (result == null) result = caseEditableSecureObject(classForm);
				if (result == null) result = caseUserInteractionElement(classForm);
				if (result == null) result = caseSecureObject(classForm);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.STATE_FORM: {
				StateForm stateForm = (StateForm)theEObject;
				T result = caseStateForm(stateForm);
				if (result == null) result = caseFormPanel(stateForm);
				if (result == null) result = caseUmlReference(stateForm);
				if (result == null) result = caseLayoutContainer(stateForm);
				if (result == null) result = caseUimContainer(stateForm);
				if (result == null) result = caseUimComponent(stateForm);
				if (result == null) result = caseEditableSecureObject(stateForm);
				if (result == null) result = caseUserInteractionElement(stateForm);
				if (result == null) result = caseSecureObject(stateForm);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.WORKSPACE_SECURITY_CONSTRAINT: {
				WorkspaceSecurityConstraint workspaceSecurityConstraint = (WorkspaceSecurityConstraint)theEObject;
				T result = caseWorkspaceSecurityConstraint(workspaceSecurityConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.OPERATION_INVOCATION_FORM: {
				OperationInvocationForm operationInvocationForm = (OperationInvocationForm)theEObject;
				T result = caseOperationInvocationForm(operationInvocationForm);
				if (result == null) result = caseFormPanel(operationInvocationForm);
				if (result == null) result = caseUmlReference(operationInvocationForm);
				if (result == null) result = caseLayoutContainer(operationInvocationForm);
				if (result == null) result = caseUimContainer(operationInvocationForm);
				if (result == null) result = caseUimComponent(operationInvocationForm);
				if (result == null) result = caseEditableSecureObject(operationInvocationForm);
				if (result == null) result = caseUserInteractionElement(operationInvocationForm);
				if (result == null) result = caseSecureObject(operationInvocationForm);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.UIM_COMPONENT: {
				UimComponent uimComponent = (UimComponent)theEObject;
				T result = caseUimComponent(uimComponent);
				if (result == null) result = caseUserInteractionElement(uimComponent);
				if (result == null) result = caseSecureObject(uimComponent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.USER_INTERACTION_ELEMENT: {
				UserInteractionElement userInteractionElement = (UserInteractionElement)theEObject;
				T result = caseUserInteractionElement(userInteractionElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.OPERATION_ACTION: {
				OperationAction operationAction = (OperationAction)theEObject;
				T result = caseOperationAction(operationAction);
				if (result == null) result = caseUimAction(operationAction);
				if (result == null) result = caseUmlReference(operationAction);
				if (result == null) result = caseOutlayableComponent(operationAction);
				if (result == null) result = caseUimComponent(operationAction);
				if (result == null) result = caseUserInteractionElement(operationAction);
				if (result == null) result = caseSecureObject(operationAction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.NAVIGATION_TO_OPERATION: {
				NavigationToOperation navigationToOperation = (NavigationToOperation)theEObject;
				T result = caseNavigationToOperation(navigationToOperation);
				if (result == null) result = caseUimNavigation(navigationToOperation);
				if (result == null) result = caseUmlReference(navigationToOperation);
				if (result == null) result = caseOutlayableComponent(navigationToOperation);
				if (result == null) result = caseUimComponent(navigationToOperation);
				if (result == null) result = caseUserInteractionElement(navigationToOperation);
				if (result == null) result = caseSecureObject(navigationToOperation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.BUILT_IN_ACTION: {
				BuiltInAction builtInAction = (BuiltInAction)theEObject;
				T result = caseBuiltInAction(builtInAction);
				if (result == null) result = caseUimAction(builtInAction);
				if (result == null) result = caseOutlayableComponent(builtInAction);
				if (result == null) result = caseUimComponent(builtInAction);
				if (result == null) result = caseUserInteractionElement(builtInAction);
				if (result == null) result = caseSecureObject(builtInAction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.UIM_CONTROL: {
				UimControl uimControl = (UimControl)theEObject;
				T result = caseUimControl(uimControl);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.NAVIGATION_TO_ENTITY: {
				NavigationToEntity navigationToEntity = (NavigationToEntity)theEObject;
				T result = caseNavigationToEntity(navigationToEntity);
				if (result == null) result = caseUimNavigation(navigationToEntity);
				if (result == null) result = caseUmlReference(navigationToEntity);
				if (result == null) result = caseOutlayableComponent(navigationToEntity);
				if (result == null) result = caseUimComponent(navigationToEntity);
				if (result == null) result = caseUserInteractionElement(navigationToEntity);
				if (result == null) result = caseSecureObject(navigationToEntity);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.TRANSITION_ACTION: {
				TransitionAction transitionAction = (TransitionAction)theEObject;
				T result = caseTransitionAction(transitionAction);
				if (result == null) result = caseUimAction(transitionAction);
				if (result == null) result = caseUmlReference(transitionAction);
				if (result == null) result = caseOutlayableComponent(transitionAction);
				if (result == null) result = caseUimComponent(transitionAction);
				if (result == null) result = caseUserInteractionElement(transitionAction);
				if (result == null) result = caseSecureObject(transitionAction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.OPERATION_TASK_FORM: {
				OperationTaskForm operationTaskForm = (OperationTaskForm)theEObject;
				T result = caseOperationTaskForm(operationTaskForm);
				if (result == null) result = caseFormPanel(operationTaskForm);
				if (result == null) result = caseUmlReference(operationTaskForm);
				if (result == null) result = caseLayoutContainer(operationTaskForm);
				if (result == null) result = caseUimContainer(operationTaskForm);
				if (result == null) result = caseUimComponent(operationTaskForm);
				if (result == null) result = caseEditableSecureObject(operationTaskForm);
				if (result == null) result = caseUserInteractionElement(operationTaskForm);
				if (result == null) result = caseSecureObject(operationTaskForm);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.ACTION_TASK_FORM: {
				ActionTaskForm actionTaskForm = (ActionTaskForm)theEObject;
				T result = caseActionTaskForm(actionTaskForm);
				if (result == null) result = caseFormPanel(actionTaskForm);
				if (result == null) result = caseUmlReference(actionTaskForm);
				if (result == null) result = caseLayoutContainer(actionTaskForm);
				if (result == null) result = caseUimContainer(actionTaskForm);
				if (result == null) result = caseUimComponent(actionTaskForm);
				if (result == null) result = caseEditableSecureObject(actionTaskForm);
				if (result == null) result = caseUserInteractionElement(actionTaskForm);
				if (result == null) result = caseSecureObject(actionTaskForm);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.UIM_ACTION: {
				UimAction uimAction = (UimAction)theEObject;
				T result = caseUimAction(uimAction);
				if (result == null) result = caseOutlayableComponent(uimAction);
				if (result == null) result = caseUimComponent(uimAction);
				if (result == null) result = caseUserInteractionElement(uimAction);
				if (result == null) result = caseSecureObject(uimAction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.SECURITY_CONSTRAINT: {
				SecurityConstraint securityConstraint = (SecurityConstraint)theEObject;
				T result = caseSecurityConstraint(securityConstraint);
				if (result == null) result = caseWorkspaceSecurityConstraint(securityConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.UIM_GRID_LAYOUT: {
				UimGridLayout uimGridLayout = (UimGridLayout)theEObject;
				T result = caseUimGridLayout(uimGridLayout);
				if (result == null) result = caseUimLayout(uimGridLayout);
				if (result == null) result = caseUimContainer(uimGridLayout);
				if (result == null) result = caseUimComponent(uimGridLayout);
				if (result == null) result = caseEditableSecureObject(uimGridLayout);
				if (result == null) result = caseUserInteractionElement(uimGridLayout);
				if (result == null) result = caseSecureObject(uimGridLayout);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.UIM_DATA_TABLE: {
				UimDataTable uimDataTable = (UimDataTable)theEObject;
				T result = caseUimDataTable(uimDataTable);
				if (result == null) result = caseMasterComponent(uimDataTable);
				if (result == null) result = caseUimContainer(uimDataTable);
				if (result == null) result = caseOutlayableComponent(uimDataTable);
				if (result == null) result = caseUimComponent(uimDataTable);
				if (result == null) result = caseEditableSecureObject(uimDataTable);
				if (result == null) result = caseUserInteractionElement(uimDataTable);
				if (result == null) result = caseSecureObject(uimDataTable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.UIM_BINDING: {
				UimBinding uimBinding = (UimBinding)theEObject;
				T result = caseUimBinding(uimBinding);
				if (result == null) result = caseUmlReference(uimBinding);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.PROPERTY_REF: {
				PropertyRef propertyRef = (PropertyRef)theEObject;
				T result = casePropertyRef(propertyRef);
				if (result == null) result = caseUmlReference(propertyRef);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.UIM_DATA_COLUMN: {
				UimDataColumn uimDataColumn = (UimDataColumn)theEObject;
				T result = caseUimDataColumn(uimDataColumn);
				if (result == null) result = caseLayoutContainer(uimDataColumn);
				if (result == null) result = caseUimContainer(uimDataColumn);
				if (result == null) result = caseUimComponent(uimDataColumn);
				if (result == null) result = caseEditableSecureObject(uimDataColumn);
				if (result == null) result = caseUserInteractionElement(uimDataColumn);
				if (result == null) result = caseSecureObject(uimDataColumn);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.TABLE_BINDING: {
				TableBinding tableBinding = (TableBinding)theEObject;
				T result = caseTableBinding(tableBinding);
				if (result == null) result = caseUimBinding(tableBinding);
				if (result == null) result = caseUmlReference(tableBinding);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.FIELD_BINDING: {
				FieldBinding fieldBinding = (FieldBinding)theEObject;
				T result = caseFieldBinding(fieldBinding);
				if (result == null) result = caseUimBinding(fieldBinding);
				if (result == null) result = caseUmlReference(fieldBinding);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.FORM_PANEL: {
				FormPanel formPanel = (FormPanel)theEObject;
				T result = caseFormPanel(formPanel);
				if (result == null) result = caseUmlReference(formPanel);
				if (result == null) result = caseLayoutContainer(formPanel);
				if (result == null) result = caseUimContainer(formPanel);
				if (result == null) result = caseUimComponent(formPanel);
				if (result == null) result = caseEditableSecureObject(formPanel);
				if (result == null) result = caseUserInteractionElement(formPanel);
				if (result == null) result = caseSecureObject(formPanel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.STATE_MACHINE_FOLDER: {
				StateMachineFolder stateMachineFolder = (StateMachineFolder)theEObject;
				T result = caseStateMachineFolder(stateMachineFolder);
				if (result == null) result = caseOperationContainingFolder(stateMachineFolder);
				if (result == null) result = caseAbstractFormFolder(stateMachineFolder);
				if (result == null) result = caseAbstractFolder(stateMachineFolder);
				if (result == null) result = caseEditableSecureObject(stateMachineFolder);
				if (result == null) result = caseUserInteractionElement(stateMachineFolder);
				if (result == null) result = caseUmlReference(stateMachineFolder);
				if (result == null) result = caseSecureObject(stateMachineFolder);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.ENTITY_FOLDER: {
				EntityFolder entityFolder = (EntityFolder)theEObject;
				T result = caseEntityFolder(entityFolder);
				if (result == null) result = caseOperationContainingFolder(entityFolder);
				if (result == null) result = caseAbstractFormFolder(entityFolder);
				if (result == null) result = caseAbstractFolder(entityFolder);
				if (result == null) result = caseEditableSecureObject(entityFolder);
				if (result == null) result = caseUserInteractionElement(entityFolder);
				if (result == null) result = caseUmlReference(entityFolder);
				if (result == null) result = caseSecureObject(entityFolder);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.ACTIVITY_FOLDER: {
				ActivityFolder activityFolder = (ActivityFolder)theEObject;
				T result = caseActivityFolder(activityFolder);
				if (result == null) result = caseAbstractFormFolder(activityFolder);
				if (result == null) result = caseAbstractFolder(activityFolder);
				if (result == null) result = caseEditableSecureObject(activityFolder);
				if (result == null) result = caseUserInteractionElement(activityFolder);
				if (result == null) result = caseUmlReference(activityFolder);
				if (result == null) result = caseSecureObject(activityFolder);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.OPERATION_CONTAINING_FOLDER: {
				OperationContainingFolder operationContainingFolder = (OperationContainingFolder)theEObject;
				T result = caseOperationContainingFolder(operationContainingFolder);
				if (result == null) result = caseAbstractFormFolder(operationContainingFolder);
				if (result == null) result = caseAbstractFolder(operationContainingFolder);
				if (result == null) result = caseEditableSecureObject(operationContainingFolder);
				if (result == null) result = caseUserInteractionElement(operationContainingFolder);
				if (result == null) result = caseUmlReference(operationContainingFolder);
				if (result == null) result = caseSecureObject(operationContainingFolder);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.NAVIGATION_BINDING: {
				NavigationBinding navigationBinding = (NavigationBinding)theEObject;
				T result = caseNavigationBinding(navigationBinding);
				if (result == null) result = caseUimBinding(navigationBinding);
				if (result == null) result = caseUmlReference(navigationBinding);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.DETAIL_PANEL: {
				DetailPanel detailPanel = (DetailPanel)theEObject;
				T result = caseDetailPanel(detailPanel);
				if (result == null) result = caseUimPanel(detailPanel);
				if (result == null) result = caseLayoutContainer(detailPanel);
				if (result == null) result = caseOutlayableComponent(detailPanel);
				if (result == null) result = caseUimContainer(detailPanel);
				if (result == null) result = caseUimComponent(detailPanel);
				if (result == null) result = caseEditableSecureObject(detailPanel);
				if (result == null) result = caseUserInteractionElement(detailPanel);
				if (result == null) result = caseSecureObject(detailPanel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.PACKAGE_FOLDER: {
				PackageFolder packageFolder = (PackageFolder)theEObject;
				T result = casePackageFolder(packageFolder);
				if (result == null) result = caseAbstractFormFolder(packageFolder);
				if (result == null) result = caseAbstractFolder(packageFolder);
				if (result == null) result = caseEditableSecureObject(packageFolder);
				if (result == null) result = caseUserInteractionElement(packageFolder);
				if (result == null) result = caseUmlReference(packageFolder);
				if (result == null) result = caseSecureObject(packageFolder);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.UIM_TAB_PANEL: {
				UimTabPanel uimTabPanel = (UimTabPanel)theEObject;
				T result = caseUimTabPanel(uimTabPanel);
				if (result == null) result = caseUimContainer(uimTabPanel);
				if (result == null) result = caseOutlayableComponent(uimTabPanel);
				if (result == null) result = caseUimComponent(uimTabPanel);
				if (result == null) result = caseEditableSecureObject(uimTabPanel);
				if (result == null) result = caseUserInteractionElement(uimTabPanel);
				if (result == null) result = caseSecureObject(uimTabPanel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.UIM_TAB: {
				UimTab uimTab = (UimTab)theEObject;
				T result = caseUimTab(uimTab);
				if (result == null) result = caseLayoutContainer(uimTab);
				if (result == null) result = caseUimContainer(uimTab);
				if (result == null) result = caseUimComponent(uimTab);
				if (result == null) result = caseEditableSecureObject(uimTab);
				if (result == null) result = caseUserInteractionElement(uimTab);
				if (result == null) result = caseSecureObject(uimTab);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.UIM_CHECK_BOX: {
				UimCheckBox uimCheckBox = (UimCheckBox)theEObject;
				T result = caseUimCheckBox(uimCheckBox);
				if (result == null) result = caseUimControl(uimCheckBox);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.UIM_LOOKUP: {
				UimLookup uimLookup = (UimLookup)theEObject;
				T result = caseUimLookup(uimLookup);
				if (result == null) result = caseUimControl(uimLookup);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.LOOKUP_BINDING: {
				LookupBinding lookupBinding = (LookupBinding)theEObject;
				T result = caseLookupBinding(lookupBinding);
				if (result == null) result = caseUimBinding(lookupBinding);
				if (result == null) result = caseUmlReference(lookupBinding);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.UIM_TEXT: {
				UimText uimText = (UimText)theEObject;
				T result = caseUimText(uimText);
				if (result == null) result = caseUimControl(uimText);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.UIM_TEXT_AREA: {
				UimTextArea uimTextArea = (UimTextArea)theEObject;
				T result = caseUimTextArea(uimTextArea);
				if (result == null) result = caseUimControl(uimTextArea);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.UIM_DROPDOWN: {
				UimDropdown uimDropdown = (UimDropdown)theEObject;
				T result = caseUimDropdown(uimDropdown);
				if (result == null) result = caseUimLookup(uimDropdown);
				if (result == null) result = caseUimControl(uimDropdown);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.UIM_DATE_POPUP: {
				UimDatePopup uimDatePopup = (UimDatePopup)theEObject;
				T result = caseUimDatePopup(uimDatePopup);
				if (result == null) result = caseUimControl(uimDatePopup);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.UIM_SINGLE_SELECT_LIST_BOX: {
				UimSingleSelectListBox uimSingleSelectListBox = (UimSingleSelectListBox)theEObject;
				T result = caseUimSingleSelectListBox(uimSingleSelectListBox);
				if (result == null) result = caseUimLookup(uimSingleSelectListBox);
				if (result == null) result = caseUimControl(uimSingleSelectListBox);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.UIM_CONTAINER: {
				UimContainer uimContainer = (UimContainer)theEObject;
				T result = caseUimContainer(uimContainer);
				if (result == null) result = caseUimComponent(uimContainer);
				if (result == null) result = caseEditableSecureObject(uimContainer);
				if (result == null) result = caseUserInteractionElement(uimContainer);
				if (result == null) result = caseSecureObject(uimContainer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.UIM_SINGLE_SELECT_TREE_VIEW: {
				UimSingleSelectTreeView uimSingleSelectTreeView = (UimSingleSelectTreeView)theEObject;
				T result = caseUimSingleSelectTreeView(uimSingleSelectTreeView);
				if (result == null) result = caseMasterComponent(uimSingleSelectTreeView);
				if (result == null) result = caseUimLookup(uimSingleSelectTreeView);
				if (result == null) result = caseUimControl(uimSingleSelectTreeView);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.MASTER_COMPONENT: {
				MasterComponent masterComponent = (MasterComponent)theEObject;
				T result = caseMasterComponent(masterComponent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.UIM_LAYOUT: {
				UimLayout uimLayout = (UimLayout)theEObject;
				T result = caseUimLayout(uimLayout);
				if (result == null) result = caseUimContainer(uimLayout);
				if (result == null) result = caseUimComponent(uimLayout);
				if (result == null) result = caseEditableSecureObject(uimLayout);
				if (result == null) result = caseUserInteractionElement(uimLayout);
				if (result == null) result = caseSecureObject(uimLayout);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.UIM_TOOLBAR_LAYOUT: {
				UimToolbarLayout uimToolbarLayout = (UimToolbarLayout)theEObject;
				T result = caseUimToolbarLayout(uimToolbarLayout);
				if (result == null) result = caseUimLayout(uimToolbarLayout);
				if (result == null) result = caseUimContainer(uimToolbarLayout);
				if (result == null) result = caseUimComponent(uimToolbarLayout);
				if (result == null) result = caseEditableSecureObject(uimToolbarLayout);
				if (result == null) result = caseUserInteractionElement(uimToolbarLayout);
				if (result == null) result = caseSecureObject(uimToolbarLayout);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.UIM_BORDER_LAYOUT: {
				UimBorderLayout uimBorderLayout = (UimBorderLayout)theEObject;
				T result = caseUimBorderLayout(uimBorderLayout);
				if (result == null) result = caseUimLayout(uimBorderLayout);
				if (result == null) result = caseUimContainer(uimBorderLayout);
				if (result == null) result = caseUimComponent(uimBorderLayout);
				if (result == null) result = caseEditableSecureObject(uimBorderLayout);
				if (result == null) result = caseUserInteractionElement(uimBorderLayout);
				if (result == null) result = caseSecureObject(uimBorderLayout);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.UIM_XY_LAYOUT: {
				UimXYLayout uimXYLayout = (UimXYLayout)theEObject;
				T result = caseUimXYLayout(uimXYLayout);
				if (result == null) result = caseUimLayout(uimXYLayout);
				if (result == null) result = caseUimContainer(uimXYLayout);
				if (result == null) result = caseUimComponent(uimXYLayout);
				if (result == null) result = caseEditableSecureObject(uimXYLayout);
				if (result == null) result = caseUserInteractionElement(uimXYLayout);
				if (result == null) result = caseSecureObject(uimXYLayout);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.UIM_MULTI_SELECT_TREE_VIEW: {
				UimMultiSelectTreeView uimMultiSelectTreeView = (UimMultiSelectTreeView)theEObject;
				T result = caseUimMultiSelectTreeView(uimMultiSelectTreeView);
				if (result == null) result = caseUimLookup(uimMultiSelectTreeView);
				if (result == null) result = caseUimControl(uimMultiSelectTreeView);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.UIM_MULTI_SELECT_LIST_BOX: {
				UimMultiSelectListBox uimMultiSelectListBox = (UimMultiSelectListBox)theEObject;
				T result = caseUimMultiSelectListBox(uimMultiSelectListBox);
				if (result == null) result = caseUimLookup(uimMultiSelectListBox);
				if (result == null) result = caseUimControl(uimMultiSelectListBox);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.UIM_MULTI_SELECT_POPUP_SEARCH: {
				UimMultiSelectPopupSearch uimMultiSelectPopupSearch = (UimMultiSelectPopupSearch)theEObject;
				T result = caseUimMultiSelectPopupSearch(uimMultiSelectPopupSearch);
				if (result == null) result = caseUimLookup(uimMultiSelectPopupSearch);
				if (result == null) result = caseUimControl(uimMultiSelectPopupSearch);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.UIM_SINGLE_SELECT_POPUP_SEARCH: {
				UimSingleSelectPopupSearch uimSingleSelectPopupSearch = (UimSingleSelectPopupSearch)theEObject;
				T result = caseUimSingleSelectPopupSearch(uimSingleSelectPopupSearch);
				if (result == null) result = caseUimLookup(uimSingleSelectPopupSearch);
				if (result == null) result = caseUimControl(uimSingleSelectPopupSearch);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.UIM_TOGGLE_BUTTON: {
				UimToggleButton uimToggleButton = (UimToggleButton)theEObject;
				T result = caseUimToggleButton(uimToggleButton);
				if (result == null) result = caseUimControl(uimToggleButton);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.UIM_NUMBER_SCROLLER: {
				UimNumberScroller uimNumberScroller = (UimNumberScroller)theEObject;
				T result = caseUimNumberScroller(uimNumberScroller);
				if (result == null) result = caseUimControl(uimNumberScroller);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.UML_REFERENCE: {
				UmlReference umlReference = (UmlReference)theEObject;
				T result = caseUmlReference(umlReference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.USER_INTERACTION_WORKSPACE: {
				UserInteractionWorkspace userInteractionWorkspace = (UserInteractionWorkspace)theEObject;
				T result = caseUserInteractionWorkspace(userInteractionWorkspace);
				if (result == null) result = caseAbstractFolder(userInteractionWorkspace);
				if (result == null) result = caseUserInteractionElement(userInteractionWorkspace);
				if (result == null) result = caseUmlReference(userInteractionWorkspace);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.REQUIRED_ROLE: {
				RequiredRole requiredRole = (RequiredRole)theEObject;
				T result = caseRequiredRole(requiredRole);
				if (result == null) result = caseUmlReference(requiredRole);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.SECURE_OBJECT: {
				SecureObject secureObject = (SecureObject)theEObject;
				T result = caseSecureObject(secureObject);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.EDITABLE_SECURE_OBJECT: {
				EditableSecureObject editableSecureObject = (EditableSecureObject)theEObject;
				T result = caseEditableSecureObject(editableSecureObject);
				if (result == null) result = caseSecureObject(editableSecureObject);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.UIM_FULL_LAYOUT: {
				UimFullLayout uimFullLayout = (UimFullLayout)theEObject;
				T result = caseUimFullLayout(uimFullLayout);
				if (result == null) result = caseUimLayout(uimFullLayout);
				if (result == null) result = caseUimContainer(uimFullLayout);
				if (result == null) result = caseUimComponent(uimFullLayout);
				if (result == null) result = caseEditableSecureObject(uimFullLayout);
				if (result == null) result = caseUserInteractionElement(uimFullLayout);
				if (result == null) result = caseSecureObject(uimFullLayout);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.OUTLAYABLE_COMPONENT: {
				OutlayableComponent outlayableComponent = (OutlayableComponent)theEObject;
				T result = caseOutlayableComponent(outlayableComponent);
				if (result == null) result = caseUimComponent(outlayableComponent);
				if (result == null) result = caseUserInteractionElement(outlayableComponent);
				if (result == null) result = caseSecureObject(outlayableComponent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.UIM_PANEL: {
				UimPanel uimPanel = (UimPanel)theEObject;
				T result = caseUimPanel(uimPanel);
				if (result == null) result = caseLayoutContainer(uimPanel);
				if (result == null) result = caseOutlayableComponent(uimPanel);
				if (result == null) result = caseUimContainer(uimPanel);
				if (result == null) result = caseUimComponent(uimPanel);
				if (result == null) result = caseEditableSecureObject(uimPanel);
				if (result == null) result = caseUserInteractionElement(uimPanel);
				if (result == null) result = caseSecureObject(uimPanel);
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
	public T caseUimForm(UimForm object) {
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
	public T caseUimField(UimField object) {
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
	public T caseUimNavigation(UimNavigation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Layout Container</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Layout Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLayoutContainer(LayoutContainer object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Workspace Security Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Workspace Security Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWorkspaceSecurityConstraint(WorkspaceSecurityConstraint object) {
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
	public T caseUimComponent(UimComponent object) {
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
	public T caseUimControl(UimControl object) {
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
	public T caseUimAction(UimAction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Security Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Security Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSecurityConstraint(SecurityConstraint object) {
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
	public T caseUimGridLayout(UimGridLayout object) {
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
	public T caseUimDataTable(UimDataTable object) {
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
	public T caseUimBinding(UimBinding object) {
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
	public T caseUimDataColumn(UimDataColumn object) {
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
	public T caseUimTabPanel(UimTabPanel object) {
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
	public T caseUimTab(UimTab object) {
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
	public T caseUimCheckBox(UimCheckBox object) {
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
	public T caseUimLookup(UimLookup object) {
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
	public T caseUimText(UimText object) {
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
	public T caseUimTextArea(UimTextArea object) {
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
	public T caseUimDropdown(UimDropdown object) {
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
	public T caseUimDatePopup(UimDatePopup object) {
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
	public T caseUimSingleSelectListBox(UimSingleSelectListBox object) {
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
	public T caseUimContainer(UimContainer object) {
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
	public T caseUimSingleSelectTreeView(UimSingleSelectTreeView object) {
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
	public T caseUimLayout(UimLayout object) {
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
	public T caseUimToolbarLayout(UimToolbarLayout object) {
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
	public T caseUimBorderLayout(UimBorderLayout object) {
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
	public T caseUimXYLayout(UimXYLayout object) {
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
	public T caseUimMultiSelectTreeView(UimMultiSelectTreeView object) {
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
	public T caseUimMultiSelectListBox(UimMultiSelectListBox object) {
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
	public T caseUimMultiSelectPopupSearch(UimMultiSelectPopupSearch object) {
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
	public T caseUimSingleSelectPopupSearch(UimSingleSelectPopupSearch object) {
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
	public T caseUimToggleButton(UimToggleButton object) {
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
	public T caseUimNumberScroller(UimNumberScroller object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>User Interaction Workspace</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>User Interaction Workspace</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUserInteractionWorkspace(UserInteractionWorkspace object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Required Role</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Required Role</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRequiredRole(RequiredRole object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Secure Object</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Secure Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSecureObject(SecureObject object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Editable Secure Object</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Editable Secure Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEditableSecureObject(EditableSecureObject object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Full Layout</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Full Layout</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUimFullLayout(UimFullLayout object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Outlayable Component</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Outlayable Component</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOutlayableComponent(OutlayableComponent object) {
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
	public T caseUimPanel(UimPanel object) {
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

} //UimSwitch
