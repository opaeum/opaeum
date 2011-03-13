/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package org.nakeduml.uim.folderdiagram.policies;


import org.eclipse.emf.ecore.EObject;
import org.nakeduml.uim.EntityFolder;
import org.nakeduml.uim.PackageFolder;

/**
 * @generated
 */
public class PackageFolderLayoutEditPolicy extends
		org.topcased.modeler.edit.policies.ModelerLayoutEditPolicy {
	/**
	 * Default contructor.
	 *
	 * @generated
	 */
	public PackageFolderLayoutEditPolicy() {
		super();
	}

	/**
	 * @see org.topcased.modeler.edit.policies.ModelerLayoutEditPolicy#isValid(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	protected boolean isValid(EObject child, EObject parent) {
		if (child instanceof PackageFolder) {
			return true;
		}
		if (child instanceof EntityFolder) {
			return true;
		}
		return false;
	}

}