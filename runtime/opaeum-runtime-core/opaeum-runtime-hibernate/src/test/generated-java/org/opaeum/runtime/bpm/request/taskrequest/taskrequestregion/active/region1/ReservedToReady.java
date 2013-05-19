package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.Region1;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class ReservedToReady<SME extends TaskRequest> extends TransitionInstance<SME, TaskRequestToken<SME>> {
	static public String ID = "252060@_E2ZNFKDTEeCi16HgBnUGFw";

	/** Constructor for ReservedToReady
	 * 
	 * @param regionActivation 
	 */
	public ReservedToReady(Region1 regionActivation) {
	super(ID,regionActivation,Reserved.ID,Ready.ID);
		((Reserved)getSource()).setReservedToReady(this);
	}

	public boolean consumeRevokeOccurrence() {
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