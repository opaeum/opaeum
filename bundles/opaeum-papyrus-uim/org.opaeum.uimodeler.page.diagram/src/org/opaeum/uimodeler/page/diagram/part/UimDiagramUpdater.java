package org.opaeum.uimodeler.page.diagram.part;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.update.DiagramUpdater;
import org.opaeum.uim.Page;
import org.opaeum.uim.action.AbstractActionButton;
import org.opaeum.uim.component.UimComponent;
import org.opaeum.uim.component.UimDataTable;
import org.opaeum.uim.panel.AbstractPanel;
import org.opaeum.uim.panel.GridPanel;
import org.opaeum.uimodeler.page.diagram.edit.parts.BuiltInActionButton2EditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.BuiltInActionButton3EditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.BuiltInActionButtonEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.BuiltInLink2EditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.BuiltInLinkEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.GridPanel2EditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.GridPanelEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.GridPanelGridPanelChildrenCompartment2EditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.GridPanelGridPanelChildrenCompartmentEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.HorizontalPanel2EditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.HorizontalPanelEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.InvocationButton2EditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.InvocationButton3EditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.InvocationButtonEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.LinkToQueryEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.PageEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.TransitionButtonEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.UimDataTableDataTableColumnCompartmentEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.UimDataTableEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.UimDataTableTableTableActionBarCompartmentEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.UimField2EditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.UimFieldEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.VerticalPanel2EditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.VerticalPanelEditPart;

/**
 * @generated
 */
public class UimDiagramUpdater {

	/**
	 * @generated
	 */
	public static List<UimNodeDescriptor> getSemanticChildren(View view) {
		switch (UimVisualIDRegistry.getVisualID(view)) {
		case PageEditPart.VISUAL_ID:
			return getPage_1000SemanticChildren(view);
		case GridPanelGridPanelChildrenCompartmentEditPart.VISUAL_ID:
			return getGridPanelGridPanelChildrenCompartment_7001SemanticChildren(view);
		case UimDataTableDataTableColumnCompartmentEditPart.VISUAL_ID:
			return getUimDataTableDataTableColumnCompartment_7006SemanticChildren(view);
		case UimDataTableTableTableActionBarCompartmentEditPart.VISUAL_ID:
			return getUimDataTableTableTableActionBarCompartment_7007SemanticChildren(view);
		case GridPanelGridPanelChildrenCompartment2EditPart.VISUAL_ID:
			return getGridPanelGridPanelChildrenCompartment_7008SemanticChildren(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimNodeDescriptor> getPage_1000SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		Page modelElement = (Page) view.getElement();
		LinkedList<UimNodeDescriptor> result = new LinkedList<UimNodeDescriptor>();
		{
			AbstractPanel childElement = modelElement.getPanel();
			int visualID = UimVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == GridPanelEditPart.VISUAL_ID) {
				result.add(new UimNodeDescriptor(childElement, visualID));
			}
			if (visualID == HorizontalPanelEditPart.VISUAL_ID) {
				result.add(new UimNodeDescriptor(childElement, visualID));
			}
			if (visualID == VerticalPanelEditPart.VISUAL_ID) {
				result.add(new UimNodeDescriptor(childElement, visualID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<UimNodeDescriptor> getGridPanelGridPanelChildrenCompartment_7001SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.emptyList();
		}
		GridPanel modelElement = (GridPanel) containerView.getElement();
		LinkedList<UimNodeDescriptor> result = new LinkedList<UimNodeDescriptor>();
		for (Iterator<?> it = modelElement.getChildren().iterator(); it
				.hasNext();) {
			UimComponent childElement = (UimComponent) it.next();
			int visualID = UimVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == UimFieldEditPart.VISUAL_ID) {
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == BuiltInActionButtonEditPart.VISUAL_ID) {
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == HorizontalPanel2EditPart.VISUAL_ID) {
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == VerticalPanel2EditPart.VISUAL_ID) {
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == TransitionButtonEditPart.VISUAL_ID) {
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == InvocationButtonEditPart.VISUAL_ID) {
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == LinkToQueryEditPart.VISUAL_ID) {
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == BuiltInLinkEditPart.VISUAL_ID) {
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == UimDataTableEditPart.VISUAL_ID) {
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == GridPanel2EditPart.VISUAL_ID) {
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<UimNodeDescriptor> getUimDataTableDataTableColumnCompartment_7006SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.emptyList();
		}
		UimDataTable modelElement = (UimDataTable) containerView.getElement();
		LinkedList<UimNodeDescriptor> result = new LinkedList<UimNodeDescriptor>();
		for (Iterator<?> it = modelElement.getChildren().iterator(); it
				.hasNext();) {
			UimComponent childElement = (UimComponent) it.next();
			int visualID = UimVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == UimField2EditPart.VISUAL_ID) {
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == BuiltInActionButton2EditPart.VISUAL_ID) {
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == InvocationButton2EditPart.VISUAL_ID) {
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == BuiltInLink2EditPart.VISUAL_ID) {
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<UimNodeDescriptor> getUimDataTableTableTableActionBarCompartment_7007SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.emptyList();
		}
		UimDataTable modelElement = (UimDataTable) containerView.getElement();
		LinkedList<UimNodeDescriptor> result = new LinkedList<UimNodeDescriptor>();
		for (Iterator<?> it = modelElement.getActionsOnMultipleSelection()
				.iterator(); it.hasNext();) {
			AbstractActionButton childElement = (AbstractActionButton) it
					.next();
			int visualID = UimVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == BuiltInActionButton3EditPart.VISUAL_ID) {
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == InvocationButton3EditPart.VISUAL_ID) {
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<UimNodeDescriptor> getGridPanelGridPanelChildrenCompartment_7008SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.emptyList();
		}
		GridPanel modelElement = (GridPanel) containerView.getElement();
		LinkedList<UimNodeDescriptor> result = new LinkedList<UimNodeDescriptor>();
		for (Iterator<?> it = modelElement.getChildren().iterator(); it
				.hasNext();) {
			UimComponent childElement = (UimComponent) it.next();
			int visualID = UimVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == UimFieldEditPart.VISUAL_ID) {
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == BuiltInActionButtonEditPart.VISUAL_ID) {
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == HorizontalPanel2EditPart.VISUAL_ID) {
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == VerticalPanel2EditPart.VISUAL_ID) {
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == TransitionButtonEditPart.VISUAL_ID) {
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == InvocationButtonEditPart.VISUAL_ID) {
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == LinkToQueryEditPart.VISUAL_ID) {
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == BuiltInLinkEditPart.VISUAL_ID) {
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == UimDataTableEditPart.VISUAL_ID) {
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == GridPanel2EditPart.VISUAL_ID) {
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getContainedLinks(View view) {
		switch (UimVisualIDRegistry.getVisualID(view)) {
		case PageEditPart.VISUAL_ID:
			return getPage_1000ContainedLinks(view);
		case GridPanelEditPart.VISUAL_ID:
			return getGridPanel_2001ContainedLinks(view);
		case HorizontalPanelEditPart.VISUAL_ID:
			return getHorizontalPanel_2002ContainedLinks(view);
		case VerticalPanelEditPart.VISUAL_ID:
			return getVerticalPanel_2003ContainedLinks(view);
		case UimFieldEditPart.VISUAL_ID:
			return getUimField_3028ContainedLinks(view);
		case BuiltInActionButtonEditPart.VISUAL_ID:
			return getBuiltInActionButton_3029ContainedLinks(view);
		case HorizontalPanel2EditPart.VISUAL_ID:
			return getHorizontalPanel_3030ContainedLinks(view);
		case VerticalPanel2EditPart.VISUAL_ID:
			return getVerticalPanel_3031ContainedLinks(view);
		case TransitionButtonEditPart.VISUAL_ID:
			return getTransitionButton_3032ContainedLinks(view);
		case InvocationButtonEditPart.VISUAL_ID:
			return getInvocationButton_3033ContainedLinks(view);
		case LinkToQueryEditPart.VISUAL_ID:
			return getLinkToQuery_3034ContainedLinks(view);
		case BuiltInLinkEditPart.VISUAL_ID:
			return getBuiltInLink_3035ContainedLinks(view);
		case UimDataTableEditPart.VISUAL_ID:
			return getUimDataTable_3036ContainedLinks(view);
		case UimField2EditPart.VISUAL_ID:
			return getUimField_3037ContainedLinks(view);
		case BuiltInActionButton2EditPart.VISUAL_ID:
			return getBuiltInActionButton_3038ContainedLinks(view);
		case InvocationButton2EditPart.VISUAL_ID:
			return getInvocationButton_3039ContainedLinks(view);
		case BuiltInActionButton3EditPart.VISUAL_ID:
			return getBuiltInActionButton_3040ContainedLinks(view);
		case InvocationButton3EditPart.VISUAL_ID:
			return getInvocationButton_3041ContainedLinks(view);
		case BuiltInLink2EditPart.VISUAL_ID:
			return getBuiltInLink_3042ContainedLinks(view);
		case GridPanel2EditPart.VISUAL_ID:
			return getGridPanel_3043ContainedLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getIncomingLinks(View view) {
		switch (UimVisualIDRegistry.getVisualID(view)) {
		case GridPanelEditPart.VISUAL_ID:
			return getGridPanel_2001IncomingLinks(view);
		case HorizontalPanelEditPart.VISUAL_ID:
			return getHorizontalPanel_2002IncomingLinks(view);
		case VerticalPanelEditPart.VISUAL_ID:
			return getVerticalPanel_2003IncomingLinks(view);
		case UimFieldEditPart.VISUAL_ID:
			return getUimField_3028IncomingLinks(view);
		case BuiltInActionButtonEditPart.VISUAL_ID:
			return getBuiltInActionButton_3029IncomingLinks(view);
		case HorizontalPanel2EditPart.VISUAL_ID:
			return getHorizontalPanel_3030IncomingLinks(view);
		case VerticalPanel2EditPart.VISUAL_ID:
			return getVerticalPanel_3031IncomingLinks(view);
		case TransitionButtonEditPart.VISUAL_ID:
			return getTransitionButton_3032IncomingLinks(view);
		case InvocationButtonEditPart.VISUAL_ID:
			return getInvocationButton_3033IncomingLinks(view);
		case LinkToQueryEditPart.VISUAL_ID:
			return getLinkToQuery_3034IncomingLinks(view);
		case BuiltInLinkEditPart.VISUAL_ID:
			return getBuiltInLink_3035IncomingLinks(view);
		case UimDataTableEditPart.VISUAL_ID:
			return getUimDataTable_3036IncomingLinks(view);
		case UimField2EditPart.VISUAL_ID:
			return getUimField_3037IncomingLinks(view);
		case BuiltInActionButton2EditPart.VISUAL_ID:
			return getBuiltInActionButton_3038IncomingLinks(view);
		case InvocationButton2EditPart.VISUAL_ID:
			return getInvocationButton_3039IncomingLinks(view);
		case BuiltInActionButton3EditPart.VISUAL_ID:
			return getBuiltInActionButton_3040IncomingLinks(view);
		case InvocationButton3EditPart.VISUAL_ID:
			return getInvocationButton_3041IncomingLinks(view);
		case BuiltInLink2EditPart.VISUAL_ID:
			return getBuiltInLink_3042IncomingLinks(view);
		case GridPanel2EditPart.VISUAL_ID:
			return getGridPanel_3043IncomingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getOutgoingLinks(View view) {
		switch (UimVisualIDRegistry.getVisualID(view)) {
		case GridPanelEditPart.VISUAL_ID:
			return getGridPanel_2001OutgoingLinks(view);
		case HorizontalPanelEditPart.VISUAL_ID:
			return getHorizontalPanel_2002OutgoingLinks(view);
		case VerticalPanelEditPart.VISUAL_ID:
			return getVerticalPanel_2003OutgoingLinks(view);
		case UimFieldEditPart.VISUAL_ID:
			return getUimField_3028OutgoingLinks(view);
		case BuiltInActionButtonEditPart.VISUAL_ID:
			return getBuiltInActionButton_3029OutgoingLinks(view);
		case HorizontalPanel2EditPart.VISUAL_ID:
			return getHorizontalPanel_3030OutgoingLinks(view);
		case VerticalPanel2EditPart.VISUAL_ID:
			return getVerticalPanel_3031OutgoingLinks(view);
		case TransitionButtonEditPart.VISUAL_ID:
			return getTransitionButton_3032OutgoingLinks(view);
		case InvocationButtonEditPart.VISUAL_ID:
			return getInvocationButton_3033OutgoingLinks(view);
		case LinkToQueryEditPart.VISUAL_ID:
			return getLinkToQuery_3034OutgoingLinks(view);
		case BuiltInLinkEditPart.VISUAL_ID:
			return getBuiltInLink_3035OutgoingLinks(view);
		case UimDataTableEditPart.VISUAL_ID:
			return getUimDataTable_3036OutgoingLinks(view);
		case UimField2EditPart.VISUAL_ID:
			return getUimField_3037OutgoingLinks(view);
		case BuiltInActionButton2EditPart.VISUAL_ID:
			return getBuiltInActionButton_3038OutgoingLinks(view);
		case InvocationButton2EditPart.VISUAL_ID:
			return getInvocationButton_3039OutgoingLinks(view);
		case BuiltInActionButton3EditPart.VISUAL_ID:
			return getBuiltInActionButton_3040OutgoingLinks(view);
		case InvocationButton3EditPart.VISUAL_ID:
			return getInvocationButton_3041OutgoingLinks(view);
		case BuiltInLink2EditPart.VISUAL_ID:
			return getBuiltInLink_3042OutgoingLinks(view);
		case GridPanel2EditPart.VISUAL_ID:
			return getGridPanel_3043OutgoingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getPage_1000ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getGridPanel_2001ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getHorizontalPanel_2002ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getVerticalPanel_2003ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getUimField_3028ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getBuiltInActionButton_3029ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getHorizontalPanel_3030ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getVerticalPanel_3031ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getTransitionButton_3032ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getInvocationButton_3033ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getLinkToQuery_3034ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getBuiltInLink_3035ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getUimDataTable_3036ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getUimField_3037ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getBuiltInActionButton_3038ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getInvocationButton_3039ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getBuiltInActionButton_3040ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getInvocationButton_3041ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getBuiltInLink_3042ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getGridPanel_3043ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getGridPanel_2001IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getHorizontalPanel_2002IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getVerticalPanel_2003IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getUimField_3028IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getBuiltInActionButton_3029IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getHorizontalPanel_3030IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getVerticalPanel_3031IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getTransitionButton_3032IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getInvocationButton_3033IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getLinkToQuery_3034IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getBuiltInLink_3035IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getUimDataTable_3036IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getUimField_3037IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getBuiltInActionButton_3038IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getInvocationButton_3039IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getBuiltInActionButton_3040IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getInvocationButton_3041IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getBuiltInLink_3042IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getGridPanel_3043IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getGridPanel_2001OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getHorizontalPanel_2002OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getVerticalPanel_2003OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getUimField_3028OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getBuiltInActionButton_3029OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getHorizontalPanel_3030OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getVerticalPanel_3031OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getTransitionButton_3032OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getInvocationButton_3033OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getLinkToQuery_3034OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getBuiltInLink_3035OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getUimDataTable_3036OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getUimField_3037OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getBuiltInActionButton_3038OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getInvocationButton_3039OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getBuiltInActionButton_3040OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getInvocationButton_3041OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getBuiltInLink_3042OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getGridPanel_3043OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static final DiagramUpdater TYPED_INSTANCE = new DiagramUpdater() {
		/**
		 * @generated
		 */
		@Override
		public List<UimNodeDescriptor> getSemanticChildren(View view) {
			return UimDiagramUpdater.getSemanticChildren(view);
		}

		/**
		 * @generated
		 */
		@Override
		public List<UimLinkDescriptor> getContainedLinks(View view) {
			return UimDiagramUpdater.getContainedLinks(view);
		}

		/**
		 * @generated
		 */
		@Override
		public List<UimLinkDescriptor> getIncomingLinks(View view) {
			return UimDiagramUpdater.getIncomingLinks(view);
		}

		/**
		 * @generated
		 */
		@Override
		public List<UimLinkDescriptor> getOutgoingLinks(View view) {
			return UimDiagramUpdater.getOutgoingLinks(view);
		}
	};

}
