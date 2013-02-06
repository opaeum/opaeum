package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion;

import org.opaeum.hibernate.domain.StateMachineToken;
import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1.InProgress;
import org.opaeum.runtime.statemachines.RegionActivation;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class InProgressToSuspended extends TransitionInstance {


	/** Constructor for InProgressToSuspended
	 * 
	 * @param regionActivation 
	 */
	public InProgressToSuspended(RegionActivation regionActivation) {
	super("252060@_wDQGoIobEeCPduia_-NbFw",regionActivation,"252060@_RMUEIIoaEeCPduia_-NbFw","252060@_RMUEIIoaEeCPduia_-NbFw");
		((InProgress)getSource()).setInProgressToSuspended(this);
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