package org.opaeum.uimodeler.userinterface.diagram.part;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.uml.diagram.common.providers.BaseViewInfo;
import org.eclipse.papyrus.uml.diagram.common.providers.ViewInfo;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.UserInterface;
import org.opaeum.uim.action.ActionPackage;
import org.opaeum.uim.panel.PanelPackage;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.BuiltInAction2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.BuiltInAction3EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.BuiltInActionEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.BuiltInActionName2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.BuiltInActionName3EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.BuiltInActionNameEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.GridPanel2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.GridPanelEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.GridPanelGridPanelChildrenCompartment2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.GridPanelGridPanelChildrenCompartmentEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.GridPanelName2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.GridPanelNameEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.HorizontalPanel2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.HorizontalPanelEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.LinkToEntity2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.LinkToEntityEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.LinkToEntityName2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.LinkToEntityNameEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.LinkToOperationEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.LinkToOperationNameEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.OperationAction2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.OperationAction3EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.OperationActionEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.OperationActionName2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.OperationActionName3EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.OperationActionNameEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.TransitionActionEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.TransitionActionNameEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UimDataTableDataTableColumnCompartmentEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UimDataTableEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UimDataTableTableTableActionBarCompartmentEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UimField2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UimFieldEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UimFieldName2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UimFieldNameEditPart;
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
		if(UimPackage.eINSTANCE.getUserInterface().isSuperTypeOf(domainElement.eClass()) && isDiagram((UserInterface) domainElement)){
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
			if(UimPackage.eINSTANCE.getUimField().isSuperTypeOf(domainElement.eClass())){
				return UimFieldEditPart.VISUAL_ID;
			}
			if(ActionPackage.eINSTANCE.getBuiltInAction().isSuperTypeOf(domainElement.eClass())){
				return BuiltInActionEditPart.VISUAL_ID;
			}
			if(PanelPackage.eINSTANCE.getHorizontalPanel().isSuperTypeOf(domainElement.eClass())){
				return HorizontalPanel2EditPart.VISUAL_ID;
			}
			if(PanelPackage.eINSTANCE.getVerticalPanel().isSuperTypeOf(domainElement.eClass())){
				return VerticalPanel2EditPart.VISUAL_ID;
			}
			if(ActionPackage.eINSTANCE.getTransitionAction().isSuperTypeOf(domainElement.eClass())){
				return TransitionActionEditPart.VISUAL_ID;
			}
			if(ActionPackage.eINSTANCE.getOperationAction().isSuperTypeOf(domainElement.eClass())){
				return OperationActionEditPart.VISUAL_ID;
			}
			if(ActionPackage.eINSTANCE.getLinkToOperation().isSuperTypeOf(domainElement.eClass())){
				return LinkToOperationEditPart.VISUAL_ID;
			}
			if(ActionPackage.eINSTANCE.getLinkToEntity().isSuperTypeOf(domainElement.eClass())){
				return LinkToEntityEditPart.VISUAL_ID;
			}
			if(UimPackage.eINSTANCE.getUimDataTable().isSuperTypeOf(domainElement.eClass())){
				return UimDataTableEditPart.VISUAL_ID;
			}
			if(PanelPackage.eINSTANCE.getGridPanel().isSuperTypeOf(domainElement.eClass())){
				return GridPanel2EditPart.VISUAL_ID;
			}
			break;
		case UimDataTableDataTableColumnCompartmentEditPart.VISUAL_ID:
			if(UimPackage.eINSTANCE.getUimField().isSuperTypeOf(domainElement.eClass())){
				return UimField2EditPart.VISUAL_ID;
			}
			if(ActionPackage.eINSTANCE.getLinkToEntity().isSuperTypeOf(domainElement.eClass())){
				return LinkToEntity2EditPart.VISUAL_ID;
			}
			if(ActionPackage.eINSTANCE.getBuiltInAction().isSuperTypeOf(domainElement.eClass())){
				return BuiltInAction2EditPart.VISUAL_ID;
			}
			if(ActionPackage.eINSTANCE.getOperationAction().isSuperTypeOf(domainElement.eClass())){
				return OperationAction2EditPart.VISUAL_ID;
			}
			break;
		case UimDataTableTableTableActionBarCompartmentEditPart.VISUAL_ID:
			if(ActionPackage.eINSTANCE.getBuiltInAction().isSuperTypeOf(domainElement.eClass())){
				return BuiltInAction3EditPart.VISUAL_ID;
			}
			if(ActionPackage.eINSTANCE.getOperationAction().isSuperTypeOf(domainElement.eClass())){
				return OperationAction3EditPart.VISUAL_ID;
			}
			break;
		case GridPanelGridPanelChildrenCompartment2EditPart.VISUAL_ID:
			if(UimPackage.eINSTANCE.getUimField().isSuperTypeOf(domainElement.eClass())){
				return UimFieldEditPart.VISUAL_ID;
			}
			if(ActionPackage.eINSTANCE.getBuiltInAction().isSuperTypeOf(domainElement.eClass())){
				return BuiltInActionEditPart.VISUAL_ID;
			}
			if(PanelPackage.eINSTANCE.getHorizontalPanel().isSuperTypeOf(domainElement.eClass())){
				return HorizontalPanel2EditPart.VISUAL_ID;
			}
			if(PanelPackage.eINSTANCE.getVerticalPanel().isSuperTypeOf(domainElement.eClass())){
				return VerticalPanel2EditPart.VISUAL_ID;
			}
			if(ActionPackage.eINSTANCE.getTransitionAction().isSuperTypeOf(domainElement.eClass())){
				return TransitionActionEditPart.VISUAL_ID;
			}
			if(ActionPackage.eINSTANCE.getOperationAction().isSuperTypeOf(domainElement.eClass())){
				return OperationActionEditPart.VISUAL_ID;
			}
			if(ActionPackage.eINSTANCE.getLinkToOperation().isSuperTypeOf(domainElement.eClass())){
				return LinkToOperationEditPart.VISUAL_ID;
			}
			if(ActionPackage.eINSTANCE.getLinkToEntity().isSuperTypeOf(domainElement.eClass())){
				return LinkToEntityEditPart.VISUAL_ID;
			}
			if(UimPackage.eINSTANCE.getUimDataTable().isSuperTypeOf(domainElement.eClass())){
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
		case TransitionActionEditPart.VISUAL_ID:
			if(TransitionActionNameEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			break;
		case LinkToOperationEditPart.VISUAL_ID:
			if(LinkToOperationNameEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			break;
		case LinkToEntityEditPart.VISUAL_ID:
			if(LinkToEntityNameEditPart.VISUAL_ID == nodeVisualID){
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
			if(BuiltInActionEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			if(HorizontalPanel2EditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			if(VerticalPanel2EditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			if(TransitionActionEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			if(OperationActionEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			if(LinkToOperationEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			if(LinkToEntityEditPart.VISUAL_ID == nodeVisualID){
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
			if(LinkToEntity2EditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			if(BuiltInAction2EditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			if(OperationAction2EditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			break;
		case UimDataTableTableTableActionBarCompartmentEditPart.VISUAL_ID:
			if(BuiltInAction3EditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			if(OperationAction3EditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			break;
		case GridPanelGridPanelChildrenCompartment2EditPart.VISUAL_ID:
			if(UimFieldEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			if(BuiltInActionEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			if(HorizontalPanel2EditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			if(VerticalPanel2EditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			if(TransitionActionEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			if(OperationActionEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			if(LinkToOperationEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			if(LinkToEntityEditPart.VISUAL_ID == nodeVisualID){
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
	private static boolean isDiagram(UserInterface element){
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
		viewInfo = new BaseViewInfo(3002, ViewInfo.Node, "BuiltInAction");
		root.addNode(7001, viewInfo);
		root.addNode(7005, viewInfo);
		viewInfo = new BaseViewInfo(3003, ViewInfo.Node, "HorizontalPanel");
		root.addNode(7001, viewInfo);
		root.addNode(7005, viewInfo);
		viewInfo = new BaseViewInfo(3004, ViewInfo.Node, "VerticalPanel");
		root.addNode(7001, viewInfo);
		root.addNode(7005, viewInfo);
		viewInfo = new BaseViewInfo(3005, ViewInfo.Node, "TransitionAction");
		root.addNode(7001, viewInfo);
		root.addNode(7005, viewInfo);
		viewInfo = new BaseViewInfo(3006, ViewInfo.Node, "OperationAction");
		root.addNode(7001, viewInfo);
		root.addNode(7005, viewInfo);
		viewInfo = new BaseViewInfo(3007, ViewInfo.Node, "LinkToOperation");
		root.addNode(7001, viewInfo);
		root.addNode(7005, viewInfo);
		viewInfo = new BaseViewInfo(3008, ViewInfo.Node, "LinkToEntity");
		root.addNode(7001, viewInfo);
		root.addNode(7005, viewInfo);
		viewInfo = new BaseViewInfo(3009, ViewInfo.Node, "UimDataTable");
		root.addNode(7001, viewInfo);
		root.addNode(7005, viewInfo);
		viewInfo = new BaseViewInfo(3010, ViewInfo.Node, "UimField");
		root.addNode(7002, viewInfo);
		viewInfo = new BaseViewInfo(3011, ViewInfo.Node, "LinkToEntity");
		root.addNode(7002, viewInfo);
		viewInfo = new BaseViewInfo(3012, ViewInfo.Node, "BuiltInAction");
		root.addNode(7002, viewInfo);
		viewInfo = new BaseViewInfo(3014, ViewInfo.Node, "OperationAction");
		root.addNode(7002, viewInfo);
		viewInfo = new BaseViewInfo(3015, ViewInfo.Node, "BuiltInAction");
		root.addNode(7003, viewInfo);
		viewInfo = new BaseViewInfo(3016, ViewInfo.Node, "OperationAction");
		root.addNode(7003, viewInfo);
		viewInfo = new BaseViewInfo(3017, ViewInfo.Node, "GridPanel");
		root.addNode(7005, viewInfo);
		root.addNode(7001, viewInfo);
		return root;
	}
}
