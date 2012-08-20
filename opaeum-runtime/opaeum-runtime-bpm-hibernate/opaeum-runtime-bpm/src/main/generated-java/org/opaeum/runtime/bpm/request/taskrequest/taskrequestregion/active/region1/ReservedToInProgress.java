package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.Region1;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class ReservedToInProgress extends TransitionInstance<TaskRequest, TaskRequestToken> {
	static public String ID = "252060@_f0sNUIobEeCPduia_-NbFw";

	/** Constructor for ReservedToInProgress
	 * 
	 * @param regionActivation 
	 */
	public ReservedToInProgress(Region1 regionActivation) {
	super(ID,regionActivation,"252060@_Q6NAcIoaEeCPduia_-NbFw","252060@_RMUEIIoaEeCPduia_-NbFw");
		((Reserved)getSource()).setReservedToInProgress(this);
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