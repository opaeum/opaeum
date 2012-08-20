package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1;

import javax.persistence.Transient;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestState;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.InProgressToSuspended;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.Region1;
import org.opaeum.runtime.domain.TriggerMethod;
import org.opaeum.runtime.statemachines.StateActivation;

public class InProgress extends StateActivation<TaskRequest, TaskRequestToken> {
	static public String ID = "252060@_RMUEIIoaEeCPduia_-NbFw";
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
	super(ID,region);
	}

	public TaskRequest getBehaviorExecution() {
		TaskRequest result = (TaskRequest)super.getBehaviorExecution();
		
		return result;
	}
	
	public String getHumanName() {
		String result = "In progress";
		
		return result;
	}
	
	public InProgressToReserved getInProgressToReserved() {
		return this.InProgressToReserved;
	}
	
	public InProgressToSuspended getInProgressToSuspended() {
		return this.InProgressToSuspended;
	}
	
	public Transition5 getTransition5() {
		return this.Transition5;
	}
	
	public TriggerMethod[] getTriggerMethods() {
		TriggerMethod[] result = new TriggerMethod[]{new TriggerMethod(false,"Stopped","Stopped"),new TriggerMethod(false,"On complete","OnComplete"),new TriggerMethod(false,"Suspended","Suspended")};
		
		return result;
	}
	
	public boolean onCompletion() {
		boolean result = false;
		
		return result;
	}
	
	public void onEntry(TaskRequestToken token) {
		getStateMachineExecution().setHistory(ID);
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