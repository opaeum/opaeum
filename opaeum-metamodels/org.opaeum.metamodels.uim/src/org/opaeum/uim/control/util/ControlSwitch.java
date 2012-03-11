/**
 */
package org.opaeum.uim.control.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.opaeum.uim.control.ControlPackage;
import org.opaeum.uim.control.UimCheckBox;
import org.opaeum.uim.control.UimControl;
import org.opaeum.uim.control.UimDatePopup;
import org.opaeum.uim.control.UimDropdown;
import org.opaeum.uim.control.UimLookup;
import org.opaeum.uim.control.UimMultiSelectListBox;
import org.opaeum.uim.control.UimMultiSelectPopupSearch;
import org.opaeum.uim.control.UimMultiSelectTreeView;
import org.opaeum.uim.control.UimNumberScroller;
import org.opaeum.uim.control.UimSingleSelectListBox;
import org.opaeum.uim.control.UimSingleSelectPopupSearch;
import org.opaeum.uim.control.UimSingleSelectTreeView;
import org.opaeum.uim.control.UimText;
import org.opaeum.uim.control.UimTextArea;
import org.opaeum.uim.control.UimToggleButton;

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
 * @see org.opaeum.uim.control.ControlPackage
 * @generated
 */
public class ControlSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ControlPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ControlSwitch() {
		if (modelPackage == null) {
			modelPackage = ControlPackage.eINSTANCE;
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
			case ControlPackage.UIM_NUMBER_SCROLLER: {
				UimNumberScroller uimNumberScroller = (UimNumberScroller)theEObject;
				T result = caseUimNumberScroller(uimNumberScroller);
				if (result == null) result = caseUimControl(uimNumberScroller);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ControlPackage.UIM_TOGGLE_BUTTON: {
				UimToggleButton uimToggleButton = (UimToggleButton)theEObject;
				T result = caseUimToggleButton(uimToggleButton);
				if (result == null) result = caseUimControl(uimToggleButton);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ControlPackage.UIM_SINGLE_SELECT_POPUP_SEARCH: {
				UimSingleSelectPopupSearch uimSingleSelectPopupSearch = (UimSingleSelectPopupSearch)theEObject;
				T result = caseUimSingleSelectPopupSearch(uimSingleSelectPopupSearch);
				if (result == null) result = caseUimLookup(uimSingleSelectPopupSearch);
				if (result == null) result = caseUimControl(uimSingleSelectPopupSearch);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ControlPackage.UIM_MULTI_SELECT_POPUP_SEARCH: {
				UimMultiSelectPopupSearch uimMultiSelectPopupSearch = (UimMultiSelectPopupSearch)theEObject;
				T result = caseUimMultiSelectPopupSearch(uimMultiSelectPopupSearch);
				if (result == null) result = caseUimLookup(uimMultiSelectPopupSearch);
				if (result == null) result = caseUimControl(uimMultiSelectPopupSearch);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ControlPackage.UIM_MULTI_SELECT_TREE_VIEW: {
				UimMultiSelectTreeView uimMultiSelectTreeView = (UimMultiSelectTreeView)theEObject;
				T result = caseUimMultiSelectTreeView(uimMultiSelectTreeView);
				if (result == null) result = caseUimLookup(uimMultiSelectTreeView);
				if (result == null) result = caseUimControl(uimMultiSelectTreeView);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ControlPackage.UIM_MULTI_SELECT_LIST_BOX: {
				UimMultiSelectListBox uimMultiSelectListBox = (UimMultiSelectListBox)theEObject;
				T result = caseUimMultiSelectListBox(uimMultiSelectListBox);
				if (result == null) result = caseUimLookup(uimMultiSelectListBox);
				if (result == null) result = caseUimControl(uimMultiSelectListBox);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ControlPackage.UIM_DROPDOWN: {
				UimDropdown uimDropdown = (UimDropdown)theEObject;
				T result = caseUimDropdown(uimDropdown);
				if (result == null) result = caseUimLookup(uimDropdown);
				if (result == null) result = caseUimControl(uimDropdown);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ControlPackage.UIM_CHECK_BOX: {
				UimCheckBox uimCheckBox = (UimCheckBox)theEObject;
				T result = caseUimCheckBox(uimCheckBox);
				if (result == null) result = caseUimControl(uimCheckBox);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ControlPackage.UIM_LOOKUP: {
				UimLookup uimLookup = (UimLookup)theEObject;
				T result = caseUimLookup(uimLookup);
				if (result == null) result = caseUimControl(uimLookup);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ControlPackage.UIM_TEXT_AREA: {
				UimTextArea uimTextArea = (UimTextArea)theEObject;
				T result = caseUimTextArea(uimTextArea);
				if (result == null) result = caseUimControl(uimTextArea);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ControlPackage.UIM_TEXT: {
				UimText uimText = (UimText)theEObject;
				T result = caseUimText(uimText);
				if (result == null) result = caseUimControl(uimText);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ControlPackage.UIM_DATE_POPUP: {
				UimDatePopup uimDatePopup = (UimDatePopup)theEObject;
				T result = caseUimDatePopup(uimDatePopup);
				if (result == null) result = caseUimControl(uimDatePopup);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ControlPackage.UIM_SINGLE_SELECT_LIST_BOX: {
				UimSingleSelectListBox uimSingleSelectListBox = (UimSingleSelectListBox)theEObject;
				T result = caseUimSingleSelectListBox(uimSingleSelectListBox);
				if (result == null) result = caseUimLookup(uimSingleSelectListBox);
				if (result == null) result = caseUimControl(uimSingleSelectListBox);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ControlPackage.UIM_CONTROL: {
				UimControl uimControl = (UimControl)theEObject;
				T result = caseUimControl(uimControl);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ControlPackage.UIM_SINGLE_SELECT_TREE_VIEW: {
				UimSingleSelectTreeView uimSingleSelectTreeView = (UimSingleSelectTreeView)theEObject;
				T result = caseUimSingleSelectTreeView(uimSingleSelectTreeView);
				if (result == null) result = caseUimLookup(uimSingleSelectTreeView);
				if (result == null) result = caseUimControl(uimSingleSelectTreeView);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Uim Number Scroller</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Uim Number Scroller</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUimNumberScroller(UimNumberScroller object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Uim Toggle Button</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Uim Toggle Button</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUimToggleButton(UimToggleButton object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Uim Single Select Popup Search</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Uim Single Select Popup Search</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUimSingleSelectPopupSearch(UimSingleSelectPopupSearch object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Uim Multi Select Popup Search</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Uim Multi Select Popup Search</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUimMultiSelectPopupSearch(UimMultiSelectPopupSearch object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Uim Multi Select Tree View</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Uim Multi Select Tree View</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUimMultiSelectTreeView(UimMultiSelectTreeView object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Uim Multi Select List Box</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Uim Multi Select List Box</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUimMultiSelectListBox(UimMultiSelectListBox object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Uim Dropdown</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Uim Dropdown</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUimDropdown(UimDropdown object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Uim Check Box</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Uim Check Box</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUimCheckBox(UimCheckBox object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Uim Lookup</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Uim Lookup</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUimLookup(UimLookup object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Uim Text Area</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Uim Text Area</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUimTextArea(UimTextArea object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Uim Text</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Uim Text</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUimText(UimText object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Uim Date Popup</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Uim Date Popup</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUimDatePopup(UimDatePopup object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Uim Single Select List Box</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Uim Single Select List Box</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUimSingleSelectListBox(UimSingleSelectListBox object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Uim Control</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Uim Control</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUimControl(UimControl object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Uim Single Select Tree View</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Uim Single Select Tree View</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUimSingleSelectTreeView(UimSingleSelectTreeView object) {
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

} //ControlSwitch
