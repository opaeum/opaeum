/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package org.nakeduml.uim.folderdiagram.policies;

import org.eclipse.emf.ecore.EObject;
import org.nakeduml.uim.ActivityFolder;
import org.nakeduml.uim.ClassForm;
import org.nakeduml.uim.OperationInvocationForm;
import org.nakeduml.uim.OperationTaskForm;
import org.nakeduml.uim.StateMachineFolder;

/**
 * @generated
 */
public class EntityFolderLayoutEditPolicy extends org.topcased.modeler.edit.policies.ModelerLayoutEditPolicy{
	/**
	 * Default contructor.
	 *
	 * @generated
	 */
	public EntityFolderLayoutEditPolicy(){
		super();
	}
	/**
	 * @see org.topcased.modeler.edit.policies.ModelerLayoutEditPolicy#isValid(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	protected boolean isValid(EObject child,EObject parent){
		if(child instanceof ClassForm){
			return true;
		}
		if(child instanceof OperationTaskForm){
			return true;
		}
		if(child instanceof OperationInvocationForm){
			return true;
		}
		if(child instanceof StateMachineFolder){
			return true;
		}
		if(child instanceof ActivityFolder){
			return true;
		}
		return false;
	}
}