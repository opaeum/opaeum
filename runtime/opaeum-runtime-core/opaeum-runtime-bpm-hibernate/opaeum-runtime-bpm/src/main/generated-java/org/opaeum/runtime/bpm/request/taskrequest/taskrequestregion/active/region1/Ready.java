package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1;

import javax.persistence.Transient;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestState;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.ReadyToSuspended;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.Region1;
import org.opaeum.runtime.domain.TriggerMethod;
import org.opaeum.runtime.statemachines.StateActivation;

public class Ready extends StateActivation<TaskRequest, TaskRequestToken> {
	static public String ID = "252060@_Q0VB8IoaEeCPduia_-NbFw";
	@Transient
	private ReadyToReserved ReadyToReserved;
	@Transient
	private ReadyToSuspended ReadyToSuspended;

	/** Constructor for Ready
	 * 
	 * @param region 
	 */
	public Ready(Region1 region) {
	super(ID,region);
	}

	public TaskRequest getBehaviorExecution() {
		TaskRequest result = (TaskRequest)super.getBehaviorExecution();
		
		return result;
	}
	
	public String getHumanName() {
		String result = "Ready";
		
		return result;
	}
	
	public ReadyToReserved getReadyToReserved() {
		return this.ReadyToReserved;
	}
	
	public ReadyToSuspended getReadyToSuspended() {
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
		getStateMachineExecution().setHistory(ID);
	}
	
	public void setReadyToReserved(ReadyToReserved ReadyToReserved) {
		this.ReadyToReserved=ReadyToReserved;
	}
	
	public void setReadyToSuspended(ReadyToSuspended ReadyToSuspended) {
		this.ReadyToSuspended=ReadyToSuspended;
	}

}