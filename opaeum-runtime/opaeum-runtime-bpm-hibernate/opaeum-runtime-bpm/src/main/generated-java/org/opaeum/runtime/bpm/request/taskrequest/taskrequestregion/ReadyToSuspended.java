package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.TaskRequestRegion;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1.Ready;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class ReadyToSuspended extends TransitionInstance<TaskRequest, TaskRequestToken> {
	static public String ID = "252060@_qzQzsIobEeCPduia_-NbFw";

	/** Constructor for ReadyToSuspended
	 * 
	 * @param regionActivation 
	 */
	public ReadyToSuspended(TaskRequestRegion regionActivation) {
	super(ID,regionActivation,"252060@_Q0VB8IoaEeCPduia_-NbFw","252060@_RC_JAIoaEeCPduia_-NbFw");
		((Ready)getSource()).setReadyToSuspended(this);
	}

	public boolean consumeSuspendOccurrence() {
		boolean result = false;
		result=true;
		TaskRequestToken token= getMainSource().exit();
		getMainTarget().enter(token,target);
		return result;
	}
	
	public TaskRequest getBehaviorExecution() {
		TaskRequest result = (TaskRequest)super.getBehaviorExecution();
		
		return result;
	}

}