package org.opaeum.uim.uml2uim;

import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.uim.Page;
import org.opaeum.uim.UserInterfaceEntryPoint;
import org.opaeum.uim.wizard.AbstractWizard;
import org.opaeum.uim.wizard.WizardFactory;
import org.opaeum.uim.wizard.WizardPage;

public class WizardCreator extends AbstractUserInterfaceCreator{
	private AbstractWizard wizard;
	public WizardCreator(EmfWorkspace w, AbstractWizard cf){
		super(w);
		this.wizard = cf;
	}
	protected Page addPage(){
		WizardPage page = WizardFactory.eINSTANCE.createWizardPage();
		wizard.getPages().add(page);
		return page;
	}
	@Override
	protected UserInterfaceEntryPoint getUserInterfaceEntryPoint(){
		return wizard;
	}

}