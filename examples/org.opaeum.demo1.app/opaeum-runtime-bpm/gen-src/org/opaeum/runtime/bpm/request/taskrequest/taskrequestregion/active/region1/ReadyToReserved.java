package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.Region1;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class ReadyToReserved<SME extends TaskRequest> extends TransitionInstance<SME, TaskRequestToken<SME>> {
	static public String ID = "252060@_fp_ZMIobEeCPduia_-NbFw";

	/** Constructor for ReadyToReserved
	 * 
	 * @param regionActivation 
	 */
	public ReadyToReserved(Region1 regionActivation) {
	super(ID,regionActivation,Ready.ID,Reserved.ID);
		((Ready)getSource()).setReadyToReserved(this);
	}

	public boolean consumeClaimOccurrence() {
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