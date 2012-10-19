package structuredbusiness.branch.businessstatemachine1.region1;

import java.util.Date;

import org.opaeum.runtime.statemachines.TransitionInstance;

import structuredbusiness.Branch;
import structuredbusiness.branch.BusinessStateMachine1;
import structuredbusiness.branch.BusinessStateMachine1Token;
import structuredbusiness.branch.businessstatemachine1.Region1;

public class Submitted<SME extends BusinessStateMachine1> extends TransitionInstance<SME, BusinessStateMachine1Token<SME>> {
	static public String ID = "914890@_O1gLEBYbEeKsDbmQL25eBw";

	/** Constructor for Submitted
	 * 
	 * @param regionActivation 
	 */
	public Submitted(Region1 regionActivation) {
	super(ID,regionActivation,Draft.ID,State1.ID);
		((Draft)getSource()).setSubmitted(this);
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
		BusinessStateMachine1Token<SME> token= getMainSource().exit();
		super.onEntry(token);
		getMainTarget().enter(token,target);
		super.onExit(token);
		return result;
	}

}