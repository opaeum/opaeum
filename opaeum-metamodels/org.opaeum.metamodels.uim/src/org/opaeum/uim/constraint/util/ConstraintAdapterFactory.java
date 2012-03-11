/**
 */
package org.opaeum.uim.constraint.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.constraint.ConstrainedObject;
import org.opaeum.uim.constraint.ConstraintPackage;
import org.opaeum.uim.constraint.EditableConstrainedObject;
import org.opaeum.uim.constraint.RequiredRole;
import org.opaeum.uim.constraint.RequiredState;
import org.opaeum.uim.constraint.RootUserInteractionConstraint;
import org.opaeum.uim.constraint.UserInteractionConstraint;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.opaeum.uim.constraint.ConstraintPackage
 * @generated
 */
public class ConstraintAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ConstraintPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConstraintAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = ConstraintPackage.eINSTANCE;
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
	protected ConstraintSwitch<Adapter> modelSwitch =
		new ConstraintSwitch<Adapter>() {
			@Override
			public Adapter caseEditableConstrainedObject(EditableConstrainedObject object) {
				return createEditableConstrainedObjectAdapter();
			}
			@Override
			public Adapter caseConstrainedObject(ConstrainedObject object) {
				return createConstrainedObjectAdapter();
			}
			@Override
			public Adapter caseRequiredRole(RequiredRole object) {
				return createRequiredRoleAdapter();
			}
			@Override
			public Adapter caseRootUserInteractionConstraint(RootUserInteractionConstraint object) {
				return createRootUserInteractionConstraintAdapter();
			}
			@Override
			public Adapter caseUserInteractionConstraint(UserInteractionConstraint object) {
				return createUserInteractionConstraintAdapter();
			}
			@Override
			public Adapter caseRequiredState(RequiredState object) {
				return createRequiredStateAdapter();
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
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.constraint.RequiredRole <em>Required Role</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.constraint.RequiredRole
	 * @generated
	 */
	public Adapter createRequiredRoleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.constraint.RootUserInteractionConstraint <em>Root User Interaction Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.constraint.RootUserInteractionConstraint
	 * @generated
	 */
	public Adapter createRootUserInteractionConstraintAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.constraint.UserInteractionConstraint <em>User Interaction Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.constraint.UserInteractionConstraint
	 * @generated
	 */
	public Adapter createUserInteractionConstraintAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.constraint.RequiredState <em>Required State</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.constraint.RequiredState
	 * @generated
	 */
	public Adapter createRequiredStateAdapter() {
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

} //ConstraintAdapterFactory
