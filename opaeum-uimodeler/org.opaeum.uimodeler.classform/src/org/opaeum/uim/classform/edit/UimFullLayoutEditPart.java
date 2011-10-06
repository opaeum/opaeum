/*******************************************************************************
 * 
 ******************************************************************************/
package org.opaeum.uim.classform.edit;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.opaeum.uim.classform.commands.UimFullLayoutRestoreConnectionCommand;
import org.opaeum.uim.classform.policies.UimFullLayoutLayoutEditPolicy;
import org.opaeum.uim.classform.preferences.ClassFormDiagramPreferenceConstants;
import org.opaeum.uim.figures.UimFullLayoutFigure;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.edit.EMFGraphNodeEditPart;
import org.topcased.modeler.edit.policies.LabelDirectEditPolicy;
import org.topcased.modeler.edit.policies.ResizableEditPolicy;
import org.topcased.modeler.edit.policies.RestoreEditPolicy;
import org.topcased.modeler.requests.RestoreConnectionsRequest;
import org.topcased.modeler.utils.Utils;

/**
 * The UimFullLayout object controller
 *
 * @generated
 */
public class UimFullLayoutEditPart extends EMFGraphNodeEditPart{
	/**
	 * Constructor
	 *
	 * @param obj the graph node
	 * @generated
	 */
	public UimFullLayoutEditPart(GraphNode obj){
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
				return new UimFullLayoutRestoreConnectionCommand(getHost());
			}
		});
		installEditPolicy(ModelerEditPolicyConstants.RESIZABLE_EDITPOLICY, new ResizableEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new UimFullLayoutLayoutEditPolicy());
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new LabelDirectEditPolicy());
	}
	/**
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 * @generated
	 */
	protected IFigure createFigure(){
		return new UimFullLayoutFigure();
	}
	/**
	 * @see org.topcased.modeler.edit.GraphNodeEditPart#getPreferenceDefaultBackgroundColor()
	 * @generated
	 */
	protected Color getPreferenceDefaultBackgroundColor(){
		String backgroundColor = getPreferenceStore().getString(ClassFormDiagramPreferenceConstants.UIMFULLLAYOUT_DEFAULT_BACKGROUND_COLOR);
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
		String foregroundColor = getPreferenceStore().getString(ClassFormDiagramPreferenceConstants.UIMFULLLAYOUT_DEFAULT_FOREGROUND_COLOR);
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
		String preferenceFont = getPreferenceStore().getString(ClassFormDiagramPreferenceConstants.UIMFULLLAYOUT_DEFAULT_FONT);
		if(preferenceFont.length() != 0){
			return Utils.getFont(new FontData(preferenceFont));
		}
		return null;
	}
}