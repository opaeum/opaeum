package org.opaeum.demo1.structuredbusiness.branch.branch.preparequote.region1;

import org.opaeum.demo1.structuredbusiness.branch.Branch;
import org.opaeum.demo1.structuredbusiness.branch.branch.PrepareQuote;
import org.opaeum.demo1.structuredbusiness.branch.branch.PrepareQuoteToken;
import org.opaeum.demo1.structuredbusiness.branch.branch.preparequote.Region1;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class Transition1<SME extends PrepareQuote> extends TransitionInstance<SME, PrepareQuoteToken<SME>> {
	static public String ID = "914890@_QOqWYBYbEeKsDbmQL25eBw";

	/** Constructor for Transition1
	 * 
	 * @param regionActivation 
	 */
	public Transition1(Region1 regionActivation) {
	super(ID,regionActivation,Submitted.ID,Approved.ID);
		((Submitted)getSource()).setTransition1(this);
	}

	public SME getBehaviorExecution() {
		SME result = (SME)super.getBehaviorExecution();
		
		return result;
	}
	
	public Branch getContextObject() {
		Branch result = ((SME)super.getBehaviorExecution()).getContextObject();
		
		return result;
	}
	
	public boolean onOccurrenceOfTransition1ChangeEvent() {
		boolean result = false;
		result=true;
		PrepareQuoteToken<SME> token= getMainSource().exit();
		super.onEntry(token);
		getMainTarget().enter(token,target);
		super.onExit(token);
		return result;
	}

}