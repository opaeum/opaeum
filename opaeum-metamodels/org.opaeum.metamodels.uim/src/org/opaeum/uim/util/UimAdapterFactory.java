/**
 */
package org.opaeum.uim.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.opaeum.uim.*;
import org.opaeum.uim.AbstractActionBar;
import org.opaeum.uim.ClassUserInteractionModel;
import org.opaeum.uim.DetailComponent;
import org.opaeum.uim.MasterComponent;
import org.opaeum.uim.ObjectSelectorTree;
import org.opaeum.uim.Page;
import org.opaeum.uim.PageContainer;
import org.opaeum.uim.PanelClass;
import org.opaeum.uim.ResponsibilityUserInteractionModel;
import org.opaeum.uim.UimComponent;
import org.opaeum.uim.UimContainer;
import org.opaeum.uim.UimDataTable;
import org.opaeum.uim.UimField;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.UserInterface;
import org.opaeum.uim.UserInterfaceEntryPoint;
import org.opaeum.uim.constraint.ConstrainedObject;
import org.opaeum.uim.constraint.EditableConstrainedObject;
import org.opaeum.uim.panel.AbstractPanel;
import org.opaeum.uim.panel.Outlayable;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.opaeum.uim.UimPackage
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
			public Adapter caseUimField(UimField object) {
				return createUimFieldAdapter();
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
			public Adapter caseUimDataTable(UimDataTable object) {
				return createUimDataTableAdapter();
			}
			@Override
			public Adapter caseUimContainer(UimContainer object) {
				return createUimContainerAdapter();
			}
			@Override
			public Adapter caseMasterComponent(MasterComponent object) {
				return createMasterComponentAdapter();
			}
			@Override
			public Adapter caseUmlReference(UmlReference object) {
				return createUmlReferenceAdapter();
			}
			@Override
			public Adapter caseObjectSelectorTree(ObjectSelectorTree object) {
				return createObjectSelectorTreeAdapter();
			}
			@Override
			public Adapter caseDetailComponent(DetailComponent object) {
				return createDetailComponentAdapter();
			}
			@Override
			public Adapter caseUserInterfaceEntryPoint(UserInterfaceEntryPoint object) {
				return createUserInterfaceEntryPointAdapter();
			}
			@Override
			public Adapter casePage(Page object) {
				return createPageAdapter();
			}
			@Override
			public Adapter caseUserInterface(UserInterface object) {
				return createUserInterfaceAdapter();
			}
			@Override
			public Adapter casePanelClass(PanelClass object) {
				return createPanelClassAdapter();
			}
			@Override
			public Adapter caseClassUserInteractionModel(ClassUserInteractionModel object) {
				return createClassUserInteractionModelAdapter();
			}
			@Override
			public Adapter caseResponsibilityUserInteractionModel(ResponsibilityUserInteractionModel object) {
				return createResponsibilityUserInteractionModelAdapter();
			}
			@Override
			public Adapter caseAbstractActionBar(AbstractActionBar object) {
				return createAbstractActionBarAdapter();
			}
			@Override
			public Adapter casePageContainer(PageContainer object) {
				return createPageContainerAdapter();
			}
			@Override
			public Adapter caseUimRootElement(UimRootElement object) {
				return createUimRootElementAdapter();
			}
			@Override
			public Adapter caseConstrainedObject(ConstrainedObject object) {
				return createConstrainedObjectAdapter();
			}
			@Override
			public Adapter caseEditableConstrainedObject(EditableConstrainedObject object) {
				return createEditableConstrainedObjectAdapter();
			}
			@Override
			public Adapter caseOutlayable(Outlayable object) {
				return createOutlayableAdapter();
			}
			@Override
			public Adapter caseAbstractPanel(AbstractPanel object) {
				return createAbstractPanelAdapter();
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
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.UimField <em>Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.UimField
	 * @generated
	 */
	public Adapter createUimFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.UimComponent <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.UimComponent
	 * @generated
	 */
	public Adapter createUimComponentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.UserInteractionElement <em>User Interaction Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.UserInteractionElement
	 * @generated
	 */
	public Adapter createUserInteractionElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.UimDataTable <em>Data Table</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.UimDataTable
	 * @generated
	 */
	public Adapter createUimDataTableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.UimContainer <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.UimContainer
	 * @generated
	 */
	public Adapter createUimContainerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.MasterComponent <em>Master Component</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.MasterComponent
	 * @generated
	 */
	public Adapter createMasterComponentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.UmlReference <em>Uml Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.UmlReference
	 * @generated
	 */
	public Adapter createUmlReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.ObjectSelectorTree <em>Object Selector Tree</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.ObjectSelectorTree
	 * @generated
	 */
	public Adapter createObjectSelectorTreeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.DetailComponent <em>Detail Component</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.DetailComponent
	 * @generated
	 */
	public Adapter createDetailComponentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.UserInterfaceEntryPoint <em>User Interface Entry Point</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.UserInterfaceEntryPoint
	 * @generated
	 */
	public Adapter createUserInterfaceEntryPointAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.Page <em>Page</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.Page
	 * @generated
	 */
	public Adapter createPageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.UserInterface <em>User Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.UserInterface
	 * @generated
	 */
	public Adapter createUserInterfaceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.PanelClass <em>Panel Class</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.PanelClass
	 * @generated
	 */
	public Adapter createPanelClassAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.ClassUserInteractionModel <em>Class User Interaction Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.ClassUserInteractionModel
	 * @generated
	 */
	public Adapter createClassUserInteractionModelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.ResponsibilityUserInteractionModel <em>Responsibility User Interaction Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.ResponsibilityUserInteractionModel
	 * @generated
	 */
	public Adapter createResponsibilityUserInteractionModelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.AbstractActionBar <em>Abstract Action Bar</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.AbstractActionBar
	 * @generated
	 */
	public Adapter createAbstractActionBarAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.PageContainer <em>Page Container</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.PageContainer
	 * @generated
	 */
	public Adapter createPageContainerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.UimRootElement <em>Root Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.UimRootElement
	 * @generated
	 */
	public Adapter createUimRootElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.constraint.ConstrainedObject <em>Constrained Object</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.constraint.ConstrainedObject
	 * @generated
	 */
	public Adapter createConstrainedObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.constraint.EditableConstrainedObject <em>Editable Constrained Object</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.constraint.EditableConstrainedObject
	 * @generated
	 */
	public Adapter createEditableConstrainedObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.panel.Outlayable <em>Outlayable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.panel.Outlayable
	 * @generated
	 */
	public Adapter createOutlayableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.panel.AbstractPanel <em>Abstract Panel</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.panel.AbstractPanel
	 * @generated
	 */
	public Adapter createAbstractPanelAdapter() {
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
