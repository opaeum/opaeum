package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1;

import javax.persistence.Transient;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestState;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.ReservedToSuspended;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.Region1;
import org.opaeum.runtime.statemachines.StateActivation;
import org.opaeum.runtime.statemachines.StateMachineToken;

public class Reserved extends StateActivation {
	@Transient
	private ReservedToInProgress ReservedToInProgress;
	@Transient
	private ReservedToReady ReservedToReady;
	@Transient
	private ReservedToSuspended ReservedToSuspended;

	/** Constructor for Reserved
	 * 
	 * @param region 
	 */
	public Reserved(Region1 region) {
	super("252060@_Q6NAcIoaEeCPduia_-NbFw",region);
	}

	public ReservedToInProgress getReservedToInProgress() {
		return this.ReservedToInProgress;
	}
	
	public ReservedToReady getReservedToReady() {
		return this.ReservedToReady;
	}
	
	public ReservedToSuspended getReservedToSuspended() {
		return this.ReservedToSuspended;
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
		getStateMachineExecution().setHistory("252060@_Q6NAcIoaEeCPduia_-NbFw");
		token.fireCompletionEvent();
	}
	
	public void setReservedToInProgress(ReservedToInProgress ReservedToInProgress) {
		this.ReservedToInProgress=ReservedToInProgress;
	}
	
	public void setReservedToReady(ReservedToReady ReservedToReady) {
		this.ReservedToReady=ReservedToReady;
	}
	
	public void setReservedToSuspended(ReservedToSuspended ReservedToSuspended) {
		this.ReservedToSuspended=ReservedToSuspended;
	}

}