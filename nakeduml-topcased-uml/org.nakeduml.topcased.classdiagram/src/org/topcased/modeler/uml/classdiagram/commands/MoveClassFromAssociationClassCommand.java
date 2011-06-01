/*******************************************************************************
 * Copyright (c) 2005 AIRBUS FRANCE.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.topcased.modeler.uml.classdiagram.commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.topcased.modeler.di.model.GraphNode;

/**
 * Move a class from association class command.
 * 
 * @author <a href="mailto:thomas.szadel@atosorigin.com">Thomas Szadel</a>
 * 
 */
public class MoveClassFromAssociationClassCommand extends Command
{
    private GraphNode node;

    private Point newLocation;

    private Point oldLocation;

    /**
     * Constructor
     * 
     * @param node the node to move
     * @param newLocation the new location of the figure.
     */
    public MoveClassFromAssociationClassCommand(GraphNode node, Point newLocation)
    {
        super("Move");
        this.node = node;
        this.newLocation = new Point(newLocation);
    }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute()
    {
        return node != null && newLocation != null && !newLocation.equals(node.getPosition());
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    @Override
    public void execute()
    {
        oldLocation = node.getPosition();
        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    @Override
    public void redo()
    {
        node.setPosition(newLocation);
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    @Override
    public void undo()
    {
        node.setPosition(oldLocation);
    }
}
