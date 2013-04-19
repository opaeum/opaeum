package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1;

import javax.persistence.Transient;

import org.opaeum.hibernate.domain.StateMachineToken;
import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.Region1;
import org.opaeum.runtime.statemachines.HistoryStateActivation;

public class History extends HistoryStateActivation {
	@Transient
	private HistoryToNumberOfPotentialOwners HistoryToNumberOfPotentialOwners;

	/** Constructor for History
	 * 
	 * @param region 
	 */
	public History(Region1 region) {
	super("252060@_UBMQAKCWEeCmJqvPP4zbUw",region);
		setInitial(true);
	}

	public String getHistoricalStateId() {
		String result = null;
		if ( getStateMachineExecution().getHistory()!=null ) {
			result=getStateMachineExecution().getHistory();
		}
		return result;
	}
	
	public HistoryToNumberOfPotentialOwners getHistoryToNumberOfPotentialOwners() {
		return this.HistoryToNumberOfPotentialOwners;
	}
	
	public TaskRequest getStateMachineExecution() {
		TaskRequest result = (TaskRequest)super.getStateMachineExecution();
		
		return result;
	}
	
	public boolean onComplete() {
		boolean result = false;
		if ( HistoryToNumberOfPotentialOwners.onHistoryCompleted() ) {
			return true;
		}
		return result;
	}
	
	public void onEntry(StateMachineToken token) {
	}
	
	public void setHistoryToNumberOfPotentialOwners(HistoryToNumberOfPotentialOwners HistoryToNumberOfPotentialOwners) {
		this.HistoryToNumberOfPotentialOwners=HistoryToNumberOfPotentialOwners;
	}

}