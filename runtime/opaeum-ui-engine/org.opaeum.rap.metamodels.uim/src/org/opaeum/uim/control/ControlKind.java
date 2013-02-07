package org.opaeum.uim.control;


public enum ControlKind {
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
	DATE_TIME_POPUP;


	static public ControlKind getByName(String name) {
		ControlKind result = null;
		if ( "datePopup".equals(name) ) {
			return DATE_POPUP;
		}
		if ( "dropdown".equals(name) ) {
			return DROPDOWN;
		}
		if ( "text".equals(name) ) {
			return TEXT;
		}
		if ( "textArea".equals(name) ) {
			return TEXT_AREA;
		}
		if ( "listBox".equals(name) ) {
			return LIST_BOX;
		}
		if ( "checkBox".equals(name) ) {
			return CHECK_BOX;
		}
		if ( "numberScroller".equals(name) ) {
			return NUMBER_SCROLLER;
		}
		if ( "treeView".equals(name) ) {
			return TREE_VIEW;
		}
		if ( "popupSearch".equals(name) ) {
			return POPUP_SEARCH;
		}
		if ( "toggleButton".equals(name) ) {
			return TOGGLE_BUTTON;
		}
		if ( "selectionTable".equals(name) ) {
			return SELECTION_TABLE;
		}
		if ( "link".equals(name) ) {
			return LINK;
		}
		if ( "radioButton".equals(name) ) {
			return RADIO_BUTTON;
		}
		if ( "label".equals(name) ) {
			return LABEL;
		}
		if ( "dateScroller".equals(name) ) {
			return DATE_SCROLLER;
		}
		if ( "dateTimePopup".equals(name) ) {
			return DATE_TIME_POPUP;
		}
		return result;
	}

}