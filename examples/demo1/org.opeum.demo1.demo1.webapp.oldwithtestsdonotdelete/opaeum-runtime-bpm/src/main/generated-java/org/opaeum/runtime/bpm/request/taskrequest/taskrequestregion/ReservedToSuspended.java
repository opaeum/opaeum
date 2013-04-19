package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion;

import org.opaeum.hibernate.domain.StateMachineToken;
import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1.Reserved;
import org.opaeum.runtime.statemachines.RegionActivation;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class ReservedToSuspended extends TransitionInstance {


	/** Constructor for ReservedToSuspended
	 * 
	 * @param regionActivation 
	 */
	public ReservedToSuspended(RegionActivation regionActivation) {
	super("252060@_vQNgIIobEeCPduia_-NbFw",regionActivation,"252060@_Q6NAcIoaEeCPduia_-NbFw","252060@_Q6NAcIoaEeCPduia_-NbFw");
		((Reserved)getSource()).setReservedToSuspended(this);
	}

	public boolean consumeSuspendOccurrence() {
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