package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1;

import java.util.Set;

import javax.persistence.Transient;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.Region1;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.statemachines.HistoryStateActivation;

public class History<SME extends TaskRequest> extends HistoryStateActivation<SME, TaskRequestToken<SME>> {
	@Transient
	private HistoryToNumberOfPotentialOwners<SME> HistoryToNumberOfPotentialOwners;
	static public String ID = "252060@_UBMQAKCWEeCmJqvPP4zbUw";

	/** Constructor for History
	 * 
	 * @param region 
	 */
	public History(Region1<SME> region) {
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
	
	public String getHistoricalStateId() {
		String result = null;
		if ( getBehaviorExecution().getHistory()!=null ) {
			result=getBehaviorExecution().getHistory();
		}
		return result;
	}
	
	public HistoryToNumberOfPotentialOwners<SME> getHistoryToNumberOfPotentialOwners() {
		return this.HistoryToNumberOfPotentialOwners;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		Set result = getBehaviorExecution().getOutgoingEvents();
		
		return result;
	}
	
	public boolean onCompletion() {
		boolean result = false;
		if ( HistoryToNumberOfPotentialOwners.onHistoryCompleted() ) {
			return true;
		}
		return result;
	}
	
	public void onEntry(TaskRequestToken token) {
		super.onEntry(token);
		token.fireCompletionEvent();
	}
	
	public void setHistoryToNumberOfPotentialOwners(HistoryToNumberOfPotentialOwners<SME> HistoryToNumberOfPotentialOwners) {
		this.HistoryToNumberOfPotentialOwners=HistoryToNumberOfPotentialOwners;
	}

}