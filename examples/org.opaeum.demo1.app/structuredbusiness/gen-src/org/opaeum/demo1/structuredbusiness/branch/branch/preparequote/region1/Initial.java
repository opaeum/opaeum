package org.opaeum.demo1.structuredbusiness.branch.branch.preparequote.region1;

import java.util.Set;

import javax.persistence.Transient;

import org.opaeum.demo1.structuredbusiness.branch.Branch;
import org.opaeum.demo1.structuredbusiness.branch.branch.PrepareQuote;
import org.opaeum.demo1.structuredbusiness.branch.branch.PrepareQuoteToken;
import org.opaeum.demo1.structuredbusiness.branch.branch.preparequote.Region1;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.statemachines.PseudoStateActivation;

public class Initial<SME extends PrepareQuote> extends PseudoStateActivation<SME, PrepareQuoteToken<SME>> {
	static public String ID = "914890@_MwyhUBYbEeKsDbmQL25eBw";
	@Transient
	private Transition0<SME> Transition0;

	/** Constructor for Initial
	 * 
	 * @param region 
	 */
	public Initial(Region1<SME> region) {
	super(ID,region);
		setInitial(true);
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
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		Set result = getBehaviorExecution().getOutgoingEvents();
		
		return result;
	}
	
	public Transition0<SME> getTransition0() {
		return this.Transition0;
	}
	
	public boolean onCompletion() {
		boolean result = false;
		
		return result;
	}
	
	public void setTransition0(Transition0<SME> Transition0) {
		this.Transition0=Transition0;
	}

}