/*******************************************************************************
 * 
 ******************************************************************************/
package org.opeum.uim.classform.policies;

import org.eclipse.emf.ecore.EObject;
import org.opeum.uim.UimDataTable;
import org.opeum.uim.UimField;
import org.opeum.uim.UimPanel;
import org.opeum.uim.UimTabPanel;
import org.opeum.uim.action.BuiltInAction;
import org.opeum.uim.action.NavigationToEntity;
import org.opeum.uim.action.NavigationToOperation;
import org.opeum.uim.action.OperationAction;
import org.opeum.uim.action.TransitionAction;
import org.opeum.uim.form.DetailPanel;

/**
 * @generated
 */
public class UimToolbarLayoutLayoutEditPolicy extends org.topcased.modeler.edit.policies.ModelerLayoutEditPolicy{
	/**
	 * Default contructor.
	 *
	 * @generated
	 */
	public UimToolbarLayoutLayoutEditPolicy(){
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