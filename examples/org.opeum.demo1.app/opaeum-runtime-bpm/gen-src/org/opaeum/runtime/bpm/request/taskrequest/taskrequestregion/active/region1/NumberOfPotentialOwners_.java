package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1;

import java.util.Set;

import javax.persistence.Transient;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestState;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.Region1;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.statemachines.PseudoStateActivation;

public class NumberOfPotentialOwners_<SME extends TaskRequest> extends PseudoStateActivation<SME, TaskRequestToken<SME>> {
	static public String ID = "252060@_t3M00KDPEeCv9IRqC7lfYw";
	@Transient
	private NumberOfPotentialOwnersToReady<SME> NumberOfPotentialOwnersToReady;
	@Transient
	private NumberOfPotentialOwnersToReserved<SME> NumberOfPotentialOwnersToReserved;

	/** Constructor for NumberOfPotentialOwners_
	 * 
	 * @param region 
	 */
	public NumberOfPotentialOwners_(Region1<SME> region) {
	super(ID,region);
	}

	public SME getBehaviorExecution() {
		SME result = (SME)super.getBehaviorExecution();
		
		return result;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		Set result = getBehaviorExecution().getCancelledEvents();
		
		return result;
	}
	
	public NumberOfPotentialOwnersToReady<SME> getNumberOfPotentialOwnersToReady() {
		return this.NumberOfPotentialOwnersToReady;
	}
	
	public NumberOfPotentialOwnersToReserved<SME> getNumberOfPotentialOwnersToReserved() {
		return this.NumberOfPotentialOwnersToReserved;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		Set result = getBehaviorExecution().getOutgoingEvents();
		
		return result;
	}
	
	public boolean onCompletion() {
		boolean result = false;
		if ( NumberOfPotentialOwnersToReady.onNumberOfPotentialOwners_Completed() ) {
			return true;
		}
		if ( NumberOfPotentialOwnersToReserved.onNumberOfPotentialOwners_Completed() ) {
			return true;
		}
		return result;
	}
	
	public void onEntry(TaskRequestToken token) {
		super.onEntry(token);
		token.fireCompletionEvent();
	}
	
	public void setNumberOfPotentialOwnersToReady(NumberOfPotentialOwnersToReady<SME> NumberOfPotentialOwnersToReady) {
		this.NumberOfPotentialOwnersToReady=NumberOfPotentialOwnersToReady;
	}
	
	public void setNumberOfPotentialOwnersToReserved(NumberOfPotentialOwnersToReserved<SME> NumberOfPotentialOwnersToReserved) {
		this.NumberOfPotentialOwnersToReserved=NumberOfPotentialOwnersToReserved;
	}

}