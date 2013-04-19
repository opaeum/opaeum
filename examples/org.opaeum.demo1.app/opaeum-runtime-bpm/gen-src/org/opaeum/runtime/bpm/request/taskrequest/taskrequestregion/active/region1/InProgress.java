package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1;

import java.util.Set;

import javax.persistence.Transient;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestState;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.InProgressToSuspended;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.Region1;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.domain.TriggerMethod;
import org.opaeum.runtime.statemachines.StateActivation;

public class InProgress<SME extends TaskRequest> extends StateActivation<SME, TaskRequestToken<SME>> {
	static public String ID = "252060@_RMUEIIoaEeCPduia_-NbFw";
	@Transient
	private InProgressToReserved<SME> InProgressToReserved;
	@Transient
	private InProgressToSuspended<SME> InProgressToSuspended;
	@Transient
	private Transition5<SME> Transition5;

	/** Constructor for InProgress
	 * 
	 * @param region 
	 */
	public InProgress(Region1<SME> region) {
	super(ID,region);
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
		String result = "In progress";
		
		return result;
	}
	
	public InProgressToReserved<SME> getInProgressToReserved() {
		return this.InProgressToReserved;
	}
	
	public InProgressToSuspended<SME> getInProgressToSuspended() {
		return this.InProgressToSuspended;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		Set result = getBehaviorExecution().getOutgoingEvents();
		
		return result;
	}
	
	public Transition5<SME> getTransition5() {
		return this.Transition5;
	}
	
	public TriggerMethod[] getTriggerMethods() {
		TriggerMethod[] result = new TriggerMethod[]{new TriggerMethod(false,"Stopped","Stopped"),new TriggerMethod(false,"On complete","OnComplete"),new TriggerMethod(false,"Suspended","Suspended")};
		
		return result;
	}
	
	public boolean onCompletion() {
		boolean result = false;
		
		return result;
	}
	
	public void onEntry(TaskRequestToken token) {
		super.onEntry(token);
		getStateMachineExecution().setHistory(ID);
	}
	
	public void onExit() {
	}
	
	public void setInProgressToReserved(InProgressToReserved<SME> InProgressToReserved) {
		this.InProgressToReserved=InProgressToReserved;
	}
	
	public void setInProgressToSuspended(InProgressToSuspended<SME> InProgressToSuspended) {
		this.InProgressToSuspended=InProgressToSuspended;
	}
	
	public void setTransition5(Transition5<SME> Transition5) {
		this.Transition5=Transition5;
	}

}