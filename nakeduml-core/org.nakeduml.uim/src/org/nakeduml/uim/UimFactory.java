/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.nakeduml.uim.UimPackage
 * @generated
 */
public interface UimFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	UimFactory eINSTANCE = org.nakeduml.uim.impl.UimFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Form</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Form</em>'.
	 * @generated
	 */
	UimForm createUimForm();

	/**
	 * Returns a new object of class '<em>User Interaction Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>User Interaction Model</em>'.
	 * @generated
	 */
	UserInteractionModel createUserInteractionModel();

	/**
	 * Returns a new object of class '<em>Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Field</em>'.
	 * @generated
	 */
	UimField createUimField();

	/**
	 * Returns a new object of class '<em>Class Form</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Class Form</em>'.
	 * @generated
	 */
	ClassForm createClassForm();

	/**
	 * Returns a new object of class '<em>State Form</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>State Form</em>'.
	 * @generated
	 */
	StateForm createStateForm();

	/**
	 * Returns a new object of class '<em>Workspace Security Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Workspace Security Constraint</em>'.
	 * @generated
	 */
	WorkspaceSecurityConstraint createWorkspaceSecurityConstraint();

	/**
	 * Returns a new object of class '<em>Operation Invocation Form</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Operation Invocation Form</em>'.
	 * @generated
	 */
	OperationInvocationForm createOperationInvocationForm();

	/**
	 * Returns a new object of class '<em>Operation Action</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Operation Action</em>'.
	 * @generated
	 */
	OperationAction createOperationAction();

	/**
	 * Returns a new object of class '<em>Navigation To Operation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Navigation To Operation</em>'.
	 * @generated
	 */
	NavigationToOperation createNavigationToOperation();

	/**
	 * Returns a new object of class '<em>Built In Action</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Built In Action</em>'.
	 * @generated
	 */
	BuiltInAction createBuiltInAction();

	/**
	 * Returns a new object of class '<em>Control</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Control</em>'.
	 * @generated
	 */
	UimControl createUimControl();

	/**
	 * Returns a new object of class '<em>Navigation To Entity</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Navigation To Entity</em>'.
	 * @generated
	 */
	NavigationToEntity createNavigationToEntity();

	/**
	 * Returns a new object of class '<em>Transition Action</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Transition Action</em>'.
	 * @generated
	 */
	TransitionAction createTransitionAction();

	/**
	 * Returns a new object of class '<em>Operation Task Form</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Operation Task Form</em>'.
	 * @generated
	 */
	OperationTaskForm createOperationTaskForm();

	/**
	 * Returns a new object of class '<em>Action Task Form</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Action Task Form</em>'.
	 * @generated
	 */
	ActionTaskForm createActionTaskForm();

	/**
	 * Returns a new object of class '<em>Security Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Security Constraint</em>'.
	 * @generated
	 */
	SecurityConstraint createSecurityConstraint();

	/**
	 * Returns a new object of class '<em>Grid Layout</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Grid Layout</em>'.
	 * @generated
	 */
	UimGridLayout createUimGridLayout();

	/**
	 * Returns a new object of class '<em>Data Table</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Data Table</em>'.
	 * @generated
	 */
	UimDataTable createUimDataTable();

	/**
	 * Returns a new object of class '<em>Property Ref</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Property Ref</em>'.
	 * @generated
	 */
	PropertyRef createPropertyRef();

	/**
	 * Returns a new object of class '<em>Data Column</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Data Column</em>'.
	 * @generated
	 */
	UimDataColumn createUimDataColumn();

	/**
	 * Returns a new object of class '<em>Table Binding</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Table Binding</em>'.
	 * @generated
	 */
	TableBinding createTableBinding();

	/**
	 * Returns a new object of class '<em>Field Binding</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Field Binding</em>'.
	 * @generated
	 */
	FieldBinding createFieldBinding();

	/**
	 * Returns a new object of class '<em>Form Panel</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Form Panel</em>'.
	 * @generated
	 */
	FormPanel createFormPanel();

	/**
	 * Returns a new object of class '<em>State Machine Folder</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>State Machine Folder</em>'.
	 * @generated
	 */
	StateMachineFolder createStateMachineFolder();

	/**
	 * Returns a new object of class '<em>Entity Folder</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Entity Folder</em>'.
	 * @generated
	 */
	EntityFolder createEntityFolder();

	/**
	 * Returns a new object of class '<em>Activity Folder</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Activity Folder</em>'.
	 * @generated
	 */
	ActivityFolder createActivityFolder();

	/**
	 * Returns a new object of class '<em>Navigation Binding</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Navigation Binding</em>'.
	 * @generated
	 */
	NavigationBinding createNavigationBinding();

	/**
	 * Returns a new object of class '<em>Detail Panel</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Detail Panel</em>'.
	 * @generated
	 */
	DetailPanel createDetailPanel();

	/**
	 * Returns a new object of class '<em>Package Folder</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Package Folder</em>'.
	 * @generated
	 */
	PackageFolder createPackageFolder();

	/**
	 * Returns a new object of class '<em>Tab Panel</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tab Panel</em>'.
	 * @generated
	 */
	UimTabPanel createUimTabPanel();

	/**
	 * Returns a new object of class '<em>Tab</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tab</em>'.
	 * @generated
	 */
	UimTab createUimTab();

	/**
	 * Returns a new object of class '<em>Check Box</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Check Box</em>'.
	 * @generated
	 */
	UimCheckBox createUimCheckBox();

	/**
	 * Returns a new object of class '<em>Lookup</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Lookup</em>'.
	 * @generated
	 */
	UimLookup createUimLookup();

	/**
	 * Returns a new object of class '<em>Lookup Binding</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Lookup Binding</em>'.
	 * @generated
	 */
	LookupBinding createLookupBinding();

	/**
	 * Returns a new object of class '<em>Text</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Text</em>'.
	 * @generated
	 */
	UimText createUimText();

	/**
	 * Returns a new object of class '<em>Text Area</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Text Area</em>'.
	 * @generated
	 */
	UimTextArea createUimTextArea();

	/**
	 * Returns a new object of class '<em>Dropdown</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Dropdown</em>'.
	 * @generated
	 */
	UimDropdown createUimDropdown();

	/**
	 * Returns a new object of class '<em>Date Popup</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Date Popup</em>'.
	 * @generated
	 */
	UimDatePopup createUimDatePopup();

	/**
	 * Returns a new object of class '<em>Single Select List Box</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Single Select List Box</em>'.
	 * @generated
	 */
	UimSingleSelectListBox createUimSingleSelectListBox();

	/**
	 * Returns a new object of class '<em>Container</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Container</em>'.
	 * @generated
	 */
	UimContainer createUimContainer();

	/**
	 * Returns a new object of class '<em>Single Select Tree View</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Single Select Tree View</em>'.
	 * @generated
	 */
	UimSingleSelectTreeView createUimSingleSelectTreeView();

	/**
	 * Returns a new object of class '<em>Layout</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Layout</em>'.
	 * @generated
	 */
	UimLayout createUimLayout();

	/**
	 * Returns a new object of class '<em>Toolbar Layout</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Toolbar Layout</em>'.
	 * @generated
	 */
	UimToolbarLayout createUimToolbarLayout();

	/**
	 * Returns a new object of class '<em>Border Layout</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Border Layout</em>'.
	 * @generated
	 */
	UimBorderLayout createUimBorderLayout();

	/**
	 * Returns a new object of class '<em>XY Layout</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>XY Layout</em>'.
	 * @generated
	 */
	UimXYLayout createUimXYLayout();

	/**
	 * Returns a new object of class '<em>Multi Select Tree View</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Multi Select Tree View</em>'.
	 * @generated
	 */
	UimMultiSelectTreeView createUimMultiSelectTreeView();

	/**
	 * Returns a new object of class '<em>Multi Select List Box</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Multi Select List Box</em>'.
	 * @generated
	 */
	UimMultiSelectListBox createUimMultiSelectListBox();

	/**
	 * Returns a new object of class '<em>Multi Select Popup Search</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Multi Select Popup Search</em>'.
	 * @generated
	 */
	UimMultiSelectPopupSearch createUimMultiSelectPopupSearch();

	/**
	 * Returns a new object of class '<em>Single Select Popup Search</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Single Select Popup Search</em>'.
	 * @generated
	 */
	UimSingleSelectPopupSearch createUimSingleSelectPopupSearch();

	/**
	 * Returns a new object of class '<em>Toggle Button</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Toggle Button</em>'.
	 * @generated
	 */
	UimToggleButton createUimToggleButton();

	/**
	 * Returns a new object of class '<em>Number Scroller</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Number Scroller</em>'.
	 * @generated
	 */
	UimNumberScroller createUimNumberScroller();

	/**
	 * Returns a new object of class '<em>Uml Reference</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Uml Reference</em>'.
	 * @generated
	 */
	UmlReference createUmlReference();

	/**
	 * Returns a new object of class '<em>User Interaction Workspace</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>User Interaction Workspace</em>'.
	 * @generated
	 */
	UserInteractionWorkspace createUserInteractionWorkspace();

	/**
	 * Returns a new object of class '<em>Required Role</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Required Role</em>'.
	 * @generated
	 */
	RequiredRole createRequiredRole();

	/**
	 * Returns a new object of class '<em>Secure Object</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Secure Object</em>'.
	 * @generated
	 */
	SecureObject createSecureObject();

	/**
	 * Returns a new object of class '<em>Editable Secure Object</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Editable Secure Object</em>'.
	 * @generated
	 */
	EditableSecureObject createEditableSecureObject();

	/**
	 * Returns a new object of class '<em>Full Layout</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Full Layout</em>'.
	 * @generated
	 */
	UimFullLayout createUimFullLayout();

	/**
	 * Returns a new object of class '<em>Panel</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Panel</em>'.
	 * @generated
	 */
	UimPanel createUimPanel();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	UimPackage getUimPackage();

} //UimFactory
