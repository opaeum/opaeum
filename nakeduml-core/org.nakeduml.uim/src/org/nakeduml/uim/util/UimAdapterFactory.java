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
import org.nakeduml.uim.MasterComponent;
import org.nakeduml.uim.ObjectSelectorTree;
import org.nakeduml.uim.UimComponent;
import org.nakeduml.uim.UimContainer;
import org.nakeduml.uim.UimDataTable;
import org.nakeduml.uim.UimField;
import org.nakeduml.uim.UimPackage;
import org.nakeduml.uim.UimPanel;
import org.nakeduml.uim.UimTab;
import org.nakeduml.uim.UimTabPanel;
import org.nakeduml.uim.UmlReference;
import org.nakeduml.uim.UserInteractionElement;
import org.nakeduml.uim.layout.LayoutContainer;
import org.nakeduml.uim.layout.OutlayableComponent;
import org.nakeduml.uim.security.EditableSecureObject;
import org.nakeduml.uim.security.SecureObject;

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
			public Adapter caseUimTabPanel(UimTabPanel object) {
				return createUimTabPanelAdapter();
			}
			@Override
			public Adapter caseUimTab(UimTab object) {
				return createUimTabAdapter();
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
			public Adapter caseUimPanel(UimPanel object) {
				return createUimPanelAdapter();
			}
			@Override
			public Adapter caseObjectSelectorTree(ObjectSelectorTree object) {
				return createObjectSelectorTreeAdapter();
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
			public Adapter caseOutlayableComponent(OutlayableComponent object) {
				return createOutlayableComponentAdapter();
			}
			@Override
			public Adapter caseLayoutContainer(LayoutContainer object) {
				return createLayoutContainerAdapter();
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
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.ObjectSelectorTree <em>Object Selector Tree</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.ObjectSelectorTree
	 * @generated
	 */
	public Adapter createObjectSelectorTreeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.security.SecureObject <em>Secure Object</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.security.SecureObject
	 * @generated
	 */
	public Adapter createSecureObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.security.EditableSecureObject <em>Editable Secure Object</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.security.EditableSecureObject
	 * @generated
	 */
	public Adapter createEditableSecureObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.layout.OutlayableComponent <em>Outlayable Component</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.layout.OutlayableComponent
	 * @generated
	 */
	public Adapter createOutlayableComponentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.layout.LayoutContainer <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.layout.LayoutContainer
	 * @generated
	 */
	public Adapter createLayoutContainerAdapter() {
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
