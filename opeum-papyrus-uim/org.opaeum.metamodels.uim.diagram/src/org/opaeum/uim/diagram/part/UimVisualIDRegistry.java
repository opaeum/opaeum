package org.opaeum.uim.diagram.part;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.uml.diagram.common.providers.BaseViewInfo;
import org.eclipse.papyrus.uml.diagram.common.providers.ViewInfo;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.action.ActionPackage;
import org.opaeum.uim.diagram.edit.parts.BuiltInActionEditPart;
import org.opaeum.uim.diagram.edit.parts.BuiltInActionNameKindEditPart;
import org.opaeum.uim.diagram.edit.parts.EditorPageEditPart;
import org.opaeum.uim.diagram.edit.parts.GridPanelEditPart;
import org.opaeum.uim.diagram.edit.parts.GridPanelGridPanelChildrenCompartmentEditPart;
import org.opaeum.uim.diagram.edit.parts.GridPanelNameEditPart;
import org.opaeum.uim.diagram.edit.parts.HorizontalPanelEditPart;
import org.opaeum.uim.diagram.edit.parts.UimFieldEditPart;
import org.opaeum.uim.diagram.edit.parts.UimFieldNameEditPart;
import org.opaeum.uim.diagram.edit.parts.VerticalPanelEditPart;
import org.opaeum.uim.editor.EditorPackage;
import org.opaeum.uim.editor.EditorPage;
import org.opaeum.uim.panel.PanelPackage;

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
	private static final String DEBUG_KEY = "org.opaeum.metamodels.uim.diagram/debug/visualID"; //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static int getVisualID(View view){
		if(view instanceof Diagram){
			if(EditorPageEditPart.MODEL_ID.equals(view.getType())){
				return EditorPageEditPart.VISUAL_ID;
			}else{
				return -1;
			}
		}
		return org.opaeum.uim.diagram.part.UimVisualIDRegistry.getVisualID(view.getType());
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
		if(EditorPackage.eINSTANCE.getEditorPage().isSuperTypeOf(domainElement.eClass()) && isDiagram((EditorPage) domainElement)){
			return EditorPageEditPart.VISUAL_ID;
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
		String containerModelID = org.opaeum.uim.diagram.part.UimVisualIDRegistry.getModelID(containerView);
		if(!EditorPageEditPart.MODEL_ID.equals(containerModelID)){
			return -1;
		}
		int containerVisualID;
		if(EditorPageEditPart.MODEL_ID.equals(containerModelID)){
			containerVisualID = org.opaeum.uim.diagram.part.UimVisualIDRegistry.getVisualID(containerView);
		}else{
			if(containerView instanceof Diagram){
				containerVisualID = EditorPageEditPart.VISUAL_ID;
			}else{
				return -1;
			}
		}
		switch(containerVisualID){
		case EditorPageEditPart.VISUAL_ID:
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
			break;
		}
		return -1;
	}
	/**
	 * @generated
	 */
	public static boolean canCreateNode(View containerView,int nodeVisualID){
		String containerModelID = org.opaeum.uim.diagram.part.UimVisualIDRegistry.getModelID(containerView);
		if(!EditorPageEditPart.MODEL_ID.equals(containerModelID)){
			return false;
		}
		int containerVisualID;
		if(EditorPageEditPart.MODEL_ID.equals(containerModelID)){
			containerVisualID = org.opaeum.uim.diagram.part.UimVisualIDRegistry.getVisualID(containerView);
		}else{
			if(containerView instanceof Diagram){
				containerVisualID = EditorPageEditPart.VISUAL_ID;
			}else{
				return false;
			}
		}
		switch(containerVisualID){
		case EditorPageEditPart.VISUAL_ID:
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
		case UimFieldEditPart.VISUAL_ID:
			if(UimFieldNameEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			break;
		case BuiltInActionEditPart.VISUAL_ID:
			if(BuiltInActionNameKindEditPart.VISUAL_ID == nodeVisualID){
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
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static boolean isDiagram(EditorPage element){
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
			diagramViewInfo = getEditorPage_1000ViewInfo();
		}
		return diagramViewInfo;
	}
	/**
	 * @generated
	 */
	protected static ViewInfo getEditorPage_1000ViewInfo(){
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
		viewInfo = new BaseViewInfo(3002, ViewInfo.Node, "BuiltInAction");
		root.addNode(7001, viewInfo);
		return root;
	}
}
