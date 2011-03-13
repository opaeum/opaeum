/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package org.nakeduml.uim.folderdiagram.policies;


import org.eclipse.emf.ecore.EObject;
import org.nakeduml.uim.ClassForm;
import org.nakeduml.uim.OperationInvocationForm;
import org.nakeduml.uim.StateForm;

/**
 * @generated
 */
public class StateMachineFolderLayoutEditPolicy extends
		org.topcased.modeler.edit.policies.ModelerLayoutEditPolicy {
	/**
	 * Default contructor.
	 *
	 * @generated
	 */
	public StateMachineFolderLayoutEditPolicy() {
		super();
	}

	/**
	 * @see org.topcased.modeler.edit.policies.ModelerLayoutEditPolicy#isValid(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	protected boolean isValid(EObject child, EObject parent) {
		if (child instanceof StateForm) {
			return true;
		}
		if (child instanceof OperationInvocationForm) {
			return true;
		}
		if (child instanceof ClassForm) {
			return true;
		}
		return false;
	}

}