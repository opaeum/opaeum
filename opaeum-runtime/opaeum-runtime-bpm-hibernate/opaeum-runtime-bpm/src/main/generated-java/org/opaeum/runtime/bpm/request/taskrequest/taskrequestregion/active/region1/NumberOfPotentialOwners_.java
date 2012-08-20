package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1;

import javax.persistence.Transient;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestState;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.Region1;
import org.opaeum.runtime.statemachines.PseudoStateActivation;

public class NumberOfPotentialOwners_ extends PseudoStateActivation<TaskRequest, TaskRequestToken> {
	static public String ID = "252060@_t3M00KDPEeCv9IRqC7lfYw";
	@Transient
	private NumberOfPotentialOwnersToReady NumberOfPotentialOwnersToReady;
	@Transient
	private NumberOfPotentialOwnersToReserved NumberOfPotentialOwnersToReserved;

	/** Constructor for NumberOfPotentialOwners_
	 * 
	 * @param region 
	 */
	public NumberOfPotentialOwners_(Region1 region) {
	super(ID,region);
	}

	public TaskRequest getBehaviorExecution() {
		TaskRequest result = (TaskRequest)super.getBehaviorExecution();
		
		return result;
	}
	
	public NumberOfPotentialOwnersToReady getNumberOfPotentialOwnersToReady() {
		return this.NumberOfPotentialOwnersToReady;
	}
	
	public NumberOfPotentialOwnersToReserved getNumberOfPotentialOwnersToReserved() {
		return this.NumberOfPotentialOwnersToReserved;
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
		token.fireCompletionEvent();
	}
	
	public void setNumberOfPotentialOwnersToReady(NumberOfPotentialOwnersToReady NumberOfPotentialOwnersToReady) {
		this.NumberOfPotentialOwnersToReady=NumberOfPotentialOwnersToReady;
	}
	
	public void setNumberOfPotentialOwnersToReserved(NumberOfPotentialOwnersToReserved NumberOfPotentialOwnersToReserved) {
		this.NumberOfPotentialOwnersToReserved=NumberOfPotentialOwnersToReserved;
	}

}