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
import structuredbusiness.branch.BusinessStateMachine1State;
import structuredbusiness.branch.BusinessStateMachine1Token;
import structuredbusiness.branch.businessstatemachine1.Region1;
import structuredbusiness.branch.businessstatemachine1.SubmittedTimeEventHandler;

public class Draft<SME extends BusinessStateMachine1> extends StateActivation<SME, BusinessStateMachine1Token<SME>> {
	static public String ID = "914890@_Jx4kYBYUEeKsDbmQL25eBw";
	@Transient
	private Submitted<SME> Submitted;

	/** Constructor for Draft
	 * 
	 * @param region 
	 */
	public Draft(Region1<SME> region) {
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
		String result = "Draft";
		
		return result;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		Set<OutgoingEvent> result = getBehaviorExecution().getOutgoingEvents();
		
		return result;
	}
	
	public Submitted<SME> getSubmitted() {
		return this.Submitted;
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
		getOutgoingEvents().add(new OutgoingEvent(this,new SubmittedTimeEventHandler(ERROR IN OCL:Ocl expression required,BusinessTimeUnit.CALENDARDAY,token)));
	}
	
	public void onExit() {
		getCancelledEvents().add(new CancelledEvent(this,"914890@_TYw30BcAEeK9SoQSQYApGA"));
	}
	
	public void setSubmitted(Submitted<SME> Submitted) {
		this.Submitted=Submitted;
	}

}