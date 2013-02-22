package org.opaeum.runtime.bpm.request.abstractrequest.region1;

import java.util.Set;

import org.opaeum.runtime.bpm.request.AbstractRequest;
import org.opaeum.runtime.bpm.request.AbstractRequestToken;
import org.opaeum.runtime.bpm.request.abstractrequest.Region1;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.statemachines.PseudoStateActivation;

public class InitialNode<SME extends AbstractRequest> extends PseudoStateActivation<SME, AbstractRequestToken<SME>> {
	static public String ID = "252060@_JKNVEPNREeGhGoYzbfvy4A";

	/** Constructor for InitialNode
	 * 
	 * @param region 
	 */
	public InitialNode(Region1<SME> region) {
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
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		Set result = getBehaviorExecution().getOutgoingEvents();
		
		return result;
	}
	
	public boolean onCompletion() {
		boolean result = false;
		
		return result;
	}

}