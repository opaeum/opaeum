package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.suspended.region1;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.suspended.Region1;
import org.opaeum.runtime.statemachines.StateActivation;

public class ReadyButSuspended extends StateActivation {


	/** Constructor for ReadyButSuspended
	 * 
	 * @param region 
	 */
	public ReadyButSuspended(Region1 region) {
	super("252060@_RC_JAIoaEeCPduia_-NbFw",region);
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