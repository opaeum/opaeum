/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package org.nakeduml.uim.classform.policies;


import org.eclipse.emf.ecore.EObject;
import org.nakeduml.uim.UIMTab;

/**
 * @generated
 */
public class UIMTabPanelLayoutEditPolicy extends
		org.topcased.modeler.edit.policies.ModelerLayoutEditPolicy {
	/**
	 * Default contructor.
	 *
	 * @generated
	 */
	public UIMTabPanelLayoutEditPolicy() {
		super();
	}

	/**
	 * @see org.topcased.modeler.edit.policies.ModelerLayoutEditPolicy#isValid(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	protected boolean isValid(EObject child, EObject parent) {
		if (child instanceof UIMTab) {
			return true;
		}
		return false;
	}

}