package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1;

import org.opaeum.hibernate.domain.StateMachineToken;
import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.statemachines.RegionActivation;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class ReservedToReady extends TransitionInstance {


	/** Constructor for ReservedToReady
	 * 
	 * @param regionActivation 
	 */
	public ReservedToReady(RegionActivation regionActivation) {
	super("252060@_E2ZNFKDTEeCi16HgBnUGFw",regionActivation,"252060@_Q6NAcIoaEeCPduia_-NbFw","252060@_Q6NAcIoaEeCPduia_-NbFw");
		((Reserved)getSource()).setReservedToReady(this);
	}

	public boolean consumeRevokeOccurrence() {
		boolean result = false;
		result=true;
		StateMachineToken token= getMainSource().exit();
		getMainTarget().enter(token,target);
		return result;
	}
	
	public TaskRequest getStateMachineExecution() {
		TaskRequest result = (TaskRequest)super.getStateMachineExecution();
		
		return result;
	}

}