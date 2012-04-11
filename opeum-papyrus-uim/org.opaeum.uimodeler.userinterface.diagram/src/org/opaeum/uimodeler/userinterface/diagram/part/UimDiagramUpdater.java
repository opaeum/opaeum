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
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.BuiltInActionButton2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.BuiltInActionButton3EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.BuiltInActionButtonEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.BuiltInLink2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.BuiltInLinkEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.GridPanel2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.GridPanelEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.GridPanelGridPanelChildrenCompartment2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.GridPanelGridPanelChildrenCompartmentEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.HorizontalPanel2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.HorizontalPanelEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.LinkToQueryEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.OperationButton2EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.OperationButton3EditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.OperationButtonEditPart;
import org.opaeum.uimodeler.userinterface.diagram.edit.parts.TransitionButtonEditPart;
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
			if(visualID == BuiltInActionButtonEditPart.VISUAL_ID){
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
			if(visualID == TransitionButtonEditPart.VISUAL_ID){
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if(visualID == OperationButtonEditPart.VISUAL_ID){
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if(visualID == LinkToQueryEditPart.VISUAL_ID){
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if(visualID == BuiltInLinkEditPart.VISUAL_ID){
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
			if(visualID == BuiltInActionButton2EditPart.VISUAL_ID){
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if(visualID == OperationButton2EditPart.VISUAL_ID){
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if(visualID == BuiltInLink2EditPart.VISUAL_ID){
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
			if(visualID == BuiltInActionButton3EditPart.VISUAL_ID){
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if(visualID == OperationButton3EditPart.VISUAL_ID){
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
			if(visualID == BuiltInActionButtonEditPart.VISUAL_ID){
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
			if(visualID == TransitionButtonEditPart.VISUAL_ID){
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if(visualID == OperationButtonEditPart.VISUAL_ID){
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if(visualID == LinkToQueryEditPart.VISUAL_ID){
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if(visualID == BuiltInLinkEditPart.VISUAL_ID){
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
		case BuiltInActionButtonEditPart.VISUAL_ID:
			return getBuiltInActionButton_3018ContainedLinks(view);
		case HorizontalPanel2EditPart.VISUAL_ID:
			return getHorizontalPanel_3003ContainedLinks(view);
		case VerticalPanel2EditPart.VISUAL_ID:
			return getVerticalPanel_3004ContainedLinks(view);
		case TransitionButtonEditPart.VISUAL_ID:
			return getTransitionButton_3019ContainedLinks(view);
		case OperationButtonEditPart.VISUAL_ID:
			return getOperationButton_3020ContainedLinks(view);
		case LinkToQueryEditPart.VISUAL_ID:
			return getLinkToQuery_3021ContainedLinks(view);
		case BuiltInLinkEditPart.VISUAL_ID:
			return getBuiltInLink_3022ContainedLinks(view);
		case UimDataTableEditPart.VISUAL_ID:
			return getUimDataTable_3009ContainedLinks(view);
		case UimField2EditPart.VISUAL_ID:
			return getUimField_3010ContainedLinks(view);
		case BuiltInActionButton2EditPart.VISUAL_ID:
			return getBuiltInActionButton_3023ContainedLinks(view);
		case OperationButton2EditPart.VISUAL_ID:
			return getOperationButton_3024ContainedLinks(view);
		case BuiltInActionButton3EditPart.VISUAL_ID:
			return getBuiltInActionButton_3025ContainedLinks(view);
		case OperationButton3EditPart.VISUAL_ID:
			return getOperationButton_3026ContainedLinks(view);
		case BuiltInLink2EditPart.VISUAL_ID:
			return getBuiltInLink_3027ContainedLinks(view);
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
		case BuiltInActionButtonEditPart.VISUAL_ID:
			return getBuiltInActionButton_3018IncomingLinks(view);
		case HorizontalPanel2EditPart.VISUAL_ID:
			return getHorizontalPanel_3003IncomingLinks(view);
		case VerticalPanel2EditPart.VISUAL_ID:
			return getVerticalPanel_3004IncomingLinks(view);
		case TransitionButtonEditPart.VISUAL_ID:
			return getTransitionButton_3019IncomingLinks(view);
		case OperationButtonEditPart.VISUAL_ID:
			return getOperationButton_3020IncomingLinks(view);
		case LinkToQueryEditPart.VISUAL_ID:
			return getLinkToQuery_3021IncomingLinks(view);
		case BuiltInLinkEditPart.VISUAL_ID:
			return getBuiltInLink_3022IncomingLinks(view);
		case UimDataTableEditPart.VISUAL_ID:
			return getUimDataTable_3009IncomingLinks(view);
		case UimField2EditPart.VISUAL_ID:
			return getUimField_3010IncomingLinks(view);
		case BuiltInActionButton2EditPart.VISUAL_ID:
			return getBuiltInActionButton_3023IncomingLinks(view);
		case OperationButton2EditPart.VISUAL_ID:
			return getOperationButton_3024IncomingLinks(view);
		case BuiltInActionButton3EditPart.VISUAL_ID:
			return getBuiltInActionButton_3025IncomingLinks(view);
		case OperationButton3EditPart.VISUAL_ID:
			return getOperationButton_3026IncomingLinks(view);
		case BuiltInLink2EditPart.VISUAL_ID:
			return getBuiltInLink_3027IncomingLinks(view);
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
		case BuiltInActionButtonEditPart.VISUAL_ID:
			return getBuiltInActionButton_3018OutgoingLinks(view);
		case HorizontalPanel2EditPart.VISUAL_ID:
			return getHorizontalPanel_3003OutgoingLinks(view);
		case VerticalPanel2EditPart.VISUAL_ID:
			return getVerticalPanel_3004OutgoingLinks(view);
		case TransitionButtonEditPart.VISUAL_ID:
			return getTransitionButton_3019OutgoingLinks(view);
		case OperationButtonEditPart.VISUAL_ID:
			return getOperationButton_3020OutgoingLinks(view);
		case LinkToQueryEditPart.VISUAL_ID:
			return getLinkToQuery_3021OutgoingLinks(view);
		case BuiltInLinkEditPart.VISUAL_ID:
			return getBuiltInLink_3022OutgoingLinks(view);
		case UimDataTableEditPart.VISUAL_ID:
			return getUimDataTable_3009OutgoingLinks(view);
		case UimField2EditPart.VISUAL_ID:
			return getUimField_3010OutgoingLinks(view);
		case BuiltInActionButton2EditPart.VISUAL_ID:
			return getBuiltInActionButton_3023OutgoingLinks(view);
		case OperationButton2EditPart.VISUAL_ID:
			return getOperationButton_3024OutgoingLinks(view);
		case BuiltInActionButton3EditPart.VISUAL_ID:
			return getBuiltInActionButton_3025OutgoingLinks(view);
		case OperationButton3EditPart.VISUAL_ID:
			return getOperationButton_3026OutgoingLinks(view);
		case BuiltInLink2EditPart.VISUAL_ID:
			return getBuiltInLink_3027OutgoingLinks(view);
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
	public static List<UimLinkDescriptor> getBuiltInActionButton_3018ContainedLinks(View view){
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
	public static List<UimLinkDescriptor> getTransitionButton_3019ContainedLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getOperationButton_3020ContainedLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getLinkToQuery_3021ContainedLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getBuiltInLink_3022ContainedLinks(View view){
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
	public static List<UimLinkDescriptor> getBuiltInActionButton_3023ContainedLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getOperationButton_3024ContainedLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getBuiltInActionButton_3025ContainedLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getOperationButton_3026ContainedLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getBuiltInLink_3027ContainedLinks(View view){
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
	public static List<UimLinkDescriptor> getBuiltInActionButton_3018IncomingLinks(View view){
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
	public static List<UimLinkDescriptor> getTransitionButton_3019IncomingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getOperationButton_3020IncomingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getLinkToQuery_3021IncomingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getBuiltInLink_3022IncomingLinks(View view){
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
	public static List<UimLinkDescriptor> getBuiltInActionButton_3023IncomingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getOperationButton_3024IncomingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getBuiltInActionButton_3025IncomingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getOperationButton_3026IncomingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getBuiltInLink_3027IncomingLinks(View view){
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
	public static List<UimLinkDescriptor> getBuiltInActionButton_3018OutgoingLinks(View view){
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
	public static List<UimLinkDescriptor> getTransitionButton_3019OutgoingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getOperationButton_3020OutgoingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getLinkToQuery_3021OutgoingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getBuiltInLink_3022OutgoingLinks(View view){
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
	public static List<UimLinkDescriptor> getBuiltInActionButton_3023OutgoingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getOperationButton_3024OutgoingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getBuiltInActionButton_3025OutgoingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getOperationButton_3026OutgoingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getBuiltInLink_3027OutgoingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getGridPanel_3017OutgoingLinks(View view){
		return Collections.emptyList();
	}
}
