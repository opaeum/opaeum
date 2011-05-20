/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.nakeduml.uim.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.nakeduml.uim.UimPackage
 * @generated
 */
public class UimAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static UimPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = UimPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UimSwitch<Adapter> modelSwitch =
		new UimSwitch<Adapter>() {
			@Override
			public Adapter caseUimForm(UimForm object) {
				return createUimFormAdapter();
			}
			@Override
			public Adapter caseUserInteractionModel(UserInteractionModel object) {
				return createUserInteractionModelAdapter();
			}
			@Override
			public Adapter caseAbstractFormFolder(AbstractFormFolder object) {
				return createAbstractFormFolderAdapter();
			}
			@Override
			public Adapter caseAbstractFolder(AbstractFolder object) {
				return createAbstractFolderAdapter();
			}
			@Override
			public Adapter caseUimField(UimField object) {
				return createUimFieldAdapter();
			}
			@Override
			public Adapter caseUimNavigation(UimNavigation object) {
				return createUimNavigationAdapter();
			}
			@Override
			public Adapter caseLayoutContainer(LayoutContainer object) {
				return createLayoutContainerAdapter();
			}
			@Override
			public Adapter caseClassForm(ClassForm object) {
				return createClassFormAdapter();
			}
			@Override
			public Adapter caseStateForm(StateForm object) {
				return createStateFormAdapter();
			}
			@Override
			public Adapter caseWorkspaceSecurityConstraint(WorkspaceSecurityConstraint object) {
				return createWorkspaceSecurityConstraintAdapter();
			}
			@Override
			public Adapter caseOperationInvocationForm(OperationInvocationForm object) {
				return createOperationInvocationFormAdapter();
			}
			@Override
			public Adapter caseUimComponent(UimComponent object) {
				return createUimComponentAdapter();
			}
			@Override
			public Adapter caseUserInteractionElement(UserInteractionElement object) {
				return createUserInteractionElementAdapter();
			}
			@Override
			public Adapter caseOperationAction(OperationAction object) {
				return createOperationActionAdapter();
			}
			@Override
			public Adapter caseNavigationToOperation(NavigationToOperation object) {
				return createNavigationToOperationAdapter();
			}
			@Override
			public Adapter caseBuiltInAction(BuiltInAction object) {
				return createBuiltInActionAdapter();
			}
			@Override
			public Adapter caseUimControl(UimControl object) {
				return createUimControlAdapter();
			}
			@Override
			public Adapter caseNavigationToEntity(NavigationToEntity object) {
				return createNavigationToEntityAdapter();
			}
			@Override
			public Adapter caseTransitionAction(TransitionAction object) {
				return createTransitionActionAdapter();
			}
			@Override
			public Adapter caseOperationTaskForm(OperationTaskForm object) {
				return createOperationTaskFormAdapter();
			}
			@Override
			public Adapter caseActionTaskForm(ActionTaskForm object) {
				return createActionTaskFormAdapter();
			}
			@Override
			public Adapter caseUimAction(UimAction object) {
				return createUimActionAdapter();
			}
			@Override
			public Adapter caseSecurityConstraint(SecurityConstraint object) {
				return createSecurityConstraintAdapter();
			}
			@Override
			public Adapter caseUimGridLayout(UimGridLayout object) {
				return createUimGridLayoutAdapter();
			}
			@Override
			public Adapter caseUimDataTable(UimDataTable object) {
				return createUimDataTableAdapter();
			}
			@Override
			public Adapter caseUimBinding(UimBinding object) {
				return createUimBindingAdapter();
			}
			@Override
			public Adapter casePropertyRef(PropertyRef object) {
				return createPropertyRefAdapter();
			}
			@Override
			public Adapter caseUimDataColumn(UimDataColumn object) {
				return createUimDataColumnAdapter();
			}
			@Override
			public Adapter caseTableBinding(TableBinding object) {
				return createTableBindingAdapter();
			}
			@Override
			public Adapter caseFieldBinding(FieldBinding object) {
				return createFieldBindingAdapter();
			}
			@Override
			public Adapter caseFormPanel(FormPanel object) {
				return createFormPanelAdapter();
			}
			@Override
			public Adapter caseStateMachineFolder(StateMachineFolder object) {
				return createStateMachineFolderAdapter();
			}
			@Override
			public Adapter caseEntityFolder(EntityFolder object) {
				return createEntityFolderAdapter();
			}
			@Override
			public Adapter caseActivityFolder(ActivityFolder object) {
				return createActivityFolderAdapter();
			}
			@Override
			public Adapter caseOperationContainingFolder(OperationContainingFolder object) {
				return createOperationContainingFolderAdapter();
			}
			@Override
			public Adapter caseNavigationBinding(NavigationBinding object) {
				return createNavigationBindingAdapter();
			}
			@Override
			public Adapter caseDetailPanel(DetailPanel object) {
				return createDetailPanelAdapter();
			}
			@Override
			public Adapter casePackageFolder(PackageFolder object) {
				return createPackageFolderAdapter();
			}
			@Override
			public Adapter caseUimTabPanel(UimTabPanel object) {
				return createUimTabPanelAdapter();
			}
			@Override
			public Adapter caseUimTab(UimTab object) {
				return createUimTabAdapter();
			}
			@Override
			public Adapter caseUimCheckBox(UimCheckBox object) {
				return createUimCheckBoxAdapter();
			}
			@Override
			public Adapter caseUimLookup(UimLookup object) {
				return createUimLookupAdapter();
			}
			@Override
			public Adapter caseLookupBinding(LookupBinding object) {
				return createLookupBindingAdapter();
			}
			@Override
			public Adapter caseUimText(UimText object) {
				return createUimTextAdapter();
			}
			@Override
			public Adapter caseUimTextArea(UimTextArea object) {
				return createUimTextAreaAdapter();
			}
			@Override
			public Adapter caseUimDropdown(UimDropdown object) {
				return createUimDropdownAdapter();
			}
			@Override
			public Adapter caseUimDatePopup(UimDatePopup object) {
				return createUimDatePopupAdapter();
			}
			@Override
			public Adapter caseUimSingleSelectListBox(UimSingleSelectListBox object) {
				return createUimSingleSelectListBoxAdapter();
			}
			@Override
			public Adapter caseUimContainer(UimContainer object) {
				return createUimContainerAdapter();
			}
			@Override
			public Adapter caseUimSingleSelectTreeView(UimSingleSelectTreeView object) {
				return createUimSingleSelectTreeViewAdapter();
			}
			@Override
			public Adapter caseMasterComponent(MasterComponent object) {
				return createMasterComponentAdapter();
			}
			@Override
			public Adapter caseUimLayout(UimLayout object) {
				return createUimLayoutAdapter();
			}
			@Override
			public Adapter caseUimToolbarLayout(UimToolbarLayout object) {
				return createUimToolbarLayoutAdapter();
			}
			@Override
			public Adapter caseUimBorderLayout(UimBorderLayout object) {
				return createUimBorderLayoutAdapter();
			}
			@Override
			public Adapter caseUimXYLayout(UimXYLayout object) {
				return createUimXYLayoutAdapter();
			}
			@Override
			public Adapter caseUimMultiSelectTreeView(UimMultiSelectTreeView object) {
				return createUimMultiSelectTreeViewAdapter();
			}
			@Override
			public Adapter caseUimMultiSelectListBox(UimMultiSelectListBox object) {
				return createUimMultiSelectListBoxAdapter();
			}
			@Override
			public Adapter caseUimMultiSelectPopupSearch(UimMultiSelectPopupSearch object) {
				return createUimMultiSelectPopupSearchAdapter();
			}
			@Override
			public Adapter caseUimSingleSelectPopupSearch(UimSingleSelectPopupSearch object) {
				return createUimSingleSelectPopupSearchAdapter();
			}
			@Override
			public Adapter caseUimToggleButton(UimToggleButton object) {
				return createUimToggleButtonAdapter();
			}
			@Override
			public Adapter caseUimNumberScroller(UimNumberScroller object) {
				return createUimNumberScrollerAdapter();
			}
			@Override
			public Adapter caseUmlReference(UmlReference object) {
				return createUmlReferenceAdapter();
			}
			@Override
			public Adapter caseUserInteractionWorkspace(UserInteractionWorkspace object) {
				return createUserInteractionWorkspaceAdapter();
			}
			@Override
			public Adapter caseRequiredRole(RequiredRole object) {
				return createRequiredRoleAdapter();
			}
			@Override
			public Adapter caseSecureObject(SecureObject object) {
				return createSecureObjectAdapter();
			}
			@Override
			public Adapter caseEditableSecureObject(EditableSecureObject object) {
				return createEditableSecureObjectAdapter();
			}
			@Override
			public Adapter caseUimFullLayout(UimFullLayout object) {
				return createUimFullLayoutAdapter();
			}
			@Override
			public Adapter caseOutlayableComponent(OutlayableComponent object) {
				return createOutlayableComponentAdapter();
			}
			@Override
			public Adapter caseUimPanel(UimPanel object) {
				return createUimPanelAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.UimForm <em>Form</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.UimForm
	 * @generated
	 */
	public Adapter createUimFormAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.UserInteractionModel <em>User Interaction Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.UserInteractionModel
	 * @generated
	 */
	public Adapter createUserInteractionModelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.AbstractFormFolder <em>Abstract Form Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.AbstractFormFolder
	 * @generated
	 */
	public Adapter createAbstractFormFolderAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.AbstractFolder <em>Abstract Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.AbstractFolder
	 * @generated
	 */
	public Adapter createAbstractFolderAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.UimField <em>Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.UimField
	 * @generated
	 */
	public Adapter createUimFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.UimNavigation <em>Navigation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.UimNavigation
	 * @generated
	 */
	public Adapter createUimNavigationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.LayoutContainer <em>Layout Container</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.LayoutContainer
	 * @generated
	 */
	public Adapter createLayoutContainerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.ClassForm <em>Class Form</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.ClassForm
	 * @generated
	 */
	public Adapter createClassFormAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.StateForm <em>State Form</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.StateForm
	 * @generated
	 */
	public Adapter createStateFormAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.WorkspaceSecurityConstraint <em>Workspace Security Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.WorkspaceSecurityConstraint
	 * @generated
	 */
	public Adapter createWorkspaceSecurityConstraintAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.OperationInvocationForm <em>Operation Invocation Form</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.OperationInvocationForm
	 * @generated
	 */
	public Adapter createOperationInvocationFormAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.UimComponent <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.UimComponent
	 * @generated
	 */
	public Adapter createUimComponentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.UserInteractionElement <em>User Interaction Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.UserInteractionElement
	 * @generated
	 */
	public Adapter createUserInteractionElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.OperationAction <em>Operation Action</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.OperationAction
	 * @generated
	 */
	public Adapter createOperationActionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.NavigationToOperation <em>Navigation To Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.NavigationToOperation
	 * @generated
	 */
	public Adapter createNavigationToOperationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.BuiltInAction <em>Built In Action</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.BuiltInAction
	 * @generated
	 */
	public Adapter createBuiltInActionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.UimControl <em>Control</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.UimControl
	 * @generated
	 */
	public Adapter createUimControlAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.NavigationToEntity <em>Navigation To Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.NavigationToEntity
	 * @generated
	 */
	public Adapter createNavigationToEntityAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.TransitionAction <em>Transition Action</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.TransitionAction
	 * @generated
	 */
	public Adapter createTransitionActionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.OperationTaskForm <em>Operation Task Form</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.OperationTaskForm
	 * @generated
	 */
	public Adapter createOperationTaskFormAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.ActionTaskForm <em>Action Task Form</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.ActionTaskForm
	 * @generated
	 */
	public Adapter createActionTaskFormAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.UimAction <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.UimAction
	 * @generated
	 */
	public Adapter createUimActionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.SecurityConstraint <em>Security Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.SecurityConstraint
	 * @generated
	 */
	public Adapter createSecurityConstraintAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.UimGridLayout <em>Grid Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.UimGridLayout
	 * @generated
	 */
	public Adapter createUimGridLayoutAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.UimDataTable <em>Data Table</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.UimDataTable
	 * @generated
	 */
	public Adapter createUimDataTableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.UimBinding <em>Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.UimBinding
	 * @generated
	 */
	public Adapter createUimBindingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.PropertyRef <em>Property Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.PropertyRef
	 * @generated
	 */
	public Adapter createPropertyRefAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.UimDataColumn <em>Data Column</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.UimDataColumn
	 * @generated
	 */
	public Adapter createUimDataColumnAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.TableBinding <em>Table Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.TableBinding
	 * @generated
	 */
	public Adapter createTableBindingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.FieldBinding <em>Field Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.FieldBinding
	 * @generated
	 */
	public Adapter createFieldBindingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.FormPanel <em>Form Panel</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.FormPanel
	 * @generated
	 */
	public Adapter createFormPanelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.StateMachineFolder <em>State Machine Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.StateMachineFolder
	 * @generated
	 */
	public Adapter createStateMachineFolderAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.EntityFolder <em>Entity Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.EntityFolder
	 * @generated
	 */
	public Adapter createEntityFolderAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.ActivityFolder <em>Activity Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.ActivityFolder
	 * @generated
	 */
	public Adapter createActivityFolderAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.OperationContainingFolder <em>Operation Containing Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.OperationContainingFolder
	 * @generated
	 */
	public Adapter createOperationContainingFolderAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.NavigationBinding <em>Navigation Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.NavigationBinding
	 * @generated
	 */
	public Adapter createNavigationBindingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.DetailPanel <em>Detail Panel</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.DetailPanel
	 * @generated
	 */
	public Adapter createDetailPanelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.PackageFolder <em>Package Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.PackageFolder
	 * @generated
	 */
	public Adapter createPackageFolderAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.UimTabPanel <em>Tab Panel</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.UimTabPanel
	 * @generated
	 */
	public Adapter createUimTabPanelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.UimTab <em>Tab</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.UimTab
	 * @generated
	 */
	public Adapter createUimTabAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.UimCheckBox <em>Check Box</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.UimCheckBox
	 * @generated
	 */
	public Adapter createUimCheckBoxAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.UimLookup <em>Lookup</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.UimLookup
	 * @generated
	 */
	public Adapter createUimLookupAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.LookupBinding <em>Lookup Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.LookupBinding
	 * @generated
	 */
	public Adapter createLookupBindingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.UimText <em>Text</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.UimText
	 * @generated
	 */
	public Adapter createUimTextAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.UimTextArea <em>Text Area</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.UimTextArea
	 * @generated
	 */
	public Adapter createUimTextAreaAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.UimDropdown <em>Dropdown</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.UimDropdown
	 * @generated
	 */
	public Adapter createUimDropdownAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.UimDatePopup <em>Date Popup</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.UimDatePopup
	 * @generated
	 */
	public Adapter createUimDatePopupAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.UimSingleSelectListBox <em>Single Select List Box</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.UimSingleSelectListBox
	 * @generated
	 */
	public Adapter createUimSingleSelectListBoxAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.UimContainer <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.UimContainer
	 * @generated
	 */
	public Adapter createUimContainerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.UimSingleSelectTreeView <em>Single Select Tree View</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.UimSingleSelectTreeView
	 * @generated
	 */
	public Adapter createUimSingleSelectTreeViewAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.MasterComponent <em>Master Component</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.MasterComponent
	 * @generated
	 */
	public Adapter createMasterComponentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.UimLayout <em>Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.UimLayout
	 * @generated
	 */
	public Adapter createUimLayoutAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.UimToolbarLayout <em>Toolbar Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.UimToolbarLayout
	 * @generated
	 */
	public Adapter createUimToolbarLayoutAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.UimBorderLayout <em>Border Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.UimBorderLayout
	 * @generated
	 */
	public Adapter createUimBorderLayoutAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.UimXYLayout <em>XY Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.UimXYLayout
	 * @generated
	 */
	public Adapter createUimXYLayoutAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.UimMultiSelectTreeView <em>Multi Select Tree View</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.UimMultiSelectTreeView
	 * @generated
	 */
	public Adapter createUimMultiSelectTreeViewAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.UimMultiSelectListBox <em>Multi Select List Box</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.UimMultiSelectListBox
	 * @generated
	 */
	public Adapter createUimMultiSelectListBoxAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.UimMultiSelectPopupSearch <em>Multi Select Popup Search</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.UimMultiSelectPopupSearch
	 * @generated
	 */
	public Adapter createUimMultiSelectPopupSearchAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.UimSingleSelectPopupSearch <em>Single Select Popup Search</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.UimSingleSelectPopupSearch
	 * @generated
	 */
	public Adapter createUimSingleSelectPopupSearchAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.UimToggleButton <em>Toggle Button</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.UimToggleButton
	 * @generated
	 */
	public Adapter createUimToggleButtonAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.UimNumberScroller <em>Number Scroller</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.UimNumberScroller
	 * @generated
	 */
	public Adapter createUimNumberScrollerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.UmlReference <em>Uml Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.UmlReference
	 * @generated
	 */
	public Adapter createUmlReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.UserInteractionWorkspace <em>User Interaction Workspace</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.UserInteractionWorkspace
	 * @generated
	 */
	public Adapter createUserInteractionWorkspaceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.RequiredRole <em>Required Role</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.RequiredRole
	 * @generated
	 */
	public Adapter createRequiredRoleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.SecureObject <em>Secure Object</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.SecureObject
	 * @generated
	 */
	public Adapter createSecureObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.EditableSecureObject <em>Editable Secure Object</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.EditableSecureObject
	 * @generated
	 */
	public Adapter createEditableSecureObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.UimFullLayout <em>Full Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.UimFullLayout
	 * @generated
	 */
	public Adapter createUimFullLayoutAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.OutlayableComponent <em>Outlayable Component</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.OutlayableComponent
	 * @generated
	 */
	public Adapter createOutlayableComponentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.UimPanel <em>Panel</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.UimPanel
	 * @generated
	 */
	public Adapter createUimPanelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //UimAdapterFactory
