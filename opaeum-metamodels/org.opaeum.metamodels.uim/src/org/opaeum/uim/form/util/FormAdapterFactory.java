/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opaeum.uim.form.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.opaeum.uim.UimComponent;
import org.opaeum.uim.UimContainer;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.form.ActionTaskForm;
import org.opaeum.uim.form.ClassForm;
import org.opaeum.uim.form.DetailPanel;
import org.opaeum.uim.form.FormPackage;
import org.opaeum.uim.form.FormPanel;
import org.opaeum.uim.form.OperationInvocationForm;
import org.opaeum.uim.form.OperationTaskForm;
import org.opaeum.uim.form.StateForm;
import org.opaeum.uim.form.UimForm;
import org.opaeum.uim.layout.LayoutContainer;
import org.opaeum.uim.security.EditableSecureObject;
import org.opaeum.uim.security.SecureObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.opaeum.uim.form.FormPackage
 * @generated
 */
public class FormAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static FormPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = FormPackage.eINSTANCE;
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
	protected FormSwitch<Adapter> modelSwitch =
		new FormSwitch<Adapter>() {
			@Override
			public Adapter caseFormPanel(FormPanel object) {
				return createFormPanelAdapter();
			}
			@Override
			public Adapter caseActionTaskForm(ActionTaskForm object) {
				return createActionTaskFormAdapter();
			}
			@Override
			public Adapter caseStateForm(StateForm object) {
				return createStateFormAdapter();
			}
			@Override
			public Adapter caseClassForm(ClassForm object) {
				return createClassFormAdapter();
			}
			@Override
			public Adapter caseUimForm(UimForm object) {
				return createUimFormAdapter();
			}
			@Override
			public Adapter caseOperationTaskForm(OperationTaskForm object) {
				return createOperationTaskFormAdapter();
			}
			@Override
			public Adapter caseOperationInvocationForm(OperationInvocationForm object) {
				return createOperationInvocationFormAdapter();
			}
			@Override
			public Adapter caseDetailPanel(DetailPanel object) {
				return createDetailPanelAdapter();
			}
			@Override
			public Adapter caseUmlReference(UmlReference object) {
				return createUmlReferenceAdapter();
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
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.form.FormPanel <em>Panel</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.form.FormPanel
	 * @generated
	 */
	public Adapter createFormPanelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.form.ActionTaskForm <em>Action Task Form</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.form.ActionTaskForm
	 * @generated
	 */
	public Adapter createActionTaskFormAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.form.StateForm <em>State Form</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.form.StateForm
	 * @generated
	 */
	public Adapter createStateFormAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.form.ClassForm <em>Class Form</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.form.ClassForm
	 * @generated
	 */
	public Adapter createClassFormAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.form.UimForm <em>Uim Form</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.form.UimForm
	 * @generated
	 */
	public Adapter createUimFormAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.form.OperationTaskForm <em>Operation Task Form</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.form.OperationTaskForm
	 * @generated
	 */
	public Adapter createOperationTaskFormAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.form.OperationInvocationForm <em>Operation Invocation Form</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.form.OperationInvocationForm
	 * @generated
	 */
	public Adapter createOperationInvocationFormAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.form.DetailPanel <em>Detail Panel</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.form.DetailPanel
	 * @generated
	 */
	public Adapter createDetailPanelAdapter() {
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

} //FormAdapterFactory
