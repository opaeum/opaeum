package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1;

import javax.persistence.Transient;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestState;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.InProgressToSuspended;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.Region1;
import org.opaeum.runtime.statemachines.StateActivation;
import org.opaeum.runtime.statemachines.StateMachineToken;

public class InProgress extends StateActivation {
	@Transient
	private InProgressToReserved InProgressToReserved;
	@Transient
	private InProgressToSuspended InProgressToSuspended;
	@Transient
	private Transition5 Transition5;

	/** Constructor for InProgress
	 * 
	 * @param region 
	 */
	public InProgress(Region1 region) {
	super("252060@_RMUEIIoaEeCPduia_-NbFw",region);
	}

	public InProgressToReserved getInProgressToReserved() {
		return this.InProgressToReserved;
	}
	
	public InProgressToSuspended getInProgressToSuspended() {
		return this.InProgressToSuspended;
	}
	
	public TaskRequest getStateMachineExecution() {
		TaskRequest result = (TaskRequest)super.getStateMachineExecution();
		
		return result;
	}
	
	public Transition5 getTransition5() {
		return this.Transition5;
	}
	
	public boolean onComplete() {
		boolean result = false;
		
		return result;
	}
	
	public void onEntry(StateMachineToken token) {
		getStateMachineExecution().setHistory("252060@_RMUEIIoaEeCPduia_-NbFw");
		token.fireCompletionEvent();
	}
	
	public void setInProgressToReserved(InProgressToReserved InProgressToReserved) {
		this.InProgressToReserved=InProgressToReserved;
	}
	
	public void setInProgressToSuspended(InProgressToSuspended InProgressToSuspended) {
		this.InProgressToSuspended=InProgressToSuspended;
	}
	
	public void setTransition5(Transition5 Transition5) {
		this.Transition5=Transition5;
	}

}