package org.opaeum.demo1.structuredbusiness.branch.branch.preparequote.region1;

import java.util.Set;

import org.opaeum.demo1.structuredbusiness.branch.Branch;
import org.opaeum.demo1.structuredbusiness.branch.branch.PrepareQuote;
import org.opaeum.demo1.structuredbusiness.branch.branch.PrepareQuoteState;
import org.opaeum.demo1.structuredbusiness.branch.branch.PrepareQuoteToken;
import org.opaeum.demo1.structuredbusiness.branch.branch.preparequote.Region1;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.domain.TriggerMethod;
import org.opaeum.runtime.statemachines.StateActivation;

public class Approved<SME extends PrepareQuote> extends StateActivation<SME, PrepareQuoteToken<SME>> {
	static public String ID = "914890@_Kd2SgBYUEeKsDbmQL25eBw";

	/** Constructor for Approved
	 * 
	 * @param region 
	 */
	public Approved(Region1<SME> region) {
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
	
	public Branch getContextObject() {
		Branch result = ((PrepareQuote)super.getBehaviorExecution()).getContextObject();
		
		return result;
	}
	
	public String getHumanName() {
		String result = "Approved";
		
		return result;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		Set result = getBehaviorExecution().getOutgoingEvents();
		
		return result;
	}
	
	public TriggerMethod[] getTriggerMethods() {
		TriggerMethod[] result = new TriggerMethod[]{};
		
		return result;
	}
	
	public boolean onCompletion() {
		boolean result = false;
		
		return result;
	}

}