package org.opaeum.papyrus.uml.diagram.businessprocess.edit.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.papyrus.uml.diagram.activity.edit.commands.CallBehaviorActionCreateCommand;
import org.eclipse.papyrus.uml.diagram.activity.providers.ElementInitializers;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.UMLFactory;

public final class OpaeumCallBehaviorActionCreateCommand extends CallBehaviorActionCreateCommand{
	public OpaeumCallBehaviorActionCreateCommand(CreateElementRequest req){
		super(req);
	}
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,IAdaptable info) throws ExecutionException{
		//TODO implement the creation logic the filtering out of scope elements and using this process or its context as context
		CallBehaviorAction newElement = UMLFactory.eINSTANCE.createCallBehaviorAction();
		Activity owner = (Activity)getElementToEdit();
		owner.getOwnedNodes().add(newElement);
		ElementInitializers.getInstance().init_CallBehaviorAction_3008(newElement);
		doConfigure(newElement, monitor, info);
		((CreateElementRequest)getRequest()).setNewElement(newElement);
		return CommandResult.newOKCommandResult(newElement);
		
	}
}