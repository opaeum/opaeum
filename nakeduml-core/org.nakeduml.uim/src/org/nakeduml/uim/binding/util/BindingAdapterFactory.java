/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim.binding.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.nakeduml.uim.UmlReference;
import org.nakeduml.uim.binding.BindingPackage;
import org.nakeduml.uim.binding.FieldBinding;
import org.nakeduml.uim.binding.LookupBinding;
import org.nakeduml.uim.binding.NavigationBinding;
import org.nakeduml.uim.binding.PropertyRef;
import org.nakeduml.uim.binding.TableBinding;
import org.nakeduml.uim.binding.UimBinding;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.nakeduml.uim.binding.BindingPackage
 * @generated
 */
public class BindingAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static BindingPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BindingAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = BindingPackage.eINSTANCE;
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
	protected BindingSwitch<Adapter> modelSwitch =
		new BindingSwitch<Adapter>() {
			@Override
			public Adapter caseLookupBinding(LookupBinding object) {
				return createLookupBindingAdapter();
			}
			@Override
			public Adapter caseNavigationBinding(NavigationBinding object) {
				return createNavigationBindingAdapter();
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
			public Adapter caseUimBinding(UimBinding object) {
				return createUimBindingAdapter();
			}
			@Override
			public Adapter casePropertyRef(PropertyRef object) {
				return createPropertyRefAdapter();
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
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.binding.LookupBinding <em>Lookup Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.binding.LookupBinding
	 * @generated
	 */
	public Adapter createLookupBindingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.binding.NavigationBinding <em>Navigation Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.binding.NavigationBinding
	 * @generated
	 */
	public Adapter createNavigationBindingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.binding.TableBinding <em>Table Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.binding.TableBinding
	 * @generated
	 */
	public Adapter createTableBindingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.binding.FieldBinding <em>Field Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.binding.FieldBinding
	 * @generated
	 */
	public Adapter createFieldBindingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.binding.UimBinding <em>Uim Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.binding.UimBinding
	 * @generated
	 */
	public Adapter createUimBindingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nakeduml.uim.binding.PropertyRef <em>Property Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nakeduml.uim.binding.PropertyRef
	 * @generated
	 */
	public Adapter createPropertyRefAdapter() {
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

} //BindingAdapterFactory
