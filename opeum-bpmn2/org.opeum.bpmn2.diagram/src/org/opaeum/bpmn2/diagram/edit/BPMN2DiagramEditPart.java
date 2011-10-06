/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package org.opeum.bpmn2.diagram.edit;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.opeum.bpmn2.diagram.figures.BPMN2DiagramFigure;
import org.opeum.bpmn2.diagram.policies.BPMN2DiagramLayoutEditPolicy;
import org.topcased.modeler.di.model.Diagram;
import org.topcased.modeler.edit.DiagramEditPart;

/**
 * @generated
 */
public class BPMN2DiagramEditPart extends DiagramEditPart{
	/**
	 * The Constructor
	 *
	 * @param model the root model element
	 * @generated
	 */
	public BPMN2DiagramEditPart(Diagram model){
		super(model);
	}
	/**
	 * @see org.topcased.modeler.edit.DiagramEditPart#getLayoutEditPolicy()
	 * @generated
	 */
	protected EditPolicy getLayoutEditPolicy(){
		return new BPMN2DiagramLayoutEditPolicy();
	}
	/**
	 * @see org.topcased.modeler.edit.DiagramEditPart#createBodyFigure()
	 * @generated
	 */
	protected IFigure createBodyFigure(){
		return new BPMN2DiagramFigure();
	}
}