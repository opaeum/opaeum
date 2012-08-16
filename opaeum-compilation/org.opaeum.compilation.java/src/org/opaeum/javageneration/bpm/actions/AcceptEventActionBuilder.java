package org.opaeum.javageneration.bpm.actions;

import org.eclipse.uml2.uml.AcceptEventAction;
import org.eclipse.uml2.uml.ChangeEvent;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.Trigger;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.bpm.EventUtil;
import org.opaeum.javageneration.maps.ActionMap;
import org.opaeum.javageneration.util.OJUtil;

public class AcceptEventActionBuilder extends Jbpm5ActionBuilder<AcceptEventAction>{
	public AcceptEventActionBuilder(OJUtil ojUtil,AcceptEventAction node){
		super(ojUtil, node);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation){
		if(!(EmfActionUtil.acceptsDeadline(node))){
			// Deadlines and their cancellations are fired from the originating task message structure
			ActionMap actionMap = ojUtil.buildActionMap(node);
			OJOperation cancel = new OJAnnotatedOperation(actionMap.getCancelEventsMethod());
			operation.getOwner().addToOperations(cancel);
			for(Trigger t:node.getTriggers()){
				if(t.getEvent() instanceof TimeEvent){
					eventUtil.implementTimeEventRequest(operation, operation.getBody(), (TimeEvent) t.getEvent(),
							getLibrary().getBusinessRole() != null);
					EventUtil.cancelTimer(cancel.getBody(), (TimeEvent) t.getEvent(), "this");
				}else if(t.getEvent() instanceof ChangeEvent){
					eventUtil.implementChangeEventRequest(operation, (ChangeEvent) t.getEvent());
					EventUtil.cancelChangeEvent(cancel.getBody(), (ChangeEvent) t.getEvent());
				}
			}
		}
	}
	@Override
	public boolean waitsForEvent(){
		return true;
	}
}
