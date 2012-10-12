/**
 */
package org.opaeum.uim.model.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
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
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.opaeum.uim.model.ModelPackage
 * @generated
 */
public class ModelSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ModelPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelSwitch() {
		if (modelPackage == null) {
			modelPackage = ModelPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case ModelPackage.CLASS_USER_INTERACTION_MODEL: {
				ClassUserInteractionModel classUserInteractionModel = (ClassUserInteractionModel)theEObject;
				T result = caseClassUserInteractionModel(classUserInteractionModel);
				if (result == null) result = caseAbstractUserInteractionModel(classUserInteractionModel);
				if (result == null) result = caseUmlReference(classUserInteractionModel);
				if (result == null) result = caseUserInteractionElement(classUserInteractionModel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.RESPONSIBILITY_USER_INTERACTION_MODEL: {
				ResponsibilityUserInteractionModel responsibilityUserInteractionModel = (ResponsibilityUserInteractionModel)theEObject;
				T result = caseResponsibilityUserInteractionModel(responsibilityUserInteractionModel);
				if (result == null) result = caseAbstractUserInteractionModel(responsibilityUserInteractionModel);
				if (result == null) result = caseUmlReference(responsibilityUserInteractionModel);
				if (result == null) result = caseUserInteractionElement(responsibilityUserInteractionModel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.ABSTRACT_USER_INTERACTION_MODEL: {
				AbstractUserInteractionModel abstractUserInteractionModel = (AbstractUserInteractionModel)theEObject;
				T result = caseAbstractUserInteractionModel(abstractUserInteractionModel);
				if (result == null) result = caseUmlReference(abstractUserInteractionModel);
				if (result == null) result = caseUserInteractionElement(abstractUserInteractionModel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.BEHAVIOR_USER_INTERACTION_MODEL: {
				BehaviorUserInteractionModel behaviorUserInteractionModel = (BehaviorUserInteractionModel)theEObject;
				T result = caseBehaviorUserInteractionModel(behaviorUserInteractionModel);
				if (result == null) result = caseAbstractUserInteractionModel(behaviorUserInteractionModel);
				if (result == null) result = caseUmlReference(behaviorUserInteractionModel);
				if (result == null) result = caseUserInteractionElement(behaviorUserInteractionModel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.QUERY_INVOKER: {
				QueryInvoker queryInvoker = (QueryInvoker)theEObject;
				T result = caseQueryInvoker(queryInvoker);
				if (result == null) result = caseAbstractEditor(queryInvoker);
				if (result == null) result = caseAbstractUserInteractionModel(queryInvoker);
				if (result == null) result = caseUserInterfaceRoot(queryInvoker);
				if (result == null) result = caseLabeledElement(queryInvoker);
				if (result == null) result = caseUserInteractionElement(queryInvoker);
				if (result == null) result = caseUmlReference(queryInvoker);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.OPERATION_INVOCATION_WIZARD: {
				OperationInvocationWizard operationInvocationWizard = (OperationInvocationWizard)theEObject;
				T result = caseOperationInvocationWizard(operationInvocationWizard);
				if (result == null) result = caseInvocationWizard(operationInvocationWizard);
				if (result == null) result = caseAbstractUserInteractionModel(operationInvocationWizard);
				if (result == null) result = caseAbstractWizard(operationInvocationWizard);
				if (result == null) result = caseUserInterfaceRoot(operationInvocationWizard);
				if (result == null) result = caseLabeledElement(operationInvocationWizard);
				if (result == null) result = caseUserInteractionElement(operationInvocationWizard);
				if (result == null) result = caseUmlReference(operationInvocationWizard);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.EMBEDDED_TASK_EDITOR: {
				EmbeddedTaskEditor embeddedTaskEditor = (EmbeddedTaskEditor)theEObject;
				T result = caseEmbeddedTaskEditor(embeddedTaskEditor);
				if (result == null) result = caseAbstractEditor(embeddedTaskEditor);
				if (result == null) result = caseAbstractUserInteractionModel(embeddedTaskEditor);
				if (result == null) result = caseUserInterfaceRoot(embeddedTaskEditor);
				if (result == null) result = caseLabeledElement(embeddedTaskEditor);
				if (result == null) result = caseUserInteractionElement(embeddedTaskEditor);
				if (result == null) result = caseUmlReference(embeddedTaskEditor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Class User Interaction Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Class User Interaction Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseClassUserInteractionModel(ClassUserInteractionModel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Responsibility User Interaction Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Responsibility User Interaction Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseResponsibilityUserInteractionModel(ResponsibilityUserInteractionModel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract User Interaction Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract User Interaction Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractUserInteractionModel(AbstractUserInteractionModel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Behavior User Interaction Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Behavior User Interaction Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBehaviorUserInteractionModel(BehaviorUserInteractionModel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Query Invoker</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Query Invoker</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseQueryInvoker(QueryInvoker object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Operation Invocation Wizard</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Operation Invocation Wizard</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOperationInvocationWizard(OperationInvocationWizard object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Embedded Task Editor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Embedded Task Editor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEmbeddedTaskEditor(EmbeddedTaskEditor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Uml Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Uml Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUmlReference(UmlReference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>User Interaction Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>User Interaction Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUserInteractionElement(UserInteractionElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Labeled Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Labeled Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLabeledElement(LabeledElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>User Interface Root</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>User Interface Root</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUserInterfaceRoot(UserInterfaceRoot object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Editor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Editor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractEditor(AbstractEditor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Wizard</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Wizard</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractWizard(AbstractWizard object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Invocation Wizard</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Invocation Wizard</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInvocationWizard(InvocationWizard object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public T defaultCase(EObject object) {
		return null;
	}

} //ModelSwitch
