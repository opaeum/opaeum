package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.Region1;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class InProgressToReserved extends TransitionInstance<TaskRequest, TaskRequestToken> {
	static public String ID = "252060@_2DpKcKDSEeCi16HgBnUGFw";

	/** Constructor for InProgressToReserved
	 * 
	 * @param regionActivation 
	 */
	public InProgressToReserved(Region1 regionActivation) {
	super(ID,regionActivation,"252060@_RMUEIIoaEeCPduia_-NbFw","252060@_Q6NAcIoaEeCPduia_-NbFw");
		((InProgress)getSource()).setInProgressToReserved(this);
	}

	public boolean consumeStopOccurrence() {
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