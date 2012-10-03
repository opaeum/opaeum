package org.opaeum.uimodeler.userinterface.diagram.part;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.uml.diagram.common.providers.BaseViewInfo;
import org.eclipse.papyrus.uml.diagram.common.providers.ViewInfo;
import org.opaeum.uim.Page;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.action.ActionPackage;
import org.opaeum.uim.component.ComponentPackage;
import org.opaeum.uim.panel.PanelPackage;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.BuiltInActionButton2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.BuiltInActionButton3EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.BuiltInActionButtonEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.BuiltInLink2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.BuiltInLinkEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.BuiltInLinkNameEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.GridPanel2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.GridPanelEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.GridPanelGridPanelChildrenCompartment2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.GridPanelGridPanelChildrenCompartmentEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.GridPanelName2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.GridPanelNameEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.HorizontalPanel2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.HorizontalPanelEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.LinkToQueryEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.LinkToQueryNameEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.InvocationButton2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.InvocationButton3EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.InvocationButtonEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.InvocationButtonName2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.InvocationButtonNameEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.TransitionButtonEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.TransitionButtonNameEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UimDataTableDataTableColumnCompartmentEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UimDataTableEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UimDataTableTableTableActionBarCompartmentEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UimField2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UimFieldEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UserInterfaceEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.VerticalPanel2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.VerticalPanelEditPart;

/**
 * This registry is used to determine which type of visual object should be
 * created for the corresponding Diagram, Node, ChildNode or Link represented
 * by a domain model object.
 * 
 * @generated
 */
public class UimVisualIDRegistry{
	/**
	 * @generated
	 */
	private static final String DEBUG_KEY = "org.opaeum.uimodeler.userinterface.diagram/debug/visualID"; //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static int getVisualID(View view){
		if(view instanceof Diagram){
			if(UserInterfaceEditPart.MODEL_ID.equals(view.getType())){
				return UserInterfaceEditPart.VISUAL_ID;
			}else{
				return -1;
			}
		}
		return org.opaeum.uimodeler.userinterface.diagram.part.UimVisualIDRegistry.getVisualID(view.getType());
	}
	/**
	 * @generated
	 */
	public static String getModelID(View view){
		View diagram = view.getDiagram();
		while(view != diagram){
			EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
			if(annotation != null){
				return (String) annotation.getDetails().get("modelID"); //$NON-NLS-1$
			}
			view = (View) view.eContainer();
		}
		return diagram != null ? diagram.getType() : null;
	}
	/**
	 * @generated
	 */
	public static int getVisualID(String type){
		try{
			return Integer.parseInt(type);
		}catch(NumberFormatException e){
			if(Boolean.TRUE.toString().equalsIgnoreCase(Platform.getDebugOption(DEBUG_KEY))){
				UimDiagramEditorPlugin.getInstance().logError("Unable to parse view type as a visualID number: " + type);
			}
		}
		return -1;
	}
	/**
	 * @generated
	 */
	public static String getType(int visualID){
		return Integer.toString(visualID);
	}
	/**
	 * @generated
	 */
	public static int getDiagramVisualID(EObject domainElement){
		if(domainElement == null){
			return -1;
		}
		if(UimPackage.eINSTANCE.getPage().isSuperTypeOf(domainElement.eClass()) && isDiagram((Page) domainElement)){
			return UserInterfaceEditPart.VISUAL_ID;
		}
		return -1;
	}
	/**
	 * @generated
	 */
	public static int getNodeVisualID(View containerView,EObject domainElement){
		if(domainElement == null){
			return -1;
		}
		String containerModelID = org.opaeum.uimodeler.userinterface.diagram.part.UimVisualIDRegistry.getModelID(containerView);
		if(!UserInterfaceEditPart.MODEL_ID.equals(containerModelID)){
			return -1;
		}
		int containerVisualID;
		if(UserInterfaceEditPart.MODEL_ID.equals(containerModelID)){
			containerVisualID = org.opaeum.uimodeler.userinterface.diagram.part.UimVisualIDRegistry.getVisualID(containerView);
		}else{
			if(containerView instanceof Diagram){
				containerVisualID = UserInterfaceEditPart.VISUAL_ID;
			}else{
				return -1;
			}
		}
		switch(containerVisualID){
		case UserInterfaceEditPart.VISUAL_ID:
			if(PanelPackage.eINSTANCE.getGridPanel().isSuperTypeOf(domainElement.eClass())){
				return GridPanelEditPart.VISUAL_ID;
			}
			if(PanelPackage.eINSTANCE.getHorizontalPanel().isSuperTypeOf(domainElement.eClass())){
				return HorizontalPanelEditPart.VISUAL_ID;
			}
			if(PanelPackage.eINSTANCE.getVerticalPanel().isSuperTypeOf(domainElement.eClass())){
				return VerticalPanelEditPart.VISUAL_ID;
			}
			break;
		case GridPanelGridPanelChildrenCompartmentEditPart.VISUAL_ID:
			if(ComponentPackage.eINSTANCE.getUimField().isSuperTypeOf(domainElement.eClass())){
				return UimFieldEditPart.VISUAL_ID;
			}
			if(ActionPackage.eINSTANCE.getBuiltInActionButton().isSuperTypeOf(domainElement.eClass())){
				return BuiltInActionButtonEditPart.VISUAL_ID;
			}
			if(PanelPackage.eINSTANCE.getHorizontalPanel().isSuperTypeOf(domainElement.eClass())){
				return HorizontalPanel2EditPart.VISUAL_ID;
			}
			if(PanelPackage.eINSTANCE.getVerticalPanel().isSuperTypeOf(domainElement.eClass())){
				return VerticalPanel2EditPart.VISUAL_ID;
			}
			if(ActionPackage.eINSTANCE.getTransitionButton().isSuperTypeOf(domainElement.eClass())){
				return TransitionButtonEditPart.VISUAL_ID;
			}
			if(ActionPackage.eINSTANCE.getInvocationButton().isSuperTypeOf(domainElement.eClass())){
				return InvocationButtonEditPart.VISUAL_ID;
			}
			if(ActionPackage.eINSTANCE.getLinkToQuery().isSuperTypeOf(domainElement.eClass())){
				return LinkToQueryEditPart.VISUAL_ID;
			}
			if(ActionPackage.eINSTANCE.getBuiltInLink().isSuperTypeOf(domainElement.eClass())){
				return BuiltInLinkEditPart.VISUAL_ID;
			}
			if(ComponentPackage.eINSTANCE.getUimDataTable().isSuperTypeOf(domainElement.eClass())){
				return UimDataTableEditPart.VISUAL_ID;
			}
			if(PanelPackage.eINSTANCE.getGridPanel().isSuperTypeOf(domainElement.eClass())){
				return GridPanel2EditPart.VISUAL_ID;
			}
			break;
		case UimDataTableDataTableColumnCompartmentEditPart.VISUAL_ID:
			if(ComponentPackage.eINSTANCE.getUimField().isSuperTypeOf(domainElement.eClass())){
				return UimField2EditPart.VISUAL_ID;
			}
			if(ActionPackage.eINSTANCE.getBuiltInActionButton().isSuperTypeOf(domainElement.eClass())){
				return BuiltInActionButton2EditPart.VISUAL_ID;
			}
			if(ActionPackage.eINSTANCE.getInvocationButton().isSuperTypeOf(domainElement.eClass())){
				return InvocationButton2EditPart.VISUAL_ID;
			}
			if(ActionPackage.eINSTANCE.getBuiltInLink().isSuperTypeOf(domainElement.eClass())){
				return BuiltInLink2EditPart.VISUAL_ID;
			}
			break;
		case UimDataTableTableTableActionBarCompartmentEditPart.VISUAL_ID:
			if(ActionPackage.eINSTANCE.getBuiltInActionButton().isSuperTypeOf(domainElement.eClass())){
				return BuiltInActionButton3EditPart.VISUAL_ID;
			}
			if(ActionPackage.eINSTANCE.getInvocationButton().isSuperTypeOf(domainElement.eClass())){
				return InvocationButton3EditPart.VISUAL_ID;
			}
			break;
		case GridPanelGridPanelChildrenCompartment2EditPart.VISUAL_ID:
			if(ComponentPackage.eINSTANCE.getUimField().isSuperTypeOf(domainElement.eClass())){
				return UimFieldEditPart.VISUAL_ID;
			}
			if(ActionPackage.eINSTANCE.getBuiltInActionButton().isSuperTypeOf(domainElement.eClass())){
				return BuiltInActionButtonEditPart.VISUAL_ID;
			}
			if(PanelPackage.eINSTANCE.getHorizontalPanel().isSuperTypeOf(domainElement.eClass())){
				return HorizontalPanel2EditPart.VISUAL_ID;
			}
			if(PanelPackage.eINSTANCE.getVerticalPanel().isSuperTypeOf(domainElement.eClass())){
				return VerticalPanel2EditPart.VISUAL_ID;
			}
			if(ActionPackage.eINSTANCE.getTransitionButton().isSuperTypeOf(domainElement.eClass())){
				return TransitionButtonEditPart.VISUAL_ID;
			}
			if(ActionPackage.eINSTANCE.getInvocationButton().isSuperTypeOf(domainElement.eClass())){
				return InvocationButtonEditPart.VISUAL_ID;
			}
			if(ActionPackage.eINSTANCE.getLinkToQuery().isSuperTypeOf(domainElement.eClass())){
				return LinkToQueryEditPart.VISUAL_ID;
			}
			if(ActionPackage.eINSTANCE.getBuiltInLink().isSuperTypeOf(domainElement.eClass())){
				return BuiltInLinkEditPart.VISUAL_ID;
			}
			if(ComponentPackage.eINSTANCE.getUimDataTable().isSuperTypeOf(domainElement.eClass())){
				return UimDataTableEditPart.VISUAL_ID;
			}
			if(PanelPackage.eINSTANCE.getGridPanel().isSuperTypeOf(domainElement.eClass())){
				return GridPanel2EditPart.VISUAL_ID;
			}
			break;
		}
		return -1;
	}
	/**
	 * @generated
	 */
	public static boolean canCreateNode(View containerView,int nodeVisualID){
		String containerModelID = org.opaeum.uimodeler.userinterface.diagram.part.UimVisualIDRegistry.getModelID(containerView);
		if(!UserInterfaceEditPart.MODEL_ID.equals(containerModelID)){
			return false;
		}
		int containerVisualID;
		if(UserInterfaceEditPart.MODEL_ID.equals(containerModelID)){
			containerVisualID = org.opaeum.uimodeler.userinterface.diagram.part.UimVisualIDRegistry.getVisualID(containerView);
		}else{
			if(containerView instanceof Diagram){
				containerVisualID = UserInterfaceEditPart.VISUAL_ID;
			}else{
				return false;
			}
		}
		switch(containerVisualID){
		case UserInterfaceEditPart.VISUAL_ID:
			if(GridPanelEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			if(HorizontalPanelEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			if(VerticalPanelEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			break;
		case GridPanelEditPart.VISUAL_ID:
			if(GridPanelNameEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			if(GridPanelGridPanelChildrenCompartmentEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			break;
		case TransitionButtonEditPart.VISUAL_ID:
			if(TransitionButtonNameEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			break;
		case InvocationButtonEditPart.VISUAL_ID:
			if(InvocationButtonNameEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			break;
		case LinkToQueryEditPart.VISUAL_ID:
			if(LinkToQueryNameEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			break;
		case BuiltInLinkEditPart.VISUAL_ID:
			if(BuiltInLinkNameEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			break;
		case UimDataTableEditPart.VISUAL_ID:
			if(UimDataTableDataTableColumnCompartmentEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			if(UimDataTableTableTableActionBarCompartmentEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			break;
		case InvocationButton3EditPart.VISUAL_ID:
			if(InvocationButtonName2EditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			break;
		case GridPanel2EditPart.VISUAL_ID:
			if(GridPanelName2EditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			if(GridPanelGridPanelChildrenCompartment2EditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			break;
		case GridPanelGridPanelChildrenCompartmentEditPart.VISUAL_ID:
			if(UimFieldEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			if(BuiltInActionButtonEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			if(HorizontalPanel2EditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			if(VerticalPanel2EditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			if(TransitionButtonEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			if(InvocationButtonEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			if(LinkToQueryEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			if(BuiltInLinkEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			if(UimDataTableEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			if(GridPanel2EditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			break;
		case UimDataTableDataTableColumnCompartmentEditPart.VISUAL_ID:
			if(UimField2EditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			if(BuiltInActionButton2EditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			if(InvocationButton2EditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			if(BuiltInLink2EditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			break;
		case UimDataTableTableTableActionBarCompartmentEditPart.VISUAL_ID:
			if(BuiltInActionButton3EditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			if(InvocationButton3EditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			break;
		case GridPanelGridPanelChildrenCompartment2EditPart.VISUAL_ID:
			if(UimFieldEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			if(BuiltInActionButtonEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			if(HorizontalPanel2EditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			if(VerticalPanel2EditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			if(TransitionButtonEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			if(InvocationButtonEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			if(LinkToQueryEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			if(BuiltInLinkEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			if(UimDataTableEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			if(GridPanel2EditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			break;
		}
		return false;
	}
	/**
	 * @generated
	 */
	public static int getLinkWithClassVisualID(EObject domainElement){
		if(domainElement == null){
			return -1;
		}
		return -1;
	}
	/**
	 * "User can change implementation of this method to handle some specific\n""situations not covered by default logic.\n"
	 * @generated
	 */
	private static boolean isDiagram(Page element){
		return true;
	}
	/**
	 * @generated
	 */
	private static ViewInfo diagramViewInfo = null;
	/**
	 * @generated
	 */
	public static ViewInfo getDiagramViewInfo(){
		if(diagramViewInfo == null){
			diagramViewInfo = getUserInterface_1000ViewInfo();
		}
		return diagramViewInfo;
	}
	/**
	 * @generated
	 */
	protected static ViewInfo getUserInterface_1000ViewInfo(){
		ViewInfo root = new BaseViewInfo(1000, ViewInfo.Head, "", null, null);
		ViewInfo viewInfo = null;
		ViewInfo labelInfo = null;
		viewInfo = new BaseViewInfo(2001, ViewInfo.Node, "GridPanel");
		root.addNode(1000, viewInfo);
		viewInfo = new BaseViewInfo(2002, ViewInfo.Node, "HorizontalPanel");
		root.addNode(1000, viewInfo);
		viewInfo = new BaseViewInfo(2003, ViewInfo.Node, "VerticalPanel");
		root.addNode(1000, viewInfo);
		viewInfo = new BaseViewInfo(3001, ViewInfo.Node, "UimField");
		root.addNode(7001, viewInfo);
		root.addNode(7005, viewInfo);
		viewInfo = new BaseViewInfo(3018, ViewInfo.Node, "BuiltInActionButton");
		root.addNode(7001, viewInfo);
		root.addNode(7005, viewInfo);
		viewInfo = new BaseViewInfo(3003, ViewInfo.Node, "HorizontalPanel");
		root.addNode(7001, viewInfo);
		root.addNode(7005, viewInfo);
		viewInfo = new BaseViewInfo(3004, ViewInfo.Node, "VerticalPanel");
		root.addNode(7001, viewInfo);
		root.addNode(7005, viewInfo);
		viewInfo = new BaseViewInfo(3019, ViewInfo.Node, "TransitionButton");
		root.addNode(7001, viewInfo);
		root.addNode(7005, viewInfo);
		viewInfo = new BaseViewInfo(3020, ViewInfo.Node, "InvocationButton");
		root.addNode(7001, viewInfo);
		root.addNode(7005, viewInfo);
		viewInfo = new BaseViewInfo(3021, ViewInfo.Node, "LinkToQuery");
		root.addNode(7001, viewInfo);
		root.addNode(7005, viewInfo);
		viewInfo = new BaseViewInfo(3022, ViewInfo.Node, "BuiltInLink");
		root.addNode(7001, viewInfo);
		root.addNode(7005, viewInfo);
		viewInfo = new BaseViewInfo(3009, ViewInfo.Node, "UimDataTable");
		root.addNode(7001, viewInfo);
		root.addNode(7005, viewInfo);
		viewInfo = new BaseViewInfo(3010, ViewInfo.Node, "UimField");
		root.addNode(7002, viewInfo);
		viewInfo = new BaseViewInfo(3023, ViewInfo.Node, "BuiltInActionButton");
		root.addNode(7002, viewInfo);
		viewInfo = new BaseViewInfo(3024, ViewInfo.Node, "InvocationButton");
		root.addNode(7002, viewInfo);
		viewInfo = new BaseViewInfo(3025, ViewInfo.Node, "BuiltInActionButton");
		root.addNode(7003, viewInfo);
		viewInfo = new BaseViewInfo(3026, ViewInfo.Node, "InvocationButton");
		root.addNode(7003, viewInfo);
		viewInfo = new BaseViewInfo(3027, ViewInfo.Node, "BuiltInLink");
		root.addNode(7002, viewInfo);
		viewInfo = new BaseViewInfo(3017, ViewInfo.Node, "GridPanel");
		root.addNode(7005, viewInfo);
		root.addNode(7001, viewInfo);
		return root;
	}
}
