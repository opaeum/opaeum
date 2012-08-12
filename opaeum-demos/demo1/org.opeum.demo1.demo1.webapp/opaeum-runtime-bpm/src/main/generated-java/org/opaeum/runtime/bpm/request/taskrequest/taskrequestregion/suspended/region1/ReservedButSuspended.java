package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.suspended.region1;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestState;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.suspended.Region1;
import org.opaeum.runtime.statemachines.StateActivation;

public class ReservedButSuspended extends StateActivation {


	/** Constructor for ReservedButSuspended
	 * 
	 * @param region 
	 */
	public ReservedButSuspended(Region1 region) {
	super("252060@_sFnzcIobEeCPduia_-NbFw",region);
	}

	public TaskRequest getStateMachineExecution() {
		TaskRequest result = (TaskRequest)super.getStateMachineExecution();
		
		return result;
	}
	
	public boolean onComplete() {
		boolean result = false;
		
		return result;
	}

}