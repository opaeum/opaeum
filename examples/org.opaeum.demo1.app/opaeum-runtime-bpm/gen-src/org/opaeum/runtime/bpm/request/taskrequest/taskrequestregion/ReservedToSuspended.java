package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.TaskRequestRegion;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1.Reserved;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.suspended.region1.ReservedButSuspended;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class ReservedToSuspended<SME extends TaskRequest> extends TransitionInstance<SME, TaskRequestToken<SME>> {
	static public String ID = "252060@_vQNgIIobEeCPduia_-NbFw";

	/** Constructor for ReservedToSuspended
	 * 
	 * @param regionActivation 
	 */
	public ReservedToSuspended(TaskRequestRegion regionActivation) {
	super(ID,regionActivation,Reserved.ID,ReservedButSuspended.ID);
		((Reserved)getSource()).setReservedToSuspended(this);
	}

	public boolean consumeSuspendOccurrence() {
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