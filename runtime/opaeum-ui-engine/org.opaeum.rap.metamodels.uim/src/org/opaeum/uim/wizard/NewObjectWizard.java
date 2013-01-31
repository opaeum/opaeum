package org.opaeum.uim.wizard;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.model.ClassUserInteractionModel;

public interface NewObjectWizard extends EObject, AbstractWizard {
	public ClassUserInteractionModel getModel();
	
	public void setModel(ClassUserInteractionModel model);

}