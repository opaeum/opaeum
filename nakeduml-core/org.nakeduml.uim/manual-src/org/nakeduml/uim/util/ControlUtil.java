package org.nakeduml.uim.util;

import org.eclipse.uml2.uml.OutputPin;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.TypedElement;
import org.nakeduml.eclipse.EmfPropertyUtil;
import org.nakeduml.uim.control.ControlFactory;
import org.nakeduml.uim.control.ControlKind;
import org.nakeduml.uim.control.UimControl;
import org.nakeduml.uim.form.FormPanel;
import org.nakeduml.uim.form.OperationInvocationForm;

public class ControlUtil{
	public static ControlKind[] getAllowedControlKinds(FormPanel form,TypedElement typedElement){
		if(typedElement == null || typedElement.getType() == null){
			return new ControlKind[0];
		}else{
			String name = typedElement.getType().getName().toLowerCase();
			if(name.endsWith("boolean")){
				return new ControlKind[]{
						ControlKind.CHECK_BOX,ControlKind.TOGGLE_BUTTON
				};
			}else if(name.endsWith("date") || name.endsWith("datetime")){
				return new ControlKind[]{
						ControlKind.DATE_POPUP,ControlKind.TEXT
				};
			}else if(name.endsWith("integer")){
				return new ControlKind[]{
						ControlKind.NUMBER_SCROLLER
				};
			}else if(typedElement.getType() instanceof org.eclipse.uml2.uml.Class){
				if(requiresManySelection(form, typedElement)){
					return new ControlKind[]{
							ControlKind.MULTI_SELECT_LIST_BOX,ControlKind.MULTI_SELECT_POPUP_SEARCH,ControlKind.MULTI_SELECT_TREE_VIEW
					};
				}else{
					return new ControlKind[]{
							ControlKind.DROPDOWN,ControlKind.SINGLE_SELECT_LIST_BOX,ControlKind.SINGLE_SELECT_POPUP_SEARCH,ControlKind.SINGLE_SELECT_TREE_VIEW
					};
				}
			}else{
				return new ControlKind[]{
						ControlKind.TEXT,ControlKind.TEXT_AREA
				};
			}
		}
	}
	public static boolean requiresManySelection(FormPanel form,TypedElement typedElement){
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
	public static boolean requiresUserInput(FormPanel form,TypedElement te){
		if(te instanceof Pin){
			return te instanceof OutputPin;
		}else if(te instanceof Parameter){
			Parameter p = (Parameter) te;
			if(form instanceof OperationInvocationForm){
				return p.getDirection() != ParameterDirectionKind.IN_LITERAL;
			}else{
				return p.getDirection() != ParameterDirectionKind.OUT_LITERAL && p.getDirection() != ParameterDirectionKind.RETURN_LITERAL;
			}
		}
		return true;// property
	}
	public static ControlKind getPreferredControlKind(FormPanel form,TypedElement typedElement){
		// Use strategy or something
		if(typedElement == null || typedElement.getType() == null){
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
					return ControlKind.MULTI_SELECT_LIST_BOX;
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
		case SINGLE_SELECT_LIST_BOX:
			return ControlFactory.eINSTANCE.createUimSingleSelectListBox();
		case MULTI_SELECT_LIST_BOX:
			return ControlFactory.eINSTANCE.createUimMultiSelectListBox();
		case SINGLE_SELECT_POPUP_SEARCH:
			return ControlFactory.eINSTANCE.createUimSingleSelectPopupSearch();
		case MULTI_SELECT_POPUP_SEARCH:
			return ControlFactory.eINSTANCE.createUimMultiSelectPopupSearch();
		case SINGLE_SELECT_TREE_VIEW:
			return ControlFactory.eINSTANCE.createUimSingleSelectTreeView();
		case MULTI_SELECT_TREE_VIEW:
			return ControlFactory.eINSTANCE.createUimMultiSelectTreeView();
		case TEXT:
			return ControlFactory.eINSTANCE.createUimText();
		case NUMBER_SCROLLER:
			return ControlFactory.eINSTANCE.createUimNumberScroller();
		case TEXT_AREA:
			return ControlFactory.eINSTANCE.createUimTextArea();
		}
		return null;
	}
	public static boolean isMultiRow(ControlKind controlKind){
		return controlKind == ControlKind.MULTI_SELECT_LIST_BOX || controlKind == ControlKind.MULTI_SELECT_TREE_VIEW || controlKind == ControlKind.TEXT_AREA
				|| controlKind == ControlKind.SINGLE_SELECT_LIST_BOX || controlKind == ControlKind.SINGLE_SELECT_TREE_VIEW;
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
		case SINGLE_SELECT_LIST_BOX:
		case MULTI_SELECT_LIST_BOX:
			return 300;
		case SINGLE_SELECT_POPUP_SEARCH:
		case MULTI_SELECT_POPUP_SEARCH:
			return 35;
		case SINGLE_SELECT_TREE_VIEW:
		case MULTI_SELECT_TREE_VIEW:
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
		case SINGLE_SELECT_LIST_BOX:
		case MULTI_SELECT_LIST_BOX:
			return 300;
		case SINGLE_SELECT_POPUP_SEARCH:
		case MULTI_SELECT_POPUP_SEARCH:
			return 400;
		case SINGLE_SELECT_TREE_VIEW:
		case MULTI_SELECT_TREE_VIEW:
			return 500;
		case TEXT_AREA:
			return 300;
		}
		return 25;
	}
}
