package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.Region1;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class HistoryToNumberOfPotentialOwners extends TransitionInstance<TaskRequest, TaskRequestToken> {
	static public String ID = "252060@_oYvoEKDPEeCv9IRqC7lfYw";

	/** Constructor for HistoryToNumberOfPotentialOwners
	 * 
	 * @param regionActivation 
	 */
	public HistoryToNumberOfPotentialOwners(Region1 regionActivation) {
	super(ID,regionActivation,"252060@_UBMQAKCWEeCmJqvPP4zbUw","252060@_t3M00KDPEeCv9IRqC7lfYw");
		((History)getSource()).setHistoryToNumberOfPotentialOwners(this);
	}

	public TaskRequest getBehaviorExecution() {
		TaskRequest result = (TaskRequest)super.getBehaviorExecution();
		
		return result;
	}
	
	public boolean onHistoryCompleted() {
		boolean result = false;
		result=true;
		TaskRequestToken token= getMainSource().exit();
		getMainTarget().enter(token,target);
		return result;
	}

}