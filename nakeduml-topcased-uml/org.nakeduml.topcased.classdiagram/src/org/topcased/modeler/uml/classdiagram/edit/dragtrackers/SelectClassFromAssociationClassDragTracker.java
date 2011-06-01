/*******************************************************************************
 * Copyright (c) 2006 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.classdiagram.edit.dragtrackers;

import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.tools.SelectEditPartTracker;
import org.eclipse.swt.graphics.Cursor;
import org.topcased.modeler.uml.classdiagram.edit.ClassFromAssociationClassEditPart;

/**
 * The drag tracker for the class of the association class.
 * 
 * @author <a href="mailto:thomas.szadel@atosorigin.com">Thomas Szadel</a>
 * 
 */
public class SelectClassFromAssociationClassDragTracker extends SelectEditPartTracker
{
    private Request sourceRequest;

    private boolean bSourceFeedback = false;

    /**
     * Constructor.
     * 
     * @param owner
     */
    public SelectClassFromAssociationClassDragTracker(ClassFromAssociationClassEditPart owner)
    {
        super(owner);
    }

    /**
     * @see org.eclipse.gef.tools.SelectEditPartTracker#handleButtonUp(int)
     */
    protected boolean handleButtonUp(int button)
    {
        boolean bExecuteDrag = isInState(STATE_DRAG_IN_PROGRESS);

        boolean bRet = super.handleButtonUp(button);

        if (bExecuteDrag)
        {
            eraseSourceFeedback();
            setCurrentCommand(getCommand());
            executeCurrentCommand();
        }

        return bRet;
    }

    /**
     * @see org.eclipse.gef.tools.AbstractTool#handleDragInProgress()
     */
    protected boolean handleDragInProgress()
    {
        if (isInState(STATE_DRAG_IN_PROGRESS))
        {
            updateSourceRequest();
            showSourceFeedback();
            setCurrentCommand(getCommand());
        }
        return true;
    }

    /**
     * @see org.eclipse.gef.tools.SelectEditPartTracker#handleDragStarted()
     */
    protected boolean handleDragStarted()
    {
        return stateTransition(STATE_DRAG, STATE_DRAG_IN_PROGRESS);
    }

    /**
     * @see org.eclipse.gef.tools.SelectEditPartTracker#calculateCursor()
     */
    protected Cursor calculateCursor()
    {
        return org.eclipse.draw2d.Cursors.SIZEALL;
    }

    /**
     * @see org.eclipse.gef.tools.TargetingTool#deactivate()
     */
    public void deactivate()
    {
        if (!isInState(STATE_TERMINAL))
        {
            eraseSourceFeedback();
        }
        sourceRequest = null;
        super.deactivate();
    }

    /**
     * @return boolean true if feedback is being displayed, false otherwise.
     */
    private boolean isShowingFeedback()
    {
        return bSourceFeedback;
    }

    /**
     * Method setShowingFeedback.
     * 
     * @param bSet boolean to set the feedback flag on or off.
     */
    private void setShowingFeedback(boolean bSet)
    {
        bSourceFeedback = bSet;
    }

    /**
     * Method showSourceFeedback. Show the source drag feedback for the drag occurring within the viewer.
     */
    private void showSourceFeedback()
    {
        List editParts = getOperationSet();
        for (int i = 0; i < editParts.size(); i++)
        {
            EditPart editPart = (EditPart) editParts.get(i);
            editPart.showSourceFeedback(getSourceRequest());
        }
        setShowingFeedback(true);
    }

    /**
     * Method eraseSourceFeedback. Show the source drag feedback for the drag occurring within the viewer.
     */
    private void eraseSourceFeedback()
    {
        if (!isShowingFeedback())
        {
            return;
        }
        setShowingFeedback(false);
        List editParts = getOperationSet();

        for (int i = 0; i < editParts.size(); i++)
        {
            EditPart editPart = (EditPart) editParts.get(i);
            editPart.eraseSourceFeedback(getSourceRequest());
        }
    }

    /**
     * Method getSourceRequest.
     * 
     * @return Request
     */
    private Request getSourceRequest()
    {
        if (sourceRequest == null)
        {
            sourceRequest = createSourceRequest();
        }
        return sourceRequest;
    }

    /**
     * Creates the source request that is activated when the drag operation occurs.
     * 
     * @return a <code>Request</code> that is the newly created source request
     */
    protected Request createSourceRequest()
    {
        return new ChangeBoundsRequest(RequestConstants.REQ_MOVE);

    }

    /**
     * @see org.eclipse.gef.tools.TargetingTool#getCommand()
     */
    protected Command getCommand()
    {
        return getSourceEditPart().getCommand(getSourceRequest());
    }

    /**
     * @see org.eclipse.gef.tools.SelectEditPartTracker#getCommandName()
     */
    protected String getCommandName()
    {
        return RequestConstants.REQ_MOVE;
    }

    /**
     * Updates the source request to reflect the current location.
     */
    protected void updateSourceRequest()
    {
        ChangeBoundsRequest request = (ChangeBoundsRequest) getSourceRequest();
        request.setLocation(getLocation());

        Dimension delta = getDragMoveDelta();
        request.setMoveDelta(new Point(delta.width, delta.height));
    }
}