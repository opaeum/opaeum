/*******************************************************************************
 * 
 ******************************************************************************/
package org.nakeduml.uim.classform.policies;

import org.eclipse.emf.ecore.EObject;
import org.nakeduml.uim.UimField;
import org.nakeduml.uim.action.BuiltInAction;
import org.nakeduml.uim.action.NavigationToEntity;
import org.nakeduml.uim.action.NavigationToOperation;
import org.nakeduml.uim.action.OperationAction;
import org.nakeduml.uim.layout.UimBorderLayout;
import org.nakeduml.uim.layout.UimGridLayout;
import org.nakeduml.uim.layout.UimToolbarLayout;
import org.nakeduml.uim.layout.UimXYLayout;

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