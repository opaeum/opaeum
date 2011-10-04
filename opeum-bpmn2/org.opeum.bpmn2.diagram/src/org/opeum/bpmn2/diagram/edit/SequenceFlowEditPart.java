/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package org.opeum.bpmn2.diagram.edit;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.opeum.bpmn2.diagram.figures.SequenceFlowFigure;
import org.opeum.bpmn2.diagram.preferences.BPMN2DiagramPreferenceConstants;
import org.topcased.modeler.ModelerEditPolicyConstants;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.edit.EMFGraphEdgeEditPart;
import org.topcased.modeler.utils.Utils;

/**
 * SequenceFlow controller
 *
 * @generated
 */
public class SequenceFlowEditPart extends EMFGraphEdgeEditPart{
	/**
	 * Constructor
	 *
	 * @param model the graph object
	 * @generated
	 */
	public SequenceFlowEditPart(GraphEdge model){
		super(model);
	}
	/**
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 * @generated
	 */
	protected void createEditPolicies(){
		super.createEditPolicies();
		installEditPolicy(ModelerEditPolicyConstants.CHANGE_FONT_EDITPOLICY, null);
	}
	/**
	 * @return the Figure
	 * @generated
	 */
	protected IFigure createFigure(){
		SequenceFlowFigure connection = new SequenceFlowFigure();
		createTargetDecoration(connection);
		return connection;
	}
	/**
	 * @param connection the PolylineConnection
	 * @generated
	 */
	private void createTargetDecoration(PolylineConnection connection){
		PolylineDecoration decoration = new PolylineDecoration();
		decoration.setScale(10, 5);
		connection.setTargetDecoration(decoration);
	}
	/**
	 * @see org.topcased.modeler.edit.GraphEdgeEditPart#getPreferenceDefaultRouter()
	 * 
	 * @generated
	 */
	protected String getPreferenceDefaultRouter(){
		return getPreferenceStore().getString(BPMN2DiagramPreferenceConstants.SEQUENCEFLOW_EDGE_DEFAULT_ROUTER);
	}
	/**
	 * @see org.topcased.modeler.edit.GraphEdgeEditPart#getPreferenceDefaultForegroundColor()
	 * 
	 * @generated
	 */
	protected Color getPreferenceDefaultForegroundColor(){
		String preferenceForeground = getPreferenceStore().getString(BPMN2DiagramPreferenceConstants.SEQUENCEFLOW_EDGE_DEFAULT_FOREGROUND_COLOR);
		if(preferenceForeground.length() != 0){
			return Utils.getColor(preferenceForeground);
		}
		return null;
	}
	/**
	 * @see org.topcased.modeler.edit.GraphEdgeEditPart#getPreferenceDefaultFont()
	 * 
	 * @generated
	 */
	protected Font getPreferenceDefaultFont(){
		String preferenceFont = getPreferenceStore().getString(BPMN2DiagramPreferenceConstants.SEQUENCEFLOW_EDGE_DEFAULT_FONT);
		if(preferenceFont.length() != 0){
			return Utils.getFont(new FontData(preferenceFont));
		}
		return null;
	}
}