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
 * @see org.nakeduml.uim.UIMPackage
 * @generated
 */
public interface UIMFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	UIMFactory eINSTANCE = org.nakeduml.uim.impl.UIMFactoryImpl.init();

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
	UIMField createUIMField();

	/**
	 * Returns a new object of class '<em>Panel</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Panel</em>'.
	 * @generated
	 */
	UIMPanel createUIMPanel();

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
	 * Returns a new object of class '<em>Model Security Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Model Security Constraint</em>'.
	 * @generated
	 */
	ModelSecurityConstraint createModelSecurityConstraint();

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
	UIMControl createUIMControl();

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
	 * Returns a new object of class '<em>Child Security Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Child Security Constraint</em>'.
	 * @generated
	 */
	ChildSecurityConstraint createChildSecurityConstraint();

	/**
	 * Returns a new object of class '<em>Grid Layout</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Grid Layout</em>'.
	 * @generated
	 */
	UIMGridLayout createUIMGridLayout();

	/**
	 * Returns a new object of class '<em>Data Table</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Data Table</em>'.
	 * @generated
	 */
	UIMDataTable createUIMDataTable();

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
	UIMDataColumn createUIMDataColumn();

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
	UIMTabPanel createUIMTabPanel();

	/**
	 * Returns a new object of class '<em>Tab</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tab</em>'.
	 * @generated
	 */
	UIMTab createUIMTab();

	/**
	 * Returns a new object of class '<em>Check Box</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Check Box</em>'.
	 * @generated
	 */
	UIMCheckBox createUIMCheckBox();

	/**
	 * Returns a new object of class '<em>Lookup</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Lookup</em>'.
	 * @generated
	 */
	UIMLookup createUIMLookup();

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
	UIMText createUIMText();

	/**
	 * Returns a new object of class '<em>Text Area</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Text Area</em>'.
	 * @generated
	 */
	UIMTextArea createUIMTextArea();

	/**
	 * Returns a new object of class '<em>Dropdown</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Dropdown</em>'.
	 * @generated
	 */
	UIMDropdown createUIMDropdown();

	/**
	 * Returns a new object of class '<em>Date Popup</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Date Popup</em>'.
	 * @generated
	 */
	UIMDatePopup createUIMDatePopup();

	/**
	 * Returns a new object of class '<em>Single Select List Box</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Single Select List Box</em>'.
	 * @generated
	 */
	UIMSingleSelectListBox createUIMSingleSelectListBox();

	/**
	 * Returns a new object of class '<em>Container</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Container</em>'.
	 * @generated
	 */
	UIMContainer createUIMContainer();

	/**
	 * Returns a new object of class '<em>Single Select Tree View</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Single Select Tree View</em>'.
	 * @generated
	 */
	UIMSingleSelectTreeView createUIMSingleSelectTreeView();

	/**
	 * Returns a new object of class '<em>Layout</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Layout</em>'.
	 * @generated
	 */
	UIMLayout createUIMLayout();

	/**
	 * Returns a new object of class '<em>Toolbar Layout</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Toolbar Layout</em>'.
	 * @generated
	 */
	UIMToolbarLayout createUIMToolbarLayout();

	/**
	 * Returns a new object of class '<em>Border Layout</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Border Layout</em>'.
	 * @generated
	 */
	UIMBorderLayout createUIMBorderLayout();

	/**
	 * Returns a new object of class '<em>XY Layout</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>XY Layout</em>'.
	 * @generated
	 */
	UIMXYLayout createUIMXYLayout();

	/**
	 * Returns a new object of class '<em>Multi Select Tree View</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Multi Select Tree View</em>'.
	 * @generated
	 */
	UIMMultiSelectTreeView createUIMMultiSelectTreeView();

	/**
	 * Returns a new object of class '<em>Multi Select List Box</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Multi Select List Box</em>'.
	 * @generated
	 */
	UIMMultiSelectListBox createUIMMultiSelectListBox();

	/**
	 * Returns a new object of class '<em>Multi Select Popup Search</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Multi Select Popup Search</em>'.
	 * @generated
	 */
	UIMMultiSelectPopupSearch createUIMMultiSelectPopupSearch();

	/**
	 * Returns a new object of class '<em>Single Select Popup Search</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Single Select Popup Search</em>'.
	 * @generated
	 */
	UIMSingleSelectPopupSearch createUIMSingleSelectPopupSearch();

	/**
	 * Returns a new object of class '<em>Toggle Button</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Toggle Button</em>'.
	 * @generated
	 */
	UIMToggleButton createUIMToggleButton();

	/**
	 * Returns a new object of class '<em>Number Scroller</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Number Scroller</em>'.
	 * @generated
	 */
	UIMNumberScroller createUIMNumberScroller();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	UIMPackage getUIMPackage();

} //UIMFactory
