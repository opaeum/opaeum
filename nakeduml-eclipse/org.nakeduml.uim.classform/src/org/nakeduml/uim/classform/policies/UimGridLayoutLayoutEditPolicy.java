/*******************************************************************************
 * 
 ******************************************************************************/
package org.nakeduml.uim.classform.policies;

import org.eclipse.emf.ecore.EObject;
import org.nakeduml.uim.BuiltInAction;
import org.nakeduml.uim.DetailPanel;
import org.nakeduml.uim.NavigationToEntity;
import org.nakeduml.uim.NavigationToOperation;
import org.nakeduml.uim.OperationAction;
import org.nakeduml.uim.TransitionAction;
import org.nakeduml.uim.UimDataTable;
import org.nakeduml.uim.UimField;
import org.nakeduml.uim.UimPanel;
import org.nakeduml.uim.UimTabPanel;

/**
 * @generated
 */
public class UimGridLayoutLayoutEditPolicy extends org.topcased.modeler.edit.policies.ModelerLayoutEditPolicy{
	/**
	 * Default contructor.
	 *
	 * @generated
	 */
	public UimGridLayoutLayoutEditPolicy(){
		super();
	}
	/**
	 * @see org.topcased.modeler.edit.policies.ModelerLayoutEditPolicy#isValid(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	protected boolean isValid(EObject child,EObject parent){
		if(child instanceof UimField){
			return true;
		}
		if(child instanceof NavigationToEntity){
			return true;
		}
		if(child instanceof NavigationToOperation){
			return true;
		}
		if(child instanceof BuiltInAction){
			return true;
		}
		if(child instanceof OperationAction){
			return true;
		}
		if(child instanceof UimPanel){
			return true;
		}
		if(child instanceof UimTabPanel){
			return true;
		}
		if(child instanceof UimDataTable){
			return true;
		}
		if(child instanceof DetailPanel){
			return true;
		}
		if(child instanceof TransitionAction){
			return true;
		}
		return false;
	}
}