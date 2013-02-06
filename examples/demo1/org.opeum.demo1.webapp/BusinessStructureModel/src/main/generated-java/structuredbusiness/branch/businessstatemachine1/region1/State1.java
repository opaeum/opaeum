package structuredbusiness.branch.businessstatemachine1.region1;

import java.util.Set;

import javax.persistence.Transient;

import org.opaeum.runtime.domain.BusinessTimeUnit;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.domain.TriggerMethod;
import org.opaeum.runtime.statemachines.StateActivation;

import structuredbusiness.Branch;
import structuredbusiness.branch.BusinessStateMachine1;
import structuredbusiness.branch.BusinessStateMachine1Token;
import structuredbusiness.branch.businessstatemachine1.Region1;
import structuredbusiness.branch.businessstatemachine1.Transition1TimeEventHandler;

public class State1<SME extends BusinessStateMachine1> extends StateActivation<SME, BusinessStateMachine1Token<SME>> {
	static public String ID = "914890@_KIj_ABYUEeKsDbmQL25eBw";
	@Transient
	private Transition1<SME> Transition1;

	/** Constructor for State1
	 * 
	 * @param region 
	 */
	public State1(Region1<SME> region) {
	super(ID,region);
	}

	public SME getBehaviorExecution() {
		SME result = (SME)super.getBehaviorExecution();
		
		return result;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		Set<CancelledEvent> result = getBehaviorExecution().getCancelledEvents();
		
		return result;
	}
	
	public Branch getContextObject() {
		Branch result = ((BusinessStateMachine1)super.getBehaviorExecution()).getContextObject();
		
		return result;
	}
	
	public String getHumanName() {
		String result = "State1";
		
		return result;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		Set<OutgoingEvent> result = getBehaviorExecution().getOutgoingEvents();
		
		return result;
	}
	
	public Transition1<SME> getTransition1() {
		return this.Transition1;
	}
	
	public TriggerMethod[] getTriggerMethods() {
		TriggerMethod[] result = new TriggerMethod[]{};
		
		return result;
	}
	
	public boolean onCompletion() {
		boolean result = false;
		
		return result;
	}
	
	public void onEntry(BusinessStateMachine1Token token) {
		super.onEntry(token);
		this.firstOccurrenceScheduledFor=BusinessCalendar.getInstance().addTimeTo(new Date(), timeUnit,delay);
		getOutgoingEvents().add(new OutgoingEvent(this,new Transition1TimeEventHandler(123,BusinessTimeUnit.ACTUALHOUR,token)));
	}
	
	public void onExit() {
		getCancelledEvents().add(new CancelledEvent(this,"914890@_eu1uUBb3EeKI68QaBu0uBA"));
	}
	
	public void setTransition1(Transition1<SME> Transition1) {
		this.Transition1=Transition1;
	}

}