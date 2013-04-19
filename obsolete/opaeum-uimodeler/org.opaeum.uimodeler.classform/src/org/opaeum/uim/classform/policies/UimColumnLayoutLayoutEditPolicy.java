/*******************************************************************************
 * 
 ******************************************************************************/
package org.opaeum.uim.classform.policies;

import org.eclipse.emf.ecore.EObject;
import org.opaeum.uim.UimDataTable;
import org.opaeum.uim.UimField;
import org.opaeum.uim.UimPanel;
import org.opaeum.uim.UimTabPanel;
import org.opaeum.uim.action.BuiltInAction;
import org.opaeum.uim.action.NavigationToEntity;
import org.opaeum.uim.action.NavigationToOperation;
import org.opaeum.uim.action.OperationAction;
import org.opaeum.uim.action.TransitionAction;
import org.opaeum.uim.form.DetailPanel;

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