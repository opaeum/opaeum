package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestState;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.Region1;
import org.opaeum.runtime.domain.TriggerMethod;
import org.opaeum.runtime.statemachines.StateActivation;

public class FinalActiveState extends StateActivation<TaskRequest, TaskRequestToken> {
	static public String ID = "252060@_YxQFsKCWEeCmJqvPP4zbUw";

	/** Constructor for FinalActiveState
	 * 
	 * @param region 
	 */
	public FinalActiveState(Region1 region) {
	super(ID,region);
	}

	public TaskRequest getBehaviorExecution() {
		TaskRequest result = (TaskRequest)super.getBehaviorExecution();
		
		return result;
	}
	
	public String getHumanName() {
		String result = "Final active state";
		
		return result;
	}
	
	public TriggerMethod[] getTriggerMethods() {
		TriggerMethod[] result = new TriggerMethod[]{};
		
		return result;
	}
	
	public boolean onCompletion() {
		boolean result = false;
		
		return result;
	}
	
	public void onEntry(TaskRequestToken token) {
		getStateMachineExecution().setHistory(null);
		token.setHasRunToCompletion(true);
		((TaskRequestToken)token.getParentToken()).fireCompletionEvent();
	}

}