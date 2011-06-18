/*******************************************************************************
 * 
 ******************************************************************************/
package org.nakeduml.uim.classform.policies;

import org.eclipse.emf.ecore.EObject;
import org.nakeduml.uim.UimDataTable;
import org.nakeduml.uim.UimField;
import org.nakeduml.uim.UimPanel;
import org.nakeduml.uim.UimTabPanel;
import org.nakeduml.uim.action.BuiltInAction;
import org.nakeduml.uim.action.NavigationToEntity;
import org.nakeduml.uim.action.NavigationToOperation;
import org.nakeduml.uim.action.OperationAction;
import org.nakeduml.uim.action.TransitionAction;
import org.nakeduml.uim.form.DetailPanel;

/**
 * @generated
 */
public class UimColumnLayoutLayoutEditPolicy extends org.topcased.modeler.edit.policies.ModelerLayoutEditPolicy{
	/**
	 * Default contructor.
	 *
	 * @generated
	 */
	public UimColumnLayoutLayoutEditPolicy(){
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
		if(child instanceof TransitionAction){
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
		return false;
	}
}