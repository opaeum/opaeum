package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.TaskRequestRegion;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class ActiveToInProgress extends TransitionInstance<TaskRequest, TaskRequestToken> {
	static public String ID = "252060@_JhMXAKDNEeCv9IRqC7lfYw";

	/** Constructor for ActiveToInProgress
	 * 
	 * @param regionActivation 
	 */
	public ActiveToInProgress(TaskRequestRegion regionActivation) {
	super(ID,regionActivation,"252060@_XwDj4KCVEeCmJqvPP4zbUw","252060@_RMUEIIoaEeCPduia_-NbFw");
		((Active)getSource()).setActiveToInProgress(this);
	}

	public boolean consumeStartOccurrence() {
		boolean result = false;
		result=true;
		TaskRequestToken token= getMainSource().exit();
		getMainTarget().enter(token,target);
		return result;
	}
	
	public TaskRequest getBehaviorExecution() {
		TaskRequest result = (TaskRequest)super.getBehaviorExecution();
		
		return result;
	}

}