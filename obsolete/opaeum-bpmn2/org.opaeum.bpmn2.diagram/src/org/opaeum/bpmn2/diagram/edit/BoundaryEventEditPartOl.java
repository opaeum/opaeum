/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package org.opaeum.bpmn2.diagram.edit;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.opaeum.bpmn2.diagram.commands.BoundaryEventRestoreConnectionCommand;
import org.opaeum.bpmn2.diagram.figure.BoundaryEventFigure;
import org.opaeum.bpmn2.diagram.preferences.BPMN2DiagramPreferenceConstants;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.EMFGraphNodeEditPart;
import org.topcased.modeler.edit.policies.LabelDirectEditPolicy;
import org.topcased.modeler.edit.policies.ResizableEditPolicy;
import org.topcased.modeler.edit.policies.RestoreEditPolicy;
import org.topcased.modeler.requests.RestoreConnectionsRequest;
import org.topcased.modeler.utils.Utils;

/**
 * The BoundaryEvent object controller
 * 
 * @generated NOT
 */
public class BoundaryEventEditPartOl extends EMFGraphNodeEditPart{
	/**
	 * Constructor
	 * 
	 * @param obj
	 *            the graph node
	 * @generated
	 */
	public BoundaryEventEditPartOl(GraphNode obj){
		super(obj);
	}
	/**
	 * Creates edit policies and associates these with roles
	 * 
	 * @generated
	 */
	protected void createEditPolicies(){
		super.createEditPolicies();
		installEditPolicy(ModelerEditPolicyConstants.RESTORE_EDITPOLICY, new RestoreEditPolicy(){
			protected Command getRestoreConnectionsCommand(RestoreConnectionsRequest request){
				return new BoundaryEventRestoreConnectionCommand(getHost());
			}
		});
		installEditPolicy(ModelerEditPolicyConstants.RESIZABLE_EDITPOLICY, new ResizableEditPolicy());
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new LabelDirectEditPolicy());
	}
	/**
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 * @generated
	 */
	protected IFigure createFigure(){
		return new BoundaryEventFigure();
	}
	/**
	 * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultBackgroundColor()
	 * @generated
	 */
	protected Color getPreferenceDefaultBackgroundColor(){
		String backgroundColor = getPreferenceStore().getString(BPMN2DiagramPreferenceConstants.BOUNDARYEVENT_DEFAULT_BACKGROUND_COLOR);
		if(backgroundColor.length() != 0){
			return Utils.getColor(backgroundColor);
		}
		return null;
	}
	/**
	 * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultForegroundColor()
	 * @generated
	 */
	protected Color getPreferenceDefaultForegroundColor(){
		String foregroundColor = getPreferenceStore().getString(BPMN2DiagramPreferenceConstants.BOUNDARYEVENT_DEFAULT_FOREGROUND_COLOR);
		if(foregroundColor.length() != 0){
			return Utils.getColor(foregroundColor);
		}
		return null;
	}
	/**
	 * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultFont()
	 * @generated
	 */
	protected Font getPreferenceDefaultFont(){
		String preferenceFont = getPreferenceStore().getString(BPMN2DiagramPreferenceConstants.BOUNDARYEVENT_DEFAULT_FONT);
		if(preferenceFont.length() != 0){
			return Utils.getFont(new FontData(preferenceFont));
		}
		return null;
	}
}