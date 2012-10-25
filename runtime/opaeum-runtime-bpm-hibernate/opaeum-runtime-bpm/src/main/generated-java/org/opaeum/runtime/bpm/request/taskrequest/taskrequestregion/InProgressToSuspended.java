package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.TaskRequestRegion;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1.InProgress;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class InProgressToSuspended extends TransitionInstance<TaskRequest, TaskRequestToken> {
	static public String ID = "252060@_wDQGoIobEeCPduia_-NbFw";

	/** Constructor for InProgressToSuspended
	 * 
	 * @param regionActivation 
	 */
	public InProgressToSuspended(TaskRequestRegion regionActivation) {
	super(ID,regionActivation,"252060@_RMUEIIoaEeCPduia_-NbFw","252060@_tnUPsIobEeCPduia_-NbFw");
		((InProgress)getSource()).setInProgressToSuspended(this);
	}

	public boolean consumeSuspendOccurrence() {
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