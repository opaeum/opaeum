/**
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
	TEXT(2, "text", "text"),

	/**
	 * The '<em><b>Text Area</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TEXT_AREA_VALUE
	 * @generated
	 * @ordered
	 */
	TEXT_AREA(3, "textArea", "textArea"),

	/**
	 * The '<em><b>List Box</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LIST_BOX_VALUE
	 * @generated
	 * @ordered
	 */
	LIST_BOX(4, "listBox", "listBox"), /**
	 * The '<em><b>Check Box</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CHECK_BOX_VALUE
	 * @generated
	 * @ordered
	 */
	CHECK_BOX(5, "checkBox", "checkBox"),

	/**
	 * The '<em><b>Number Scroller</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NUMBER_SCROLLER_VALUE
	 * @generated
	 * @ordered
	 */
	NUMBER_SCROLLER(6, "numberScroller", "numberScroller"),

	/**
	 * The '<em><b>Tree View</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TREE_VIEW_VALUE
	 * @generated
	 * @ordered
	 */
	TREE_VIEW(7, "treeView", "treeView"), /**
	 * The '<em><b>Popup Search</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #POPUP_SEARCH_VALUE
	 * @generated
	 * @ordered
	 */
	POPUP_SEARCH(8, "popupSearch", "popupSearch"), /**
	 * The '<em><b>Toggle Button</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TOGGLE_BUTTON_VALUE
	 * @generated
	 * @ordered
	 */
	TOGGLE_BUTTON(9, "toggleButton", "toggleButton"), /**
	 * The '<em><b>Selection Table</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SELECTION_TABLE_VALUE
	 * @generated
	 * @ordered
	 */
	SELECTION_TABLE(10, "selectionTable", "selectionTable"), /**
	 * The '<em><b>Link</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LINK_VALUE
	 * @generated
	 * @ordered
	 */
	LINK(11, "link", "link"), /**
	 * The '<em><b>Radio Button</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RADIO_BUTTON_VALUE
	 * @generated
	 * @ordered
	 */
	RADIO_BUTTON(12, "radioButton", "radioButton"), /**
	 * The '<em><b>Label</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LABEL_VALUE
	 * @generated
	 * @ordered
	 */
	LABEL(13, "label", "label"), /**
	 * The '<em><b>Date Scroller</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DATE_SCROLLER_VALUE
	 * @generated
	 * @ordered
	 */
	DATE_SCROLLER(14, "dateScroller", "dateScroller"), /**
	 * The '<em><b>Date Time Popup</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DATE_TIME_POPUP_VALUE
	 * @generated
	 * @ordered
	 */
	DATE_TIME_POPUP(15, "dateTimePopup", "dateTimePopup");

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
	public static final int TEXT_VALUE = 2;

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
	public static final int TEXT_AREA_VALUE = 3;

	/**
	 * The '<em><b>List Box</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>List Box</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LIST_BOX
	 * @model name="listBox"
	 * @generated
	 * @ordered
	 */
	public static final int LIST_BOX_VALUE = 4;

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
	public static final int CHECK_BOX_VALUE = 5;

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
	public static final int NUMBER_SCROLLER_VALUE = 6;

	/**
	 * The '<em><b>Tree View</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Tree View</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TREE_VIEW
	 * @model name="treeView"
	 * @generated
	 * @ordered
	 */
	public static final int TREE_VIEW_VALUE = 7;

	/**
	 * The '<em><b>Popup Search</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Popup Search</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #POPUP_SEARCH
	 * @model name="popupSearch"
	 * @generated
	 * @ordered
	 */
	public static final int POPUP_SEARCH_VALUE = 8;

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
	public static final int TOGGLE_BUTTON_VALUE = 9;

	/**
	 * The '<em><b>Selection Table</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Selection Table</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SELECTION_TABLE
	 * @model name="selectionTable"
	 * @generated
	 * @ordered
	 */
	public static final int SELECTION_TABLE_VALUE = 10;

	/**
	 * The '<em><b>Link</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Link</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LINK
	 * @model name="link"
	 * @generated
	 * @ordered
	 */
	public static final int LINK_VALUE = 11;

	/**
	 * The '<em><b>Radio Button</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Radio Button</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #RADIO_BUTTON
	 * @model name="radioButton"
	 * @generated
	 * @ordered
	 */
	public static final int RADIO_BUTTON_VALUE = 12;

	/**
	 * The '<em><b>Label</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Label</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LABEL
	 * @model name="label"
	 * @generated
	 * @ordered
	 */
	public static final int LABEL_VALUE = 13;

	/**
	 * The '<em><b>Date Scroller</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Date Scroller</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DATE_SCROLLER
	 * @model name="dateScroller"
	 * @generated
	 * @ordered
	 */
	public static final int DATE_SCROLLER_VALUE = 14;

	/**
	 * The '<em><b>Date Time Popup</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Date Time Popup</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DATE_TIME_POPUP
	 * @model name="dateTimePopup"
	 * @generated
	 * @ordered
	 */
	public static final int DATE_TIME_POPUP_VALUE = 15;

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
			LIST_BOX,
			CHECK_BOX,
			NUMBER_SCROLLER,
			TREE_VIEW,
			POPUP_SEARCH,
			TOGGLE_BUTTON,
			SELECTION_TABLE,
			LINK,
			RADIO_BUTTON,
			LABEL,
			DATE_SCROLLER,
			DATE_TIME_POPUP,
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
			case LIST_BOX_VALUE: return LIST_BOX;
			case CHECK_BOX_VALUE: return CHECK_BOX;
			case NUMBER_SCROLLER_VALUE: return NUMBER_SCROLLER;
			case TREE_VIEW_VALUE: return TREE_VIEW;
			case POPUP_SEARCH_VALUE: return POPUP_SEARCH;
			case TOGGLE_BUTTON_VALUE: return TOGGLE_BUTTON;
			case SELECTION_TABLE_VALUE: return SELECTION_TABLE;
			case LINK_VALUE: return LINK;
			case RADIO_BUTTON_VALUE: return RADIO_BUTTON;
			case LABEL_VALUE: return LABEL;
			case DATE_SCROLLER_VALUE: return DATE_SCROLLER;
			case DATE_TIME_POPUP_VALUE: return DATE_TIME_POPUP;
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
