package structuredbusiness.branch.businessstatemachine1.region1;

import org.opaeum.runtime.statemachines.TransitionInstance;

import structuredbusiness.Branch;
import structuredbusiness.ProductAnnouncement;
import structuredbusiness.branch.BusinessStateMachine1;
import structuredbusiness.branch.BusinessStateMachine1Token;
import structuredbusiness.branch.businessstatemachine1.Region1;

public class Transition0<SME extends BusinessStateMachine1> extends TransitionInstance<SME, BusinessStateMachine1Token<SME>> {
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
			BusinessStateMachine1Token<SME> token= getMainSource().exit();
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