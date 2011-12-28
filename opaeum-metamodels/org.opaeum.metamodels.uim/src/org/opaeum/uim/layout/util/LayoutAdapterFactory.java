/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opaeum.uim.layout.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.opaeum.uim.UimComponent;
import org.opaeum.uim.UimContainer;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.layout.*;
import org.opaeum.uim.layout.LayoutContainer;
import org.opaeum.uim.layout.LayoutPackage;
import org.opaeum.uim.layout.OutlayableComponent;
import org.opaeum.uim.layout.UimBorderLayout;
import org.opaeum.uim.layout.UimColumnLayout;
import org.opaeum.uim.layout.UimFullLayout;
import org.opaeum.uim.layout.UimGridLayout;
import org.opaeum.uim.layout.UimLayout;
import org.opaeum.uim.layout.UimToolbarLayout;
import org.opaeum.uim.layout.UimXYLayout;
import org.opaeum.uim.security.EditableSecureObject;
import org.opaeum.uim.security.SecureObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.opaeum.uim.layout.LayoutPackage
 * @generated
 */
public class LayoutAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static LayoutPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LayoutAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = LayoutPackage.eINSTANCE;
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
	protected LayoutSwitch<Adapter> modelSwitch =
		new LayoutSwitch<Adapter>() {
			@Override
			public Adapter caseUimColumnLayout(UimColumnLayout object) {
				return createUimColumnLayoutAdapter();
			}
			@Override
			public Adapter caseUimFullLayout(UimFullLayout object) {
				return createUimFullLayoutAdapter();
			}
			@Override
			public Adapter caseUimXYLayout(UimXYLayout object) {
				return createUimXYLayoutAdapter();
			}
			@Override
			public Adapter caseUimBorderLayout(UimBorderLayout object) {
				return createUimBorderLayoutAdapter();
			}
			@Override
			public Adapter caseUimToolbarLayout(UimToolbarLayout object) {
				return createUimToolbarLayoutAdapter();
			}
			@Override
			public Adapter caseOutlayableComponent(OutlayableComponent object) {
				return createOutlayableComponentAdapter();
			}
			@Override
			public Adapter caseUimLayout(UimLayout object) {
				return createUimLayoutAdapter();
			}
			@Override
			public Adapter caseLayoutContainer(LayoutContainer object) {
				return createLayoutContainerAdapter();
			}
			@Override
			public Adapter caseUimGridLayout(UimGridLayout object) {
				return createUimGridLayoutAdapter();
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
			public Adapter caseEditableSecureObject(EditableSecureObject object) {
				return createEditableSecureObjectAdapter();
			}
			@Override
			public Adapter caseUimContainer(UimContainer object) {
				return createUimContainerAdapter();
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
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.layout.UimColumnLayout <em>Uim Column Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.layout.UimColumnLayout
	 * @generated
	 */
	public Adapter createUimColumnLayoutAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.layout.UimFullLayout <em>Uim Full Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.layout.UimFullLayout
	 * @generated
	 */
	public Adapter createUimFullLayoutAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.layout.UimXYLayout <em>Uim XY Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.layout.UimXYLayout
	 * @generated
	 */
	public Adapter createUimXYLayoutAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.layout.UimBorderLayout <em>Uim Border Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.layout.UimBorderLayout
	 * @generated
	 */
	public Adapter createUimBorderLayoutAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.layout.UimToolbarLayout <em>Uim Toolbar Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.layout.UimToolbarLayout
	 * @generated
	 */
	public Adapter createUimToolbarLayoutAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.layout.OutlayableComponent <em>Outlayable Component</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.layout.OutlayableComponent
	 * @generated
	 */
	public Adapter createOutlayableComponentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.layout.UimLayout <em>Uim Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.layout.UimLayout
	 * @generated
	 */
	public Adapter createUimLayoutAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.layout.LayoutContainer <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.layout.LayoutContainer
	 * @generated
	 */
	public Adapter createLayoutContainerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.layout.UimGridLayout <em>Uim Grid Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.layout.UimGridLayout
	 * @generated
	 */
	public Adapter createUimGridLayoutAdapter() {
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
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.security.SecureObject <em>Secure Object</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.security.SecureObject
	 * @generated
	 */
	public Adapter createSecureObjectAdapter() {
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
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.security.EditableSecureObject <em>Editable Secure Object</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.security.EditableSecureObject
	 * @generated
	 */
	public Adapter createEditableSecureObjectAdapter() {
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

} //LayoutAdapterFactory
