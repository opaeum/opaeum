package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1;

import java.util.Set;

import javax.persistence.Transient;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestState;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.ReadyToSuspended;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.Region1;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.domain.TriggerMethod;
import org.opaeum.runtime.statemachines.StateActivation;

public class Ready<SME extends TaskRequest> extends StateActivation<SME, TaskRequestToken<SME>> {
	static public String ID = "252060@_Q0VB8IoaEeCPduia_-NbFw";
	@Transient
	private ReadyToReserved<SME> ReadyToReserved;
	@Transient
	private ReadyToSuspended<SME> ReadyToSuspended;

	/** Constructor for Ready
	 * 
	 * @param region 
	 */
	public Ready(Region1<SME> region) {
	super(ID,region);
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
		String result = "Ready";
		
		return result;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		Set result = getBehaviorExecution().getOutgoingEvents();
		
		return result;
	}
	
	public ReadyToReserved<SME> getReadyToReserved() {
		return this.ReadyToReserved;
	}
	
	public ReadyToSuspended<SME> getReadyToSuspended() {
		return this.ReadyToSuspended;
	}
	
	public TriggerMethod[] getTriggerMethods() {
		TriggerMethod[] result = new TriggerMethod[]{new TriggerMethod(false,"Suspended","Suspended"),new TriggerMethod(false,"Claimed","Claimed")};
		
		return result;
	}
	
	public boolean onCompletion() {
		boolean result = false;
		
		return result;
	}
	
	public void onEntry(TaskRequestToken token) {
		super.onEntry(token);
		getStateMachineExecution().setHistory(ID);
	}
	
	public void setReadyToReserved(ReadyToReserved<SME> ReadyToReserved) {
		this.ReadyToReserved=ReadyToReserved;
	}
	
	public void setReadyToSuspended(ReadyToSuspended<SME> ReadyToSuspended) {
		this.ReadyToSuspended=ReadyToSuspended;
	}

}