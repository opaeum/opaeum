package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.TaskRequestRegion;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1.Ready;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.suspended.region1.ReadyButSuspended;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class ReadyToSuspended<SME extends TaskRequest> extends TransitionInstance<SME, TaskRequestToken<SME>> {
	static public String ID = "252060@_qzQzsIobEeCPduia_-NbFw";

	/** Constructor for ReadyToSuspended
	 * 
	 * @param regionActivation 
	 */
	public ReadyToSuspended(TaskRequestRegion regionActivation) {
	super(ID,regionActivation,Ready.ID,ReadyButSuspended.ID);
		((Ready)getSource()).setReadyToSuspended(this);
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