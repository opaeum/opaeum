package org.opaeum.demo1.structuredbusiness.appliancedoctor.businessstatemachine1.region1;

import java.util.Set;

import org.opaeum.demo1.structuredbusiness.ApplianceDoctor;
import org.opaeum.demo1.structuredbusiness.appliancedoctor.BusinessStateMachine1;
import org.opaeum.demo1.structuredbusiness.appliancedoctor.BusinessStateMachine1State;
import org.opaeum.demo1.structuredbusiness.appliancedoctor.BusinessStateMachine1Token;
import org.opaeum.demo1.structuredbusiness.appliancedoctor.businessstatemachine1.Region1;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.domain.TriggerMethod;
import org.opaeum.runtime.statemachines.StateActivation;

public class State0<SME extends BusinessStateMachine1> extends StateActivation<SME, BusinessStateMachine1Token<SME>> {
	static public String ID = "914890@_eLtBsH2lEeK5F45wEGRv4A";

	/** Constructor for State0
	 * 
	 * @param region 
	 */
	public State0(Region1<SME> region) {
	super(ID,region);
	}

	public SME getBehaviorExecution() {
		SME result = (SME)super.getBehaviorExecution();
		
		return result;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		Set result = getBehaviorExecution().getCancelledEvents();
		
		return result;
	}
	
	public ApplianceDoctor getContextObject() {
		ApplianceDoctor result = ((BusinessStateMachine1)super.getBehaviorExecution()).getContextObject();
		
		return result;
	}
	
	public String getHumanName() {
		String result = "State0";
		
		return result;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		Set result = getBehaviorExecution().getOutgoingEvents();
		
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