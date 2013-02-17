package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion;

import java.util.Set;

import javax.persistence.Transient;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestState;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.TaskRequestRegion;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.statemachines.PseudoStateActivation;

public class Inactive<SME extends TaskRequest> extends PseudoStateActivation<SME, TaskRequestToken<SME>> {
	static public String ID = "252060@_PGwF0IoaEeCPduia_-NbFw";
	@Transient
	private InactiveToCreated<SME> InactiveToCreated;

	/** Constructor for Inactive
	 * 
	 * @param region 
	 */
	public Inactive(TaskRequestRegion<SME> region) {
	super(ID,region);
		setInitial(true);
	}

	public SME getBehaviorExecution() {
		SME result = (SME)super.getBehaviorExecution();
		
		return result;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		Set result = getBehaviorExecution().getCancelledEvents();
		
		return result;
	}
	
	public InactiveToCreated<SME> getInactiveToCreated() {
		return this.InactiveToCreated;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		Set result = getBehaviorExecution().getOutgoingEvents();
		
		return result;
	}
	
	public boolean onCompletion() {
		boolean result = false;
		if ( InactiveToCreated.onInactiveCompleted() ) {
			return true;
		}
		return result;
	}
	
	public void onEntry(TaskRequestToken token) {
		super.onEntry(token);
		token.fireCompletionEvent();
	}
	
	public void setInactiveToCreated(InactiveToCreated<SME> InactiveToCreated) {
		this.InactiveToCreated=InactiveToCreated;
	}

}