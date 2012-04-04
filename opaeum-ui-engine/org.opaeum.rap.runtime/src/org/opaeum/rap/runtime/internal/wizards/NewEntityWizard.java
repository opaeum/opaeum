package org.opaeum.rap.runtime.internal.wizards;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.wizard.Wizard;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.rap.runtime.OpaeumRapSession;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.persistence.ConversationalPersistence;
import org.opaeum.uim.ClassUserInteractionModel;
import org.opaeum.uim.Page;
import org.opaeum.uim.PageContainer;

public class NewEntityWizard extends Wizard{
	private OpaeumRapSession opaeumRapSession;
	private PageContainer pageContainer;
	private DataBindingContext dataBindingContext;
	private CompositionNode child;
	private ConversationalPersistence persistence;
	public NewEntityWizard(CompositionNode newOne,OpaeumRapSession session){
		this.opaeumRapSession = session;
		setWindowTitle("New " + newOne.getClass().getSimpleName());
		this.child=newOne;
		persistence=session.getApplication().getEnvironment().createConversationalPersistence();
		Resource resource = session.getApplication().getUimResource(newOne.getClass().getAnnotation(NumlMetaInfo.class).uuid());
		ClassUserInteractionModel model=(ClassUserInteractionModel) resource.getContents().get(0);
		this.pageContainer = model.getNewObjectWizard();
		this.dataBindingContext = new DataBindingContext();
	}
	@Override
	public void addPages(){
		for(Page page:pageContainer.getPages()){
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
