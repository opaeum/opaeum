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
package org.opaeum.topcased.activitydiagram.bpm;

import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.action.IAction;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.ActivityParameterNode;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.FinalNode;
import org.eclipse.uml2.uml.util.UMLSwitch;
import org.opaeum.topcased.EditPartUtil;
import org.opaeum.topcased.activitydiagram.OpaeumActivityEditPartFactory;
import org.opaeum.topcased.activitydiagram.OpaeumActivityNodeUMLSwitch;
import org.opaeum.topcased.activitydiagram.OpaeumObjectFlowEdgeCreationEditPolicy;
import org.opaeum.topcased.activitydiagram.bpm.edit.SimpleTaskEditPart;
import org.opaeum.topcased.classdiagram.figure.Gradient;
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
import org.topcased.modeler.editor.ModelerGraphicalViewer;
import org.topcased.modeler.uml.UMLPlugin;
import org.topcased.modeler.uml.activitydiagram.ActivityEditPolicyConstants;
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
import org.topcased.modeler.uml.activitydiagram.figures.CallBehaviorActionFigure;
import org.topcased.modeler.uml.activitydiagram.internal.structuredactivitynode.edit.BodyClauseLinkEditPart;
import org.topcased.modeler.uml.activitydiagram.internal.structuredactivitynode.edit.ClauseEditPart;
import org.topcased.modeler.uml.activitydiagram.internal.structuredactivitynode.edit.SuccessorClauseLinkEditPart;
import org.topcased.modeler.uml.activitydiagram.internal.structuredactivitynode.edit.TestClauseLinkEditPart;
import org.topcased.modeler.uml.alldiagram.edit.CommentEditPart;
import org.topcased.modeler.uml.alldiagram.edit.CommentLinkEditPart;
import org.topcased.modeler.uml.alldiagram.edit.ConstraintEditPart;
import org.topcased.modeler.uml.alldiagram.edit.ConstraintLinkEditPart;
import org.topcased.modeler.utils.Utils;

public class BusinessProcessEditPartFactory extends OpaeumActivityEditPartFactory{
	public EditPart createEditPart(EditPart context,Object model){
		if(model instanceof Diagram){
			return new BusinessProcessDiagramEditPart((Diagram) model);
		}else if(model instanceof GraphNode){
			final GraphNode node = (GraphNode) model;
			EObject element = Utils.getElement(node);
			if(element != null){
				if(UMLPlugin.UML_URI.equals(element.eClass().getEPackage().getNsURI())){
					return (EditPart) new OpaeumActivityNodeUMLSwitch(node).doSwitch(element);
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