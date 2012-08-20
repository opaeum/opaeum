package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1;

import javax.persistence.Transient;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestState;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.ReservedToSuspended;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.Region1;
import org.opaeum.runtime.domain.TriggerMethod;
import org.opaeum.runtime.statemachines.StateActivation;

public class Reserved extends StateActivation<TaskRequest, TaskRequestToken> {
	static public String ID = "252060@_Q6NAcIoaEeCPduia_-NbFw";
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
	super(ID,region);
	}

	public TaskRequest getBehaviorExecution() {
		TaskRequest result = (TaskRequest)super.getBehaviorExecution();
		
		return result;
	}
	
	public String getHumanName() {
		String result = "Reserved";
		
		return result;
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
	
	public TriggerMethod[] getTriggerMethods() {
		TriggerMethod[] result = new TriggerMethod[]{new TriggerMethod(false,"Revoked","Revoked"),new TriggerMethod(false,"Suspended","Suspended"),new TriggerMethod(false,"Started","Started")};
		
		return result;
	}
	
	public boolean onCompletion() {
		boolean result = false;
		
		return result;
	}
	
	public void onEntry(TaskRequestToken token) {
		getStateMachineExecution().setHistory(ID);
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