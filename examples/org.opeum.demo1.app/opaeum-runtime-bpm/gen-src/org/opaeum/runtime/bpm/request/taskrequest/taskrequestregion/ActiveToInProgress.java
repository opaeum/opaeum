package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.TaskRequestRegion;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1.InProgress;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class ActiveToInProgress<SME extends TaskRequest> extends TransitionInstance<SME, TaskRequestToken<SME>> {
	static public String ID = "252060@_JhMXAKDNEeCv9IRqC7lfYw";

	/** Constructor for ActiveToInProgress
	 * 
	 * @param regionActivation 
	 */
	public ActiveToInProgress(TaskRequestRegion regionActivation) {
	super(ID,regionActivation,Active.ID,InProgress.ID);
		((Active)getSource()).setActiveToInProgress(this);
	}

	public boolean consumeStartOccurrence() {
		boolean result = false;
		result=true;
		TaskRequestToken<SME> token= getMainSource().exit();
		super.onEntry(token);
		getMainTarget().enter(token,target);
		super.onExit(token);
		return result;
	}
	
	public SME getBehaviorExecution() {
		SME result = (SME)super.getBehaviorExecution();
		
		return result;
	}

}