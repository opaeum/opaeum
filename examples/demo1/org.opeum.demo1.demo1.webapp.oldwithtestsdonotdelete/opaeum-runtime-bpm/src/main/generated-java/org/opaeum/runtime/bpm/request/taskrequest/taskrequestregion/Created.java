package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion;

import javax.persistence.Transient;

import org.opaeum.hibernate.domain.StateMachineToken;
import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.taskrequest.TaskRequestRegion;
import org.opaeum.runtime.statemachines.StateActivation;

public class Created extends StateActivation {
	@Transient
	private CreatedToActive CreatedToActive;

	/** Constructor for Created
	 * 
	 * @param region 
	 */
	public Created(TaskRequestRegion region) {
	super("252060@_Qtk5sIoaEeCPduia_-NbFw",region);
	}

	public CreatedToActive getCreatedToActive() {
		return this.CreatedToActive;
	}
	
	public TaskRequest getStateMachineExecution() {
		TaskRequest result = (TaskRequest)super.getStateMachineExecution();
		
		return result;
	}
	
	public boolean onComplete() {
		boolean result = false;
		
		return result;
	}
	
	public void onEntry(StateMachineToken token) {
		token.fireCompletionEvent();
	}
	
	public void setCreatedToActive(CreatedToActive CreatedToActive) {
		this.CreatedToActive=CreatedToActive;
	}

}