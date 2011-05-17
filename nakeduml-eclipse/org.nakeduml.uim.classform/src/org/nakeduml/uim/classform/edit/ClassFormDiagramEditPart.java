/*******************************************************************************
 * 
 ******************************************************************************/
package org.nakeduml.uim.classform.edit;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.nakeduml.uim.classform.figures.ClassFormDiagramFigure;
import org.nakeduml.uim.classform.policies.ClassFormDiagramLayoutEditPolicy;
import org.topcased.modeler.di.model.Diagram;
import org.topcased.modeler.edit.DiagramEditPart;

/**
 * @generated
 */
public class ClassFormDiagramEditPart extends DiagramEditPart{
	/**
	 * The Constructor
	 * 
	 * @param model
	 *            the root model element
	 * @generated
	 */
	public ClassFormDiagramEditPart(Diagram model){
		super(model);
	}
	/**
	 * @see org.topcased.modeler.edit.DiagramEditPart#getLayoutEditPolicy()
	 * @generated
	 */
	protected EditPolicy getLayoutEditPolicy(){
		return new ClassFormDiagramLayoutEditPolicy();
	}
	/**
	 * @see org.topcased.modeler.edit.DiagramEditPart#createBodyFigure()
	 * @generated
	 */
	protected IFigure createBodyFigure(){
		return new ClassFormDiagramFigure();
	}
}