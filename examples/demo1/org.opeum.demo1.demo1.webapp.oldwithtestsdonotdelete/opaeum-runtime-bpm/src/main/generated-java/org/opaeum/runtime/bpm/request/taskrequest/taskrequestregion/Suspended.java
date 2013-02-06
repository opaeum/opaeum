package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion;

import javax.persistence.Transient;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.taskrequest.TaskRequestRegion;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.suspended.Region1;
import org.opaeum.runtime.statemachines.StateActivation;

public class Suspended extends StateActivation {
	@Transient
	private SuspendedToActive SuspendedToActive;

	/** Constructor for Suspended
	 * 
	 * @param region 
	 */
	public Suspended(TaskRequestRegion region) {
	super("252060@_eXmqMKCWEeCmJqvPP4zbUw",region);
		regions.add(new Region1(this));
	}

	public TaskRequest getStateMachineExecution() {
		TaskRequest result = (TaskRequest)super.getStateMachineExecution();
		
		return result;
	}
	
	public SuspendedToActive getSuspendedToActive() {
		return this.SuspendedToActive;
	}
	
	public boolean onComplete() {
		boolean result = false;
		
		return result;
	}
	
	public void setSuspendedToActive(SuspendedToActive SuspendedToActive) {
		this.SuspendedToActive=SuspendedToActive;
	}

}