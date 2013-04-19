package org.opaeum.runtime.jface.wizards;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.wizard.Wizard;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.persistence.ConversationalPersistence;
import org.opaeum.runtime.rwt.OpaeumRapSession;
import org.opaeum.uim.Page;
import org.opaeum.uim.UserInterfaceRoot;
import org.opaeum.uim.model.ClassUserInteractionModel;

public class NewEntityWizard extends Wizard{
	private OpaeumRapSession opaeumRapSession;
	private UserInterfaceRoot UserInterfaceRoot;
	private DataBindingContext dataBindingContext;
	private CompositionNode child;
	private ConversationalPersistence persistence;
	public NewEntityWizard(CompositionNode newOne,OpaeumRapSession session){
		this.opaeumRapSession = session;
		setWindowTitle("New " + newOne.getClass().getSimpleName());
		this.child=newOne;
		persistence=session.getApplication().getEnvironment().createConversationalPersistence();
		ClassUserInteractionModel model=(ClassUserInteractionModel) session.getApplication().getUserInteractionModel(newOne.getClass().getAnnotation(NumlMetaInfo.class).uuid());
		this.UserInterfaceRoot = model.getNewObjectWizard();
		this.dataBindingContext = new DataBindingContext();
	}
	@Override
	public void addPages(){
		for(Page page:UserInterfaceRoot.getPages()){
			addPage(new NewEntityWizardPage(page, dataBindingContext, (IPersistentObject) child, opaeumRapSession));
		}
	}
	@Override
	public boolean performFinish(){
		boolean result = true;
		persistence.persist(child);
		persistence.flush();
		persistence.close();
		return result;
	}
}
