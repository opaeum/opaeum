package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1;

import org.opaeum.hibernate.domain.StateMachineToken;
import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.statemachines.RegionActivation;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class NumberOfPotentialOwnersToReserved extends TransitionInstance {


	/** Constructor for NumberOfPotentialOwnersToReserved
	 * 
	 * @param regionActivation 
	 */
	public NumberOfPotentialOwnersToReserved(RegionActivation regionActivation) {
	super("252060@_wyEqgKDPEeCv9IRqC7lfYw",regionActivation,"252060@_t3M00KDPEeCv9IRqC7lfYw","252060@_t3M00KDPEeCv9IRqC7lfYw");
		((NumberOfPotentialOwners_)getSource()).setNumberOfPotentialOwnersToReserved(this);
	}

	public TaskRequest getStateMachineExecution() {
		TaskRequest result = (TaskRequest)super.getStateMachineExecution();
		
		return result;
	}
	
	public boolean onNumberOfPotentialOwners_Completed() {
		boolean result = false;
		if ( (getStateMachineExecution().getPotentialOwners().size() == 1) ) {
			result=true;
			StateMachineToken token= getMainSource().exit();
			getMainTarget().enter(token,target);
		}
		return result;
	}

}