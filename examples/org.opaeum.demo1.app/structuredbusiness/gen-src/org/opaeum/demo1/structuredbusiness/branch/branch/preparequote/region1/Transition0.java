package org.opaeum.demo1.structuredbusiness.branch.branch.preparequote.region1;

import org.opaeum.demo1.structuredbusiness.appliance.ProductAnnouncement;
import org.opaeum.demo1.structuredbusiness.branch.Branch;
import org.opaeum.demo1.structuredbusiness.branch.branch.PrepareQuote;
import org.opaeum.demo1.structuredbusiness.branch.branch.PrepareQuoteToken;
import org.opaeum.demo1.structuredbusiness.branch.branch.preparequote.Region1;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class Transition0<SME extends PrepareQuote> extends TransitionInstance<SME, PrepareQuoteToken<SME>> {
	static public String ID = "914890@_NwXvABYbEeKsDbmQL25eBw";

	/** Constructor for Transition0
	 * 
	 * @param regionActivation 
	 */
	public Transition0(Region1 regionActivation) {
	super(ID,regionActivation,Initial.ID,Draft.ID);
		((Initial)getSource()).setTransition0(this);
	}

	public boolean consumeProductAnnouncementEvent(ProductAnnouncement signal) {
		boolean result = false;
		if ( (getStateMachineExecution().getLll() == 3) ) {
			result=true;
			PrepareQuoteToken<SME> token= getMainSource().exit();
			super.onEntry(token);
			getMainTarget().enter(token,target);
			super.onExit(token);
		}
		return result;
	}
	
	public SME getBehaviorExecution() {
		SME result = (SME)super.getBehaviorExecution();
		
		return result;
	}
	
	public Branch getContextObject() {
		Branch result = ((SME)super.getBehaviorExecution()).getContextObject();
		
		return result;
	}

}