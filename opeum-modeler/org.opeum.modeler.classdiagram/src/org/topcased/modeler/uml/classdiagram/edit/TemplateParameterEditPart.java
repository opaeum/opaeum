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
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.ParameterableElement;
import org.eclipse.uml2.uml.TemplateParameter;
import org.topcased.draw2d.figures.ComposedLabel;
import org.topcased.draw2d.figures.ILabelFigure;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.policies.RestoreEditPolicy;
import org.topcased.modeler.requests.RestoreConnectionsRequest;
import org.topcased.modeler.uml.alldiagram.edit.ElementEditPart;
import org.topcased.modeler.uml.classdiagram.commands.TemplateParameterRestoreConnectionCommand;
import org.topcased.modeler.uml.classdiagram.figures.TemplateParameterFigure;
import org.topcased.modeler.uml.classdiagram.preferences.ClassDiagramPreferenceConstants;
import org.topcased.modeler.utils.Utils;

/**
 * The TemplateParameter object controller
 * 
 * @generated
 */
public class TemplateParameterEditPart extends ElementEditPart
{
    /**
     * Constructor
     *
     * @param obj the graph node
     * @generated
     */
    public TemplateParameterEditPart(GraphNode obj)
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
                return new TemplateParameterRestoreConnectionCommand(getHost());
            }
        });
        installEditPolicy(ModelerEditPolicyConstants.RESIZABLE_EDITPOLICY, null);
        installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, null);
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     * @generated
     */
    protected IFigure createFigure()
    {

        return new TemplateParameterFigure();
    }
    
    /**
     * @see org.topcased.modeler.edit.EMFGraphNodeEditPart#refreshHeaderLabel()
     */
    @Override
    protected void refreshHeaderLabel()
    {
        ComposedLabel lbl = (ComposedLabel) ((ILabelFigure) getFigure()).getLabel();
        TemplateParameter templateParameter = (TemplateParameter) Utils.getElement(getGraphNode());
        ParameterableElement parameteredElement = templateParameter.getParameteredElement();
        if (parameteredElement instanceof NamedElement)
        {
            lbl.setMain(((NamedElement)parameteredElement).getName());            
        }
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
                ClassDiagramPreferenceConstants.TEMPLATEPARAMETER_DEFAULT_BACKGROUND_COLOR);
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
                ClassDiagramPreferenceConstants.TEMPLATEPARAMETER_DEFAULT_FOREGROUND_COLOR);
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
                ClassDiagramPreferenceConstants.TEMPLATEPARAMETER_DEFAULT_FONT);
        if (preferenceFont.length() != 0)
        {
            return Utils.getFont(new FontData(preferenceFont));
        }
        return null;

    }

}