/*******************************************************************************
 * Copyright (c) 2006, 2008 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.classdiagram.edit;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.EMFGraphNodeEditPart;
import org.topcased.modeler.edit.policies.LabelDirectEditPolicy;
import org.topcased.modeler.edit.policies.RestoreEditPolicy;
import org.topcased.modeler.requests.RestoreConnectionsRequest;
import org.topcased.modeler.uml.UMLLabel;
import org.topcased.modeler.uml.classdiagram.commands.EnumerationLiteralRestoreConnectionCommand;
import org.topcased.modeler.uml.classdiagram.figures.EnumerationLiteralFigure;
import org.topcased.modeler.uml.classdiagram.preferences.ClassDiagramPreferenceConstants;
import org.topcased.modeler.utils.Utils;

/**
 * The EnumerationLiteral object controller <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class EnumerationLiteralEditPart extends EMFGraphNodeEditPart
{
    /**
     * Constructor <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param obj the graph node
     * @generated
     */
    public EnumerationLiteralEditPart(GraphNode obj)
    {
        super(obj);
    }

    /**
     * Creates edit policies and associates these with roles <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void createEditPolicies()
    {
        super.createEditPolicies();

        installEditPolicy(ModelerEditPolicyConstants.RESTORE_EDITPOLICY, new RestoreEditPolicy()
        {
            protected Command getRestoreConnectionsCommand(RestoreConnectionsRequest request)
            {
                return new EnumerationLiteralRestoreConnectionCommand(getHost());
            }
        });

        installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new LabelDirectEditPolicy());
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     * @generated
     */
    protected IFigure createFigure()
    {

        return new EnumerationLiteralFigure();
    }

    /**
     * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultBackgroundColor()
     * 
     * @generated
     */
    protected Color getPreferenceDefaultBackgroundColor()
    {
        String backgroundColor = getPreferenceStore().getString(ClassDiagramPreferenceConstants.ENUMERATIONLITERAL_DEFAULT_BACKGROUND_COLOR);
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
    protected Color getPreferenceDefaultForegroundColor()
    {
        String foregroundColor = getPreferenceStore().getString(ClassDiagramPreferenceConstants.ENUMERATIONLITERAL_DEFAULT_FOREGROUND_COLOR);
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
    protected Font getPreferenceDefaultFont()
    {
        String preferenceFont = getPreferenceStore().getString(ClassDiagramPreferenceConstants.ENUMERATIONLITERAL_DEFAULT_FONT);
        if (preferenceFont.length() != 0)
        {
            return Utils.getFont(new FontData(preferenceFont));
        }
        return null;

    }

    /**
     * Set the name of the property with its stereotypes
     * 
     * @see org.topcased.modeler.edit.EMFGraphNodeEditPart#refreshHeaderLabel()
     */
    @Override
    protected void refreshHeaderLabel()
    {
        if (getEObject() instanceof EnumerationLiteral)
        {
            StringBuffer sb = new StringBuffer();
            EnumerationLiteral enumLiteral = (EnumerationLiteral) getEObject();
            sb.append(UMLLabel.getStereotypesNotation(enumLiteral, getPreferenceStore()));
            sb.append(enumLiteral.getName());
            getEditableLabel().setText(sb.toString());
        }
    }

}