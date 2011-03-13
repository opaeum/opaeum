/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package org.nakeduml.uim.folderdiagram.policies;


import org.eclipse.emf.ecore.EObject;
import org.nakeduml.uim.ActionTaskForm;
import org.nakeduml.uim.ClassForm;
import org.nakeduml.uim.OperationInvocationForm;
import org.nakeduml.uim.StateMachineFolder;

/**
 * @generated
 */
public class ActivityFolderLayoutEditPolicy extends
		org.topcased.modeler.edit.policies.ModelerLayoutEditPolicy {
	/**
	 * Default contructor.
	 *
	 * @generated
	 */
	public ActivityFolderLayoutEditPolicy() {
		super();
	}

	/**
	 * @see org.topcased.modeler.edit.policies.ModelerLayoutEditPolicy#isValid(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	protected boolean isValid(EObject child, EObject parent) {
		if (child instanceof StateMachineFolder) {
			return true;
		}
		if (child instanceof ActionTaskForm) {
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