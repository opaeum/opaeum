package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion;

import java.util.Set;

import javax.persistence.Transient;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.TaskRequestRegion;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.Region1;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.domain.TriggerMethod;

public class Active<SME extends TaskRequest> extends org.opaeum.runtime.bpm.request.abstractrequest.region1.Active<SME> {
	@Transient
	private ActiveToCompleted<SME> ActiveToCompleted;
	@Transient
	private ActiveToInProgress<SME> ActiveToInProgress;
	@Transient
	private ActiveToReady<SME> ActiveToReady;
	@Transient
	private ActiveToReserved<SME> ActiveToReserved;
	@Transient
	private AtiveToObsolete<SME> AtiveToObsolete;
	static public String ID = "252060@_XwDj4KCVEeCmJqvPP4zbUw";

	/** Constructor for Active
	 * 
	 * @param region 
	 */
	public Active(TaskRequestRegion<SME> region) {
	super(region);
		getBehaviorExecution().getExecutionElements().put(ID, this);
		regions.add(new Region1(this));
	}

	public ActiveToCompleted<SME> getActiveToCompleted() {
		return this.ActiveToCompleted;
	}
	
	public ActiveToInProgress<SME> getActiveToInProgress() {
		return this.ActiveToInProgress;
	}
	
	public ActiveToReady<SME> getActiveToReady() {
		return this.ActiveToReady;
	}
	
	public ActiveToReserved<SME> getActiveToReserved() {
		return this.ActiveToReserved;
	}
	
	public AtiveToObsolete<SME> getAtiveToObsolete() {
		return this.AtiveToObsolete;
	}
	
	public SME getBehaviorExecution() {
		SME result = (SME)super.getBehaviorExecution();
		
		return result;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		Set result = getBehaviorExecution().getCancelledEvents();
		
		return result;
	}
	
	public String getHumanName() {
		String result = "Active";
		
		return result;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		Set result = getBehaviorExecution().getOutgoingEvents();
		
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
	
	public void onEntry(TaskRequestToken token) {
		super.onEntry(token);
	}
	
	public void onExit() {
	}
	
	public void setActiveToCompleted(ActiveToCompleted<SME> ActiveToCompleted) {
		this.ActiveToCompleted=ActiveToCompleted;
	}
	
	public void setActiveToInProgress(ActiveToInProgress<SME> ActiveToInProgress) {
		this.ActiveToInProgress=ActiveToInProgress;
	}
	
	public void setActiveToReady(ActiveToReady<SME> ActiveToReady) {
		this.ActiveToReady=ActiveToReady;
	}
	
	public void setActiveToReserved(ActiveToReserved<SME> ActiveToReserved) {
		this.ActiveToReserved=ActiveToReserved;
	}
	
	public void setAtiveToObsolete(AtiveToObsolete<SME> AtiveToObsolete) {
		this.AtiveToObsolete=AtiveToObsolete;
	}

}