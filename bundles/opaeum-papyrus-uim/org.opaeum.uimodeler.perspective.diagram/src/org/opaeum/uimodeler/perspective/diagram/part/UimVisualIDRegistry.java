package org.opaeum.uimodeler.perspective.diagram.part;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.uml.diagram.common.providers.BaseViewInfo;
import org.eclipse.papyrus.uml.diagram.common.providers.ViewInfo;
import org.opaeum.uim.perspective.PerspectiveConfiguration;
import org.opaeum.uim.perspective.PerspectivePackage;
import org.opaeum.uimodeler.perspective.diagram.edit.parts.EditorConfigurationEditPart;
import org.opaeum.uimodeler.perspective.diagram.edit.parts.ExplorerConfigurationEditPart;
import org.opaeum.uimodeler.perspective.diagram.edit.parts.PerspectiveConfigurationEditPart;
import org.opaeum.uimodeler.perspective.diagram.edit.parts.PropertiesConfigurationEditPart;

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
	private static final String DEBUG_KEY = "org.opaeum.uimodeler.perspective.diagram/debug/visualID"; //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static int getVisualID(View view){
		if(view instanceof Diagram){
			if(PerspectiveConfigurationEditPart.MODEL_ID.equals(view.getType())){
				return PerspectiveConfigurationEditPart.VISUAL_ID;
			}else{
				return -1;
			}
		}
		return org.opaeum.uimodeler.perspective.diagram.part.UimVisualIDRegistry.getVisualID(view.getType());
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
				PerspectiveConfigurationDiagramEditorPlugin.getInstance().logError("Unable to parse view type as a visualID number: " + type);
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
		if(PerspectivePackage.eINSTANCE.getPerspectiveConfiguration().isSuperTypeOf(domainElement.eClass()) && isDiagram((PerspectiveConfiguration) domainElement)){
			return PerspectiveConfigurationEditPart.VISUAL_ID;
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
		String containerModelID = org.opaeum.uimodeler.perspective.diagram.part.UimVisualIDRegistry.getModelID(containerView);
		if(!PerspectiveConfigurationEditPart.MODEL_ID.equals(containerModelID)){
			return -1;
		}
		int containerVisualID;
		if(PerspectiveConfigurationEditPart.MODEL_ID.equals(containerModelID)){
			containerVisualID = org.opaeum.uimodeler.perspective.diagram.part.UimVisualIDRegistry.getVisualID(containerView);
		}else{
			if(containerView instanceof Diagram){
				containerVisualID = PerspectiveConfigurationEditPart.VISUAL_ID;
			}else{
				return -1;
			}
		}
		switch(containerVisualID){
		case PerspectiveConfigurationEditPart.VISUAL_ID:
			if(PerspectivePackage.eINSTANCE.getEditorConfiguration().isSuperTypeOf(domainElement.eClass())){
				return EditorConfigurationEditPart.VISUAL_ID;
			}
			if(PerspectivePackage.eINSTANCE.getPropertiesConfiguration().isSuperTypeOf(domainElement.eClass())){
				return PropertiesConfigurationEditPart.VISUAL_ID;
			}
			if(PerspectivePackage.eINSTANCE.getNavigatorConfiguration().isSuperTypeOf(domainElement.eClass())){
				return ExplorerConfigurationEditPart.VISUAL_ID;
			}
			break;
		}
		return -1;
	}
	/**
	 * @generated
	 */
	public static boolean canCreateNode(View containerView,int nodeVisualID){
		String containerModelID = org.opaeum.uimodeler.perspective.diagram.part.UimVisualIDRegistry.getModelID(containerView);
		if(!PerspectiveConfigurationEditPart.MODEL_ID.equals(containerModelID)){
			return false;
		}
		int containerVisualID;
		if(PerspectiveConfigurationEditPart.MODEL_ID.equals(containerModelID)){
			containerVisualID = org.opaeum.uimodeler.perspective.diagram.part.UimVisualIDRegistry.getVisualID(containerView);
		}else{
			if(containerView instanceof Diagram){
				containerVisualID = PerspectiveConfigurationEditPart.VISUAL_ID;
			}else{
				return false;
			}
		}
		switch(containerVisualID){
		case PerspectiveConfigurationEditPart.VISUAL_ID:
			if(EditorConfigurationEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			if(PropertiesConfigurationEditPart.VISUAL_ID == nodeVisualID){
				return true;
			}
			if(ExplorerConfigurationEditPart.VISUAL_ID == nodeVisualID){
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
	private static boolean isDiagram(PerspectiveConfiguration element){
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
			diagramViewInfo = getPerspectiveConfiguration_1000ViewInfo();
		}
		return diagramViewInfo;
	}
	/**
	 * @generated
	 */
	protected static ViewInfo getPerspectiveConfiguration_1000ViewInfo(){
		ViewInfo root = new BaseViewInfo(1000, ViewInfo.Head, "", null, null);
		ViewInfo viewInfo = null;
		ViewInfo labelInfo = null;
		viewInfo = new BaseViewInfo(2001, ViewInfo.Node, "EditorConfiguration");
		root.addNode(1000, viewInfo);
		viewInfo = new BaseViewInfo(2002, ViewInfo.Node, "PropertiesConfiguration");
		root.addNode(1000, viewInfo);
		viewInfo = new BaseViewInfo(2003, ViewInfo.Node, "ExplorerConfiguration");
		root.addNode(1000, viewInfo);
		return root;
	}
}
