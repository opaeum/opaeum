/*******************************************************************************
 * Copyright (c) 2006, 2008 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.classdiagram.edit;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.topcased.modeler.di.model.DiagramElement;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.uml.classdiagram.figures.AssociationFigure;
import org.topcased.modeler.uml.classdiagram.preferences.ClassDiagramPreferenceConstants;
import org.topcased.modeler.utils.Utils;

/**
 * AssociationClass controller <br>
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class AssociationClassEditPart extends AssociationEditPart
{

    /** The edit part of the class. */
    private ClassFromAssociationClassEditPart classEditPart;

    /** The association between the class and that association. */
    private AssociationFromAssociationClassEditPart associationEditPart;

    /**
     * Constructor <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param model the graph object
     * @generated
     */
    public AssociationClassEditPart(GraphEdge model)
    {
        super(model);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the Figure
     * @generated
     */
    protected IFigure createFigure()
    {
        return new AssociationFigure();
    }

    /**
     * @see org.topcased.modeler.edit.GraphEdgeEditPart#refreshColors()
     */
    @Override
    protected void refreshColors()
    {
        super.refreshColors();
        // Forward the color to the connection line and the class
        if (associationEditPart != null)
        {
            associationEditPart.getFigure().setForegroundColor(getFigure().getForegroundColor());
        }
    }

    /**
     * @see org.topcased.modeler.uml.classdiagram.edit.AssociationEditPart#activate()
     */
    @Override
    public void activate()
    {
        super.activate();

        if (associationEditPart == null)
        {
            associationEditPart = new AssociationFromAssociationClassEditPart();
            // As the association is not really bounded to a model, just add it in the
            // visual components
            IFigure assocFigure = associationEditPart.getFigure();
            assocFigure.setParent(getFigure());
            getFigure().add(assocFigure);
            assocFigure.setForegroundColor(getFigure().getForegroundColor());

            associationEditPart.setSource(this);
            associationEditPart.setTarget(classEditPart);
        }

        // If it is the first time that we show the class, we have to locate it
        // In that case, the target is not yet set
        // FIXME improve placement of the class
        Point position = ((GraphElement) classEditPart.getModel()).getPosition();
        if (position.x == -1 && position.y == -1)
        {
            int offset = 50;
            Point middlePoint = new Point(((GraphElement) getSource().getModel()).getPosition());
            middlePoint.x += offset;
            middlePoint.y += offset;
            ((GraphElement) classEditPart.getModel()).setPosition(middlePoint);
        }
    }

    /**
     * @see org.topcased.modeler.uml.classdiagram.edit.AssociationEditPart#deactivate()
     */
    @Override
    public void deactivate()
    {

        if (associationEditPart != null)
        {
            IFigure parent = associationEditPart.getFigure().getParent();
            if (parent != null)
            {
                parent.remove(associationEditPart.getFigure());
            }
            associationEditPart.deactivate();
        }
        super.deactivate();
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createChild(java.lang.Object)
     */
    @Override
    protected EditPart createChild(Object model)
    {
        EditPart editPart = super.createChild(model);
        if (editPart instanceof ClassFromAssociationClassEditPart)
        {
            classEditPart = (ClassFromAssociationClassEditPart) editPart;
        }
        return editPart;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#getModelChildren()
     * @generated
     */
    protected List< ? > getModelChildren()
    {
        List<DiagramElement> graphNodeChildren = new ArrayList<DiagramElement>();
        for (DiagramElement elt : getGraphEdge().getContained())
        {
            if (elt instanceof GraphNode)
            {
                graphNodeChildren.add(elt);
            }
        }
        return graphNodeChildren;
    }

    /**
     * @see org.topcased.modeler.edit.GraphEdgeEditPart#getPreferenceDefaultRouter()
     * 
     * @generated
     */
    protected String getPreferenceDefaultRouter()
    {
        return getPreferenceStore().getString(ClassDiagramPreferenceConstants.ASSOCIATIONCLASS_EDGE_DEFAULT_ROUTER);
    }

    /**
     * @see org.topcased.modeler.edit.GraphEdgeEditPart#getPreferenceDefaultForegroundColor()
     * 
     * @generated
     */
    protected Color getPreferenceDefaultForegroundColor()
    {
        String preferenceForeground = getPreferenceStore().getString(ClassDiagramPreferenceConstants.ASSOCIATIONCLASS_EDGE_DEFAULT_FOREGROUND_COLOR);
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
        String preferenceFont = getPreferenceStore().getString(ClassDiagramPreferenceConstants.ASSOCIATIONCLASS_EDGE_DEFAULT_FONT);
        if (preferenceFont.length() != 0)
        {
            return Utils.getFont(new FontData(preferenceFont));
        }
        return null;
    }

}