/***********************************************************************************************************************
 * Copyright (c) 2010 Communication & Systems.
 * 
 * All rights reserved. This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sebastien GABEL (CS) - initial API and implementation
 * 
 **********************************************************************************************************************/
package org.topcased.modeler.uml.alldiagram.commands;

import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Element;
import org.topcased.modeler.commands.DeleteNonSemanticGraphEdgeCommand;
import org.topcased.modeler.di.model.GraphElement;

/**
 * Handles deletion of a <i>CommentLink</i> edge by removing the target object from the <i>annotatedElement</i>
 * reference of the source object which is a {@link Comment}.<br>
 * 
 * Creation : 14 june 2010<br>
 * 
 * @generated NOT
 * @author <a href="mailto:sebastien.gabel@c-s.fr">Sebastien GABEL</a>
 * @since Topcased 4.0.0
 */
public class DeleteCommentLinkEdgeCommand extends DeleteNonSemanticGraphEdgeCommand
{
    /**
     * Constructor
     * 
     * @param elt the GraphElement to delete
     * @param deleteEdge specify whether connections to or from the elt should be deleted too
     */
    public DeleteCommentLinkEdgeCommand(GraphElement elt, boolean deleteEdge)
    {
        super(elt, deleteEdge);
    }

    /**
     * @see org.topcased.modeler.commands.DeleteGraphElementCommand#canExecute()
     */
    @Override
    public boolean canExecute()
    {
        return super.canExecute() && getSource() instanceof Comment && getTarget() instanceof Element;
    }

    /**
     * @see org.topcased.modeler.commands.DeleteNonSemanticGraphEdgeCommand#setReference()
     */
    @Override
    protected void setReference()
    {
        ((Comment) getSource()).getAnnotatedElements().add((Element) getTarget());
    }

    /**
     * @see org.topcased.modeler.commands.DeleteNonSemanticGraphEdgeCommand#unsetReference()
     */
    @Override
    protected void unsetReference()
    {
        ((Comment) getSource()).getAnnotatedElements().remove(getTarget());
    }

}