/*****************************************************************************
 * Copyright (c) 2008 Atos Origin
 *
 * Contributors:
 *  Frédéric Barraillé; [(Atos Origin)] [frederic.barraille@atosorigin.com]
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *****************************************************************************/
package org.topcased.modeler.uml.alldiagram.edit;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.commands.Command;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ValueSpecification;
import org.topcased.draw2d.figures.ILabel;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.policies.ResizableEditPolicy;
import org.topcased.modeler.edit.policies.RestoreEditPolicy;
import org.topcased.modeler.requests.RestoreConnectionsRequest;
import org.topcased.modeler.uml.UMLLabel;
import org.topcased.modeler.uml.alldiagram.commands.ConstraintRestoreConnectionCommand;
import org.topcased.modeler.uml.alldiagram.figures.ConstraintNodeFigure;
import org.topcased.modeler.uml.alldiagram.preferences.AllDiagramPreferenceConstants;
import org.topcased.modeler.utils.Utils;

/**
 * The Constraint object controller
 * 
 * @generated
 */
public class ConstraintEditPart extends NamedElementEditPart
{
    /**
     * Constructor
     * 
     * @param obj the graph node
     * @generated NOT
     */
    public ConstraintEditPart(GraphNode obj)
    {
        super(obj);
    }

    /**
     * Creates edit policies and associates these with roles
     * 
     * @generated NOT
     */
    @Override
    protected void createEditPolicies()
    {
        super.createEditPolicies();

        installEditPolicy(ModelerEditPolicyConstants.RESTORE_EDITPOLICY, new RestoreEditPolicy()
        {
            @Override
            protected Command getRestoreConnectionsCommand(RestoreConnectionsRequest request)
            {
                return new ConstraintRestoreConnectionCommand(getHost());
            }
        });
        installEditPolicy(ModelerEditPolicyConstants.RESIZABLE_EDITPOLICY, new ResizableEditPolicy());
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     * @generated
     */
    @Override
    protected IFigure createFigure()
    {
        return new ConstraintNodeFigure();
    }

    /**
     * 
     */
    @Override
    protected void handleModelChanged(Notification msg)
    {
        super.handleModelChanged(msg);
        Constraint constraint = (Constraint) getEObject();
        if (constraint.getSpecification() != null)
        {
            listenValues(constraint);
        }
        else
        {
            unlistenValues(constraint);
        }
    }

    /**
     * Always editable
     * 
     * @see org.topcased.modeler.edit.GraphNodeEditPart#directEditHitTest(org.eclipse.draw2d.geometry.Point)
     */
    @Override
    protected boolean directEditHitTest(Point requestLoc)
    {
        return true;
    }

    /**
     * 
     */
    @Override
    protected void refreshHeaderLabel()
    {
        Constraint constraint = (Constraint) getEObject();
        String name = constraint.getName();
        ValueSpecification valueSpecification = constraint.getSpecification();
        String spec = null;
        String stereotype = UMLLabel.getStereotypesNotation((Element) getEObject(), getPreferenceStore());
        if (valueSpecification == null)
        {
            spec = "";
        }
        else
        {
            spec = valueSpecification.stringValue();
        }
        if (name == null)
        {
            name = "";
        }
        if (spec == null)
        {
            spec = "";
        }
            ((ILabel) getFigure()).setText(stereotype + "{" + name + " : " + spec + "}");
    }

    /**
     * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultBackgroundColor()
     * 
     * @generated
     */
    @Override
    protected Color getPreferenceDefaultBackgroundColor()
    {
        String backgroundColor = getPreferenceStore().getString(AllDiagramPreferenceConstants.CONSTRAINT_DEFAULT_BACKGROUND_COLOR);
        if (backgroundColor.length() != 0)
        {
            return Utils.getColor(backgroundColor);
        }
        return null;
    }

    /**
     * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultForegroundColor()
     * 
     * @generated
     */
    @Override
    protected Color getPreferenceDefaultForegroundColor()
    {
        String foregroundColor = getPreferenceStore().getString(AllDiagramPreferenceConstants.CONSTRAINT_DEFAULT_FOREGROUND_COLOR);
        if (foregroundColor.length() != 0)
        {
            return Utils.getColor(foregroundColor);
        }
        return null;
    }

    /**
     * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultFont()
     * 
     * @generated
     */
    @Override
    protected Font getPreferenceDefaultFont()
    {
        String preferenceFont = getPreferenceStore().getString(AllDiagramPreferenceConstants.CONSTRAINT_DEFAULT_FONT);
        if (preferenceFont.length() != 0)
        {
            return Utils.getFont(new FontData(preferenceFont));
        }
        return null;

    }
    /**
     * Listen to the bounds of the property
     * 
     * @param property the model object
     */
    private void listenValues(Constraint constraint)
    {
        // Only listen to object that are not yet listened
        if (constraint.getSpecification() != null && !constraint.getSpecification().eAdapters().contains(getModelListener()))
        {
            constraint.getSpecification().eAdapters().add(getModelListener());
        }
    }

    /**
     * Stop listening to the bounds of the property
     * 
     * @param property the model object
     */
    private void unlistenValues(Constraint constraint)
    {
        if (constraint.getSpecification() != null)
        {
            constraint.getSpecification().eAdapters().remove(getModelListener());
        }
    }
}