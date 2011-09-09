/*****************************************************************************
 * Copyright (c) 2009 Atos Origin.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Caroline Bourdeu d'Aguerre (Atos Origin) caroline.bourdeudaguerre@atosorigin.com - Initial API and implementation
 *
 *****************************************************************************/
package org.nakeduml.topcased.activitydiagram.method;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.uml2.uml.util.UMLSwitch;
import org.topcased.modeler.ModelerPropertyConstants;
import org.topcased.modeler.di.model.Diagram;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.di.model.SimpleSemanticModelElement;
import org.topcased.modeler.di.model.util.DIUtils;
import org.topcased.modeler.edit.DynamicInstanceEditPartController;
import org.topcased.modeler.edit.EListEditPart;
import org.topcased.modeler.edit.EMFGraphEdgeEditPart;
import org.topcased.modeler.edit.EMFGraphNodeEditPart;
import org.topcased.modeler.editor.ModelerEditPartFactory;
import org.topcased.modeler.uml.UMLPlugin;
import org.topcased.modeler.uml.activitydiagram.ActivitySimpleObjectConstants;
import org.topcased.modeler.uml.activitydiagram.edit.AcceptCallActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.AcceptEventActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ActivityFinalNodeEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ActivityNodeEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ActivityParameterNodeEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ActivityPartitionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.AddStructuralFeatureValueActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.AddVariableValueActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.BroadcastSignalActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.CallBehaviorActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.CallOperationActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.CentralBufferNodeEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ClearAssociationActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ClearStructuralFeatureActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ClearVariableActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ConditionalNodeEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ControlFlowEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ControlNodeEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.CreateLinkActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.CreateLinkObjectActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.CreateObjectActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.DataStoreNodeEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.DecisionNodeEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.DestroyLinkActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.DestroyObjectActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ExceptionHandlerEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ExecutableNodeEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ExpansionNodeEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ExpansionRegionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.FlowFinalNodeEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ForkNodeEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.InitialNodeEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.InputPinEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.InterruptibleActivityRegionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.JoinNodeEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.LoopNodeEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.MergeNodeEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ObjectFlowEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.OpaqueActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.OutputPinEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.RaiseExceptionActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ReadExtentActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ReadIsClassifiedObjectActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ReadLinkActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ReadLinkObjectEndActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ReadLinkObjectEndQualifierActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ReadSelfActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ReadStructuralFeatureActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ReadVariableActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ReclassifyObjectActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ReduceActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.RemoveStructuralFeatureValueActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.RemoveVariableValueActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ReplyActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.SendObjectActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.SendSignalActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.SequenceNodeEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.StartClassifierBehaviorActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.StructuredActivityNodeEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.TestIdentityActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.UnmarshallActionEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ValueSpecificationActionEditPart;
import org.topcased.modeler.uml.activitydiagram.internal.structuredactivitynode.edit.BodyClauseLinkEditPart;
import org.topcased.modeler.uml.activitydiagram.internal.structuredactivitynode.edit.ClauseEditPart;
import org.topcased.modeler.uml.activitydiagram.internal.structuredactivitynode.edit.SuccessorClauseLinkEditPart;
import org.topcased.modeler.uml.activitydiagram.internal.structuredactivitynode.edit.TestClauseLinkEditPart;
import org.topcased.modeler.uml.alldiagram.edit.CommentEditPart;
import org.topcased.modeler.uml.alldiagram.edit.CommentLinkEditPart;
import org.topcased.modeler.uml.alldiagram.edit.ConstraintEditPart;
import org.topcased.modeler.uml.alldiagram.edit.ConstraintLinkEditPart;
import org.topcased.modeler.utils.Utils;

public class MethodEditPartFactory extends ModelerEditPartFactory{
	public EditPart createEditPart(EditPart context,Object model){
		if(model instanceof Diagram){
			return new MethodDiagramEditPart((Diagram) model);
		}else if(model instanceof GraphNode){
			final GraphNode node = (GraphNode) model;
			EObject element = Utils.getElement(node);
			if(element != null){
				if(UMLPlugin.UML_URI.equals(element.eClass().getEPackage().getNsURI())){
					return (EditPart) new NodeUMLSwitch(node).doSwitch(element);
				}else{
					// This is for the extension point org.topcased.modeler.customEditPart
					return DynamicInstanceEditPartController.instance.getInstanceEditPart(element, node, this.getClass());
				}
			}
		}else if(model instanceof GraphEdge){
			final GraphEdge edge = (GraphEdge) model;
			EObject element = Utils.getElement(edge);
			if(element != null){
				if(UMLPlugin.UML_URI.equals(element.eClass().getEPackage().getNsURI())){
					return (EditPart) new EdgeUMLSwitch(edge).doSwitch(element);
				}else{
					// This is for the extension point org.topcased.modeler.customEditPart
					return DynamicInstanceEditPartController.instance.getInstanceEditPart(element, edge, this.getClass());
				}
			}
			if(edge.getSemanticModel() instanceof SimpleSemanticModelElement){
				// Manage the Element that are not associated with a model object
				if(ActivitySimpleObjectConstants.SIMPLE_OBJECT_COMMENTLINK.equals(((SimpleSemanticModelElement) edge.getSemanticModel()).getTypeInfo())){
					return new CommentLinkEditPart(edge);
				}
				if(ActivitySimpleObjectConstants.SIMPLE_OBJECT_CONSTRAINTLINK.equals(((SimpleSemanticModelElement) edge.getSemanticModel()).getTypeInfo())){
					return new ConstraintLinkEditPart(edge);
				}
				if(ActivitySimpleObjectConstants.SIMPLE_OBJECT_TESTCLAUSELINK.equals(((SimpleSemanticModelElement) edge.getSemanticModel()).getTypeInfo())){
					return new TestClauseLinkEditPart(edge);
				}
				if(ActivitySimpleObjectConstants.SIMPLE_OBJECT_BODYCLAUSELINK.equals(((SimpleSemanticModelElement) edge.getSemanticModel()).getTypeInfo())){
					return new BodyClauseLinkEditPart(edge);
				}
				if(ActivitySimpleObjectConstants.SIMPLE_OBJECT_SUCCESSORCLAUSELINK.equals(((SimpleSemanticModelElement) edge.getSemanticModel()).getTypeInfo())){
					return new SuccessorClauseLinkEditPart(edge);
				}
			}
		}
		return super.createEditPart(context, model);
	}
	private class NodeUMLSwitch extends UMLSwitch<Object>{
		/** The graphical node */
		private GraphNode node;
		/**
		 * Constructor <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @param node
		 *            the graphical node
		 * @generated
		 */
		public NodeUMLSwitch(GraphNode node){
			this.node = node;
		}
		// Fix #809
		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseInterruptibleActivityRegion(org.eclipse.uml2.uml.InterruptibleActivityRegion)
		 */
		public Object caseInterruptibleActivityRegion(org.eclipse.uml2.uml.InterruptibleActivityRegion object){
			return new InterruptibleActivityRegionEditPart(node);
		}
		// EndFix #809
		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseActivityNode(org.eclipse.uml2.uml.ActivityNode)
		 * @generated
		 */
		public Object caseActivityNode(org.eclipse.uml2.uml.ActivityNode object){
			return new ActivityNodeEditPart(node);
		}
		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseControlNode(org.eclipse.uml2.uml.ControlNode)
		 * @generated
		 */
		public Object caseControlNode(org.eclipse.uml2.uml.ControlNode object){
			return new ControlNodeEditPart(node);
		}
		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseInitialNode(org.eclipse.uml2.uml.InitialNode)
		 * @generated
		 */
		public Object caseInitialNode(org.eclipse.uml2.uml.InitialNode object){
			return new InitialNodeEditPart(node);
		}
		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseDecisionNode(org.eclipse.uml2.uml.DecisionNode)
		 * @generated
		 */
		public Object caseDecisionNode(org.eclipse.uml2.uml.DecisionNode object){
			return new DecisionNodeEditPart(node);
		}
		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseMergeNode(org.eclipse.uml2.uml.MergeNode)
		 * @generated
		 */
		public Object caseMergeNode(org.eclipse.uml2.uml.MergeNode object){
			return new MergeNodeEditPart(node);
		}
		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseForkNode(org.eclipse.uml2.uml.ForkNode)
		 * @generated
		 */
		public Object caseForkNode(org.eclipse.uml2.uml.ForkNode object){
			return new ForkNodeEditPart(node);
		}
		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseJoinNode(org.eclipse.uml2.uml.JoinNode)
		 * @generated
		 */
		public Object caseJoinNode(org.eclipse.uml2.uml.JoinNode object){
			return new JoinNodeEditPart(node);
		}
		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseActivityFinalNode(org.eclipse.uml2.uml.ActivityFinalNode)
		 * @generated
		 */
		public Object caseActivityFinalNode(org.eclipse.uml2.uml.ActivityFinalNode object){
			return new ActivityFinalNodeEditPart(node);
		}
		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseFlowFinalNode(org.eclipse.uml2.uml.FlowFinalNode)
		 * @generated
		 */
		public Object caseFlowFinalNode(org.eclipse.uml2.uml.FlowFinalNode object){
			return new FlowFinalNodeEditPart(node);
		}
		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseCentralBufferNode(org.eclipse.uml2.uml.CentralBufferNode)
		 * @generated
		 */
		public Object caseCentralBufferNode(org.eclipse.uml2.uml.CentralBufferNode object){
			return new CentralBufferNodeEditPart(node);
		}
		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseDataStoreNode(org.eclipse.uml2.uml.DataStoreNode)
		 * @generated
		 */
		public Object caseDataStoreNode(org.eclipse.uml2.uml.DataStoreNode object){
			return new DataStoreNodeEditPart(node);
		}
		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseActivityParameterNode(org.eclipse.uml2.uml.ActivityParameterNode)
		 * @generated
		 */
		public Object caseActivityParameterNode(org.eclipse.uml2.uml.ActivityParameterNode object){
			return new ActivityParameterNodeEditPart(node);
		}
		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseExpansionNode(org.eclipse.uml2.uml.ExpansionNode)
		 * @generated
		 */
		public Object caseExpansionNode(org.eclipse.uml2.uml.ExpansionNode object){
			return new ExpansionNodeEditPart(node);
		}
		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseExecutableNode(org.eclipse.uml2.uml.ExecutableNode)
		 * @generated
		 */
		public Object caseExecutableNode(org.eclipse.uml2.uml.ExecutableNode object){
			return new ExecutableNodeEditPart(node);
		}
		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseAction(org.eclipse.uml2.uml.Action)
		 * @generated
		 */
		public Object caseAction(org.eclipse.uml2.uml.Action object){
			return new ActionEditPart(node);
		}
		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseCallBehaviorAction(org.eclipse.uml2.uml.CallBehaviorAction)
		 * @generated
		 */
		public Object caseCallBehaviorAction(org.eclipse.uml2.uml.CallBehaviorAction object){
			return new CallBehaviorActionEditPart(node);
		}
		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseCallOperationAction(org.eclipse.uml2.uml.CallOperationAction)
		 * @generated
		 */
		public Object caseCallOperationAction(org.eclipse.uml2.uml.CallOperationAction object){
			return new CallOperationActionEditPart(node);
		}
		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseExpansionRegion(org.eclipse.uml2.uml.ExpansionRegion)
		 * @generated
		 */
		public Object caseExpansionRegion(org.eclipse.uml2.uml.ExpansionRegion object){
			return new ExpansionRegionEditPart(node);
		}
		/**
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseInputPin(org.eclipse.uml2.uml.InputPin)
		 * @generated
		 */
		public Object caseInputPin(org.eclipse.uml2.uml.InputPin object){
			return new InputPinEditPart(node);
		}
		/**
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseOutputPin(org.eclipse.uml2.uml.OutputPin)
		 * @generated
		 */
		public Object caseOutputPin(org.eclipse.uml2.uml.OutputPin object){
			return new OutputPinEditPart(node);
		}
		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseActivityPartition(org.eclipse.uml2.uml.ActivityPartition)
		 * @generated
		 */
		public Object caseActivityPartition(org.eclipse.uml2.uml.ActivityPartition object){
			return new ActivityPartitionEditPart(node);
		}
		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseComment(org.eclipse.uml2.uml.Comment)
		 * @generated
		 */
		public Object caseComment(org.eclipse.uml2.uml.Comment object){
			return new CommentEditPart(node);
		}
		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseSendSignalAction(org.eclipse.uml2.uml.SendSignalAction)
		 * @generated
		 */
		public Object caseSendSignalAction(org.eclipse.uml2.uml.SendSignalAction object){
			return new SendSignalActionEditPart(node);
		}
		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseAcceptEventAction(org.eclipse.uml2.uml.AcceptEventAction)
		 * @generated
		 */
		public Object caseAcceptEventAction(org.eclipse.uml2.uml.AcceptEventAction object){
			return new AcceptEventActionEditPart(node);
		}
		public Object caseAcceptCallAction(org.eclipse.uml2.uml.AcceptCallAction object){
			return new AcceptCallActionEditPart(node);
		}
		public Object caseClearAssociationAction(org.eclipse.uml2.uml.ClearAssociationAction object){
			return new ClearAssociationActionEditPart(node);
		}
		public Object caseCreateObjectAction(org.eclipse.uml2.uml.CreateObjectAction object){
			return new CreateObjectActionEditPart(node);
		}
		public Object caseDestroyObjectAction(org.eclipse.uml2.uml.DestroyObjectAction object){
			return new DestroyObjectActionEditPart(node);
		}
		public Object caseBroadcastSignalAction(org.eclipse.uml2.uml.BroadcastSignalAction object){
			return new BroadcastSignalActionEditPart(node);
		}
		public Object caseSendObjectAction(org.eclipse.uml2.uml.SendObjectAction object){
			return new SendObjectActionEditPart(node);
		}
		public Object caseReadLinkAction(org.eclipse.uml2.uml.ReadLinkAction object){
			return new ReadLinkActionEditPart(node);
		}
		public Object caseCreateLinkAction(org.eclipse.uml2.uml.CreateLinkAction object){
			return new CreateLinkActionEditPart(node);
		}
		public Object caseCreateLinkObjectAction(org.eclipse.uml2.uml.CreateLinkObjectAction object){
			return new CreateLinkObjectActionEditPart(node);
		}
		public Object caseDestroyLinkAction(org.eclipse.uml2.uml.DestroyLinkAction object){
			return new DestroyLinkActionEditPart(node);
		}
		public Object caseOpaqueAction(org.eclipse.uml2.uml.OpaqueAction object){
			return new OpaqueActionEditPart(node);
		}
		public Object caseRaiseExceptionAction(org.eclipse.uml2.uml.RaiseExceptionAction object){
			return new RaiseExceptionActionEditPart(node);
		}
		public Object caseReadExtentAction(org.eclipse.uml2.uml.ReadExtentAction object){
			return new ReadExtentActionEditPart(node);
		}
		public Object caseReadIsClassifiedObjectAction(org.eclipse.uml2.uml.ReadIsClassifiedObjectAction object){
			return new ReadIsClassifiedObjectActionEditPart(node);
		}
		public Object caseReadLinkObjectEndAction(org.eclipse.uml2.uml.ReadLinkObjectEndAction object){
			return new ReadLinkObjectEndActionEditPart(node);
		}
		public Object caseReadLinkObjectEndQualifierAction(org.eclipse.uml2.uml.ReadLinkObjectEndQualifierAction object){
			return new ReadLinkObjectEndQualifierActionEditPart(node);
		}
		public Object caseReadSelfAction(org.eclipse.uml2.uml.ReadSelfAction object){
			return new ReadSelfActionEditPart(node);
		}
		public Object caseReclassifyObjectAction(org.eclipse.uml2.uml.ReclassifyObjectAction object){
			return new ReclassifyObjectActionEditPart(node);
		}
		public Object caseReduceAction(org.eclipse.uml2.uml.ReduceAction object){
			return new ReduceActionEditPart(node);
		}
		public Object caseReplyAction(org.eclipse.uml2.uml.ReplyAction object){
			return new ReplyActionEditPart(node);
		}
		public Object caseStartClassifierBehaviorAction(org.eclipse.uml2.uml.StartClassifierBehaviorAction object){
			return new StartClassifierBehaviorActionEditPart(node);
		}
		public Object caseClearStructuralFeatureAction(org.eclipse.uml2.uml.ClearStructuralFeatureAction object){
			return new ClearStructuralFeatureActionEditPart(node);
		}
		public Object caseReadStructuralFeatureAction(org.eclipse.uml2.uml.ReadStructuralFeatureAction object){
			return new ReadStructuralFeatureActionEditPart(node);
		}
		public Object caseAddStructuralFeatureValueAction(org.eclipse.uml2.uml.AddStructuralFeatureValueAction object){
			return new AddStructuralFeatureValueActionEditPart(node);
		}
		public Object caseRemoveStructuralFeatureValueAction(org.eclipse.uml2.uml.RemoveStructuralFeatureValueAction object){
			return new RemoveStructuralFeatureValueActionEditPart(node);
		}
		public Object caseStructuredActivityNode(org.eclipse.uml2.uml.StructuredActivityNode object){
			return new StructuredActivityNodeEditPart(node);
		}
		public Object caseConditionalNode(org.eclipse.uml2.uml.ConditionalNode object){
			return new ConditionalNodeEditPart(node);
		}
		public Object caseLoopNode(org.eclipse.uml2.uml.LoopNode object){
			return new LoopNodeEditPart(node);
		}
		public Object caseSequenceNode(org.eclipse.uml2.uml.SequenceNode object){
			return new SequenceNodeEditPart(node);
		}
		public Object caseTestIdentityAction(org.eclipse.uml2.uml.TestIdentityAction object){
			return new TestIdentityActionEditPart(node);
		}
		public Object caseUnmarshallAction(org.eclipse.uml2.uml.UnmarshallAction object){
			return new UnmarshallActionEditPart(node);
		}
		public Object caseValueSpecificationAction(org.eclipse.uml2.uml.ValueSpecificationAction object){
			return new ValueSpecificationActionEditPart(node);
		}
		public Object caseClearVariableAction(org.eclipse.uml2.uml.ClearVariableAction object){
			return new ClearVariableActionEditPart(node);
		}
		public Object caseReadVariableAction(org.eclipse.uml2.uml.ReadVariableAction object){
			return new ReadVariableActionEditPart(node);
		}
		public Object caseAddVariableValueAction(org.eclipse.uml2.uml.AddVariableValueAction object){
			return new AddVariableValueActionEditPart(node);
		}
		public Object caseRemoveVariableValueAction(org.eclipse.uml2.uml.RemoveVariableValueAction object){
			return new RemoveVariableValueActionEditPart(node);
		}
		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object caseClause(org.eclipse.uml2.uml.Clause object){
			return new ClauseEditPart(node);
		}
		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object){
			return new EMFGraphNodeEditPart(node);
		}
		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseConstraint(org.eclipse.uml2.uml.Constraint)
		 * @generated NOT
		 */
		public Object caseConstraint(org.eclipse.uml2.uml.Constraint object){
			String feature = DIUtils.getPropertyValue(node, ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
			if(!"".equals(feature)){
				int featureID = Integer.parseInt(feature);
				return new EListEditPart(node, object.eClass().getEStructuralFeature(featureID));
			}else{
				return new ConstraintEditPart(node);
			}
		}
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private class EdgeUMLSwitch extends UMLSwitch<Object>{
		/** The graphical edge */
		private GraphEdge edge;
		/**
		 * Constructor <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @param edge
		 *            the graphical edge
		 * @generated
		 */
		public EdgeUMLSwitch(GraphEdge edge){
			this.edge = edge;
		}
		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseExceptionHandler(org.eclipse.uml2.uml.ExceptionHandler)
		 * @generated
		 */
		public Object caseExceptionHandler(org.eclipse.uml2.uml.ExceptionHandler object){
			return new ExceptionHandlerEditPart(edge);
		}
		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseControlFlow(org.eclipse.uml2.uml.ControlFlow)
		 * @generated
		 */
		public Object caseControlFlow(org.eclipse.uml2.uml.ControlFlow object){
			return new ControlFlowEditPart(edge);
		}
		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#caseObjectFlow(org.eclipse.uml2.uml.ObjectFlow)
		 * @generated
		 */
		public Object caseObjectFlow(org.eclipse.uml2.uml.ObjectFlow object){
			return new ObjectFlowEditPart(edge);
		}
		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.uml2.uml.util.UMLSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object){
			return new EMFGraphEdgeEditPart(edge);
		}
	}
}