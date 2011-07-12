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
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.uml.alldiagram.AllSimpleObjectConstants;
import org.topcased.modeler.utils.Utils;

/**
 * Comment restore connection command<br>
 */
public class CommentRestoreConnectionCommand extends ElementRestoreConnectionCommand
{
    /**
     * @param part the EditPart that is restored
     */
    public CommentRestoreConnectionCommand(EditPart part)
    {
        super(part);
    }

    /**
     * @see org.topcased.modeler.commands.AbstractRestoreConnectionCommand#initializeCommands()
     */
    protected void initializeCommands()
    {

        super.initializeCommands();

        GraphElement graphElementSrc = getGraphElement();
        EObject eObjectSrc = Utils.getElement(graphElementSrc);

        if (eObjectSrc instanceof Comment)
        {
        	for(GraphElement graphElementTgt : getAllGraphElements())
        	{
        		EObject eObjectTgt = Utils.getElement(graphElementTgt);
        		if (eObjectTgt instanceof Element)
        		{
        			// if the elt is the source of the edge or if it is the target and that the SourceTargetCouple
        			// is reversible
        			createCommentLinkFromCommentToElement(graphElementSrc, graphElementTgt);
        		}
        	}
        }
    }

    /**
     * @param srcNode the source node
     * @param targetNode the target node
     */
    private void createCommentLinkFromCommentToElement(GraphElement srcNode, GraphElement targetNode)
    {
        Comment sourceObject = (Comment) Utils.getElement(srcNode);
        Element targetObject = (Element) Utils.getElement(targetNode);

        if (sourceObject.getAnnotatedElements().contains(targetObject))
        {
            // check if the relation does not exists yet
            if (getExistingEdges(srcNode, targetNode, AllSimpleObjectConstants.SIMPLE_OBJECT_COMMENTLINK).size() == 0)
            {
                GraphEdge edge = Utils.createGraphEdge(AllSimpleObjectConstants.SIMPLE_OBJECT_COMMENTLINK);
                CommentLinkEdgeCreationCommand cmd = new CommentLinkEdgeCreationCommand(null, edge, srcNode, false);
                cmd.setTarget(targetNode);
                add(cmd);
            }
        }
    }

}