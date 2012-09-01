package org.opaeum.topcased.statemachinediagram;

import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.StateMachine;
import org.topcased.modeler.uml.statemachinediagram.commands.CreateSTMDiagramCommand;

public class CreateStateMachineDiagramFromCallBehaviorAction extends CreateSTMDiagramCommand{
	private Activity parentActivity;
	private CallBehaviorAction callBehaviorAction;
	private Behavior oldBehavior;
	private CreateStateMachineParametersCommand createStateMachineParameters;
	public EObject getLinkedElementWithDiagram(EObject initialObject){
		if(initialObject instanceof CallBehaviorAction){
			callBehaviorAction = (CallBehaviorAction) initialObject;
			parentActivity = getActivityAncestor(callBehaviorAction);
			oldBehavior = callBehaviorAction.getBehavior();
			setStateMachineName(callBehaviorAction.getName() + "Impl");
			StateMachine sm = getStateMachine();
			if(StereotypesHelper.hasStereotype(callBehaviorAction, StereotypeNames.EMBEDDED_SCREEN_FLOW_TASK)){
				StereotypesHelper.getNumlAnnotation(sm).getDetails().put(StereotypeNames.SCREEN_FLOW, "");
			}else if(StereotypesHelper.hasStereotype(callBehaviorAction, StereotypeNames.CALL_BUSINESS_STATE_MACHINE_ACTION)){
				StereotypesHelper.getNumlAnnotation(sm).getDetails().put(StereotypeNames.BUSINESS_STATE_MACHINE, "");
			}
			return sm;
		}
		return initialObject;
	}
	private Activity getActivityAncestor(Element pElement){
		Element owner = pElement.getOwner();
		while(!(owner == null || owner instanceof Activity)){
			owner = pElement.getOwner();
		}
		return (Activity) owner;
	}
	@Override
	public void execute(){
		redo();
	}
	@Override
	public void redo(){
		parentActivity.getOwnedBehaviors().add(getStateMachine());
		createStateMachineParameters = new CreateStateMachineParametersCommand(callBehaviorAction, getStateMachine());
		if(createStateMachineParameters.canExecute()){
			createStateMachineParameters.execute();
		}
		callBehaviorAction.setBehavior(getStateMachine());
	}
	@Override
	public void undo(){
		callBehaviorAction.setBehavior(oldBehavior);
		if(createStateMachineParameters != null){
			createStateMachineParameters.undo();
		}

		parentActivity.getOwnedBehaviors().remove(getStateMachine());
	}
}
