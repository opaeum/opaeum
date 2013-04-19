/**
 */
package org.opaeum.uim.component.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.opaeum.uim.LabelContainer;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.component.ComponentPackage;
import org.opaeum.uim.component.DetailComponent;
import org.opaeum.uim.component.MasterComponent;
import org.opaeum.uim.component.MasterTree;
import org.opaeum.uim.component.PanelForClass;
import org.opaeum.uim.component.UimComponent;
import org.opaeum.uim.component.UimContainer;
import org.opaeum.uim.component.UimDataTable;
import org.opaeum.uim.component.UimField;
import org.opaeum.uim.constraint.ConstrainedObject;
import org.opaeum.uim.constraint.EditableConstrainedObject;
import org.opaeum.uim.panel.Outlayable;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.opaeum.uim.component.ComponentPackage
 * @generated
 */
public class ComponentAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ComponentPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = ComponentPackage.eINSTANCE;
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
	protected ComponentSwitch<Adapter> modelSwitch =
		new ComponentSwitch<Adapter>() {
			@Override
			public Adapter caseUimField(UimField object) {
				return createUimFieldAdapter();
			}
			@Override
			public Adapter caseUimComponent(UimComponent object) {
				return createUimComponentAdapter();
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
			public Adapter caseMasterTree(MasterTree object) {
				return createMasterTreeAdapter();
			}
			@Override
			public Adapter caseDetailComponent(DetailComponent object) {
				return createDetailComponentAdapter();
			}
			@Override
			public Adapter casePanelForClass(PanelForClass object) {
				return createPanelForClassAdapter();
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
			public Adapter caseUserInteractionElement(UserInteractionElement object) {
				return createUserInteractionElementAdapter();
			}
			@Override
			public Adapter caseLabelContainer(LabelContainer object) {
				return createLabelContainerAdapter();
			}
			@Override
			public Adapter caseOutlayable(Outlayable object) {
				return createOutlayableAdapter();
			}
			@Override
			public Adapter caseUmlReference(UmlReference object) {
				return createUmlReferenceAdapter();
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
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.component.UimField <em>Uim Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.component.UimField
	 * @generated
	 */
	public Adapter createUimFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.component.UimComponent <em>Uim Component</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.component.UimComponent
	 * @generated
	 */
	public Adapter createUimComponentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.component.UimDataTable <em>Uim Data Table</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.component.UimDataTable
	 * @generated
	 */
	public Adapter createUimDataTableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.component.UimContainer <em>Uim Container</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.component.UimContainer
	 * @generated
	 */
	public Adapter createUimContainerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.component.MasterComponent <em>Master Component</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.component.MasterComponent
	 * @generated
	 */
	public Adapter createMasterComponentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.component.MasterTree <em>Master Tree</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.component.MasterTree
	 * @generated
	 */
	public Adapter createMasterTreeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.component.DetailComponent <em>Detail Component</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.component.DetailComponent
	 * @generated
	 */
	public Adapter createDetailComponentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.component.PanelForClass <em>Panel For Class</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.component.PanelForClass
	 * @generated
	 */
	public Adapter createPanelForClassAdapter() {
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
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.LabelContainer <em>Label Container</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.LabelContainer
	 * @generated
	 */
	public Adapter createLabelContainerAdapter() {
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

} //ComponentAdapterFactory
