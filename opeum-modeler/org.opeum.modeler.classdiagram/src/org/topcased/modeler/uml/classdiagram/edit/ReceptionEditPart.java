/*******************************************************************************
 * Copyright (c) 2006 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.classdiagram.edit;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.uml2.uml.Reception;
import org.topcased.draw2d.figures.ComposedLabel;
import org.topcased.draw2d.figures.ILabelFigure;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.EMFGraphNodeEditPart;
import org.topcased.modeler.edit.policies.LabelDirectEditPolicy;
import org.topcased.modeler.edit.policies.ResizableEditPolicy;
import org.topcased.modeler.edit.policies.RestoreEditPolicy;
import org.topcased.modeler.requests.RestoreConnectionsRequest;
import org.topcased.modeler.uml.classdiagram.commands.ReceptionRestoreConnectionCommand;
import org.topcased.modeler.uml.classdiagram.figures.ReceptionFigure;
import org.topcased.modeler.uml.classdiagram.preferences.ClassDiagramPreferenceConstants;
import org.topcased.modeler.utils.Utils;

/**
 * The Reception object controller
 *
 * @generated
 */
public class ReceptionEditPart extends EMFGraphNodeEditPart {
	/**
	 * Constructor
	 *
	 * @param obj the graph node
	 * @generated
	 */
	public ReceptionEditPart(GraphNode obj) {
		super(obj);
	}

	/**
	 * Creates edit policies and associates these with roles
	 *
	 * @generated
	 */
	protected void createEditPolicies() {
		super.createEditPolicies();

		installEditPolicy(ModelerEditPolicyConstants.RESTORE_EDITPOLICY,
				new RestoreEditPolicy() {
					protected Command getRestoreConnectionsCommand(
							RestoreConnectionsRequest request) {
						return new ReceptionRestoreConnectionCommand(getHost());
					}
				});

		ResizableEditPolicy resizableEditPolicy = new ResizableEditPolicy();
		resizableEditPolicy.setResizeDirections(PositionConstants.EAST_WEST);
		installEditPolicy(ModelerEditPolicyConstants.RESIZABLE_EDITPOLICY,
				resizableEditPolicy);

		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE,
				new LabelDirectEditPolicy());
	}

	/**
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 * @generated
	 */
	protected IFigure createFigure() {

		return new ReceptionFigure();
	}
	
    /**
     * Set the name of the operation, with its argument and its stereotypes
     * 
     * @see org.topcased.modeler.edit.EMFGraphNodeEditPart#refreshHeaderLabel()
     */
    @Override
    protected void refreshHeaderLabel()
    {

        ComposedLabel lbl = (ComposedLabel) ((ILabelFigure) getFigure()).getLabel();
        Reception reception = (Reception) Utils.getElement(getGraphNode());

        if (reception.getName() != null && reception.getName().length() != 0)
        {
            lbl.setPrefix("<< signal >> ");
            lbl.setMain(reception.getName());
        }
    }

	/**
	 * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultBackgroundColor()
	 * @generated
	 */
	protected Color getPreferenceDefaultBackgroundColor() {
		String backgroundColor = getPreferenceStore()
				.getString(
						ClassDiagramPreferenceConstants.RECEPTION_DEFAULT_BACKGROUND_COLOR);
		if (backgroundColor.length() != 0) {
			return Utils.getColor(backgroundColor);
		}
		return null;
	}

	/**
	 * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultForegroundColor()
	 * @generated
	 */
	protected Color getPreferenceDefaultForegroundColor() {
		String foregroundColor = getPreferenceStore()
				.getString(
						ClassDiagramPreferenceConstants.RECEPTION_DEFAULT_FOREGROUND_COLOR);
		if (foregroundColor.length() != 0) {
			return Utils.getColor(foregroundColor);
		}
		return null;
	}

	/**
	 * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultFont()
	 * @generated
	 */
	protected Font getPreferenceDefaultFont() {
		String preferenceFont = getPreferenceStore().getString(
				ClassDiagramPreferenceConstants.RECEPTION_DEFAULT_FONT);
		if (preferenceFont.length() != 0) {
			return Utils.getFont(new FontData(preferenceFont));
		}
		return null;

	}

}