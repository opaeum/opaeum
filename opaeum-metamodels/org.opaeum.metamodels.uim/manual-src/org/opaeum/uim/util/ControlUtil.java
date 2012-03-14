package org.opaeum.uim.util;

import org.eclipse.uml2.uml.OutputPin;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.TypedElement;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.uim.UserInterface;
import org.opaeum.uim.action.OperationButton;
import org.opaeum.uim.control.ControlFactory;
import org.opaeum.uim.control.ControlKind;
import org.opaeum.uim.control.UimControl;
import org.opaeum.uim.editor.QueryInvocationEditor;

public class ControlUtil{
	public static ControlKind[] getAllowedControlKinds(UserInterface form,TypedElement typedElement,boolean inTable){
		if(typedElement == null || typedElement.getType() == null){
			return new ControlKind[0];
		}else{
			String name = typedElement.getType().getName().toLowerCase();
			if(name.endsWith("boolean")){
				return new ControlKind[]{ControlKind.CHECK_BOX,ControlKind.TOGGLE_BUTTON};
			}else if(name.endsWith("date") || name.endsWith("datetime")){// TODO make this more sophisticated
				return new ControlKind[]{ControlKind.DATE_POPUP,ControlKind.TEXT,ControlKind.DATE_SCROLLER,ControlKind.DATE_TIME_POPUP};
			}else if(name.endsWith("integer")){
				return new ControlKind[]{ControlKind.NUMBER_SCROLLER};
			}else if(typedElement.getType() instanceof org.eclipse.uml2.uml.Enumeration){
				if(inTable){
					return new ControlKind[]{ControlKind.DROPDOWN,ControlKind.POPUP_SEARCH,ControlKind.RADIO_BUTTON};
				}else{
					return new ControlKind[]{ControlKind.DROPDOWN,ControlKind.LIST_BOX,ControlKind.POPUP_SEARCH,ControlKind.TREE_VIEW,
							ControlKind.RADIO_BUTTON};
				}
			}else if(typedElement.getType() instanceof org.eclipse.uml2.uml.Class
					|| typedElement.getType() instanceof org.eclipse.uml2.uml.Interface){
				if(requiresManySelection(form, typedElement)){
					if(inTable){
						return new ControlKind[]{ControlKind.SELECTION_TABLE};
					}else{
						return new ControlKind[]{ControlKind.LIST_BOX,ControlKind.POPUP_SEARCH,ControlKind.TREE_VIEW};
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
	public static boolean requiresManySelection(UserInterface form,TypedElement typedElement){
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
	public static boolean requiresUserInput(UserInterface form,TypedElement te){
		if(te instanceof Pin){
			return te instanceof OutputPin;
		}else if(te instanceof Parameter){
			Parameter p = (Parameter) te;
			if(form.eContainer() instanceof QueryInvocationEditor || form.eContainer() instanceof OperationButton){
				return p.getDirection() != ParameterDirectionKind.IN_LITERAL;
			}else{
				return p.getDirection() != ParameterDirectionKind.OUT_LITERAL && p.getDirection() != ParameterDirectionKind.RETURN_LITERAL;
			}
		}
		return true;// property
	}
	public static ControlKind getPreferredControlKind(UserInterface form,TypedElement typedElement){
		// Use strategy or something
		if(typedElement == null || typedElement.getType() == null || typedElement.getType().getName()==null){
			return ControlKind.TEXT;
		}else{
			String typeName = typedElement.getType().getName().toLowerCase();
			if(typeName.endsWith("boolean")){
				return ControlKind.CHECK_BOX;
			}else if(typeName.endsWith("integer")){
				return ControlKind.NUMBER_SCROLLER;
			}else if(typeName.endsWith("date") || typeName.endsWith("datetime")){
				return ControlKind.DATE_POPUP;
			}else if(typedElement.getType() instanceof org.eclipse.uml2.uml.Class){
				if(requiresManySelection(form, typedElement)){
					return ControlKind.LIST_BOX;
				}else{
					return ControlKind.DROPDOWN;
				}
			}else{
				return ControlKind.TEXT;
			}
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
