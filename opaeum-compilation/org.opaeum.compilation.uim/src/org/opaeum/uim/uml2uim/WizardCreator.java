package org.opaeum.uim.uml2uim;

import org.opaeum.uim.Page;
import org.opaeum.uim.UserInterfaceRoot;
import org.opaeum.uim.wizard.AbstractWizard;
import org.opaeum.uim.wizard.WizardFactory;
import org.opaeum.uim.wizard.WizardPage;

public class WizardCreator extends AbstractUserInterfaceCreator{
	private AbstractWizard wizard;
	public WizardCreator(UserInterfaceResourceFactory w,AbstractWizard cf){
		super(w);
		this.wizard = cf;
	}
	protected Page addPage(UserInterfaceRoot pc){
			WizardPage page = WizardFactory.eINSTANCE.createWizardPage();
			((AbstractWizard) pc).getPages().add(page);
			return page;
	}
	@Override
	protected UserInterfaceRoot getUserInterfaceRoot(){
		return wizard;
	}
}