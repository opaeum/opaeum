package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion;

import javax.persistence.Transient;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestState;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.TaskRequestRegion;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.Region1;
import org.opaeum.runtime.domain.TriggerMethod;
import org.opaeum.runtime.statemachines.StateActivation;

public class Active extends StateActivation<TaskRequest, TaskRequestToken> {
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
	static public String ID = "252060@_XwDj4KCVEeCmJqvPP4zbUw";

	/** Constructor for Active
	 * 
	 * @param region 
	 */
	public Active(TaskRequestRegion region) {
	super(ID,region);
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
	
	public TaskRequest getBehaviorExecution() {
		TaskRequest result = (TaskRequest)super.getBehaviorExecution();
		
		return result;
	}
	
	public String getHumanName() {
		String result = "Active";
		
		return result;
	}
	
	public TriggerMethod[] getTriggerMethods() {
		TriggerMethod[] result = new TriggerMethod[]{new TriggerMethod(false,"Forwarded","Forwarded"),new TriggerMethod(false,"Started","Started"),new TriggerMethod(false,"On skip","OnSkip"),new TriggerMethod(false,"Delegated","Delegated")};
		
		return result;
	}
	
	public boolean onCompletion() {
		boolean result = false;
		if ( ActiveToCompleted.onActiveCompleted() ) {
			return true;
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