/*******************************************************************************
 * 
 ******************************************************************************/
package org.opaeum.uim.classform.policies;

import org.eclipse.emf.ecore.EObject;
import org.opaeum.uim.UimField;
import org.opaeum.uim.action.BuiltInAction;
import org.opaeum.uim.action.NavigationToEntity;
import org.opaeum.uim.action.NavigationToOperation;
import org.opaeum.uim.action.OperationAction;
import org.opaeum.uim.layout.UimBorderLayout;
import org.opaeum.uim.layout.UimGridLayout;
import org.opaeum.uim.layout.UimToolbarLayout;
import org.opaeum.uim.layout.UimXYLayout;

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