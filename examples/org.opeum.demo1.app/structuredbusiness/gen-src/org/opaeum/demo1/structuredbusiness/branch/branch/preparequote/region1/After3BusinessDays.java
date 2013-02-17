package org.opaeum.demo1.structuredbusiness.branch.branch.preparequote.region1;

import java.util.Date;

import org.opaeum.demo1.structuredbusiness.branch.Branch;
import org.opaeum.demo1.structuredbusiness.branch.branch.PrepareQuote;
import org.opaeum.demo1.structuredbusiness.branch.branch.PrepareQuoteToken;
import org.opaeum.demo1.structuredbusiness.branch.branch.preparequote.Region1;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class After3BusinessDays<SME extends PrepareQuote> extends TransitionInstance<SME, PrepareQuoteToken<SME>> {
	static public String ID = "914890@_O1gLEBYbEeKsDbmQL25eBw";

	/** Constructor for After3BusinessDays
	 * 
	 * @param regionActivation 
	 */
	public After3BusinessDays(Region1 regionActivation) {
	super(ID,regionActivation,Draft.ID,Escalated.ID);
		((Draft)getSource()).setAfter3BusinessDays(this);
	}

	public SME getBehaviorExecution() {
		SME result = (SME)super.getBehaviorExecution();
		
		return result;
	}
	
	public Branch getContextObject() {
		Branch result = ((SME)super.getBehaviorExecution()).getContextObject();
		
		return result;
	}
	
	public boolean onOccurrenceOfSubmittedTimeEvent(Date triggerDate) {
		boolean result = false;
		result=true;
		PrepareQuoteToken<SME> token= getMainSource().exit();
		super.onEntry(token);
		getMainTarget().enter(token,target);
		super.onExit(token);
		return result;
	}

}