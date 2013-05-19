package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.Region1;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class HistoryToNumberOfPotentialOwners<SME extends TaskRequest> extends TransitionInstance<SME, TaskRequestToken<SME>> {
	static public String ID = "252060@_oYvoEKDPEeCv9IRqC7lfYw";

	/** Constructor for HistoryToNumberOfPotentialOwners
	 * 
	 * @param regionActivation 
	 */
	public HistoryToNumberOfPotentialOwners(Region1 regionActivation) {
	super(ID,regionActivation,History.ID,NumberOfPotentialOwners_.ID);
		((History)getSource()).setHistoryToNumberOfPotentialOwners(this);
	}

	public SME getBehaviorExecution() {
		SME result = (SME)super.getBehaviorExecution();
		
		return result;
	}
	
	public boolean onHistoryCompleted() {
		boolean result = false;
		result=true;
		TaskRequestToken<SME> token= getMainSource().exit();
		super.onEntry(token);
		getMainTarget().enter(token,target);
		super.onExit(token);
		return result;
	}

}