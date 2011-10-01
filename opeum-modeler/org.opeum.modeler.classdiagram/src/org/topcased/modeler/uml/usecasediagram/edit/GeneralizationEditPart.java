/*******************************************************************************
 * Copyright (c) 2006 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.usecasediagram.edit;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.uml2.uml.Element;
import org.topcased.draw2d.figures.Label;
import org.topcased.modeler.ModelerColorConstants;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.EdgeObject;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.edit.EMFGraphEdgeEditPart;
import org.topcased.modeler.edit.policies.EdgeObjectOffsetEditPolicy;
import org.topcased.modeler.figures.IEdgeObjectFigure;
import org.topcased.modeler.uml.UMLLabel;
import org.topcased.modeler.uml.usecasediagram.UseCaseEdgeObjectConstants;
import org.topcased.modeler.uml.usecasediagram.figures.GeneralizationFigure;
import org.topcased.modeler.uml.usecasediagram.preferences.UseCaseDiagramPreferenceConstants;
import org.topcased.modeler.utils.Utils;

/**
 * Generalization controller <br>
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class GeneralizationEditPart extends EMFGraphEdgeEditPart
{

    /**
     * Constructor <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param model the graph object
     * @generated
     */
    public GeneralizationEditPart(GraphEdge model)
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

        installEditPolicy(ModelerEditPolicyConstants.EDGE_OBJECTS_OFFSET_EDITPOLICY, new EdgeObjectOffsetEditPolicy());
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
        GeneralizationFigure connection = new GeneralizationFigure();
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

        PolygonDecoration decoration = new PolygonDecoration();
        decoration.setScale(14, 6);
        decoration.setBackgroundColor(ModelerColorConstants.white);
        connection.setTargetDecoration(decoration);

    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.edit.GraphEdgeEditPart#getEdgeObjectFigure(org.topcased.modeler.di.model.EdgeObject)
     * @generated
     */
    @Override
    public IEdgeObjectFigure getEdgeObjectFigure(EdgeObject edgeObject)
    {
        if (UseCaseEdgeObjectConstants.STEREOTYPE_EDGE_OBJECT_ID.equals(edgeObject.getId()))
        {
            return ((GeneralizationFigure) getFigure()).getStereotypeEdgeObjectFigure();
        }
        return null;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.edit.GraphEdgeEditPart#refreshEdgeObjects()
     * @generated
     */
    @Override
    protected void refreshEdgeObjects()
    {
        super.refreshEdgeObjects();
        updateStereotypeLabel();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> Update the stereotype Label
     * 
     * @generated NOT
     */
    private void updateStereotypeLabel()
    {
        ((Label) ((GeneralizationFigure) getFigure()).getStereotypeEdgeObjectFigure()).setText(UMLLabel.getStereotypesNotation((Element) getEObject(), getPreferenceStore()));
    }

    /**
     * @see org.topcased.modeler.edit.GraphEdgeEditPart#getPreferenceDefaultRouter()
     * 
     * @generated
     */
    @Override
    protected String getPreferenceDefaultRouter()
    {
        return getPreferenceStore().getString(UseCaseDiagramPreferenceConstants.GENERALIZATION_EDGE_DEFAULT_ROUTER);
    }

    /**
     * @see org.topcased.modeler.edit.GraphEdgeEditPart#getPreferenceDefaultForegroundColor()
     * 
     * @generated
     */
    @Override
    protected Color getPreferenceDefaultForegroundColor()
    {
        String preferenceForeground = getPreferenceStore().getString(UseCaseDiagramPreferenceConstants.GENERALIZATION_EDGE_DEFAULT_FOREGROUND_COLOR);
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
    @Override
    protected Font getPreferenceDefaultFont()
    {
        String preferenceFont = getPreferenceStore().getString(UseCaseDiagramPreferenceConstants.GENERALIZATION_EDGE_DEFAULT_FONT);
        if (preferenceFont.length() != 0)
        {
            return Utils.getFont(new FontData(preferenceFont));
        }
        return null;
    }

}