package org.opaeum.uim.diagram.part;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.gmf.runtime.notation.View;
import org.opaeum.uim.UimComponent;
import org.opaeum.uim.diagram.edit.parts.BuiltInActionEditPart;
import org.opaeum.uim.diagram.edit.parts.EditorPageEditPart;
import org.opaeum.uim.diagram.edit.parts.GridPanelEditPart;
import org.opaeum.uim.diagram.edit.parts.GridPanelGridPanelChildrenCompartmentEditPart;
import org.opaeum.uim.diagram.edit.parts.HorizontalPanelEditPart;
import org.opaeum.uim.diagram.edit.parts.UimFieldEditPart;
import org.opaeum.uim.diagram.edit.parts.VerticalPanelEditPart;
import org.opaeum.uim.editor.EditorPage;
import org.opaeum.uim.panel.AbstractPanel;
import org.opaeum.uim.panel.GridPanel;

/**
 * @generated
 */
public class UimDiagramUpdater{
	/**
	 * @generated
	 */
	public static List<UimNodeDescriptor> getSemanticChildren(View view){
		switch(UimVisualIDRegistry.getVisualID(view)){
		case EditorPageEditPart.VISUAL_ID:
			return getEditorPage_1000SemanticChildren(view);
		case GridPanelGridPanelChildrenCompartmentEditPart.VISUAL_ID:
			return getGridPanelGridPanelChildrenCompartment_7001SemanticChildren(view);
		}
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimNodeDescriptor> getEditorPage_1000SemanticChildren(View view){
		if(!view.isSetElement()){
			return Collections.EMPTY_LIST;
		}
		EditorPage modelElement = (EditorPage) view.getElement();
		LinkedList<UimNodeDescriptor> result = new LinkedList<UimNodeDescriptor>();
		{
			AbstractPanel childElement = modelElement.getPanel();
			int visualID = UimVisualIDRegistry.getNodeVisualID(view, childElement);
			if(visualID == GridPanelEditPart.VISUAL_ID){
				result.add(new UimNodeDescriptor(childElement, visualID));
			}
			if(visualID == HorizontalPanelEditPart.VISUAL_ID){
				result.add(new UimNodeDescriptor(childElement, visualID));
			}
			if(visualID == VerticalPanelEditPart.VISUAL_ID){
				result.add(new UimNodeDescriptor(childElement, visualID));
			}
		}
		return result;
	}
	/**
	 * @generated
	 */
	public static List<UimNodeDescriptor> getGridPanelGridPanelChildrenCompartment_7001SemanticChildren(View view){
		if(false == view.eContainer() instanceof View){
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if(!containerView.isSetElement()){
			return Collections.emptyList();
		}
		GridPanel modelElement = (GridPanel) containerView.getElement();
		LinkedList<UimNodeDescriptor> result = new LinkedList<UimNodeDescriptor>();
		for(Iterator<?> it = modelElement.getChildren().iterator();it.hasNext();){
			UimComponent childElement = (UimComponent) it.next();
			int visualID = UimVisualIDRegistry.getNodeVisualID(view, childElement);
			if(visualID == UimFieldEditPart.VISUAL_ID){
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if(visualID == BuiltInActionEditPart.VISUAL_ID){
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getContainedLinks(View view){
		switch(UimVisualIDRegistry.getVisualID(view)){
		case EditorPageEditPart.VISUAL_ID:
			return getEditorPage_1000ContainedLinks(view);
		case GridPanelEditPart.VISUAL_ID:
			return getGridPanel_2001ContainedLinks(view);
		case HorizontalPanelEditPart.VISUAL_ID:
			return getHorizontalPanel_2002ContainedLinks(view);
		case VerticalPanelEditPart.VISUAL_ID:
			return getVerticalPanel_2003ContainedLinks(view);
		case UimFieldEditPart.VISUAL_ID:
			return getUimField_3001ContainedLinks(view);
		case BuiltInActionEditPart.VISUAL_ID:
			return getBuiltInAction_3002ContainedLinks(view);
		}
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getIncomingLinks(View view){
		switch(UimVisualIDRegistry.getVisualID(view)){
		case GridPanelEditPart.VISUAL_ID:
			return getGridPanel_2001IncomingLinks(view);
		case HorizontalPanelEditPart.VISUAL_ID:
			return getHorizontalPanel_2002IncomingLinks(view);
		case VerticalPanelEditPart.VISUAL_ID:
			return getVerticalPanel_2003IncomingLinks(view);
		case UimFieldEditPart.VISUAL_ID:
			return getUimField_3001IncomingLinks(view);
		case BuiltInActionEditPart.VISUAL_ID:
			return getBuiltInAction_3002IncomingLinks(view);
		}
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getOutgoingLinks(View view){
		switch(UimVisualIDRegistry.getVisualID(view)){
		case GridPanelEditPart.VISUAL_ID:
			return getGridPanel_2001OutgoingLinks(view);
		case HorizontalPanelEditPart.VISUAL_ID:
			return getHorizontalPanel_2002OutgoingLinks(view);
		case VerticalPanelEditPart.VISUAL_ID:
			return getVerticalPanel_2003OutgoingLinks(view);
		case UimFieldEditPart.VISUAL_ID:
			return getUimField_3001OutgoingLinks(view);
		case BuiltInActionEditPart.VISUAL_ID:
			return getBuiltInAction_3002OutgoingLinks(view);
		}
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getEditorPage_1000ContainedLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getGridPanel_2001ContainedLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getHorizontalPanel_2002ContainedLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getVerticalPanel_2003ContainedLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getUimField_3001ContainedLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getBuiltInAction_3002ContainedLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getGridPanel_2001IncomingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getHorizontalPanel_2002IncomingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getVerticalPanel_2003IncomingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getUimField_3001IncomingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getBuiltInAction_3002IncomingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getGridPanel_2001OutgoingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getHorizontalPanel_2002OutgoingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getVerticalPanel_2003OutgoingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getUimField_3001OutgoingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getBuiltInAction_3002OutgoingLinks(View view){
		return Collections.emptyList();
	}
}
