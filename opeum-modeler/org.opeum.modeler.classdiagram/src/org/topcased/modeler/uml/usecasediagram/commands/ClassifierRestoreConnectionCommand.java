/*******************************************************************************
 * Copyright (c) 2005 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   David Sciamma (Anyware Technologies), Mathieu Garcia (Anyware Technologies),
 *   Jacques Lescot (Anyware Technologies), Thomas Friol (Anyware Technologies),
 *   Nicolas Lalev�e (Anyware Technologies) - initial API and implementation 
 ******************************************************************************/

package org.topcased.modeler.uml.usecasediagram.commands;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.editor.ICreationUtils;
import org.topcased.modeler.uml.alldiagram.commands.CommentLinkEdgeCreationCommand;
import org.topcased.modeler.uml.alldiagram.commands.ConstraintLinkEdgeCreationCommand;
import org.topcased.modeler.uml.alldiagram.commands.NamedElementRestoreConnectionCommand;
import org.topcased.modeler.uml.classdiagram.commands.AssociationEdgeCreationCommand;
import org.topcased.modeler.uml.usecasediagram.UseCaseSimpleObjectConstants;
import org.topcased.modeler.utils.Utils;

/**
 * Classifier restore connection command<br>
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 * @author <a href="mailto:david.sciamma@anyware-tech.com">David Sciamma</a>
 */
public class ClassifierRestoreConnectionCommand extends NamedElementRestoreConnectionCommand
{
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param part the EditPart that is restored
     * @generated
     */
    public ClassifierRestoreConnectionCommand(EditPart part)
    {
        super(part);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.commands.AbstractRestoreConnectionCommand#initializeCommands()
     * @generated NOT
     */
    protected void initializeCommands()
    {
        super.initializeCommands();

        GraphElement graphElementSrc = getGraphElement();
        EObject eObjectSrc = Utils.getElement(graphElementSrc);

        if (eObjectSrc instanceof Classifier)
        {
        	for(GraphElement graphElementTgt : getAllGraphElements())
        	{                   
        		boolean autoRef = graphElementTgt.equals(graphElementSrc);

        		EObject eObjectTgt = Utils.getElement(graphElementTgt);

        		if (eObjectTgt instanceof Classifier)
        		{
        			if (autoRef)
        			{
        				createAssociationFromClassifierToClassifier(graphElementSrc, graphElementSrc);
        			}
        			else
        			{
        				// if the node is the source of the edge or if it is
        				// the target and that the SourceTargetCouple is
        				// reversible
        				createAssociationFromClassifierToClassifier(graphElementSrc, graphElementTgt);
        				// if node is the target of the edge or if it is the
        				// source and that the SourceTargetCouple is
        				// reversible
        				createAssociationFromClassifierToClassifier(graphElementTgt, graphElementSrc);
        			}
        		}

        		if (eObjectTgt instanceof Classifier)
        		{
        			// if the node is the source of the edge or if it is
        			// the target and that the SourceTargetCouple is
        			// reversible
        			createGeneralizationFromClassifierToClassifier(graphElementSrc, graphElementTgt);
        			// if node is the target of the edge or if it is the
        			// source and that the SourceTargetCouple is
        			// reversible
        			createGeneralizationFromClassifierToClassifier(graphElementTgt, graphElementSrc);
        		}

        		if (eObjectTgt instanceof Comment)
        		{
        			// if node is the target of the edge or if it is the
        			// source and that the SourceTargetCouple is
        			// reversible
        			createCommentLinkFromCommentToClassifier(graphElementTgt, graphElementSrc);
        		}
        		if (eObjectTgt instanceof Constraint)
        		{
        			createConstraintLinkFromConstraintToClassifier(graphElementTgt, graphElementSrc);
        		}
        	}
        }

    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param srcNode the source node
     * @param targetNode the target node
     * @generated NOT
     */
    private void createAssociationFromClassifierToClassifier(GraphElement srcNode, GraphElement targetNode)
    {
    	 Type sourceObject = (Type) Utils.getElement(srcNode);
         Type targetObject = (Type) Utils.getElement(targetNode);
         
         for (Association edgeObject : sourceObject.getAssociations())
         {
                 if (edgeObject.getMemberEnds().size() == 2)
                 {
                     if (sourceObject.equals(((Property) edgeObject.getMemberEnds().get(0)).getType()) && targetObject.equals(((Property) edgeObject.getMemberEnds().get(1)).getType()))
                     {
                         // check if the relation does not exists yet
                         List<GraphEdge> existing = getExistingEdges(srcNode, targetNode, Association.class);
                         if (!isAlreadyPresent(existing, edgeObject))
                         {
                             ICreationUtils factory = getModeler().getActiveConfiguration().getCreationUtils();
                             GraphElement edge = factory.createGraphElement(edgeObject);
                             if (edge instanceof GraphEdge)
                             {
                                 AssociationEdgeCreationCommand cmd = new AssociationEdgeCreationCommand(getEditDomain(), (GraphEdge) edge, srcNode, false);
                                 cmd.setTarget(targetNode);
                                 add(cmd);
                             }
                         }
                     }
                 } 
         }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param srcNode the source node
     * @param targetNode the target node
     * @generated
     */
    private void createGeneralizationFromClassifierToClassifier(GraphElement srcNode, GraphElement targetNode)
    {
        Classifier sourceObject = (Classifier) Utils.getElement(srcNode);
        Classifier targetObject = (Classifier) Utils.getElement(targetNode);

        for (Generalization edgeObject : sourceObject.getGeneralizations())
        {
            if (targetObject.equals(edgeObject.getGeneral()))
            {
                // check if the relation does not exists yet
                List<GraphEdge> existing = getExistingEdges(srcNode, targetNode, Generalization.class);
                if (!isAlreadyPresent(existing, edgeObject))
                {
                    ICreationUtils factory = getModeler().getActiveConfiguration().getCreationUtils();
                    GraphElement edge = factory.createGraphElement(edgeObject);
                    if (edge instanceof GraphEdge)
                    {
                        GeneralizationEdgeCreationCommand cmd = new GeneralizationEdgeCreationCommand(getEditDomain(), (GraphEdge) edge, srcNode, false);
                        cmd.setTarget(targetNode);
                        add(cmd);
                    }
                }
            }
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param srcNode the source node
     * @param targetNode the target node
     * @generated
     */
    private void createCommentLinkFromCommentToClassifier(GraphElement srcNode, GraphElement targetNode)
    {
        Comment sourceObject = (Comment) Utils.getElement(srcNode);
        Classifier targetObject = (Classifier) Utils.getElement(targetNode);

        if (sourceObject.getAnnotatedElements().contains(targetObject))
        {
            // check if the relation does not exists yet
            if (getExistingEdges(srcNode, targetNode, UseCaseSimpleObjectConstants.SIMPLE_OBJECT_COMMENTLINK).size() == 0)
            {
                GraphEdge edge = Utils.createGraphEdge(UseCaseSimpleObjectConstants.SIMPLE_OBJECT_COMMENTLINK);
                CommentLinkEdgeCreationCommand cmd = new CommentLinkEdgeCreationCommand(null, edge, srcNode, false);
                cmd.setTarget(targetNode);
                add(cmd);
            }
        }
    }
        
        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @param srcNode the source node
         * @param targetNode the target node
         * @generated NOT
         */
     private void createConstraintLinkFromConstraintToClassifier(GraphElement srcNode, GraphElement targetNode)
     {
         Constraint sourceObject = (Constraint) Utils.getElement(srcNode);
         Classifier targetObject = (Classifier) Utils.getElement(targetNode);

         if (sourceObject.getConstrainedElements().contains(targetObject))
         {
             // check if the relation does not exists yet
             if (getExistingEdges(srcNode, targetNode, UseCaseSimpleObjectConstants.SIMPLE_OBJECT_CONSTRAINTLINK).size() == 0)
             {
                 GraphEdge edge = Utils.createGraphEdge(UseCaseSimpleObjectConstants.SIMPLE_OBJECT_CONSTRAINTLINK);
                 ConstraintLinkEdgeCreationCommand cmd = new ConstraintLinkEdgeCreationCommand(null, edge, srcNode, false);
                 cmd.setTarget(targetNode);
                 add(cmd);
             }
         }
        
    }

}