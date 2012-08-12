package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.statemachines.RegionActivation;
import org.opaeum.runtime.statemachines.StateMachineToken;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class ReservedToInProgress extends TransitionInstance {


	/** Constructor for ReservedToInProgress
	 * 
	 * @param regionActivation 
	 */
	public ReservedToInProgress(RegionActivation regionActivation) {
	super("252060@_f0sNUIobEeCPduia_-NbFw",regionActivation,"252060@_Q6NAcIoaEeCPduia_-NbFw","252060@_Q6NAcIoaEeCPduia_-NbFw");
		((Reserved)getSource()).setReservedToInProgress(this);
	}

	public boolean consumeStartOccurrence() {
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