package org.opaeum.uim.wizard;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.Page;

public interface WizardPage extends EObject, Page {
	public AbstractWizard getWizard();
	
	public void setWizard(AbstractWizard wizard);

}