package org.opaeum.rap.runtime.internal.views;

import org.eclipse.jface.wizard.Wizard;
import org.opaeum.rap.runtime.IOpaeumApplication;

import com.google.gdata.client.contacts.ContactsService;

public class UserRoleAllocationWizard extends Wizard{
	UserRoleAllocationWizardData model;
	public UserRoleAllocationWizard(ContactsService service,IOpaeumApplication application){
		model = new UserRoleAllocationWizardData(service,application);
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
