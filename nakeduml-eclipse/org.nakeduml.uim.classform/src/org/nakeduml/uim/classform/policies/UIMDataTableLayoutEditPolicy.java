/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package org.nakeduml.uim.classform.policies;

import org.eclipse.emf.ecore.EObject;
import org.nakeduml.uim.UIMDataColumn;

/**
 * @generated
 */
public class UIMDataTableLayoutEditPolicy extends org.topcased.modeler.edit.policies.ModelerLayoutEditPolicy{
	/**
	 * Default contructor.
	 *
	 * @generated
	 */
	public UIMDataTableLayoutEditPolicy(){
		super();
	}
	/**
	 * @see org.topcased.modeler.edit.policies.ModelerLayoutEditPolicy#isValid(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	protected boolean isValid(EObject child,EObject parent){
		if(child instanceof UIMDataColumn){
			return true;
		}
		return false;
	}
}