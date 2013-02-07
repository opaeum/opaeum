package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion;

import javax.persistence.Transient;

import org.opaeum.hibernate.domain.StateMachineToken;
import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.taskrequest.TaskRequestRegion;
import org.opaeum.runtime.statemachines.PseudoStateActivation;

public class Inactive extends PseudoStateActivation {
	@Transient
	private InactiveToCreated InactiveToCreated;

	/** Constructor for Inactive
	 * 
	 * @param region 
	 */
	public Inactive(TaskRequestRegion region) {
	super("252060@_PGwF0IoaEeCPduia_-NbFw",region);
		setInitial(true);
	}

	public InactiveToCreated getInactiveToCreated() {
		return this.InactiveToCreated;
	}
	
	public TaskRequest getStateMachineExecution() {
		TaskRequest result = (TaskRequest)super.getStateMachineExecution();
		
		return result;
	}
	
	public boolean onComplete() {
		boolean result = false;
		if ( InactiveToCreated.onInactiveCompleted() ) {
			return true;
		}
		return result;
	}
	
	public void onEntry(StateMachineToken token) {
		token.fireCompletionEvent();
	}
	
	public void setInactiveToCreated(InactiveToCreated InactiveToCreated) {
		this.InactiveToCreated=InactiveToCreated;
	}

}