package org.opaeum.uimodeler.abstractactionbar.diagram.part;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.gmf.runtime.notation.View;
import org.opaeum.uim.AbstractActionBar;
import org.opaeum.uim.UimComponent;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.AbstractActionBarEditPart;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.BuiltInActionEditPart;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.OperationActionEditPart;
import org.opaeum.uimodeler.abstractactionbar.diagram.edit.parts.TransitionActionEditPart;

/**
 * @generated
 */
public class UimDiagramUpdater{
	/**
	 * @generated
	 */
	public static List<UimNodeDescriptor> getSemanticChildren(View view){
		switch(UimVisualIDRegistry.getVisualID(view)){
		case AbstractActionBarEditPart.VISUAL_ID:
			return getAbstractActionBar_1000SemanticChildren(view);
		}
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimNodeDescriptor> getAbstractActionBar_1000SemanticChildren(View view){
		if(!view.isSetElement()){
			return Collections.EMPTY_LIST;
		}
		AbstractActionBar modelElement = (AbstractActionBar) view.getElement();
		LinkedList<UimNodeDescriptor> result = new LinkedList<UimNodeDescriptor>();
		for(Iterator<?> it = modelElement.getChildren().iterator();it.hasNext();){
			UimComponent childElement = (UimComponent) it.next();
			int visualID = UimVisualIDRegistry.getNodeVisualID(view, childElement);
			if(visualID == BuiltInActionEditPart.VISUAL_ID){
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
		}
		return result;
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getContainedLinks(View view){
		switch(UimVisualIDRegistry.getVisualID(view)){
		case AbstractActionBarEditPart.VISUAL_ID:
			return getAbstractActionBar_1000ContainedLinks(view);
		case BuiltInActionEditPart.VISUAL_ID:
			return getBuiltInAction_2001ContainedLinks(view);
		case TransitionActionEditPart.VISUAL_ID:
			return getTransitionAction_2002ContainedLinks(view);
		case OperationActionEditPart.VISUAL_ID:
			return getOperationAction_2003ContainedLinks(view);
		}
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getIncomingLinks(View view){
		switch(UimVisualIDRegistry.getVisualID(view)){
		case BuiltInActionEditPart.VISUAL_ID:
			return getBuiltInAction_2001IncomingLinks(view);
		case TransitionActionEditPart.VISUAL_ID:
			return getTransitionAction_2002IncomingLinks(view);
		case OperationActionEditPart.VISUAL_ID:
			return getOperationAction_2003IncomingLinks(view);
		}
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getOutgoingLinks(View view){
		switch(UimVisualIDRegistry.getVisualID(view)){
		case BuiltInActionEditPart.VISUAL_ID:
			return getBuiltInAction_2001OutgoingLinks(view);
		case TransitionActionEditPart.VISUAL_ID:
			return getTransitionAction_2002OutgoingLinks(view);
		case OperationActionEditPart.VISUAL_ID:
			return getOperationAction_2003OutgoingLinks(view);
		}
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getAbstractActionBar_1000ContainedLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getBuiltInAction_2001ContainedLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getTransitionAction_2002ContainedLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getOperationAction_2003ContainedLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getBuiltInAction_2001IncomingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getTransitionAction_2002IncomingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getOperationAction_2003IncomingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getBuiltInAction_2001OutgoingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getTransitionAction_2002OutgoingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getOperationAction_2003OutgoingLinks(View view){
		return Collections.emptyList();
	}
}
