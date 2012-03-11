package org.opaeum.uimodeler.abstractactionbar.diagram.part;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.uml.diagram.common.providers.BaseViewInfo;
import org.eclipse.papyrus.uml.diagram.common.providers.ViewInfo;
import org.opaeum.uim.AbstractActionBar;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.action.ActionPackage;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.AbstractActionBarEditPart;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.BuiltInActionEditPart;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.BuiltInActionNameEditPart;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.OperationActionEditPart;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.OperationActionNameEditPart;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.TransitionActionEditPart;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.TransitionActionNameEditPart;

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
	private static final String DEBUG_KEY = "org.opaeum.uimodeler.abstractactionbar.diagram/debug/visualID"; //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static int getVisualID(View view){
		if(view instanceof Diagram){
			if(AbstractActionBarEditPart.MODEL_ID.equals(view.getType())){
				return AbstractActionBarEditPart.VISUAL_ID;
			}else{
				return -1;
			}
		}
		return org.opaeum.uimodeler.abstractactionbar.diagram.part.UimVisualIDRegistry.getVisualID(view.getType());
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
		if(UimPackage.eINSTANCE.getAbstractActionBar().isSuperTypeOf(domainElement.eClass()) && isDiagram((AbstractActionBar) domainElement)){
			return AbstractActionBarEditPart.VISUAL_ID;
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
		String containerModelID = org.opaeum.uimodeler.abstractactionbar.diagram.part.UimVisualIDRegistry.getModelID(containerView);
		if(!AbstractActionBarEditPart.MODEL_ID.equals(containerModelID)){
			return -1;
		}
		int containerVisualID;
		if(AbstractActionBarEditPart.MODEL_ID.equals(containerModelID)){
			containerVisualID = org.opaeum.uimodeler.abstractactionbar.diagram.part.UimVisualIDRegistry.getVisualID(containerView);
		}else{
			if(containerView instanceof Diagram){
				containerVisualID = AbstractActionBarEditPart.VISUAL_ID;
			}else{
				return -1;
			}
		}
		switch(containerVisualID){
		case AbstractActionBarEditPart.VISUAL_ID:
			if(ActionPackage.eINSTANCE.getBuiltInAction().isSuperTypeOf(domainElement.eClass())){
				return BuiltInActionEditPart.VISUAL_ID;
			}
			if(ActionPackage.eINSTANCE.getTransitionAction().isSuperTypeOf(domainElement.eClass())){
				return TransitionActionEditPart.VISUAL_ID;
			}
			if(ActionPackage.eINSTANCE.getOperationAction().isSuperTypeOf(domainElement.eClass())){
				return OperationActionEditPart.VISUAL_ID;
			}
			break;
		}
		return -1;
	}
	/**
	 * @generated
	 */
	public static boolean canCreateNode(View containerView,int nodeVisualID){
		String containerModelID = org.opaeum.uimodeler.abstractactionbar.diagram.part.UimVisualIDRegistry.getModelID(containerView);
		if(!AbstractActionBarEditPart.MODEL_ID.equals(containerModelID)){
			return false;
		}
		int containerVisualID;
		if(AbstractActionBarEditPart.MODEL_ID.equals(containerModelID)){
			containerVisualID = org.opaeum.uimodeler.abstractactionbar.diagram.part.UimVisualIDRegistry.getVisualID(containerView);
		}else{
			if(containerView instanceof Diagram){
				containerVisualID = AbstractActionBarEditPart.VISUAL_ID;
			}else{
				return false;
			}
		}
		switch(containerVisualID){
		case AbstractActionBarEditPart.VISUAL_ID:
			if(BuiltInActionEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			if(TransitionActionEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			if(OperationActionEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			break;
		case BuiltInActionEditPart.VISUAL_ID:
			if(BuiltInActionNameEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			break;
		case TransitionActionEditPart.VISUAL_ID:
			if(TransitionActionNameEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			break;
		case OperationActionEditPart.VISUAL_ID:
			if(OperationActionNameEditPart.VISUAL_ID == nodeVisualID){
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
	private static boolean isDiagram(AbstractActionBar element){
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
			diagramViewInfo = getAbstractActionBar_1000ViewInfo();
		}
		return diagramViewInfo;
	}
	/**
	 * @generated
	 */
	protected static ViewInfo getAbstractActionBar_1000ViewInfo(){
		ViewInfo root = new BaseViewInfo(1000, ViewInfo.Head, "", null, null);
		ViewInfo viewInfo = null;
		ViewInfo labelInfo = null;
		viewInfo = new BaseViewInfo(2001, ViewInfo.Node, "BuiltInAction");
		root.addNode(1000, viewInfo);
		viewInfo = new BaseViewInfo(2002, ViewInfo.Node, "TransitionAction");
		root.addNode(1000, viewInfo);
		viewInfo = new BaseViewInfo(2003, ViewInfo.Node, "OperationAction");
		root.addNode(1000, viewInfo);
		return root;
	}
}
