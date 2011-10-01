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


import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.topcased.modeler.edit.policies.ResizableEditPolicy;
import org.topcased.modeler.uml.classdiagram.edit.TemplateSignatureEditPart;

/**
 * Specific policy for TemplateSignature to get the command from the right editPart
 * 
 * @author eperico
 *
 */
public class TemplateSignatureResizableEditPolicy extends ResizableEditPolicy
{
    
    /**
     * @see org.topcased.modeler.edit.policies.ResizableEditPolicy#getCommand(org.eclipse.gef.Request)
     */
    @Override
    public Command getCommand(Request request)
    {
        Command command = null;
        EditPart part = getHost();
        if (part instanceof TemplateSignatureEditPart && REQ_RESIZE.equals(request.getType()))
        {
            ChangeBoundsRequest changeBoundsRequest = (ChangeBoundsRequest) request;
            Dimension delta = changeBoundsRequest.getSizeDelta();
            // disable resize on the left side
            if (changeBoundsRequest.getMoveDelta().x < 0)
            {
                command = UnexecutableCommand.INSTANCE;
            }
            else if (delta.width != 0 && delta.height == 0)
            {
                ChangeBoundsRequest req = new ChangeBoundsRequest(REQ_RESIZE_CHILDREN);
                req.setEditParts(part);
                req.setMoveDelta(changeBoundsRequest.getMoveDelta());
                req.setSizeDelta(changeBoundsRequest.getSizeDelta());
                req.setLocation(changeBoundsRequest.getLocation());
                req.setExtendedData(changeBoundsRequest.getExtendedData());
                req.setResizeDirection(changeBoundsRequest.getResizeDirection());
                EditPart classEdiPart = part.getParent();
                command = classEdiPart.getParent().getCommand(req);                            
            }            
        }
        else
        {
            command = super.getCommand(request);            
        }
        return command;
    }

}
