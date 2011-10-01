/***********************************************************************
 * Copyright (c) 2008 Anyware Technologies
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Anyware Technologies - initial API and implementation
 **********************************************************************/
package org.topcased.modeler.uml.profilediagram.edit;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.uml2.uml.Extension;
import org.topcased.draw2d.figures.Label;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.EdgeObject;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.edit.EMFGraphEdgeEditPart;
import org.topcased.modeler.figures.IEdgeObjectFigure;
import org.topcased.modeler.uml.profilediagram.ProfileEdgeObjectConstants;
import org.topcased.modeler.uml.profilediagram.figures.ExtensionFigure;
import org.topcased.modeler.uml.profilediagram.policies.ExtensionEdgeObjectUVEditPolicy;
import org.topcased.modeler.uml.profilediagram.preferences.ProfileDiagramPreferenceConstants;
import org.topcased.modeler.utils.Utils;

/**
 * Extension controller
 * 
 * @generated
 */
public class ExtensionEditPart extends EMFGraphEdgeEditPart
{

    /**
     * Constructor
     * 
     * @param model the graph object
     * @generated
     */
    public ExtensionEditPart(GraphEdge model)
    {
        super(model);
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     * @generated
     */
    protected void createEditPolicies()
    {
        super.createEditPolicies();

        installEditPolicy(ModelerEditPolicyConstants.EDGE_OBJECTS_UV_EDITPOLICY, new ExtensionEdgeObjectUVEditPolicy());

    }

    /**
     * @return the Figure
     * @generated
     */
    protected IFigure createFigure()
    {
        ExtensionFigure connection = new ExtensionFigure();

        createTargetDecoration(connection);

        return connection;
    }

    /**
     * @param connection the PolylineConnection
     * @generated NOT
     */
    private void createTargetDecoration(PolylineConnection connection)
    {

        PolygonDecoration decoration = new PolygonDecoration();
        decoration.setScale(14, 6);
        connection.setTargetDecoration(decoration);

    }

    /**
     * @see org.topcased.modeler.edit.GraphEdgeEditPart#getEdgeObjectFigure(org.topcased.modeler.di.model.EdgeObject)
     * @generated
     */
    public IEdgeObjectFigure getEdgeObjectFigure(EdgeObject edgeObject)
    {
        if (ProfileEdgeObjectConstants.REQUIREDFIELD_EDGE_OBJECT_ID.equals(edgeObject.getId()))
        {
            return ((ExtensionFigure) getFigure()).getRequiredFieldEdgeObjectFigure();
        }
        return null;
    }

    /**
     * @see org.topcased.modeler.edit.GraphEdgeEditPart#refreshEdgeObjects()
     * @generated
     */
    protected void refreshEdgeObjects()
    {
        super.refreshEdgeObjects();
        updateRequiredFieldLabel();
    }

    /**
     * Update the requiredField Label
     * 
     * @generated
     */
    private void updateRequiredFieldLabel()
    {
        ((Label) ((ExtensionFigure) getFigure()).getRequiredFieldEdgeObjectFigure()).setText(((Extension) getEObject()).isRequired() ? "{required}"
                : "");
    }

    /**
     * @see org.topcased.modeler.edit.GraphEdgeEditPart#getPreferenceDefaultRouter()
     * 
     * @generated
     */
    protected String getPreferenceDefaultRouter()
    {
        return getPreferenceStore().getString(ProfileDiagramPreferenceConstants.EXTENSION_EDGE_DEFAULT_ROUTER);
    }

    /**
     * @see org.topcased.modeler.edit.GraphEdgeEditPart#getPreferenceDefaultForegroundColor()
     * 
     * @generated
     */
    protected Color getPreferenceDefaultForegroundColor()
    {
        String preferenceForeground = getPreferenceStore().getString(
                ProfileDiagramPreferenceConstants.EXTENSION_EDGE_DEFAULT_FOREGROUND_COLOR);
        if (preferenceForeground.length() != 0)
        {
            return Utils.getColor(preferenceForeground);
        }
        return null;

    }

    /**
     * @see org.topcased.modeler.edit.GraphEdgeEditPart#getPreferenceDefaultFont()
     * 
     * @generated
     */
    protected Font getPreferenceDefaultFont()
    {
        String preferenceFont = getPreferenceStore().getString(
                ProfileDiagramPreferenceConstants.EXTENSION_EDGE_DEFAULT_FONT);
        if (preferenceFont.length() != 0)
        {
            return Utils.getFont(new FontData(preferenceFont));
        }
        return null;
    }
}