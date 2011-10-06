/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package org.opaeum.bpmn2.diagram.policies;

import org.eclipse.bpmn2.BoundaryEvent;
import org.eclipse.emf.ecore.EObject;

/**
 * @generated
 */
public class UserTaskLayoutEditPolicy extends org.topcased.modeler.edit.policies.ModelerLayoutEditPolicy{
	/**
	 * Default contructor.
	 *
	 * @generated
	 */
	public UserTaskLayoutEditPolicy(){
		super();
	}
	/**
	 * @see org.topcased.modeler.edit.policies.ModelerLayoutEditPolicy#isValid(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	protected boolean isValid(EObject child,EObject parent){
		if(child instanceof BoundaryEvent){
			return true;
		}
		return false;
	}
}