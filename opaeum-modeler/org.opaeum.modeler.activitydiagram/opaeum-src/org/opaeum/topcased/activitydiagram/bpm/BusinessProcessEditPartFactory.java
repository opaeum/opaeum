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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.uml2.uml.util.UMLSwitch;
import org.opaeum.topcased.activitydiagram.OpaeumActivityEditPartFactory;
import org.opaeum.topcased.activitydiagram.OpaeumActivityNodeUMLSwitch;
import org.topcased.modeler.di.model.Diagram;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.di.model.SimpleSemanticModelElement;
import org.topcased.modeler.edit.DynamicInstanceEditPartController;
import org.topcased.modeler.edit.EMFGraphEdgeEditPart;
import org.topcased.modeler.uml.UMLPlugin;
import org.topcased.modeler.uml.activitydiagram.ActivitySimpleObjectConstants;
import org.topcased.modeler.uml.activitydiagram.edit.ControlFlowEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ExceptionHandlerEditPart;
import org.topcased.modeler.uml.activitydiagram.edit.ObjectFlowEditPart;
import org.topcased.modeler.uml.activitydiagram.internal.structuredactivitynode.edit.BodyClauseLinkEditPart;
import org.topcased.modeler.uml.activitydiagram.internal.structuredactivitynode.edit.SuccessorClauseLinkEditPart;
import org.topcased.modeler.uml.activitydiagram.internal.structuredactivitynode.edit.TestClauseLinkEditPart;
import org.topcased.modeler.uml.alldiagram.edit.CommentLinkEditPart;
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

	private class EdgeUMLSwitch extends UMLSwitch<Object>{
		private GraphEdge edge;

		public EdgeUMLSwitch(GraphEdge edge){
			this.edge = edge;
		}
		public Object caseExceptionHandler(org.eclipse.uml2.uml.ExceptionHandler object){
			return new ExceptionHandlerEditPart(edge);
		}
		public Object caseControlFlow(org.eclipse.uml2.uml.ControlFlow object){
			return new ControlFlowEditPart(edge);
		}
		public Object caseObjectFlow(org.eclipse.uml2.uml.ObjectFlow object){
			return new ObjectFlowEditPart(edge);
		}
		public Object defaultCase(EObject object){
			return new EMFGraphEdgeEditPart(edge);
		}
	}
}