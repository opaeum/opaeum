/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package org.nakeduml.uim.folderdiagram.edit;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.nakeduml.uim.folderdiagram.figures.UIMFDiagramFigure;
import org.nakeduml.uim.folderdiagram.policies.UIMFDiagramLayoutEditPolicy;
import org.topcased.modeler.di.model.Diagram;
import org.topcased.modeler.edit.DiagramEditPart;

/**
 * @generated
 */
public class UIMFDiagramEditPart extends DiagramEditPart{
	/**
	 * The Constructor
	 *
	 * @param model the root model element
	 * @generated
	 */
	public UIMFDiagramEditPart(Diagram model){
		super(model);
	}
	/**
	 * @see org.topcased.modeler.edit.DiagramEditPart#getLayoutEditPolicy()
	 * @generated
	 */
	protected EditPolicy getLayoutEditPolicy(){
		return new UIMFDiagramLayoutEditPolicy();
	}
	/**
	 * @see org.topcased.modeler.edit.DiagramEditPart#createBodyFigure()
	 * @generated
	 */
	protected IFigure createBodyFigure(){
		return new UIMFDiagramFigure();
	}
}