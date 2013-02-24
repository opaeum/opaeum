package org.opaeum.uimodeler.perspective.diagram.part;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.gmf.runtime.notation.View;
import org.opaeum.uim.perspective.EditorConfiguration;
import org.opaeum.uim.perspective.NavigatorConfiguration;
import org.opaeum.uim.perspective.PerspectiveConfiguration;
import org.opaeum.uim.perspective.PropertiesConfiguration;
import org.opaeum.uimodeler.perspective.diagram.edit.parts.EditorConfigurationEditPart;
import org.opaeum.uimodeler.perspective.diagram.edit.parts.ExplorerConfigurationEditPart;
import org.opaeum.uimodeler.perspective.diagram.edit.parts.PerspectiveConfigurationEditPart;
import org.opaeum.uimodeler.perspective.diagram.edit.parts.PropertiesConfigurationEditPart;

/**
 * @generated
 */
public class UimDiagramUpdater{
	/**
	 * @generated
	 */
	public static List<UimNodeDescriptor> getSemanticChildren(View view){
		switch(UimVisualIDRegistry.getVisualID(view)){
		case PerspectiveConfigurationEditPart.VISUAL_ID:
			return getPerspectiveConfiguration_1000SemanticChildren(view);
		}
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimNodeDescriptor> getPerspectiveConfiguration_1000SemanticChildren(View view){
		if(!view.isSetElement()){
			return Collections.EMPTY_LIST;
		}
		PerspectiveConfiguration modelElement = (PerspectiveConfiguration) view.getElement();
		LinkedList<UimNodeDescriptor> result = new LinkedList<UimNodeDescriptor>();
		{
			EditorConfiguration childElement = modelElement.getEditor();
			int visualID = UimVisualIDRegistry.getNodeVisualID(view, childElement);
			if(visualID == EditorConfigurationEditPart.VISUAL_ID){
				result.add(new UimNodeDescriptor(childElement, visualID));
			}
		}
		{
			PropertiesConfiguration childElement = modelElement.getProperties();
			int visualID = UimVisualIDRegistry.getNodeVisualID(view, childElement);
			if(visualID == PropertiesConfigurationEditPart.VISUAL_ID){
				result.add(new UimNodeDescriptor(childElement, visualID));
			}
		}
		{
			NavigatorConfiguration childElement = modelElement.getExplorer();
			int visualID = UimVisualIDRegistry.getNodeVisualID(view, childElement);
			if(visualID == ExplorerConfigurationEditPart.VISUAL_ID){
				result.add(new UimNodeDescriptor(childElement, visualID));
			}
		}
		return result;
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getContainedLinks(View view){
		switch(UimVisualIDRegistry.getVisualID(view)){
		case PerspectiveConfigurationEditPart.VISUAL_ID:
			return getPerspectiveConfiguration_1000ContainedLinks(view);
		case EditorConfigurationEditPart.VISUAL_ID:
			return getEditorConfiguration_2001ContainedLinks(view);
		case PropertiesConfigurationEditPart.VISUAL_ID:
			return getPropertiesConfiguration_2002ContainedLinks(view);
		case ExplorerConfigurationEditPart.VISUAL_ID:
			return getExplorerConfiguration_2003ContainedLinks(view);
		}
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getIncomingLinks(View view){
		switch(UimVisualIDRegistry.getVisualID(view)){
		case EditorConfigurationEditPart.VISUAL_ID:
			return getEditorConfiguration_2001IncomingLinks(view);
		case PropertiesConfigurationEditPart.VISUAL_ID:
			return getPropertiesConfiguration_2002IncomingLinks(view);
		case ExplorerConfigurationEditPart.VISUAL_ID:
			return getExplorerConfiguration_2003IncomingLinks(view);
		}
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getOutgoingLinks(View view){
		switch(UimVisualIDRegistry.getVisualID(view)){
		case EditorConfigurationEditPart.VISUAL_ID:
			return getEditorConfiguration_2001OutgoingLinks(view);
		case PropertiesConfigurationEditPart.VISUAL_ID:
			return getPropertiesConfiguration_2002OutgoingLinks(view);
		case ExplorerConfigurationEditPart.VISUAL_ID:
			return getExplorerConfiguration_2003OutgoingLinks(view);
		}
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getPerspectiveConfiguration_1000ContainedLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getEditorConfiguration_2001ContainedLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getPropertiesConfiguration_2002ContainedLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getExplorerConfiguration_2003ContainedLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getEditorConfiguration_2001IncomingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getPropertiesConfiguration_2002IncomingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getExplorerConfiguration_2003IncomingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getEditorConfiguration_2001OutgoingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getPropertiesConfiguration_2002OutgoingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getExplorerConfiguration_2003OutgoingLinks(View view){
		return Collections.emptyList();
	}
}
