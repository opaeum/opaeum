/**
 */
package org.opaeum.uim.action.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.opaeum.uim.LabeledElement;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.action.AbstractActionButton;
import org.opaeum.uim.action.AbstractLink;
import org.opaeum.uim.action.ActionPackage;
import org.opaeum.uim.action.BuiltInActionButton;
import org.opaeum.uim.action.BuiltInLink;
import org.opaeum.uim.action.InvocationButton;
import org.opaeum.uim.action.LinkToQuery;
import org.opaeum.uim.action.TransitionButton;
import org.opaeum.uim.component.UimComponent;
import org.opaeum.uim.constraint.ConstrainedObject;
import org.opaeum.uim.panel.Outlayable;

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
 * @see org.opaeum.uim.action.ActionPackage
 * @generated
 */
public class ActionSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ActionPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActionSwitch() {
		if (modelPackage == null) {
			modelPackage = ActionPackage.eINSTANCE;
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
			case ActionPackage.BUILT_IN_ACTION_BUTTON: {
				BuiltInActionButton builtInActionButton = (BuiltInActionButton)theEObject;
				T result = caseBuiltInActionButton(builtInActionButton);
				if (result == null) result = caseAbstractActionButton(builtInActionButton);
				if (result == null) result = caseUimComponent(builtInActionButton);
				if (result == null) result = caseOutlayable(builtInActionButton);
				if (result == null) result = caseUserInteractionElement(builtInActionButton);
				if (result == null) result = caseConstrainedObject(builtInActionButton);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ActionPackage.ABSTRACT_ACTION_BUTTON: {
				AbstractActionButton abstractActionButton = (AbstractActionButton)theEObject;
				T result = caseAbstractActionButton(abstractActionButton);
				if (result == null) result = caseUimComponent(abstractActionButton);
				if (result == null) result = caseOutlayable(abstractActionButton);
				if (result == null) result = caseUserInteractionElement(abstractActionButton);
				if (result == null) result = caseConstrainedObject(abstractActionButton);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ActionPackage.TRANSITION_BUTTON: {
				TransitionButton transitionButton = (TransitionButton)theEObject;
				T result = caseTransitionButton(transitionButton);
				if (result == null) result = caseAbstractActionButton(transitionButton);
				if (result == null) result = caseLabeledElement(transitionButton);
				if (result == null) result = caseUimComponent(transitionButton);
				if (result == null) result = caseOutlayable(transitionButton);
				if (result == null) result = caseUmlReference(transitionButton);
				if (result == null) result = caseUserInteractionElement(transitionButton);
				if (result == null) result = caseConstrainedObject(transitionButton);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ActionPackage.LINK_TO_QUERY: {
				LinkToQuery linkToQuery = (LinkToQuery)theEObject;
				T result = caseLinkToQuery(linkToQuery);
				if (result == null) result = caseAbstractLink(linkToQuery);
				if (result == null) result = caseLabeledElement(linkToQuery);
				if (result == null) result = caseUimComponent(linkToQuery);
				if (result == null) result = caseOutlayable(linkToQuery);
				if (result == null) result = caseUmlReference(linkToQuery);
				if (result == null) result = caseUserInteractionElement(linkToQuery);
				if (result == null) result = caseConstrainedObject(linkToQuery);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ActionPackage.INVOCATION_BUTTON: {
				InvocationButton invocationButton = (InvocationButton)theEObject;
				T result = caseInvocationButton(invocationButton);
				if (result == null) result = caseAbstractActionButton(invocationButton);
				if (result == null) result = caseLabeledElement(invocationButton);
				if (result == null) result = caseUimComponent(invocationButton);
				if (result == null) result = caseOutlayable(invocationButton);
				if (result == null) result = caseUmlReference(invocationButton);
				if (result == null) result = caseUserInteractionElement(invocationButton);
				if (result == null) result = caseConstrainedObject(invocationButton);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ActionPackage.ABSTRACT_LINK: {
				AbstractLink abstractLink = (AbstractLink)theEObject;
				T result = caseAbstractLink(abstractLink);
				if (result == null) result = caseUimComponent(abstractLink);
				if (result == null) result = caseOutlayable(abstractLink);
				if (result == null) result = caseUserInteractionElement(abstractLink);
				if (result == null) result = caseConstrainedObject(abstractLink);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ActionPackage.BUILT_IN_LINK: {
				BuiltInLink builtInLink = (BuiltInLink)theEObject;
				T result = caseBuiltInLink(builtInLink);
				if (result == null) result = caseAbstractLink(builtInLink);
				if (result == null) result = caseUimComponent(builtInLink);
				if (result == null) result = caseOutlayable(builtInLink);
				if (result == null) result = caseUserInteractionElement(builtInLink);
				if (result == null) result = caseConstrainedObject(builtInLink);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Built In Action Button</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Built In Action Button</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBuiltInActionButton(BuiltInActionButton object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Action Button</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Action Button</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractActionButton(AbstractActionButton object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Transition Button</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Transition Button</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTransitionButton(TransitionButton object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Link To Query</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Link To Query</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLinkToQuery(LinkToQuery object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Invocation Button</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Invocation Button</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInvocationButton(InvocationButton object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Link</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Link</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractLink(AbstractLink object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Built In Link</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Built In Link</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBuiltInLink(BuiltInLink object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Uim Component</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Uim Component</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUimComponent(UimComponent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Outlayable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Outlayable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOutlayable(Outlayable object) {
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

} //ActionSwitch
