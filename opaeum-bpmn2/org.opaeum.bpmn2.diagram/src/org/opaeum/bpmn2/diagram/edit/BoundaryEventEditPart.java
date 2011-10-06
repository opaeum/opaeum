/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package org.opaeum.bpmn2.diagram.edit;

import org.eclipse.bpmn2.BoundaryEvent;
import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.notify.Notification;
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
public class BoundaryEventEditPart extends org.topcased.modeler.edit.PortEditPart{
	/**
	 * Constructor
	 * 
	 * @param obj
	 *            the graph node
	 * @generated
	 */
	public BoundaryEventEditPart(GraphNode obj){
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
	@Override
	public void activate(){
		super.activate();
		if(getBoundaryEvent().getAttachedToRef() != null){
			((BoundaryEventFigure) getFigure()).setUserTaskId(getBoundaryEvent().getAttachedToRef().getId());
		}
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
	@Override
	protected void handleModelChanged(Notification msg){
		// TODO Auto-generated method stub
		super.handleModelChanged(msg);
		if(msg.getNotifier() instanceof BoundaryEvent && msg.getFeatureID(BoundaryEvent.class) == Bpmn2Package.BOUNDARY_EVENT__ATTACHED_TO_REF){
			BoundaryEvent be = (BoundaryEvent) msg.getNotifier();
			if(getBoundaryEvent().getAttachedToRef() != null){
				((BoundaryEventFigure) getFigure()).setUserTaskId(getBoundaryEvent().getAttachedToRef().getId());
			}else{
				((BoundaryEventFigure) getFigure()).setUserTaskId(null);
			}
			getFigure().getParent().invalidate();
		}
	}
	@Override
	protected void refreshVisuals(){
		super.refreshVisuals();
	}
	public BoundaryEvent getBoundaryEvent(){
		return (BoundaryEvent) getEObject();
	}
}