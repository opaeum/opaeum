/*******************************************************************************
 * Copyright (c) 2006 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.classdiagram.edit;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.uml2.uml.Signal;
import org.topcased.draw2d.figures.ComposedLabel;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.policies.LabelDirectEditPolicy;
import org.topcased.modeler.edit.policies.ResizableEditPolicy;
import org.topcased.modeler.edit.policies.RestoreEditPolicy;
import org.topcased.modeler.requests.RestoreConnectionsRequest;
import org.topcased.modeler.uml.UMLLabel;
import org.topcased.modeler.uml.classdiagram.commands.SignalRestoreConnectionCommand;
import org.topcased.modeler.uml.classdiagram.figures.SignalFigure;
import org.topcased.modeler.uml.classdiagram.policies.SignalLayoutEditPolicy;
import org.topcased.modeler.uml.classdiagram.preferences.ClassDiagramPreferenceConstants;
import org.topcased.modeler.utils.Utils;

/**
 * The Signal object controller
 * 
 * @generated
 */
public class SignalEditPart extends ClassifierEditPart
{
    /**
     * Constructor
     * 
     * @param obj the graph node
     * @generated
     */
    public SignalEditPart(GraphNode obj)
    {
        super(obj);
    }

    /**
     * Creates edit policies and associates these with roles
     * 
     * @generated NOT
     */
    protected void createEditPolicies()
    {
        super.createEditPolicies();

        installEditPolicy(ModelerEditPolicyConstants.RESTORE_EDITPOLICY, new RestoreEditPolicy()
        {
            protected Command getRestoreConnectionsCommand(RestoreConnectionsRequest request)
            {
                return new SignalRestoreConnectionCommand(getHost());
            }
        });

        ResizableEditPolicy resizableEditPolicy = new ResizableEditPolicy();
        resizableEditPolicy.setResizeDirections(PositionConstants.EAST_WEST);
        installEditPolicy(ModelerEditPolicyConstants.RESIZABLE_EDITPOLICY, resizableEditPolicy);

        installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new LabelDirectEditPolicy());
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new SignalLayoutEditPolicy());
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     * @generated
     */
    protected IFigure createFigure()
    {

        return new SignalFigure();
    }

    /**
     * Set the name of the interface with the stereotype and the "from"
     * 
     * @see org.topcased.modeler.edit.EMFGraphNodeEditPart#refreshHeaderLabel()
     * @generated NOT
     */
    @Override
    protected void refreshHeaderLabel()
    {
        SignalFigure fig = (SignalFigure) getFigure();
        ComposedLabel lbl = (ComposedLabel) fig.getLabel();
        Signal signal = (Signal) Utils.getElement(getGraphNode());

        String stereotype = UMLLabel.getStereotypesNotation(signal, getPreferenceStore());
        if (stereotype != null && stereotype.length() != 0)
        {
            stereotype += " ";
        }
        lbl.setPrefix(stereotype + "<<signal>>");

        if (signal.getName() != null)
        {
            lbl.setMain(signal.getName());
        }
    }

    /**
     * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultBackgroundColor()
     * @generated
     */
    protected Color getPreferenceDefaultBackgroundColor()
    {
        String backgroundColor = getPreferenceStore().getString(ClassDiagramPreferenceConstants.SIGNAL_DEFAULT_BACKGROUND_COLOR);
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
        String foregroundColor = getPreferenceStore().getString(ClassDiagramPreferenceConstants.SIGNAL_DEFAULT_FOREGROUND_COLOR);
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
        String preferenceFont = getPreferenceStore().getString(ClassDiagramPreferenceConstants.SIGNAL_DEFAULT_FONT);
        if (preferenceFont.length() != 0)
        {
            return Utils.getFont(new FontData(preferenceFont));
        }
        return null;

    }

}