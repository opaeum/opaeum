package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1;

import java.util.Set;

import javax.persistence.Transient;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestState;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.ReservedToSuspended;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.Region1;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.domain.TriggerMethod;
import org.opaeum.runtime.statemachines.StateActivation;

public class Reserved<SME extends TaskRequest> extends StateActivation<SME, TaskRequestToken<SME>> {
	static public String ID = "252060@_Q6NAcIoaEeCPduia_-NbFw";
	@Transient
	private ReservedToInProgress<SME> ReservedToInProgress;
	@Transient
	private ReservedToReady<SME> ReservedToReady;
	@Transient
	private ReservedToSuspended<SME> ReservedToSuspended;

	/** Constructor for Reserved
	 * 
	 * @param region 
	 */
	public Reserved(Region1<SME> region) {
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
		String result = "Reserved";
		
		return result;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		Set result = getBehaviorExecution().getOutgoingEvents();
		
		return result;
	}
	
	public ReservedToInProgress<SME> getReservedToInProgress() {
		return this.ReservedToInProgress;
	}
	
	public ReservedToReady<SME> getReservedToReady() {
		return this.ReservedToReady;
	}
	
	public ReservedToSuspended<SME> getReservedToSuspended() {
		return this.ReservedToSuspended;
	}
	
	public TriggerMethod[] getTriggerMethods() {
		TriggerMethod[] result = new TriggerMethod[]{new TriggerMethod(false,"Revoked","Revoked"),new TriggerMethod(false,"Suspended","Suspended"),new TriggerMethod(false,"Started","Started")};
		
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
	
	public void setReservedToInProgress(ReservedToInProgress<SME> ReservedToInProgress) {
		this.ReservedToInProgress=ReservedToInProgress;
	}
	
	public void setReservedToReady(ReservedToReady<SME> ReservedToReady) {
		this.ReservedToReady=ReservedToReady;
	}
	
	public void setReservedToSuspended(ReservedToSuspended<SME> ReservedToSuspended) {
		this.ReservedToSuspended=ReservedToSuspended;
	}

}