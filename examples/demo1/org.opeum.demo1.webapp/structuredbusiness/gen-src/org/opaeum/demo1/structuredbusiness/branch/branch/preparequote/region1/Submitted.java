package org.opaeum.demo1.structuredbusiness.branch.branch.preparequote.region1;

import java.util.Set;

import javax.persistence.Transient;

import org.opaeum.demo1.structuredbusiness.branch.Branch;
import org.opaeum.demo1.structuredbusiness.branch.branch.PrepareQuote;
import org.opaeum.demo1.structuredbusiness.branch.branch.PrepareQuoteState;
import org.opaeum.demo1.structuredbusiness.branch.branch.PrepareQuoteToken;
import org.opaeum.demo1.structuredbusiness.branch.branch.preparequote.Region1;
import org.opaeum.demo1.structuredbusiness.branch.branch.preparequote.Transition1ChangeEventHandler;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.domain.TriggerMethod;
import org.opaeum.runtime.statemachines.StateActivation;

public class Submitted<SME extends PrepareQuote> extends StateActivation<SME, PrepareQuoteToken<SME>> {
	static public String ID = "914890@_KIj_ABYUEeKsDbmQL25eBw";
	@Transient
	private Transition1<SME> Transition1;

	/** Constructor for Submitted
	 * 
	 * @param region 
	 */
	public Submitted(Region1<SME> region) {
	super(ID,region);
	}

	public boolean evaluateTransition1ChangeEvent() {
		return this.getCustomerApproved();
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
		Branch result = ((PrepareQuote)super.getBehaviorExecution()).getContextObject();
		
		return result;
	}
	
	public String getHumanName() {
		String result = "Submitted";
		
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
	
	public void onEntry(PrepareQuoteToken token) {
		super.onEntry(token);
		getOutgoingEvents().add(new OutgoingEvent(this, new Transition1ChangeEventHandler(token));
	}
	
	public void onExit() {
		getCancelledEvents().add(new CancelledEvent(this,"914890@_q8beEHgGEeKNG8mFSp3Ijg"));
	}
	
	public void setTransition1(Transition1<SME> Transition1) {
		this.Transition1=Transition1;
	}

}