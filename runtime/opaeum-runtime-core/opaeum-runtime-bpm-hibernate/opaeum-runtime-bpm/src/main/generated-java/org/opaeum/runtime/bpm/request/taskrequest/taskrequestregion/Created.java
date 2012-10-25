package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion;

import javax.persistence.Transient;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestState;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.TaskRequestRegion;
import org.opaeum.runtime.domain.TriggerMethod;
import org.opaeum.runtime.statemachines.StateActivation;

public class Created extends StateActivation<TaskRequest, TaskRequestToken> {
	@Transient
	private CreatedToActive CreatedToActive;
	static public String ID = "252060@_Qtk5sIoaEeCPduia_-NbFw";

	/** Constructor for Created
	 * 
	 * @param region 
	 */
	public Created(TaskRequestRegion region) {
	super(ID,region);
	}

	public TaskRequest getBehaviorExecution() {
		TaskRequest result = (TaskRequest)super.getBehaviorExecution();
		
		return result;
	}
	
	public CreatedToActive getCreatedToActive() {
		return this.CreatedToActive;
	}
	
	public String getHumanName() {
		String result = "Created";
		
		return result;
	}
	
	public TriggerMethod[] getTriggerMethods() {
		TriggerMethod[] result = new TriggerMethod[]{new TriggerMethod(false,"On activate","OnActivate")};
		
		return result;
	}
	
	public boolean onCompletion() {
		boolean result = false;
		
		return result;
	}
	
	public void setCreatedToActive(CreatedToActive CreatedToActive) {
		this.CreatedToActive=CreatedToActive;
	}

}