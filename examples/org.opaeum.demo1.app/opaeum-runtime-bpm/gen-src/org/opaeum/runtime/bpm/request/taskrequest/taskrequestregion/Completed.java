package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion;

import java.util.Set;

import org.opaeum.runtime.bpm.organization.IParticipant;
import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.abstractrequest.region1.Complete;
import org.opaeum.runtime.bpm.request.taskrequest.TaskRequestRegion;
import org.opaeum.runtime.bpm.requestobject.IRequestObject;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.domain.TriggerMethod;

public class Completed<SME extends TaskRequest> extends Complete<SME> {
	static public String ID = "252060@_dAgD4IoaEeCPduia_-NbFw";

	/** Constructor for Completed
	 * 
	 * @param region 
	 */
	public Completed(TaskRequestRegion<SME> region) {
	super(region);
		getBehaviorExecution().getExecutionElements().put(ID, this);
	}

	public SME getBehaviorExecution() {
		SME result = (SME)super.getBehaviorExecution();
		
		return result;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		Set result = getBehaviorExecution().getCancelledEvents();
		
		return result;
	}
	
	public String getHumanName() {
		String result = "Completed";
		
		return result;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		Set result = getBehaviorExecution().getOutgoingEvents();
		
		return result;
	}
	
	public TriggerMethod[] getTriggerMethods() {
		TriggerMethod[] result = new TriggerMethod[]{};
		
		return result;
	}
	
	public boolean onCompletion() {
		boolean result = false;
		
		return result;
	}
	
	public void onEntry(TaskRequestToken token) {
		super.onEntry(token);
		IRequestObject tgtCallOperationAction1=getStateMachineExecution().getTaskObject();
		tgtCallOperationAction1.onCompleted((IParticipant)org.opeum.demo1.util.Demo1Environment.INSTANCE.getCurrentRole());
		token.setHasRunToCompletion(true);
	}
	
	public void onExit() {
	}

}