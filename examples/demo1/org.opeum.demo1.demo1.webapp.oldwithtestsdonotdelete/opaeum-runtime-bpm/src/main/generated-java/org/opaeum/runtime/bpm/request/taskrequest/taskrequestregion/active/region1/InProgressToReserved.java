package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1;

import org.opaeum.hibernate.domain.StateMachineToken;
import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.statemachines.IStateMachineExecution;
import org.opaeum.runtime.statemachines.RegionActivation;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class InProgressToReserved extends TransitionInstance {


	/** Constructor for InProgressToReserved
	 * 
	 * @param regionActivation 
	 */
	public InProgressToReserved(RegionActivation regionActivation) {
	super("252060@_2DpKcKDSEeCi16HgBnUGFw",regionActivation,"252060@_RMUEIIoaEeCPduia_-NbFw","252060@_RMUEIIoaEeCPduia_-NbFw");
		((InProgress)getSource()).setInProgressToReserved(this);
	}

	public boolean consumeStopOccurrence() {
		boolean result = false;
		result=true;
		StateMachineToken<IStateMachineExecution> token= getMainSource().exit();
		getMainTarget().enter(token,target);
		return result;
	}
	
	public TaskRequest getStateMachineExecution() {
		TaskRequest result = (TaskRequest)super.getStateMachineExecution();
		
		return result;
	}

}