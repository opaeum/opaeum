package org.opaeum.uim.model;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.wizard.InvocationWizard;
import org.opaeum.uim.wizard.OperationResultPage;

public interface OperationInvocationWizard extends EObject, AbstractUserInteractionModel, InvocationWizard {
	public OperationResultPage getResultPage();
	
	public void setResultPage(OperationResultPage resultPage);

}