package org.opaeum.uim.wizard;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.model.ResponsibilityUserInteractionModel;

public interface ResponsibilityInvocationWizard extends EObject, InvocationWizard {
	public ResponsibilityUserInteractionModel getModel();
	
	public void setModel(ResponsibilityUserInteractionModel model);

}