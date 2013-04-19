package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.Region1;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class ReservedToReady extends TransitionInstance<TaskRequest, TaskRequestToken> {
	static public String ID = "252060@_E2ZNFKDTEeCi16HgBnUGFw";

	/** Constructor for ReservedToReady
	 * 
	 * @param regionActivation 
	 */
	public ReservedToReady(Region1 regionActivation) {
	super(ID,regionActivation,"252060@_Q6NAcIoaEeCPduia_-NbFw","252060@_Q0VB8IoaEeCPduia_-NbFw");
		((Reserved)getSource()).setReservedToReady(this);
	}

	public boolean consumeRevokeOccurrence() {
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