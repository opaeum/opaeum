package org.opaeum.demo1.structuredbusiness.branch.branch.preparequote.region1;

import java.util.Date;
import java.util.Set;

import javax.persistence.Transient;

import org.opaeum.demo1.structuredbusiness.branch.Branch;
import org.opaeum.demo1.structuredbusiness.branch.branch.PrepareQuote;
import org.opaeum.demo1.structuredbusiness.branch.branch.PrepareQuoteState;
import org.opaeum.demo1.structuredbusiness.branch.branch.PrepareQuoteToken;
import org.opaeum.demo1.structuredbusiness.branch.branch.preparequote.Region1;
import org.opaeum.demo1.structuredbusiness.branch.branch.preparequote.SubmittedTimeEventHandler;
import org.opaeum.runtime.bpm.businesscalendar.BusinessCalendar;
import org.opaeum.runtime.domain.BusinessTimeUnit;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.domain.TriggerMethod;
import org.opaeum.runtime.statemachines.StateActivation;

public class Draft<SME extends PrepareQuote> extends StateActivation<SME, PrepareQuoteToken<SME>> {
	@Transient
	private After3BusinessDays<SME> After3BusinessDays;
	static public String ID = "914890@_Jx4kYBYUEeKsDbmQL25eBw";
	@Transient
	private OnSubmit<SME> OnSubmit;

	/** Constructor for Draft
	 * 
	 * @param region 
	 */
	public Draft(Region1<SME> region) {
	super(ID,region);
	}

	public After3BusinessDays<SME> getAfter3BusinessDays() {
		return this.After3BusinessDays;
	}
	
	public SME getBehaviorExecution() {
		SME result = (SME)super.getBehaviorExecution();
		
		return result;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		Set result = getBehaviorExecution().getCancelledEvents();
		
		return result;
	}
	
	public Branch getContextObject() {
		Branch result = ((PrepareQuote)super.getBehaviorExecution()).getContextObject();
		
		return result;
	}
	
	public String getHumanName() {
		String result = "Draft";
		
		return result;
	}
	
	public OnSubmit<SME> getOnSubmit() {
		return this.OnSubmit;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		Set result = getBehaviorExecution().getOutgoingEvents();
		
		return result;
	}
	
	public TriggerMethod[] getTriggerMethods() {
		TriggerMethod[] result = new TriggerMethod[]{new TriggerMethod(false,"Transition2 call event","Transition2CallEvent")};
		
		return result;
	}
	
	public boolean onCompletion() {
		boolean result = false;
		
		return result;
	}
	
	public void onEntry(PrepareQuoteToken token) {
		super.onEntry(token);
		getOutgoingEvents().add(new OutgoingEvent(this,new SubmittedTimeEventHandler(BusinessCalendar.getInstance().addTimeTo(new Date(), BusinessTimeUnit.BUSINESSDAY,3),token)));
	}
	
	public void onExit() {
		getCancelledEvents().add(new CancelledEvent(this,"914890@_TYw30BcAEeK9SoQSQYApGA"));
	}
	
	public void setAfter3BusinessDays(After3BusinessDays<SME> After3BusinessDays) {
		this.After3BusinessDays=After3BusinessDays;
	}
	
	public void setOnSubmit(OnSubmit<SME> OnSubmit) {
		this.OnSubmit=OnSubmit;
	}

}