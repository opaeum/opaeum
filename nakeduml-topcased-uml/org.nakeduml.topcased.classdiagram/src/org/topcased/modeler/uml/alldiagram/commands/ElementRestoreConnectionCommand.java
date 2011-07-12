/*******************************************************************************
 * Copyright (c) 2006 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.alldiagram.commands;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Element;
import org.topcased.modeler.commands.AbstractRestoreConnectionCommand;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.uml.classdiagram.ClassSimpleObjectConstants;
import org.topcased.modeler.utils.Utils;

/**
 * Element restore connection command<br>
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class ElementRestoreConnectionCommand extends AbstractRestoreConnectionCommand
{
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param part the EditPart that is restored
     * @generated
     */
    public ElementRestoreConnectionCommand(EditPart part)
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
        GraphElement elt = getGraphElement();
        EObject eltObject = Utils.getElement(elt);

        if (eltObject instanceof Element)
        {
        	for(GraphElement graphElementTgt : getAllGraphElements())
        	{
        		boolean autoRef = graphElementTgt.equals(elt);

        		EObject eltObject2 = Utils.getElement(graphElementTgt);
        		if (eltObject2 instanceof Comment && !autoRef)
        		{
        			createCommentLinkFromCommentToElement(graphElementTgt, elt);
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
    private void createCommentLinkFromCommentToElement(GraphElement srcNode, GraphElement targetNode)
    {
        Comment sourceObject = (Comment) Utils.getElement(srcNode);
        Element targetObject = (Element) Utils.getElement(targetNode);

        if (sourceObject.getAnnotatedElements().contains(targetObject))
        {
            // check if the relation does not exists yet
            if (getExistingEdges(srcNode, targetNode, ClassSimpleObjectConstants.SIMPLE_OBJECT_COMMENTLINK).size() == 0)
            {
                GraphEdge edge = Utils.createGraphEdge(ClassSimpleObjectConstants.SIMPLE_OBJECT_COMMENTLINK);
                CommentLinkEdgeCreationCommand cmd = new CommentLinkEdgeCreationCommand(null, edge, srcNode, false);
                cmd.setTarget(targetNode);
                add(cmd);
            }
        }
    }

}