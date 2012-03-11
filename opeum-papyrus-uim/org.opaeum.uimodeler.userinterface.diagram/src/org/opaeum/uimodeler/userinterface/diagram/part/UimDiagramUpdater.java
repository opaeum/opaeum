package org.opaeum.uimodeler.userinterface.diagram.part;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.gmf.runtime.notation.View;
import org.opaeum.uim.UimComponent;
import org.opaeum.uim.UimDataTable;
import org.opaeum.uim.UserInterface;
import org.opaeum.uim.action.UimAction;
import org.opaeum.uim.panel.AbstractPanel;
import org.opaeum.uim.panel.GridPanel;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.BuiltInAction2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.BuiltInAction3EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.BuiltInActionEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.GridPanel2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.GridPanelEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.GridPanelGridPanelChildrenCompartment2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.GridPanelGridPanelChildrenCompartmentEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.HorizontalPanel2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.HorizontalPanelEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.LinkToEntity2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.LinkToEntityEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.LinkToOperationEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.OperationAction2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.OperationAction3EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.OperationActionEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.TransitionActionEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UimDataTableDataTableColumnCompartmentEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UimDataTableEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UimDataTableTableTableActionBarCompartmentEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UimField2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UimFieldEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.UserInterfaceEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.VerticalPanel2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.VerticalPanelEditPart;

/**
 * @generated
 */
public class UimDiagramUpdater{
	/**
	 * @generated
	 */
	public static List<UimNodeDescriptor> getSemanticChildren(View view){
		switch(UimVisualIDRegistry.getVisualID(view)){
		case UserInterfaceEditPart.VISUAL_ID:
			return getUserInterface_1000SemanticChildren(view);
		case GridPanelGridPanelChildrenCompartmentEditPart.VISUAL_ID:
			return getGridPanelGridPanelChildrenCompartment_7001SemanticChildren(view);
		case UimDataTableDataTableColumnCompartmentEditPart.VISUAL_ID:
			return getUimDataTableDataTableColumnCompartment_7002SemanticChildren(view);
		case UimDataTableTableTableActionBarCompartmentEditPart.VISUAL_ID:
			return getUimDataTableTableTableActionBarCompartment_7003SemanticChildren(view);
		case GridPanelGridPanelChildrenCompartment2EditPart.VISUAL_ID:
			return getGridPanelGridPanelChildrenCompartment_7005SemanticChildren(view);
		}
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimNodeDescriptor> getUserInterface_1000SemanticChildren(View view){
		if(!view.isSetElement()){
			return Collections.EMPTY_LIST;
		}
		UserInterface modelElement = (UserInterface) view.getElement();
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
			if(visualID == HorizontalPanel2EditPart.VISUAL_ID){
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if(visualID == VerticalPanel2EditPart.VISUAL_ID){
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if(visualID == TransitionActionEditPart.VISUAL_ID){
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if(visualID == OperationActionEditPart.VISUAL_ID){
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if(visualID == LinkToOperationEditPart.VISUAL_ID){
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if(visualID == LinkToEntityEditPart.VISUAL_ID){
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if(visualID == UimDataTableEditPart.VISUAL_ID){
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if(visualID == GridPanel2EditPart.VISUAL_ID){
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}
	/**
	 * @generated
	 */
	public static List<UimNodeDescriptor> getUimDataTableDataTableColumnCompartment_7002SemanticChildren(View view){
		if(false == view.eContainer() instanceof View){
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if(!containerView.isSetElement()){
			return Collections.emptyList();
		}
		UimDataTable modelElement = (UimDataTable) containerView.getElement();
		LinkedList<UimNodeDescriptor> result = new LinkedList<UimNodeDescriptor>();
		for(Iterator<?> it = modelElement.getChildren().iterator();it.hasNext();){
			UimComponent childElement = (UimComponent) it.next();
			int visualID = UimVisualIDRegistry.getNodeVisualID(view, childElement);
			if(visualID == UimField2EditPart.VISUAL_ID){
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if(visualID == LinkToEntity2EditPart.VISUAL_ID){
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if(visualID == BuiltInAction2EditPart.VISUAL_ID){
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if(visualID == OperationAction2EditPart.VISUAL_ID){
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}
	/**
	 * @generated
	 */
	public static List<UimNodeDescriptor> getUimDataTableTableTableActionBarCompartment_7003SemanticChildren(View view){
		if(false == view.eContainer() instanceof View){
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if(!containerView.isSetElement()){
			return Collections.emptyList();
		}
		UimDataTable modelElement = (UimDataTable) containerView.getElement();
		LinkedList<UimNodeDescriptor> result = new LinkedList<UimNodeDescriptor>();
		for(Iterator<?> it = modelElement.getActionsOnMultipleSelection().iterator();it.hasNext();){
			UimAction childElement = (UimAction) it.next();
			int visualID = UimVisualIDRegistry.getNodeVisualID(view, childElement);
			if(visualID == BuiltInAction3EditPart.VISUAL_ID){
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if(visualID == OperationAction3EditPart.VISUAL_ID){
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}
	/**
	 * @generated
	 */
	public static List<UimNodeDescriptor> getGridPanelGridPanelChildrenCompartment_7005SemanticChildren(View view){
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
			if(visualID == HorizontalPanel2EditPart.VISUAL_ID){
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if(visualID == VerticalPanel2EditPart.VISUAL_ID){
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if(visualID == TransitionActionEditPart.VISUAL_ID){
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if(visualID == OperationActionEditPart.VISUAL_ID){
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if(visualID == LinkToOperationEditPart.VISUAL_ID){
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if(visualID == LinkToEntityEditPart.VISUAL_ID){
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if(visualID == UimDataTableEditPart.VISUAL_ID){
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if(visualID == GridPanel2EditPart.VISUAL_ID){
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
		case UserInterfaceEditPart.VISUAL_ID:
			return getUserInterface_1000ContainedLinks(view);
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
		case HorizontalPanel2EditPart.VISUAL_ID:
			return getHorizontalPanel_3003ContainedLinks(view);
		case VerticalPanel2EditPart.VISUAL_ID:
			return getVerticalPanel_3004ContainedLinks(view);
		case TransitionActionEditPart.VISUAL_ID:
			return getTransitionAction_3005ContainedLinks(view);
		case OperationActionEditPart.VISUAL_ID:
			return getOperationAction_3006ContainedLinks(view);
		case LinkToOperationEditPart.VISUAL_ID:
			return getLinkToOperation_3007ContainedLinks(view);
		case LinkToEntityEditPart.VISUAL_ID:
			return getLinkToEntity_3008ContainedLinks(view);
		case UimDataTableEditPart.VISUAL_ID:
			return getUimDataTable_3009ContainedLinks(view);
		case UimField2EditPart.VISUAL_ID:
			return getUimField_3010ContainedLinks(view);
		case LinkToEntity2EditPart.VISUAL_ID:
			return getLinkToEntity_3011ContainedLinks(view);
		case BuiltInAction2EditPart.VISUAL_ID:
			return getBuiltInAction_3012ContainedLinks(view);
		case OperationAction2EditPart.VISUAL_ID:
			return getOperationAction_3014ContainedLinks(view);
		case BuiltInAction3EditPart.VISUAL_ID:
			return getBuiltInAction_3015ContainedLinks(view);
		case OperationAction3EditPart.VISUAL_ID:
			return getOperationAction_3016ContainedLinks(view);
		case GridPanel2EditPart.VISUAL_ID:
			return getGridPanel_3017ContainedLinks(view);
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
		case HorizontalPanel2EditPart.VISUAL_ID:
			return getHorizontalPanel_3003IncomingLinks(view);
		case VerticalPanel2EditPart.VISUAL_ID:
			return getVerticalPanel_3004IncomingLinks(view);
		case TransitionActionEditPart.VISUAL_ID:
			return getTransitionAction_3005IncomingLinks(view);
		case OperationActionEditPart.VISUAL_ID:
			return getOperationAction_3006IncomingLinks(view);
		case LinkToOperationEditPart.VISUAL_ID:
			return getLinkToOperation_3007IncomingLinks(view);
		case LinkToEntityEditPart.VISUAL_ID:
			return getLinkToEntity_3008IncomingLinks(view);
		case UimDataTableEditPart.VISUAL_ID:
			return getUimDataTable_3009IncomingLinks(view);
		case UimField2EditPart.VISUAL_ID:
			return getUimField_3010IncomingLinks(view);
		case LinkToEntity2EditPart.VISUAL_ID:
			return getLinkToEntity_3011IncomingLinks(view);
		case BuiltInAction2EditPart.VISUAL_ID:
			return getBuiltInAction_3012IncomingLinks(view);
		case OperationAction2EditPart.VISUAL_ID:
			return getOperationAction_3014IncomingLinks(view);
		case BuiltInAction3EditPart.VISUAL_ID:
			return getBuiltInAction_3015IncomingLinks(view);
		case OperationAction3EditPart.VISUAL_ID:
			return getOperationAction_3016IncomingLinks(view);
		case GridPanel2EditPart.VISUAL_ID:
			return getGridPanel_3017IncomingLinks(view);
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
		case HorizontalPanel2EditPart.VISUAL_ID:
			return getHorizontalPanel_3003OutgoingLinks(view);
		case VerticalPanel2EditPart.VISUAL_ID:
			return getVerticalPanel_3004OutgoingLinks(view);
		case TransitionActionEditPart.VISUAL_ID:
			return getTransitionAction_3005OutgoingLinks(view);
		case OperationActionEditPart.VISUAL_ID:
			return getOperationAction_3006OutgoingLinks(view);
		case LinkToOperationEditPart.VISUAL_ID:
			return getLinkToOperation_3007OutgoingLinks(view);
		case LinkToEntityEditPart.VISUAL_ID:
			return getLinkToEntity_3008OutgoingLinks(view);
		case UimDataTableEditPart.VISUAL_ID:
			return getUimDataTable_3009OutgoingLinks(view);
		case UimField2EditPart.VISUAL_ID:
			return getUimField_3010OutgoingLinks(view);
		case LinkToEntity2EditPart.VISUAL_ID:
			return getLinkToEntity_3011OutgoingLinks(view);
		case BuiltInAction2EditPart.VISUAL_ID:
			return getBuiltInAction_3012OutgoingLinks(view);
		case OperationAction2EditPart.VISUAL_ID:
			return getOperationAction_3014OutgoingLinks(view);
		case BuiltInAction3EditPart.VISUAL_ID:
			return getBuiltInAction_3015OutgoingLinks(view);
		case OperationAction3EditPart.VISUAL_ID:
			return getOperationAction_3016OutgoingLinks(view);
		case GridPanel2EditPart.VISUAL_ID:
			return getGridPanel_3017OutgoingLinks(view);
		}
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getUserInterface_1000ContainedLinks(View view){
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
	public static List<UimLinkDescriptor> getBuiltInAction_3015ContainedLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getOperationAction_3016ContainedLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getGridPanel_3017ContainedLinks(View view){
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
	public static List<UimLinkDescriptor> getHorizontalPanel_3003ContainedLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getVerticalPanel_3004ContainedLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getTransitionAction_3005ContainedLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getOperationAction_3006ContainedLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getLinkToOperation_3007ContainedLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getLinkToEntity_3008ContainedLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getUimDataTable_3009ContainedLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getUimField_3010ContainedLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getLinkToEntity_3011ContainedLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getBuiltInAction_3012ContainedLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getOperationAction_3014ContainedLinks(View view){
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
	public static List<UimLinkDescriptor> getBuiltInAction_3015IncomingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getOperationAction_3016IncomingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getGridPanel_3017IncomingLinks(View view){
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
	public static List<UimLinkDescriptor> getHorizontalPanel_3003IncomingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getVerticalPanel_3004IncomingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getTransitionAction_3005IncomingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getOperationAction_3006IncomingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getLinkToOperation_3007IncomingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getLinkToEntity_3008IncomingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getUimDataTable_3009IncomingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getUimField_3010IncomingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getLinkToEntity_3011IncomingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getBuiltInAction_3012IncomingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getOperationAction_3014IncomingLinks(View view){
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
	public static List<UimLinkDescriptor> getBuiltInAction_3015OutgoingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getOperationAction_3016OutgoingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getGridPanel_3017OutgoingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getBuiltInAction_3002OutgoingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getHorizontalPanel_3003OutgoingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getVerticalPanel_3004OutgoingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getTransitionAction_3005OutgoingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getOperationAction_3006OutgoingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getLinkToOperation_3007OutgoingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getLinkToEntity_3008OutgoingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getUimDataTable_3009OutgoingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getUimField_3010OutgoingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getLinkToEntity_3011OutgoingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getBuiltInAction_3012OutgoingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getOperationAction_3014OutgoingLinks(View view){
		return Collections.emptyList();
	}
}
