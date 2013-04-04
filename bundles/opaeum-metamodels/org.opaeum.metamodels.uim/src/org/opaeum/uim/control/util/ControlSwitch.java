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
import org.opaeum.uim.control.UimDateScroller;
import org.opaeum.uim.control.UimDateTimePopup;
import org.opaeum.uim.control.UimDropdown;
import org.opaeum.uim.control.UimLabel;
import org.opaeum.uim.control.UimLinkControl;
import org.opaeum.uim.control.UimListBox;
import org.opaeum.uim.control.UimLookup;
import org.opaeum.uim.control.UimNumberScroller;
import org.opaeum.uim.control.UimPopupSearch;
import org.opaeum.uim.control.UimRadioButton;
import org.opaeum.uim.control.UimSelectionTable;
import org.opaeum.uim.control.UimText;
import org.opaeum.uim.control.UimTextArea;
import org.opaeum.uim.control.UimToggleButton;
import org.opaeum.uim.control.UimTreeView;

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
			case ControlPackage.UIM_POPUP_SEARCH: {
				UimPopupSearch uimPopupSearch = (UimPopupSearch)theEObject;
				T result = caseUimPopupSearch(uimPopupSearch);
				if (result == null) result = caseUimLookup(uimPopupSearch);
				if (result == null) result = caseUimControl(uimPopupSearch);
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
			case ControlPackage.UIM_LIST_BOX: {
				UimListBox uimListBox = (UimListBox)theEObject;
				T result = caseUimListBox(uimListBox);
				if (result == null) result = caseUimLookup(uimListBox);
				if (result == null) result = caseUimControl(uimListBox);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ControlPackage.UIM_CONTROL: {
				UimControl uimControl = (UimControl)theEObject;
				T result = caseUimControl(uimControl);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ControlPackage.UIM_TREE_VIEW: {
				UimTreeView uimTreeView = (UimTreeView)theEObject;
				T result = caseUimTreeView(uimTreeView);
				if (result == null) result = caseUimLookup(uimTreeView);
				if (result == null) result = caseUimControl(uimTreeView);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ControlPackage.UIM_LINK_CONTROL: {
				UimLinkControl uimLinkControl = (UimLinkControl)theEObject;
				T result = caseUimLinkControl(uimLinkControl);
				if (result == null) result = caseUimControl(uimLinkControl);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ControlPackage.UIM_DATE_SCROLLER: {
				UimDateScroller uimDateScroller = (UimDateScroller)theEObject;
				T result = caseUimDateScroller(uimDateScroller);
				if (result == null) result = caseUimControl(uimDateScroller);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ControlPackage.UIM_SELECTION_TABLE: {
				UimSelectionTable uimSelectionTable = (UimSelectionTable)theEObject;
				T result = caseUimSelectionTable(uimSelectionTable);
				if (result == null) result = caseUimControl(uimSelectionTable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ControlPackage.UIM_RADIO_BUTTON: {
				UimRadioButton uimRadioButton = (UimRadioButton)theEObject;
				T result = caseUimRadioButton(uimRadioButton);
				if (result == null) result = caseUimControl(uimRadioButton);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ControlPackage.UIM_LABEL: {
				UimLabel uimLabel = (UimLabel)theEObject;
				T result = caseUimLabel(uimLabel);
				if (result == null) result = caseUimControl(uimLabel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ControlPackage.UIM_DATE_TIME_POPUP: {
				UimDateTimePopup uimDateTimePopup = (UimDateTimePopup)theEObject;
				T result = caseUimDateTimePopup(uimDateTimePopup);
				if (result == null) result = caseUimControl(uimDateTimePopup);
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
	 * Returns the result of interpreting the object as an instance of '<em>Uim Popup Search</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Uim Popup Search</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUimPopupSearch(UimPopupSearch object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Uim List Box</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Uim List Box</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUimListBox(UimListBox object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Uim Tree View</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Uim Tree View</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUimTreeView(UimTreeView object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Uim Link Control</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Uim Link Control</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUimLinkControl(UimLinkControl object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Uim Date Scroller</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Uim Date Scroller</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUimDateScroller(UimDateScroller object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Uim Selection Table</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Uim Selection Table</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUimSelectionTable(UimSelectionTable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Uim Radio Button</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Uim Radio Button</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUimRadioButton(UimRadioButton object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Uim Label</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Uim Label</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUimLabel(UimLabel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Uim Date Time Popup</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Uim Date Time Popup</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUimDateTimePopup(UimDateTimePopup object) {
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
