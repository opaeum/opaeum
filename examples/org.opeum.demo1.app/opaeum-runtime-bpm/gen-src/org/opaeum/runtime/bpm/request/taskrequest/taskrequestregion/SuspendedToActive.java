package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.TaskRequestRegion;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class SuspendedToActive<SME extends TaskRequest> extends TransitionInstance<SME, TaskRequestToken<SME>> {
	static public String ID = "252060@_vnrq4KCWEeCmJqvPP4zbUw";

	/** Constructor for SuspendedToActive
	 * 
	 * @param regionActivation 
	 */
	public SuspendedToActive(TaskRequestRegion regionActivation) {
	super(ID,regionActivation,Suspended.ID,Active.ID);
		((Suspended)getSource()).setSuspendedToActive(this);
	}

	public boolean consumeResumeOccurrence() {
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