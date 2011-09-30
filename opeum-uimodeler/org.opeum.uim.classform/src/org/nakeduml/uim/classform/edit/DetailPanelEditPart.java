/*******************************************************************************
 * 
 ******************************************************************************/
package org.opeum.uim.classform.edit;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.opeum.uim.classform.commands.DetailPanelRestoreConnectionCommand;
import org.opeum.uim.classform.policies.DetailPanelLayoutEditPolicy;
import org.opeum.uim.classform.preferences.ClassFormDiagramPreferenceConstants;
import org.topcased.draw2d.figures.ContainerFigure;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.EMFGraphNodeEditPart;
import org.topcased.modeler.edit.policies.LabelDirectEditPolicy;
import org.topcased.modeler.edit.policies.ResizableEditPolicy;
import org.topcased.modeler.edit.policies.RestoreEditPolicy;
import org.topcased.modeler.requests.RestoreConnectionsRequest;
import org.topcased.modeler.utils.Utils;

/**
 * The DetailPanel object controller
 * 
 * @generated
 */
public class DetailPanelEditPart extends EMFGraphNodeEditPart{
	/**
	 * Constructor
	 * 
	 * @param obj
	 *            the graph node
	 * @generated
	 */
	public DetailPanelEditPart(GraphNode obj){
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
				return new DetailPanelRestoreConnectionCommand(getHost());
			}
		});
		installEditPolicy(ModelerEditPolicyConstants.RESIZABLE_EDITPOLICY, new ResizableEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new DetailPanelLayoutEditPolicy());
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new LabelDirectEditPolicy());
	}
	/**
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 * @generated NOT
	 */
	protected IFigure createFigure(){
		return new ContainerFigure();
	}
	/**
	 * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultBackgroundColor()
	 * @generated
	 */
	protected Color getPreferenceDefaultBackgroundColor(){
		String backgroundColor = getPreferenceStore().getString(ClassFormDiagramPreferenceConstants.DETAILPANEL_DEFAULT_BACKGROUND_COLOR);
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
		String foregroundColor = getPreferenceStore().getString(ClassFormDiagramPreferenceConstants.DETAILPANEL_DEFAULT_FOREGROUND_COLOR);
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
		String preferenceFont = getPreferenceStore().getString(ClassFormDiagramPreferenceConstants.DETAILPANEL_DEFAULT_FONT);
		if(preferenceFont.length() != 0){
			return Utils.getFont(new FontData(preferenceFont));
		}
		return null;
	}
}