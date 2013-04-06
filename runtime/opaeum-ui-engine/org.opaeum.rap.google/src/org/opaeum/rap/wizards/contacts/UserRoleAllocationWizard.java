package org.opaeum.rap.wizards.contacts;

import org.eclipse.jface.wizard.Wizard;
import org.opaeum.rap.login.GoogleOpaeumRapSession;
import org.opaeum.runtime.rwt.IOpaeumApplication;

public class UserRoleAllocationWizard extends Wizard{
	UserRoleAllocationWizardData model;
	public UserRoleAllocationWizard(GoogleOpaeumRapSession opaeumRapSession,IOpaeumApplication application){
		model = new UserRoleAllocationWizardData(opaeumRapSession.getContactsService(),application);
		model.populateBusinessRoleClasses();
	}
	@Override
	public void addPages(){
		super.addPage(new MultiUserRoleAllocationMatrixPage(model));
		super.addPage(new SingleUserRoleAllocationMatrixPage(model));
	}
	@Override
	public boolean performFinish(){
		model.flush();
		return true;
	}
}
