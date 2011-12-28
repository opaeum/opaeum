/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opaeum.uim.action.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.opaeum.uim.UimComponent;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.action.*;
import org.opaeum.uim.action.ActionPackage;
import org.opaeum.uim.action.BuiltInAction;
import org.opaeum.uim.action.NavigationToEntity;
import org.opaeum.uim.action.NavigationToOperation;
import org.opaeum.uim.action.OperationAction;
import org.opaeum.uim.action.TransitionAction;
import org.opaeum.uim.action.UimAction;
import org.opaeum.uim.action.UimNavigation;
import org.opaeum.uim.layout.OutlayableComponent;
import org.opaeum.uim.security.SecureObject;

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
			case ActionPackage.BUILT_IN_ACTION: {
				BuiltInAction builtInAction = (BuiltInAction)theEObject;
				T result = caseBuiltInAction(builtInAction);
				if (result == null) result = caseUimAction(builtInAction);
				if (result == null) result = caseOutlayableComponent(builtInAction);
				if (result == null) result = caseUimComponent(builtInAction);
				if (result == null) result = caseUserInteractionElement(builtInAction);
				if (result == null) result = caseSecureObject(builtInAction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ActionPackage.UIM_ACTION: {
				UimAction uimAction = (UimAction)theEObject;
				T result = caseUimAction(uimAction);
				if (result == null) result = caseOutlayableComponent(uimAction);
				if (result == null) result = caseUimComponent(uimAction);
				if (result == null) result = caseUserInteractionElement(uimAction);
				if (result == null) result = caseSecureObject(uimAction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ActionPackage.TRANSITION_ACTION: {
				TransitionAction transitionAction = (TransitionAction)theEObject;
				T result = caseTransitionAction(transitionAction);
				if (result == null) result = caseUimAction(transitionAction);
				if (result == null) result = caseUmlReference(transitionAction);
				if (result == null) result = caseOutlayableComponent(transitionAction);
				if (result == null) result = caseUimComponent(transitionAction);
				if (result == null) result = caseUserInteractionElement(transitionAction);
				if (result == null) result = caseSecureObject(transitionAction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ActionPackage.NAVIGATION_TO_OPERATION: {
				NavigationToOperation navigationToOperation = (NavigationToOperation)theEObject;
				T result = caseNavigationToOperation(navigationToOperation);
				if (result == null) result = caseUimNavigation(navigationToOperation);
				if (result == null) result = caseUmlReference(navigationToOperation);
				if (result == null) result = caseOutlayableComponent(navigationToOperation);
				if (result == null) result = caseUimComponent(navigationToOperation);
				if (result == null) result = caseUserInteractionElement(navigationToOperation);
				if (result == null) result = caseSecureObject(navigationToOperation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ActionPackage.OPERATION_ACTION: {
				OperationAction operationAction = (OperationAction)theEObject;
				T result = caseOperationAction(operationAction);
				if (result == null) result = caseUimAction(operationAction);
				if (result == null) result = caseUmlReference(operationAction);
				if (result == null) result = caseOutlayableComponent(operationAction);
				if (result == null) result = caseUimComponent(operationAction);
				if (result == null) result = caseUserInteractionElement(operationAction);
				if (result == null) result = caseSecureObject(operationAction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ActionPackage.UIM_NAVIGATION: {
				UimNavigation uimNavigation = (UimNavigation)theEObject;
				T result = caseUimNavigation(uimNavigation);
				if (result == null) result = caseOutlayableComponent(uimNavigation);
				if (result == null) result = caseUimComponent(uimNavigation);
				if (result == null) result = caseUserInteractionElement(uimNavigation);
				if (result == null) result = caseSecureObject(uimNavigation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ActionPackage.NAVIGATION_TO_ENTITY: {
				NavigationToEntity navigationToEntity = (NavigationToEntity)theEObject;
				T result = caseNavigationToEntity(navigationToEntity);
				if (result == null) result = caseUimNavigation(navigationToEntity);
				if (result == null) result = caseUmlReference(navigationToEntity);
				if (result == null) result = caseOutlayableComponent(navigationToEntity);
				if (result == null) result = caseUimComponent(navigationToEntity);
				if (result == null) result = caseUserInteractionElement(navigationToEntity);
				if (result == null) result = caseSecureObject(navigationToEntity);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Built In Action</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Built In Action</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBuiltInAction(BuiltInAction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Uim Action</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Uim Action</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUimAction(UimAction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Transition Action</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Transition Action</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTransitionAction(TransitionAction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Navigation To Operation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Navigation To Operation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNavigationToOperation(NavigationToOperation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Operation Action</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Operation Action</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOperationAction(OperationAction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Uim Navigation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Uim Navigation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUimNavigation(UimNavigation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Navigation To Entity</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Navigation To Entity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNavigationToEntity(NavigationToEntity object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Secure Object</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Secure Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSecureObject(SecureObject object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Component</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Component</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUimComponent(UimComponent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Outlayable Component</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Outlayable Component</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOutlayableComponent(OutlayableComponent object) {
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
