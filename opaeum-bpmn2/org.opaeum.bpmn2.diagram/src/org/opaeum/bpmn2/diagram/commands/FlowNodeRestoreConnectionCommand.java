/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package org.opaeum.bpmn2.diagram.commands;

import java.util.Iterator;
import java.util.List;

import org.eclipse.bpmn2.EndEvent;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.topcased.modeler.commands.AbstractRestoreConnectionCommand;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.editor.ICreationUtils;
import org.topcased.modeler.utils.Utils;

/**
 * FlowNode restore connection command
 *
 * @generated
 */
public class FlowNodeRestoreConnectionCommand extends AbstractRestoreConnectionCommand{
	/**
	 * @param part the EditPart that is restored
	 * @generated
	 */
	public FlowNodeRestoreConnectionCommand(EditPart part){
		super(part);
	}
	/**
	 * @see org.topcased.modeler.commands.AbstractRestoreConnectionCommand#initializeCommands()
	 * @generated
	 */
	protected void initializeCommands(){
		GraphElement graphElementSrc = getGraphElement();
		EObject eObjectSrc = Utils.getElement(graphElementSrc);
		if(eObjectSrc instanceof FlowNode){
			for(GraphElement graphElementTgt:getAllGraphElements()){
				boolean autoRef = graphElementTgt.equals(graphElementSrc);
				EObject eObjectTgt = Utils.getElement(graphElementTgt);
				if(eObjectTgt instanceof FlowNode){
					if(autoRef){
						createSequenceFlowFromFlowNodeToFlowNode_Incoming(graphElementSrc, graphElementSrc);
					}else{
						// if the graphElementSrc is the source of the edge or if it is the target and that the SourceTargetCouple is reversible
						createSequenceFlowFromFlowNodeToFlowNode_Incoming(graphElementSrc, graphElementTgt);
						// if graphElementSrc is the target of the edge or if it is the source and that the SourceTargetCouple is reversible
						createSequenceFlowFromFlowNodeToFlowNode_Incoming(graphElementTgt, graphElementSrc);
					}
				}
				if(eObjectTgt instanceof StartEvent){
					if(autoRef){
						// autoRef not allowed
					}else{
						// if graphElementSrc is the target of the edge or if it is the source and that the SourceTargetCouple is reversible
						createSequenceFlowFromStartEventToFlowNode_Incoming(graphElementTgt, graphElementSrc);
					}
				}
				if(eObjectTgt instanceof EndEvent){
					if(autoRef){
						// autoRef not allowed
					}else{
						// if the graphElementSrc is the source of the edge or if it is the target and that the SourceTargetCouple is reversible
						createSequenceFlowFromFlowNodeToEndEvent_Incoming(graphElementSrc, graphElementTgt);
					}
				}
			}
		}
	}
	/**
	 * @param srcElt the source element
	 * @param targetElt the target element
	 * @generated NOT
	 */
	private void createSequenceFlowFromFlowNodeToFlowNode_Incoming(GraphElement srcElt,GraphElement targetElt){
		FlowNode sourceObject = (FlowNode) Utils.getElement(srcElt);
		FlowNode targetObject = (FlowNode) Utils.getElement(targetElt);
		List edgeObjectList = ((org.eclipse.bpmn2.FlowElementsContainer) sourceObject.eContainer()).getFlowElements();
		for(Iterator it = edgeObjectList.iterator();it.hasNext();){
			Object obj = it.next();
			if(obj instanceof SequenceFlow){
				SequenceFlow edgeObject = (SequenceFlow) obj;
				if(targetObject.equals(edgeObject.getTargetRef()) && sourceObject.equals(edgeObject.getSourceRef()) && sourceObject.getOutgoing().contains(edgeObject)
						&& targetObject.getIncoming().contains(edgeObject)){
					// check if the relation does not exists yet
					List<GraphEdge> existing = getExistingEdges(srcElt, targetElt, SequenceFlow.class);
					if(!isAlreadyPresent(existing, edgeObject)){
						ICreationUtils factory = getModeler().getActiveConfiguration().getCreationUtils();
						// restore the link with its default presentation
						GraphElement edge = factory.createGraphElement(edgeObject);
						if(edge instanceof GraphEdge){
							SequenceFlowEdgeCreationCommand cmd = new SequenceFlowEdgeCreationCommand(getEditDomain(), (GraphEdge) edge, srcElt, false);
							cmd.setTarget(targetElt);
							add(cmd);
						}
					}
				}
			}
		}
	}
	/**
	 * @param srcElt the source element
	 * @param targetElt the target element
	 * @generated
	 */
	private void createSequenceFlowFromStartEventToFlowNode_Incoming(GraphElement srcElt,GraphElement targetElt){
		StartEvent sourceObject = (StartEvent) Utils.getElement(srcElt);
		FlowNode targetObject = (FlowNode) Utils.getElement(targetElt);
		List edgeObjectList = ((org.eclipse.bpmn2.Process) sourceObject.eContainer()).getFlowElements();
		for(Iterator it = edgeObjectList.iterator();it.hasNext();){
			Object obj = it.next();
			if(obj instanceof SequenceFlow){
				SequenceFlow edgeObject = (SequenceFlow) obj;
				if(targetObject.equals(edgeObject.getTargetRef()) && sourceObject.equals(edgeObject.getSourceRef()) && sourceObject.getOutgoing().contains(edgeObject)
						&& targetObject.getIncoming().contains(edgeObject)){
					// check if the relation does not exists yet
					List<GraphEdge> existing = getExistingEdges(srcElt, targetElt, SequenceFlow.class);
					if(!isAlreadyPresent(existing, edgeObject)){
						ICreationUtils factory = getModeler().getActiveConfiguration().getCreationUtils();
						// restore the link with its default presentation
						GraphElement edge = factory.createGraphElement(edgeObject);
						if(edge instanceof GraphEdge){
							SequenceFlowEdgeCreationCommand cmd = new SequenceFlowEdgeCreationCommand(getEditDomain(), (GraphEdge) edge, srcElt, false);
							cmd.setTarget(targetElt);
							add(cmd);
						}
					}
				}
			}
		}
	}
	/**
	 * @param srcElt the source element
	 * @param targetElt the target element
	 * @generated
	 */
	private void createSequenceFlowFromFlowNodeToEndEvent_Incoming(GraphElement srcElt,GraphElement targetElt){
		FlowNode sourceObject = (FlowNode) Utils.getElement(srcElt);
		EndEvent targetObject = (EndEvent) Utils.getElement(targetElt);
		List edgeObjectList = ((org.eclipse.bpmn2.Process) sourceObject.eContainer()).getFlowElements();
		for(Iterator it = edgeObjectList.iterator();it.hasNext();){
			Object obj = it.next();
			if(obj instanceof SequenceFlow){
				SequenceFlow edgeObject = (SequenceFlow) obj;
				if(targetObject.equals(edgeObject.getTargetRef()) && sourceObject.equals(edgeObject.getSourceRef()) && sourceObject.getOutgoing().contains(edgeObject)
						&& targetObject.getIncoming().contains(edgeObject)){
					// check if the relation does not exists yet
					List<GraphEdge> existing = getExistingEdges(srcElt, targetElt, SequenceFlow.class);
					if(!isAlreadyPresent(existing, edgeObject)){
						ICreationUtils factory = getModeler().getActiveConfiguration().getCreationUtils();
						// restore the link with its default presentation
						GraphElement edge = factory.createGraphElement(edgeObject);
						if(edge instanceof GraphEdge){
							SequenceFlowEdgeCreationCommand cmd = new SequenceFlowEdgeCreationCommand(getEditDomain(), (GraphEdge) edge, srcElt, false);
							cmd.setTarget(targetElt);
							add(cmd);
						}
					}
				}
			}
		}
	}
}