package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.TaskRequestRegion;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1.Reserved;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class ReservedToSuspended extends TransitionInstance<TaskRequest, TaskRequestToken> {
	static public String ID = "252060@_vQNgIIobEeCPduia_-NbFw";

	/** Constructor for ReservedToSuspended
	 * 
	 * @param regionActivation 
	 */
	public ReservedToSuspended(TaskRequestRegion regionActivation) {
	super(ID,regionActivation,"252060@_Q6NAcIoaEeCPduia_-NbFw","252060@_sFnzcIobEeCPduia_-NbFw");
		((Reserved)getSource()).setReservedToSuspended(this);
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