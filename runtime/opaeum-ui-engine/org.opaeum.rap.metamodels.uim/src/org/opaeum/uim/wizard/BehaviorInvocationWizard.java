package org.opaeum.uim.wizard;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.model.BehaviorUserInteractionModel;

public interface BehaviorInvocationWizard extends EObject, InvocationWizard {
	public BehaviorUserInteractionModel getModel();
	
	public void setModel(BehaviorUserInteractionModel model);

}