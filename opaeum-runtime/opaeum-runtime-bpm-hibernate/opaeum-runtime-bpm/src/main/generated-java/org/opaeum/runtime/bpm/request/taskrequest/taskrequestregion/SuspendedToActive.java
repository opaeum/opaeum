package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.TaskRequestRegion;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class SuspendedToActive extends TransitionInstance<TaskRequest, TaskRequestToken> {
	static public String ID = "252060@_vnrq4KCWEeCmJqvPP4zbUw";

	/** Constructor for SuspendedToActive
	 * 
	 * @param regionActivation 
	 */
	public SuspendedToActive(TaskRequestRegion regionActivation) {
	super(ID,regionActivation,"252060@_eXmqMKCWEeCmJqvPP4zbUw","252060@_XwDj4KCVEeCmJqvPP4zbUw");
		((Suspended)getSource()).setSuspendedToActive(this);
	}

	public boolean consumeResumeOccurrence() {
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