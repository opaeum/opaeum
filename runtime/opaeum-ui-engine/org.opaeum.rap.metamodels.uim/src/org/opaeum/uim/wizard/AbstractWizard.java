package org.opaeum.uim.wizard;

import java.util.List;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.UserInterfaceRoot;

public interface AbstractWizard extends EObject, UserInterfaceRoot {
	public List<WizardPage> getPages();
	
	public void setPages(List<WizardPage> pages);

}