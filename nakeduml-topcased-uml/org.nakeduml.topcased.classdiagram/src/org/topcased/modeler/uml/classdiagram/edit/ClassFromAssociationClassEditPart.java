/*******************************************************************************
 * Copyright (c) 2006 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.classdiagram.edit;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.topcased.draw2d.figures.ComposedLabel;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.uml.UMLLabel;
import org.topcased.modeler.uml.UMLTools;
import org.topcased.modeler.uml.classdiagram.commands.MoveClassFromAssociationClassCommand;
import org.topcased.modeler.uml.classdiagram.edit.dragtrackers.SelectClassFromAssociationClassDragTracker;
import org.topcased.modeler.uml.classdiagram.figures.ClassFigure;
import org.topcased.modeler.uml.classdiagram.preferences.ClassDiagramPreferenceConstants;
import org.topcased.modeler.utils.Utils;

/**
 * The Class element associated with an AssociationClass object controller.
 * 
 * @author <a href="mailto:thomas.szadel@atosorigin.com">Thomas Szadel</a>
 */
public class ClassFromAssociationClassEditPart extends ClassEditPart
{
    /**
     * Constructor
     * 
     * @param obj the graph node
     */
    public ClassFromAssociationClassEditPart(GraphNode obj)
    {
        super(obj);
    }

    /**
     * @see org.topcased.modeler.uml.classdiagram.edit.ClassEditPart#createEditPolicies()
     */
    @Override
    protected void createEditPolicies()
    {
        super.createEditPolicies();
        // Allow to move the class
        ResizableEditPolicy resizableEditPolicy = new ResizableEditPolicy()
        {
            @Override
            protected Command getMoveCommand(ChangeBoundsRequest request)
            {
                Point location = new Point(getGraphNode().getPosition());
                location.x += request.getMoveDelta().x;
                location.y += request.getMoveDelta().y;
                return new MoveClassFromAssociationClassCommand(getGraphNode(), location);
            }

        };
        resizableEditPolicy.setResizeDirections(PositionConstants.EAST_WEST);
        installEditPolicy(RequestConstants.REQ_MOVE, resizableEditPolicy);
    }

    /**
     * Set the name of Class with the stereotypes and the "from"
     * 
     * @see org.topcased.modeler.edit.EMFGraphNodeEditPart#refreshHeaderLabel()
     */
    @Override
    protected void refreshHeaderLabel()
    {
        ClassFigure fig = (ClassFigure) getFigure();
        ComposedLabel lbl = (ComposedLabel) fig.getLabel();
        Class clazz = (Class) Utils.getElement(getGraphNode());

        lbl.setPrefix(UMLLabel.getStereotypesNotation(clazz, getPreferenceStore()));

        if (clazz.getName() != null)
        {
            lbl.setMain(clazz.getName());
        }

        AssociationClassEditPart parentPart = (AssociationClassEditPart) getParent();
        // Get the source (normally, it is a classpart)
        if (parentPart.getSource() != null && parentPart.getSource().getParent() != null)
        {
            EditPart sourcePart = parentPart.getSource().getParent();
            lbl.setSuffix(UMLTools.getFromPackageNotation(clazz, (Element) Utils.getElement((GraphElement) sourcePart.getModel())));
        }
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#getDragTracker(org.eclipse.gef.Request)
     */
    @Override
    public DragTracker getDragTracker(Request request)
    {
        return new SelectClassFromAssociationClassDragTracker(this);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     * @generated
     */
    @Override
    protected IFigure createFigure()
    {
        return new ClassFigure();
    }

    /**
     * @see org.topcased.modeler.edit.GraphNodeEditPart#refreshConstraints()
     */
    @Override
    protected void refreshConstraints()
    {
        // Needed to update the location (otherwise, do not work...)
        getFigure().setLocation(getGraphNode().getPosition());
        super.refreshConstraints();
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
    @Override
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
    @Override
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