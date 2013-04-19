package org.opaeum.runtime.jface.wizards;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.wizard.Wizard;
import org.opaeum.runtime.domain.IBehaviorExecution;
import org.opaeum.runtime.domain.IExecutable;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.event.IEventHandler;
import org.opaeum.runtime.jface.entityeditor.EntityEditorInputJface;
import org.opaeum.runtime.rwt.OpaeumRapSession;
import org.opaeum.uim.Page;
import org.opaeum.uim.UserInterfaceRoot;

public class InvocationWizard extends Wizard{
	OpaeumRapSession opaeumRapSession;
	Collection<IPersistentObject> target;
	Object eventHandler;
	private UserInterfaceRoot UserInterfaceRoot;
	private EntityEditorInputJface input;
	private DataBindingContext dataBindingContext;
	public InvocationWizard(List<IPersistentObject> target,Object eventHandler,UserInterfaceRoot UserInterfaceRoot,
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
	public InvocationWizard(IPersistentObject target,Object eventHandler,UserInterfaceRoot UserInterfaceRoot,
			EntityEditorInputJface input){
		this(Collections.singletonList(target), eventHandler, UserInterfaceRoot, input);
	}
	@Override
	public void addPages(){
		for(Page page:UserInterfaceRoot.getPages()){
			addPage(new InvocationWizardPage(page, dataBindingContext, target.iterator().next(), eventHandler, opaeumRapSession));
		}
	}
	@Override
	public boolean performFinish(){
		boolean result = true;
		for(IPersistentObject po:target){
			if(eventHandler instanceof IEventHandler){
				result &= ((IEventHandler) eventHandler).handleOn(po, input.getPersistence());
			}else if(eventHandler instanceof IExecutable){
				//Should only be called once
				((IExecutable) eventHandler).execute();
				result =true;
			}
		}
		if(result){
			input.setDirty(true);
		}
		return result;
	}
}
