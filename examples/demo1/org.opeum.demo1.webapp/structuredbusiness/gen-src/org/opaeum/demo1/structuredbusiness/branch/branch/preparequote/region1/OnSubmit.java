package org.opaeum.demo1.structuredbusiness.branch.branch.preparequote.region1;

import org.opaeum.demo1.structuredbusiness.branch.Branch;
import org.opaeum.demo1.structuredbusiness.branch.branch.PrepareQuote;
import org.opaeum.demo1.structuredbusiness.branch.branch.PrepareQuoteToken;
import org.opaeum.demo1.structuredbusiness.branch.branch.preparequote.Region1;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class OnSubmit<SME extends PrepareQuote> extends TransitionInstance<SME, PrepareQuoteToken<SME>> {
	static public String ID = "914890@_f7m08HgGEeKNG8mFSp3Ijg";

	/** Constructor for OnSubmit
	 * 
	 * @param regionActivation 
	 */
	public OnSubmit(Region1 regionActivation) {
	super(ID,regionActivation,Draft.ID,Submitted.ID);
		((Draft)getSource()).setOnSubmit(this);
	}

	public boolean consumeSubmitOccurrence() {
		boolean result = false;
		result=true;
		PrepareQuoteToken<SME> token= getMainSource().exit();
		super.onEntry(token);
		getMainTarget().enter(token,target);
		super.onExit(token);
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