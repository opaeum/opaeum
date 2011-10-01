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
package org.topcased.modeler.uml.classdiagram.edit;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.commands.Command;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.policies.RestoreEditPolicy;
import org.topcased.modeler.requests.RestoreConnectionsRequest;
import org.topcased.modeler.uml.classdiagram.commands.RedefinableTemplateSignatureRestoreConnectionCommand;
import org.topcased.modeler.uml.classdiagram.figures.RedefinableTemplateSignatureFigure;
import org.topcased.modeler.uml.classdiagram.preferences.ClassDiagramPreferenceConstants;
import org.topcased.modeler.utils.Utils;

/**
 * The Class RedefinableTemplateSignatureEditPart.
 * 
 * @generated
 */
public class RedefinableTemplateSignatureEditPart extends TemplateSignatureEditPart
{    
    /**
     * Constructor
     *
     * @param obj the graph node
     * @generated
     */
    public RedefinableTemplateSignatureEditPart(GraphNode obj)
    {
        super(obj);
    }

    /**
     * Creates edit policies and associates these with roles
     *
     * @generated
     */
    protected void createEditPolicies()
    {
        super.createEditPolicies();

        installEditPolicy(ModelerEditPolicyConstants.RESTORE_EDITPOLICY, new RestoreEditPolicy()
        {
            protected Command getRestoreConnectionsCommand(RestoreConnectionsRequest request)
            {
                return new RedefinableTemplateSignatureRestoreConnectionCommand(getHost());
            }
        });
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     * @generated
     */
    protected IFigure createFigure()
    {
        return new RedefinableTemplateSignatureFigure();
    }

    /**
     * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultBackgroundColor()
     * @generated
     */
    protected Color getPreferenceDefaultBackgroundColor()
    {
        String backgroundColor = getPreferenceStore().getString(
                ClassDiagramPreferenceConstants.REDEFINABLETEMPLATESIGNATURE_DEFAULT_BACKGROUND_COLOR);
        if (backgroundColor.length() != 0)
        {
            return Utils.getColor(backgroundColor);
        }
        return null;
    }

    /**
     * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultForegroundColor()
     * @generated
     */
    protected Color getPreferenceDefaultForegroundColor()
    {
        String foregroundColor = getPreferenceStore().getString(
                ClassDiagramPreferenceConstants.REDEFINABLETEMPLATESIGNATURE_DEFAULT_FOREGROUND_COLOR);
        if (foregroundColor.length() != 0)
        {
            return Utils.getColor(foregroundColor);
        }
        return null;
    }

    /**
     * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultFont()
     * @generated
     */
    protected Font getPreferenceDefaultFont()
    {
        String preferenceFont = getPreferenceStore().getString(
                ClassDiagramPreferenceConstants.REDEFINABLETEMPLATESIGNATURE_DEFAULT_FONT);
        if (preferenceFont.length() != 0)
        {
            return Utils.getFont(new FontData(preferenceFont));
        }
        return null;

    }

}