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
package org.topcased.modeler.uml.alldiagram.edit;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.topcased.draw2d.figures.Label;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.EdgeObject;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.edit.EMFGraphEdgeEditPart;
import org.topcased.modeler.edit.policies.EdgeObjectOffsetEditPolicy;
import org.topcased.modeler.figures.IEdgeObjectFigure;
import org.topcased.modeler.uml.alldiagram.figures.UsageFigure;
import org.topcased.modeler.uml.alldiagram.preferences.AllDiagramPreferenceConstants;
import org.topcased.modeler.uml.classdiagram.ClassEdgeObjectConstants;
import org.topcased.modeler.utils.Utils;

/**
 * Usage controller <br>
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class UsageEditPart extends EMFGraphEdgeEditPart
{
    /**
     * Constructor <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param model the graph object
     * @generated
     */
    public UsageEditPart(GraphEdge model)
    {
        super(model);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     * @generated
     */
    protected void createEditPolicies()
    {
        super.createEditPolicies();

        installEditPolicy(ModelerEditPolicyConstants.EDGE_OBJECTS_OFFSET_EDITPOLICY, new EdgeObjectOffsetEditPolicy());

    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the Figure
     * @generated
     */
    protected IFigure createFigure()
    {
        UsageFigure connection = new UsageFigure();

        createTargetDecoration(connection);

        return connection;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param connection the PolylineConnection
     * @generated
     */
    private void createTargetDecoration(PolylineConnection connection)
    {

        PolylineDecoration decoration = new PolylineDecoration();
        decoration.setScale(10, 5);
        connection.setTargetDecoration(decoration);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.edit.GraphEdgeEditPart#getEdgeObjectFigure(org.topcased.modeler.di.model.EdgeObject)
     * @generated
     */
    public IEdgeObjectFigure getEdgeObjectFigure(EdgeObject edgeObject)
    {
        if (ClassEdgeObjectConstants.MIDDLENAME_EDGE_OBJECT_ID.equals(edgeObject.getId()))
        {
            return ((UsageFigure) getFigure()).getMiddleNameEdgeObjectFigure();
        }
        return null;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.edit.GraphEdgeEditPart#refreshEdgeObjects()
     * @generated
     */
    protected void refreshEdgeObjects()
    {
        super.refreshEdgeObjects();
        updateMiddleNameLabel();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> Update the stereotype Label
     * 
     * @generated NOT
     */
    private void updateMiddleNameLabel()
    {
        ((Label) ((UsageFigure) getFigure()).getMiddleNameEdgeObjectFigure()).setText("<<use>>");
    }

    /**
     * @see org.topcased.modeler.edit.GraphEdgeEditPart#getPreferenceDefaultRouter()
     * 
     * @generated
     */
    protected String getPreferenceDefaultRouter()
    {
        return getPreferenceStore().getString(AllDiagramPreferenceConstants.USAGE_EDGE_DEFAULT_ROUTER);
    }

    /**
     * @see org.topcased.modeler.edit.GraphEdgeEditPart#getPreferenceDefaultForegroundColor()
     * 
     * @generated
     */
    protected Color getPreferenceDefaultForegroundColor()
    {
        String foregroundColor = getPreferenceStore().getString(AllDiagramPreferenceConstants.USAGE_EDGE_DEFAULT_FOREGROUND_COLOR);
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
    protected Font getPreferenceDefaultFont()
    {
        String font = getPreferenceStore().getString(AllDiagramPreferenceConstants.USAGE_EDGE_DEFAULT_FONT);
        if (font.length() != 0)
        {
            return Utils.getFont(new FontData(font));
        }
        return null;

    }

}