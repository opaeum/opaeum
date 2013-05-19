package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.Region1;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class InProgressToReserved<SME extends TaskRequest> extends TransitionInstance<SME, TaskRequestToken<SME>> {
	static public String ID = "252060@_2DpKcKDSEeCi16HgBnUGFw";

	/** Constructor for InProgressToReserved
	 * 
	 * @param regionActivation 
	 */
	public InProgressToReserved(Region1 regionActivation) {
	super(ID,regionActivation,InProgress.ID,Reserved.ID);
		((InProgress)getSource()).setInProgressToReserved(this);
	}

	public boolean consumeStopOccurrence() {
		boolean result = false;
		result=true;
		TaskRequestToken<SME> token= getMainSource().exit();
		super.onEntry(token);
		getMainTarget().enter(token,target);
		super.onExit(token);
		return result;
	}
	
	public SME getBehaviorExecution() {
		SME result = (SME)super.getBehaviorExecution();
		
		return result;
	}

}