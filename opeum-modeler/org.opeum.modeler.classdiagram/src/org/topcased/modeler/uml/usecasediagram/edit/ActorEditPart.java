/*******************************************************************************
 * Copyright (c) 2005 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: David Sciamma (Anyware Technologies), Mathieu Garcia (Anyware
 * Technologies), Jacques Lescot (Anyware Technologies), Thomas Friol (Anyware
 * Technologies), Nicolas Lalev�e (Anyware Technologies) - initial API and
 * implementation
 ******************************************************************************/

package org.topcased.modeler.uml.usecasediagram.edit;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.topcased.draw2d.figures.ComposedLabel;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.policies.LabelDirectEditPolicy;
import org.topcased.modeler.edit.policies.ResizableEditPolicy;
import org.topcased.modeler.edit.policies.RestoreEditPolicy;
import org.topcased.modeler.requests.RestoreConnectionsRequest;
import org.topcased.modeler.uml.UMLLabel;
import org.topcased.modeler.uml.UMLTools;
import org.topcased.modeler.uml.usecasediagram.commands.ActorRestoreConnectionCommand;
import org.topcased.modeler.uml.usecasediagram.figures.ActorFigure;
import org.topcased.modeler.uml.usecasediagram.preferences.UseCaseDiagramPreferenceConstants;
import org.topcased.modeler.utils.Utils;

/**
 * The Actor object controller <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class ActorEditPart extends ClassifierEditPart
{
    /**
     * Constructor <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param obj the graph node
     * @generated
     */
    public ActorEditPart(GraphNode obj)
    {
        super(obj);
    }

    /**
     * Creates edit policies and associates these with roles <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
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
                return new ActorRestoreConnectionCommand(getHost());
            }
        });

        installEditPolicy(ModelerEditPolicyConstants.RESIZABLE_EDITPOLICY, new ResizableEditPolicy());

        installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new LabelDirectEditPolicy());
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

        return new ActorFigure();
    }

    /**
     * Set the name of the actor, with its stereotype and its "from"
     * 
     * @see org.topcased.modeler.edit.EMFGraphNodeEditPart#refreshHeaderLabel()
     */
    @Override
    protected void refreshHeaderLabel()
    {
        Actor actor = (Actor) Utils.getElement(getGraphNode());

        ComposedLabel lbl = (ComposedLabel) getLabel();

        lbl.setPrefix(UMLLabel.getStereotypesNotation(actor, getPreferenceStore()));

        lbl.setMain(actor.getName());

        lbl.setSuffix(UMLTools.getFromPackageNotation(actor, (Element) Utils.getElement((GraphElement) getParent().getModel())));
    }

    /**
     * @see org.topcased.modeler.edit.GraphNodeEditPart#refreshTextAndFont()
     */
    @Override
    protected void refreshTextAndFont()
    {
        super.refreshTextAndFont();

        // When the Actor is abstract, the name has an Italic style
        if (((Classifier) getEObject()).isAbstract())
        {
            ((ComposedLabel) getLabel()).getMain().setFont(getStyledFont(SWT.ITALIC));
        }
        else
        {
            ((ComposedLabel) getLabel()).getMain().setFont(getLabel().getFont());
        }
    }

    /**
     * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultBackgroundColor()
     * @generated
     */
    @Override
    protected Color getPreferenceDefaultBackgroundColor()
    {
        String backgroundColor = getPreferenceStore().getString(UseCaseDiagramPreferenceConstants.ACTOR_DEFAULT_BACKGROUND_COLOR);
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
    @Override
    protected Color getPreferenceDefaultForegroundColor()
    {
        String foregroundColor = getPreferenceStore().getString(UseCaseDiagramPreferenceConstants.ACTOR_DEFAULT_FOREGROUND_COLOR);
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
    @Override
    protected Font getPreferenceDefaultFont()
    {
        String preferenceFont = getPreferenceStore().getString(UseCaseDiagramPreferenceConstants.ACTOR_DEFAULT_FONT);
        if (preferenceFont.length() != 0)
        {
            return Utils.getFont(new FontData(preferenceFont));
        }
        return null;

    }

}