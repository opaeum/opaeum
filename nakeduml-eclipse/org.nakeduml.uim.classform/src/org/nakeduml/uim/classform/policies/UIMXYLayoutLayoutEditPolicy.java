/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package org.nakeduml.uim.classform.policies;


import org.eclipse.emf.ecore.EObject;
import org.nakeduml.uim.BuiltInAction;
import org.nakeduml.uim.DetailPanel;
import org.nakeduml.uim.NavigationToEntity;
import org.nakeduml.uim.NavigationToOperation;
import org.nakeduml.uim.OperationAction;
import org.nakeduml.uim.TransitionAction;
import org.nakeduml.uim.UIMDataTable;
import org.nakeduml.uim.UIMField;
import org.nakeduml.uim.UIMPanel;
import org.nakeduml.uim.UIMTabPanel;

/**
 * @generated
 */
public class UIMXYLayoutLayoutEditPolicy extends
		org.topcased.modeler.edit.policies.ModelerLayoutEditPolicy {
	/**
	 * Default contructor.
	 *
	 * @generated
	 */
	public UIMXYLayoutLayoutEditPolicy() {
		super();
	}

	/**
	 * @see org.topcased.modeler.edit.policies.ModelerLayoutEditPolicy#isValid(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	protected boolean isValid(EObject child, EObject parent) {
		if (child instanceof UIMField) {
			return true;
		}
		if (child instanceof NavigationToEntity) {
			return true;
		}
		if (child instanceof NavigationToOperation) {
			return true;
		}
		if (child instanceof BuiltInAction) {
			return true;
		}
		if (child instanceof OperationAction) {
			return true;
		}
		if (child instanceof UIMPanel) {
			return true;
		}
		if (child instanceof UIMTabPanel) {
			return true;
		}
		if (child instanceof UIMDataTable) {
			return true;
		}
		if (child instanceof DetailPanel) {
			return true;
		}
		if (child instanceof TransitionAction) {
			return true;
		}
		return false;
	}

}