package net.sf.nakeduml.javageneration.jbpm5.actions;

import net.sf.nakeduml.javageneration.basicjava.simpleactions.ActionMap;
import net.sf.nakeduml.javageneration.jbpm5.Jbpm5Util;
import net.sf.nakeduml.metamodel.actions.INakedAcceptEventAction;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedChangeEvent;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTimeEvent;
import nl.klasse.octopus.oclengine.IOclEngine;

import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public class AcceptEventActionBuilder extends Jbpm5ActionBuilder<INakedAcceptEventAction>{
	public AcceptEventActionBuilder(IOclEngine oclEngine,INakedAcceptEventAction node){
		super(oclEngine, node);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation){
		if(node.getTrigger().getEvent() instanceof INakedTimeEvent){
			Jbpm5Util.implementTimeEventRequest(operation, (INakedTimeEvent) node.getTrigger().getEvent());
			OJOperation cancel = new OJAnnotatedOperation();
			ActionMap map = new ActionMap(node);
			cancel.setName(map.getCancelEventsMethod());
			operation.getOwner().addToOperations(cancel);
			Jbpm5Util.cancelTimer(cancel, (INakedTimeEvent) node.getTrigger().getEvent());
		}else if(node.getTrigger().getEvent() instanceof INakedChangeEvent){
			Jbpm5Util.implementChangeEventRequest(operation, (INakedChangeEvent) node.getTrigger().getEvent());
			OJOperation cancel = new OJAnnotatedOperation();
			ActionMap map = new ActionMap(node);
			cancel.setName(map.getCancelEventsMethod());
			operation.getOwner().addToOperations(cancel);
			Jbpm5Util.cancelChangeEvent(cancel, (INakedChangeEvent) node.getTrigger().getEvent());
		}
	}
	@Override
	public boolean waitsForEvent(){
		return true;
	}
}
