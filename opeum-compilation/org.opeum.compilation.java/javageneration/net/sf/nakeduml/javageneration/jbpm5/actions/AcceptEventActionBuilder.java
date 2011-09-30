package org.opeum.javageneration.jbpm5.actions;

import org.opeum.javageneration.jbpm5.EventUtil;
import org.opeum.javageneration.jbpm5.Jbpm5Util;
import org.opeum.javageneration.maps.ActionMap;
import org.opeum.metamodel.actions.INakedAcceptEventAction;
import org.opeum.metamodel.bpm.INakedAcceptDeadlineAction;
import org.opeum.metamodel.commonbehaviors.INakedChangeEvent;
import org.opeum.metamodel.commonbehaviors.INakedTimeEvent;
import org.opeum.metamodel.commonbehaviors.INakedTrigger;
import org.opeum.metamodel.workspace.OpeumLibrary;

import org.opeum.java.metamodel.OJOperation;
import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;

public class AcceptEventActionBuilder extends Jbpm5ActionBuilder<INakedAcceptEventAction>{
	public AcceptEventActionBuilder(OpeumLibrary oclEngine,INakedAcceptEventAction node){
		super(oclEngine, node);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation){
		if(!(node instanceof INakedAcceptDeadlineAction)){
			// Deadlines and their cancellations are fired from the originating task message structure
			if(node.requiresEventRequest()){
				ActionMap actionMap = new ActionMap(node);
				OJOperation cancel = new OJAnnotatedOperation(actionMap.getCancelEventsMethod());
				cancel.addParam("context", Jbpm5Util.getProcessContext());
				operation.getOwner().addToOperations(cancel);
				for(INakedTrigger t:node.getTriggers()){
					if(t.getEvent() instanceof INakedTimeEvent){
						EventUtil.implementTimeEventRequest(operation, operation.getBody(), (INakedTimeEvent) t.getEvent(),getLibrary().getBusinessRole()!=null);
						EventUtil.cancelTimer(cancel.getBody(), (INakedTimeEvent) t.getEvent(), "this");
					}else if(t.getEvent() instanceof INakedChangeEvent){
						EventUtil.implementChangeEventRequest(operation, (INakedChangeEvent) t.getEvent());
						EventUtil.cancelChangeEvent(cancel.getBody(), (INakedChangeEvent) t.getEvent());
					}
				}
			}
		}
	}
	@Override
	public boolean waitsForEvent(){
		return true;
	}
}
