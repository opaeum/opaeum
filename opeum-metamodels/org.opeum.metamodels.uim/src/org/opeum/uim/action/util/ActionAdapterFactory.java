/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opeum.uim.action.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.opeum.uim.UimComponent;
import org.opeum.uim.UmlReference;
import org.opeum.uim.UserInteractionElement;
import org.opeum.uim.action.ActionPackage;
import org.opeum.uim.action.BuiltInAction;
import org.opeum.uim.action.NavigationToEntity;
import org.opeum.uim.action.NavigationToOperation;
import org.opeum.uim.action.OperationAction;
import org.opeum.uim.action.TransitionAction;
import org.opeum.uim.action.UimAction;
import org.opeum.uim.action.UimNavigation;
import org.opeum.uim.layout.OutlayableComponent;
import org.opeum.uim.security.SecureObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.opeum.uim.action.ActionPackage
 * @generated
 */
public class ActionAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ActionPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActionAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = ActionPackage.eINSTANCE;
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
	protected ActionSwitch<Adapter> modelSwitch =
		new ActionSwitch<Adapter>() {
			@Override
			public Adapter caseBuiltInAction(BuiltInAction object) {
				return createBuiltInActionAdapter();
			}
			@Override
			public Adapter caseUimAction(UimAction object) {
				return createUimActionAdapter();
			}
			@Override
			public Adapter caseTransitionAction(TransitionAction object) {
				return createTransitionActionAdapter();
			}
			@Override
			public Adapter caseNavigationToOperation(NavigationToOperation object) {
				return createNavigationToOperationAdapter();
			}
			@Override
			public Adapter caseOperationAction(OperationAction object) {
				return createOperationActionAdapter();
			}
			@Override
			public Adapter caseUimNavigation(UimNavigation object) {
				return createUimNavigationAdapter();
			}
			@Override
			public Adapter caseNavigationToEntity(NavigationToEntity object) {
				return createNavigationToEntityAdapter();
			}
			@Override
			public Adapter caseUserInteractionElement(UserInteractionElement object) {
				return createUserInteractionElementAdapter();
			}
			@Override
			public Adapter caseSecureObject(SecureObject object) {
				return createSecureObjectAdapter();
			}
			@Override
			public Adapter caseUimComponent(UimComponent object) {
				return createUimComponentAdapter();
			}
			@Override
			public Adapter caseOutlayableComponent(OutlayableComponent object) {
				return createOutlayableComponentAdapter();
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
	 * Creates a new adapter for an object of class '{@link org.opeum.uim.action.BuiltInAction <em>Built In Action</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opeum.uim.action.BuiltInAction
	 * @generated
	 */
	public Adapter createBuiltInActionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opeum.uim.action.UimAction <em>Uim Action</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opeum.uim.action.UimAction
	 * @generated
	 */
	public Adapter createUimActionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opeum.uim.action.TransitionAction <em>Transition Action</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opeum.uim.action.TransitionAction
	 * @generated
	 */
	public Adapter createTransitionActionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opeum.uim.action.NavigationToOperation <em>Navigation To Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opeum.uim.action.NavigationToOperation
	 * @generated
	 */
	public Adapter createNavigationToOperationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opeum.uim.action.OperationAction <em>Operation Action</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opeum.uim.action.OperationAction
	 * @generated
	 */
	public Adapter createOperationActionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opeum.uim.action.UimNavigation <em>Uim Navigation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opeum.uim.action.UimNavigation
	 * @generated
	 */
	public Adapter createUimNavigationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opeum.uim.action.NavigationToEntity <em>Navigation To Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opeum.uim.action.NavigationToEntity
	 * @generated
	 */
	public Adapter createNavigationToEntityAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opeum.uim.UserInteractionElement <em>User Interaction Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opeum.uim.UserInteractionElement
	 * @generated
	 */
	public Adapter createUserInteractionElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opeum.uim.security.SecureObject <em>Secure Object</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opeum.uim.security.SecureObject
	 * @generated
	 */
	public Adapter createSecureObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opeum.uim.UimComponent <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opeum.uim.UimComponent
	 * @generated
	 */
	public Adapter createUimComponentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opeum.uim.layout.OutlayableComponent <em>Outlayable Component</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opeum.uim.layout.OutlayableComponent
	 * @generated
	 */
	public Adapter createOutlayableComponentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opeum.uim.UmlReference <em>Uml Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opeum.uim.UmlReference
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

} //ActionAdapterFactory
