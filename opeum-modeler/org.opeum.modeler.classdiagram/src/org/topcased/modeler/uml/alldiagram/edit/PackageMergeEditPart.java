/*******************************************************************************
 * Copyright (c) 2006, 2008 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
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
import org.topcased.modeler.uml.alldiagram.AllEdgeObjectConstants;
import org.topcased.modeler.uml.alldiagram.figures.PackageMergeFigure;
import org.topcased.modeler.uml.alldiagram.preferences.AllDiagramPreferenceConstants;
import org.topcased.modeler.utils.Utils;

/**
 * PackageMerge controller <br>
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class PackageMergeEditPart extends EMFGraphEdgeEditPart
{

    /**
     * Constructor <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param model the graph object
     * @generated
     */
    public PackageMergeEditPart(GraphEdge model)
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
        PackageMergeFigure connection = new PackageMergeFigure();

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
        if (AllEdgeObjectConstants.MERGE_EDGE_OBJECT_ID.equals(edgeObject.getId()))
        {
            return ((PackageMergeFigure) getFigure()).getMergeEdgeObjectFigure();
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
        updateMergeLabel();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> Update the merge Label
     * 
     * @generated NOT
     */
    private void updateMergeLabel()
    {
        ((Label) ((PackageMergeFigure) getFigure()).getMergeEdgeObjectFigure()).setText("<<merge>>");
    }

    /**
     * @see org.topcased.modeler.edit.GraphEdgeEditPart#getPreferenceDefaultRouter()
     * 
     * @generated
     */
    protected String getPreferenceDefaultRouter()
    {
        return getPreferenceStore().getString(AllDiagramPreferenceConstants.PACKAGEMERGE_EDGE_DEFAULT_ROUTER);
    }

    /**
     * @see org.topcased.modeler.edit.GraphEdgeEditPart#getPreferenceDefaultForegroundColor()
     * 
     * @generated
     */
    protected Color getPreferenceDefaultForegroundColor()
    {
        String preferenceForeground = getPreferenceStore().getString(AllDiagramPreferenceConstants.PACKAGEMERGE_EDGE_DEFAULT_FOREGROUND_COLOR);
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
        String preferenceFont = getPreferenceStore().getString(AllDiagramPreferenceConstants.PACKAGEMERGE_EDGE_DEFAULT_FONT);
        if (preferenceFont.length() != 0)
        {
            return Utils.getFont(new FontData(preferenceFont));
        }
        return null;
    }

}