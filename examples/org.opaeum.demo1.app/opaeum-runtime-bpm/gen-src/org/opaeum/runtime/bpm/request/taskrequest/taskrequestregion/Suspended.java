package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion;

import java.util.Set;

import javax.persistence.Transient;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.taskrequest.TaskRequestRegion;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.suspended.Region1;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.domain.TriggerMethod;

public class Suspended<SME extends TaskRequest> extends org.opaeum.runtime.bpm.request.abstractrequest.region1.Suspended<SME> {
	static public String ID = "252060@_eXmqMKCWEeCmJqvPP4zbUw";
	@Transient
	private SuspendedToActive<SME> SuspendedToActive;

	/** Constructor for Suspended
	 * 
	 * @param region 
	 */
	public Suspended(TaskRequestRegion<SME> region) {
	super(region);
		getBehaviorExecution().getExecutionElements().put(ID, this);
		regions.add(new Region1(this));
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
		String result = "Suspended";
		
		return result;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		Set result = getBehaviorExecution().getOutgoingEvents();
		
		return result;
	}
	
	public SuspendedToActive<SME> getSuspendedToActive() {
		return this.SuspendedToActive;
	}
	
	public TriggerMethod[] getTriggerMethods() {
		TriggerMethod[] result = new TriggerMethod[]{new TriggerMethod(false,"Resumed","Resumed")};
		
		return result;
	}
	
	public boolean onCompletion() {
		boolean result = false;
		
		return result;
	}
	
	public void setSuspendedToActive(SuspendedToActive<SME> SuspendedToActive) {
		this.SuspendedToActive=SuspendedToActive;
	}

}