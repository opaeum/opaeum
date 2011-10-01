/*****************************************************************************
 * Copyright (c) 2009 atos origin.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  eperico (atos origin) emilien.perico@atosorigin.com - Initial API and implementation
 *
  *****************************************************************************/
package org.topcased.modeler.uml.classdiagram.policies;

import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.uml2.uml.TemplateSignature;
import org.topcased.draw2d.figures.PackageFigure;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.policies.ModelerLayoutEditPolicy;
import org.topcased.modeler.requests.AutoLayoutRequest;
import org.topcased.modeler.utils.Utils;

/**
 * The layout edit policy for templateableElement
 * 
 * @author eperico
 */
public abstract class TemplateableElementLayoutEditPolicy extends ModelerLayoutEditPolicy
{
    /**
     * @see org.topcased.modeler.edit.policies.ModelerLayoutEditPolicy#isValid(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject)
     */
    @Override
    protected boolean isValid(EObject child, EObject parent)
    {
        // TemplateSignature for package
        // RedefinableTemplateSignature for classifier
        return child instanceof TemplateSignature;
    }
    
    /**
     * @see org.topcased.modeler.edit.policies.ModelerLayoutEditPolicy#calculateChildPosition(org.eclipse.draw2d.geometry.Rectangle, org.eclipse.gef.GraphicalEditPart)
     */
    @Override
    protected Point calculateChildPosition(Rectangle constraint, GraphicalEditPart parent)
    {
        Figure figure = (Figure) parent.getFigure();
        Point location = figure.getLocation();
        Figure parentFigure = (Figure) ((GraphicalEditPart) parent.getParent()).getFigure();
        Point parentLocation = parentFigure.getLocation();

        Point point = null;
        // find a better way to manage the child position
        if (parentFigure instanceof PackageFigure)
        {
            point = new Point(location.x+figure.getSize().width-parentLocation.x-12, location.y-parentLocation.y-45);            
        }
        else
        {
            point = new Point(location.x+figure.getSize().width-10, location.y-52);            
        }
        return point;
    }
    
    /**
     * @see org.topcased.modeler.edit.policies.ModelerLayoutEditPolicy#getCreateCommand(org.eclipse.gef.requests.CreateRequest)
     */
    protected Command getCreateCommand(CreateRequest request)
    {
        Command result = null;
        Object newObject = request.getNewObject();
        if (newObject instanceof EObject)
        {
            result = super.getCreateCommand(request);
        }
        // If the request is about a List of objects, we create a
        // CompoundCommand and we send the CreateRequest
        else if (newObject instanceof List && !((List<?>) newObject).isEmpty())
        {
            result = new CompoundCommand();
            Command command = getCommandForTemplateSignature(request, (List< ? >) newObject);
            if (command != null)
            {
                ((CompoundCommand)result).add(command);
            }
            Iterator< ? > it = getHost().getChildren().iterator();
            while (it.hasNext())
            {
                EditPart ep = (EditPart) it.next();
                Command childCmd = ep.getCommand(request);
                if (childCmd != null && childCmd.canExecute())
                {
                    ((CompoundCommand) result).add(childCmd);
                }
            }
        }
        return result;
    }
    
    /**
     * Gets the command for template signature.
     * 
     * @param request the request
     * @param newObject list of GraphElement
     * 
     * @return the command for template signature
     */
    private Command getCommandForTemplateSignature(CreateRequest request, List <?> newObject)
    {
        Command command = null;
        for (Object element: (List < ? >) newObject)
        {
            if (element instanceof GraphNode)
            {
                if (Utils.getElement((GraphNode) element) instanceof TemplateSignature)
                {
                    Rectangle container = ((GraphicalEditPart) getHost()).getContentPane().getClientArea();
                    Rectangle constraint = new Rectangle(0, 0, -1, -1);
                    if (request.getLocation() != null)
                    {
                        constraint = (Rectangle) getConstraintFor(request);
                    }
                    Dimension dim = constraint.getSize();

                    // get the GraphNodes associated in the diagram model
                    GraphNode parentGraphNode = (GraphNode) getHost().getModel();
                    GraphNode childGraphNode = (GraphNode) element;

                    Point loc = calculateChildPosition(constraint, (GraphicalEditPart) getHost());
                    int attachment = calculateChildAttachment(childGraphNode, loc, container);

                    Command childCmd = getCreateCommand(parentGraphNode, childGraphNode, loc.getCopy(), dim, attachment, false);
                    if (childCmd != null && childCmd.canExecute())
                    {
                        command =  childCmd;
                    }                        
                }
            }
        }
        return command;
    }
    
    /**
     * @see org.eclipse.gef.editpolicies.XYLayoutEditPolicy#getXYLayout()
     */
    protected XYLayout getXYLayout() 
    {
        IFigure hostFigure = getHostFigure();
        LayoutManager layoutManager = hostFigure.getParent().getLayoutManager();
        return (XYLayout) layoutManager;  
    }
    
    /**
     * Create the AutoLayout Command for the given request
     * 
     * @param request the autolayout request
     * @return the autolayout command
     */
    protected Command getAutoLayoutCommand(AutoLayoutRequest request)
    {
        return null;
    }
    
    /**
     * @see org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy#getAddCommand(org.eclipse.gef.Request)
     */
    protected Command getAddCommand(Request request)
    {
        Command result = null;

        // If only one EObject is added, we iterate on all the children
        // EListEditPart until we found a valid Command.
        if (((ChangeBoundsRequest) request).getEditParts().size() <= 1)
        {
            Iterator it = getHost().getChildren().iterator();
            while (it.hasNext())
            {
                EditPart ep = (EditPart) it.next();
                Command childCmd = ep.getCommand(request);
                if (childCmd != null && childCmd.canExecute())
                {
                    result = childCmd;
                }
            }
        }
        // If the request is about a List of objects, we create a
        // CompoundCommand and we send the CreateRequest to each EListEditPart
        else
        {
            result = new CompoundCommand();
            Iterator it = getHost().getChildren().iterator();
            while (it.hasNext())
            {
                EditPart ep = (EditPart) it.next();
                Command childCmd = ep.getCommand(request);
                if (childCmd != null && childCmd.canExecute())
                {
                    ((CompoundCommand) result).add(childCmd);
                }
            }
        }
        return result;
    }
    
}
