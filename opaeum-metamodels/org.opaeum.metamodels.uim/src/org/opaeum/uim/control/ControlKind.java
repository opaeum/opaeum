/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opaeum.uim.control;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.opaeum.uim.control.ControlPackage#getControlKind()
 * @model
 * @generated
 */
public enum ControlKind implements Enumerator {
	/**
	 * The '<em><b>Date Popup</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DATE_POPUP_VALUE
	 * @generated
	 * @ordered
	 */
	DATE_POPUP(0, "datePopup", "datePopup"),

	/**
	 * The '<em><b>Dropdown</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DROPDOWN_VALUE
	 * @generated
	 * @ordered
	 */
	DROPDOWN(1, "dropdown", "dropdown"),

	/**
	 * The '<em><b>Text</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TEXT_VALUE
	 * @generated
	 * @ordered
	 */
	TEXT(3, "text", "text"),

	/**
	 * The '<em><b>Text Area</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TEXT_AREA_VALUE
	 * @generated
	 * @ordered
	 */
	TEXT_AREA(4, "textArea", "textArea"),

	/**
	 * The '<em><b>Single Select List Box</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SINGLE_SELECT_LIST_BOX_VALUE
	 * @generated
	 * @ordered
	 */
	SINGLE_SELECT_LIST_BOX(5, "singleSelectListBox", "singleSelectListBox"),

	/**
	 * The '<em><b>Check Box</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CHECK_BOX_VALUE
	 * @generated
	 * @ordered
	 */
	CHECK_BOX(7, "checkBox", "checkBox"),

	/**
	 * The '<em><b>Number Scroller</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NUMBER_SCROLLER_VALUE
	 * @generated
	 * @ordered
	 */
	NUMBER_SCROLLER(9, "numberScroller", "numberScroller"),

	/**
	 * The '<em><b>Single Select Tree View</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SINGLE_SELECT_TREE_VIEW_VALUE
	 * @generated
	 * @ordered
	 */
	SINGLE_SELECT_TREE_VIEW(8, "singleSelectTreeView", "singleSelectTreeView"),

	/**
	 * The '<em><b>Multi Select Tree View</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MULTI_SELECT_TREE_VIEW_VALUE
	 * @generated
	 * @ordered
	 */
	MULTI_SELECT_TREE_VIEW(10, "multiSelectTreeView", "multiSelectTreeView"),

	/**
	 * The '<em><b>Multi Select List Box</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MULTI_SELECT_LIST_BOX_VALUE
	 * @generated
	 * @ordered
	 */
	MULTI_SELECT_LIST_BOX(11, "multiSelectListBox", "multiSelectListBox"),

	/**
	 * The '<em><b>Single Select Popup Search</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SINGLE_SELECT_POPUP_SEARCH_VALUE
	 * @generated
	 * @ordered
	 */
	SINGLE_SELECT_POPUP_SEARCH(13, "singleSelectPopupSearch", "singleSelectPopupSearch"),

	/**
	 * The '<em><b>Multi Select Popup Search</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MULTI_SELECT_POPUP_SEARCH_VALUE
	 * @generated
	 * @ordered
	 */
	MULTI_SELECT_POPUP_SEARCH(6, "multiSelectPopupSearch", "multiSelectPopupSearch"),

	/**
	 * The '<em><b>Toggle Button</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TOGGLE_BUTTON_VALUE
	 * @generated
	 * @ordered
	 */
	TOGGLE_BUTTON(12, "toggleButton", "toggleButton");

	/**
	 * The '<em><b>Date Popup</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Date Popup</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DATE_POPUP
	 * @model name="datePopup"
	 * @generated
	 * @ordered
	 */
	public static final int DATE_POPUP_VALUE = 0;

	/**
	 * The '<em><b>Dropdown</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Dropdown</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DROPDOWN
	 * @model name="dropdown"
	 * @generated
	 * @ordered
	 */
	public static final int DROPDOWN_VALUE = 1;

	/**
	 * The '<em><b>Text</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Displays a single line text input field
	 * <!-- end-model-doc -->
	 * @see #TEXT
	 * @model name="text"
	 * @generated
	 * @ordered
	 */
	public static final int TEXT_VALUE = 3;

	/**
	 * The '<em><b>Text Area</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Displays a multiline text area
	 * <!-- end-model-doc -->
	 * @see #TEXT_AREA
	 * @model name="textArea"
	 * @generated
	 * @ordered
	 */
	public static final int TEXT_AREA_VALUE = 4;

	/**
	 * The '<em><b>Single Select List Box</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Single Select List Box</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SINGLE_SELECT_LIST_BOX
	 * @model name="singleSelectListBox"
	 * @generated
	 * @ordered
	 */
	public static final int SINGLE_SELECT_LIST_BOX_VALUE = 5;

	/**
	 * The '<em><b>Check Box</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Check Box</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CHECK_BOX
	 * @model name="checkBox"
	 * @generated
	 * @ordered
	 */
	public static final int CHECK_BOX_VALUE = 7;

	/**
	 * The '<em><b>Number Scroller</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Number Scroller</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NUMBER_SCROLLER
	 * @model name="numberScroller"
	 * @generated
	 * @ordered
	 */
	public static final int NUMBER_SCROLLER_VALUE = 9;

	/**
	 * The '<em><b>Single Select Tree View</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Single Select Tree View</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SINGLE_SELECT_TREE_VIEW
	 * @model name="singleSelectTreeView"
	 * @generated
	 * @ordered
	 */
	public static final int SINGLE_SELECT_TREE_VIEW_VALUE = 8;

	/**
	 * The '<em><b>Multi Select Tree View</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Multi Select Tree View</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MULTI_SELECT_TREE_VIEW
	 * @model name="multiSelectTreeView"
	 * @generated
	 * @ordered
	 */
	public static final int MULTI_SELECT_TREE_VIEW_VALUE = 10;

	/**
	 * The '<em><b>Multi Select List Box</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Multi Select List Box</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MULTI_SELECT_LIST_BOX
	 * @model name="multiSelectListBox"
	 * @generated
	 * @ordered
	 */
	public static final int MULTI_SELECT_LIST_BOX_VALUE = 11;

	/**
	 * The '<em><b>Single Select Popup Search</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Single Select Popup Search</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SINGLE_SELECT_POPUP_SEARCH
	 * @model name="singleSelectPopupSearch"
	 * @generated
	 * @ordered
	 */
	public static final int SINGLE_SELECT_POPUP_SEARCH_VALUE = 13;

	/**
	 * The '<em><b>Multi Select Popup Search</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Displays a dialog box that allows the user to search for matches and select multiple entries
	 * <!-- end-model-doc -->
	 * @see #MULTI_SELECT_POPUP_SEARCH
	 * @model name="multiSelectPopupSearch"
	 * @generated
	 * @ordered
	 */
	public static final int MULTI_SELECT_POPUP_SEARCH_VALUE = 6;

	/**
	 * The '<em><b>Toggle Button</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Toggle Button</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TOGGLE_BUTTON
	 * @model name="toggleButton"
	 * @generated
	 * @ordered
	 */
	public static final int TOGGLE_BUTTON_VALUE = 12;

	/**
	 * An array of all the '<em><b>Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ControlKind[] VALUES_ARRAY =
		new ControlKind[] {
			DATE_POPUP,
			DROPDOWN,
			TEXT,
			TEXT_AREA,
			SINGLE_SELECT_LIST_BOX,
			CHECK_BOX,
			NUMBER_SCROLLER,
			SINGLE_SELECT_TREE_VIEW,
			MULTI_SELECT_TREE_VIEW,
			MULTI_SELECT_LIST_BOX,
			SINGLE_SELECT_POPUP_SEARCH,
			MULTI_SELECT_POPUP_SEARCH,
			TOGGLE_BUTTON,
		};

	/**
	 * A public read-only list of all the '<em><b>Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<ControlKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Kind</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ControlKind get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ControlKind result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Kind</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ControlKind getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ControlKind result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Kind</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ControlKind get(int value) {
		switch (value) {
			case DATE_POPUP_VALUE: return DATE_POPUP;
			case DROPDOWN_VALUE: return DROPDOWN;
			case TEXT_VALUE: return TEXT;
			case TEXT_AREA_VALUE: return TEXT_AREA;
			case SINGLE_SELECT_LIST_BOX_VALUE: return SINGLE_SELECT_LIST_BOX;
			case CHECK_BOX_VALUE: return CHECK_BOX;
			case NUMBER_SCROLLER_VALUE: return NUMBER_SCROLLER;
			case SINGLE_SELECT_TREE_VIEW_VALUE: return SINGLE_SELECT_TREE_VIEW;
			case MULTI_SELECT_TREE_VIEW_VALUE: return MULTI_SELECT_TREE_VIEW;
			case MULTI_SELECT_LIST_BOX_VALUE: return MULTI_SELECT_LIST_BOX;
			case SINGLE_SELECT_POPUP_SEARCH_VALUE: return SINGLE_SELECT_POPUP_SEARCH;
			case MULTI_SELECT_POPUP_SEARCH_VALUE: return MULTI_SELECT_POPUP_SEARCH;
			case TOGGLE_BUTTON_VALUE: return TOGGLE_BUTTON;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private ControlKind(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //ControlKind
