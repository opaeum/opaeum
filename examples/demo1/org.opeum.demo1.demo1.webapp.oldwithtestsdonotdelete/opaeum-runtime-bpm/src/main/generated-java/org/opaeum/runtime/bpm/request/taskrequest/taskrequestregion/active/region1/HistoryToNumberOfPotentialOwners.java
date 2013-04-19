package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1;

import org.opaeum.hibernate.domain.StateMachineToken;
import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.statemachines.RegionActivation;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class HistoryToNumberOfPotentialOwners extends TransitionInstance {


	/** Constructor for HistoryToNumberOfPotentialOwners
	 * 
	 * @param regionActivation 
	 */
	public HistoryToNumberOfPotentialOwners(RegionActivation regionActivation) {
	super("252060@_oYvoEKDPEeCv9IRqC7lfYw",regionActivation,"252060@_UBMQAKCWEeCmJqvPP4zbUw","252060@_UBMQAKCWEeCmJqvPP4zbUw");
		((History)getSource()).setHistoryToNumberOfPotentialOwners(this);
	}

	public TaskRequest getStateMachineExecution() {
		TaskRequest result = (TaskRequest)super.getStateMachineExecution();
		
		return result;
	}
	
	public boolean onHistoryCompleted() {
		boolean result = false;
		result=true;
		StateMachineToken token= getMainSource().exit();
		getMainTarget().enter(token,target);
		return result;
	}

}