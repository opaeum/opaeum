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
import org.eclipse.uml2.uml.Extend;
import org.eclipse.uml2.uml.Include;
import org.eclipse.uml2.uml.UseCase;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.editor.ICreationUtils;
import org.topcased.modeler.utils.Utils;

/**
 * UseCase restore connection command<br>
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 * @author <a href="mailto:david.sciamma@anyware-tech.com">David Sciamma</a>
 */
public class UseCaseRestoreConnectionCommand extends ClassifierRestoreConnectionCommand
{
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param part the EditPart that is restored
     * @generated
     */
    public UseCaseRestoreConnectionCommand(EditPart part)
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

        if (eObjectSrc instanceof UseCase)
        {
        	for(GraphElement graphElementTgt : getAllGraphElements())
        	{     
        		EObject eObjectTgt = Utils.getElement(graphElementTgt);

        		if (eObjectTgt instanceof UseCase)
        		{
        			// if the node is the source of the edge or if it is
        			// the target and that the SourceTargetCouple is
        			// reversible
        			createExtendFromUseCaseToUseCase(graphElementSrc, graphElementTgt);
        			// if node is the target of the edge or if it is the
        			// source and that the SourceTargetCouple is
        			// reversible
        			createExtendFromUseCaseToUseCase(graphElementTgt, graphElementSrc);
        		}

        		if (eObjectTgt instanceof UseCase)
        		{
        			// if the node is the source of the edge or if it is
        			// the target and that the SourceTargetCouple is
        			// reversible
        			createIncludeFromUseCaseToUseCase(graphElementSrc, graphElementTgt);
        			// if node is the target of the edge or if it is the
        			// source and that the SourceTargetCouple is
        			// reversible
        			createIncludeFromUseCaseToUseCase(graphElementTgt, graphElementSrc);
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
    private void createExtendFromUseCaseToUseCase(GraphElement srcNode, GraphElement targetNode)
    {
        UseCase sourceObject = (UseCase) Utils.getElement(srcNode);
        UseCase targetObject = (UseCase) Utils.getElement(targetNode);

        for (Extend edgeObject : sourceObject.getExtends())
        {
            if (targetObject.equals(edgeObject.getExtendedCase()))
            {
                // check if the relation does not exists yet
                List<GraphEdge> existing = getExistingEdges(srcNode, targetNode, Extend.class);
                if (!isAlreadyPresent(existing, edgeObject))
                {
                    ICreationUtils factory = getModeler().getActiveConfiguration().getCreationUtils();
                    GraphElement edge = factory.createGraphElement(edgeObject);
                    if (edge instanceof GraphEdge)
                    {
                        ExtendEdgeCreationCommand cmd = new ExtendEdgeCreationCommand(getEditDomain(), (GraphEdge) edge, srcNode, false);
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
    private void createIncludeFromUseCaseToUseCase(GraphElement srcNode, GraphElement targetNode)
    {
        UseCase sourceObject = (UseCase) Utils.getElement(srcNode);
        UseCase targetObject = (UseCase) Utils.getElement(targetNode);

        for (Include edgeObject : sourceObject.getIncludes())
        {
            if (targetObject.equals(edgeObject.getAddition()))
            {
                // check if the relation does not exists yet
                List<GraphEdge> existing = getExistingEdges(srcNode, targetNode, Include.class);
                if (!isAlreadyPresent(existing, edgeObject))
                {
                    ICreationUtils factory = getModeler().getActiveConfiguration().getCreationUtils();
                    GraphElement edge = factory.createGraphElement(edgeObject);
                    if (edge instanceof GraphEdge)
                    {
                        IncludeEdgeCreationCommand cmd = new IncludeEdgeCreationCommand(getEditDomain(), (GraphEdge) edge, srcNode, false);
                        cmd.setTarget(targetNode);
                        add(cmd);
                    }
                }
            }
        }
    }

}