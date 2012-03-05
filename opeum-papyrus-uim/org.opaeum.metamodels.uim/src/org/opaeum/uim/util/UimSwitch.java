/**
 */
package org.opaeum.uim.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.opaeum.uim.*;
import org.opaeum.uim.DetailPanel;
import org.opaeum.uim.MasterComponent;
import org.opaeum.uim.ObjectSelectorTree;
import org.opaeum.uim.Page;
import org.opaeum.uim.UimComponent;
import org.opaeum.uim.UimContainer;
import org.opaeum.uim.UimDataTable;
import org.opaeum.uim.UimField;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.UserInterfaceEntryPoint;
import org.opaeum.uim.constraint.ConstrainedObject;
import org.opaeum.uim.constraint.EditableConstrainedObject;

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
 * @see org.opaeum.uim.UimPackage
 * @generated
 */
public class UimSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static UimPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimSwitch() {
		if (modelPackage == null) {
			modelPackage = UimPackage.eINSTANCE;
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
			case UimPackage.UIM_FIELD: {
				UimField uimField = (UimField)theEObject;
				T result = caseUimField(uimField);
				if (result == null) result = caseEditableConstrainedObject(uimField);
				if (result == null) result = caseUimComponent(uimField);
				if (result == null) result = caseConstrainedObject(uimField);
				if (result == null) result = caseUserInteractionElement(uimField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.UIM_COMPONENT: {
				UimComponent uimComponent = (UimComponent)theEObject;
				T result = caseUimComponent(uimComponent);
				if (result == null) result = caseUserInteractionElement(uimComponent);
				if (result == null) result = caseConstrainedObject(uimComponent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.USER_INTERACTION_ELEMENT: {
				UserInteractionElement userInteractionElement = (UserInteractionElement)theEObject;
				T result = caseUserInteractionElement(userInteractionElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.UIM_DATA_TABLE: {
				UimDataTable uimDataTable = (UimDataTable)theEObject;
				T result = caseUimDataTable(uimDataTable);
				if (result == null) result = caseMasterComponent(uimDataTable);
				if (result == null) result = caseUimContainer(uimDataTable);
				if (result == null) result = caseUimComponent(uimDataTable);
				if (result == null) result = caseEditableConstrainedObject(uimDataTable);
				if (result == null) result = caseUserInteractionElement(uimDataTable);
				if (result == null) result = caseConstrainedObject(uimDataTable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.UIM_CONTAINER: {
				UimContainer uimContainer = (UimContainer)theEObject;
				T result = caseUimContainer(uimContainer);
				if (result == null) result = caseUimComponent(uimContainer);
				if (result == null) result = caseEditableConstrainedObject(uimContainer);
				if (result == null) result = caseUserInteractionElement(uimContainer);
				if (result == null) result = caseConstrainedObject(uimContainer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.MASTER_COMPONENT: {
				MasterComponent masterComponent = (MasterComponent)theEObject;
				T result = caseMasterComponent(masterComponent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.UML_REFERENCE: {
				UmlReference umlReference = (UmlReference)theEObject;
				T result = caseUmlReference(umlReference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.OBJECT_SELECTOR_TREE: {
				ObjectSelectorTree objectSelectorTree = (ObjectSelectorTree)theEObject;
				T result = caseObjectSelectorTree(objectSelectorTree);
				if (result == null) result = caseMasterComponent(objectSelectorTree);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.DETAIL_COMPONENT: {
				DetailComponent detailComponent = (DetailComponent)theEObject;
				T result = caseDetailComponent(detailComponent);
				if (result == null) result = caseUimComponent(detailComponent);
				if (result == null) result = caseUserInteractionElement(detailComponent);
				if (result == null) result = caseConstrainedObject(detailComponent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.USER_INTERFACE_ENTRY_POINT: {
				UserInterfaceEntryPoint userInterfaceEntryPoint = (UserInterfaceEntryPoint)theEObject;
				T result = caseUserInterfaceEntryPoint(userInterfaceEntryPoint);
				if (result == null) result = caseUserInteractionElement(userInterfaceEntryPoint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.PAGE: {
				Page page = (Page)theEObject;
				T result = casePage(page);
				if (result == null) result = caseUserInterface(page);
				if (result == null) result = caseEditableConstrainedObject(page);
				if (result == null) result = caseUserInteractionElement(page);
				if (result == null) result = caseConstrainedObject(page);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.USER_INTERFACE: {
				UserInterface userInterface = (UserInterface)theEObject;
				T result = caseUserInterface(userInterface);
				if (result == null) result = caseUserInteractionElement(userInterface);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.PANEL_CLASS: {
				PanelClass panelClass = (PanelClass)theEObject;
				T result = casePanelClass(panelClass);
				if (result == null) result = caseUmlReference(panelClass);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Field</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Field</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUimField(UimField object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Data Table</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Data Table</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUimDataTable(UimDataTable object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Master Component</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Master Component</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMasterComponent(MasterComponent object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Object Selector Tree</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Object Selector Tree</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseObjectSelectorTree(ObjectSelectorTree object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Detail Component</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Detail Component</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDetailComponent(DetailComponent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>User Interface Entry Point</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>User Interface Entry Point</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUserInterfaceEntryPoint(UserInterfaceEntryPoint object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Panel Class</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Panel Class</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePanelClass(PanelClass object) {
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

} //UimSwitch
