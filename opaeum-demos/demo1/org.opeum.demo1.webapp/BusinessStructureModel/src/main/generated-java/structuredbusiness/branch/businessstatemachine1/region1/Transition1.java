package structuredbusiness.branch.businessstatemachine1.region1;

import java.util.Date;

import org.opaeum.runtime.statemachines.TransitionInstance;

import structuredbusiness.Branch;
import structuredbusiness.branch.BusinessStateMachine1;
import structuredbusiness.branch.BusinessStateMachine1Token;
import structuredbusiness.branch.businessstatemachine1.Region1;

public class Transition1<SME extends BusinessStateMachine1> extends TransitionInstance<SME, BusinessStateMachine1Token<SME>> {
	static public String ID = "914890@_QOqWYBYbEeKsDbmQL25eBw";

	/** Constructor for Transition1
	 * 
	 * @param regionActivation 
	 */
	public Transition1(Region1 regionActivation) {
	super(ID,regionActivation,State1.ID,Approved.ID);
		((State1)getSource()).setTransition1(this);
	}

	public SME getBehaviorExecution() {
		SME result = (SME)super.getBehaviorExecution();
		
		return result;
	}
	
	public Branch getContextObject() {
		Branch result = ((SME)super.getBehaviorExecution()).getContextObject();
		
		return result;
	}
	
	public boolean onOccurrenceOfTransition1TimeEvent(Date triggerDate) {
		boolean result = false;
		result=true;
		BusinessStateMachine1Token<SME> token= getMainSource().exit();
		super.onEntry(token);
		getMainTarget().enter(token,target);
		super.onExit(token);
		return result;
	}

}