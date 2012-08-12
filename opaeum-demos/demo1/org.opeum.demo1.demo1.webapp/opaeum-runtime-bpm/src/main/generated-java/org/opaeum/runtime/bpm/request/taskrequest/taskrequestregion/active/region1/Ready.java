package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1;

import javax.persistence.Transient;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestState;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.ReadyToSuspended;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.Region1;
import org.opaeum.runtime.statemachines.StateActivation;
import org.opaeum.runtime.statemachines.StateMachineToken;

public class Ready extends StateActivation {
	@Transient
	private ReadyToReserved ReadyToReserved;
	@Transient
	private ReadyToSuspended ReadyToSuspended;

	/** Constructor for Ready
	 * 
	 * @param region 
	 */
	public Ready(Region1 region) {
	super("252060@_Q0VB8IoaEeCPduia_-NbFw",region);
	}

	public ReadyToReserved getReadyToReserved() {
		return this.ReadyToReserved;
	}
	
	public ReadyToSuspended getReadyToSuspended() {
		return this.ReadyToSuspended;
	}
	
	public TaskRequest getStateMachineExecution() {
		TaskRequest result = (TaskRequest)super.getStateMachineExecution();
		
		return result;
	}
	
	public boolean onComplete() {
		boolean result = false;
		
		return result;
	}
	
	public void onEntry(StateMachineToken token) {
		getStateMachineExecution().setHistory("252060@_Q0VB8IoaEeCPduia_-NbFw");
		token.fireCompletionEvent();
	}
	
	public void setReadyToReserved(ReadyToReserved ReadyToReserved) {
		this.ReadyToReserved=ReadyToReserved;
	}
	
	public void setReadyToSuspended(ReadyToSuspended ReadyToSuspended) {
		this.ReadyToSuspended=ReadyToSuspended;
	}

}