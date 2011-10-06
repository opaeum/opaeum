/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package org.opaeum.bpmn2.diagram.policies;

import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.commands.Command;
import org.opaeum.bpmn2.diagram.commands.SequenceFlowEdgeCreationCommand;
import org.topcased.modeler.commands.CreateTypedEdgeCommand;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy;
import org.topcased.modeler.utils.SourceTargetData;
import org.topcased.modeler.utils.Utils;

/**
 * SequenceFlow edge creation
 *
 * @generated
 */
public class SequenceFlowEdgeCreationEditPolicy extends AbstractEdgeCreationEditPolicy{
	/**
	 * @see org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy#createCommand(org.eclipse.gef.EditDomain, org.topcased.modeler.di.model.GraphEdge, org.topcased.modeler.di.model.GraphElement)
	 * @generated
	 */
	protected CreateTypedEdgeCommand createCommand(EditDomain domain,GraphEdge edge,GraphElement source){
		return new SequenceFlowEdgeCreationCommand(domain, edge, source);
	}
	/**
	 * @see org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy#checkEdge(org.topcased.modeler.di.model.GraphEdge)
	 * @generated
	 */
	protected boolean checkEdge(GraphEdge edge){
		return Utils.getElement(edge) instanceof SequenceFlow;
	}
	/**
	 * @see org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy#checkSource(org.topcased.modeler.di.model.GraphElement)
	 * @generated
	 */
	protected boolean checkSource(GraphElement source){
		EObject object = Utils.getElement(source);
		if(object instanceof org.eclipse.bpmn2.FlowNode){
			return true;
		}
		return false;
	}
	/**
	 * @see org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy#checkTargetForSource(org.topcased.modeler.di.model.GraphElement, org.topcased.modeler.di.model.GraphElement)
	 * @generated
	 */
	protected boolean checkTargetForSource(GraphElement source,GraphElement target){
		EObject sourceObject = Utils.getElement(source);
		EObject targetObject = Utils.getElement(target);
		if(sourceObject instanceof org.eclipse.bpmn2.FlowNode && targetObject instanceof org.eclipse.bpmn2.FlowNode){
			return true;
		}
		return false;
	}
	/**
	 * @see org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy#checkCommand(org.eclipse.gef.commands.Command)
	 * @generated
	 */
	protected boolean checkCommand(Command command){
		return command instanceof SequenceFlowEdgeCreationCommand;
	}
	/**
	 * @see org.topcased.modeler.edit.policies.AbstractEdgeCreationEditPolicy#getSourceTargetData(org.topcased.modeler.di.model.GraphElement, org.topcased.modeler.di.model.GraphElement)
	 * @generated
	 */
	protected SourceTargetData getSourceTargetData(GraphElement source,GraphElement target){
		EObject sourceObject = Utils.getElement(source);
		EObject targetObject = Utils.getElement(target);
		if(sourceObject instanceof org.eclipse.bpmn2.FlowNode && targetObject instanceof org.eclipse.bpmn2.FlowNode){
			return new SourceTargetData(false, true, SourceTargetData.SOURCE_CONTAINER, "org.eclipse.bpmn2.FlowElementsContainer", "flowElements", "sourceRef",
					"targetRef", "outgoing", null, "incoming", null);
		}
		return null;
	}
}