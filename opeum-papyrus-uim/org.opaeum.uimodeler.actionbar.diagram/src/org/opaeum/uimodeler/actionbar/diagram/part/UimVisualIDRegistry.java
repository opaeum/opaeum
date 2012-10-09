package org.opaeum.uimodeler.actionbar.diagram.part;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.uml.diagram.common.providers.BaseViewInfo;
import org.eclipse.papyrus.uml.diagram.common.providers.ViewInfo;
import org.opaeum.uim.action.ActionPackage;
import org.opaeum.uim.editor.AbstractEditor;
import org.opaeum.uim.editor.EditorPackage;
import org.opaeum.uimodeler.actionbar.diagram.edit.parts.AbstractEditorEditPart;
import org.opaeum.uimodeler.actionbar.diagram.edit.parts.ActionBarActionBarChildrenCompartmentEditPart;
import org.opaeum.uimodeler.actionbar.diagram.edit.parts.ActionBarEditPart;
import org.opaeum.uimodeler.actionbar.diagram.edit.parts.ActionBarNameEditPart;
import org.opaeum.uimodeler.actionbar.diagram.edit.parts.BuiltInActionButtonEditPart;
import org.opaeum.uimodeler.actionbar.diagram.edit.parts.BuiltInActionButtonNameEditPart;
import org.opaeum.uimodeler.actionbar.diagram.edit.parts.BuiltInLinkEditPart;
import org.opaeum.uimodeler.actionbar.diagram.edit.parts.BuiltInLinkNameEditPart;
import org.opaeum.uimodeler.actionbar.diagram.edit.parts.InvocationButtonEditPart;
import org.opaeum.uimodeler.actionbar.diagram.edit.parts.InvocationButtonNameEditPart;
import org.opaeum.uimodeler.actionbar.diagram.edit.parts.LinkToQueryEditPart;
import org.opaeum.uimodeler.actionbar.diagram.edit.parts.LinkToQueryNameEditPart;
import org.opaeum.uimodeler.actionbar.diagram.edit.parts.TransitionButtonEditPart;
import org.opaeum.uimodeler.actionbar.diagram.edit.parts.TransitionButtonNameEditPart;

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
	private static final String DEBUG_KEY = "org.opaeum.uimodeler.actionbar.diagram/debug/visualID"; //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static int getVisualID(View view){
		if(view instanceof Diagram){
			if(AbstractEditorEditPart.MODEL_ID.equals(view.getType())){
				return AbstractEditorEditPart.VISUAL_ID;
			}else{
				return -1;
			}
		}
		return org.opaeum.uimodeler.actionbar.diagram.part.UimVisualIDRegistry.getVisualID(view.getType());
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
		if(EditorPackage.eINSTANCE.getAbstractEditor().isSuperTypeOf(domainElement.eClass()) && isDiagram((AbstractEditor) domainElement)){
			return AbstractEditorEditPart.VISUAL_ID;
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
		String containerModelID = org.opaeum.uimodeler.actionbar.diagram.part.UimVisualIDRegistry.getModelID(containerView);
		if(!AbstractEditorEditPart.MODEL_ID.equals(containerModelID)){
			return -1;
		}
		int containerVisualID;
		if(AbstractEditorEditPart.MODEL_ID.equals(containerModelID)){
			containerVisualID = org.opaeum.uimodeler.actionbar.diagram.part.UimVisualIDRegistry.getVisualID(containerView);
		}else{
			if(containerView instanceof Diagram){
				containerVisualID = AbstractEditorEditPart.VISUAL_ID;
			}else{
				return -1;
			}
		}
		switch(containerVisualID){
		case AbstractEditorEditPart.VISUAL_ID:
			if(EditorPackage.eINSTANCE.getActionBar().isSuperTypeOf(domainElement.eClass())){
				return ActionBarEditPart.VISUAL_ID;
			}
			break;
		case ActionBarActionBarChildrenCompartmentEditPart.VISUAL_ID:
			if(ActionPackage.eINSTANCE.getBuiltInLink().isSuperTypeOf(domainElement.eClass())){
				return BuiltInLinkEditPart.VISUAL_ID;
			}
			if(ActionPackage.eINSTANCE.getLinkToQuery().isSuperTypeOf(domainElement.eClass())){
				return LinkToQueryEditPart.VISUAL_ID;
			}
			if(ActionPackage.eINSTANCE.getInvocationButton().isSuperTypeOf(domainElement.eClass())){
				return InvocationButtonEditPart.VISUAL_ID;
			}
			if(ActionPackage.eINSTANCE.getBuiltInActionButton().isSuperTypeOf(domainElement.eClass())){
				return BuiltInActionButtonEditPart.VISUAL_ID;
			}
			if(ActionPackage.eINSTANCE.getTransitionButton().isSuperTypeOf(domainElement.eClass())){
				return TransitionButtonEditPart.VISUAL_ID;
			}
			break;
		}
		return -1;
	}
	/**
	 * @generated
	 */
	public static boolean canCreateNode(View containerView,int nodeVisualID){
		String containerModelID = org.opaeum.uimodeler.actionbar.diagram.part.UimVisualIDRegistry.getModelID(containerView);
		if(!AbstractEditorEditPart.MODEL_ID.equals(containerModelID)){
			return false;
		}
		int containerVisualID;
		if(AbstractEditorEditPart.MODEL_ID.equals(containerModelID)){
			containerVisualID = org.opaeum.uimodeler.actionbar.diagram.part.UimVisualIDRegistry.getVisualID(containerView);
		}else{
			if(containerView instanceof Diagram){
				containerVisualID = AbstractEditorEditPart.VISUAL_ID;
			}else{
				return false;
			}
		}
		switch(containerVisualID){
		case AbstractEditorEditPart.VISUAL_ID:
			if(ActionBarEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			break;
		case ActionBarEditPart.VISUAL_ID:
			if(ActionBarNameEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			if(ActionBarActionBarChildrenCompartmentEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			break;
		case BuiltInLinkEditPart.VISUAL_ID:
			if(BuiltInLinkNameEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			break;
		case LinkToQueryEditPart.VISUAL_ID:
			if(LinkToQueryNameEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			break;
		case InvocationButtonEditPart.VISUAL_ID:
			if(InvocationButtonNameEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			break;
		case BuiltInActionButtonEditPart.VISUAL_ID:
			if(BuiltInActionButtonNameEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			break;
		case TransitionButtonEditPart.VISUAL_ID:
			if(TransitionButtonNameEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			break;
		case ActionBarActionBarChildrenCompartmentEditPart.VISUAL_ID:
			if(BuiltInLinkEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			if(LinkToQueryEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			if(InvocationButtonEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			if(BuiltInActionButtonEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			if(TransitionButtonEditPart.VISUAL_ID == nodeVisualID){
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
	private static boolean isDiagram(AbstractEditor element){
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
			diagramViewInfo = getAbstractEditor_1000ViewInfo();
		}
		return diagramViewInfo;
	}
	/**
	 * @generated
	 */
	protected static ViewInfo getAbstractEditor_1000ViewInfo(){
		ViewInfo root = new BaseViewInfo(1000, ViewInfo.Head, "", null, null);
		ViewInfo viewInfo = null;
		ViewInfo labelInfo = null;
		viewInfo = new BaseViewInfo(2011, ViewInfo.Node, "ActionBar");
		root.addNode(1000, viewInfo);
		viewInfo = new BaseViewInfo(3001, ViewInfo.Node, "BuiltInLink");
		root.addNode(7001, viewInfo);
		viewInfo = new BaseViewInfo(3002, ViewInfo.Node, "LinkToQuery");
		root.addNode(7001, viewInfo);
		viewInfo = new BaseViewInfo(3003, ViewInfo.Node, "InvocationButton");
		root.addNode(7001, viewInfo);
		viewInfo = new BaseViewInfo(3004, ViewInfo.Node, "BuiltInActionButton");
		root.addNode(7001, viewInfo);
		viewInfo = new BaseViewInfo(3005, ViewInfo.Node, "TransitionButton");
		root.addNode(7001, viewInfo);
		return root;
	}
}
