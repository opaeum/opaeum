package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.Region1;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class NumberOfPotentialOwnersToReady extends TransitionInstance<TaskRequest, TaskRequestToken> {
	static public String ID = "252060@_weFXgKDPEeCv9IRqC7lfYw";

	/** Constructor for NumberOfPotentialOwnersToReady
	 * 
	 * @param regionActivation 
	 */
	public NumberOfPotentialOwnersToReady(Region1 regionActivation) {
	super(ID,regionActivation,"252060@_t3M00KDPEeCv9IRqC7lfYw","252060@_Q0VB8IoaEeCPduia_-NbFw");
		((NumberOfPotentialOwners_)getSource()).setNumberOfPotentialOwnersToReady(this);
	}

	public TaskRequest getBehaviorExecution() {
		TaskRequest result = (TaskRequest)super.getBehaviorExecution();
		
		return result;
	}
	
	public boolean onNumberOfPotentialOwners_Completed() {
		boolean result = false;
		if ( (getStateMachineExecution().getPotentialOwners().size() > 1) ) {
			result=true;
			TaskRequestToken token= getMainSource().exit();
			getMainTarget().enter(token,target);
		}
		return result;
	}

}