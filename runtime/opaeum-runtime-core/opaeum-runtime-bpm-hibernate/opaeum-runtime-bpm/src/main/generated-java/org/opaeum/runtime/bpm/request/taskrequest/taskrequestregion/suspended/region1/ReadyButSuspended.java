package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.suspended.region1;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestState;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.suspended.Region1;
import org.opaeum.runtime.domain.TriggerMethod;
import org.opaeum.runtime.statemachines.StateActivation;

public class ReadyButSuspended extends StateActivation<TaskRequest, TaskRequestToken> {
	static public String ID = "252060@_RC_JAIoaEeCPduia_-NbFw";

	/** Constructor for ReadyButSuspended
	 * 
	 * @param region 
	 */
	public ReadyButSuspended(Region1 region) {
	super(ID,region);
	}

	public TaskRequest getBehaviorExecution() {
		TaskRequest result = (TaskRequest)super.getBehaviorExecution();
		
		return result;
	}
	
	public String getHumanName() {
		String result = "Ready but suspended";
		
		return result;
	}
	
	public TriggerMethod[] getTriggerMethods() {
		TriggerMethod[] result = new TriggerMethod[]{};
		
		return result;
	}
	
	public boolean onCompletion() {
		boolean result = false;
		
		return result;
	}

}