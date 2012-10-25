package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion;

import javax.persistence.Transient;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestState;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.TaskRequestRegion;
import org.opaeum.runtime.statemachines.PseudoStateActivation;

public class Inactive extends PseudoStateActivation<TaskRequest, TaskRequestToken> {
	static public String ID = "252060@_PGwF0IoaEeCPduia_-NbFw";
	@Transient
	private InactiveToCreated InactiveToCreated;

	/** Constructor for Inactive
	 * 
	 * @param region 
	 */
	public Inactive(TaskRequestRegion region) {
	super(ID,region);
		setInitial(true);
	}

	public TaskRequest getBehaviorExecution() {
		TaskRequest result = (TaskRequest)super.getBehaviorExecution();
		
		return result;
	}
	
	public InactiveToCreated getInactiveToCreated() {
		return this.InactiveToCreated;
	}
	
	public boolean onCompletion() {
		boolean result = false;
		if ( InactiveToCreated.onInactiveCompleted() ) {
			return true;
		}
		return result;
	}
	
	public void onEntry(TaskRequestToken token) {
		token.fireCompletionEvent();
	}
	
	public void setInactiveToCreated(InactiveToCreated InactiveToCreated) {
		this.InactiveToCreated=InactiveToCreated;
	}

}