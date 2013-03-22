package org.opaeum.uimodeler.page.diagram.part;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.structure.DiagramStructure;
import org.opaeum.uim.Page;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.action.ActionPackage;
import org.opaeum.uim.component.ComponentPackage;
import org.opaeum.uim.panel.PanelPackage;
import org.opaeum.uimodeler.page.diagram.edit.parts.BuiltInActionButton2EditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.BuiltInActionButton3EditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.BuiltInActionButtonEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.BuiltInLink2EditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.BuiltInLinkEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.BuiltInLinkNameEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.GridPanel2EditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.GridPanelEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.GridPanelGridPanelChildrenCompartment2EditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.GridPanelGridPanelChildrenCompartmentEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.GridPanelName2EditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.GridPanelNameEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.HorizontalPanel2EditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.HorizontalPanelEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.InvocationButton2EditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.InvocationButton3EditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.InvocationButtonEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.InvocationButtonName2EditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.InvocationButtonNameEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.LinkToQueryEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.LinkToQueryNameEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.PageEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.TransitionButtonEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.TransitionButtonNameEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.UimDataTableDataTableColumnCompartmentEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.UimDataTableEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.UimDataTableTableTableActionBarCompartmentEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.UimField2EditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.UimFieldEditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.VerticalPanel2EditPart;
import org.opaeum.uimodeler.page.diagram.edit.parts.VerticalPanelEditPart;

/**
 * This registry is used to determine which type of visual object should be
 * created for the corresponding Diagram, Node, ChildNode or Link represented
 * by a domain model object.
 * 
 * @generated
 */
public class UimVisualIDRegistry {
	/**
	 * @generated
	 */
	private static final String DEBUG_KEY = "org.opaeum.uimodeler.page.diagram/debug/visualID"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static int getVisualID(View view) {
		if (view instanceof Diagram) {
			if (PageEditPart.MODEL_ID.equals(view.getType())) {
				return PageEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		return org.opaeum.uimodeler.page.diagram.part.UimVisualIDRegistry
				.getVisualID(view.getType());
	}

	/**
	 * @generated
	 */
	public static String getModelID(View view) {
		View diagram = view.getDiagram();
		while (view != diagram) {
			EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
			if (annotation != null) {
				return (String) annotation.getDetails().get("modelID"); //$NON-NLS-1$
			}
			view = (View) view.eContainer();
		}
		return diagram != null ? diagram.getType() : null;
	}

	/**
	 * @generated
	 */
	public static int getVisualID(String type) {
		try {
			return Integer.parseInt(type);
		} catch (NumberFormatException e) {
			if (Boolean.TRUE.toString().equalsIgnoreCase(
					Platform.getDebugOption(DEBUG_KEY))) {
				UimDiagramEditorPlugin.getInstance().logError(
						"Unable to parse view type as a visualID number: "
								+ type);
			}
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static String getType(int visualID) {
		return Integer.toString(visualID);
	}

	/**
	 * @generated
	 */
	public static int getDiagramVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (UimPackage.eINSTANCE.getPage()
				.isSuperTypeOf(domainElement.eClass())
				&& isDiagram((Page) domainElement)) {
			return PageEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static int getNodeVisualID(View containerView, EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		String containerModelID = org.opaeum.uimodeler.page.diagram.part.UimVisualIDRegistry
				.getModelID(containerView);
		if (!PageEditPart.MODEL_ID.equals(containerModelID)) {
			return -1;
		}
		int containerVisualID;
		if (PageEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = org.opaeum.uimodeler.page.diagram.part.UimVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = PageEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		switch (containerVisualID) {
		case PageEditPart.VISUAL_ID:
			if (PanelPackage.eINSTANCE.getGridPanel().isSuperTypeOf(
					domainElement.eClass())

			) {
				return GridPanelEditPart.VISUAL_ID;
			}
			if (PanelPackage.eINSTANCE.getHorizontalPanel().isSuperTypeOf(
					domainElement.eClass())

			) {
				return HorizontalPanelEditPart.VISUAL_ID;
			}
			if (PanelPackage.eINSTANCE.getVerticalPanel().isSuperTypeOf(
					domainElement.eClass())

			) {
				return VerticalPanelEditPart.VISUAL_ID;
			}
			break;
		case GridPanelGridPanelChildrenCompartmentEditPart.VISUAL_ID:
			if (ComponentPackage.eINSTANCE.getUimField().isSuperTypeOf(
					domainElement.eClass())

			) {
				return UimFieldEditPart.VISUAL_ID;
			}
			if (ActionPackage.eINSTANCE.getBuiltInActionButton().isSuperTypeOf(
					domainElement.eClass())

			) {
				return BuiltInActionButtonEditPart.VISUAL_ID;
			}
			if (PanelPackage.eINSTANCE.getHorizontalPanel().isSuperTypeOf(
					domainElement.eClass())

			) {
				return HorizontalPanel2EditPart.VISUAL_ID;
			}
			if (PanelPackage.eINSTANCE.getVerticalPanel().isSuperTypeOf(
					domainElement.eClass())

			) {
				return VerticalPanel2EditPart.VISUAL_ID;
			}
			if (ActionPackage.eINSTANCE.getTransitionButton().isSuperTypeOf(
					domainElement.eClass())

			) {
				return TransitionButtonEditPart.VISUAL_ID;
			}
			if (ActionPackage.eINSTANCE.getInvocationButton().isSuperTypeOf(
					domainElement.eClass())

			) {
				return InvocationButtonEditPart.VISUAL_ID;
			}
			if (ActionPackage.eINSTANCE.getLinkToQuery().isSuperTypeOf(
					domainElement.eClass())

			) {
				return LinkToQueryEditPart.VISUAL_ID;
			}
			if (ActionPackage.eINSTANCE.getBuiltInLink().isSuperTypeOf(
					domainElement.eClass())

			) {
				return BuiltInLinkEditPart.VISUAL_ID;
			}
			if (ComponentPackage.eINSTANCE.getUimDataTable().isSuperTypeOf(
					domainElement.eClass())

			) {
				return UimDataTableEditPart.VISUAL_ID;
			}
			if (PanelPackage.eINSTANCE.getGridPanel().isSuperTypeOf(
					domainElement.eClass())

			) {
				return GridPanel2EditPart.VISUAL_ID;
			}
			break;
		case UimDataTableDataTableColumnCompartmentEditPart.VISUAL_ID:
			if (ComponentPackage.eINSTANCE.getUimField().isSuperTypeOf(
					domainElement.eClass())

			) {
				return UimField2EditPart.VISUAL_ID;
			}
			if (ActionPackage.eINSTANCE.getBuiltInActionButton().isSuperTypeOf(
					domainElement.eClass())

			) {
				return BuiltInActionButton2EditPart.VISUAL_ID;
			}
			if (ActionPackage.eINSTANCE.getInvocationButton().isSuperTypeOf(
					domainElement.eClass())

			) {
				return InvocationButton2EditPart.VISUAL_ID;
			}
			if (ActionPackage.eINSTANCE.getBuiltInLink().isSuperTypeOf(
					domainElement.eClass())

			) {
				return BuiltInLink2EditPart.VISUAL_ID;
			}
			break;
		case UimDataTableTableTableActionBarCompartmentEditPart.VISUAL_ID:
			if (ActionPackage.eINSTANCE.getBuiltInActionButton().isSuperTypeOf(
					domainElement.eClass())

			) {
				return BuiltInActionButton3EditPart.VISUAL_ID;
			}
			if (ActionPackage.eINSTANCE.getInvocationButton().isSuperTypeOf(
					domainElement.eClass())

			) {
				return InvocationButton3EditPart.VISUAL_ID;
			}
			break;
		case GridPanelGridPanelChildrenCompartment2EditPart.VISUAL_ID:
			if (ComponentPackage.eINSTANCE.getUimField().isSuperTypeOf(
					domainElement.eClass())

			) {
				return UimFieldEditPart.VISUAL_ID;
			}
			if (ActionPackage.eINSTANCE.getBuiltInActionButton().isSuperTypeOf(
					domainElement.eClass())

			) {
				return BuiltInActionButtonEditPart.VISUAL_ID;
			}
			if (PanelPackage.eINSTANCE.getHorizontalPanel().isSuperTypeOf(
					domainElement.eClass())

			) {
				return HorizontalPanel2EditPart.VISUAL_ID;
			}
			if (PanelPackage.eINSTANCE.getVerticalPanel().isSuperTypeOf(
					domainElement.eClass())

			) {
				return VerticalPanel2EditPart.VISUAL_ID;
			}
			if (ActionPackage.eINSTANCE.getTransitionButton().isSuperTypeOf(
					domainElement.eClass())

			) {
				return TransitionButtonEditPart.VISUAL_ID;
			}
			if (ActionPackage.eINSTANCE.getInvocationButton().isSuperTypeOf(
					domainElement.eClass())

			) {
				return InvocationButtonEditPart.VISUAL_ID;
			}
			if (ActionPackage.eINSTANCE.getLinkToQuery().isSuperTypeOf(
					domainElement.eClass())

			) {
				return LinkToQueryEditPart.VISUAL_ID;
			}
			if (ActionPackage.eINSTANCE.getBuiltInLink().isSuperTypeOf(
					domainElement.eClass())

			) {
				return BuiltInLinkEditPart.VISUAL_ID;
			}
			if (ComponentPackage.eINSTANCE.getUimDataTable().isSuperTypeOf(
					domainElement.eClass())

			) {
				return UimDataTableEditPart.VISUAL_ID;
			}
			if (PanelPackage.eINSTANCE.getGridPanel().isSuperTypeOf(
					domainElement.eClass())

			) {
				return GridPanel2EditPart.VISUAL_ID;
			}
			break;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static boolean canCreateNode(View containerView, int nodeVisualID) {
		String containerModelID = org.opaeum.uimodeler.page.diagram.part.UimVisualIDRegistry
				.getModelID(containerView);
		if (!PageEditPart.MODEL_ID.equals(containerModelID)) {
			return false;
		}
		int containerVisualID;
		if (PageEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = org.opaeum.uimodeler.page.diagram.part.UimVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = PageEditPart.VISUAL_ID;
			} else {
				return false;
			}
		}
		switch (containerVisualID) {
		case PageEditPart.VISUAL_ID:
			if (GridPanelEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (HorizontalPanelEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (VerticalPanelEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case GridPanelEditPart.VISUAL_ID:
			if (GridPanelNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (GridPanelGridPanelChildrenCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case TransitionButtonEditPart.VISUAL_ID:
			if (TransitionButtonNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case InvocationButtonEditPart.VISUAL_ID:
			if (InvocationButtonNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case LinkToQueryEditPart.VISUAL_ID:
			if (LinkToQueryNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case BuiltInLinkEditPart.VISUAL_ID:
			if (BuiltInLinkNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case UimDataTableEditPart.VISUAL_ID:
			if (UimDataTableDataTableColumnCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (UimDataTableTableTableActionBarCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case InvocationButton3EditPart.VISUAL_ID:
			if (InvocationButtonName2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case GridPanel2EditPart.VISUAL_ID:
			if (GridPanelName2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (GridPanelGridPanelChildrenCompartment2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case GridPanelGridPanelChildrenCompartmentEditPart.VISUAL_ID:
			if (UimFieldEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (BuiltInActionButtonEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (HorizontalPanel2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (VerticalPanel2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (TransitionButtonEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (InvocationButtonEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (LinkToQueryEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (BuiltInLinkEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (UimDataTableEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (GridPanel2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case UimDataTableDataTableColumnCompartmentEditPart.VISUAL_ID:
			if (UimField2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (BuiltInActionButton2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (InvocationButton2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (BuiltInLink2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case UimDataTableTableTableActionBarCompartmentEditPart.VISUAL_ID:
			if (BuiltInActionButton3EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (InvocationButton3EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case GridPanelGridPanelChildrenCompartment2EditPart.VISUAL_ID:
			if (UimFieldEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (BuiltInActionButtonEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (HorizontalPanel2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (VerticalPanel2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (TransitionButtonEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (InvocationButtonEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (LinkToQueryEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (BuiltInLinkEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (UimDataTableEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (GridPanel2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		}
		return false;
	}

	/**
	 * @generated
	 */
	public static int getLinkWithClassVisualID(EObject domainElement) {
		if (domainElement == null) {
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
	private static boolean isDiagram(Page element) {
		return true;
	}

	/**
	 * @generated
	 */
	public static boolean checkNodeVisualID(View containerView,
			EObject domainElement, int candidate) {
		if (candidate == -1) {
			//unrecognized id is always bad
			return false;
		}
		int basic = getNodeVisualID(containerView, domainElement);
		return basic == candidate;
	}

	/**
	 * @generated
	 */
	public static boolean isCompartmentVisualID(int visualID) {
		switch (visualID) {
		case GridPanelGridPanelChildrenCompartmentEditPart.VISUAL_ID:
		case UimDataTableDataTableColumnCompartmentEditPart.VISUAL_ID:
		case UimDataTableTableTableActionBarCompartmentEditPart.VISUAL_ID:
		case GridPanelGridPanelChildrenCompartment2EditPart.VISUAL_ID:
			return true;
		default:
			break;
		}
		return false;
	}

	/**
	 * @generated
	 */
	public static boolean isSemanticLeafVisualID(int visualID) {
		switch (visualID) {
		case PageEditPart.VISUAL_ID:
			return false;
		case HorizontalPanelEditPart.VISUAL_ID:
		case VerticalPanelEditPart.VISUAL_ID:
		case UimFieldEditPart.VISUAL_ID:
		case BuiltInActionButtonEditPart.VISUAL_ID:
		case HorizontalPanel2EditPart.VISUAL_ID:
		case VerticalPanel2EditPart.VISUAL_ID:
		case TransitionButtonEditPart.VISUAL_ID:
		case InvocationButtonEditPart.VISUAL_ID:
		case LinkToQueryEditPart.VISUAL_ID:
		case BuiltInLinkEditPart.VISUAL_ID:
		case UimField2EditPart.VISUAL_ID:
		case BuiltInActionButton2EditPart.VISUAL_ID:
		case InvocationButton2EditPart.VISUAL_ID:
		case BuiltInActionButton3EditPart.VISUAL_ID:
		case InvocationButton3EditPart.VISUAL_ID:
		case BuiltInLink2EditPart.VISUAL_ID:
			return true;
		default:
			break;
		}
		return false;
	}

	/**
	 * @generated
	 */
	public static final DiagramStructure TYPED_INSTANCE = new DiagramStructure() {
		/**
		 * @generated
		 */
		@Override
		public int getVisualID(View view) {
			return org.opaeum.uimodeler.page.diagram.part.UimVisualIDRegistry
					.getVisualID(view);
		}

		/**
		 * @generated
		 */
		@Override
		public String getModelID(View view) {
			return org.opaeum.uimodeler.page.diagram.part.UimVisualIDRegistry
					.getModelID(view);
		}

		/**
		 * @generated
		 */
		@Override
		public int getNodeVisualID(View containerView, EObject domainElement) {
			return org.opaeum.uimodeler.page.diagram.part.UimVisualIDRegistry
					.getNodeVisualID(containerView, domainElement);
		}

		/**
		 * @generated
		 */
		@Override
		public boolean checkNodeVisualID(View containerView,
				EObject domainElement, int candidate) {
			return org.opaeum.uimodeler.page.diagram.part.UimVisualIDRegistry
					.checkNodeVisualID(containerView, domainElement, candidate);
		}

		/**
		 * @generated
		 */
		@Override
		public boolean isCompartmentVisualID(int visualID) {
			return org.opaeum.uimodeler.page.diagram.part.UimVisualIDRegistry
					.isCompartmentVisualID(visualID);
		}

		/**
		 * @generated
		 */
		@Override
		public boolean isSemanticLeafVisualID(int visualID) {
			return org.opaeum.uimodeler.page.diagram.part.UimVisualIDRegistry
					.isSemanticLeafVisualID(visualID);
		}
	};
}
