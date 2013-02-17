package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.Region1;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class NumberOfPotentialOwnersToReserved<SME extends TaskRequest> extends TransitionInstance<SME, TaskRequestToken<SME>> {
	static public String ID = "252060@_wyEqgKDPEeCv9IRqC7lfYw";

	/** Constructor for NumberOfPotentialOwnersToReserved
	 * 
	 * @param regionActivation 
	 */
	public NumberOfPotentialOwnersToReserved(Region1 regionActivation) {
	super(ID,regionActivation,NumberOfPotentialOwners_.ID,Reserved.ID);
		((NumberOfPotentialOwners_)getSource()).setNumberOfPotentialOwnersToReserved(this);
	}

	public SME getBehaviorExecution() {
		SME result = (SME)super.getBehaviorExecution();
		
		return result;
	}
	
	public boolean onNumberOfPotentialOwners_Completed() {
		boolean result = false;
		if ( (getStateMachineExecution().getPotentialOwners().size() == 1) ) {
			result=true;
			TaskRequestToken<SME> token= getMainSource().exit();
			super.onEntry(token);
			getMainTarget().enter(token,target);
			super.onExit(token);
		}
		return result;
	}

}