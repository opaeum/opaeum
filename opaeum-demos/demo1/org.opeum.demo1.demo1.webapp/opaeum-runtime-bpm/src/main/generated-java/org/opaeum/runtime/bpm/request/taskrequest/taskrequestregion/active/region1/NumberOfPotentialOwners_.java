package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1;

import javax.persistence.Transient;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestState;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.Region1;
import org.opaeum.runtime.statemachines.PseudoStateActivation;
import org.opaeum.runtime.statemachines.StateMachineToken;

public class NumberOfPotentialOwners_ extends PseudoStateActivation {
	@Transient
	private NumberOfPotentialOwnersToReady NumberOfPotentialOwnersToReady;
	@Transient
	private NumberOfPotentialOwnersToReserved NumberOfPotentialOwnersToReserved;

	/** Constructor for NumberOfPotentialOwners_
	 * 
	 * @param region 
	 */
	public NumberOfPotentialOwners_(Region1 region) {
	super("252060@_t3M00KDPEeCv9IRqC7lfYw",region);
	}

	public NumberOfPotentialOwnersToReady getNumberOfPotentialOwnersToReady() {
		return this.NumberOfPotentialOwnersToReady;
	}
	
	public NumberOfPotentialOwnersToReserved getNumberOfPotentialOwnersToReserved() {
		return this.NumberOfPotentialOwnersToReserved;
	}
	
	public TaskRequest getStateMachineExecution() {
		TaskRequest result = (TaskRequest)super.getStateMachineExecution();
		
		return result;
	}
	
	public boolean onComplete() {
		boolean result = false;
		if ( NumberOfPotentialOwnersToReady.onNumberOfPotentialOwners_Completed() ) {
			return true;
		}
		if ( NumberOfPotentialOwnersToReserved.onNumberOfPotentialOwners_Completed() ) {
			return true;
		}
		return result;
	}
	
	public void onEntry(StateMachineToken token) {
	}
	
	public void setNumberOfPotentialOwnersToReady(NumberOfPotentialOwnersToReady NumberOfPotentialOwnersToReady) {
		this.NumberOfPotentialOwnersToReady=NumberOfPotentialOwnersToReady;
	}
	
	public void setNumberOfPotentialOwnersToReserved(NumberOfPotentialOwnersToReserved NumberOfPotentialOwnersToReserved) {
		this.NumberOfPotentialOwnersToReserved=NumberOfPotentialOwnersToReserved;
	}

}