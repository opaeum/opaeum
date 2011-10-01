/*******************************************************************************
 * Copyright (c) 2006 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.alldiagram.edit;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.edit.GraphEdgeEditPart;
import org.topcased.modeler.edit.policies.EdgeEditPolicy;
import org.topcased.modeler.uml.alldiagram.commands.DeleteCommentLinkEdgeCommand;
import org.topcased.modeler.uml.alldiagram.figures.CommentLinkFigure;
import org.topcased.modeler.uml.alldiagram.preferences.AllDiagramPreferenceConstants;
import org.topcased.modeler.utils.Utils;

/**
 * CommentLink controller <br>
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class CommentLinkEditPart extends AbstractStereotypableEdgeEditPart
{

    /**
     * Constructor <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param model the graph object
     * @generated
     */
    public CommentLinkEditPart(GraphEdge model)
    {
        super(model);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     * @generated
     */
    @Override
    protected void createEditPolicies()
    {
        super.createEditPolicies();

        installEditPolicy(ModelerEditPolicyConstants.CHANGE_FONT_EDITPOLICY, null);

        // Fix for bug [#2284] Impossible to delete a comment connector
        installEditPolicy(EditPolicy.CONNECTION_ROLE, new EdgeEditPolicy()
        {
            @Override
            protected Command getDeleteCommand(GroupRequest request)
            {
                return new DeleteCommentLinkEdgeCommand((GraphElement) getHost().getModel(), false);
            }
        });
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the Figure
     * @generated
     */
    @Override
    protected IFigure createFigure()
    {
        return new CommentLinkFigure();
    }

    /**
     * @see org.topcased.modeler.edit.GraphEdgeEditPart#getPreferenceDefaultRouter()
     */
    @Override
    protected String getPreferenceDefaultRouter()
    {
        return getPreferenceStore().getString(AllDiagramPreferenceConstants.COMMENTLINK_EDGE_DEFAULT_ROUTER);
    }

    /**
     * @see org.topcased.modeler.edit.GraphEdgeEditPart#getPreferenceDefaultForegroundColor()
     * 
     * @generated
     */
    @Override
    protected Color getPreferenceDefaultForegroundColor()
    {
        String foregroundColor = getPreferenceStore().getString(AllDiagramPreferenceConstants.COMMENTLINK_EDGE_DEFAULT_FOREGROUND_COLOR);
        if (foregroundColor.length() != 0)
        {
            return Utils.getColor(foregroundColor);
        }
        return null;
    }

    /**
     * @see org.topcased.modeler.edit.GraphEdgeEditPart#getPreferenceDefaultFont()
     * 
     * @generated
     */
    @Override
    protected Font getPreferenceDefaultFont()
    {
        String font = getPreferenceStore().getString(AllDiagramPreferenceConstants.COMMENTLINK_EDGE_DEFAULT_FONT);
        if (font.length() != 0)
        {
            return Utils.getFont(new FontData(font));
        }
        return null;

    }

}