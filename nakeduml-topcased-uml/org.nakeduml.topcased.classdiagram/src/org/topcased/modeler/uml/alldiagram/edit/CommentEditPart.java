/*******************************************************************************
 * Copyright (c) 2006, 2008 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.alldiagram.edit;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.uml2.uml.Comment;
import org.topcased.draw2d.figures.ILabel;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.LabelCellEditorLocator;
import org.topcased.modeler.edit.ModelerLabelDirectEditManager;
import org.topcased.modeler.edit.policies.LabelDirectEditPolicy;
import org.topcased.modeler.edit.policies.ResizableEditPolicy;
import org.topcased.modeler.edit.policies.RestoreEditPolicy;
import org.topcased.modeler.requests.RestoreConnectionsRequest;
import org.topcased.modeler.uml.UMLLabel;
import org.topcased.modeler.uml.alldiagram.commands.CommentRestoreConnectionCommand;
import org.topcased.modeler.uml.alldiagram.figures.CommentFigure;
import org.topcased.modeler.uml.alldiagram.policies.CommentLinkEdgeCreationEditPolicy;
import org.topcased.modeler.uml.alldiagram.preferences.AllDiagramPreferenceConstants;
import org.topcased.modeler.uml.classdiagram.ClassEditPolicyConstants;
import org.topcased.modeler.utils.Utils;

/**
 * The Comment object controller <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class CommentEditPart extends ElementEditPart
{
    /**
     * Constructor <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param obj the graph node
     * @generated
     */
    public CommentEditPart(GraphNode obj)
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

        installEditPolicy(ClassEditPolicyConstants.COMMENTLINK_EDITPOLICY, new CommentLinkEdgeCreationEditPolicy());

        installEditPolicy(ModelerEditPolicyConstants.RESTORE_EDITPOLICY, new RestoreEditPolicy()
        {
            protected Command getRestoreConnectionsCommand(RestoreConnectionsRequest request)
            {
                return new CommentRestoreConnectionCommand(getHost());
            }
        });

        installEditPolicy(ModelerEditPolicyConstants.RESIZABLE_EDITPOLICY, new ResizableEditPolicy());

        installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new LabelDirectEditPolicy());
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     * @generated
     */
    protected IFigure createFigure()
    {
        return new CommentFigure();
    }

    /**
     * Handle the comment edition
     * 
     * @see org.topcased.modeler.edit.GraphNodeEditPart#performDirectEdit()
     */
    protected void performDirectEdit()
    {
        if (getDirectEditManager() == null)
        {
            ILabel label = ((CommentFigure) getFigure()).getLabel();
            setDirectEditManager(new ModelerLabelDirectEditManager(this, TextCellEditor.class, new LabelCellEditorLocator(getFigure()), label)
            {
                protected CellEditor createCellEditorOn(Composite composite)
                {
                    return new TextCellEditor(composite, SWT.MULTI | SWT.WRAP);
                }
            });

        }
        getDirectEditManager().show();
    }

    /**
     * Always editable
     * 
     * @see org.topcased.modeler.edit.GraphNodeEditPart#directEditHitTest(org.eclipse.draw2d.geometry.Point)
     */
    protected boolean directEditHitTest(Point requestLoc)
    {
        return true;
    }

    /**
     * Set the comment text
     * 
     * @see org.topcased.modeler.edit.EMFGraphNodeEditPart#refreshHeaderLabel()
     */
    protected void refreshHeaderLabel()
    {
        ILabel label = ((CommentFigure) getFigure()).getLabel();
        Comment comment = (Comment) Utils.getElement(getGraphNode());
        String stereo = UMLLabel.getStereotypesNotation(comment, getPreferenceStore());
        ((CommentFigure) getFigure()).setStereotypeLabel(stereo);
        label.setText(comment.getBody());
    }

    /**
     * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultBackgroundColor()
     * 
     * @generated
     */
    protected Color getPreferenceDefaultBackgroundColor()
    {
        String backgroundColor = getPreferenceStore().getString(AllDiagramPreferenceConstants.COMMENT_DEFAULT_BACKGROUND_COLOR);
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
        String foregroundColor = getPreferenceStore().getString(AllDiagramPreferenceConstants.COMMENT_DEFAULT_FOREGROUND_COLOR);
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
        String preferenceFont = getPreferenceStore().getString(AllDiagramPreferenceConstants.COMMENT_DEFAULT_FONT);
        if (preferenceFont.length() != 0)
        {
            return Utils.getFont(new FontData(preferenceFont));
        }
        return null;

    }
    
    /**
     * @see org.topcased.modeler.edit.GraphNodeEditPart#getEditableLabel()
     */
    @Override
    public ILabel getEditableLabel()
    {
        return ((CommentFigure) getFigure()).getLabel();
    }
}