package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.statemachines.RegionActivation;
import org.opaeum.runtime.statemachines.StateMachineToken;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class ReadyToReserved extends TransitionInstance {


	/** Constructor for ReadyToReserved
	 * 
	 * @param regionActivation 
	 */
	public ReadyToReserved(RegionActivation regionActivation) {
	super("252060@_fp_ZMIobEeCPduia_-NbFw",regionActivation,"252060@_Q0VB8IoaEeCPduia_-NbFw","252060@_Q0VB8IoaEeCPduia_-NbFw");
		((Ready)getSource()).setReadyToReserved(this);
	}

	public boolean consumeClaimOccurrence() {
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