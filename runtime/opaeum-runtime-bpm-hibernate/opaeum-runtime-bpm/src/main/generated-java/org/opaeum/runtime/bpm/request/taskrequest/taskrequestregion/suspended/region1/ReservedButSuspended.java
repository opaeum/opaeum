package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.suspended.region1;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestState;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.suspended.Region1;
import org.opaeum.runtime.domain.TriggerMethod;
import org.opaeum.runtime.statemachines.StateActivation;

public class ReservedButSuspended extends StateActivation<TaskRequest, TaskRequestToken> {
	static public String ID = "252060@_sFnzcIobEeCPduia_-NbFw";

	/** Constructor for ReservedButSuspended
	 * 
	 * @param region 
	 */
	public ReservedButSuspended(Region1 region) {
	super(ID,region);
	}

	public TaskRequest getBehaviorExecution() {
		TaskRequest result = (TaskRequest)super.getBehaviorExecution();
		
		return result;
	}
	
	public String getHumanName() {
		String result = "Reserved but suspended";
		
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