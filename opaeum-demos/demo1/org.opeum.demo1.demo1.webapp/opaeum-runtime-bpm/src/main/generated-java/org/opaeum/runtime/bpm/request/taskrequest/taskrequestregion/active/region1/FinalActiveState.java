package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestState;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.Region1;
import org.opaeum.runtime.statemachines.StateActivation;
import org.opaeum.runtime.statemachines.StateMachineToken;

public class FinalActiveState extends StateActivation {


	/** Constructor for FinalActiveState
	 * 
	 * @param region 
	 */
	public FinalActiveState(Region1 region) {
	super("252060@_YxQFsKCWEeCmJqvPP4zbUw",region);
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
		getStateMachineExecution().setHistory(null);
		token.getParentToken().fireCompletionEvent();
	}

}