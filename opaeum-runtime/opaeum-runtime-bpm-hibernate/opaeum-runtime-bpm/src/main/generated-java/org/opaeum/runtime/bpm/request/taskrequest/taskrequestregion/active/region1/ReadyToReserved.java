package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.Region1;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class ReadyToReserved extends TransitionInstance<TaskRequest, TaskRequestToken> {
	static public String ID = "252060@_fp_ZMIobEeCPduia_-NbFw";

	/** Constructor for ReadyToReserved
	 * 
	 * @param regionActivation 
	 */
	public ReadyToReserved(Region1 regionActivation) {
	super(ID,regionActivation,"252060@_Q0VB8IoaEeCPduia_-NbFw","252060@_Q6NAcIoaEeCPduia_-NbFw");
		((Ready)getSource()).setReadyToReserved(this);
	}

	public boolean consumeClaimOccurrence() {
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