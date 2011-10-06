package org.opaeum.javageneration.jbpm5.actions;

import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.jbpm5.EventUtil;
import org.opaeum.javageneration.jbpm5.Jbpm5Util;
import org.opaeum.javageneration.maps.ActionMap;
import org.opaeum.metamodel.actions.INakedAcceptEventAction;
import org.opaeum.metamodel.bpm.INakedAcceptDeadlineAction;
import org.opaeum.metamodel.commonbehaviors.INakedChangeEvent;
import org.opaeum.metamodel.commonbehaviors.INakedTimeEvent;
import org.opaeum.metamodel.commonbehaviors.INakedTrigger;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public class AcceptEventActionBuilder extends Jbpm5ActionBuilder<INakedAcceptEventAction>{
	public AcceptEventActionBuilder(OpaeumLibrary oclEngine,INakedAcceptEventAction node){
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
