/*******************************************************************************
 * 
 ******************************************************************************/
package org.opeum.uim.classform.policies;

import org.eclipse.emf.ecore.EObject;
import org.opeum.uim.UimField;
import org.opeum.uim.action.BuiltInAction;
import org.opeum.uim.action.NavigationToEntity;
import org.opeum.uim.action.NavigationToOperation;
import org.opeum.uim.action.OperationAction;
import org.opeum.uim.layout.UimBorderLayout;
import org.opeum.uim.layout.UimGridLayout;
import org.opeum.uim.layout.UimToolbarLayout;
import org.opeum.uim.layout.UimXYLayout;

/**
 * @generated
 */
public class UimDataColumnLayoutEditPolicy extends org.topcased.modeler.edit.policies.ModelerLayoutEditPolicy{
	/**
	 * Default contructor.
	 *
	 * @generated
	 */
	public UimDataColumnLayoutEditPolicy(){
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
		if(child instanceof UimXYLayout){
			return true;
		}
		if(child instanceof UimGridLayout){
			return true;
		}
		if(child instanceof UimToolbarLayout){
			return true;
		}
		if(child instanceof UimBorderLayout){
			return true;
		}
		return false;
	}
}