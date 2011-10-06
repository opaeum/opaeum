/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opaeum.uim.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.opaeum.uim.MasterComponent;
import org.opaeum.uim.ObjectSelectorTree;
import org.opaeum.uim.UimComponent;
import org.opaeum.uim.UimContainer;
import org.opaeum.uim.UimDataTable;
import org.opaeum.uim.UimField;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.UimPanel;
import org.opaeum.uim.UimTab;
import org.opaeum.uim.UimTabPanel;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.layout.LayoutContainer;
import org.opaeum.uim.layout.OutlayableComponent;
import org.opaeum.uim.security.EditableSecureObject;
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
				if (result == null) result = caseEditableSecureObject(uimField);
				if (result == null) result = caseOutlayableComponent(uimField);
				if (result == null) result = caseUimComponent(uimField);
				if (result == null) result = caseSecureObject(uimField);
				if (result == null) result = caseUserInteractionElement(uimField);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.UIM_COMPONENT: {
				UimComponent uimComponent = (UimComponent)theEObject;
				T result = caseUimComponent(uimComponent);
				if (result == null) result = caseUserInteractionElement(uimComponent);
				if (result == null) result = caseSecureObject(uimComponent);
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
				if (result == null) result = caseOutlayableComponent(uimDataTable);
				if (result == null) result = caseLayoutContainer(uimDataTable);
				if (result == null) result = caseUimContainer(uimDataTable);
				if (result == null) result = caseUimComponent(uimDataTable);
				if (result == null) result = caseUserInteractionElement(uimDataTable);
				if (result == null) result = caseEditableSecureObject(uimDataTable);
				if (result == null) result = caseSecureObject(uimDataTable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.UIM_TAB_PANEL: {
				UimTabPanel uimTabPanel = (UimTabPanel)theEObject;
				T result = caseUimTabPanel(uimTabPanel);
				if (result == null) result = caseUimContainer(uimTabPanel);
				if (result == null) result = caseOutlayableComponent(uimTabPanel);
				if (result == null) result = caseUimComponent(uimTabPanel);
				if (result == null) result = caseEditableSecureObject(uimTabPanel);
				if (result == null) result = caseUserInteractionElement(uimTabPanel);
				if (result == null) result = caseSecureObject(uimTabPanel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.UIM_TAB: {
				UimTab uimTab = (UimTab)theEObject;
				T result = caseUimTab(uimTab);
				if (result == null) result = caseLayoutContainer(uimTab);
				if (result == null) result = caseUimContainer(uimTab);
				if (result == null) result = caseUimComponent(uimTab);
				if (result == null) result = caseEditableSecureObject(uimTab);
				if (result == null) result = caseUserInteractionElement(uimTab);
				if (result == null) result = caseSecureObject(uimTab);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.UIM_CONTAINER: {
				UimContainer uimContainer = (UimContainer)theEObject;
				T result = caseUimContainer(uimContainer);
				if (result == null) result = caseUimComponent(uimContainer);
				if (result == null) result = caseEditableSecureObject(uimContainer);
				if (result == null) result = caseUserInteractionElement(uimContainer);
				if (result == null) result = caseSecureObject(uimContainer);
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
			case UimPackage.UIM_PANEL: {
				UimPanel uimPanel = (UimPanel)theEObject;
				T result = caseUimPanel(uimPanel);
				if (result == null) result = caseLayoutContainer(uimPanel);
				if (result == null) result = caseOutlayableComponent(uimPanel);
				if (result == null) result = caseUimContainer(uimPanel);
				if (result == null) result = caseUimComponent(uimPanel);
				if (result == null) result = caseEditableSecureObject(uimPanel);
				if (result == null) result = caseUserInteractionElement(uimPanel);
				if (result == null) result = caseSecureObject(uimPanel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UimPackage.OBJECT_SELECTOR_TREE: {
				ObjectSelectorTree objectSelectorTree = (ObjectSelectorTree)theEObject;
				T result = caseObjectSelectorTree(objectSelectorTree);
				if (result == null) result = caseMasterComponent(objectSelectorTree);
				if (result == null) result = caseOutlayableComponent(objectSelectorTree);
				if (result == null) result = caseUimComponent(objectSelectorTree);
				if (result == null) result = caseUserInteractionElement(objectSelectorTree);
				if (result == null) result = caseSecureObject(objectSelectorTree);
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
	 * Returns the result of interpreting the object as an instance of '<em>Tab Panel</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tab Panel</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUimTabPanel(UimTabPanel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tab</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tab</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUimTab(UimTab object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Panel</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Panel</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUimPanel(UimPanel object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Editable Secure Object</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Editable Secure Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEditableSecureObject(EditableSecureObject object) {
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
	public T caseLayoutContainer(LayoutContainer object) {
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
