package org.opaeum.uim.util;

import org.eclipse.uml2.uml.OutputPin;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.TypedElement;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfParameterUtil;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.uim.UserInterfaceRoot;
import org.opaeum.uim.control.ControlFactory;
import org.opaeum.uim.control.ControlKind;
import org.opaeum.uim.control.UimControl;
import org.opaeum.uim.model.QueryInvoker;
import org.opaeum.uim.wizard.InvocationWizard;

public class ControlUtil{
	public static ControlKind[] getAllowedControlKinds(UserInterfaceRoot form,TypedElement typedElement,boolean inTable){
		// TODO factor
		Type type = typedElement.getType();
		if(typedElement == null || type == null || type.getName() == null){
			return new ControlKind[0];
		}else{
			if(EmfClassifierUtil.comformsToLibraryType(type, "Boolean")){
				return new ControlKind[]{ControlKind.CHECK_BOX,ControlKind.TOGGLE_BUTTON};
			}else if(EmfClassifierUtil.comformsToLibraryType(type, "Date") || EmfClassifierUtil.comformsToLibraryType(type,"datetime")){// TODO make this more sophisticated
				return new ControlKind[]{ControlKind.DATE_POPUP,ControlKind.TEXT,ControlKind.DATE_SCROLLER,ControlKind.DATE_TIME_POPUP};
			}else if(EmfClassifierUtil.comformsToLibraryType(type, "Integer")){
				return new ControlKind[]{ControlKind.NUMBER_SCROLLER,ControlKind.TEXT};
			}else if(EmfClassifierUtil.comformsToLibraryType(type, "Real")){
				return new ControlKind[]{ControlKind.TEXT};
			}else if(type instanceof org.eclipse.uml2.uml.Enumeration){
				if(inTable){
					return new ControlKind[]{ControlKind.DROPDOWN,ControlKind.POPUP_SEARCH,ControlKind.RADIO_BUTTON};
				}else{
					return new ControlKind[]{ControlKind.DROPDOWN,ControlKind.LIST_BOX,ControlKind.POPUP_SEARCH,ControlKind.TREE_VIEW,
							ControlKind.RADIO_BUTTON};
				}
			}else if(type instanceof org.eclipse.uml2.uml.Class
					|| type instanceof org.eclipse.uml2.uml.Interface){
				if(requiresManySelection(form, typedElement)){
					if(inTable){
						return new ControlKind[]{ControlKind.SELECTION_TABLE};
					}else{
						return new ControlKind[]{ControlKind.LIST_BOX,ControlKind.POPUP_SEARCH,ControlKind.TREE_VIEW,ControlKind.SELECTION_TABLE};
					}
				}else{
					if(inTable){
						return new ControlKind[]{ControlKind.DROPDOWN,ControlKind.POPUP_SEARCH,ControlKind.LABEL,ControlKind.LINK};
					}else{
						return new ControlKind[]{ControlKind.DROPDOWN,ControlKind.LIST_BOX,ControlKind.POPUP_SEARCH,ControlKind.TREE_VIEW,
								ControlKind.LABEL,ControlKind.LINK};
					}
				}
			}else{
				if(inTable){
					return new ControlKind[]{ControlKind.TEXT,ControlKind.LABEL};
				}else{
					return new ControlKind[]{ControlKind.TEXT,ControlKind.TEXT_AREA,ControlKind.LABEL};
				}
			}
		}
	}
	public static boolean requiresManySelection(UserInterfaceRoot form,TypedElement typedElement){
		if(typedElement instanceof Property){
			Property p = (Property) typedElement;
			if(p.getOtherEnd() != null){
				return EmfPropertyUtil.isMany(typedElement) && EmfPropertyUtil.isMany(p.getOtherEnd());
			}else{
				return EmfPropertyUtil.isMany(typedElement);
			}
		}else{
			return EmfPropertyUtil.isMany(typedElement) && requiresUserInput(form, typedElement);
		}
	}
	public static boolean requiresUserInput(UserInterfaceRoot uiRoot,TypedElement te){
		if(te instanceof Pin){
			return te instanceof OutputPin;
		}else if(te instanceof Parameter){
			Parameter p = (Parameter) te;
			if(uiRoot instanceof QueryInvoker || uiRoot instanceof InvocationWizard){
				return EmfParameterUtil.isArgument(p.getDirection());
			}else{
				return EmfParameterUtil.isResult(p.getDirection());
			}
		}
		return true;// property
	}
	public static ControlKind getPreferredControlKind(UserInterfaceRoot form,TypedElement typedElement,boolean inTable){
		ControlKind[] allowedControlKinds = getAllowedControlKinds(form, typedElement, inTable);
		if(allowedControlKinds.length == 0){
			return ControlKind.TEXT;
		}else{
			return allowedControlKinds[0];
		}
	}
	public static UimControl instantiate(ControlKind kind){
		switch(kind){
		case CHECK_BOX:
			return ControlFactory.eINSTANCE.createUimCheckBox();
		case TOGGLE_BUTTON:
			return ControlFactory.eINSTANCE.createUimToggleButton();
		case DATE_POPUP:
			return ControlFactory.eINSTANCE.createUimDatePopup();
		case DROPDOWN:
			return ControlFactory.eINSTANCE.createUimDropdown();
		case LIST_BOX:
			return ControlFactory.eINSTANCE.createUimListBox();
		case POPUP_SEARCH:
			return ControlFactory.eINSTANCE.createUimPopupSearch();
		case TREE_VIEW:
			return ControlFactory.eINSTANCE.createUimTreeView();
		case TEXT:
			return ControlFactory.eINSTANCE.createUimText();
		case NUMBER_SCROLLER:
			return ControlFactory.eINSTANCE.createUimNumberScroller();
		case TEXT_AREA:
			return ControlFactory.eINSTANCE.createUimTextArea();
		case DATE_SCROLLER:
			return ControlFactory.eINSTANCE.createUimDateScroller();
		case DATE_TIME_POPUP:
			return ControlFactory.eINSTANCE.createUimDateTimePopup();
		case LABEL:
			return ControlFactory.eINSTANCE.createUimLabel();
		case LINK:
			return ControlFactory.eINSTANCE.createUimLinkControl();
		case RADIO_BUTTON:
			return ControlFactory.eINSTANCE.createUimRadioButton();
		case SELECTION_TABLE:
			return ControlFactory.eINSTANCE.createUimSelectionTable();
		}
		return null;
	}
	public static boolean isMultiRow(ControlKind controlKind){
		return controlKind == ControlKind.LIST_BOX || controlKind == ControlKind.TREE_VIEW || controlKind == ControlKind.TEXT_AREA
				|| controlKind == ControlKind.LIST_BOX || controlKind == ControlKind.TREE_VIEW;
	}
	public static int getPreferredHeight(ControlKind kind){
		switch(kind){
		case TEXT:
		case NUMBER_SCROLLER:
		case CHECK_BOX:
		case TOGGLE_BUTTON:
		case DROPDOWN:
			return 35;
		case DATE_POPUP:
			return 150;
		case LIST_BOX:
			return 300;
		case POPUP_SEARCH:
			return 35;
		case TREE_VIEW:
			return 500;
		case TEXT_AREA:
			return 300;
		}
		return 25;
	}
	public static int getPreferredWidth(ControlKind kind){
		switch(kind){
		case TEXT:
		case NUMBER_SCROLLER:
		case CHECK_BOX:
		case TOGGLE_BUTTON:
		case DROPDOWN:
			return 300;
		case DATE_POPUP:
			return 300;
		case LIST_BOX:
			return 300;
		case POPUP_SEARCH:
			return 400;
		case TREE_VIEW:
			return 500;
		case TEXT_AREA:
			return 300;
		}
		return 25;
	}
}
