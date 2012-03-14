package org.opaeum.uim.uml2uim;

import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.uim.Page;
import org.opaeum.uim.PageContainer;
import org.opaeum.uim.UserInterfaceEntryPoint;
import org.opaeum.uim.action.ActionFactory;
import org.opaeum.uim.action.OperationPopup;
import org.opaeum.uim.action.OperationPopupPage;
import org.opaeum.uim.wizard.AbstractWizard;
import org.opaeum.uim.wizard.WizardFactory;
import org.opaeum.uim.wizard.WizardPage;
import org.opaeum.uim.wizard.impl.AbstractWizardImpl;

public class WizardCreator extends AbstractUserInterfaceCreator{
	private AbstractWizard wizard;
	public WizardCreator(EmfWorkspace w,AbstractWizard cf){
		super(w);
		this.wizard = cf;
	}
	protected Page addPage(PageContainer pc){
		if(pc instanceof AbstractWizard){
			WizardPage page = WizardFactory.eINSTANCE.createWizardPage();
			((AbstractWizard) pc).getPages().add(page);
			return page;
		}else{
			OperationPopup popup = (OperationPopup) pc;
			OperationPopupPage page = ActionFactory.eINSTANCE.createOperationPopupPage();
			popup.getPages().add(page);
			return page;
		}
	}
	@Override
	protected UserInterfaceEntryPoint getUserInterfaceEntryPoint(){
		return wizard;
	}
}