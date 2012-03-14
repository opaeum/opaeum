/**
 */
package org.opaeum.uim.action.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.opaeum.uim.Page;
import org.opaeum.uim.PageContainer;
import org.opaeum.uim.UimComponent;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.UserInterface;
import org.opaeum.uim.action.*;
import org.opaeum.uim.action.ActionPackage;
import org.opaeum.uim.action.BuiltInActionButton;
import org.opaeum.uim.action.BuiltInLink;
import org.opaeum.uim.action.LinkToQuery;
import org.opaeum.uim.action.OperationButton;
import org.opaeum.uim.action.OperationPopup;
import org.opaeum.uim.action.OperationPopupPage;
import org.opaeum.uim.action.TransitionButton;
import org.opaeum.uim.action.UimAction;
import org.opaeum.uim.action.UimLink;
import org.opaeum.uim.constraint.ConstrainedObject;
import org.opaeum.uim.constraint.EditableConstrainedObject;
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
				if (result == null) result = caseUimAction(builtInActionButton);
				if (result == null) result = caseUimComponent(builtInActionButton);
				if (result == null) result = caseOutlayable(builtInActionButton);
				if (result == null) result = caseUserInteractionElement(builtInActionButton);
				if (result == null) result = caseConstrainedObject(builtInActionButton);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ActionPackage.UIM_ACTION: {
				UimAction uimAction = (UimAction)theEObject;
				T result = caseUimAction(uimAction);
				if (result == null) result = caseUimComponent(uimAction);
				if (result == null) result = caseOutlayable(uimAction);
				if (result == null) result = caseUserInteractionElement(uimAction);
				if (result == null) result = caseConstrainedObject(uimAction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ActionPackage.TRANSITION_BUTTON: {
				TransitionButton transitionButton = (TransitionButton)theEObject;
				T result = caseTransitionButton(transitionButton);
				if (result == null) result = caseUimAction(transitionButton);
				if (result == null) result = caseUmlReference(transitionButton);
				if (result == null) result = caseUimComponent(transitionButton);
				if (result == null) result = caseOutlayable(transitionButton);
				if (result == null) result = caseUserInteractionElement(transitionButton);
				if (result == null) result = caseConstrainedObject(transitionButton);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ActionPackage.LINK_TO_QUERY: {
				LinkToQuery linkToQuery = (LinkToQuery)theEObject;
				T result = caseLinkToQuery(linkToQuery);
				if (result == null) result = caseUimLink(linkToQuery);
				if (result == null) result = caseUmlReference(linkToQuery);
				if (result == null) result = caseUimComponent(linkToQuery);
				if (result == null) result = caseOutlayable(linkToQuery);
				if (result == null) result = caseUserInteractionElement(linkToQuery);
				if (result == null) result = caseConstrainedObject(linkToQuery);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ActionPackage.OPERATION_BUTTON: {
				OperationButton operationButton = (OperationButton)theEObject;
				T result = caseOperationButton(operationButton);
				if (result == null) result = caseUimAction(operationButton);
				if (result == null) result = caseUmlReference(operationButton);
				if (result == null) result = caseUimComponent(operationButton);
				if (result == null) result = caseOutlayable(operationButton);
				if (result == null) result = caseUserInteractionElement(operationButton);
				if (result == null) result = caseConstrainedObject(operationButton);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ActionPackage.UIM_LINK: {
				UimLink uimLink = (UimLink)theEObject;
				T result = caseUimLink(uimLink);
				if (result == null) result = caseUimComponent(uimLink);
				if (result == null) result = caseOutlayable(uimLink);
				if (result == null) result = caseUserInteractionElement(uimLink);
				if (result == null) result = caseConstrainedObject(uimLink);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ActionPackage.OPERATION_POPUP: {
				OperationPopup operationPopup = (OperationPopup)theEObject;
				T result = caseOperationPopup(operationPopup);
				if (result == null) result = casePageContainer(operationPopup);
				if (result == null) result = caseUmlReference(operationPopup);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ActionPackage.OPERATION_POPUP_PAGE: {
				OperationPopupPage operationPopupPage = (OperationPopupPage)theEObject;
				T result = caseOperationPopupPage(operationPopupPage);
				if (result == null) result = casePage(operationPopupPage);
				if (result == null) result = caseUserInterface(operationPopupPage);
				if (result == null) result = caseEditableConstrainedObject(operationPopupPage);
				if (result == null) result = caseUserInteractionElement(operationPopupPage);
				if (result == null) result = caseUmlReference(operationPopupPage);
				if (result == null) result = caseConstrainedObject(operationPopupPage);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ActionPackage.BUILT_IN_LINK: {
				BuiltInLink builtInLink = (BuiltInLink)theEObject;
				T result = caseBuiltInLink(builtInLink);
				if (result == null) result = caseUimLink(builtInLink);
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
	 * Returns the result of interpreting the object as an instance of '<em>Operation Button</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Operation Button</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOperationButton(OperationButton object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Uim Link</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Uim Link</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUimLink(UimLink object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Operation Popup</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Operation Popup</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOperationPopup(OperationPopup object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Operation Popup Page</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Operation Popup Page</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOperationPopupPage(OperationPopupPage object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Page Container</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Page Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePageContainer(PageContainer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>User Interface</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>User Interface</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUserInterface(UserInterface object) {
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

} //ActionSwitch
