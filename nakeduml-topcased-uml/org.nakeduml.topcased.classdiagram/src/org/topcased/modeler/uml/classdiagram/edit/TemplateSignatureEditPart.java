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
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.UnmovableShapeEditPolicy;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.EMFGraphNodeEditPart;
import org.topcased.modeler.edit.policies.RestoreEditPolicy;
import org.topcased.modeler.requests.RestoreConnectionsRequest;
import org.topcased.modeler.uml.classdiagram.ClassEditPolicyConstants;
import org.topcased.modeler.uml.classdiagram.commands.TemplateSignatureRestoreConnectionCommand;
import org.topcased.modeler.uml.classdiagram.figures.TemplateSignatureFigure;
import org.topcased.modeler.uml.classdiagram.policies.TemplateBindingEdgeCreationEditPolicy;
import org.topcased.modeler.uml.classdiagram.policies.TemplateSignatureLayoutEditPolicy;
import org.topcased.modeler.uml.classdiagram.policies.TemplateSignatureResizableEditPolicy;
import org.topcased.modeler.uml.classdiagram.preferences.ClassDiagramPreferenceConstants;
import org.topcased.modeler.utils.Utils;

/**
 * The TemplateSignature object controller
 * 
 * @generated
 */
public class TemplateSignatureEditPart extends EMFGraphNodeEditPart
{
    /**
     * Constructor
     *
     * @param obj the graph node
     * @generated
     */
    public TemplateSignatureEditPart(GraphNode obj)
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

        installEditPolicy(ClassEditPolicyConstants.TEMPLATEBINDING_EDITPOLICY,
                new TemplateBindingEdgeCreationEditPolicy());

        installEditPolicy(ModelerEditPolicyConstants.RESTORE_EDITPOLICY, new RestoreEditPolicy()
        {
            protected Command getRestoreConnectionsCommand(RestoreConnectionsRequest request)
            {
                return new TemplateSignatureRestoreConnectionCommand(getHost());
            }
        });
        
        TemplateSignatureResizableEditPolicy resizableEditPolicy = new TemplateSignatureResizableEditPolicy();
        resizableEditPolicy.setResizeDirections(PositionConstants.EAST_WEST);
        installEditPolicy(ModelerEditPolicyConstants.RESIZABLE_EDITPOLICY, resizableEditPolicy);
        
        installEditPolicy(ModelerEditPolicyConstants.UNMOVABLE_SHAPE_EDITPOLICY, new UnmovableShapeEditPolicy());
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new TemplateSignatureLayoutEditPolicy());
        installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, null);
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     * @generated
     */
    protected IFigure createFigure()
    {
        return new TemplateSignatureFigure();
    }
    
    @Override
    protected void refreshHeaderLabel()
    {
        // do nothing
    }
    
    @Override
    protected void performDirectEdit()
    {
        // do nothing
    }

    /**
     * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultBackgroundColor()
     * @generated
     */
    protected Color getPreferenceDefaultBackgroundColor()
    {
        String backgroundColor = getPreferenceStore().getString(
                ClassDiagramPreferenceConstants.TEMPLATESIGNATURE_DEFAULT_BACKGROUND_COLOR);
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
                ClassDiagramPreferenceConstants.TEMPLATESIGNATURE_DEFAULT_FOREGROUND_COLOR);
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
                ClassDiagramPreferenceConstants.TEMPLATESIGNATURE_DEFAULT_FONT);
        if (preferenceFont.length() != 0)
        {
            return Utils.getFont(new FontData(preferenceFont));
        }
        return null;

    }

}