/*******************************************************************************
 * 
 ******************************************************************************/
package org.opaeum.uim.classform.policies;

import org.eclipse.emf.ecore.EObject;
import org.opaeum.uim.UimTab;

/**
 * @generated
 */
public class UimTabPanelLayoutEditPolicy extends org.topcased.modeler.edit.policies.ModelerLayoutEditPolicy{
	/**
	 * Default contructor.
	 *
	 * @generated
	 */
	public UimTabPanelLayoutEditPolicy(){
		super();
	}
	/**
	 * @see org.topcased.modeler.edit.policies.ModelerLayoutEditPolicy#isValid(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	protected boolean isValid(EObject child,EObject parent){
		if(child instanceof UimTab){
			return true;
		}
		return false;
	}
}