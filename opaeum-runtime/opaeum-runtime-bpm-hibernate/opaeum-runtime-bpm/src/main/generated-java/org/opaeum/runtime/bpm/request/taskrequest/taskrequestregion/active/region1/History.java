package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1;

import javax.persistence.Transient;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestState;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.Region1;
import org.opaeum.runtime.statemachines.HistoryStateActivation;

public class History extends HistoryStateActivation<TaskRequest, TaskRequestToken> {
	@Transient
	private HistoryToNumberOfPotentialOwners HistoryToNumberOfPotentialOwners;
	static public String ID = "252060@_UBMQAKCWEeCmJqvPP4zbUw";

	/** Constructor for History
	 * 
	 * @param region 
	 */
	public History(Region1 region) {
	super(ID,region);
		setInitial(true);
	}

	public TaskRequest getBehaviorExecution() {
		TaskRequest result = (TaskRequest)super.getBehaviorExecution();
		
		return result;
	}
	
	public String getHistoricalStateId() {
		String result = null;
		if ( getBehaviorExecution().getHistory()!=null ) {
			result=getBehaviorExecution().getHistory();
		}
		return result;
	}
	
	public HistoryToNumberOfPotentialOwners getHistoryToNumberOfPotentialOwners() {
		return this.HistoryToNumberOfPotentialOwners;
	}
	
	public boolean onCompletion() {
		boolean result = false;
		if ( HistoryToNumberOfPotentialOwners.onHistoryCompleted() ) {
			return true;
		}
		return result;
	}
	
	public void onEntry(TaskRequestToken token) {
		token.fireCompletionEvent();
	}
	
	public void setHistoryToNumberOfPotentialOwners(HistoryToNumberOfPotentialOwners HistoryToNumberOfPotentialOwners) {
		this.HistoryToNumberOfPotentialOwners=HistoryToNumberOfPotentialOwners;
	}

}