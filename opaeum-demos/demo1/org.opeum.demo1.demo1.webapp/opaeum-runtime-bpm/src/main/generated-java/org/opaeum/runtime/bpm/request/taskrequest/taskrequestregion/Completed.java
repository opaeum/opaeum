package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestState;
import org.opaeum.runtime.bpm.request.taskrequest.TaskRequestRegion;
import org.opaeum.runtime.statemachines.StateActivation;
import org.opaeum.runtime.statemachines.StateMachineToken;

public class Completed extends StateActivation {


	/** Constructor for Completed
	 * 
	 * @param region 
	 */
	public Completed(TaskRequestRegion region) {
	super("252060@_dAgD4IoaEeCPduia_-NbFw",region);
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
	}

}