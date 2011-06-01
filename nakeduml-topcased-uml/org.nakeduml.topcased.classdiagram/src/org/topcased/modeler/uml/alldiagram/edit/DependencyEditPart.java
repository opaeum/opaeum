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
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Element;
import org.topcased.draw2d.figures.Label;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.EdgeObject;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.edit.EMFGraphEdgeEditPart;
import org.topcased.modeler.edit.policies.EdgeObjectOffsetEditPolicy;
import org.topcased.modeler.figures.IEdgeObjectFigure;
import org.topcased.modeler.uml.UMLLabel;
import org.topcased.modeler.uml.alldiagram.AllEdgeObjectConstants;
import org.topcased.modeler.uml.alldiagram.figures.DependencyFigure;
import org.topcased.modeler.uml.alldiagram.preferences.AllDiagramPreferenceConstants;
import org.topcased.modeler.utils.Utils;

/**
 * Dependency controller
 */
public class DependencyEditPart extends EMFGraphEdgeEditPart
{
    /**
     * Constructor
     * 
     * @param model the graph object
     */
    public DependencyEditPart(GraphEdge model)
    {
        super(model);
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    @Override
    protected void createEditPolicies()
    {
        super.createEditPolicies();

        installEditPolicy(ModelerEditPolicyConstants.EDGE_OBJECTS_OFFSET_EDITPOLICY, new EdgeObjectOffsetEditPolicy());

    }

    /**
     * @return the Figure
     */
    @Override
    protected IFigure createFigure()
    {
        DependencyFigure connection = new DependencyFigure();

        createTargetDecoration(connection);

        return connection;
    }

    /**
     * @param connection the PolylineConnection
     */
    private void createTargetDecoration(PolylineConnection connection)
    {

        PolylineDecoration decoration = new PolylineDecoration();
        decoration.setScale(10, 5);
        connection.setTargetDecoration(decoration);

    }

    /**
     * @see org.topcased.modeler.edit.GraphEdgeEditPart#getEdgeObjectFigure(org.topcased.modeler.di.model.EdgeObject)
     */
    @Override
    public IEdgeObjectFigure getEdgeObjectFigure(EdgeObject edgeObject)
    {
        if (AllEdgeObjectConstants.STEREOTYPE_EDGE_OBJECT_ID.equals(edgeObject.getId()))
        {
            return ((DependencyFigure) getFigure()).getStereotypeEdgeObjectFigure();
        }
        if (AllEdgeObjectConstants.MIDDLENAME_EDGE_OBJECT_ID.equals(edgeObject.getId()))
        {
            return ((DependencyFigure) getFigure()).getNameEdgeObjectFigure();
        }
        return null;
    }

    /**
     * @see org.topcased.modeler.edit.GraphEdgeEditPart#refreshEdgeObjects()
     */
    @Override
    protected void refreshEdgeObjects()
    {
        super.refreshEdgeObjects();
        updateStereotypeLabel();
        updateNameLabel();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> Update the stereotype Label
     */
    private void updateStereotypeLabel()
    {
        ((Label) ((DependencyFigure) getFigure()).getStereotypeEdgeObjectFigure()).setText(UMLLabel.getStereotypesNotation((Element) getEObject(), getPreferenceStore()));
    }

    /**
     * 
     */
    protected void updateNameLabel()
    {
        Dependency dependency = (Dependency) getEObject();
        if (dependency.getName() != null)
        {
            String dependencyName = dependency.getName();
            ((Label) ((DependencyFigure) getFigure()).getNameEdgeObjectFigure()).setText(dependencyName);
        }       
    }

    /**
     * @see org.topcased.modeler.edit.GraphEdgeEditPart#getPreferenceDefaultRouter()
     */
    @Override
    protected String getPreferenceDefaultRouter()
    {
        return getPreferenceStore().getString(AllDiagramPreferenceConstants.DEPENDENCY_EDGE_DEFAULT_ROUTER);
    }

    /**
     * @see org.topcased.modeler.edit.GraphEdgeEditPart#getPreferenceDefaultForegroundColor()
     */
    @Override
    protected Color getPreferenceDefaultForegroundColor()
    {
        String preferenceForeground = getPreferenceStore().getString(AllDiagramPreferenceConstants.DEPENDENCY_EDGE_DEFAULT_FOREGROUND_COLOR);
        if (preferenceForeground.length() != 0)
        {
            return Utils.getColor(preferenceForeground);
        }
        return null;

    }

    /**
     * @see org.topcased.modeler.edit.GraphEdgeEditPart#getPreferenceDefaultFont()
     */
    @Override
    protected Font getPreferenceDefaultFont()
    {
        String preferenceFont = getPreferenceStore().getString(AllDiagramPreferenceConstants.DEPENDENCY_EDGE_DEFAULT_FONT);
        if (preferenceFont.length() != 0)
        {
            return Utils.getFont(new FontData(preferenceFont));
        }
        return null;
    }

}