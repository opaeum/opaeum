/*******************************************************************************
 * Copyright (c) 2006 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.classdiagram.commands;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.topcased.modeler.ModelerPropertyConstants;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.di.model.util.DIUtils;
import org.topcased.modeler.editor.ICreationUtils;
import org.topcased.modeler.utils.Utils;

/**
 * Interface restore connection command<br>
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class InterfaceRestoreConnectionCommand extends ClassifierRestoreConnectionCommand
{
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param part the EditPart that is restored
     * @generated
     */
    public InterfaceRestoreConnectionCommand(EditPart part)
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

        if (eObjectSrc instanceof Interface)
        {
        	for(GraphElement graphElementTgt : getAllGraphElements())
        	{
        		EObject eObjectTgt = Utils.getElement(graphElementTgt);

        		if (eObjectTgt instanceof BehavioredClassifier)
        		{
        			// if element is the target of the edge or if it is the
        			// source and that the SourceTargetCouple is
        			// reversible
        			createInterfaceRealizationFromBehavioredClassifierToInterface(graphElementTgt, graphElementSrc);
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
    private void createInterfaceRealizationFromBehavioredClassifierToInterface(GraphElement srcNode, GraphElement targetNode)
    {
        BehavioredClassifier sourceObject = (BehavioredClassifier) Utils.getElement(srcNode);
        Interface targetObject = (Interface) Utils.getElement(targetNode);

        for (InterfaceRealization edgeObject : sourceObject.getInterfaceRealizations())
        {
            if (targetObject.equals(edgeObject.getContract()))
            {
                // check if the relation does not exists yet
                List<GraphEdge> existing = getExistingEdges(srcNode, targetNode, InterfaceRealization.class);
                if (!isAlreadyPresent(existing, edgeObject))
                {
                    ICreationUtils factory = getModeler().getActiveConfiguration().getCreationUtils();
                    // restore the link with its default presentation
                    GraphElement edge = factory.createGraphElement(edgeObject);
                    if (edge instanceof GraphEdge)
                    {
                        InterfaceRealizationEdgeCreationCommand cmd = new InterfaceRealizationEdgeCreationCommand(getEditDomain(), (GraphEdge) edge, srcNode, false);
                        cmd.setTarget(targetNode);
                        add(cmd);
                    }
                }
            }
        }
    }

}