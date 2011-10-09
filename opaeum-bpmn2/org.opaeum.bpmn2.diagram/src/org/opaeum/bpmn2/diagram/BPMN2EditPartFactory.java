/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package org.opaeum.bpmn2.diagram;

import org.eclipse.bpmn2.di.util.BpmnDiSwitch;
import org.eclipse.bpmn2.util.Bpmn2Switch;
import org.eclipse.dd.dc.util.DcSwitch;
import org.eclipse.dd.di.util.DiSwitch;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.opaeum.bpmn2.diagram.edit.BPMN2DiagramEditPart;
import org.opaeum.bpmn2.diagram.edit.BoundaryEventEditPart;
import org.opaeum.bpmn2.diagram.edit.ComplexGatewayEditPart;
import org.opaeum.bpmn2.diagram.edit.EndEventEditPart;
import org.opaeum.bpmn2.diagram.edit.EventBasedGatewayEditPart;
import org.opaeum.bpmn2.diagram.edit.ExclusiveGatewayEditPart;
import org.opaeum.bpmn2.diagram.edit.FlowNodeEditPart;
import org.opaeum.bpmn2.diagram.edit.GatewayEditPart;
import org.opaeum.bpmn2.diagram.edit.InclusiveGatewayEditPart;
import org.opaeum.bpmn2.diagram.edit.IntermediateCatchEventEditPart;
import org.opaeum.bpmn2.diagram.edit.IntermediateThrowEventEditPart;
import org.opaeum.bpmn2.diagram.edit.MessageEventDefinitionEditPart;
import org.opaeum.bpmn2.diagram.edit.ParallelGatewayEditPart;
import org.opaeum.bpmn2.diagram.edit.SequenceFlowEditPart;
import org.opaeum.bpmn2.diagram.edit.SignalEventDefinitionEditPart;
import org.opaeum.bpmn2.diagram.edit.StartEventEditPart;
import org.opaeum.bpmn2.diagram.edit.UserTaskEditPart;
import org.topcased.modeler.di.model.Diagram;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.di.model.SimpleSemanticModelElement;
import org.topcased.modeler.edit.EMFGraphEdgeEditPart;
import org.topcased.modeler.edit.EMFGraphNodeEditPart;
import org.topcased.modeler.editor.ModelerEditPartFactory;
import org.topcased.modeler.utils.Utils;

/**
 * Part Factory : associates a model object to its controller. <br>
 *
 * @generated
 */
public class BPMN2EditPartFactory extends ModelerEditPartFactory{
	/**
	 * @see org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart,java.lang.Object)
	 * @generated
	 */
	public EditPart createEditPart(EditPart context,Object model){
		if(model instanceof Diagram){
			return new BPMN2DiagramEditPart((Diagram) model);
		}else if(model instanceof GraphNode){
			final GraphNode node = (GraphNode) model;
			EObject element = Utils.getElement(node);
			if(element != null){
				if("http://www.omg.org/spec/BPMN/20100524/MODEL-XMI".equals(element.eClass().getEPackage().getNsURI())){
					return (EditPart) new NodeBpmn2Switch(node).doSwitch(element);
				}
				if("http://www.omg.org/spec/BPMN/20100524/DI-XMI".equals(element.eClass().getEPackage().getNsURI())){
					return (EditPart) new NodeBpmnDiSwitch(node).doSwitch(element);
				}
				if("http://www.omg.org/spec/DD/20100524/DI-XMI".equals(element.eClass().getEPackage().getNsURI())){
					return (EditPart) new NodeDiSwitch(node).doSwitch(element);
				}
				if("http://www.omg.org/spec/DD/20100524/DC-XMI".equals(element.eClass().getEPackage().getNsURI())){
					return (EditPart) new NodeDcSwitch(node).doSwitch(element);
				}
			}
			if(node.getSemanticModel() instanceof SimpleSemanticModelElement){
				// Manage the Element that are not associated with a model object
			}
		}else if(model instanceof GraphEdge){
			final GraphEdge edge = (GraphEdge) model;
			EObject element = Utils.getElement(edge);
			if(element != null){
				if("http://www.omg.org/spec/BPMN/20100524/MODEL-XMI".equals(element.eClass().getEPackage().getNsURI())){
					return (EditPart) new EdgeBpmn2Switch(edge).doSwitch(element);
				}
				if("http://www.omg.org/spec/BPMN/20100524/DI-XMI".equals(element.eClass().getEPackage().getNsURI())){
					return (EditPart) new EdgeBpmnDiSwitch(edge).doSwitch(element);
				}
				if("http://www.omg.org/spec/DD/20100524/DI-XMI".equals(element.eClass().getEPackage().getNsURI())){
					return (EditPart) new EdgeDiSwitch(edge).doSwitch(element);
				}
				if("http://www.omg.org/spec/DD/20100524/DC-XMI".equals(element.eClass().getEPackage().getNsURI())){
					return (EditPart) new EdgeDcSwitch(edge).doSwitch(element);
				}
			}
			if(edge.getSemanticModel() instanceof SimpleSemanticModelElement){
				// Manage the Element that are not associated with a model object                    
			}
		}
		return super.createEditPart(context, model);
	}
	/**
	 * @generated
	 */
	private class NodeBpmn2Switch extends Bpmn2Switch{
		/**
		 * The graphical node
		 * @generated
		 */
		private GraphNode node;
		/**
		 * Constructor
		 * 
		 * @param node the graphical node
		 * @generated
		 */
		public NodeBpmn2Switch(GraphNode node){
			this.node = node;
		}
		/**
		 * @see org.eclipse.bpmn2.util.Bpmn2Switch#caseUserTask(org.eclipse.bpmn2.UserTask)
		 * @generated
		 */
		public Object caseUserTask(org.eclipse.bpmn2.UserTask object){
			return new UserTaskEditPart(node);
		}
		/**
		 * @see org.eclipse.bpmn2.util.Bpmn2Switch#caseBoundaryEvent(org.eclipse.bpmn2.BoundaryEvent)
		 * @generated
		 */
		public Object caseBoundaryEvent(org.eclipse.bpmn2.BoundaryEvent object){
			return new BoundaryEventEditPart(node);
		}
		/**
		 * @see org.eclipse.bpmn2.util.Bpmn2Switch#caseMessageEventDefinition(org.eclipse.bpmn2.MessageEventDefinition)
		 * @generated
		 */
		public Object caseMessageEventDefinition(org.eclipse.bpmn2.MessageEventDefinition object){
			return new MessageEventDefinitionEditPart(node);
		}
		/**
		 * @see org.eclipse.bpmn2.util.Bpmn2Switch#caseFlowNode(org.eclipse.bpmn2.FlowNode)
		 * @generated
		 */
		public Object caseFlowNode(org.eclipse.bpmn2.FlowNode object){
			return new FlowNodeEditPart(node);
		}
		/**
		 * @see org.eclipse.bpmn2.util.Bpmn2Switch#caseIntermediateCatchEvent(org.eclipse.bpmn2.IntermediateCatchEvent)
		 * @generated
		 */
		public Object caseIntermediateCatchEvent(org.eclipse.bpmn2.IntermediateCatchEvent object){
			return new IntermediateCatchEventEditPart(node);
		}
		/**
		 * @see org.eclipse.bpmn2.util.Bpmn2Switch#caseStartEvent(org.eclipse.bpmn2.StartEvent)
		 * @generated
		 */
		public Object caseStartEvent(org.eclipse.bpmn2.StartEvent object){
			return new StartEventEditPart(node);
		}
		/**
		 * @see org.eclipse.bpmn2.util.Bpmn2Switch#caseEndEvent(org.eclipse.bpmn2.EndEvent)
		 * @generated
		 */
		public Object caseEndEvent(org.eclipse.bpmn2.EndEvent object){
			return new EndEventEditPart(node);
		}
		/**
		 * @see org.eclipse.bpmn2.util.Bpmn2Switch#caseIntermediateThrowEvent(org.eclipse.bpmn2.IntermediateThrowEvent)
		 * @generated
		 */
		public Object caseIntermediateThrowEvent(org.eclipse.bpmn2.IntermediateThrowEvent object){
			return new IntermediateThrowEventEditPart(node);
		}
		/**
		 * @see org.eclipse.bpmn2.util.Bpmn2Switch#caseSignalEventDefinition(org.eclipse.bpmn2.SignalEventDefinition)
		 * @generated
		 */
		public Object caseSignalEventDefinition(org.eclipse.bpmn2.SignalEventDefinition object){
			return new SignalEventDefinitionEditPart(node);
		}
		/**
		 * @see org.eclipse.bpmn2.util.Bpmn2Switch#caseGateway(org.eclipse.bpmn2.Gateway)
		 * @generated
		 */
		public Object caseGateway(org.eclipse.bpmn2.Gateway object){
			return new GatewayEditPart(node);
		}
		/**
		 * @see org.eclipse.bpmn2.util.Bpmn2Switch#caseInclusiveGateway(org.eclipse.bpmn2.InclusiveGateway)
		 * @generated
		 */
		public Object caseInclusiveGateway(org.eclipse.bpmn2.InclusiveGateway object){
			return new InclusiveGatewayEditPart(node);
		}
		/**
		 * @see org.eclipse.bpmn2.util.Bpmn2Switch#caseExclusiveGateway(org.eclipse.bpmn2.ExclusiveGateway)
		 * @generated
		 */
		public Object caseExclusiveGateway(org.eclipse.bpmn2.ExclusiveGateway object){
			return new ExclusiveGatewayEditPart(node);
		}
		/**
		 * @see org.eclipse.bpmn2.util.Bpmn2Switch#caseParallelGateway(org.eclipse.bpmn2.ParallelGateway)
		 * @generated
		 */
		public Object caseParallelGateway(org.eclipse.bpmn2.ParallelGateway object){
			return new ParallelGatewayEditPart(node);
		}
		/**
		 * @see org.eclipse.bpmn2.util.Bpmn2Switch#caseEventBasedGateway(org.eclipse.bpmn2.EventBasedGateway)
		 * @generated
		 */
		public Object caseEventBasedGateway(org.eclipse.bpmn2.EventBasedGateway object){
			return new EventBasedGatewayEditPart(node);
		}
		/**
		 * @see org.eclipse.bpmn2.util.Bpmn2Switch#caseComplexGateway(org.eclipse.bpmn2.ComplexGateway)
		 * @generated
		 */
		public Object caseComplexGateway(org.eclipse.bpmn2.ComplexGateway object){
			return new ComplexGatewayEditPart(node);
		}
		/**
		 * @see org.eclipse.bpmn2.util.Bpmn2Switch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object){
			return new EMFGraphNodeEditPart(node);
		}
	}
	/**
	 * @generated
	 */
	private class NodeBpmnDiSwitch extends BpmnDiSwitch{
		/**
		 * The graphical node
		 * @generated
		 */
		private GraphNode node;
		/**
		 * Constructor
		 * 
		 * @param node the graphical node
		 * @generated
		 */
		public NodeBpmnDiSwitch(GraphNode node){
			this.node = node;
		}
		/**
		 * @see org.eclipse.bpmn2.di.util.BpmnDiSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object){
			return new EMFGraphNodeEditPart(node);
		}
	}
	/**
	 * @generated
	 */
	private class NodeDiSwitch extends DiSwitch{
		/**
		 * The graphical node
		 * @generated
		 */
		private GraphNode node;
		/**
		 * Constructor
		 * 
		 * @param node the graphical node
		 * @generated
		 */
		public NodeDiSwitch(GraphNode node){
			this.node = node;
		}
		/**
		 * @see org.eclipse.dd.di.util.DiSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object){
			return new EMFGraphNodeEditPart(node);
		}
	}
	/**
	 * @generated
	 */
	private class NodeDcSwitch extends DcSwitch{
		/**
		 * The graphical node
		 * @generated
		 */
		private GraphNode node;
		/**
		 * Constructor
		 * 
		 * @param node the graphical node
		 * @generated
		 */
		public NodeDcSwitch(GraphNode node){
			this.node = node;
		}
		/**
		 * @see org.eclipse.dd.dc.util.DcSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object){
			return new EMFGraphNodeEditPart(node);
		}
	}
	/**
	 * @generated
	 */
	private class EdgeBpmn2Switch extends Bpmn2Switch{
		/**
		 * The graphical edge
		 * @generated
		 */
		private GraphEdge edge;
		/**
		 * Constructor
		 * 
		 * @param edge the graphical edge
		 * @generated
		 */
		public EdgeBpmn2Switch(GraphEdge edge){
			this.edge = edge;
		}
		/**
		 * @see org.eclipse.bpmn2.util.Bpmn2Switch#caseSequenceFlow(org.eclipse.bpmn2.SequenceFlow)
		 * @generated
		 */
		public Object caseSequenceFlow(org.eclipse.bpmn2.SequenceFlow object){
			return new SequenceFlowEditPart(edge);
		}
		/**
		 * @see org.eclipse.bpmn2.util.Bpmn2Switch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object){
			return new EMFGraphEdgeEditPart(edge);
		}
	}
	/**
	 * @generated
	 */
	private class EdgeBpmnDiSwitch extends BpmnDiSwitch{
		/**
		 * The graphical edge
		 * @generated
		 */
		private GraphEdge edge;
		/**
		 * Constructor
		 * 
		 * @param edge the graphical edge
		 * @generated
		 */
		public EdgeBpmnDiSwitch(GraphEdge edge){
			this.edge = edge;
		}
		/**
		 * @see org.eclipse.bpmn2.di.util.BpmnDiSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object){
			return new EMFGraphEdgeEditPart(edge);
		}
	}
	/**
	 * @generated
	 */
	private class EdgeDiSwitch extends DiSwitch{
		/**
		 * The graphical edge
		 * @generated
		 */
		private GraphEdge edge;
		/**
		 * Constructor
		 * 
		 * @param edge the graphical edge
		 * @generated
		 */
		public EdgeDiSwitch(GraphEdge edge){
			this.edge = edge;
		}
		/**
		 * @see org.eclipse.dd.di.util.DiSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object){
			return new EMFGraphEdgeEditPart(edge);
		}
	}
	/**
	 * @generated
	 */
	private class EdgeDcSwitch extends DcSwitch{
		/**
		 * The graphical edge
		 * @generated
		 */
		private GraphEdge edge;
		/**
		 * Constructor
		 * 
		 * @param edge the graphical edge
		 * @generated
		 */
		public EdgeDcSwitch(GraphEdge edge){
			this.edge = edge;
		}
		/**
		 * @see org.eclipse.dd.dc.util.DcSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object){
			return new EMFGraphEdgeEditPart(edge);
		}
	}
}