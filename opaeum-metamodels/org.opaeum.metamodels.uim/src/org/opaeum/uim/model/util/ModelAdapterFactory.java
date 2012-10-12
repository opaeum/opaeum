/**
 */
package org.opaeum.uim.model.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.opaeum.uim.LabeledElement;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.UserInterfaceRoot;
import org.opaeum.uim.editor.AbstractEditor;
import org.opaeum.uim.model.AbstractUserInteractionModel;
import org.opaeum.uim.model.BehaviorUserInteractionModel;
import org.opaeum.uim.model.ClassUserInteractionModel;
import org.opaeum.uim.model.EmbeddedTaskEditor;
import org.opaeum.uim.model.ModelPackage;
import org.opaeum.uim.model.OperationInvocationWizard;
import org.opaeum.uim.model.QueryInvoker;
import org.opaeum.uim.model.ResponsibilityUserInteractionModel;
import org.opaeum.uim.wizard.AbstractWizard;
import org.opaeum.uim.wizard.InvocationWizard;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.opaeum.uim.model.ModelPackage
 * @generated
 */
public class ModelAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ModelPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = ModelPackage.eINSTANCE;
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
	protected ModelSwitch<Adapter> modelSwitch =
		new ModelSwitch<Adapter>() {
			@Override
			public Adapter caseClassUserInteractionModel(ClassUserInteractionModel object) {
				return createClassUserInteractionModelAdapter();
			}
			@Override
			public Adapter caseResponsibilityUserInteractionModel(ResponsibilityUserInteractionModel object) {
				return createResponsibilityUserInteractionModelAdapter();
			}
			@Override
			public Adapter caseAbstractUserInteractionModel(AbstractUserInteractionModel object) {
				return createAbstractUserInteractionModelAdapter();
			}
			@Override
			public Adapter caseBehaviorUserInteractionModel(BehaviorUserInteractionModel object) {
				return createBehaviorUserInteractionModelAdapter();
			}
			@Override
			public Adapter caseQueryInvoker(QueryInvoker object) {
				return createQueryInvokerAdapter();
			}
			@Override
			public Adapter caseOperationInvocationWizard(OperationInvocationWizard object) {
				return createOperationInvocationWizardAdapter();
			}
			@Override
			public Adapter caseEmbeddedTaskEditor(EmbeddedTaskEditor object) {
				return createEmbeddedTaskEditorAdapter();
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
			public Adapter caseLabeledElement(LabeledElement object) {
				return createLabeledElementAdapter();
			}
			@Override
			public Adapter caseUserInterfaceRoot(UserInterfaceRoot object) {
				return createUserInterfaceRootAdapter();
			}
			@Override
			public Adapter caseAbstractEditor(AbstractEditor object) {
				return createAbstractEditorAdapter();
			}
			@Override
			public Adapter caseAbstractWizard(AbstractWizard object) {
				return createAbstractWizardAdapter();
			}
			@Override
			public Adapter caseInvocationWizard(InvocationWizard object) {
				return createInvocationWizardAdapter();
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
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.model.ClassUserInteractionModel <em>Class User Interaction Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.model.ClassUserInteractionModel
	 * @generated
	 */
	public Adapter createClassUserInteractionModelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.model.ResponsibilityUserInteractionModel <em>Responsibility User Interaction Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.model.ResponsibilityUserInteractionModel
	 * @generated
	 */
	public Adapter createResponsibilityUserInteractionModelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.model.AbstractUserInteractionModel <em>Abstract User Interaction Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.model.AbstractUserInteractionModel
	 * @generated
	 */
	public Adapter createAbstractUserInteractionModelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.model.BehaviorUserInteractionModel <em>Behavior User Interaction Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.model.BehaviorUserInteractionModel
	 * @generated
	 */
	public Adapter createBehaviorUserInteractionModelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.model.QueryInvoker <em>Query Invoker</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.model.QueryInvoker
	 * @generated
	 */
	public Adapter createQueryInvokerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.model.OperationInvocationWizard <em>Operation Invocation Wizard</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.model.OperationInvocationWizard
	 * @generated
	 */
	public Adapter createOperationInvocationWizardAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.model.EmbeddedTaskEditor <em>Embedded Task Editor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.model.EmbeddedTaskEditor
	 * @generated
	 */
	public Adapter createEmbeddedTaskEditorAdapter() {
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
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.LabeledElement <em>Labeled Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.LabeledElement
	 * @generated
	 */
	public Adapter createLabeledElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.UserInterfaceRoot <em>User Interface Root</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.UserInterfaceRoot
	 * @generated
	 */
	public Adapter createUserInterfaceRootAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.editor.AbstractEditor <em>Abstract Editor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.editor.AbstractEditor
	 * @generated
	 */
	public Adapter createAbstractEditorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.wizard.AbstractWizard <em>Abstract Wizard</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.wizard.AbstractWizard
	 * @generated
	 */
	public Adapter createAbstractWizardAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.opaeum.uim.wizard.InvocationWizard <em>Invocation Wizard</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.opaeum.uim.wizard.InvocationWizard
	 * @generated
	 */
	public Adapter createInvocationWizardAdapter() {
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

} //ModelAdapterFactory
