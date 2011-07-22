package net.sf.nakeduml.javageneration.jbpm5.actions;

import net.sf.nakeduml.javageneration.basicjava.simpleactions.ActionMap;
import net.sf.nakeduml.javageneration.jbpm5.EventUtil;
import net.sf.nakeduml.metamodel.actions.INakedAcceptEventAction;
import net.sf.nakeduml.metamodel.bpm.INakedAcceptDeadlineAction;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedChangeEvent;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedEvent;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTimeEvent;
import net.sf.nakeduml.metamodel.workspace.NakedUmlLibrary;
import nl.klasse.octopus.oclengine.IOclEngine;

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
			if(node.getTrigger().getEvent() instanceof INakedTimeEvent){
				ActionMap map = new ActionMap(node);
				EventUtil.implementTimeEventRequest(operation, operation.getBody(),(INakedTimeEvent) node.getTrigger().getEvent());
				OJOperation cancel = new OJAnnotatedOperation();
				cancel.setName(map.getCancelEventsMethod());
				operation.getOwner().addToOperations(cancel);
				EventUtil.cancelTimer(cancel.getBody(), (INakedTimeEvent) node.getTrigger().getEvent(),"this");
			}else if(node.getTrigger().getEvent() instanceof INakedEvent){
				EventUtil.implementChangeEventRequest(operation, (INakedChangeEvent) node.getTrigger().getEvent());
				OJOperation cancel = new OJAnnotatedOperation();
				ActionMap map = new ActionMap(node);
				cancel.setName(map.getCancelEventsMethod());
				operation.getOwner().addToOperations(cancel);
				EventUtil.cancelChangeEvent(cancel.getBody(), (INakedChangeEvent) node.getTrigger().getEvent());
			}
		}
	}
	@Override
	public boolean waitsForEvent(){
		return true;
	}
}
