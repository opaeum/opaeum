package org.opaeum.rap.runtime.internal.wizards;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.wizard.Wizard;
import org.opaeum.rap.runtime.OpaeumRapSession;
import org.opaeum.rap.runtime.internal.editors.EntityEditorInputJface;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.event.IEventHandler;
import org.opaeum.uim.Page;
import org.opaeum.uim.UserInterfaceRoot;

public class OperationInvocationWizard extends Wizard{
	OpaeumRapSession opaeumRapSession;
	Collection<IPersistentObject> target;
	IEventHandler eventHandler;
	private UserInterfaceRoot UserInterfaceRoot;
	private EntityEditorInputJface input;
	private DataBindingContext dataBindingContext;
	public OperationInvocationWizard(List<IPersistentObject> target,IEventHandler eventHandler,UserInterfaceRoot UserInterfaceRoot,
			EntityEditorInputJface input){
		this.opaeumRapSession = input.getOpaeumSession();
		setWindowTitle("Execute "
				+ eventHandler.getClass().getSimpleName().substring(0, eventHandler.getClass().getSimpleName().indexOf("Handler")));
		this.target = target;
		this.eventHandler = eventHandler;
		this.UserInterfaceRoot = UserInterfaceRoot;
		this.input = input;
		this.dataBindingContext = new DataBindingContext();
	}
	public OperationInvocationWizard(IPersistentObject target,IEventHandler eventHandler,UserInterfaceRoot UserInterfaceRoot,EntityEditorInputJface input){
		this(Collections.singletonList(target), eventHandler, UserInterfaceRoot, input);
	}
	@Override
	public void addPages(){
		for(Page page:UserInterfaceRoot.getPages()){
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
