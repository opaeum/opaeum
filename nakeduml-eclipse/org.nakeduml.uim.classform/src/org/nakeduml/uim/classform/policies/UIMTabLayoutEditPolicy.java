/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package org.nakeduml.uim.classform.policies;


import org.eclipse.emf.ecore.EObject;
import org.nakeduml.uim.UIMBorderLayout;
import org.nakeduml.uim.UIMGridLayout;
import org.nakeduml.uim.UIMToolbarLayout;
import org.nakeduml.uim.UIMXYLayout;

/**
 * @generated
 */
public class UIMTabLayoutEditPolicy extends
		org.topcased.modeler.edit.policies.ModelerLayoutEditPolicy {
	/**
	 * Default contructor.
	 *
	 * @generated
	 */
	public UIMTabLayoutEditPolicy() {
		super();
	}

	/**
	 * @see org.topcased.modeler.edit.policies.ModelerLayoutEditPolicy#isValid(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	protected boolean isValid(EObject child, EObject parent) {
		if (child instanceof UIMXYLayout) {
			return true;
		}
		if (child instanceof UIMGridLayout) {
			return true;
		}
		if (child instanceof UIMToolbarLayout) {
			return true;
		}
		if (child instanceof UIMBorderLayout) {
			return true;
		}
		return false;
	}

}