package org.opaeum.uim.wizard;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.Page;
import org.opaeum.uim.model.OperationInvocationWizard;

public interface OperationResultPage extends EObject, Page {
	public OperationInvocationWizard getWizard();
	
	public void setWizard(OperationInvocationWizard wizard);

}