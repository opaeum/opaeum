package structuredbusiness.branch.businessstatemachine1.region1;

import java.util.Set;

import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.domain.TriggerMethod;
import org.opaeum.runtime.statemachines.StateActivation;

import structuredbusiness.Branch;
import structuredbusiness.branch.BusinessStateMachine1;
import structuredbusiness.branch.BusinessStateMachine1Token;
import structuredbusiness.branch.businessstatemachine1.Region1;

public class Approved<SME extends BusinessStateMachine1> extends StateActivation<SME, BusinessStateMachine1Token<SME>> {
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
		Set<CancelledEvent> result = getBehaviorExecution().getCancelledEvents();
		
		return result;
	}
	
	public Branch getContextObject() {
		Branch result = ((BusinessStateMachine1)super.getBehaviorExecution()).getContextObject();
		
		return result;
	}
	
	public String getHumanName() {
		String result = "Approved";
		
		return result;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		Set<OutgoingEvent> result = getBehaviorExecution().getOutgoingEvents();
		
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