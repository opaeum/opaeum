package net.sf.nakeduml.javageneration.jbpm5.actions;

import net.sf.nakeduml.javageneration.jbpm5.EventUtil;
import net.sf.nakeduml.javageneration.jbpm5.Jbpm5Util;
import net.sf.nakeduml.javageneration.maps.ActionMap;
import net.sf.nakeduml.metamodel.actions.INakedAcceptEventAction;
import net.sf.nakeduml.metamodel.bpm.INakedAcceptDeadlineAction;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedChangeEvent;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTimeEvent;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTrigger;
import net.sf.nakeduml.metamodel.workspace.NakedUmlLibrary;

import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public class AcceptEventActionBuilder extends Jbpm5ActionBuilder<INakedAcceptEventAction>{
	public AcceptEventActionBuilder(NakedUmlLibrary oclEngine,INakedAcceptEventAction node){
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
