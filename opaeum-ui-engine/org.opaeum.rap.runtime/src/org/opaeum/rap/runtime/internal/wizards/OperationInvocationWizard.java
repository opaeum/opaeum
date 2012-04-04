package org.opaeum.rap.runtime.internal.wizards;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.wizard.Wizard;
import org.opaeum.rap.runtime.OpaeumRapSession;
import org.opaeum.rap.runtime.internal.editors.EntityEditorInput;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.event.IEventHandler;
import org.opaeum.uim.Page;
import org.opaeum.uim.PageContainer;

public class OperationInvocationWizard extends Wizard{
	OpaeumRapSession opaeumRapSession;
	Collection<IPersistentObject> target;
	IEventHandler eventHandler;
	private PageContainer pageContainer;
	private EntityEditorInput input;
	private DataBindingContext dataBindingContext;
	public OperationInvocationWizard(IPersistentObject target,IEventHandler eventHandler,PageContainer pageContainer,EntityEditorInput input){
		this.opaeumRapSession = input.getOpaeumSession();
		setWindowTitle("Execute "
				+ eventHandler.getClass().getSimpleName().substring(0, eventHandler.getClass().getSimpleName().indexOf("Handler")));
		this.target = Collections.singleton(target);
		this.eventHandler = eventHandler;
		this.pageContainer = pageContainer;
		this.input = input;
		this.dataBindingContext = new DataBindingContext();
	}
	@Override
	public void addPages(){
		for(Page page:pageContainer.getPages()){
			addPage(new OperationInvocationWizardPage(page, dataBindingContext, target.iterator().next(), eventHandler, opaeumRapSession));
		}
	}
	@Override
	public boolean performFinish(){
		boolean result = true;
		for(IPersistentObject po:target){
			result &= eventHandler.handleOn(po);
		}
		if(result){
			input.setDirty(true);
		}
		return result;
	}
}
