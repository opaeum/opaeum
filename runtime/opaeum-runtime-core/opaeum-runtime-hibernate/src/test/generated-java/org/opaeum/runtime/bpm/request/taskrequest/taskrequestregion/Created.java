package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion;

import java.util.Set;

import javax.persistence.Transient;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.taskrequest.TaskRequestRegion;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.domain.TriggerMethod;

public class Created<SME extends TaskRequest> extends org.opaeum.runtime.bpm.request.abstractrequest.region1.Created<SME> {
	@Transient
	private CreatedToActive<SME> CreatedToActive;
	static public String ID = "252060@_Qtk5sIoaEeCPduia_-NbFw";

	/** Constructor for Created
	 * 
	 * @param region 
	 */
	public Created(TaskRequestRegion<SME> region) {
	super(region);
		getBehaviorExecution().getExecutionElements().put(ID, this);
	}

	public SME getBehaviorExecution() {
		SME result = (SME)super.getBehaviorExecution();
		
		return result;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		Set result = getBehaviorExecution().getCancelledEvents();
		
		return result;
	}
	
	public CreatedToActive<SME> getCreatedToActive() {
		return this.CreatedToActive;
	}
	
	public String getHumanName() {
		String result = "Created";
		
		return result;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		Set result = getBehaviorExecution().getOutgoingEvents();
		
		return result;
	}
	
	public TriggerMethod[] getTriggerMethods() {
		TriggerMethod[] result = new TriggerMethod[]{new TriggerMethod(false,"On activate","OnActivate")};
		
		return result;
	}
	
	public boolean onCompletion() {
		boolean result = false;
		
		return result;
	}
	
	public void setCreatedToActive(CreatedToActive<SME> CreatedToActive) {
		this.CreatedToActive=CreatedToActive;
	}

}