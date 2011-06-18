/*******************************************************************************
 * 
 ******************************************************************************/
package org.nakeduml.uim.classform.policies;

import org.eclipse.emf.ecore.EObject;
import org.nakeduml.uim.layout.UimBorderLayout;
import org.nakeduml.uim.layout.UimFullLayout;
import org.nakeduml.uim.layout.UimGridLayout;
import org.nakeduml.uim.layout.UimToolbarLayout;
import org.nakeduml.uim.layout.UimXYLayout;

/**
 * @generated
 */
public class FormPanelLayoutEditPolicy extends org.topcased.modeler.edit.policies.ModelerLayoutEditPolicy{
	/**
	 * Default contructor.
	 * 
	 * @generated
	 */
	public FormPanelLayoutEditPolicy(){
		super();
	}
	/**
	 * @see org.topcased.modeler.edit.policies.ModelerLayoutEditPolicy#isValid(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	protected boolean isValid(EObject child,EObject parent){
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
		if(child instanceof UimFullLayout){
			return true;
		}
		return false;
	}
}