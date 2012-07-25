package org.opaeum.javageneration.jbpm5.actions;

import org.eclipse.uml2.uml.AcceptEventAction;
import org.eclipse.uml2.uml.ChangeEvent;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.Trigger;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.jbpm5.EventUtil;
import org.opaeum.javageneration.jbpm5.Jbpm5Util;
import org.opaeum.javageneration.maps.ActionMap;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public class AcceptEventActionBuilder extends Jbpm5ActionBuilder<AcceptEventAction>{
	public AcceptEventActionBuilder(OpaeumLibrary oclEngine,AcceptEventAction node){
		super(oclEngine, node);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation){
		if(!(node instanceof AcceptDeadlineAction)){
			// Deadlines and their cancellations are fired from the originating task message structure
			if(node.requiresEventRequest()){
				ActionMap actionMap = new ActionMap(node);
				OJOperation cancel = new OJAnnotatedOperation(actionMap.getCancelEventsMethod());
				cancel.addParam("context", Jbpm5Util.getProcessContext());
				operation.getOwner().addToOperations(cancel);
				for(Trigger t:node.getTriggers()){
					if(t.getEvent() instanceof TimeEvent){
						eventUtil.implementTimeEventRequest(operation, operation.getBody(), (TimeEvent) t.getEvent(),getLibrary().getBusinessRole()!=null);
						EventUtil.cancelTimer(cancel.getBody(), (TimeEvent) t.getEvent(), "this");
					}else if(t.getEvent() instanceof ChangeEvent){
						eventUtil.implementChangeEventRequest(operation, (ChangeEvent) t.getEvent());
						EventUtil.cancelChangeEvent(cancel.getBody(), (ChangeEvent) t.getEvent());
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
