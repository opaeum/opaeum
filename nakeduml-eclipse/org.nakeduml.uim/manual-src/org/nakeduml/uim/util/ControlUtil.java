package org.nakeduml.uim.util;


import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.OutputPin;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.TypedElement;
import org.nakeduml.uim.ControlKind;
import org.nakeduml.uim.UIMControl;
import org.nakeduml.uim.UIMFactory;
import org.nakeduml.uim.UIMMultiSelectListBox;
import org.nakeduml.uim.UIMMultiSelectTreeView;
import org.nakeduml.uim.UIMSingleSelectListBox;
import org.nakeduml.uim.UIMSingleSelectTreeView;
import org.nakeduml.uim.UIMTextArea;

public class ControlUtil {
	public static ControlKind[] getAllowedControlKinds(TypedElement typedElement) {

		if (typedElement == null || typedElement.getType() == null) {
			return new ControlKind[0];
		} else if (typedElement.getType().getName().toLowerCase().endsWith(
				"boolean")) {
			return new ControlKind[] { ControlKind.CHECK_BOX,
					ControlKind.TOGGLE_BUTTON };
		} else if (typedElement.getType().getName().toLowerCase().endsWith(
				"date")) {
			return new ControlKind[] { ControlKind.DATE_POPUP, ControlKind.TEXT };
		} else if (typedElement.getType().getName().toLowerCase().endsWith(
				"integer")) {
			return new ControlKind[] { ControlKind.NUMBER_SCROLLER,
					ControlKind.TEXT };
		} else if (typedElement.getType() instanceof org.eclipse.uml2.uml.Class) {
			if (requiresManySelection(typedElement)) {
				return new ControlKind[] { ControlKind.MULTI_SELECT_LIST_BOX,
						ControlKind.MULTI_SELECT_POPUP_SEARCH,
						ControlKind.MULTI_SELECT_TREE_VIEW };

			} else {
				return new ControlKind[] { ControlKind.DROPDOWN,
						ControlKind.SINGLE_SELECT_LIST_BOX,
						ControlKind.SINGLE_SELECT_POPUP_SEARCH,
						ControlKind.SINGLE_SELECT_TREE_VIEW };
			}
		} else {
			return new ControlKind[] { ControlKind.TEXT, ControlKind.TEXT_AREA };
		}
	}

	public static boolean requiresManySelection(TypedElement typedElement) {
		if (typedElement instanceof Property) {
			Property p = (Property) typedElement;
			if (p.getOtherEnd() != null) {
				return UimUtil.isMany(typedElement)
						&& UimUtil.isMany(p.getOtherEnd());
			} else {
				return UimUtil.isMany(typedElement);
			}
		} else {
			return UimUtil.isMany(typedElement)
					&& requiresUserInput(typedElement);
		}

	}

	public static boolean requiresUserInput(TypedElement te) {
		if (te instanceof Pin) {
			return te instanceof OutputPin;
		} else if (te instanceof Parameter) {
			Parameter p = (Parameter) te;
			if (UimUtil.isTask(p.getOperation())) {
				return p.getDirection() != ParameterDirectionKind.IN_LITERAL;
			} else {
				return p.getDirection() == ParameterDirectionKind.IN_LITERAL;
			}
		}
		return true;//property
	}

	public static ControlKind getPreferredControlKind(TypedElement typedElement) {
		if (typedElement == null || typedElement.getType() == null) {
			return ControlKind.TEXT;
		} else if (typedElement.getType().getName().toLowerCase().endsWith(
				"boolean")) {
			return ControlKind.CHECK_BOX;
		} else if (typedElement.getType().getName().toLowerCase().endsWith(
				"integer")) {
			return ControlKind.NUMBER_SCROLLER;
		} else if (typedElement.getType().getName().toLowerCase().endsWith(
				"date")) {
			return ControlKind.DATE_POPUP;
		} else if (typedElement.getType() instanceof org.eclipse.uml2.uml.Class) {
			if (requiresManySelection(typedElement)) {
				return ControlKind.MULTI_SELECT_LIST_BOX;
			} else {
				return ControlKind.DROPDOWN;
			}
		} else {
			return ControlKind.TEXT;
		}

	}

	public static UIMControl instantiate(ControlKind kind) {
		switch (kind) {
		case CHECK_BOX:
			return UIMFactory.eINSTANCE.createUIMCheckBox();
		case TOGGLE_BUTTON:
			return UIMFactory.eINSTANCE.createUIMToggleButton();
		case DATE_POPUP:
			return UIMFactory.eINSTANCE.createUIMDatePopup();
		case DROPDOWN:
			return UIMFactory.eINSTANCE.createUIMDropdown();
		case SINGLE_SELECT_LIST_BOX:
			return UIMFactory.eINSTANCE.createUIMSingleSelectListBox();
		case MULTI_SELECT_LIST_BOX:
			return UIMFactory.eINSTANCE.createUIMMultiSelectListBox();
		case SINGLE_SELECT_POPUP_SEARCH:
			return UIMFactory.eINSTANCE.createUIMSingleSelectPopupSearch();
		case MULTI_SELECT_POPUP_SEARCH:
			return UIMFactory.eINSTANCE.createUIMMultiSelectPopupSearch();
		case SINGLE_SELECT_TREE_VIEW:
			return UIMFactory.eINSTANCE.createUIMSingleSelectTreeView();
		case MULTI_SELECT_TREE_VIEW:
			return UIMFactory.eINSTANCE.createUIMMultiSelectTreeView();
		case TEXT:
			return UIMFactory.eINSTANCE.createUIMText();
		case NUMBER_SCROLLER:
			return UIMFactory.eINSTANCE.createUIMNumberScroller();
		case TEXT_AREA:
			return UIMFactory.eINSTANCE.createUIMTextArea();
		}
		return null;
	}

	public static boolean isMultiRow(ControlKind controlKind) {
		return controlKind == ControlKind.MULTI_SELECT_LIST_BOX
				|| controlKind == ControlKind.MULTI_SELECT_TREE_VIEW
				|| controlKind == ControlKind.TEXT_AREA
				|| controlKind == ControlKind.SINGLE_SELECT_LIST_BOX
				|| controlKind == ControlKind.SINGLE_SELECT_TREE_VIEW;
	}

}
