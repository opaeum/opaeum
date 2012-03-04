/**
 */
package org.opaeum.uim.panel.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.opaeum.uim.UimComponent;
import org.opaeum.uim.UimContainer;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.constraint.ConstrainedObject;
import org.opaeum.uim.constraint.EditableConstrainedObject;
import org.opaeum.uim.panel.*;
import org.opaeum.uim.panel.AbstractPanel;
import org.opaeum.uim.panel.CollapsiblePanel;
import org.opaeum.uim.panel.GridPanel;
import org.opaeum.uim.panel.HorizontalPanel;
import org.opaeum.uim.panel.PanelPackage;
import org.opaeum.uim.panel.VerticalPanel;

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
 * @see org.opaeum.uim.panel.PanelPackage
 * @generated
 */
public class PanelSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static PanelPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PanelSwitch() {
		if (modelPackage == null) {
			modelPackage = PanelPackage.eINSTANCE;
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
			case PanelPackage.GRID_PANEL: {
				GridPanel gridPanel = (GridPanel)theEObject;
				T result = caseGridPanel(gridPanel);
				if (result == null) result = caseCollapsiblePanel(gridPanel);
				if (result == null) result = caseAbstractPanel(gridPanel);
				if (result == null) result = caseUimContainer(gridPanel);
				if (result == null) result = caseUimComponent(gridPanel);
				if (result == null) result = caseEditableConstrainedObject(gridPanel);
				if (result == null) result = caseUserInteractionElement(gridPanel);
				if (result == null) result = caseConstrainedObject(gridPanel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PanelPackage.VERTICAL_PANEL: {
				VerticalPanel verticalPanel = (VerticalPanel)theEObject;
				T result = caseVerticalPanel(verticalPanel);
				if (result == null) result = caseCollapsiblePanel(verticalPanel);
				if (result == null) result = caseAbstractPanel(verticalPanel);
				if (result == null) result = caseUimContainer(verticalPanel);
				if (result == null) result = caseUimComponent(verticalPanel);
				if (result == null) result = caseEditableConstrainedObject(verticalPanel);
				if (result == null) result = caseUserInteractionElement(verticalPanel);
				if (result == null) result = caseConstrainedObject(verticalPanel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PanelPackage.HORIZONTAL_PANEL: {
				HorizontalPanel horizontalPanel = (HorizontalPanel)theEObject;
				T result = caseHorizontalPanel(horizontalPanel);
				if (result == null) result = caseCollapsiblePanel(horizontalPanel);
				if (result == null) result = caseAbstractPanel(horizontalPanel);
				if (result == null) result = caseUimContainer(horizontalPanel);
				if (result == null) result = caseUimComponent(horizontalPanel);
				if (result == null) result = caseEditableConstrainedObject(horizontalPanel);
				if (result == null) result = caseUserInteractionElement(horizontalPanel);
				if (result == null) result = caseConstrainedObject(horizontalPanel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PanelPackage.ABSTRACT_PANEL: {
				AbstractPanel abstractPanel = (AbstractPanel)theEObject;
				T result = caseAbstractPanel(abstractPanel);
				if (result == null) result = caseUimContainer(abstractPanel);
				if (result == null) result = caseUimComponent(abstractPanel);
				if (result == null) result = caseEditableConstrainedObject(abstractPanel);
				if (result == null) result = caseUserInteractionElement(abstractPanel);
				if (result == null) result = caseConstrainedObject(abstractPanel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PanelPackage.COLLAPSIBLE_PANEL: {
				CollapsiblePanel collapsiblePanel = (CollapsiblePanel)theEObject;
				T result = caseCollapsiblePanel(collapsiblePanel);
				if (result == null) result = caseAbstractPanel(collapsiblePanel);
				if (result == null) result = caseUimContainer(collapsiblePanel);
				if (result == null) result = caseUimComponent(collapsiblePanel);
				if (result == null) result = caseEditableConstrainedObject(collapsiblePanel);
				if (result == null) result = caseUserInteractionElement(collapsiblePanel);
				if (result == null) result = caseConstrainedObject(collapsiblePanel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Grid Panel</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Grid Panel</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGridPanel(GridPanel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Vertical Panel</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Vertical Panel</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseVerticalPanel(VerticalPanel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Horizontal Panel</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Horizontal Panel</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseHorizontalPanel(HorizontalPanel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Panel</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Panel</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractPanel(AbstractPanel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Collapsible Panel</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Collapsible Panel</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCollapsiblePanel(CollapsiblePanel object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Container</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUimContainer(UimContainer object) {
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

} //PanelSwitch
