/**
 */
package org.opaeum.uim.wizard.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.opaeum.uim.LabeledElement;
import org.opaeum.uim.Page;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.UserInterfaceRoot;
import org.opaeum.uim.constraint.ConstrainedObject;
import org.opaeum.uim.constraint.EditableConstrainedObject;
import org.opaeum.uim.wizard.*;
import org.opaeum.uim.wizard.AbstractWizard;
import org.opaeum.uim.wizard.BehaviorInvocationWizard;
import org.opaeum.uim.wizard.InvocationWizard;
import org.opaeum.uim.wizard.NewObjectWizard;
import org.opaeum.uim.wizard.OperationResultPage;
import org.opaeum.uim.wizard.ResponsibilityInvocationWizard;
import org.opaeum.uim.wizard.WizardPackage;
import org.opaeum.uim.wizard.WizardPage;

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
 * @see org.opaeum.uim.wizard.WizardPackage
 * @generated
 */
public class WizardSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static WizardPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WizardSwitch() {
		if (modelPackage == null) {
			modelPackage = WizardPackage.eINSTANCE;
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
			case WizardPackage.ABSTRACT_WIZARD: {
				AbstractWizard abstractWizard = (AbstractWizard)theEObject;
				T result = caseAbstractWizard(abstractWizard);
				if (result == null) result = caseUserInterfaceRoot(abstractWizard);
				if (result == null) result = caseLabeledElement(abstractWizard);
				if (result == null) result = caseUserInteractionElement(abstractWizard);
				if (result == null) result = caseUmlReference(abstractWizard);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WizardPackage.NEW_OBJECT_WIZARD: {
				NewObjectWizard newObjectWizard = (NewObjectWizard)theEObject;
				T result = caseNewObjectWizard(newObjectWizard);
				if (result == null) result = caseAbstractWizard(newObjectWizard);
				if (result == null) result = caseUserInterfaceRoot(newObjectWizard);
				if (result == null) result = caseLabeledElement(newObjectWizard);
				if (result == null) result = caseUserInteractionElement(newObjectWizard);
				if (result == null) result = caseUmlReference(newObjectWizard);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WizardPackage.INVOCATION_WIZARD: {
				InvocationWizard invocationWizard = (InvocationWizard)theEObject;
				T result = caseInvocationWizard(invocationWizard);
				if (result == null) result = caseAbstractWizard(invocationWizard);
				if (result == null) result = caseUserInterfaceRoot(invocationWizard);
				if (result == null) result = caseLabeledElement(invocationWizard);
				if (result == null) result = caseUserInteractionElement(invocationWizard);
				if (result == null) result = caseUmlReference(invocationWizard);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WizardPackage.WIZARD_PAGE: {
				WizardPage wizardPage = (WizardPage)theEObject;
				T result = caseWizardPage(wizardPage);
				if (result == null) result = casePage(wizardPage);
				if (result == null) result = caseEditableConstrainedObject(wizardPage);
				if (result == null) result = caseLabeledElement(wizardPage);
				if (result == null) result = caseConstrainedObject(wizardPage);
				if (result == null) result = caseUmlReference(wizardPage);
				if (result == null) result = caseUserInteractionElement(wizardPage);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WizardPackage.RESPONSIBILITY_INVOCATION_WIZARD: {
				ResponsibilityInvocationWizard responsibilityInvocationWizard = (ResponsibilityInvocationWizard)theEObject;
				T result = caseResponsibilityInvocationWizard(responsibilityInvocationWizard);
				if (result == null) result = caseInvocationWizard(responsibilityInvocationWizard);
				if (result == null) result = caseAbstractWizard(responsibilityInvocationWizard);
				if (result == null) result = caseUserInterfaceRoot(responsibilityInvocationWizard);
				if (result == null) result = caseLabeledElement(responsibilityInvocationWizard);
				if (result == null) result = caseUserInteractionElement(responsibilityInvocationWizard);
				if (result == null) result = caseUmlReference(responsibilityInvocationWizard);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WizardPackage.BEHAVIOR_INVOCATION_WIZARD: {
				BehaviorInvocationWizard behaviorInvocationWizard = (BehaviorInvocationWizard)theEObject;
				T result = caseBehaviorInvocationWizard(behaviorInvocationWizard);
				if (result == null) result = caseInvocationWizard(behaviorInvocationWizard);
				if (result == null) result = caseAbstractWizard(behaviorInvocationWizard);
				if (result == null) result = caseUserInterfaceRoot(behaviorInvocationWizard);
				if (result == null) result = caseLabeledElement(behaviorInvocationWizard);
				if (result == null) result = caseUserInteractionElement(behaviorInvocationWizard);
				if (result == null) result = caseUmlReference(behaviorInvocationWizard);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case WizardPackage.OPERATION_RESULT_PAGE: {
				OperationResultPage operationResultPage = (OperationResultPage)theEObject;
				T result = caseOperationResultPage(operationResultPage);
				if (result == null) result = casePage(operationResultPage);
				if (result == null) result = caseEditableConstrainedObject(operationResultPage);
				if (result == null) result = caseLabeledElement(operationResultPage);
				if (result == null) result = caseConstrainedObject(operationResultPage);
				if (result == null) result = caseUmlReference(operationResultPage);
				if (result == null) result = caseUserInteractionElement(operationResultPage);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
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
	 * Returns the result of interpreting the object as an instance of '<em>New Object Wizard</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>New Object Wizard</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNewObjectWizard(NewObjectWizard object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Page</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Page</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWizardPage(WizardPage object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Responsibility Invocation Wizard</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Responsibility Invocation Wizard</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseResponsibilityInvocationWizard(ResponsibilityInvocationWizard object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Behavior Invocation Wizard</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Behavior Invocation Wizard</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBehaviorInvocationWizard(BehaviorInvocationWizard object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Operation Result Page</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Operation Result Page</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOperationResultPage(OperationResultPage object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Constrained Object</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Constrained Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConstrainedObject(ConstrainedObject object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Editable Constrained Object</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Editable Constrained Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEditableConstrainedObject(EditableConstrainedObject object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Page</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Page</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePage(Page object) {
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

} //WizardSwitch
