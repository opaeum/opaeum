package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion;

import javax.persistence.Transient;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestState;
import org.opaeum.runtime.bpm.request.taskrequest.TaskRequestRegion;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.Region1;
import org.opaeum.runtime.statemachines.StateActivation;

public class Active extends StateActivation {
	@Transient
	private ActiveToCompleted ActiveToCompleted;
	@Transient
	private ActiveToInProgress ActiveToInProgress;
	@Transient
	private ActiveToReady ActiveToReady;
	@Transient
	private ActiveToReserved ActiveToReserved;
	@Transient
	private AtiveToObsolete AtiveToObsolete;

	/** Constructor for Active
	 * 
	 * @param region 
	 */
	public Active(TaskRequestRegion region) {
	super("252060@_XwDj4KCVEeCmJqvPP4zbUw",region);
		regions.add(new Region1(this));
	}

	public ActiveToCompleted getActiveToCompleted() {
		return this.ActiveToCompleted;
	}
	
	public ActiveToInProgress getActiveToInProgress() {
		return this.ActiveToInProgress;
	}
	
	public ActiveToReady getActiveToReady() {
		return this.ActiveToReady;
	}
	
	public ActiveToReserved getActiveToReserved() {
		return this.ActiveToReserved;
	}
	
	public AtiveToObsolete getAtiveToObsolete() {
		return this.AtiveToObsolete;
	}
	
	public TaskRequest getStateMachineExecution() {
		TaskRequest result = (TaskRequest)super.getStateMachineExecution();
		
		return result;
	}
	
	public boolean onComplete() {
		boolean result = false;
		if ( ActiveToCompleted.onActiveCompleted() ) {
		
		}
		return result;
	}
	
	public void setActiveToCompleted(ActiveToCompleted ActiveToCompleted) {
		this.ActiveToCompleted=ActiveToCompleted;
	}
	
	public void setActiveToInProgress(ActiveToInProgress ActiveToInProgress) {
		this.ActiveToInProgress=ActiveToInProgress;
	}
	
	public void setActiveToReady(ActiveToReady ActiveToReady) {
		this.ActiveToReady=ActiveToReady;
	}
	
	public void setActiveToReserved(ActiveToReserved ActiveToReserved) {
		this.ActiveToReserved=ActiveToReserved;
	}
	
	public void setAtiveToObsolete(AtiveToObsolete AtiveToObsolete) {
		this.AtiveToObsolete=AtiveToObsolete;
	}

}