package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion;

import javax.persistence.Transient;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestState;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.TaskRequestRegion;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.suspended.Region1;
import org.opaeum.runtime.domain.TriggerMethod;
import org.opaeum.runtime.statemachines.StateActivation;

public class Suspended extends StateActivation<TaskRequest, TaskRequestToken> {
	static public String ID = "252060@_eXmqMKCWEeCmJqvPP4zbUw";
	@Transient
	private SuspendedToActive SuspendedToActive;

	/** Constructor for Suspended
	 * 
	 * @param region 
	 */
	public Suspended(TaskRequestRegion region) {
	super(ID,region);
		regions.add(new Region1(this));
	}

	public TaskRequest getBehaviorExecution() {
		TaskRequest result = (TaskRequest)super.getBehaviorExecution();
		
		return result;
	}
	
	public String getHumanName() {
		String result = "Suspended";
		
		return result;
	}
	
	public SuspendedToActive getSuspendedToActive() {
		return this.SuspendedToActive;
	}
	
	public TriggerMethod[] getTriggerMethods() {
		TriggerMethod[] result = new TriggerMethod[]{new TriggerMethod(false,"Resumed","Resumed")};
		
		return result;
	}
	
	public boolean onCompletion() {
		boolean result = false;
		
		return result;
	}
	
	public void setSuspendedToActive(SuspendedToActive SuspendedToActive) {
		this.SuspendedToActive=SuspendedToActive;
	}

}