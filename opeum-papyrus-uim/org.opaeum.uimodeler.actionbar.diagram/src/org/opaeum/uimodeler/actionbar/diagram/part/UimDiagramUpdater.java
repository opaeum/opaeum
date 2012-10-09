package org.opaeum.uimodeler.actionbar.diagram.part;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.gmf.runtime.notation.View;
import org.opaeum.uim.component.UimComponent;
import org.opaeum.uim.editor.AbstractEditor;
import org.opaeum.uim.editor.ActionBar;
import org.opaeum.uim.editor.InstanceEditor;
import org.opaeum.uimodeler.actionbar.diagram.edit.parts.AbstractEditorEditPart;
import org.opaeum.uimodeler.actionbar.diagram.edit.parts.ActionBarActionBarChildrenCompartmentEditPart;
import org.opaeum.uimodeler.actionbar.diagram.edit.parts.ActionBarEditPart;
import org.opaeum.uimodeler.actionbar.diagram.edit.parts.BuiltInActionButtonEditPart;
import org.opaeum.uimodeler.actionbar.diagram.edit.parts.BuiltInLinkEditPart;
import org.opaeum.uimodeler.actionbar.diagram.edit.parts.InvocationButtonEditPart;
import org.opaeum.uimodeler.actionbar.diagram.edit.parts.LinkToQueryEditPart;
import org.opaeum.uimodeler.actionbar.diagram.edit.parts.TransitionButtonEditPart;

/**
 * @generated
 */
public class UimDiagramUpdater{
	/**
	 * @generated
	 */
	public static List<UimNodeDescriptor> getSemanticChildren(View view){
		switch(UimVisualIDRegistry.getVisualID(view)){
		case AbstractEditorEditPart.VISUAL_ID:
			return getAbstractEditor_1000SemanticChildren(view);
		case ActionBarActionBarChildrenCompartmentEditPart.VISUAL_ID:
			return getActionBarActionBarChildrenCompartment_7001SemanticChildren(view);
		}
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimNodeDescriptor> getAbstractEditor_1000SemanticChildren(View view){
		if(!view.isSetElement()){
			return Collections.EMPTY_LIST;
		}
		InstanceEditor modelElement = (InstanceEditor) view.getElement();
		LinkedList<UimNodeDescriptor> result = new LinkedList<UimNodeDescriptor>();
		{
			ActionBar childElement = modelElement.getActionBar();
			int visualID = UimVisualIDRegistry.getNodeVisualID(view, childElement);
			if(visualID == ActionBarEditPart.VISUAL_ID){
				result.add(new UimNodeDescriptor(childElement, visualID));
			}
		}
		return result;
	}
	/**
	 * @generated
	 */
	public static List<UimNodeDescriptor> getActionBarActionBarChildrenCompartment_7001SemanticChildren(View view){
		if(false == view.eContainer() instanceof View){
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if(!containerView.isSetElement()){
			return Collections.emptyList();
		}
		ActionBar modelElement = (ActionBar) containerView.getElement();
		LinkedList<UimNodeDescriptor> result = new LinkedList<UimNodeDescriptor>();
		for(Iterator<?> it = modelElement.getChildren().iterator();it.hasNext();){
			UimComponent childElement = (UimComponent) it.next();
			int visualID = UimVisualIDRegistry.getNodeVisualID(view, childElement);
			if(visualID == BuiltInLinkEditPart.VISUAL_ID){
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if(visualID == LinkToQueryEditPart.VISUAL_ID){
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if(visualID == InvocationButtonEditPart.VISUAL_ID){
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if(visualID == BuiltInActionButtonEditPart.VISUAL_ID){
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
			if(visualID == TransitionButtonEditPart.VISUAL_ID){
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
		case AbstractEditorEditPart.VISUAL_ID:
			return getAbstractEditor_1000ContainedLinks(view);
		case ActionBarEditPart.VISUAL_ID:
			return getActionBar_2011ContainedLinks(view);
		case BuiltInLinkEditPart.VISUAL_ID:
			return getBuiltInLink_3001ContainedLinks(view);
		case LinkToQueryEditPart.VISUAL_ID:
			return getLinkToQuery_3002ContainedLinks(view);
		case InvocationButtonEditPart.VISUAL_ID:
			return getInvocationButton_3003ContainedLinks(view);
		case BuiltInActionButtonEditPart.VISUAL_ID:
			return getBuiltInActionButton_3004ContainedLinks(view);
		case TransitionButtonEditPart.VISUAL_ID:
			return getTransitionButton_3005ContainedLinks(view);
		}
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getIncomingLinks(View view){
		switch(UimVisualIDRegistry.getVisualID(view)){
		case ActionBarEditPart.VISUAL_ID:
			return getActionBar_2011IncomingLinks(view);
		case BuiltInLinkEditPart.VISUAL_ID:
			return getBuiltInLink_3001IncomingLinks(view);
		case LinkToQueryEditPart.VISUAL_ID:
			return getLinkToQuery_3002IncomingLinks(view);
		case InvocationButtonEditPart.VISUAL_ID:
			return getInvocationButton_3003IncomingLinks(view);
		case BuiltInActionButtonEditPart.VISUAL_ID:
			return getBuiltInActionButton_3004IncomingLinks(view);
		case TransitionButtonEditPart.VISUAL_ID:
			return getTransitionButton_3005IncomingLinks(view);
		}
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getOutgoingLinks(View view){
		switch(UimVisualIDRegistry.getVisualID(view)){
		case ActionBarEditPart.VISUAL_ID:
			return getActionBar_2011OutgoingLinks(view);
		case BuiltInLinkEditPart.VISUAL_ID:
			return getBuiltInLink_3001OutgoingLinks(view);
		case LinkToQueryEditPart.VISUAL_ID:
			return getLinkToQuery_3002OutgoingLinks(view);
		case InvocationButtonEditPart.VISUAL_ID:
			return getInvocationButton_3003OutgoingLinks(view);
		case BuiltInActionButtonEditPart.VISUAL_ID:
			return getBuiltInActionButton_3004OutgoingLinks(view);
		case TransitionButtonEditPart.VISUAL_ID:
			return getTransitionButton_3005OutgoingLinks(view);
		}
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getAbstractEditor_1000ContainedLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getActionBar_2011ContainedLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getBuiltInLink_3001ContainedLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getLinkToQuery_3002ContainedLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getInvocationButton_3003ContainedLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getBuiltInActionButton_3004ContainedLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getTransitionButton_3005ContainedLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getActionBar_2011IncomingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getBuiltInLink_3001IncomingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getLinkToQuery_3002IncomingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getInvocationButton_3003IncomingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getBuiltInActionButton_3004IncomingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getTransitionButton_3005IncomingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getActionBar_2011OutgoingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getBuiltInLink_3001OutgoingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getLinkToQuery_3002OutgoingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getInvocationButton_3003OutgoingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getBuiltInActionButton_3004OutgoingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getTransitionButton_3005OutgoingLinks(View view){
		return Collections.emptyList();
	}
}
