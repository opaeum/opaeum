package org.opaeum.uim.model;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.editor.ResponsibilityViewer;
import org.opaeum.uim.wizard.ResponsibilityInvocationWizard;

public interface ResponsibilityUserInteractionModel extends EObject, AbstractUserInteractionModel {
	public ResponsibilityInvocationWizard getInvocationWizard();
	
	public ResponsibilityViewer getViewer();
	
	public void setInvocationWizard(ResponsibilityInvocationWizard invocationWizard);
	
	public void setViewer(ResponsibilityViewer viewer);

}